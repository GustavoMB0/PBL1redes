/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package uefs.pblsd1;
import java.io.IOException;
import static java.lang.System.out;
import uefs.pblsd1.Model.*;
/**
 *
 * @author gusta
 */
public class PBLSD1 {
    private final Server server = new Server();
    
    public static void main(String[] args) throws IOException {
        PBLSD1 main = new PBLSD1();
        
        if(main.server.start(8080)){
            main.server.stop();
        }else{
            out.println("Não funcionou");
            main.server.stop();
        }
    }
}
