/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Controler;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import uefs.pblsd1.Model.Administrador;

/**
 *
 * @author gusta
 */
public class Cadm {
    private String word, lixeiras[];
    private final Scanner reader;
    private final Administrador adm;
    
    public Cadm(String IP, int port) throws IOException{
        adm = new Administrador(IP, port);
        reader = new Scanner(System.in);
    }
    
    public void start() throws IOException{
        new Ler().start();
        while(true){
            System.out.println("Digite o comando que deseja executar:(T)ravar, (P)riorizar ou (.) para finalizar ");
            word = reader.nextLine();      
            if(word.equals("T")){
                travar();
            }else if(word.equals("P")){
                priorizar();
            }else if(word.equals(".")){
                adm.stop();
                adm.closeConnection();
                break;
            }else{
                System.out.println("Digite um comando valido");
                reader.reset();
            }
        }
    }
    
    private void travar() throws IOException{
        System.out.println("Digite o numero da lixeira para travar");
        word = reader.nextLine();
        adm.travar(word);
    }
    
    private void priorizar() throws IOException{
        System.out.println("Digite o numero da lixeira que deseja priorizar");
        word = reader.nextLine();
        adm.ordem(word);
    }
    
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
            while(adm.isRuning())
                try {
                    adm.read();
                    lixeiras = adm.getLixeiras();
                    printLixeiras();
                } catch (IOException ex) {
                    Logger.getLogger(Cadm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}
