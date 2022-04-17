/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package uefs.pblsd1;
import java.io.IOException;
import static java.lang.System.out;
import uefs.pblsd1.Model.*;
import java.util.*;
import uefs.pblsd1.Controler.Cadm;
import uefs.pblsd1.Controler.Ccaminhao;
import uefs.pblsd1.Controler.Clixeira;
/**
 *
 * @author gusta
 */
public class PBLSD1 {   
    public static void main(String[] args) throws IOException {

        int port, capacidade;
        PBLSD1 main = new PBLSD1();
        Scanner reader = new Scanner(System.in);
        Clixeira lixeira;
        Ccaminhao caminhao;
        Cadm adm;
        Server servidor;
        String word, IP;
        
        
        
        // Seleciona se vai inicializar como um servidor ou algum dos clientes
        OUTER:
        while (true) {
            System.out.println("Deseja abrir um (S)ervidor, uma (L)ixeira, um (C)aminhão ou um (A)dministrador");
            word = reader.nextLine();
            switch (word) {
                case "S":
                    System.out.println("Digite a porta para iniciar o servidor");
                    port = reader.nextInt();
                    servidor = new Server();
                    servidor.start(port);
                    break;
                case "L":
                    System.out.println("Digite o IP da lixeira a capacidade e a porta do Servidor");
                    IP = reader.nextLine();
                    capacidade = reader.nextInt();
                    port = reader.nextInt();
                    lixeira = new Clixeira(capacidade, IP, port);
                    lixeira.start();
                    break;
                case "C":
                    System.out.println("Digite o IP e a porta do Servidor");
                    IP = reader.nextLine();
                    port = reader.nextInt();
                    caminhao = new Ccaminhao(IP, port);
                    caminhao.start();
                    break;
                case "A":
                    System.out.println("Digite o IP e a porta do Servidor");
                    IP = reader.nextLine();
                    port = reader.nextInt();
                    adm = new Cadm(IP, port);
                    adm.start();
                case ".":
                    break OUTER;
                default:
                   System.out.println("Digite um caractere valido");
                   reader.reset();
            }
        }
    }
}
