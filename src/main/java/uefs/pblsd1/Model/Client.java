/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Model;

import java.net.*;
import java.io.*;
/**
 *
 * @author gusta
 */
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    protected BufferedReader in;
    protected String name;
    protected boolean run = true;
    
    public Client(){}
    
    protected void connect(String ip, int port) throws IOException{
        clientSocket = new Socket(ip, port);
        out =  new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        out.println(name);
    }
    
    protected void sendMessage(String message) throws IOException{
        out.println(message);
    }
    
    public void closeConnection() throws IOException{
        out.println(".");
        in.close();
        out.close();
        clientSocket.close();
    }
    
    protected String getId(){
        return name;
    }

    public void stop(){
        run = false;
    }
    
    public boolean isRuning(){
        return run;
    }
}
