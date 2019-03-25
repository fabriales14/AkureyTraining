/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


// nombre de la clase no define cual es su proposito y definicion
/**
 *
 * @author Usuario
 */
public class Consulta3 {
    
    private int idProvincia;
    private String nombre;
    private int hombre;
    private int mujer;
    private int desconocido;

    public Consulta3(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setSexo(int idSexo, int cantidad) {
        if (idSexo == 1)
            this.hombre = cantidad;
        else if (idSexo == 2)
            this.mujer = cantidad;
        else
            this.desconocido = cantidad;
    }
    
    public int getSexo(int idSexo){
        if (idSexo == 1)
            return this.hombre;
        else if (idSexo == 2)
            return this.mujer;
        else
            return this.desconocido;
    }
    
    public String getSexoNombre(int idSexo){
        if (idSexo == 1)
            return "hombre";
        else if (idSexo == 2)
            return "mujer";
        else
            return "desconocido";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getHombre() {
        return hombre;
    }

    public int getMujer() {
        return mujer;
    }

    public int getDesconocido() {
        return desconocido;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public String toString() {
        return "Consulta3{" + "idProvincia=" + idProvincia + ", hombre=" + hombre + ", mujer=" + mujer + ", desconocido=" + desconocido + '}';
    }
  
}
