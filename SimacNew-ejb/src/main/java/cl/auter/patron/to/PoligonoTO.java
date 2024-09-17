package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Rodrigo Baeza O.
 */
public class PoligonoTO implements Serializable{
    private int idPoligono;
    private int idComuna;
    private BigDecimal longitud;
    private BigDecimal latitud;
    private int orden;
    private boolean dummy;

    public int getIdPoligono() {
        return idPoligono;
    }

    public void setIdPoligono(int idPoligono) {
        this.idPoligono = idPoligono;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }
    
    

    @Override
    public String toString() {
        return "PoligonoTO{" + "idPoligono=" + idPoligono + ", idComuna=" + idComuna + ", longitud=" + longitud + ", latitud=" + latitud + ", orden=" + orden + '}';
    }
    
    
    
}
