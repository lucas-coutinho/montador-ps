/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacks;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Object;
import java.util.*;
/**
 *
 * @author Lucas Freitas
 */
public class Montador{
    private Map<String,Integer> memoria;
    private TabelaDeSimbolos tabela;
    private Map<String,String> assemblerComp;
    private Map<String,String> assemblerJump;
    private Map<String,String> assemblerDest;
    private String line;
    private String fileName;
    private List<String> memVar;
    
    /*SETAR espaco de memoria para variaveis de 0 a 15 */
    
    public Montador(String fileName) {
        this.memoria = new HashMap<>();
        this.tabela = new TabelaDeSimbolos();
        this.assemblerComp = new HashMap<>();
        this.assemblerDest = new HashMap<>();
        this.assemblerJump = new HashMap<>();
        this.fileName = fileName;
        memVar = new ArrayList<>();
        iniciaAssembler();
    }
    
   public String lerLinha() throws FileNotFoundException
   {
       try
       {
           FileReader file = new FileReader(fileName);
           BufferedReader arq = new BufferedReader(file);
      
           line = arq.readLine();
           
           return line;
           
           
       }
       catch(FileNotFoundException e)
       {
           throw e;
       }
       catch(IOException e)
       {
           System.err.printf("Erro na leitura do arquilo %s\n", e.getMessage());
       }
       
      return null;
           
   }
   public void primeiroPasso(String line, int pc)
   {
       if(line.charAt(0) == '(' && !isNumeric((line.charAt(1)+"")) && line.charAt(line.length()-1) == ')')
       {
           
           String simbolo;
           simbolo = line.substring(1, line.length() -1);
          // System.out.println(simbolo);
           tabela.inserirSimbolo(simbolo, pc);
       }
       else
       {
           String key;
           if(line.charAt(0) == '@')
           {
                key = line.substring(1);
                if(isNumeric(key) == false)
                {
                    //System.out.println(key);
                    if(tabela.getSimboloEnd(key) == -1) tabela.inserirSimbolo(key, -1);
                }// inserindo simbolo na tabela de simbolos
                else
                {  }
           }
       }
   }
   public String traduzLinha(String line)
   {
       String key;
       
       if(line.charAt(0) == '@') // Se eh um A-instrunction
       {
           if(isNumeric(line.substring(1)))
           {
            key = line.substring(1);
           
            Integer i;
              
            i = Integer.parseInt(key);
            key = Integer.toBinaryString(i);
            
            while(key.length() < 16) {key = "0" + key;}
           }
           else
           {
                if(tabela.getSimboloEnd(line.substring(1)) == -1)
                {
                    if(memVar.size() == 15) memVar.remove(0);
                    if(memVar.contains(line.substring(1)))
                    {
                        Integer i;
              
                        i = memVar.indexOf(line.substring(1));
                        
                        key = Integer.toBinaryString(i);
                        while(key.length() < 16) {key = "0" + key;}
                       
                    }
                    else
                    {
                        memVar.add(line.substring(1));
                        Integer i;
              
                        i = memVar.indexOf(line.substring(1));
                        
                        key = Integer.toBinaryString(i);
                        while(key.length() < 16) {key = "0" + key;}
  
                    }
                    
                    
                }
                else
                {

                    Integer i;

                    i = tabela.getSimboloEnd(line.substring(1));
                    key = Integer.toBinaryString(i);

                    while(key.length() < 16) {key = "0" + key;}
                }
           }
           
       }
       else 
       {
            String comp;
            String dest;
            String jump;
           
            if(!line.contains("="))
            {
               // definindo qual o destino
               //dest = qualDest(line.substring(0, line.indexOf('=')-1));
               // definindo qual a opercao
               dest = "000";
               comp = assemblerComp.get(line.substring(0,line.indexOf(';')));
               //definindo qual o jump
               jump = assemblerJump.get(line.substring(line.indexOf(';')+1));
            }
            else{
                if(!line.contains(";"))
                {
                    dest = assemblerDest.get(line.substring(0, line.indexOf('=')));
                    comp = assemblerComp.get(line.substring(line.indexOf('=')+1));
                    jump = "000";
                }
                else
                {
                    dest = assemblerDest.get(line.substring(0, line.indexOf('=')));
                    comp = assemblerComp.get(line.substring(line.indexOf('=')+1,line.indexOf(';') ));
                    jump = assemblerJump.get(line.substring(line.indexOf(';')+1));
                }
            }
           key = "111" + comp + dest + jump;
           
           if(key.length() < 16) return null;
       }
       return key;
   }
   private void iniciaAssembler()
   {
       //
       this.assemblerComp.put("0", "0101010");
       this.assemblerComp.put("1", "0111111");
       this.assemblerComp.put("-1","0111010");
       this.assemblerComp.put("D", "0001100");
       this.assemblerComp.put("A", "0110000");
       this.assemblerComp.put("!D","0001101");
       this.assemblerComp.put("!A","0110001");
       this.assemblerComp.put("-D","0001111");
       this.assemblerComp.put("-A","0110011");
       this.assemblerComp.put("D+1","0011111");
       this.assemblerComp.put("A+1","0110111");
       this.assemblerComp.put("A-1","0110010");
       this.assemblerComp.put("D-1","0001110");
       this.assemblerComp.put("D+A","0000010");
       this.assemblerComp.put("D-A","0010011");
       this.assemblerComp.put("A-D","0000111");
       this.assemblerComp.put("D&A","0000000");
       this.assemblerComp.put("D|A","0010101");
       this.assemblerComp.put("M","1110000");
       this.assemblerComp.put("!M","1110001");
       this.assemblerComp.put("M+1","1110111");
       this.assemblerComp.put("M-1","1110010");
       this.assemblerComp.put("D+M","1000010");
       this.assemblerComp.put("D-M","1010011");
       this.assemblerComp.put("M-D","1000111");
       this.assemblerComp.put("D&M","1000000");
       this.assemblerComp.put("D|M","1010101");
       //
       this.assemblerDest.put("M","001");
       this.assemblerDest.put("D","010");
       this.assemblerDest.put("MD","011");
       this.assemblerDest.put("A","100");
       this.assemblerDest.put("AM","101");
       this.assemblerDest.put("AD","110");
       this.assemblerDest.put("AMD","111");
       //
       this.assemblerJump.put("JGT","001");
       this.assemblerJump.put("JEQ","010");
       this.assemblerJump.put("JLT","100");
       this.assemblerJump.put("JNE","101");
       this.assemblerJump.put("JLE","110");
       this.assemblerJump.put("JMP","111");
       //
       
   }
   
   public static boolean isNumeric(String str)
{
    for (char c : str.toCharArray())
    {
        if (!Character.isDigit(c)) return false;
    }
    return true;
}
           
}
