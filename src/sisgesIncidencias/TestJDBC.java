/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisgesIncidencias;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sfcar
 */
public class TestJDBC {
    
    public static void main (String [] args){
        IncidenciasJDBC miGestor = new IncidenciasJDBC(); //?
//        //2C. Test de actualizar empleado
//        Empleados e1 = new Empleados("jperez", "trz254");
//        Empleados e2 = new Empleados("jperez", "plq657", "Jose Pérez Fernández", "+34985365235");
//        try {
//            System.out.println("Actualizando empleado...");
//            miGestor.actualizarEmpleado(e1, e2);
//            System.out.println("Emplado actualizado");
//            
//        } catch (SQLException ex) {
//
//            System.out.println("El empleado no ha sido actualizado por el siguiente motivo: "+ex.getMessage());
        
//        //2D.Test de actualizar contraseña
//        Empleados e1 = new Empleados("jperez", "plq657");
//        Empleados e2 = new Empleados("jperez", "trz254");
//        try {
//            System.out.println("Actualizando password...");
//            miGestor.actualizarPassword(e1,e2);
//            System.out.println("Password actualizado");
//            
//        } catch (SQLException ex) {
//
//            System.out.println("El empleado no ha sido actualizado por el siguiente motivo: "+ex.getMessage());
//            
//        }    
      
//        //??.Test de consultar todos los empleados
//        try{
//            System.out.println("Listado de emplados");
//            List<Empleados> losEmpleados = miGestor.consultaTodosEmpleados();
//            for (Empleados empleadoActual : losEmpleados){
//                System.out.println(empleadoActual);/*TestJDBC.Empleados@... Le estamos pidiendo que imprima el "object", usa el .toString e imprime esto --> 
//                *Vamos a sobreescribirlo para que nos muestre los datos del "object" que queremos*/
//              
//            }
//        } catch (SQLException ex) {
//
//            System.out.println(ex.getMessage());
            
         
            
       
        
//        //2B.Prueba de validar usuario con usuario y contraseña
//        Empleados e = new Empleados("jperez", "trz254");
//        try{
//            if(miGestor.validarEmpleado(e)){
//                System.out.println("Prueba de validar usuario...");
//                System.out.println("Usuario valido!");
//            }  
//            
//        }catch (SQLException ex) {
//            System.out.println("El usuario no existe: "+ex.getMessage());
// 
//        }
//        
//        //2A.Test de insertar empleado
//        Empleados e = new Empleados("amartinez", "lmg254", "Antonio Martínez Rodríguez", "+34956214789");
//        try{
//            System.out.println("Insertando empleado...");
//            miGestor.insertarEmpleado(e);
//            System.out.println("Emplado insertado");
//            
//            
//        } catch (SQLException ex) {
//            System.out.println("El empleado no ha sido insertado por el siguiente motivo: "+ex.getMessage());
//        
//        }
        
//        //2E.Test de eliminar empleado
//        Empleados e = new Empleados("amartinez","lmg254");
//        try{
//            System.out.println("Eliminando empleado...");
//            miGestor.eliminarEmpleado(e);
//            System.out.println("Emplado eliminado");
//            
//            
//        } catch (SQLException ex) {
//            System.out.println("El empleado no ha sido eliminado por el siguiente motivo: "+ex.getMessage());
//        
//        }
        
//        //3C. Insertar una incidencia a partir de un objeto de clase Incidencia
//        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
//        Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
//        String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
//        System.out.println(fechaHoraenTexto); //Lo imprimimos por pantalla 
//        Empleados e = new Empleados("amartinez", "lmg254", "Antonio Martínez Rodríguez", "+34956214789");
//        Incidencias i = new Incidencias (0,fechaHoraenTexto, e, "plopez", "Normal", "La impresora no tiene toner");
//        try{
//            System.out.println("Insertando incidencia...");
//            miGestor.insertarIncidencia(i);
//            System.out.println("Incidencia insertada");
//            System.out.println("Datos de la incidencia");
//            System.out.println(i);
//            
//            
//        } catch (SQLException ex) {
//            System.out.println("La incidencia no ha sido insertada por el siguiente motivo: "+ex.getMessage());
//        
//        }
        
//        //3A. Consultar una incidencia por su ID (FALLA)
//        System.out.println("Consultar una incidencia por su ID");
//        try{
//            Incidencias consultada = miGestor.getIncidenciaByID(5);
//            System.out.println("consultada");
//            
//        }catch( SQLException ex) {
//            System.out.println("La incidencia no puede ser consultada por el siguiente motivo: "+ex.getMessage());
//        
//        }
        
//        //3B. Consultar todas las incidencias
//        try{
//            System.out.println("Listado de incidencias");
//            List<Incidencias> lasIncidencias = miGestor.consultaTodaslasIncidencias();
//            for (Incidencias incidenciaActual : lasIncidencias){
//                System.out.println(incidenciaActual);/*TestJDBC.Incidencias@... Le estamos pidiendo que imprima el "object", usa el .toString e imprime esto --> 
//                *Vamos a sobreescribirlo para que nos muestre los datos del "object" que queremos*/
//              
//            }
//        } catch (SQLException ex) {
//
//            System.out.println(ex.getMessage());
//        }
        
//        //3D. Obtener las incidencias creadas a traves de un objeto de la clase Empleado.(FALLA)
//        System.out.println("Obtener las incidencias para el empleado");
//        Empleados e = new Empleados ("amartinez", "lmg254", "Antonio Martínez Rodríguez", "+34956214789");
//        try{
//            List<Incidencias> lasIncidencias = miGestor.consultaTodaslasIncidenciasparaunEmpleadoapartirdeEmpleado(e);
//            for (Incidencias incidenciaActual : lasIncidencias){
//                System.out.println(incidenciaActual);/*TestJDBC.Incidencias@... Le estamos pidiendo que imprima el "object", usa el .toString e imprime esto --> 
//                *Vamos a sobreescribirlo para que nos muestre los datos del "object" que queremos*/
//              
//            }
//            
//        }catch(SQLException ex) {
//            System.out.println("La incidencia no puede ser consultada por el siguiente motivo: "+ex.getMessage());
//        
//        }
        
//        //3E.	Obtener las incidencias creadas por un empleado concreto. (FALLA)
//        System.out.println("Consultar Incidencia de un empleado");
//        try{
//            List<Incidencias> lasIncidencias = miGestor.getIncidenciaByOrigen("amartinez");
//            for (Incidencias incidenciaActual : lasIncidencias){
//                System.out.println(incidenciaActual);/*TestJDBC.Incidencias@... Le estamos pidiendo que imprima el "object", usa el .toString e imprime esto --> 
//                *Vamos a sobreescribirlo para que nos muestre los datos del "object" que queremos*/
//            }
//        }catch (SQLException ex) {
//            System.out.println("La incidencia no puede ser consultada por el siguiente motivo: "+ex.getMessage());
//        
//        }        
        
    }
    
}

/*Como 
SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
String fechaHoraenTexto = sdf.format(ahora); //Creamos un string indicando al sdf que mofique el formato del dato
System.out.println(fechaHoraenTexto); //Lo imprimimos por pantalla 
*/
