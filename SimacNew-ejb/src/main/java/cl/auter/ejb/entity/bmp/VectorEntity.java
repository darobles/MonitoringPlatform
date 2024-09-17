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
@Table(name = "VECTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VectorEntity.findAll", query = "SELECT v FROM VectorEntity v"),
    @NamedQuery(name = "VectorEntity.findById", query = "SELECT v FROM VectorEntity v WHERE v.id = :id"),
    @NamedQuery(name = "VectorEntity.findByAntValAlg", query = "SELECT v FROM VectorEntity v WHERE v.antValAlg = :antValAlg")})
public class VectorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "IDFABRICA")
    private BigInteger idfabrica;
    @Column(name = "ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacion;
    @Column(name = "AVGTIME")
    private BigDecimal avgtime;
    @Column(name = "DISTANCIA")
    private BigDecimal distancia;
    @Column(name = "SPEED")
    private BigDecimal speed;
    @Column(name = "NODOINICIAL")
    private BigInteger nodoinicial;
    @Column(name = "NODOFINAL")
    private BigInteger nodofinal;
    @Size(max = 50)
    @Column(name = "ID_MARCA")
    private String idMarca;
    @Size(max = 100)
    @Column(name = "TRAYECTO")
    private String trayecto;
    @Column(name = "PCINICIAL")
    private BigInteger pcinicial;
    @Column(name = "PCFINAL")
    private BigInteger pcfinal;
    @Column(name = "PCMINICIAL")
    private BigInteger pcminicial;
    @Column(name = "PCMFINAL")
    private BigInteger pcmfinal;
    @Column(name = "FPCMINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fpcminicial;
    @Column(name = "FPCMFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fpcmfinal;
    @Column(name = "MOVIL")
    private BigInteger movil;
    @Column(name = "NUM_LEC")
    private BigInteger numLec;
    @Column(name = "FEC_SIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecSis;
    @Size(max = 50)
    @Column(name = "INF_MAIL_ERR")
    private String infMailErr;
    @Column(name = "NEW_VAL_ALG")
    private BigDecimal newValAlg;
    @Column(name = "ANT_VAL_ALG")
    private BigDecimal antValAlg;

    public VectorEntity() {
    }

    public VectorEntity(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getIdfabrica() {
        return idfabrica;
    }

    public void setIdfabrica(BigInteger idfabrica) {
        this.idfabrica = idfabrica;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public BigDecimal getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(BigDecimal avgtime) {
        this.avgtime = avgtime;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigInteger getNodoinicial() {
        return nodoinicial;
    }

    public void setNodoinicial(BigInteger nodoinicial) {
        this.nodoinicial = nodoinicial;
    }

    public BigInteger getNodofinal() {
        return nodofinal;
    }

    public void setNodofinal(BigInteger nodofinal) {
        this.nodofinal = nodofinal;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(String trayecto) {
        this.trayecto = trayecto;
    }

    public BigInteger getPcinicial() {
        return pcinicial;
    }

    public void setPcinicial(BigInteger pcinicial) {
        this.pcinicial = pcinicial;
    }

    public BigInteger getPcfinal() {
        return pcfinal;
    }

    public void setPcfinal(BigInteger pcfinal) {
        this.pcfinal = pcfinal;
    }

    public BigInteger getPcminicial() {
        return pcminicial;
    }

    public void setPcminicial(BigInteger pcminicial) {
        this.pcminicial = pcminicial;
    }

    public BigInteger getPcmfinal() {
        return pcmfinal;
    }

    public void setPcmfinal(BigInteger pcmfinal) {
        this.pcmfinal = pcmfinal;
    }

    public Date getFpcminicial() {
        return fpcminicial;
    }

    public void setFpcminicial(Date fpcminicial) {
        this.fpcminicial = fpcminicial;
    }

    public Date getFpcmfinal() {
        return fpcmfinal;
    }

    public void setFpcmfinal(Date fpcmfinal) {
        this.fpcmfinal = fpcmfinal;
    }

    public BigInteger getMovil() {
        return movil;
    }

    public void setMovil(BigInteger movil) {
        this.movil = movil;
    }

    public BigInteger getNumLec() {
        return numLec;
    }

    public void setNumLec(BigInteger numLec) {
        this.numLec = numLec;
    }

    public Date getFecSis() {
        return fecSis;
    }

    public void setFecSis(Date fecSis) {
        this.fecSis = fecSis;
    }

    public String getInfMailErr() {
        return infMailErr;
    }

    public void setInfMailErr(String infMailErr) {
        this.infMailErr = infMailErr;
    }

    public BigDecimal getNewValAlg() {
        return newValAlg;
    }

    public void setNewValAlg(BigDecimal newValAlg) {
        this.newValAlg = newValAlg;
    }

    public BigDecimal getAntValAlg() {
        return antValAlg;
    }

    public void setAntValAlg(BigDecimal antValAlg) {
        this.antValAlg = antValAlg;
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
        if (!(object instanceof VectorEntity)) {
            return false;
        }
        VectorEntity other = (VectorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.Vector[ id=" + id + " ]";
    }
    
}
