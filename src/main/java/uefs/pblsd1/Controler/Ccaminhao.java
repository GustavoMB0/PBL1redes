/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Controler;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import uefs.pblsd1.Model.Caminhao;

/**
 *
 * @author gusta
 */
public class Ccaminhao {
    private String lixeiras[], word;
    private Scanner reader;
    private Caminhao caminhao;
    
    public Ccaminhao(String IP, int port) throws IOException{
        caminhao = new Caminhao(IP, port);
        reader = new Scanner(System.in);
    }
    
    public void start() throws IOException{
        new Ler().start();
        while(true){
            System.out.println("Digite (.) para encerrrar");
            word = reader.nextLine();
            
            if(word.equals(".")){
                caminhao.stop();
                caminhao.closeConnection();
                break;
            }else if(word.equals("M")){
                caminhao.send(word);
            }else{
                System.out.println("Digite um comando valido");
                reader.reset();
            }
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
            System.out.print(aux + "\t\t");
            
            for(int i = 2; i < c.length; i++){
                aux2 += c[i];
            }
            System.out.println(aux2 + "\n");
            aux = "Lixeira ";
            aux2 = "";
        }
    }
    
    private class Ler extends Thread{
        @Override
        public void run(){
            while(caminhao.isRuning())
                try {
                    caminhao.read();
                    lixeiras = caminhao.getLixeiras();
                    printLixeiras();
                } catch (IOException ex) {
                    Logger.getLogger(Cadm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}
