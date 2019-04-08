
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
// se cambia nombre de clase
public class PoblacionProvincia {
    
    private int idProvincia;
    private String nombreProvincia; // nombre de atributo cambiado
    // cantidad de poblacion por sexo
    private int cantidadHombres;
    private int cantidadMujeres;
    private int cantidadDesconocido;

    public PoblacionProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setSexo(int idSexo, int cantidad) {
        if (idSexo == 1)
            this.cantidadHombres = cantidad;
        else if (idSexo == 2)
            this.cantidadMujeres = cantidad;
        else
            this.cantidadDesconocido = cantidad;
    }
   
    // se cambia nombre de funcion
    public int getCantidadPorSexo(int idSexo){
        if (idSexo == 1)
            return this.cantidadHombres;
        else if (idSexo == 2)
            return this.cantidadHombres; 
        else
            return this.cantidadDesconocido;
    }
    
    public String getSexoNombre(int idSexo){
        if (idSexo == 1)
            return "hombre";
        else if (idSexo == 2)
            return "mujer";
        else
            return "desconocido";
    }

    public String getNombreProvincia() {
        return this.nombreProvincia;
    }

    public void setNombreProvincia(String nombre) {
        this.nombreProvincia = nombre;
    }


    public int getCantidadHombres() {
        return this.cantidadHombres;
    }

    public int getCantidadMujeres() {
        return this.cantidadMujeres;
    }

    public int getCantidadDesconocido() {
        return this.cantidadDesconocido;
    }

    public int getIdProvincia() {
        return this.idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    @Override
    public String toString() {
        return "PoblacionProvincia{" + "idProvincia=" + this.idProvincia + 
        ", hombre=" + this.cantidadHombres + ", mujer=" + this.cantidadMujeres + 
        ", desconocido=" + this.cantidadDesconocido + '}';
    }
  
}
