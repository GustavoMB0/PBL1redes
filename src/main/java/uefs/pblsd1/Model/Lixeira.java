/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uefs.pblsd1.Model;

import java.io.IOException;

/**
 *  Classe que servira como lixeira na solução do problema PBL, essa classe como todos clientes
 *  irão herdar da classe Client onde está contida os metodos de comunicação e de conexão com o servidor
 *  além do metodo de finalizar a conexão 
 * @author gusta
 */
public class Lixeira extends Client{
    private float capacidade;
    private boolean loked;
    
    public Lixeira(float capacidade, String ip, int port) throws IOException{
       super.name = "L";
       this.capacidade = capacidade;      
       super.connect(ip, port);
    }
    
    /**
     * Metodo que será utilizado para esvaziar a lixeira, envia uma mensagem com a seguinte mensagem
     * ID COMAND PARAM END.o ID é um identificador para os clientes do servidor cada cliente possui um id unico
     * composto por uma letra (indicador do tipo do cliente) e um valor númerico para identificar qual cliente
     * de determinado tipo é.COMAND é o que está acontecendo para esse metodo o caractere E representa 
     * a encher a lixeira, toda função realizada no servidor terá um comando diferente.PARAM são os
     * parametros da função no servidor ele é onde são passados valores para o servidor trabalhar,
     * nem todos as funções terão parametros.Por fim o END signficia fim do comando para esse
     * portocolo foi escolhido o caractere ; para indicar fim do comando
     * @param quantidade Quantidade de lixo adicionado a lixeira, ainda não foi decidido se será um valor fixo ou inserido pelo "usuario"
     * @return retorna true se a mensagem do servidor foi OK indicando que o comando foi recebido com sucesso pelo servidor
     * @throws java.io.IOException
     */
    
    public boolean encher(float quantidade) throws IOException{
       String msg, confirm;
       this.capacidade += quantidade;
       msg = "E" + capacidade + ";";
       
       confirm = super.sendMessage(msg);
       
       return confirm.equals("OK");
    }
    
    /**
     * Metodo para esvaziar a lixeira segue o mesmo protocolo de envio de mensagem que o metodo de encher, A mensagem
     * desse metodo segue o padrão ID COMAND END.Esse metodo não precisa de um parametro e o comando utilizado foi "C"
     * (Clear).
     * @return retorna true se a mensagem do servidor foi OK indicando que o comando foi recebido com sucesso pelo servidor
     * @throws java.io.IOException
     */
    public boolean esvaziar() throws IOException{
        String msg, confirm;
        
        msg = "C;";
        confirm = super.sendMessage(msg);
        
        return confirm.equals("OK");
    }
   
   
    /*
    *Metodo para travar a lixeira, não decidi o envio de mensagens do servidor ainda
    public boolean travar(){
        return true;
    }
    */
}
