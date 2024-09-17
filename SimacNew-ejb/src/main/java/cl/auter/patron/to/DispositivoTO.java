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
public class DispositivoTO implements Serializable{
    
    private BigDecimal idDispositivo;
    private String imei;      
    private String dirip;
    private String dyndns;
    private String ubicacion;
    private String numSerie;
    private String comentario;
    private String activo;
    private Date fechaAct;
    private String estadoOperativo;
    private BigDecimal valModo220;
    private BigDecimal valModoUps;        
    private BigDecimal valModoUtc1;
    private BigDecimal valModoLam;        
    private String indUps;
    private BigDecimal valModoUtc2;
    private BigDecimal valModoUtc3;
    private BigDecimal valModoUtc4;
    private String numTel;
    private String indOtu;
    private BigDecimal indResetCtr;
    private boolean resetCtr;
    private BigDecimal tipoMonitoreo;
    private BigDecimal indLamFault;
    private boolean indLam;
    
    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDirip() {
        return dirip;
    }

    public void setDirip(String dirip) {
        this.dirip = dirip;
    }

    public String getDyndns() {
        return dyndns;
    }

    public void setDyndns(String dyndns) {
        this.dyndns = dyndns;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public BigDecimal getValModoUtc1() {
        return valModoUtc1;
    }

    public void setValModoUtc1(BigDecimal valModoUtc1) {
        this.valModoUtc1 = valModoUtc1;
    }

    public BigDecimal getValModoLam() {
        return valModoLam;
    }

    public void setValModoLam(BigDecimal valModoLam) {
        this.valModoLam = valModoLam;
    }

    public String getIndUps() {
        return indUps;
    }

    public void setIndUps(String indUps) {
        this.indUps = indUps;
    }

    public BigDecimal getValModoUtc2() {
        return valModoUtc2;
    }

    public void setValModoUtc2(BigDecimal valModoUtc2) {
        this.valModoUtc2 = valModoUtc2;
    }

    public BigDecimal getValModoUtc3() {
        return valModoUtc3;
    }

    public void setValModoUtc3(BigDecimal valModoUtc3) {
        this.valModoUtc3 = valModoUtc3;
    }

    public BigDecimal getValModoUtc4() {
        return valModoUtc4;
    }

    public void setValModoUtc4(BigDecimal valModoUtc4) {
        this.valModoUtc4 = valModoUtc4;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getIndOtu() {
        return indOtu;
    }

    public void setIndOtu(String indOtu) {
        this.indOtu = indOtu;
    }

    public BigDecimal getIndResetCtr() {
        return indResetCtr;
    }

    public void setIndResetCtr(BigDecimal indResetCtr) {
        this.indResetCtr = indResetCtr;
    }

    public boolean isResetCtr() {
        return resetCtr;
    }

    public void setResetCtr(boolean resetCtr) {
        this.resetCtr = resetCtr;
    }    

    public BigDecimal getTipoMonitoreo() {
        return tipoMonitoreo;
    }

    public void setTipoMonitoreo(BigDecimal tipoMonitoreo) {
        this.tipoMonitoreo = tipoMonitoreo;
    }

    public BigDecimal getIndLamFault() {
        return indLamFault;
    }

    public void setIndLamFault(BigDecimal indLamFault) {
        this.indLamFault = indLamFault;
    }

    public boolean isIndLam() {
        return indLam;
    }

    public void setIndLam(boolean indLam) {
        this.indLam = indLam;
    }
    
    

    @Override
    public String toString() {
        return "DispositivoTO{" + "idDispositivo=" + idDispositivo + ", imei=" + imei + ", dirip=" + dirip + ", dyndns=" + dyndns + ", ubicacion=" + ubicacion + ", numSerie=" + numSerie + ", comentario=" + comentario + ", activo=" + activo + ", fechaAct=" + fechaAct + ", estadoOperativo=" + estadoOperativo + ", valModo220=" + valModo220 + ", valModoUps=" + valModoUps + ", valModoUtc1=" + valModoUtc1 + ", valModoLam=" + valModoLam + ", indUps=" + indUps + ", valModoUtc2=" + valModoUtc2 + ", valModoUtc3=" + valModoUtc3 + ", valModoUtc4=" + valModoUtc4 + ", numTel=" + numTel + ", indOtu=" + indOtu + ", indResetCtr=" + indResetCtr + ", resetCtr=" + resetCtr + ", tipoMonitoreo=" + tipoMonitoreo + ", indLamFault=" + indLamFault + '}';
    }

    
}
