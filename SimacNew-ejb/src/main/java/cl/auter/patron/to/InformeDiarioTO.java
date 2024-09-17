package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InformeDiarioTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private BigDecimal idAtencion;
    private BigDecimal idCruce;
    private BigDecimal idComuna;
    private Date fechaLlegada;
    private String estadoInicial;
    private String estadoFinal;
    private String estadoTarea;
    private String observacion;
    private Date fechaSalida;
    private String nombreTipoOrigen;
    private String ubicacion;
    private String fechaSalidaFormateada;
    private String fechaLlegadaFormateada;
    private String llamada;
    private String tipo;
    
    public BigDecimal getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(BigDecimal idAtencion) {
        this.idAtencion = idAtencion;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreTipoOrigen() {
        return nombreTipoOrigen;
    }

    public void setNombreTipoOrigen(String nombreTipoOrigen) {
        this.nombreTipoOrigen = nombreTipoOrigen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaSalidaFormateada() {
        return fechaSalidaFormateada;
    }

    public void setFechaSalidaFormateada(String fechaSalidaFormateada) {
        this.fechaSalidaFormateada = fechaSalidaFormateada;
    }

    public String getFechaLlegadaFormateada() {
        return fechaLlegadaFormateada;
    }

    public void setFechaLlegadaFormateada(String fechaLlegadaFormateada) {
        this.fechaLlegadaFormateada = fechaLlegadaFormateada;
    }

    public String getLlamada() {
        return llamada;
    }

    public void setLlamada(String llamada) {
        this.llamada = llamada;
    }   

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "InformeDiarioTO{" + "idAtencion=" + idAtencion + ", idCruce=" + idCruce + ", idComuna=" + idComuna + ", fechaLlegada=" + fechaLlegada + ", estadoInicial=" + estadoInicial + ", estadoFinal=" + estadoFinal + ", estadoTarea=" + estadoTarea + ", observacion=" + observacion + ", fechaSalida=" + fechaSalida + ", nombreTipoOrigen=" + nombreTipoOrigen + ", ubicacion=" + ubicacion + ", fechaSalidaFormateada=" + fechaSalidaFormateada + ", fechaLlegadaFormateada=" + fechaLlegadaFormateada + ", llamada=" + llamada + '}';
    }
    
}
