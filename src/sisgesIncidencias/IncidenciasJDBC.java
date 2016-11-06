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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sfcar
 */
public class IncidenciasJDBC {
    
    //Atributo de tipo connection
    private Connection conexion;
    
    //2A. Insertar empleado
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
    
    //2C. Actualizar empleado
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
    
    //2D. Actualizar contraseña
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
    
    //2B. Validar usuario con usuario y contraseña
    public boolean validarEmpleado(Empleados e) throws SQLException{
        boolean existe;
        conectar();
        String query = "SELECT * FROM empleados WHERE username=? AND password=?;"; //Definimos la consulta
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);//Creamos el prepareStatement para ejecutarla
        consultaParametrizada.setString(1, e.getUsername());
        consultaParametrizada.setString(2, e.getPassword());
        ResultSet resultado = consultaParametrizada.executeQuery(); //La ejecutamos
        if(resultado.next()){
            existe = true;
            //insert en la tabla historial el evento
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            String insert = "INSERT INTO historial VALUES(null, 'I', '"+fechaHoraenTexto+"', '"+e.getUsername()+"')"; //Definimos la consulta
            Statement consulta = conexion.createStatement();//Creamos el Statement para ejecutarla
            consulta.executeUpdate(insert);//Ejecuta la consulta
            consulta.close(); //Cierra la consulta
            System.out.println("Se ha insertado en el historial esta validación de usuario");
        }else{
            existe = false;
        }
        resultado.close();
        consultaParametrizada.close();
        desconectar();
        return existe;   
    }
    
    //??. Consultar todos los empleados (No pertenece a la actividad)
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
    //2E. Eliminar empleado
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
    
    //3C. Insertar una incidencia a partir de un objeto de clase Incidencia
    public void insertarIncidencia (Incidencias i) throws SQLException{
        conectar();
        String insert = "INSERT INTO incidencias VALUES (null, ?, ?, ?, ?, ?);";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(insert);
        consultaParametrizada.setString(1, i.getFechaHora());
        consultaParametrizada.setString(2, i.getOrigen().getUsername());
        consultaParametrizada.setString(3, i.getDestino().getUsername());
        consultaParametrizada.setString(4, i.getTipo());
        consultaParametrizada.setString(5, i.getDetalle());
        consultaParametrizada.executeUpdate();//Ejecuta la consulta parametrizada
        //Insertar las incidencias urgentes en la tabla historial
        if (i.getTipo()=="Urgente"){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            String insert2 = "INSERT INTO historial VALUES(null, 'U', '"+fechaHoraenTexto+"', ?)"; //Definimos la consulta
            PreparedStatement insertParametrizado = conexion.prepareStatement(insert2);//Creamos el Statement para ejecutarla
            insertParametrizado.setString(1, i.getOrigen().getUsername());
            insertParametrizado.executeUpdate();//Ejecuta la consulta
            insertParametrizado.close(); //Cierra la consulta
            System.out.println("Se ha insertado en el historial esta una incidencia URGENTE");
  
        }
        consultaParametrizada.close(); //Cierra la consulta
        
        String query = "SELECT id_incidencia FROM incidencias ORDER BY id_incidencia DESC;"; /*Como idIncidencia es autoincrementable
        si queremos saber cual es la ID de la última incidencia que se introdujo debemos lanzar una consulta que nos de este dato
        ¡CUIDADO! En multiusuario esto es un ERROR*/
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        if(resultado.next()){ //Cogemos el resultado y lo metemos en una variable de tipo int y con esto setteamos la variable idIncidencia del objeto i
            int idIncidencia = resultado.getInt("id_incidencia");
            i.setIdIncidencia(idIncidencia); 
        }
        resultado.close();
        consulta.close();
        
        desconectar();        
    }
    
    //3A. Consultar una incidencia por su ID
    public Incidencias getIncidenciaByID (int idIncidencia) throws SQLException{
        Incidencias i = new Incidencias();
        conectar();
        /* ESTA CONSULTA FUNCIONA EN mySQL PERO NO AQUÍ
        String query = "SELECT fecha_hora, empleados.username AS origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE id_incidencia = "+idIncidencia+""; // Donde la id es igual a la id que nos proporcionan*/
        String query = "SELECT * FROM incidencias WHERE id_incidencia="+idIncidencia+"";
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        if(resultado.next()){
            i.setIdIncidencia(idIncidencia);//El ID lo cogemos del mismo ID.
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.getDestino().setUsername(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
        }
        resultado.close();
        consulta.close();
        desconectar();
        return i;
        
    }
    
    //3B. Consultar todas las incidencias
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
            i.getDestino().setUsername(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consulta.close();
        desconectar();
        return lasIncidencias;  
    }
    
    //3D. Obtener las incidencias para un empleado a partir de un objeto de clase Empleado.
    public List<Incidencias> consultaTodaslasIncidenciasparaunEmpleadoapartirdeEmpleado (Empleados e) throws SQLException{
        List<Incidencias> lasIncidencias = new ArrayList<>();
        conectar();
        /*ESTA CONSULTA FUNCIONA EN mySQL PERO NO AQUÍ
        String query = "SELECT id_incidencia, fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE origen=?;";*/
        String query = "SELECT * FROM incidencias WHERE destino=?";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);
        consultaParametrizada.setString(1, e.getUsername());
        ResultSet resultado = consultaParametrizada.executeQuery();
        while(resultado.next()){
            Incidencias i = new Incidencias();
            i.setIdIncidencia(resultado.getInt("id_incidencia"));//El ID lo cogemos del mismo ID.
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.getDestino().setUsername(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consultaParametrizada.close();
        //Insertar en el historial la consulta sobre las incidencias para un empleado
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
        Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
        String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
        String insert2 = "INSERT INTO historial VALUES(null, 'C', '"+fechaHoraenTexto+"', ?)"; //Definimos la consulta
        PreparedStatement insertParametrizado = conexion.prepareStatement(insert2);//Creamos el Statement para ejecutarla
        insertParametrizado.setString(1, e.getUsername());
        insertParametrizado.executeUpdate();//Ejecuta la consulta
        insertParametrizado.close(); //Cierra la consulta
        System.out.println("--Se ha insertado en el historial esta CONSULTA--");
        
        desconectar();
        return lasIncidencias;
        
    }  
    
    //3E. Consultar una incidencia por su Origen
    public List<Incidencias> getIncidenciaByOrigen (Empleados e) throws SQLException{
        List<Incidencias> lasIncidencias = new ArrayList<>();
        conectar();
        /*ESTA CONSULTA FUNCIONA EN mySQL PERO NO AQUÍ
        String query = "SELECT id_incidencia, fecha_hora, empleados.username as origen, destino, tipo, detalle " //Seleccionamos de la unión, las columnas que queremos
                + "FROM incidencias JOIN empleados " //Unimos las dos tablas...
                + "ON incidencias.origen = username" //...Donde la columna origen de incidencias es igual al username de Empleado
                + "WHERE origen='"+username+"'"; // Donde la id es igual a la id que nos proporcionan*/
        String query = "SELECT * FROM incidencias WHERE origen=?";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);
        consultaParametrizada.setString(1, e.getUsername());
        ResultSet resultado = consultaParametrizada.executeQuery();
        while(resultado.next()){
            Incidencias i = new Incidencias();
            i.setIdIncidencia(resultado.getInt("id_incidencia"));
            i.setFechaHora(resultado.getString("fecha_hora"));
            i.getOrigen().setUsername(resultado.getString("origen"));
            i.getDestino().setUsername(resultado.getString("destino"));
            i.setTipo(resultado.getString("tipo"));
            i.setDetalle(resultado.getString("detalle"));
            lasIncidencias.add(i);//Añadir la incidencia al array
        }
        resultado.close();
        consultaParametrizada.close();
        desconectar();
        return lasIncidencias;
        
    }
    
    //4C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero).
    public List<Historial> getRankingUrgenciasGroupByUsername () throws SQLException{
        List<Historial> RankingUrgencias = new ArrayList<>();
        conectar();
        String query = "SELECT username, COUNT(id_incidencia) AS 'Nº Incidencias Urgentes' FROM historial WHERE tipo_evento = 'U' group by username;";
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(query);
        while(resultado.next()){//Bucle que recorre las filas de la consulta
            Historial h = new Historial();
            h.getUsername().setUsername(resultado.getString("username"));
            h.setIdIncidencia(resultado.getInt("Nº Incidencias Urgentes"));
            RankingUrgencias.add(h);//Añadir la incidencia al array
        }
        resultado.close();
        consulta.close();
        desconectar();
        return RankingUrgencias;
    }
    
    //4B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.
    public Historial getFechaHoraUltimoLogin (Empleados e) throws SQLException{
        Historial h = new Historial();
        conectar();
        String query = "SELECT MAX(fecha_hora) FROM historial WHERE tipo_evento = 'I' AND username=?;";
        PreparedStatement consultaParametrizada = conexion.prepareStatement(query);
        consultaParametrizada.setString(1, e.getUsername());
        ResultSet resultado = consultaParametrizada.executeQuery();
        if(resultado.next()){ //Cogemos el resultado y lo metemos en una variable de tipo String y con esto setteamos la variable fechahora del objeto h
            String fechaHora = resultado.getString("MAX(fecha_hora)");
            h.setFechaHora(fechaHora);
        }
        resultado.close();
        consultaParametrizada.close();
        desconectar();      
        return h;
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
