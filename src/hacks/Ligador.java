/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacks;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *
 * @author Lucas Freitas
 */
public class Ligador {
    /*Ideia estupida, mas parece promissora:
     *somente juntar os dois codes em um grande*/
    private List<String> fileName;
    private String fileFinal;
    /*Construtor da Classe Ligador 
     *@param fileName eh uma lista de nomes
     *de arquivos para serem ligados */
    
    public Ligador(List<String> fileName, String fileFinal)
    {
        this.fileName = fileName;
        this.fileFinal = fileFinal;
    }
    
    
    void ligar()
    {
        List<String> copiaCodes = new ArrayList<>();
        while(!fileName.isEmpty())
        {
            String file = fileName.get(0);
            try
            {
                FileReader arq = new FileReader(file);
                BufferedReader doc = new BufferedReader(arq);
                
                String read;
                read = doc.readLine();
                
                while(read != null)
                {
                    if(read.contains("INTUSE")|| read.contains("EXTDEF")){read = doc.readLine();}
                    else
                    {
                        copiaCodes.add(read);
                        read = doc.readLine();
                    }
                        
                }
                
                fileName.remove(0);
                arq.close();
            }
            catch(IOException e){}
            
            try
            {
                FileWriter arq  = new FileWriter(fileFinal);
                PrintWriter doc = new PrintWriter(arq);
                
                for(String s:copiaCodes){if(!s.isEmpty()){doc.println(s);}}
                
                arq.close();
            }
            catch(IOException e){}
        }
    }
    
    
    
}
