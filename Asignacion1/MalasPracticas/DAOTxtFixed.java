/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.DTOAlgoritmos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import libcomp.DTO_Comunicacion;

public class DAOTxt implements IPersistencia {
    // se definen atributos privados de la clase
    private FileWriter fileWriter;
    private BufferedWriter bufferWritter;
    private PrintWriter printWriter;

    // funcion larga y con varias responsabilidades es cambiada y distribuida en mas funciones
    @Override
    public boolean saveTxt(DTO_Comunicacion dto_algoritmos) {
        // se mejoran los nombres de las variables
        File txtFile = new File("Bitacora/DAOTxt.txt");
        //escritura
        try {
            this.fileWriter = new FileWriter(txtFile, true);
            this.bufferWritter = new BufferedWriter(fileWriter);
            this.printWriter = new PrintWriter(bufferWritter);
            this.bufferWritter.newLine();
            // el apppend se realiza en otra funcion
            appendToTxtFile(dto_algoritmos);
            this.printWriter.close();
            this.bufferWritter.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public void appendToTxtFile(DTO_Comunicacion dto_algoritmos){
        Calendar Calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //define formato para fecha y hora
        // inserta en archivo txt
        this.printWriter.append("Fecha: " + sdf.format(cal.getTime()) + " ");
        this.printWriter.append("Entrada: " + dto_algoritmos.getEntrada() + " ");
        this.printWriter.append("Algoritmos y salidas: \n");
        for (int i=0; i<dto_algoritmos.getTipos_algoritmos().size(); i++){
            this.printWriter.append(dto_algoritmos.getTipos_algoritmos().get(i) + ": " + dto_algoritmos.getSalida().get(i));
        }
        if (dto_algoritmos.isCodificacion()) {
            this.printWriter.append("en codificacion ");
        } else {
            this.printWriter.append("en decodificacion ");
        }
    }

}
