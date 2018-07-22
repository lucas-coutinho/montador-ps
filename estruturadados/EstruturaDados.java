/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturadados;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author william
 */
public class EstruturaDados {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */     
        
    public static void main(String[] args) throws IOException {
        
        Scanner ler = new Scanner(System.in);
        
            System.out.println("nome do arquivo a ler lido: binario.txt");
            ArrayList<Binario> binario = new ArrayList();
            
            //Arquivo arquivo = new Arquivo(ler.nextLine());
            Arquivo arquivo = new Arquivo("binario.txt");
            
            BufferedReader lerBinario = arquivo.lerArquivo(arquivo.arquivo);
            
            String linha = lerBinario.readLine();
        
            while(linha != null){
                
                binario.add(new Binario(linha));
                linha = lerBinario.readLine();
            }
            
            
            for(int i=0; i < binario.size(); i++){
                System.out.println("LINHA: "+(i+1));
                System.out.println("Binario completo: " + binario.get(i).getBinario());
                if(binario.get(i).getLabel()  == true){
                    System.out.println("Instrução tipo @label");
                }
                else{
                    System.out.println("Instrução tipo Comando");
                }
                if(binario.get(i).getA() == false){
                    System.out.println("a = 0 false");
                }
                else{
                    System.out.println("a = 1 true");
                }
                System.out.println("Inicial: " + binario.get(i).getInicial());
                System.out.println("Comp: " + binario.get(i).getComp());
                System.out.println("Dest: " + binario.get(i).getDest());
                System.out.println("Jump: " + binario.get(i).getJump());
                System.out.println("\n");
            }
               
    }
    
}
