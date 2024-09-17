package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VistaInstalacionTO implements Serializable{
    private BigDecimal idCruce;
    private BigDecimal idComuna;        
    private String nombreComuna;
    private String ubicacion;
    private String codigoSistema;
    private BigDecimal idInstalacion;
    private String empalme;
    private String latitud;
    private String longitud;                         
    private String tipo;
    private String enlace;
    private BigDecimal numCabezales;        
    private BigDecimal numEspScoot;        
    private BigDecimal numEspLocal;
    private BigDecimal numBotoneras;
    private BigDecimal numHitElec;        
    private BigDecimal numHitSolar;
    private String ups;
    private BigDecimal numCtaRegres;
    private BigDecimal numSenSenTto;
    private BigDecimal numLetVms;
    private BigDecimal balizas;
    private String tipoCable;
    private String modeloOtu;
    private String observacion;
    private Date fechaActualizacion;
    private String datoFiltro;
    private String imagen;
    private String red;
    private String controlador;
    private String descripcionTipo;
    
    private String activo;    
    private String descripcionFecha;
    
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

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public BigDecimal getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(BigDecimal idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public String getEmpalme() {
        return empalme;
    }

    public void setEmpalme(String empalme) {
        this.empalme = empalme;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public BigDecimal getNumCabezales() {
        return numCabezales;
    }

    public void setNumCabezales(BigDecimal numCabezales) {
        this.numCabezales = numCabezales;
    }

    public BigDecimal getNumEspScoot() {
        return numEspScoot;
    }

    public void setNumEspScoot(BigDecimal numEspScoot) {
        this.numEspScoot = numEspScoot;
    }

    public BigDecimal getNumEspLocal() {
        return numEspLocal;
    }

    public void setNumEspLocal(BigDecimal numEspLocal) {
        this.numEspLocal = numEspLocal;
    }

    public BigDecimal getNumBotoneras() {
        return numBotoneras;
    }

    public void setNumBotoneras(BigDecimal numBotoneras) {
        this.numBotoneras = numBotoneras;
    }

    public BigDecimal getNumHitElec() {
        return numHitElec;
    }

    public void setNumHitElec(BigDecimal numHitElec) {
        this.numHitElec = numHitElec;
    }

    public BigDecimal getNumHitSolar() {
        return numHitSolar;
    }

    public void setNumHitSolar(BigDecimal numHitSolar) {
        this.numHitSolar = numHitSolar;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public BigDecimal getNumCtaRegres() {
        return numCtaRegres;
    }

    public void setNumCtaRegres(BigDecimal numCtaRegres) {
        this.numCtaRegres = numCtaRegres;
    }

    public BigDecimal getNumSenSenTto() {
        return numSenSenTto;
    }

    public void setNumSenSenTto(BigDecimal numSenSenTto) {
        this.numSenSenTto = numSenSenTto;
    }

    public BigDecimal getNumLetVms() {
        return numLetVms;
    }

    public void setNumLetVms(BigDecimal numLetVms) {
        this.numLetVms = numLetVms;
    }

    public BigDecimal getBalizas() {
        return balizas;
    }

    public void setBalizas(BigDecimal balizas) {
        this.balizas = balizas;
    }

    public String getTipoCable() {
        return tipoCable;
    }

    public void setTipoCable(String tipoCable) {
        this.tipoCable = tipoCable;
    }

    public String getModeloOtu() {
        return modeloOtu;
    }

    public void setModeloOtu(String modeloOtu) {
        this.modeloOtu = modeloOtu;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getDatoFiltro() {
        return datoFiltro;
    }

    public void setDatoFiltro(String datoFiltro) {
        this.datoFiltro = datoFiltro;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescripcionFecha() {
        return descripcionFecha;
    }

    public void setDescripcionFecha(String descripcionFecha) {
        this.descripcionFecha = descripcionFecha;
    }

    @Override
    public String toString() {
        return "VistaInstalacionTO{" + "idCruce=" + idCruce + ", idComuna=" + idComuna + ", nombreComuna=" + nombreComuna + ", ubicacion=" + ubicacion + ", codigoSistema=" + codigoSistema + ", idInstalacion=" + idInstalacion + ", empalme=" + empalme + ", latitud=" + latitud + ", longitud=" + longitud + ", tipo=" + tipo + ", enlace=" + enlace + ", numCabezales=" + numCabezales + ", numEspScoot=" + numEspScoot + ", numEspLocal=" + numEspLocal + ", numBotoneras=" + numBotoneras + ", numHitElec=" + numHitElec + ", numHitSolar=" + numHitSolar + ", ups=" + ups + ", numCtaRegres=" + numCtaRegres + ", numSenSenTto=" + numSenSenTto + ", numLetVms=" + numLetVms + ", balizas=" + balizas + ", tipoCable=" + tipoCable + ", modeloOtu=" + modeloOtu + ", observacion=" + observacion + ", fechaActualizacion=" + fechaActualizacion + ", datoFiltro=" + datoFiltro + ", imagen=" + imagen + ", red=" + red + ", controlador=" + controlador + ", descripcionTipo=" + descripcionTipo + ", activo=" + activo + ", descripcionFecha=" + descripcionFecha + '}';
    }
    
}
