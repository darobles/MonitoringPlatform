package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ComunaTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idComuna;
    private String descripcionComuna;    
    private BigDecimal idRegion;
    private String nombre;
    private String urlLogo;
    private String latitud;
    private String longitud;
    private String telefono;
    private String email;
    private String ind_mon;
    private int zoom;
    private String aut_uoct;

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public BigDecimal getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(BigDecimal idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
   

    public String getDescripcionComuna() {
        return descripcionComuna;
    }

    public void setDescripcionComuna(String descripcionComuna) {
        this.descripcionComuna = descripcionComuna;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInd_mon() {
        return ind_mon;
    }

    public void setInd_mon(String ind_mon) {
        this.ind_mon = ind_mon;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getAut_uoct() {
        return aut_uoct;
    }

    public void setAut_uoct(String aut_uoct) {
        this.aut_uoct = aut_uoct;
    }
    
    
    
    
}
