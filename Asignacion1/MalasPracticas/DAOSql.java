/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class DAOSql {

    private Connection con = null;

    public Connection getCon() {
        return con;
    }

    public void conectar() throws SQLException {
        try {
            //conexion a ms sql 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//esto siempre es igual
            String password = "12345678";
            String url = "jdbc:sqlserver://localhost;databaseName=BD_AccidentesCR";
            con = DriverManager.getConnection(url, "root", password);
            System.out.println("Conectado a base de datos");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            System.out.println("Error en conexion");
        }
    }

    public void desconectar() throws SQLException {
        if (this.con != null) {
            this.con.close();
        }
        System.out.println("Desconectado de la base");
    }

    public Datos cargarDatos() throws SQLException {
        conectar();
        Datos datos = new Datos();
        Statement select = con.createStatement();
        ResultSet result = select
                .executeQuery("SELECT * FROM Dia");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Dia = " + val);
            datos.dias.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT * FROM Sexo");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Sexo = " + val);
            datos.sexos.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT * FROM Rol");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Rol = " + val);
            datos.roles.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT * FROM Mes");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Mes = " + val);
            datos.meses.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT id_provincia, RTRIM(nombre) FROM Provincia");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Provincia = " + val);
            datos.provincias.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT * FROM Lesion");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            System.out.println("Lesion = " + val);
            datos.lesiones.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT id_canton, RTRIM(nombre) FROM Canton");
        while (result.next()) { 
            int key = result.getInt(1);
            String val = result.getString(2);

            datos.cantones.put(key, val);
        }
        
        select = con.createStatement();
        result = select
                .executeQuery("SELECT d.id_distrito, RTRIM(d.nombre), d.id_canton, c.id_provincia, RTRIM(d.latitud), RTRIM(d.longitud)"
                        + " FROM Distrito d INNER JOIN Canton c on c.id_canton = d.id_canton");
        while (result.next()) { 
            Distrito d =  new Distrito();
            int key = result.getInt(1);
            d.setNombre(result.getString(2));
            d.setCanton(datos.cantones.get(result.getInt(3)));
            d.setProvincia(datos.provincias.get(result.getInt(4)));
            d.setLatitud(result.getString(5));
            d.setLongitud(result.getString(6));

            datos.distritos.put(key, d);
        }
        desconectar();
        return datos;
    }
    
    public ArrayList consultaGrafic(String indicador) throws SQLException{
        conectar();
        Statement select = con.createStatement();
        Resultado resultado = new Resultado();
        int i = 0;
        ResultSet result = select
                .executeQuery("SELECT RTRIM(nombre), COUNT(*)" + 
                        " FROM Afectado a JOIN "+ indicador + " i on a.id_" +indicador + " = i.id_"+
                        indicador+ " GROUP BY nombre");
       
        ArrayList<Integer> ALCount =  new ArrayList();
        ArrayList<Object>  ALName = new ArrayList();
        ArrayList<ArrayList> Result = new ArrayList();
        while (result.next()) { 
            String nombre = result.getNString(1);
            int id = result.getInt(2);
            resultado.resultado.put(id, nombre);
            ALName.add(nombre);
            ALCount.add(id);
        }
        Result.add(ALCount);
        Result.add(ALName);
        desconectar();
        return Result;
    }
        
    public Resultado consulta3() throws SQLException {
        conectar(); // se conecta a la base
        Statement select = con.createStatement();
        Resultado resultado = new Resultado();
        int i = 0;
        ResultSet result = select
                .executeQuery("SELECT a.id_sexo, a.id_provincia, RTRIM(p.nombre), COUNT(id_sexo) FROM Afectado a \n" +
                              "INNER JOIN PROVINCIA p ON p.id_provincia = a.id_provincia\n" +
                              "GROUP BY a.id_provincia, a.id_sexo, p.nombre");
        while (result.next()) { 
            int sexo = result.getInt(1);
            int provincia = result.getInt(2);
            String nombre = result.getString(3);
            int count = result.getInt(4);
            Consulta3 consulta3;
            if (resultado.resultado.containsKey(provincia)){
                consulta3 = (Consulta3)resultado.resultado.get(provincia);
                consulta3.setSexo(sexo, count);
                resultado.resultado.replace(provincia, consulta3);
            } else {
                consulta3 = new Consulta3(provincia);
                consulta3.setSexo(sexo, count);
                consulta3.setNombre(nombre);
                resultado.resultado.put(provincia, consulta3);
            }
        }
        desconectar();
        return resultado;
    }

}
