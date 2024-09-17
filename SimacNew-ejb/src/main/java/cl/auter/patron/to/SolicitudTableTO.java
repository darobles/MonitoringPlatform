package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SolicitudTableTO implements Serializable{
   
   
    private BigDecimal idSolicitud;
    private String descripcionEstadoActual;   
    private String descripcionComuna;
    private String nombreSolicitante;    
    private String emailSolicitante;
    private String asunto;    
    private boolean verEstado;
    private Date fechaIngreso; 
    private String fechaIngresoFormat;  
    private BigDecimal numTareaAsma;
    private BigDecimal numReclamo;

    public BigDecimal getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(BigDecimal idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDescripcionEstadoActual() {
        return descripcionEstadoActual;
    }

    public void setDescripcionEstadoActual(String descripcionEstadoActual) {
        this.descripcionEstadoActual = descripcionEstadoActual;
    }

    public String getDescripcionComuna() {
        return descripcionComuna;
    }

    public void setDescripcionComuna(String descripcionComuna) {
        this.descripcionComuna = descripcionComuna;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public boolean isVerEstado() {
        return verEstado;
    }

    public void setVerEstado(boolean verEstado) {
        this.verEstado = verEstado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaIngresoFormat() {
        return fechaIngresoFormat;
    }

    public void setFechaIngresoFormat(String fechaIngresoFormat) {
        this.fechaIngresoFormat = fechaIngresoFormat;
    }

    public BigDecimal getNumTareaAsma() {
        return numTareaAsma;
    }

    public void setNumTareaAsma(BigDecimal numTareaAsma) {
        this.numTareaAsma = numTareaAsma;
    }

    public BigDecimal getNumReclamo() {
        return numReclamo;
    }

    public void setNumReclamo(BigDecimal numReclamo) {
        this.numReclamo = numReclamo;
    }
    
    
    
    
    
}
