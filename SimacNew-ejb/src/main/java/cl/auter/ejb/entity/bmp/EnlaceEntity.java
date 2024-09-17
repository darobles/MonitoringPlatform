/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "ENLACE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnlaceEntity.findAll", query = "SELECT e FROM EnlaceEntity e")
    , @NamedQuery(name = "EnlaceEntity.findByIdEnlace", query = "SELECT e FROM EnlaceEntity e WHERE e.idEnlace = :idEnlace")
    , @NamedQuery(name = "EnlaceEntity.findByIdTipo", query = "SELECT e FROM EnlaceEntity e WHERE e.idTipo = :idTipo")
    , @NamedQuery(name = "EnlaceEntity.findByNroServicio", query = "SELECT e FROM EnlaceEntity e WHERE e.nroServicio = :nroServicio")
    , @NamedQuery(name = "EnlaceEntity.findByIdProveedor", query = "SELECT e FROM EnlaceEntity e WHERE e.idProveedor = :idProveedor")
    , @NamedQuery(name = "EnlaceEntity.findByIdCruce", query = "SELECT e FROM EnlaceEntity e WHERE e.idCruce = :idCruce")
    , @NamedQuery(name = "EnlaceEntity.findByIdCaracteristica", query = "SELECT e FROM EnlaceEntity e WHERE e.idCaracteristica = :idCaracteristica")
    , @NamedQuery(name = "EnlaceEntity.findByFechaHabilitacion", query = "SELECT e FROM EnlaceEntity e WHERE e.fechaHabilitacion = :fechaHabilitacion")
    , @NamedQuery(name = "EnlaceEntity.findByEquipoEnlace", query = "SELECT e FROM EnlaceEntity e WHERE e.equipoEnlace = :equipoEnlace")
    , @NamedQuery(name = "EnlaceEntity.findByUrlConfiguracion", query = "SELECT e FROM EnlaceEntity e WHERE e.urlConfiguracion = :urlConfiguracion")
    , @NamedQuery(name = "EnlaceEntity.findByUrlPlanGeneral", query = "SELECT e FROM EnlaceEntity e WHERE e.urlPlanGeneral = :urlPlanGeneral")
    , @NamedQuery(name = "EnlaceEntity.findByIdUsuarioMod", query = "SELECT e FROM EnlaceEntity e WHERE e.idUsuarioMod = :idUsuarioMod")
    , @NamedQuery(name = "EnlaceEntity.findByFechaActualizacion", query = "SELECT e FROM EnlaceEntity e WHERE e.fechaActualizacion = :fechaActualizacion")})
public class EnlaceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_enlace")
    private Integer idEnlace;
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Size(max = 50)
    @Column(name = "nro_servicio")
    private String nroServicio;
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "id_cruce")
    private Integer idCruce;
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;
    @Column(name = "fecha_habilitacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHabilitacion;
    @Size(max = 255)
    @Column(name = "equipo_enlace")
    private String equipoEnlace;
    @Size(max = 255)
    @Column(name = "url_configuracion")
    private String urlConfiguracion;
    @Size(max = 255)
    @Column(name = "url_plan_general")
    private String urlPlanGeneral;
    @Column(name = "id_usuario_mod")
    private Integer idUsuarioMod;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public EnlaceEntity() {
    }

    public EnlaceEntity(Integer idEnlace) {
        this.idEnlace = idEnlace;
    }

    public Integer getIdEnlace() {
        return idEnlace;
    }

    public void setIdEnlace(Integer idEnlace) {
        this.idEnlace = idEnlace;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNroServicio() {
        return nroServicio;
    }

    public void setNroServicio(String nroServicio) {
        this.nroServicio = nroServicio;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(Integer idCruce) {
        this.idCruce = idCruce;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Date getFechaHabilitacion() {
        return fechaHabilitacion;
    }

    public void setFechaHabilitacion(Date fechaHabilitacion) {
        this.fechaHabilitacion = fechaHabilitacion;
    }

    public String getEquipoEnlace() {
        return equipoEnlace;
    }

    public void setEquipoEnlace(String equipoEnlace) {
        this.equipoEnlace = equipoEnlace;
    }

    public String getUrlConfiguracion() {
        return urlConfiguracion;
    }

    public void setUrlConfiguracion(String urlConfiguracion) {
        this.urlConfiguracion = urlConfiguracion;
    }

    public String getUrlPlanGeneral() {
        return urlPlanGeneral;
    }

    public void setUrlPlanGeneral(String urlPlanGeneral) {
        this.urlPlanGeneral = urlPlanGeneral;
    }

    public Integer getIdUsuarioMod() {
        return idUsuarioMod;
    }

    public void setIdUsuarioMod(Integer idUsuarioMod) {
        this.idUsuarioMod = idUsuarioMod;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnlace != null ? idEnlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnlaceEntity)) {
            return false;
        }
        EnlaceEntity other = (EnlaceEntity) object;
        if ((this.idEnlace == null && other.idEnlace != null) || (this.idEnlace != null && !this.idEnlace.equals(other.idEnlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.entity.bmp.EnlaceEntity[ idEnlace=" + idEnlace + " ]";
    }
    
}
