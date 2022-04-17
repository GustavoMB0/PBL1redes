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
            
            //Elaborar o print de lixeiras, ver se vai ser nesseraio ao inves de mandar uma string, um arquivo
            
        }
        
        //caminhao.closeConnection();
    }
    public void printLixeiras(){
        System.out.println("Lixeiras atribuidas a rota do caminhão");
        for (String lixeira : lixeiras) {
            System.out.println(lixeira);
        }
    }
}
