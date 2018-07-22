/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hacks;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas Freitas
 */
public class TabelaDeSimbolos {
    private Map<String,Integer> table;
    
    public TabelaDeSimbolos()
    {
        this.table = new HashMap<String,Integer>();
    }
    


public int getSimboloEnd(String simbolo)
{
  if(table.get(simbolo) != null){return table.get(simbolo);}
  else return -1;
  
}

public void inserirSimbolo(String simbolo, int valor)
{
        Integer aux;
        aux = valor;
        table.put(simbolo, aux);
}

}