package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class PuntoTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idCruce;
    private double latitud;
    private double longitud;
    private String informacion;
    private String icono;
    private String ubicacion;

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public double getLatitud() {
            return latitud;
    }
    public void setLatitud(double latitud) {
            this.latitud = latitud;
    }
    public double getLongitud() {
            return longitud;
    }
    public void setLongitud(double longitud) {
            this.longitud = longitud;
    }
    public String getInformacion() {
            return informacion;
    }
    public void setInformacion(String informacion) {
            this.informacion = informacion;
    }
    public String getIcono() {
        return icono;
    }
    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
	
}
