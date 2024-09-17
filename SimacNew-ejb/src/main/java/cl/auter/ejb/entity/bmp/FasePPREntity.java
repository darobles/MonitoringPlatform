/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "FASEPPR")
@SequenceGenerator(name="SEQ_FASE_GEN",sequenceName="SEQ_FASE", initialValue=1, allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "FasePPREntity.findAll", query = "SELECT f FROM FasePPREntity f")
    , @NamedQuery(name = "FasePPREntity.findById", query = "SELECT f FROM FasePPREntity f WHERE f.id = :id")
    , @NamedQuery(name = "FasePPREntity.findByIdcruce", query = "SELECT f FROM FasePPREntity f WHERE f.idcruce = :idcruce order by f.fase asc")
    , @NamedQuery(name = "FasePPREntity.findByFase", query = "SELECT f FROM FasePPREntity f WHERE f.fase = :fase")
    , @NamedQuery(name = "FasePPREntity.findByDirfase", query = "SELECT f FROM FasePPREntity f WHERE f.dirfase = :dirfase")})
public class FasePPREntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_FASE_GEN")
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCRUCE")
    private BigDecimal idcruce;
    @Column(name = "FASE")
    private BigDecimal fase;
    @Column(name = "DIRFASE")
    private BigDecimal dirfase;

    public FasePPREntity() {
    }

    public FasePPREntity(BigDecimal id) {
        this.id = id;
    }

    public FasePPREntity(BigDecimal id, BigDecimal idcruce) {
        this.id = id;
        this.idcruce = idcruce;
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

    public BigDecimal getFase() {
        return fase;
    }

    public void setFase(BigDecimal fase) {
        this.fase = fase;
    }

    public BigDecimal getDirfase() {
        return dirfase;
    }

    public void setDirfase(BigDecimal dirfase) {
        this.dirfase = dirfase;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FasePPREntity other = (FasePPREntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "FasePPREntity{" + "id=" + id + ", idcruce=" + idcruce + ", fase=" + fase + ", dirfase=" + dirfase + '}';
    }
    
}
