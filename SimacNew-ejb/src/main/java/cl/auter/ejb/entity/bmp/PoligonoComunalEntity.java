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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "poligono_comunal")
@SequenceGenerator(name = "SEQ_POLIGONO", sequenceName = "SEQ_POLIGONO", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoligonoComunalEntity.findAll", query = "SELECT p FROM PoligonoComunalEntity p"),
    @NamedQuery(name = "PoligonoComunalEntity.findByIdcomuna", query = "SELECT p FROM PoligonoComunalEntity p WHERE p.idcomuna = :idcomuna order by p.orden asc"),
    @NamedQuery(name = "PoligonoComunalEntity.findByLongitud", query = "SELECT p FROM PoligonoComunalEntity p WHERE p.longitud = :longitud"),
    @NamedQuery(name = "PoligonoComunalEntity.findByLatitud", query = "SELECT p FROM PoligonoComunalEntity p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "PoligonoComunalEntity.findByOrden", query = "SELECT p FROM PoligonoComunalEntity p WHERE p.orden = :orden"),
    @NamedQuery(name = "PoligonoComunalEntity.findByIdpoligono", query = "SELECT p FROM PoligonoComunalEntity p WHERE p.idpoligono = :idpoligono")})
public class PoligonoComunalEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "idcomuna")
    private Integer idcomuna;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "orden")
    private Integer orden;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POLIGONO")
    @NotNull
    @Column(name = "idpoligono")
    private Integer idpoligono;

    public PoligonoComunalEntity() {
    }

    public PoligonoComunalEntity(Integer idpoligono) {
        this.idpoligono = idpoligono;
    }

    public Integer getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(Integer idcomuna) {
        this.idcomuna = idcomuna;
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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getIdpoligono() {
        return idpoligono;
    }

    public void setIdpoligono(Integer idpoligono) {
        this.idpoligono = idpoligono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpoligono != null ? idpoligono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoligonoComunalEntity)) {
            return false;
        }
        PoligonoComunalEntity other = (PoligonoComunalEntity) object;
        if ((this.idpoligono == null && other.idpoligono != null) || (this.idpoligono != null && !this.idpoligono.equals(other.idpoligono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.PoligonoComunalEntity[ idpoligono=" + idpoligono + " ]";
    }
    
}
