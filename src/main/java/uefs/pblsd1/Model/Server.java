/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Model;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gusta
 */
public class Server {
    private ServerSocket serverSocket;
    private String message;
    //private Map<>
    public Server(){}
    
    public boolean start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        new HandleConnect(serverSocket).start();
        while (true){
            /*
            * Adicionar função de ordenar lixeiras, enviar condição das lixeiras para os outros usuarios e outras mensagens.
            * Pensar em uma forma de salvar a capacidade e ordenar as lixeiras
            */
        }

    }
    
    public void stop() throws IOException{
        serverSocket.close();
    }
    
    // Classe privada que sera utilizada nos mapas de conexões, threads que ficam lendo mensagens enviadas pelos clientes e chamando uma outra classe para tratar essas mensagens
    private static class Connection extends Thread{
        /* Ideia de colocar dois atributos para conexões de lixeiras, um para capacidade maxima e outro para a quantidade de lixo atual, esses atributos seriam utilizados
        *  somente por lixeiras   
        */ 
        private final Socket clientSocket;
        private String id;
        private PrintWriter out;
        private BufferedReader in;
       
        public Connection(Socket socket){
            this.clientSocket = socket;
        }
        
        @Override
        public void run(){
            try {
                String inputLine;
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (true) {
                    inputLine = in.readLine();
                    if(inputLine != null){
                        if (inputLine.equals(".")) {
                            break;
                        }else{
                            out.println(inputLine);
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
    }
    
    private static class HandleConnect extends Thread{
        private final ServerSocket aux;
        public HandleConnect (ServerSocket socket){
            this.aux = socket;
        }
        
        
        // Pensar em uma condição de parada
        @Override
        public void run(){
            while(true){
                try {
                    new Connection(aux.accept()).start();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}

