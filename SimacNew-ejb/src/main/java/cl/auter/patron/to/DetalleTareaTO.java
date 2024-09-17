/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Marco
 */
public class DetalleTareaTO implements Serializable{
    public BigDecimal idTarea;
    public String tipoEquipo;
    public String funcionamientiIni;
    public BigDecimal idCruce;
    public String fechaCreacionStr;
    public String obsPreliminar;
    public String tareaTerminada;
    public String informadoPor;
    public String nombreFuente;
    public String fechaCierreStr;  

    public BigDecimal getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(BigDecimal idTarea) {
        this.idTarea = idTarea;
    }   

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getFuncionamientiIni() {
        return funcionamientiIni;
    }

    public void setFuncionamientiIni(String funcionamientiIni) {
        this.funcionamientiIni = funcionamientiIni;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public String getFechaCreacionStr() {
        return fechaCreacionStr;
    }

    public void setFechaCreacionStr(String fechaCreacionStr) {
        this.fechaCreacionStr = fechaCreacionStr;
    }
    

    public String getObsPreliminar() {
        return obsPreliminar;
    }

    public void setObsPreliminar(String obsPreliminar) {
        this.obsPreliminar = obsPreliminar;
    }

    public String getTareaTerminada() {
        return tareaTerminada;
    }

    public void setTareaTerminada(String tareaTerminada) {
        this.tareaTerminada = tareaTerminada;
    }

    public String getInformadoPor() {
        return informadoPor;
    }

    public void setInformadoPor(String informadoPor) {
        this.informadoPor = informadoPor;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getFechaCierreStr() {
        return fechaCierreStr;
    }

    public void setFechaCierreStr(String fechaCierreStr) {
        this.fechaCierreStr = fechaCierreStr;
    }

    @Override
    public String toString() {
        return "DetalleTareaTO{" + "idTarea=" + idTarea + ", tipoEquipo=" + tipoEquipo + ", funcionamientiIni=" + funcionamientiIni + ", idCruce=" + idCruce + ", fechaCreacionStr=" + fechaCreacionStr + ", obsPreliminar=" + obsPreliminar + ", tareaTerminada=" + tareaTerminada + ", informadoPor=" + informadoPor + ", nombreFuente=" + nombreFuente + ", fechaCierreStr=" + fechaCierreStr + '}';
    }

 
 
}
