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
@Table(name = "RECLAMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReclamoEntity.findAll", query = "SELECT r FROM ReclamoEntity r")
    , @NamedQuery(name = "ReclamoEntity.findByIdReclamo", query = "SELECT r FROM ReclamoEntity r WHERE r.idReclamo = :idReclamo")
    , @NamedQuery(name = "ReclamoEntity.findByIdEnlace", query = "SELECT r FROM ReclamoEntity r WHERE r.idEnlace = :idEnlace")
    , @NamedQuery(name = "ReclamoEntity.findByIdTicket", query = "SELECT r FROM ReclamoEntity r WHERE r.idTicket = :idTicket")
    , @NamedQuery(name = "ReclamoEntity.findByIdCruce", query = "SELECT r FROM ReclamoEntity r WHERE r.idCruce = :idCruce")
    , @NamedQuery(name = "ReclamoEntity.findByFechaIngreso", query = "SELECT r FROM ReclamoEntity r WHERE r.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "ReclamoEntity.findByIdEstado", query = "SELECT r FROM ReclamoEntity r WHERE r.idEstado = :idEstado")
    , @NamedQuery(name = "ReclamoEntity.findByFechaTermino", query = "SELECT r FROM ReclamoEntity r WHERE r.fechaTermino = :fechaTermino")
    , @NamedQuery(name = "ReclamoEntity.findByIdTipoFalla", query = "SELECT r FROM ReclamoEntity r WHERE r.idTipoFalla = :idTipoFalla")
    , @NamedQuery(name = "ReclamoEntity.findByObservacion", query = "SELECT r FROM ReclamoEntity r WHERE r.observacion = :observacion")
    , @NamedQuery(name = "ReclamoEntity.findByIdResponsable", query = "SELECT r FROM ReclamoEntity r WHERE r.idResponsable = :idResponsable")
    , @NamedQuery(name = "ReclamoEntity.findByIdTipoAtencion", query = "SELECT r FROM ReclamoEntity r WHERE r.idTipoAtencion = :idTipoAtencion")})
public class ReclamoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reclamo")
    private Integer idReclamo;
    @Column(name = "id_enlace")
    private Integer idEnlace;
    @Size(max = 50)
    @Column(name = "id_ticket")
    private String idTicket;
    @Column(name = "id_cruce")
    private Integer idCruce;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "id_estado")
    private Integer idEstado;
    @Column(name = "fecha_termino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;
    @Column(name = "id_tipo_falla")
    private Integer idTipoFalla;
    @Size(max = 2000)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 50)
    @Column(name = "id_responsable")
    private String idResponsable;
    @Column(name = "id_tipo_atencion")
    private Integer idTipoAtencion;

    public ReclamoEntity() {
    }

    public ReclamoEntity(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Integer getIdEnlace() {
        return idEnlace;
    }

    public void setIdEnlace(Integer idEnlace) {
        this.idEnlace = idEnlace;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(Integer idCruce) {
        this.idCruce = idCruce;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Integer getIdTipoFalla() {
        return idTipoFalla;
    }

    public void setIdTipoFalla(Integer idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Integer getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public void setIdTipoAtencion(Integer idTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReclamo != null ? idReclamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReclamoEntity)) {
            return false;
        }
        ReclamoEntity other = (ReclamoEntity) object;
        if ((this.idReclamo == null && other.idReclamo != null) || (this.idReclamo != null && !this.idReclamo.equals(other.idReclamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.ReclamoEntity[ idReclamo=" + idReclamo + " ]";
    }
    
}
