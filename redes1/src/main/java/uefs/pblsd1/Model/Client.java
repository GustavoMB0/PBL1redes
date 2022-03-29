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
    private BufferedReader in;
    protected String id;
    
    public Client(String id){
        this.id = id;
    }
    
    protected void connect(String ip, int port) throws IOException{
        clientSocket = new Socket(ip, port);
        out =  new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    protected String sendMessage(String message) throws IOException{
        out.print(message);
        String resp = in.readLine();
        return resp;
    }
    
    public void closeConnection() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
    }
    
    protected void setId(String number){
        id += number;
    }
    
    protected String getId(){
        return id;
    }    
}
