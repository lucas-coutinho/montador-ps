/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturadados;

import java.util.Scanner;

/**
 *
 * @author william
 */
public class Binario {
    
    private String inicial;
    private Boolean label;
    private String comp;
    private String dest;
    private String jump;
    private Boolean a;
    private String binario;
    
    private Scanner ler = new Scanner(System.in);

    Binario() {
        this.comp = "";
        this.dest = "";
        this.jump = "";
        this.a = false;
        this.inicial = "";
        this.binario = "";
        this.label = false;
    }
    
    Binario(String binario) {
        this.inicial = "";
        this.comp = "";
        this.dest = "";
        this.jump = "";
        this.a = false;
        this.label = false;
        this.binario = this.normalizaBits(binario);        
        this.separaArquivo();  //vai dividir os bits 
    }
    
    private void separaArquivo(){   //pega o binario completo e separa em inicial (A OU C), comp, dest e jump
        
        for(int i = 0;  i <= this.binario.length(); i++){
            
            if(i>=0 && i <= 2){
                this.inicial = this.inicial.concat(this.binario.substring(i, i+1));
            }
            else if(i > 2 && i <= 9 ){
                this.comp = this.comp.concat(this.binario.substring(i, i+1));
            }
            else if(i > 9 && i <= 12 ){
                this.dest = this.dest.concat(this.binario.substring(i, i+1));
            }
            else if (i > 12 && i <= 15 ){
                this.jump = this.jump.concat(this.binario.substring(i, i+1));
            }
        }
        
        if (this.inicial.equals("000")){
            this.label = true;
        }
        if (this.comp.charAt(0) == '1'){
            this.a = true;
        }
    }
    
    private String normalizaBits(String binario){
        
        binario = binario.replaceAll(" ", "");
        
        return binario;
    }

    public String getComp() {
        return comp;
    }

    public String getDest() {
        return dest;
    }

    public String getJump() {
        return jump;
    }

    public String getBinario() {
        return binario;
    }
    
    public String getInicial() {
        return inicial;
    }
    
    public Boolean getLabel() {
        return label;
    }
    public Boolean getA() {
        return a;
    }
    
    
    
               
}
