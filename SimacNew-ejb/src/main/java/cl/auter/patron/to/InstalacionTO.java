/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Marco
 */
public class InstalacionTO implements Serializable{
    
    private BigDecimal id;
    private BigDecimal idCruce;
    private String latitud;
    private String longitud;
    private String tipo;
    private String tipoDescripcion;
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
    private Date fechaActual;
    private String fechaActualDesc;
    private String empalme;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
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

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getEmpalme() {
        return empalme;
    }

    public void setEmpalme(String empalme) {
        this.empalme = empalme;
    }

    public String getFechaActualDesc() {
        return fechaActualDesc;
    }

    public void setFechaActualDesc(String fechaActualDesc) {
        this.fechaActualDesc = fechaActualDesc;
    }   

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    @Override
    public String toString() {
        return "InstalacionTO{" + "id=" + id + ", idCruce=" + idCruce + ", latitud=" + latitud + ", longitud=" + longitud + ", tipo=" + tipo + ", tipoDescripcion=" + tipoDescripcion + ", enlace=" + enlace + ", numCabezales=" + numCabezales + ", numEspScoot=" + numEspScoot + ", numEspLocal=" + numEspLocal + ", numBotoneras=" + numBotoneras + ", numHitElec=" + numHitElec + ", numHitSolar=" + numHitSolar + ", ups=" + ups + ", numCtaRegres=" + numCtaRegres + ", numSenSenTto=" + numSenSenTto + ", numLetVms=" + numLetVms + ", balizas=" + balizas + ", tipoCable=" + tipoCable + ", modeloOtu=" + modeloOtu + ", observacion=" + observacion + ", fechaActual=" + fechaActual + ", fechaActualDesc=" + fechaActualDesc + ", empalme=" + empalme + '}';
    }
    
    
    
}
