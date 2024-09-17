package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class MovilTO implements Serializable {
     private BigDecimal idMovil;
     private String codigoMovil;
     private String latitud;
     private String longitud;
     private Date fecultLec;
     private String tipo;
     private String descripcionFecha;
     private String icono;

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

    public Date getFecultLec() {
        return fecultLec;
    }

    public void setFecultLec(Date fecultLec) {
        this.fecultLec = fecultLec;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        return "MovilTO{" + "idMovil=" + idMovil + ", codigoMovil=" + codigoMovil + ", latitud=" + latitud + ", longitud=" + longitud + ", fecultLec=" + fecultLec + ", tipo=" + tipo + ", descripcionFecha=" + descripcionFecha + ", icono=" + icono + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.codigoMovil != null ? this.codigoMovil.hashCode() : 0);
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
        final MovilTO other = (MovilTO) obj;
        if ((this.codigoMovil == null) ? (other.codigoMovil != null) : !this.codigoMovil.equals(other.codigoMovil)) {
            return false;
        }
        return true;
    }
    
    
    
}
