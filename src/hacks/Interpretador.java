/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author gabramos1
 */
public class Interpretador {
    private String codObjeto;
    private String line;
    private int Reg_pc;
    private int Reg_A;
    private int Reg_data;
    private int[] RAM;
    
    public Interpretador(String codObjeto, int pc) throws FileNotFoundException {
        this.codObjeto = codObjeto;
        this.Reg_pc = pc;
        interpreta(codObjeto, Reg_pc);
    }
    
    public String lerLinha() throws FileNotFoundException {
       try {
           FileReader file = new FileReader(getCodObjeto());
           BufferedReader arq = new BufferedReader(file);
      
           setLine(arq.readLine());
           return getLine(); 
       }
       catch(FileNotFoundException e) {
           throw e;
       }
       catch(IOException e) {
           System.err.printf("Erro na leitura do arquilo %s\n", e.getMessage());
       }
      return null;           
   }
   
   private void interpreta(String line, int pc) throws FileNotFoundException {
       
       String novaLinha = lerLinha();
       String OpCode;
       System.out.println(novaLinha);
       
       OpCode = novaLinha.substring(0,4);
       System.out.println(OpCode);
       /*Começa a baguncinha*/
       
       
       
       
       
       
       
       
   }

    /**
     * @return the codObjeto
     */
    public String getCodObjeto() {
        return codObjeto;
    }

    /**
     * @param codObjeto the codObjeto to set
     */
    public void setCodObjeto(String codObjeto) {
        this.codObjeto = codObjeto;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }
}
