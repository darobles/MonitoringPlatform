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
public class EstadisticaTO implements Serializable{
    
    private BigDecimal idComuna;
    private String tipoFalla;
    private int cantidad;
    private BigDecimal tiempoRespuesta;
    private String nombreEquipo;

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }   

    public String getTipoFalla() {
        return tipoFalla;
    }

    public void setTipoFalla(String tipoFalla) {
        this.tipoFalla = tipoFalla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(BigDecimal tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }  

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    
    
}
