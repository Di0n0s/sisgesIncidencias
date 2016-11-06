/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisgesIncidencias;

/**
 *
 * @author sfcar
 */
public class Historial {
    
    private int idIncidencia;
    private String tipoEvento;
    private String fechaHora;
    private Empleados username;

    public Historial() {
        idIncidencia = 0;
        tipoEvento = "";
        fechaHora = "";
        username = new Empleados();
    }

    public Historial(int idIncidencia, String tipoEvento, String fechaHora, Empleados username) {
        this.idIncidencia = idIncidencia;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.username = username;
    }
        
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleados getUsername() {
        return username;
    }

    public void setUsername(Empleados username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Nº Incidencias Urgentes= " + idIncidencia + ", username= " + username + "";
    }





    
}
