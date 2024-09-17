/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class VistaCruceHistoricoTO implements Serializable{
   
    
    private BigDecimal idDispositivo;    
    private BigDecimal idCruce;
    private String ubicacion;
    private String controlador;
    private String activo;
    private Date fechaAct;
    private String estadoOperativo;
    private BigDecimal valModo220;
    private BigDecimal valModoUps;
    private BigDecimal valModoUtc;
    private BigDecimal valModoLam;
    private String red;
    private String longitud;
    private String latitud;
    private String junction;
    private BigDecimal idComuna;   
    private String descripcionEstadoOperativo;
    private String descripcionValModo220;
    private String descripcionValModoUps;
    private String descripcionValModoUtc;
    private String descripcionValModoLam;
    private String descripcionFecha;
    private String nombre_comuna;
    
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

    public String getJunction() {
        return junction;
    }

    public void setJunction(String junction) {
        this.junction = junction;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
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

    public String getDescripcionFecha() {
        return descripcionFecha;
    }

    public void setDescripcionFecha(String descripcionFecha) {
        this.descripcionFecha = descripcionFecha;
    }

    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }
    
}
