/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Model;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gusta
 */
public class Administrador extends Client{
    private String word, lixeiras[];
    
    public Administrador(String IP, int port) throws IOException{
        super.connect(IP, port);
    }
    
    public void travar(String lixeira) throws IOException{
        String msg;
        String confirm;
        msg = "TL" + lixeira + ";";
        super.sendMessage(msg);
    }
    
    public void ordem(String lixeira) throws IOException{
        String msg;
        msg = "PL" + lixeira + ";";
        super.sendMessage(msg);
    }
    
    public void read() throws IOException{
        word = in.readLine();
        lixeiras = word.split(",");   
    }
    
    public String[] getLixeiras(){
        return lixeiras;
    }
}
