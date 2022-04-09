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
public class Caminhao extends Client{
    private final Scanner reader;
    private String word, lixeiras[];
    
    public Caminhao(String ip, int port) throws IOException{
       super.name = "C";      
       super.connect(ip, port);
       reader = new Scanner(System.in);
    }
    
    public void read() throws IOException{
        word = in.readLine();
        lixeiras = word.split(";");   
    }
    
    public String[] getLixeiras(){
        return lixeiras;
    }
}
