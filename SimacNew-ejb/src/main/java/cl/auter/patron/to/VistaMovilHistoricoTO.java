/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class VistaMovilHistoricoTO implements Serializable{

    private BigDecimal id_Reg;
    private String codigoMovil;
    private String latitud;
    private String longitud;
    private Date fecultlec;
    private Date fechorloc;
    private BigInteger ultCruce;

    public BigDecimal getId_Reg() {
        return id_Reg;
    }

    public void setId_Reg(BigDecimal id_Reg) {
        this.id_Reg = id_Reg;
    }

    public String getCodigoMovil() {
        return codigoMovil;
    }

    public void setCodigoMovil(String codigoMovil) {
        this.codigoMovil = codigoMovil;
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

    public Date getFechorloc() {
        return fechorloc;
    }

    public void setFechorloc(Date fechorloc) {
        this.fechorloc = fechorloc;
    }

    public BigInteger getUltCruce() {
        return ultCruce;
    }

    public void setUltCruce(BigInteger ultCruce) {
        this.ultCruce = ultCruce;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id_Reg != null ? this.id_Reg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VistaMovilHistoricoTO other = (VistaMovilHistoricoTO) obj;
        if (this.id_Reg != other.id_Reg && (this.id_Reg == null || !this.id_Reg.equals(other.id_Reg))) {
            return false;
        }
        return true;
    }

}
