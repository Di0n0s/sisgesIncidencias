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
public class Incidencias {
    
    private int idIncidencia;
    private String fechaHora;
    private Empleados origen;
    private String destino;
    private String tipo;
    private String detalle;

    public Incidencias() {
        idIncidencia = 0;
        fechaHora = "";
        origen = new Empleados();
        destino = "";
        tipo = "";
        detalle = "";
    }

    public Incidencias(int idIncidencia, String fechaHora, Empleados origen, String destino, String tipo, String detalle) {
        this.idIncidencia = idIncidencia;
        this.fechaHora = fechaHora;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.detalle = detalle;
    }
    
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Empleados getOrigen() {
        return origen;
    }

    public void setOrigen(Empleados origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Incidencias: " + "idIncidencia=" + idIncidencia + ", fechaHora=" + fechaHora + ", origen=" + origen + ", destino=" + destino + ", tipo=" + tipo + ", detalle=" + detalle + '}';
    }

    
}
