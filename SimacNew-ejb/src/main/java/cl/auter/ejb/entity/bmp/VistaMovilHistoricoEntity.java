/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "MOVIL_HIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaMovilHistoricoEntity.findAll", query = "SELECT m FROM VistaMovilHistoricoEntity m"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByIdReg", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.idReg = :idReg"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByCodigomovil", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.codigomovil = :codigomovil"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByLatitud", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.latitud = :latitud"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByLongitud", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.longitud = :longitud"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByFecultlec", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.fecultlec = :fecultlec"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByFechorloc", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.fechorloc = :fechorloc"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.findByUltcruce", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.ultcruce = :ultcruce"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.listaMovilCruce", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.ultcruce = :idCruce and m.fecultlec between :fechaDesde and :fechaHasta order by m.fecultlec asc"),
    @NamedQuery(name = "VistaMovilHistoricoEntity.MovilesPorFecha", query = "SELECT m FROM VistaMovilHistoricoEntity m WHERE m.codigomovil = :codigomovil and m.fecultlec between :fechaDesde and :fechaHasta order by m.fecultlec asc")})
    public class VistaMovilHistoricoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REG")
    private BigDecimal idReg;
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
    @Column(name = "FECHORLOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechorloc;
    @Column(name = "ULTCRUCE")
    private BigInteger ultcruce;

    public VistaMovilHistoricoEntity() {
    }

    public VistaMovilHistoricoEntity(BigDecimal idReg) {
        this.idReg = idReg;
    }

    public BigDecimal getIdReg() {
        return idReg;
    }

    public void setIdReg(BigDecimal idReg) {
        this.idReg = idReg;
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

    public Date getFechorloc() {
        return fechorloc;
    }

    public void setFechorloc(Date fechorloc) {
        this.fechorloc = fechorloc;
    }

    public BigInteger getUltcruce() {
        return ultcruce;
    }

    public void setUltcruce(BigInteger ultcruce) {
        this.ultcruce = ultcruce;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReg != null ? idReg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VistaMovilHistoricoEntity)) {
            return false;
        }
        VistaMovilHistoricoEntity other = (VistaMovilHistoricoEntity) object;
        if ((this.idReg == null && other.idReg != null) || (this.idReg != null && !this.idReg.equals(other.idReg))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.VistaMovilHistoricoEntity[ idReg=" + idReg + " ]";
    }
    
}
