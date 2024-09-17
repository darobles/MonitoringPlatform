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
@Table(name = "HISTORIAL_RECLAMOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialReclamoEntity.findAll", query = "SELECT h FROM HistorialReclamoEntity h")
    , @NamedQuery(name = "HistorialReclamoEntity.findByIdHistorial", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.idHistorial = :idHistorial")
    , @NamedQuery(name = "HistorialReclamoEntity.findByIdReclamo", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.idReclamo = :idReclamo")
    , @NamedQuery(name = "HistorialReclamoEntity.findByFechaModificacion", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "HistorialReclamoEntity.findByIdEstado", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.idEstado = :idEstado")
    , @NamedQuery(name = "HistorialReclamoEntity.findByObservacion", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.observacion = :observacion")
    , @NamedQuery(name = "HistorialReclamoEntity.findByIdUsuario", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.idUsuario = :idUsuario")
    , @NamedQuery(name = "HistorialReclamoEntity.findByIdTarea", query = "SELECT h FROM HistorialReclamoEntity h WHERE h.idTarea = :idTarea")})
public class HistorialReclamoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Column(name = "id_reclamo")
    private Integer idReclamo;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "id_estado")
    private Integer idEstado;
    @Size(max = 2000)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_tarea")
    private Integer idTarea;

    public HistorialReclamoEntity() {
    }

    public HistorialReclamoEntity(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialReclamoEntity)) {
            return false;
        }
        HistorialReclamoEntity other = (HistorialReclamoEntity) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.HistorialReclamosEntity[ idHistorial=" + idHistorial + " ]";
    }
    
}
