/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hacks;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author Lucas Freitas
 */
public class Macro {
    
   private Map<String,Map<Integer,String>> mapaDeParametros = new HashMap<>();
   private Map<String, List<String>> tabelaDefMacro = new HashMap<>();
   private List<String> intermediario = new ArrayList<>();
   private String fileName;
   
   public Macro(String fileName)
   {
       this.fileName = fileName;
   }
   
   public boolean verificaArquivo() throws IOException
   {
       try{
            FileReader arq = new FileReader(fileName);
            BufferedReader file = new BufferedReader(arq);
        
       /*
        *fazer tratamentos do arquivo
        */
            String read;
            read = file.readLine();
            
            /* testa ateh o final do arquivo*/
            while(read != null)
            {
                if(read.contains("mcdef"))
                {
                    String[] aux = new String[2];
                    String simbolo;
                    String parametros;
                    Map<Integer,String> map = new HashMap<>();
                    
                    /*Separa a linha que contem mcdef em simbolo(usado pra chamar
                     * a macro) e parametros formais apara a tabela de parametros*/
                    
                    aux = read.split("mcdef");
                    simbolo = aux[0];
                    parametros = aux[1];
                    Integer j = new Integer(0);
                    for(int i = 0;;)
                    {
                       /*laco que poe todos os parametros formais ja na tabela*/ 
                       
                        if(parametros.indexOf(",", i) != -1)
                        {
                            map.put(j,parametros.substring(i, parametros.indexOf(",", i)).trim());
                            i = parametros.indexOf(",", i) + 1;
                        }
                        else { map.put(j,parametros.substring(i).trim()); break;}
                        
                        j += 1;      
                    }
                    simbolo = simbolo.trim();
                    
                    /*valor da chamada esta atrelado ao conjunto de parametros*/
                    mapaDeParametros.put(simbolo, map);
                    read = file.readLine();
                    List<String> defMacro = new ArrayList<>();
                    
                    while(read != "mcend")
                    {
                       defMacro.add(read);
                       read = file.readLine();
                    }
                   
                    
                    tabelaDefMacro.put(read, defMacro);
                    
                }
                else{read = file.readLine();}
            }
            arq.close();
           return !mapaDeParametros.isEmpty();
        
       }
       catch(IOException e)
       {
          
           throw e;
       }
   }
   
   public void expandeMacro() throws IOException
   {
       /*copia todo o codig fonte em um codigo fonte
       *intermediario com a expansao feita e entao manda
       *para o montador gerar o codigo objeto*/
       try{
            FileReader arq = new FileReader(fileName);
            BufferedReader file = new BufferedReader(arq);
        
       /*
        *fazer tratamentos do arquivo
        */
            String read;
            read = file.readLine();
            while(read != null)
            {
                /*criando codigo fonte intermediario*/
                intermediario.add(read);
                
                /*verifica se uma macro eh chamada na linha atual*/
                for(String s: mapaDeParametros.keySet())
                {
                    if(read.contains(s))
                    {
                        String[] aux = new String[2];
                        Map<String,String> param = new HashMap<>();
                        
                        aux = read.split(" ");
                        
                        int i = 0;
                        int j = 0;
                        
                        while(true)
                        {
                            String s2;
                            if(aux[1].indexOf(",") != -1)
                            {
                                s2 = aux[1].substring(i,',' );
                                param.put(mapaDeParametros.get(s).get(j),s2);
                                i = aux[1].indexOf(",") + 1;
                            }
                            else 
                            {
                                s2 = aux[1].substring(i);
                                param.put(mapaDeParametros.get(s).get(j),s2);
                                break;
                            }
                        }
                        /*acima foi feita a indentificacao dos parametros formais
                        *para os reais, agora iremos exapandir a chamada da macro*/
                        
                        /*/ideia :
                         *for each passando por todas os parametros formais, e substituido
                         *eles pelos reais*/
                        List<String> s4 = new ArrayList<>();
                        for(String s3: param.keySet())
                        {
                            
                            for(String s5:tabelaDefMacro.get(aux[0]))
                            {
                                String s6;
                                s6 = s5.replaceFirst(s3, param.get(s3));
                                s4.add(s6);
                               
                            }
                        }
                        intermediario.addAll(s4);
                    }
                    
                }
                
            }
            
       }
       catch(IOException e){throw e;}
       
   }
    
    
}
