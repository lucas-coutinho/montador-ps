/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturadados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author william
 */
public class Arquivo {
    
    String nomeArquivo;    //nome do aquivo
    FileReader arquivo;    //string para ler o arquivo

    public Arquivo(String nomeArquivo) throws FileNotFoundException {
        
        this.nomeArquivo = nomeArquivo;
        
        try{
            arquivo = new FileReader(this.nomeArquivo);  //monta o arquivo na string
        }
        catch(FileNotFoundException ex){
            System.out.println("Arquivo não encontrado: "+ex.getMessage());
        }
    }
    
    public BufferedReader lerArquivo(FileReader arquivo){
        
        BufferedReader lerArq = new BufferedReader(arquivo);
        return lerArq;
    }
    
    
    
    
}
