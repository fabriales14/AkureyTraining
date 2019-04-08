/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.IValidable;

public class Alfabeto implements IValidable{
    private int id;
    private String nombre;
    private String simbolos;
    private String simbolos_ignorados;

    public Alfabeto(int id, String nombre, String simbolos, String simbolos_ignorados) {
        this.id = id;
        this.nombre = nombre;
        this.simbolos = simbolos;
        this.simbolos_ignorados = simbolos_ignorados;
    }

    @Override
    public boolean validar(Object obj) {
        Alfabeto alfabeto =  (Alfabeto) obj;
        for (int i = 0; i < alfabeto.getSimbolos().length(); i++){
            char caracter = alfabeto.getSimbolos().charAt(i);
            int count = 0;
            for (int j = 0; j < alfabeto.getSimbolos().length(); j++){
                if (alfabeto.getSimbolos().charAt(j) == caracter)
                    count++;
            }
            if (count >= 2)
                return false;
        }
        for (int i = 0; i < alfabeto.getSimbolos_ignorados().length(); i++){
            char c = alfabeto.getSimbolos_ignorados().charAt(i);
            for (int j = 0; j < alfabeto.getSimbolos().length(); j++){
                if (alfabeto.getSimbolos().charAt(j) == c)
                    return false;
            }
        } return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    public String getSimbolos_ignorados() {
        return simbolos_ignorados;
    }

    public void setSimbolos_ignorados(String simbolos_ignorados) {
        this.simbolos_ignorados = simbolos_ignorados;
    }
    
    public int cantidadSimbolos(){
        return simbolos.length();
    }
    
    public char getSimboloAt(int pos){
        return simbolos.charAt(pos);
    }
    
    public boolean esConsecutivo(char anterior, char siguiente){
        
        int posAnterior = simbolos.indexOf(String.valueOf(anterior));
        int posSiguiente = simbolos.indexOf(String.valueOf(siguiente));
        
        return posAnterior+1 == posSiguiente || posSiguiente+1 == posAnterior;       
    }
    
    
}
