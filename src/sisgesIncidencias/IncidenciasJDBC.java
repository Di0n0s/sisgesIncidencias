/*
 * Código de acceso a la BBDD
 */
package sisgesIncidencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfcar
 */
public class IncidenciasJDBC {
    
    //Atributo de tipo connection
    private Connection conexion;
    
    public void insertarEmpleado (Empleados e) throws SQLException{
        conectar();
        String insert = "insert into Empleados values(?, ?, ?, ?);";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(insert);
        consultaParametrizada.setString(1, e.getUsername());
        consultaParametrizada.setString(2, e.getPassword());
        consultaParametrizada.setString(3, e.getNombreCompleto());
        consultaParametrizada.setString(4, e.getTelefono());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        consultaParametrizada.close(); //Cierra la consulta
        desconectar();
                
    }
    
    public void actualizarEmpleado (Empleados e1, Empleados e2) throws SQLException{
        conectar();
        String update = "UPDATE Empleados SET password=?, nombre_completo=?, telefono=? WHERE username =? AND password=?;";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(update);
        consultaParametrizada.setString(1, e2.getPassword());
        consultaParametrizada.setString(2, e2.getNombreCompleto());
        consultaParametrizada.setString(3, e2.getTelefono());
        consultaParametrizada.setString(4, e1.getUsername());
        consultaParametrizada.setString(5, e1.getPassword());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        consultaParametrizada.close(); //Cierra la consulta
        desconectar();
                
    }
    
    public void actualizarPassword (Empleados e1, Empleados e2) throws SQLException{
        conectar();
        String update = "UPDATE Empleados SET password=? WHERE username=? AND password=?;";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(update);
        consultaParametrizada.setString(1, e2.getPassword());
        consultaParametrizada.setString(2, e1.getUsername());
        consultaParametrizada.setString(3, e1.getPassword());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        consultaParametrizada.close(); //Cierra la consulta
        desconectar();
                
    }
    
    public boolean validarEmpleado(Empleados e) throws SQLException{
        boolean existe;
        conectar();
        String query = "SELECT * FROM Empleados WHERE username=? AND password=?;"; //Definimos la consulta
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);//Creamos el prepareStatement para ejecutarla
        consultaParametrizada.setString(1, e.getUsername());
        consultaParametrizada.setString(2, e.getPassword());
        ResultSet resultado = consultaParametrizada.executeQuery(query); //La ejecutamos
        if(resultado.next()){
            existe = true;
            //insert en la tabla historial el evento
        }else{
            existe = false;
        }
        resultado.close();
        consultaParametrizada.close();
        desconectar();
        return existe;   
    }
    
    public List<Empleados> consultaTodosEmpleados() throws SQLException{
        List<Empleados> losEmpleados = new ArrayList<>();
        conectar();
        String query = "Select * from empleados;";
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        while(resultado.next()){//Bucle que recorre las filas de la consulta
            Empleados e = new Empleados();
            e.setUsername(resultado.getString("username"));
            e.setPassword(resultado.getString("password"));
            e.setNombreCompleto(resultado.getString("nombre_completo"));
            e.setTelefono(resultado.getString("telefono"));
            losEmpleados.add(e);//Añadir el empleado al array
        }
        resultado.close();
        consulta.close();
        desconectar();
        return losEmpleados;  
    } 
    
    public void eliminarEmpleado (Empleados e) throws SQLException{
        conectar();
        String delete = "DELETE FROM Empleados WHERE username=? AND password=?;";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(delete);
        consultaParametrizada.setString(1, e.getUsername());
        consultaParametrizada.setString(2, e.getPassword());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        consultaParametrizada.close(); //Cierra la consulta
        desconectar();
                
    }
    
    public void insertarIncidencia (Incidencias i) throws SQLException{
        conectar();
        String insert = "INSERT INTO incidencias VALUES (null, ?, ?, ?, ?, ?);";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(insert);
        consultaParametrizada.setString(1, i.getFechaHora());
        consultaParametrizada.setString(2, i.getOrigen().getUsername());
        consultaParametrizada.setString(3, i.getDestino());
        consultaParametrizada.setString(4, i.getTipo());
        consultaParametrizada.setString(5, i.getDetalle());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        consultaParametrizada.close(); //Cierra la consulta
        String query = "SELECT id_incidencia FROM incidencias ORDER BY id_incidencia DESC;"; /*Como idIncidencia es autoincrementable
        si queremos saber cual es la ID de la última incidencia que se introdujo debemos lanzar una consulta que nos de este dato
        ¡CUIDADO! En multiusuario esto es un ERROR*/
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        if(resultado.next()){ //Cogemos el resultado y lo metemos en una variable de tipo int y con esto setteamos la variable idIncidencia del objeto i)
            int idIncidencia = resultado.getInt("id_incidencia");
            i.setIdIncidencia(idIncidencia); 
        }
        resultado.close();
        consulta.close();
        desconectar();        
    }
    
    public Incidencias getIncidenciaByID (int idIncidencia) throws SQLException{
        Incidencias i = new Incidencias();
        conectar();
        String query = "SELECT fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE id_incidencia="+idIncidencia+";"; // Donde la id es igual a la id que nos proporcionan
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        if(resultado.next()){
            i.setIdIncidencia(idIncidencia);//El ID lo cogemos del mismo ID.
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.setDestino(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
        }
        resultado.close();
        consulta.close();
        desconectar();
        return i;
        
    }
    
    public List<Incidencias> consultaTodaslasIncidencias() throws SQLException{
        List<Incidencias> lasIncidencias = new ArrayList<>();
        conectar();
        String query = "SELECT id_incidencia, fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username;"; //...Donde la columna origen de incidencias es igual al username de Empleado
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        while(resultado.next()){//Bucle que recorre las filas de la consulta
            Incidencias i = new Incidencias();
            i.setIdIncidencia(resultado.getInt("id_incidencia"));
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.setDestino(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consulta.close();
        desconectar();
        return lasIncidencias;  
    } 
    
    public List<Incidencias> consultaTodaslasIncidenciasparaunEmpleadoapartirdeEmpleado (Empleados e) throws SQLException{
        List<Incidencias> lasIncidencias = new ArrayList<>();
        conectar();
        String query = "SELECT id_incidencia, fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE origen=?;";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);
        consultaParametrizada.setString(1, e.getUsername());
        ResultSet resultado = consultaParametrizada.executeQuery(query);
        while(resultado.next()){
            Incidencias i = new Incidencias();
            i.setIdIncidencia(resultado.getInt("id_incidencia"));//El ID lo cogemos del mismo ID.
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.setDestino(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consultaParametrizada.close();
        desconectar();
        return lasIncidencias;
        
    }  
    
    public List<Incidencias> getIncidenciaByOrigen (String username) throws SQLException{
        List<Incidencias> lasIncidencias = new ArrayList<>();
        conectar();
        String query = "SELECT id_incidencia, fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE origen="+username+";"; // Donde la id es igual a la id que nos proporcionan
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        while(resultado.next()){
            Incidencias i = new Incidencias();
            i.setIdIncidencia(resultado.getInt("id_incidencia"));
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.setDestino(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consulta.close();
        desconectar();
        return lasIncidencias;
        
    }
    
    private void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/sisgesIncidencias";
        String user = "sfuentes";
        String pass = "samson180387";
        conexion = DriverManager.getConnection(url, user, pass);//Lanzo el error y lo cazo(try-catch) a nivel de la vista(main)        
    }
    
    private void desconectar() throws SQLException{
        conexion.close();
    }
}
