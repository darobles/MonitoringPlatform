
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="VISTA_CRUCE_DISPOSITIVO_HIS") 
@NamedQueries({ 
                @NamedQuery(name = "VistaCruceHistoricoEntity.comportamiento",
                            query = "SELECT o FROM VistaCruceHistoricoEntity o WHERE o.idcomuna = :idComuna and o.idcruce = :idCruce and o.fechaAct between :fechaDesde and :fechaHasta"),
              })
public class VistaCruceHistoricoEntity implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDHIST")
    @Id
    private BigDecimal idhist;

    @Column(name = "IDDISPOSITIVO")
    private BigDecimal iddispositivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCRUCE")
    private BigDecimal idcruce;
    @Column(name = "VALMODO220")
    private BigDecimal valmodo220;
    @Column(name = "VALMODOUPS")
    private BigDecimal valmodoups;
    @Column(name = "VALMODOUTC")
    private BigDecimal valmodoutc;
    @Column(name = "VALMODOLAM")
    private BigDecimal valmodolam;
    @Column(name = "IDCOMUNA")
    private BigDecimal idcomuna;
    private static final long serialVersionUID = 1L;
    @Column(name = "UBICACION" )
    private String ubicacion;
    @Column(name = "CONTROLADOR" )
    private String controlador;
    @Column(name = "ACTIVO" )
    private String activo;
    @Column(name = "FECHAACT" ,columnDefinition = "DATE" )
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAct;
    @Column(name = "ESTADOOPERATIVO" )
    private String estadoOperativo;
    @Column(name = "RED" )
    private String red;
    @Column(name = "LONGITUD" )
    private String longitud;
    @Column(name = "LATITUD" )
    private String latitud;
    @Column(name = "JUNCTION" )
    private String junction;
    @Column(name = "NOMBRE_COMUNA" , nullable = false)
    private String nombre_comuna;


    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }


    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getJunction() {
        return junction;
    }

    public void setJunction(String junction) {
        this.junction = junction;
    }


    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }

    public VistaCruceHistoricoEntity() {
    }

    public BigDecimal getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(BigDecimal iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public BigDecimal getValmodo220() {
        return valmodo220;
    }

    public void setValmodo220(BigDecimal valmodo220) {
        this.valmodo220 = valmodo220;
    }

    public BigDecimal getValmodoups() {
        return valmodoups;
    }

    public void setValmodoups(BigDecimal valmodoups) {
        this.valmodoups = valmodoups;
    }

    public BigDecimal getValmodoutc() {
        return valmodoutc;
    }

    public void setValmodoutc(BigDecimal valmodoutc) {
        this.valmodoutc = valmodoutc;
    }

    public BigDecimal getValmodolam() {
        return valmodolam;
    }

    public void setValmodolam(BigDecimal valmodolam) {
        this.valmodolam = valmodolam;
    }

    public BigDecimal getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(BigDecimal idcomuna) {
        this.idcomuna = idcomuna;
    }

    public BigDecimal getIdhist() {
        return idhist;
    }

    public void setIdhist(BigDecimal idhist) {
        this.idhist = idhist;
    }

   
    
}
