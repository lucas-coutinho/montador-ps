/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacks;

/**
 *
 * @author Lucas Freitas
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String file = "C:\\Users\\Lucas Freitas\\Desktop\\teste.txt";
        String line ;
        String[] code;
        code = new String[100];
        Montador montador;
        int pc, i = 0;
        pc = 0;
        montador = new Montador(file);
        
        String h;
        h = "(LOOP)";
        h = h.substring(1, h.length() -1);
        h = "@LOOP";
        h = h.substring(1);
        System.out.println(h);
        System.out.println(h);
        try{
            FileReader filek = new FileReader(file);
            BufferedReader arq = new BufferedReader(filek);
            
            line = arq.readLine();
         
            while(line != null)
            {
                if(line.length() > 0 )
                {
                    System.out.println(line);
                    montador.primeiroPasso(line, pc);
                    if(!line.contains("(")) pc += 2;
                } 
                
               line = arq.readLine();
            }
           
           filek.close();
          
        }
        catch(FileNotFoundException e){System.out.println("Nao conseguiu abrir o arquivo");}
        catch(IOException e) {System.out.println("Nao conseguiu abrir o arquivo");}
        try{
            FileReader filek = new FileReader(file);
            BufferedReader arq = new BufferedReader(filek);
            
            line = arq.readLine();
         
            while(line != null)
            {
                if(line.length() > 0 && !line.contains("(") )
                {
                    System.out.println(line);
                    code[i++] = montador.traduzLinha(line);
                    
                } 
                
               line = arq.readLine();
            }
           
           filek.close();
          
        }
        catch(FileNotFoundException e){System.out.println("Nao conseguiu abrir o arquivo");}
        catch(IOException e) {System.out.println("Nao conseguiu abrir o arquivo");}
        
        FileWriter write;
        try {
            write = new FileWriter("C:\\Users\\Lucas Freitas\\Desktop\\testeResponse.txt");
            PrintWriter writeDoc = new PrintWriter(write);
           
           for(int j = 0;j < i;j++){writeDoc.println(code[j]);}
           write.close();
        }
        
        catch (IOException ex) {
            Logger.getLogger(Hacks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
}
