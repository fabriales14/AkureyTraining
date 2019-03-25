/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AlgCodificacionBinaria;
import Modelo.AlgCodigoTelefonico;
import Modelo.IPersistencia;
import Modelo.AlgPalabraClave;
import Modelo.StrategyAlgoritmo;
import Modelo.AlgTransposicion;
import Modelo.AlgVigenere;
import Modelo.DAOPdf;
import Modelo.DAOTxt;
import Modelo.DAOXml;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import libcomp.Alfabeto;
import libcomp.DTO_Comunicacion;

/**
 *
 * @author Meli
 */
public class Controlador implements IValidable {

    private Alfabeto alfabetoActual;
    private IPersistencia DTO;
    private StrategyAlgoritmo algoritmo;
    private DAOAlfabetos bdAlfabetos = new DAOAlfabetos();
    private DirectorHilera director;
    
    public ArrayList cargarAlfabetos() {
        this.alfabetoActual = (Alfabeto) bdAlfabetos.getAlfabetos().get(0);
        return bdAlfabetos.getAlfabetos();
    }

    public boolean agregarAlfabeto(Alfabeto a) {
        return bdAlfabetos.agregar(a);
    }

    public boolean modificarAlfabeto(int id, Alfabeto a) {
        return bdAlfabetos.modificar(id, a);
    }

    public boolean eliminarAlfabeto(int id) {
        return bdAlfabetos.eliminar(id);
    }

    public Alfabeto consultarAlfabeto(String nombre) {

        return bdAlfabetos.consultar(nombre);
    }
    
    public void cambiarDirector(DTO_Comunicacion dto){
        predefinirAlfabeto(dto);
        BuilderHilera builder = null;
        switch(dto.getSelectedMezcla()){
            case 1:                     
                builder = new noConNoDup(alfabetoActual);
                break;
            case 2:
                builder = new conNoDup(alfabetoActual);
                break;
            case 3:
                builder = new conDup(alfabetoActual);
            }    
        
        director = new DirectorHilera(builder);
    }
    
    public String construirHilera(DTO_Comunicacion dto){
        director.BuildHilera(dto.getExtension());
        return director.getHilera();
    }

    public ArrayList cargarSalidas() {
        ArrayList salidas = new ArrayList();
        salidas.add("TXT");
        salidas.add("PDF");
        salidas.add("XML");
        return salidas;
    }

    public ArrayList cargarAlgoritmos() {
        ArrayList<String> archivos = new ArrayList<>();
        File folder = new File("src/Modelo");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            String nombre = file.getName();
            if ("Alg".equals(nombre.substring(0, 3))) {
                nombre = nombre.substring(3);
                nombre = nombre.substring(0, nombre.length() - 5);
                archivos.add(nombre);
            }
        }
        return archivos;
    }

    public File[] cargarBitacoras() {
        File folder = new File("Bitacora");
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public DTO_Comunicacion procesarPeticion(DTO_Comunicacion datos) {
        predefinirAlfabeto(datos);
         if(datos.isCodificacion()==true){
            if (!validar(datos)){
                JOptionPane.showMessageDialog(null, "Error en datos en entrada", "Error", JOptionPane.ERROR_MESSAGE);
                datos.getSalida().add("");
                return datos;
           }
         }
        for (String algoritmo : datos.getTipos_algoritmos()) {
            this.algoritmo = getAlgoritmo(algoritmo);
            if (this.algoritmo instanceof AlgVigenere) {
                ((AlgVigenere) this.algoritmo).setNumero_Vigenere(datos.getAlg_vigenere());
            }
            if (this.algoritmo instanceof AlgPalabraClave) {
                ((AlgPalabraClave) this.algoritmo).setPalabra_Clave(datos.getPalabra_clave());
            }
            String salida = this.algoritmo.procesar(datos.getEntrada(), this.alfabetoActual, datos.isCodificacion());
            datos.getSalida().add(salida);
        }
        guardar(datos);
        return datos;
    }

    private void predefinirAlfabeto(DTO_Comunicacion datos) {
        this.alfabetoActual = consultarAlfabeto(datos.getAlfabetos().get(0).getNombre());
    }

    public static StrategyAlgoritmo getAlgoritmo(String nombreAlgoritmo) {
        StrategyAlgoritmo algoritmo = null;
        try {
            //recupera el paquete donde se encuentra la clase base
            String paquete = StrategyAlgoritmo.class.getPackage().getName();

            String laInstancia = paquete + ".Alg" + nombreAlgoritmo;

            algoritmo = (StrategyAlgoritmo) Class.forName(laInstancia).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            algoritmo = null;
        }
        return algoritmo;
    }

    @Override
    public boolean validar(Object obj) {
        DTO_Comunicacion datos = (DTO_Comunicacion) obj;
        if (datos.getEntrada() == null || datos.getEntrada() == " "){
            return false;
        }
        for (int i=0; i<datos.getEntrada().length(); i++){
            char letra = datos.getEntrada().charAt(i);
            if (this.alfabetoActual.getSimbolos().indexOf(letra) == -1){
                if(this.alfabetoActual.getSimbolos().indexOf(letra)==-1){
                return false;
                }
            }
        }
        if(datos.isGenerar()!=true){
            if (datos.getTipos_algoritmos().isEmpty() || datos.getTipos_salida().isEmpty()){
                return false;
            }
        }
        return true;
    }

    private void guardar(DTO_Comunicacion dto_algoritmos) {
        for (int salida : dto_algoritmos.getTipos_salida()) {
            switch (salida) {
                case 0:
                    DAOTxt txt = new DAOTxt();
                    txt.guardar(dto_algoritmos);
                    break;
                case 1:
                    DAOPdf pdf = new DAOPdf();
                    pdf.guardar(dto_algoritmos);
                    break;
                case 2:
                    DAOXml xml = new DAOXml();
                    xml.guardar(dto_algoritmos);
                    break;
                default:
                    break;
            }
        }
    }
}
