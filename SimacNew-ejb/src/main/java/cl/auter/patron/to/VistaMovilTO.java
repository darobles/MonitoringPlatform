/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class VistaMovilTO {
    
    private BigDecimal idmovil;
    private String codigomovil;
    private String latitud;
    private String longitud;
    private Date fecultlec;
    private String tipo;
    private BigDecimal ultcruce;
    public String descripcionFecha;
    public String icono;
    
    public BigDecimal getIdmovil() {
        return idmovil;
    }

    public void setIdmovil(BigDecimal idmovil) {
        this.idmovil = idmovil;
    }

    public String getCodigomovil() {
        return codigomovil;
    }

    public void setCodigomovil(String codigomovil) {
        this.codigomovil = codigomovil;
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

    public Date getFecultlec() {
        return fecultlec;
    }

    public void setFecultlec(Date fecultlec) {
        this.fecultlec = fecultlec;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getUltcruce() {
        return ultcruce;
    }

    public void setUltcruce(BigDecimal ultcruce) {
        this.ultcruce = ultcruce;
    }
    
    public String getDescripcionFecha() {
        return descripcionFecha;
    }

    public void setDescripcionFecha(String descripcionFecha) {
        this.descripcionFecha = descripcionFecha;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    @Override
    public String toString() {
        return "VistaMovilTO{" + "idmovil=" + idmovil + ", codigomovil=" + codigomovil + ", latitud=" + latitud + ", longitud=" + longitud + ", fecultlec=" + fecultlec + ", tipo=" + tipo + ", ultcruce=" + ultcruce + '}';
    }
    
}
