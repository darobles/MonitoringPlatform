/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "VISTA_MOVIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaMovilEntity.findAll", query = "SELECT v FROM VistaMovilEntity v")
    , @NamedQuery(name = "VistaMovilEntity.findByIdmovil", query = "SELECT v FROM VistaMovilEntity v WHERE v.idmovil = :idmovil")
    , @NamedQuery(name = "VistaMovilEntity.findByCodigomovil", query = "SELECT v FROM VistaMovilEntity v WHERE v.codigomovil = :codigomovil")
    , @NamedQuery(name = "VistaMovilEntity.findByLatitud", query = "SELECT v FROM VistaMovilEntity v WHERE v.latitud = :latitud")
    , @NamedQuery(name = "VistaMovilEntity.findByLongitud", query = "SELECT v FROM VistaMovilEntity v WHERE v.longitud = :longitud")
    , @NamedQuery(name = "VistaMovilEntity.findByFecultlec", query = "SELECT v FROM VistaMovilEntity v WHERE v.fecultlec = :fecultlec")
    , @NamedQuery(name = "VistaMovilEntity.findByTipo", query = "SELECT v FROM VistaMovilEntity v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VistaMovilEntity.findByUltcruce", query = "SELECT v FROM VistaMovilEntity v WHERE v.ultcruce = :ultcruce")})
public class VistaMovilEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMOVIL")
    private BigDecimal idmovil;
    @Size(max = 100)
    @Column(name = "CODIGOMOVIL")
    private String codigomovil;
    @Size(max = 100)
    @Column(name = "LATITUD")
    private String latitud;
    @Size(max = 100)
    @Column(name = "LONGITUD")
    private String longitud;
    @Column(name = "FECULTLEC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecultlec;
    @Size(max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "ULTCRUCE")
    private BigDecimal ultcruce;

    public VistaMovilEntity() {
    }

    public VistaMovilEntity(BigDecimal idmovil) {
        this.idmovil = idmovil;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovil != null ? idmovil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaMovilEntity)) {
            return false;
        }
        VistaMovilEntity other = (VistaMovilEntity) object;
        if ((this.idmovil == null && other.idmovil != null) || (this.idmovil != null && !this.idmovil.equals(other.idmovil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.VistaMovil[ idmovil=" + idmovil + " ]";
    }
    
}
