/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "VISTA_REGION_COMUNA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaRegionComunaEntity.findAll", query = "SELECT v FROM VistaRegionComunaEntity v order by nombre asc"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByIdcomuna", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.idcomuna = :idcomuna"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByNombre", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByIdregion", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.idregion = :idregion"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByNombreRegion", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.nombreRegion = :nombreRegion"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByUrlLogo", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.urlLogo = :urlLogo"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByLatitud", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.latitud = :latitud"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByLongitud", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.longitud = :longitud"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByEmail", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.email = :email"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByTelefono", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.telefono = :telefono"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByMoviles", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.moviles = :moviles"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByIndMon", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.indMon = :indMon"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByZoom", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.zoom = :zoom"),
    @NamedQuery(name = "VistaRegionComunaEntity.findByAutUoct", query = "SELECT v FROM VistaRegionComunaEntity v WHERE v.autUoct = :autUoct")})
public class VistaRegionComunaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOMUNA")
    @Id
    private BigInteger idcomuna;
    @Size(max = 500)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "IDREGION")
    private BigInteger idregion;
    @Size(max = 100)
    @Column(name = "NOMBRE_REGION")
    private String nombreRegion;
    @Size(max = 500)
    @Column(name = "URL_LOGO")
    private String urlLogo;
    @Size(max = 40)
    @Column(name = "LATITUD")
    private String latitud;
    @Size(max = 40)
    @Column(name = "LONGITUD")
    private String longitud;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 255)
    @Column(name = "MOVILES")
    private String moviles;
    @Size(max = 255)
    @Column(name = "IND_MON")
    private String indMon;
    @Column(name = "ZOOM")
    private BigInteger zoom;
    @Size(max = 2)
    @Column(name = "AUT_UOCT")
    private String autUoct;

    public VistaRegionComunaEntity() {
    }

    public BigInteger getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(BigInteger idcomuna) {
        this.idcomuna = idcomuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getIdregion() {
        return idregion;
    }

    public void setIdregion(BigInteger idregion) {
        this.idregion = idregion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
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

    public String getMoviles() {
        return moviles;
    }

    public void setMoviles(String moviles) {
        this.moviles = moviles;
    }

    public String getIndMon() {
        return indMon;
    }

    public void setIndMon(String indMon) {
        this.indMon = indMon;
    }

    public BigInteger getZoom() {
        return zoom;
    }

    public void setZoom(BigInteger zoom) {
        this.zoom = zoom;
    }

    public String getAutUoct() {
        return autUoct;
    }

    public void setAutUoct(String autUoct) {
        this.autUoct = autUoct;
    }
    
}
