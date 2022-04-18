/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Model;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gusta
 */
public class Server {
    private ServerSocket serverSocket;
    private String message;
    private final List<Connection> lixeiras;
    private final List<Connection> clients;
    
    public Server(){
        message = null;
        lixeiras = new ArrayList<>();
        clients = new ArrayList<>();
    }
    
    public boolean start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        while (true){
            new Connection(serverSocket.accept()).start();
        }
    }
    
    public void stop() throws IOException{
        serverSocket.close();    
    }
    
    public void mensagemLida(String msg){
        char[] c = msg.toCharArray();

        if(Character.compare(c[0], 'T') == 0){
            travarLixeira(c);
        }else if(Character.compare(c[0], 'P') == 0){
            priorizar(c);
            sendLixeiras();
        }
    }
    
    private float encher(char[] msg){
        String aux = "";
        int i = 1;
        while(msg[i] != ';'){
            aux += msg[i];
            i++;
        }
        return Float.parseFloat(aux);
    }
    
    private void priorizar(char[] msg){
        int num = Integer.parseInt(String.valueOf(msg[2]));
        int a;
        Connection c;
        c = buscarLixeira(num);
        c.prioridade = c.capacidade*100;
        ordenar();
    }
    
    private void travarLixeira(char[] msg){
        int num;
        Connection c;
        num = Integer.parseInt(String.valueOf(msg[2]));
        c = buscarLixeira(num);
        c.sendMessage("T");
    }
    
    private Connection buscarLixeira(int id){
        Iterator it = lixeiras.iterator();
        Connection c;
        while(it.hasNext()){
            c = (Connection) it.next();
            if(c.id == id){
                return c;
            }
        }
        return null;
    }
    
    private void sendLixeiras(){
        for(Connection i: lixeiras){
            message += "L" + i.id + i.capacidade + ",";
        }
        for(Connection b: clients){
            b.sendMessage(message);
        }
    }
    
    private void ordenar(){
        lixeiras.sort(new SortComparator());
    }
    
    
    // Classe privada que sera utilizada nos mapas de conexões, threads que ficam lendo mensagens enviadas pelos clientes e chamando uma outra classe para tratar essas mensagens
    private class Connection extends Thread{
        /* Ideia de colocar dois atributos para conexões de lixeiras, um para capacidade maxima e outro para a quantidade de lixo atual, esses atributos seriam utilizados
        *  somente por lixeiras   
        */ 
        private final Socket clientSocket;
        private int id;
        private PrintWriter out;
        private BufferedReader in;
        private float capacidade = 0, prioridade;
        
        public Connection(Socket socket){
            this.clientSocket = socket;
        }
        
        @Override
        public void run(){
            try {
                String inputLine;
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                inputLine = in.readLine();
                if(inputLine.equals("L")){
                   id = lixeiras.size() + 1;
                   lixeiras.add(this);
                }else{
                    id = clients.size()+1;
                   clients.add(this);
                }
                while (true) {
                    inputLine = in.readLine();
                    if(inputLine != null){
                        if (inputLine.equals(".")) {
                            break;
                        }else if(inputLine.contains("E")){
                            char[] c = inputLine.toCharArray();
                            capacidade += encher(c);
                            ordenar();
                            sendLixeiras();
                        }else if(inputLine.contains("C")){
                            capacidade = 0;
                            ordenar();
                            sendLixeiras();
                        }else{
                            mensagemLida(inputLine);
                        }
                    }
                }
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void sendMessage(String msg){
            out.println(msg);
        }
    }
    
    private class SortComparator implements Comparator<Connection>{

        @Override
        public int compare(Connection o1, Connection o2) {
            if(o1.prioridade > o2.prioridade){
              return -1;
            }else if(o1.prioridade == o2.prioridade){
                if(o1.capacidade > o2.capacidade){
                    return -1;
                }else if(o1.capacidade == o2.capacidade){
                    return 0;
                }else{
                    return 1;
                }
            }else{
                return 0;
            }
        }
    }
}

