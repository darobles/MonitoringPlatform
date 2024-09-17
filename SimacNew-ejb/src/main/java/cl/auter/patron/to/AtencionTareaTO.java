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
public class AtencionTareaTO implements Serializable{
    public BigDecimal idAtencion;   
    public String funcAlLlegar;
    public String funcAlSalir;
    public String observacionCliente;
    public String fechaLlegada;
    public String fechaSalida;
    public String fechaCierre;
    public String estadoAtencion;
    public String tipoEquipo;
    public String ubicacion;

    public BigDecimal getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(BigDecimal idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getFuncAlLlegar() {
        return funcAlLlegar;
    }

    public void setFuncAlLlegar(String funcAlLlegar) {
        this.funcAlLlegar = funcAlLlegar;
    }

    public String getFuncAlSalir() {
        return funcAlSalir;
    }

    public void setFuncAlSalir(String funcAlSalir) {
        this.funcAlSalir = funcAlSalir;
    }

    public String getObservacionCliente() {
        return observacionCliente;
    }

    public void setObservacionCliente(String observacionCliente) {
        this.observacionCliente = observacionCliente;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }   

    public String getEstadoAtencion() {
        return estadoAtencion;
    }

    public void setEstadoAtencion(String estadoAtencion) {
        this.estadoAtencion = estadoAtencion;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
       
}
