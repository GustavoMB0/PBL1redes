/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Controler;

import java.io.IOException;
import java.util.Scanner;
import uefs.pblsd1.Model.Caminhao;

/**
 *
 * @author gusta
 */
public class Ccaminhao {
    private String lixeiras[];
    private Scanner reader;
    private Caminhao caminhao;
    
    public Ccaminhao(String IP, int port) throws IOException{
        caminhao = new Caminhao(IP, port);
        reader = new Scanner(System.in);
    }
    
    public void start() throws IOException{
        while(true){
            caminhao.read();
            lixeiras = caminhao.getLixeiras();
            printLixeiras();
        }
        //caminhao.closeConnection();
    }
    
    // L + id + capacidade,
    private void printLixeiras(){
        char[] c;
        String aux = "Lixeira ", aux2 = "";
        System.out.print("Lixeiras \t\t Quantidade de lixo \n\n");
        for(String lixo: lixeiras){
            c = lixo.toCharArray();
            aux += c[1];
            System.out.println(aux + "\t\t");
            
            for(int i = 2; i < c.length; i++){
                aux += c[i];
            }
            System.out.println(aux2 + "\n");
        }
    }
}
