/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class VistaCruceTO implements Serializable{
   
 
    private BigDecimal idDispositivo;
    
    private BigDecimal idCruce;
    private String ubicacion;
    private String controlador;
    private String activo;
    private Date fechaAct;
    private String descripcionFecha;
    private String estadoOperativo;
    private int estadoOperativoInt;
    private String descripcionEstadoOperativo;
    private String colorEstadoOperativo;
    private BigDecimal valModo220;
    private String descripcionValModo220;
    private BigDecimal valModoUps;
    private String descripcionValModoUps;
    private BigDecimal valModoUtc;
    private BigDecimal valModoUtc4;
    private int valModo220Int;
    private int valModoUpsInt;
    private int valModoLamInt;
    private int valModoUtcInt;
    private int valModoUtc4Int;
    private String descripcionValModoUtc;
    private BigDecimal valModoLam;
    private String descripcionValModoLam;
    private String red;
    private String longitud;
    private String latitud;
    private String codigoSistema;
    private BigDecimal idComuna; 
    private String descripcionComuna;
    private String imagenEstadoOperacional;
    private String observacion;
    private String numeroEquipo;
    private String direccionIP;
    private String telefono;
    private boolean ppr;
    private int tipoMonitoreo;
    private String tipo_monitoreo_desc;
    private boolean indLamFault;

    public String getDescripcionFecha() {
        return descripcionFecha;
    }

    public void setDescripcionFecha(String descripcionFecha) {
        this.descripcionFecha = descripcionFecha;
    }

    
    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public BigDecimal getValModo220() {
        return valModo220;
    }

    public void setValModo220(BigDecimal valModo220) {
        this.valModo220 = valModo220;
    }

    public BigDecimal getValModoUps() {
        return valModoUps;
    }

    public void setValModoUps(BigDecimal valModoUps) {
        this.valModoUps = valModoUps;
    }

    public BigDecimal getValModoUtc() {
        return valModoUtc;
    }

    public void setValModoUtc(BigDecimal valModoUtc) {
        this.valModoUtc = valModoUtc;
    }

    public BigDecimal getValModoUtc4() {
        return valModoUtc4;
    }

    public void setValModoUtc4(BigDecimal valModoUtc4) {
        this.valModoUtc4 = valModoUtc4;
    }

    public int getValModoUtc4Int() {
        return valModoUtc4.intValue();
    }

    public void setValModoUtc4Int(int valModoUtc4Int) {
        this.valModoUtc4Int = valModoUtc4Int;
    }
    
    
    
    public BigDecimal getValModoLam() {
        return valModoLam;
    }

    public void setValModoLam(BigDecimal valModoLam) {
        this.valModoLam = valModoLam;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getDescripcionComuna() {
        return descripcionComuna;
    }

    public void setDescripcionComuna(String descripcionComuna) {
        this.descripcionComuna = descripcionComuna;
    }

    public String getDescripcionEstadoOperativo() {
        return descripcionEstadoOperativo;
    }

    public void setDescripcionEstadoOperativo(String descripcionEstadoOperativo) {
        this.descripcionEstadoOperativo = descripcionEstadoOperativo;
    }

    public String getDescripcionValModo220() {
        return descripcionValModo220;
    }

    public void setDescripcionValModo220(String descripcionValModo220) {
        this.descripcionValModo220 = descripcionValModo220;
    }

    public String getDescripcionValModoUps() {
        return descripcionValModoUps;
    }

    public void setDescripcionValModoUps(String descripcionValModoUps) {
        this.descripcionValModoUps = descripcionValModoUps;
    }

    public String getDescripcionValModoUtc() {
        return descripcionValModoUtc;
    }

    public void setDescripcionValModoUtc(String descripcionValModoUtc) {
        this.descripcionValModoUtc = descripcionValModoUtc;
    }

    public String getDescripcionValModoLam() {
        return descripcionValModoLam;
    }

    public void setDescripcionValModoLam(String descripcionValModoLam) {
        this.descripcionValModoLam = descripcionValModoLam;
    }

    public String getImagenEstadoOperacional() {
        return imagenEstadoOperacional;
    }

    public void setImagenEstadoOperacional(String imagenEstadoOperacional) {
        this.imagenEstadoOperacional = imagenEstadoOperacional;
    }

    public String getColorEstadoOperativo() {
        return colorEstadoOperativo;
    }

    public void setColorEstadoOperativo(String colorEstadoOperativo) {
        this.colorEstadoOperativo = colorEstadoOperativo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroEquipo() {
        return numeroEquipo;
    }

    public void setNumeroEquipo(String numeroEquipo) {
        this.numeroEquipo = numeroEquipo;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getPpr() {
        return ppr;
    }

    public void setPpr(boolean ppr) {
        this.ppr = ppr;
    }

    public int getTipoMonitoreo() {
        return tipoMonitoreo;
    }

    public void setTipoMonitoreo(int tipoMonitoreo) {
        this.tipoMonitoreo = tipoMonitoreo;
    }

    public boolean isIndLamFault() {
        return indLamFault;
    }

    public void setIndLamFault(boolean indLamFault) {
        this.indLamFault = indLamFault;
    }

    public int getValModo220Int() {
        return valModo220.intValue();
    }

    public void setValModo220Int(int valModo220Int) {
        this.valModo220Int = valModo220Int;
    }

    public int getValModoUpsInt() {
        return valModoUps.intValue();
    }

    public void setValModoUpsInt(int valModoUpsInt) {
        this.valModoUpsInt = valModoUpsInt;
    }

    public int getValModoLamInt() {
        return valModoLam.intValue();
    }

    public void setValModoLamInt(int valModoLamInt) {
        this.valModoLamInt = valModoLamInt;
    }

    public int getValModoUtcInt() {
        return valModoUtc.intValue();
    }
    
    
    public void setValModoUtcInt(int valModoUtcInt) {
        this.valModoUtcInt = valModoUtcInt;
    }
    
    
    public int getEstadoOperativoInt() {        
        return Integer.parseInt(estadoOperativo);
    }

    public void setEstadoOperativoInt(int estadoOperativoInt) {
        this.estadoOperativoInt = estadoOperativoInt;
    }

    public String getTipo_monitoreo_desc() {
        return tipo_monitoreo_desc;
    }

    public void setTipo_monitoreo_desc(String tipo_monitoreo_desc) {
        this.tipo_monitoreo_desc = tipo_monitoreo_desc;
    }
    
    
    
    @Override
    public String toString() {
        return "VistaCruceTO{" + "idDispositivo=" + idDispositivo + ", idCruce=" + idCruce + ", ubicacion=" + ubicacion + ", controlador=" + controlador + ", activo=" + activo + ", fechaAct=" + fechaAct + ", descripcionFecha=" + descripcionFecha + ", estadoOperativo=" + estadoOperativo + ", descripcionEstadoOperativo=" + descripcionEstadoOperativo + ", colorEstadoOperativo=" + colorEstadoOperativo + ", valModo220=" + valModo220 + ", descripcionValModo220=" + descripcionValModo220 + ", valModoUps=" + valModoUps + ", descripcionValModoUps=" + descripcionValModoUps + ", valModoUtc=" + valModoUtc + ", descripcionValModoUtc=" + descripcionValModoUtc + ", valModoLam=" + valModoLam + ", descripcionValModoLam=" + descripcionValModoLam + ", red=" + red + ", longitud=" + longitud + ", latitud=" + latitud + ", codigoSistema=" + codigoSistema + ", idComuna=" + idComuna + ", descripcionComuna=" + descripcionComuna + ", imagenEstadoOperacional=" + imagenEstadoOperacional + ", observacion=" + observacion + ", numeroEquipo=" + numeroEquipo + ", direccionIP=" + direccionIP + ", telefono=" + telefono + ", ppr=" + ppr + ", tipoMonitoreo=" + tipoMonitoreo + '}';
    }
    
    
}
