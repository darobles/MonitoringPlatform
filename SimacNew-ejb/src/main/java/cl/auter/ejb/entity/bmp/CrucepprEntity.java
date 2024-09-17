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
 * @author drobles
 */
@Entity
@Table(name = "CRUCEPPR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrucepprEntity.findAll", query = "SELECT c FROM CrucepprEntity c"), 
    @NamedQuery(name = "CrucepprEntity.findById", query = "SELECT c FROM CrucepprEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CrucepprEntity.findByIdcruce", query = "SELECT c FROM CrucepprEntity c WHERE c.idcruce = :idcruce")})
public class CrucepprEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "IDCRUCE")
    private BigDecimal idcruce;
    @Size(max = 100)
    @Column(name = "FASES")
    private String fases;
    @Column(name = "DIRMANUAL")
    private BigDecimal dirmanual;
    @Size(max = 1000)
    @Column(name = "DIRFASE")
    private String dirfase;
    @Size(max = 1000)
    @Column(name = "DIRRESP")
    private String dirresp;

    public CrucepprEntity() {
    }

    public CrucepprEntity(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public String getFases() {
        return fases;
    }

    public void setFases(String fases) {
        this.fases = fases;
    }

    public BigDecimal getDirmanual() {
        return dirmanual;
    }

    public void setDirmanual(BigDecimal dirmanual) {
        this.dirmanual = dirmanual;
    }

    public String getDirfase() {
        return dirfase;
    }

    public void setDirfase(String dirfase) {
        this.dirfase = dirfase;
    }

    public String getDirresp() {
        return dirresp;
    }

    public void setDirresp(String dirresp) {
        this.dirresp = dirresp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrucepprEntity)) {
            return false;
        }
        CrucepprEntity other = (CrucepprEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.Cruceppr[ id=" + id + " ]";
    }
    
}
