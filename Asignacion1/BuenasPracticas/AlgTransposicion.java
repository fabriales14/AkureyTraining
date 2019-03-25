/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Meli
 */
public class AlgTransposicion extends StrategyAlgoritmo{

    public String codificar(String mensaje, libcomp.Alfabeto alfabeto) {
        String result = "";
        String[] palabras = mensaje.split(" ");
        
        for(String m : palabras){
            String palabraVuelta = new StringBuilder(m).reverse().toString();
            result = result + palabraVuelta + " ";
        }
        
        return result;
    }
    
    public String tomaPalabra(String frase){
        return null;
    }
    
    public boolean es1palabra(String frase){
        return true;
    }

    public String decodificar(String mensaje, libcomp.Alfabeto alfabeto) {
        String result = "";
        String[] palabras = mensaje.split(" ");
        
        for(String m : palabras){
            String palabraVuelta = new StringBuilder(m).reverse().toString();
            result = result + palabraVuelta + " ";
        }
        return result;
    }

    @Override
    public String procesar(String mensaje, libcomp.Alfabeto alfabeto, boolean codificar) {
        if (codificar){
            return codificar(mensaje,alfabeto);
        }return decodificar(mensaje, alfabeto);
    }
    
    
}
