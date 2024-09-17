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
@Table(name = "ATENCION_ARCHIVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtencionArchivoEntity.findAll", query = "SELECT a FROM AtencionArchivoEntity a"),
    @NamedQuery(name = "AtencionArchivoEntity.findByIdAtencion", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.idAtencion = :idAtencion order by a.idArchivo asc"),
    @NamedQuery(name = "AtencionArchivoEntity.findByIdArchivo", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.idArchivo = :idArchivo"),
    @NamedQuery(name = "AtencionArchivoEntity.findByTipo", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AtencionArchivoEntity.findByArchivo", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AtencionArchivoEntity.findByArchivoOrig", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.archivoOrig = :archivoOrig"),
    @NamedQuery(name = "AtencionArchivoEntity.findByFechaHora", query = "SELECT a FROM AtencionArchivoEntity a WHERE a.fechaHora = :fechaHora")})
public class AtencionArchivoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ATENCION")
    private int idAtencion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARCHIVO")
    private Integer idArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ARCHIVO")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ARCHIVO_ORIG")
    private String archivoOrig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    public AtencionArchivoEntity() {
    }

    public AtencionArchivoEntity(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public AtencionArchivoEntity(Integer idArchivo, int idAtencion, int tipo, String archivo, String archivoOrig, Date fechaHora) {
        this.idArchivo = idArchivo;
        this.idAtencion = idAtencion;
        this.tipo = tipo;
        this.archivo = archivo;
        this.archivoOrig = archivoOrig;
        this.fechaHora = fechaHora;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivoOrig() {
        return archivoOrig;
    }

    public void setArchivoOrig(String archivoOrig) {
        this.archivoOrig = archivoOrig;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtencionArchivoEntity)) {
            return false;
        }
        AtencionArchivoEntity other = (AtencionArchivoEntity) object;
        if ((this.idArchivo == null && other.idArchivo != null) || (this.idArchivo != null && !this.idArchivo.equals(other.idArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.AtencionArchivoEntity[ idArchivo=" + idArchivo + " ]";
    }
    
}
