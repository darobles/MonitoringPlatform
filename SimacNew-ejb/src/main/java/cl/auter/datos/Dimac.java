/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.datos;

import java.sql.Date;

/**
 *
 * @author drobles
 */
public class Dimac {
    int idDispositivo;
    String imei;
    String dirip;
    String dyndns;
    String ubicacion;
    String numserie;
    String comentario;
    String activo;
    Date fechaact;
    int estadooperativo;
    int valModo220;
    int valModoUps;
    int valModoUtc1;
    int valModoLam;
    String ind_ups;
    int valModoUtc2;
    int valModoUtc3;
    int valModoUtc4;
    String numtel;
    String ind_otu;

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
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

    public String getNumserie() {
        return numserie;
    }

    public void setNumserie(String numserie) {
        this.numserie = numserie;
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

    public Date getFechaact() {
        return fechaact;
    }

    public void setFechaact(Date fechaact) {
        this.fechaact = fechaact;
    }

    public int getEstadooperativo() {
        return estadooperativo;
    }

    public void setEstadooperativo(int estadooperativo) {
        this.estadooperativo = estadooperativo;
    }

    public int getValModo220() {
        return valModo220;
    }

    public void setValModo220(int valModo220) {
        this.valModo220 = valModo220;
    }

    public int getValModoUps() {
        return valModoUps;
    }

    public void setValModoUps(int valModoUps) {
        this.valModoUps = valModoUps;
    }

    public int getValModoUtc1() {
        return valModoUtc1;
    }

    public void setValModoUtc1(int valModoUtc1) {
        this.valModoUtc1 = valModoUtc1;
    }

    public int getValModoLam() {
        return valModoLam;
    }

    public void setValModoLam(int valModoLam) {
        this.valModoLam = valModoLam;
    }

    public String getInd_ups() {
        return ind_ups;
    }

    public void setInd_ups(String ind_ups) {
        this.ind_ups = ind_ups;
    }

    public int getValModoUtc2() {
        return valModoUtc2;
    }

    public void setValModoUtc2(int valModoUtc2) {
        this.valModoUtc2 = valModoUtc2;
    }

    public int getValModoUtc3() {
        return valModoUtc3;
    }

    public void setValModoUtc3(int valModoUtc3) {
        this.valModoUtc3 = valModoUtc3;
    }

    public int getValModoUtc4() {
        return valModoUtc4;
    }

    public void setValModoUtc4(int valModoUtc4) {
        this.valModoUtc4 = valModoUtc4;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getInd_otu() {
        return ind_otu;
    }

    public void setInd_otu(String ind_otu) {
        this.ind_otu = ind_otu;
    }

    @Override
    public String toString() {
        return "Dimac{" + "idDispositivo=" + idDispositivo + ", imei=" + imei + ", dirip=" + dirip + ", dyndns=" + dyndns + ", ubicacion=" + ubicacion + ", numserie=" + numserie + ", comentario=" + comentario + ", activo=" + activo + ", fechaact=" + fechaact + ", estadooperativo=" + estadooperativo + ", valModo220=" + valModo220 + ", valModoUps=" + valModoUps + ", valModoUtc1=" + valModoUtc1 + ", valModoLam=" + valModoLam + ", ind_ups=" + ind_ups + ", valModoUtc2=" + valModoUtc2 + ", valModoUtc3=" + valModoUtc3 + ", valModoUtc4=" + valModoUtc4 + ", numtel=" + numtel + ", ind_otu=" + ind_otu + '}';
    }
    
}
