package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SolicitudTO implements Serializable{
   
   
    private BigDecimal idSolicitud;
   
    private String nombreSolicitante;
    private String emailSolicitante;
    private String fonoSolicitante;
    private Clob observacion;
    private Clob motivoRechazo;
    
    private String urlAdjunto;
    private String estadoActual;     
    private Date fechaIngreso; 
    private String fechaIngresoFormat;  
    private String usrIngresado;
    private BigDecimal numTareaAsma = new BigDecimal("0");
    private String asunto;
    private BigDecimal idComuna;
    private String descripcionEstadoActual;   
    private String descripcionComuna;
    private String descripcionObservacion;
    
    private boolean verEstado;
    private boolean verLinkSolicitud;
    private boolean verReclamo;
    private boolean verTareaReclamo;
    private boolean verAtencionReclamo;
    private boolean verBotonAceptar = false;
    private boolean verBotonReasignar = false;
    private boolean verBotonRechazar = false;
    
    private boolean verDescargaAdjunto = false;
    private BigDecimal tipoSolicitante;
    private BigDecimal numReclamo;
    private ReclamoTO reclamo;
    private List<VistaTareaWebTO> listaTareaReclamo = new ArrayList();
    
    public String getFechaIngresoFormat() {
        return fechaIngresoFormat;
    }

    public void setFechaIngresoFormat(String fechaIngresoFormat) {
        this.fechaIngresoFormat = fechaIngresoFormat;
    }

       
    public boolean isVerBotonAceptar() {
        return verBotonAceptar;
    }

    public void setVerBotonAceptar(boolean verBotonAceptar) {
        this.verBotonAceptar = verBotonAceptar;
    }

    public boolean isVerBotonReasignar() {
        return verBotonReasignar;
    }

    public void setVerBotonReasignar(boolean verBotonReasignar) {
        this.verBotonReasignar = verBotonReasignar;
    }

    public boolean isVerBotonRechazar() {
        return verBotonRechazar;
    }

    public void setVerBotonRechazar(boolean verBotonRechazar) {
        this.verBotonRechazar = verBotonRechazar;
    }
    
    
    
    
    public BigDecimal getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(BigDecimal idSolicitud) {
        this.idSolicitud = idSolicitud;
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

    public String getFonoSolicitante() {
        return fonoSolicitante;
    }

    public void setFonoSolicitante(String fonoSolicitante) {
        this.fonoSolicitante = fonoSolicitante;
    }

    public Clob getObservacion() {
        return observacion;
    }

    public void setObservacion(Clob observacion) {
        this.observacion = observacion;
    }

    public String getUrlAdjunto() {
        return urlAdjunto;
    }

    public void setUrlAdjunto(String urlAdjunto) {
        this.urlAdjunto = urlAdjunto;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsrIngresado() {
        return usrIngresado;
    }

    public void setUsrIngresado(String usrIngresado) {
        this.usrIngresado = usrIngresado;
    }

    public BigDecimal getNumTareaAsma() {
        return numTareaAsma;
    }

    public void setNumTareaAsma(BigDecimal numTareaAsma) {
        this.numTareaAsma = numTareaAsma;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
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

    public Clob getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(Clob motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public boolean isVerEstado() {
        return verEstado;
    }

    public void setVerEstado(boolean verEstado) {
        this.verEstado = verEstado;
    }

    public boolean isVerLinkSolicitud() {
        return verLinkSolicitud;
    }

    public void setVerLinkSolicitud(boolean verLinkSolicitud) {
        this.verLinkSolicitud = verLinkSolicitud;
    }

    public String getDescripcionObservacion() {
        return descripcionObservacion;
    }

    public void setDescripcionObservacion(String descripcionObservacion) {
        this.descripcionObservacion = descripcionObservacion;
    }

    public boolean isVerDescargaAdjunto() {
        return verDescargaAdjunto;
    }

    public void setVerDescargaAdjunto(boolean verDescargaAdjunto) {
        this.verDescargaAdjunto = verDescargaAdjunto;
    }   

    public BigDecimal getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(BigDecimal tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public BigDecimal getNumReclamo() {
        return numReclamo;
    }

    public void setNumReclamo(BigDecimal numReclamo) {
        this.numReclamo = numReclamo;
    }

    public boolean isVerReclamo() {
        return verReclamo;
    }

    public void setVerReclamo(boolean verReclamo) {
        this.verReclamo = verReclamo;
    }

    public ReclamoTO getReclamo() {
        return reclamo;
    }

    public void setReclamo(ReclamoTO reclamo) {
        this.reclamo = reclamo;
    }

    public boolean isVerTareaReclamo() {
        return verTareaReclamo;
    }

    public void setVerTareaReclamo(boolean verTareaReclamo) {
        this.verTareaReclamo = verTareaReclamo;
    }

    public boolean isVerAtencionReclamo() {
        return verAtencionReclamo;
    }

    public void setVerAtencionReclamo(boolean verAtencionReclamo) {
        this.verAtencionReclamo = verAtencionReclamo;
    }

    public List<VistaTareaWebTO> getListaTareaReclamo() {
        return listaTareaReclamo;
    }

    public void setListaTareaReclamo(List<VistaTareaWebTO> listaTareaReclamo) {
        this.listaTareaReclamo = listaTareaReclamo;
    }
    
    
    
    @Override
    public String toString() {
        return "SolicitudTO{" + "idSolicitud=" + idSolicitud + ", nombreSolicitante=" + nombreSolicitante + ", emailSolicitante=" + emailSolicitante + ", fonoSolicitante=" + fonoSolicitante + ", observacion=" + observacion + ", motivoRechazo=" + motivoRechazo + ", urlAdjunto=" + urlAdjunto + ", estadoActual=" + estadoActual + ", fechaIngreso=" + fechaIngreso + ", fechaIngresoFormat=" + fechaIngresoFormat + ", usrIngresado=" + usrIngresado + ", numTareaAsma=" + numTareaAsma + ", asunto=" + asunto + ", idComuna=" + idComuna + ", descripcionEstadoActual=" + descripcionEstadoActual + ", descripcionComuna=" + descripcionComuna + ", descripcionObservacion=" + descripcionObservacion + ", verEstado=" + verEstado + ", verLinkSolicitud=" + verLinkSolicitud + ", verBotonAceptar=" + verBotonAceptar + ", verBotonReasignar=" + verBotonReasignar + ", verBotonRechazar=" + verBotonRechazar + ", verDescargaAdjunto=" + verDescargaAdjunto + ", tipoSolicitante=" + tipoSolicitante + ", numReclamo=" + numReclamo + '}';
    }
    
    
    
}
