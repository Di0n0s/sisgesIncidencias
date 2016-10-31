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
    
    private Incidencias idIncidencia;
    private String tipoEvento;
    private Incidencias fechaHora;
    private Empleados username;

    public Historial() {
        idIncidencia = new Incidencias();
        tipoEvento = "";
        fechaHora = new Incidencias();
        username = new Empleados();
    }

    public Historial(Incidencias idIncidencia, String tipoEvento, Incidencias fechaHora, Empleados username) {
        this.idIncidencia = idIncidencia;
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.username = username;
    }
        
    public Incidencias getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Incidencias idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Incidencias getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Incidencias fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleados getUsername() {
        return username;
    }

    public void setUsername(Empleados username) {
        this.username = username;
    }

}
