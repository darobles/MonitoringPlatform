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
 * @author drobles
 */
public class CruceMovilTO implements Serializable{

    private BigDecimal idreg;
    private BigDecimal idcruce;
    private String calle1;
    private String calle2;
    private BigDecimal idComuna;
    private String ubicacion;
    private BigDecimal idMovil;
    private String codigoMovil;
    private String fecultLec;
    private String latitud;
    private String longitud;

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(BigDecimal idMovil) {
        this.idMovil = idMovil;
    }

    public String getCodigoMovil() {
        return codigoMovil;
    }

    public void setCodigoMovil(String codigoMovil) {
        this.codigoMovil = codigoMovil;
    }

    public String getFecultLec() {
        return fecultLec;
    }

    public void setFecultLec(String fecultLec) {
        this.fecultLec = fecultLec;
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

    public BigDecimal getIdreg() {
        return idreg;
    }

    public void setIdreg(BigDecimal idreg) {
        this.idreg = idreg;
    }    
    
}
