/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEnlaceEntity.findAll", query = "SELECT u FROM UsuarioEnlaceEntity u")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findById", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.id = :id")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByIdeUsr", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.ideUsr = :ideUsr")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByNomUsr", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.nomUsr = :nomUsr")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByApePat", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.apePat = :apePat")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByApeMat", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.apeMat = :apeMat")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByIndAct", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.indAct = :indAct")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByFecIng", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.fecIng = :fecIng")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByEmaUsr", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.emaUsr = :emaUsr")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByPasUsr", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.pasUsr = :pasUsr")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByTipo", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.tipo = :tipo")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByNomEmp", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.nomEmp = :nomEmp")
    , @NamedQuery(name = "UsuarioEnlaceEntity.findByRol", query = "SELECT u FROM UsuarioEnlaceEntity u WHERE u.rol = :rol")})
public class UsuarioEnlaceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IDE_USR")
    private String ideUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOM_USR")
    private String nomUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APE_PAT")
    private String apePat;
    @Size(max = 100)
    @Column(name = "APE_MAT")
    private String apeMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "IND_ACT")
    private String indAct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEC_ING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecIng;
    @Size(max = 50)
    @Column(name = "EMA_USR")
    private String emaUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PAS_USR")
    private String pasUsr;
    @Column(name = "TIPO")
    private Integer tipo;
    @Size(max = 100)
    @Column(name = "NOM_EMP")
    private String nomEmp;
    @Size(max = 100)
    @Column(name = "ROL")
    private String rol;

    public UsuarioEnlaceEntity() {
    }

    public UsuarioEnlaceEntity(Integer id) {
        this.id = id;
    }

    public UsuarioEnlaceEntity(Integer id, String ideUsr, String nomUsr, String apePat, String indAct, Date fecIng, String pasUsr) {
        this.id = id;
        this.ideUsr = ideUsr;
        this.nomUsr = nomUsr;
        this.apePat = apePat;
        this.indAct = indAct;
        this.fecIng = fecIng;
        this.pasUsr = pasUsr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdeUsr() {
        return ideUsr;
    }

    public void setIdeUsr(String ideUsr) {
        this.ideUsr = ideUsr;
    }

    public String getNomUsr() {
        return nomUsr;
    }

    public void setNomUsr(String nomUsr) {
        this.nomUsr = nomUsr;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getIndAct() {
        return indAct;
    }

    public void setIndAct(String indAct) {
        this.indAct = indAct;
    }

    public Date getFecIng() {
        return fecIng;
    }

    public void setFecIng(Date fecIng) {
        this.fecIng = fecIng;
    }

    public String getEmaUsr() {
        return emaUsr;
    }

    public void setEmaUsr(String emaUsr) {
        this.emaUsr = emaUsr;
    }

    public String getPasUsr() {
        return pasUsr;
    }

    public void setPasUsr(String pasUsr) {
        this.pasUsr = pasUsr;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
        if (!(object instanceof UsuarioEnlaceEntity)) {
            return false;
        }
        UsuarioEnlaceEntity other = (UsuarioEnlaceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.UsuarioEnlaceEntity[ id=" + id + " ]";
    }
    
}
