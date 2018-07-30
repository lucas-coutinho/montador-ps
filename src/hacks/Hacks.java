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
import java.util.List;
import java.util.ArrayList;
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
    
    public static void main(String[] args) throws FileNotFoundException {
        
        String file = "C:\\Users\\gabramos1\\Desktop\\teste.txt";
        
        String fileInter = "C:\\Users\\gabramos1\\Desktop\\testeMacro.txt";
        String fileFinal = "C:\\Users\\gabramos1\\Desktop\\testeLigador.txt";
        String line ;
        List<String> intermediario;
        List<String> ligacoes;
        Ligador ligador;
        String[] code;
        code = new String[100];
        Montador montador;
        Interpretador interpretador;
        Macro macro;
        int pc, i = 0;
        pc = 0;
        
        //macro  = new Macro(file);
        ligacoes = new ArrayList<>();
        ligacoes.add(file);
        ligacoes.add("C:\\Users\\gabramos1\\Desktop\\teste2.txt");
        ligador = new Ligador(ligacoes, fileFinal);
        ligador.ligar();
        
        macro = new Macro(fileFinal);
        
        /* codigo teste 
        String h;
        h = "(LOOP)";
        h = h.substring(1, h.length() -1);
        h = "@LOOP";
        h = h.substring(1);
        System.out.println(h);
        System.out.println(h);
        ------------------*/
        
        
        try{
            
            /*se tiver macro ele modifica o arquivo original senao
             * monta o arquivo normalmente*/
            
            if(macro.verificaArquivo())
            {
                
                macro.expandeMacro();
               // System.out.println("oi");
                intermediario = macro.getIntermediario();
                for(String s: intermediario) 
                {
                    //for(int k = 0; k < s.length(); k++)System.out.print(s.charAt(k));
                    //if(s.isEmpty()) intermediario.remove(intermediario.indexOf(s));
                    
                }
                 //for(String s: intermediario) System.out.println(s);
                
                    try{
                        FileWriter arq = new FileWriter(fileInter);
                        PrintWriter doc = new PrintWriter(arq);
                        int k = 0;
                        for(String s: intermediario){ if(!s.isEmpty())doc.println(s); k++;}
                        
                        arq.close();
                    }
                    catch(IOException e){System.out.println(e.getMessage());}
               
                
            }
            else
            {
                fileInter = file;
            }
            
        }
        
        catch(IOException e){}
        montador = new Montador(fileInter);
        
        try{
            FileReader filek = new FileReader(fileInter);
            BufferedReader arq = new BufferedReader(filek);
            
            line = arq.readLine();
            
            while(line != null)
            {
                if(line.length() > 0 )
                {
                    //System.out.println(line);
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
            
            FileReader filek = new FileReader(fileInter);
            BufferedReader arq = new BufferedReader(filek);
            
            line = arq.readLine();
         
            while(line != null)
            {
                if(line.length() > 0 && !line.contains("(") )
                {
                    //System.out.println(line);
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
            write = new FileWriter("C:\\Users\\gabramos1\\Desktop\\testeResponse.txt");
            PrintWriter writeDoc = new PrintWriter(write);
           
           for(int j = 0;j < i;j++){writeDoc.println(code[j]);}
           write.close();
        }
        
        catch (IOException ex) {
            Logger.getLogger(Hacks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        interpretador = new Interpretador("C:\\Users\\gabramos1\\Desktop\\testeResponse.txt");
    }
 
}
