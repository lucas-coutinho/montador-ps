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
    private Integer[] RAM = new Integer[100];
    
    public Interpretador(String codObjeto) throws FileNotFoundException {
        this.codObjeto = codObjeto;
        interpreta(codObjeto);
    }
   
   private void interpreta(String line) throws FileNotFoundException {
       
       try {
           FileReader file = new FileReader(getCodObjeto());
           BufferedReader arq = new BufferedReader(file);
           
           line = arq.readLine();
      
           while(line != null)
           {
                if(line.length() > 0 ) {
                    System.out.println(line);
                    
                    /* Aqui começa o interpretador*/
                    
                    if("111".equals(line.substring(1, 3))) { // Indica que vai ser lido um valor para memória
                        //RAM[this.Reg_A] = 0;
                        switch(line.substring(4,10)){
                            case "0111111":
                                RAM[this.Reg_A] = 1;
                                break;
                            
                            case "0101010":
                                RAM[this.Reg_A] = 0;
                                break;
                            
                            case "0111010":
                                RAM[this.Reg_A] = -1;
                                break;
                            
                            case "0001100":
                                RAM[this.Reg_A] = Reg_data; 
                        }
                    }
                    
               
                    
                }
                  
   
               line = arq.readLine();
            } 
       }
       catch(FileNotFoundException e) {
           throw e;
       }
       catch(IOException e) {
           System.err.printf("Erro na leitura do arquilo %s\n", e.getMessage());
       }
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
