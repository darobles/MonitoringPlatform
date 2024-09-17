package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "COMUNA")
@NamedQueries({
    @NamedQuery(name = "ComunaEntity.buscaPorComuna",
            query = "SELECT o FROM ComunaEntity o WHERE o.idComuna > 0 ORDER BY o.nombre ")
    ,
                @NamedQuery(name = "ComunaEntity.buscaPorId",
            query = "SELECT o FROM ComunaEntity o WHERE o.idComuna=:idComuna ")
    ,
                @NamedQuery(name = "ComunaEntity.buscaComunaCoordenada",
            query = "SELECT o FROM ComunaEntity o WHERE o.latitud != ' ' AND o.longitud != ' ' ORDER BY o.nombre")
    ,
                @NamedQuery(name = "ComunaEntity.buscaComunaMonitoreo",
            query = "SELECT o FROM ComunaEntity o WHERE o.latitud != ' ' AND o.longitud != ' ' AND o.ind_mon = 'SI' ORDER BY o.nombre")
    ,
                @NamedQuery(name = "ComunaEntity.buscaComunaMonitoreoUOCT",
            query = "SELECT o FROM ComunaEntity o WHERE o.latitud != ' ' AND o.longitud != ' ' AND o.ind_mon = 'SI' AND o.autUoct = 'SI' ORDER BY o.nombre")
    ,                
                @NamedQuery(name = "ComunaEntity.buscaComunaPorRegion",
            query = "SELECT o FROM ComunaEntity o WHERE o.idregion = :idRegion AND o.longitud != ' ' AND o.ind_mon = 'SI' ORDER BY o.nombre")
    ,
})
public class ComunaEntity implements Serializable {

    @Column(name = "IDREGION")
    private BigDecimal idregion;
    @Column(name = "ZOOM")
    private BigDecimal zoom;
    @Size(max = 2)
    @Column(name = "AUT_UOCT")
    private String autUoct;

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IDCOMUNA", nullable = false)
    private BigDecimal idComuna;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "URL_LOGO")
    private String urlLogo;
    @Column(name = "LATITUD")
    private String latitud;
    @Column(name = "LONGITUD")
    private String longitud;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "MOVILES")
    private String moviles;
    @Column(name = "IND_MON")
    private String ind_mon;

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
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

    public String getMoviles() {
        return moviles;
    }

    public void setMoviles(String moviles) {
        this.moviles = moviles;
    }

    public String getInd_mon() {
        return ind_mon;
    }

    public void setInd_mon(String ind_mon) {
        this.ind_mon = ind_mon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ComunaEntity() {
    }

    public BigDecimal getIdregion() {
        return idregion;
    }

    public void setIdregion(BigDecimal idregion) {
        this.idregion = idregion;
    }

    public BigDecimal getZoom() {
        return zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

    public String getAutUoct() {
        return autUoct;
    }

    public void setAutUoct(String autUoct) {
        this.autUoct = autUoct;
    }

    @Override
    public String toString() {
        return "ComunaEntity{" + "idregion=" + idregion + ", zoom=" + zoom + ", autUoct=" + autUoct + ", idComuna=" + idComuna + ", nombre=" + nombre + ", urlLogo=" + urlLogo + ", latitud=" + latitud + ", longitud=" + longitud + ", email=" + email + ", telefono=" + telefono + ", moviles=" + moviles + ", ind_mon=" + ind_mon + '}';
    }
    
    

}
