/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Daniel
 */
@Entity
@Table(name = "REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionEntity.findAll", query = "SELECT r FROM RegionEntity r")
    , @NamedQuery(name = "RegionEntity.findByIdRegion", query = "SELECT r FROM RegionEntity r WHERE r.idRegion = :idRegion")
    , @NamedQuery(name = "RegionEntity.findByNombre", query = "SELECT r FROM RegionEntity r WHERE r.nombre = :nombre")})
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGION")
    private BigDecimal idRegion;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

    public RegionEntity() {
    }

    public RegionEntity(BigDecimal idRegion) {
        this.idRegion = idRegion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegionEntity)) {
            return false;
        }
        RegionEntity other = (RegionEntity) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.RegionEntity[ idRegion=" + idRegion + " ]";
    }
    
}
