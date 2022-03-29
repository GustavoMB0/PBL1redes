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
    
    public Server(){
   
    }
    
    public boolean start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        while (true){
            new EchoHandler(serverSocket.accept()).start();
        }
    }
    
    public void stop() throws IOException{
        serverSocket.close();
    }
    
    private static class EchoHandler extends Thread{
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        private ClientHandler handler;
       
        public EchoHandler(Socket socket){
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
                            out.println(message);
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
}

