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
    private Empleados destino;
    private String tipo;
    private String detalle;

    public Incidencias() {
        idIncidencia = 0;
        fechaHora = "";
        origen = new Empleados();
        destino = new Empleados();
        tipo = "";
        detalle = "";
    }

    public Incidencias(String fechaHora, Empleados origen, Empleados destino, String tipo, String detalle) {
        this.fechaHora = fechaHora;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.detalle = detalle;
    }

    public Incidencias(int idIncidencia, String fechaHora, Empleados origen, Empleados destino, String tipo, String detalle) {
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

    public Empleados getDestino() {
        return destino;
    }

    public void setDestino(Empleados destino) {
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
