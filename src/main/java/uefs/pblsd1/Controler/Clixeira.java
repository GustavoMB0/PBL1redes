/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Controler;

import java.io.IOException;
import java.util.Scanner;
import uefs.pblsd1.Model.Lixeira;

/**
 *
 * @author gusta
 */
public class Clixeira {
    private final Scanner reader;
    private String text;
    private final Lixeira lixo;
    
    public Clixeira(int capacidade, String IP, int port) throws IOException{
        this.lixo = new Lixeira(capacidade, IP, port);
        reader = new Scanner(System.in);
    }


    
    public void start() throws IOException{
        while(true){
            System.out.println("Digite ação a ser realizada: (E)ncher, (L)impar ou (.) para finalizar");
            text = reader.nextLine();
            if(text.equals("E")){
                encher();
            }else if(text.equals("L")){
                limpar();
            }else if(text.equals(".")){
                lixo.closeConnection();
                break;
            }else{
                System.out.println("Digite um comando valido");
            }
            lixo.travar();
        }
    }
    
    private void encher() throws IOException{
        float x;
        if(!lixo.travada()){
            System.out.println("Digite a quantidade de lixo inserida");
            x = reader.nextFloat();
            lixo.encher(x);
        }else{
            System.out.println("Lixeira travada");
        }
    }
    
    private void limpar() throws IOException{
        lixo.esvaziar();
    }
}
