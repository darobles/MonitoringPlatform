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
@Table(name = "vista_reclamo_coordinacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaReclamoCoordinacionEntity.findAll", query = "SELECT v FROM VistaReclamoCoordinacionEntity v")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByTipo", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByIdReclamo", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.idReclamo = :idReclamo order by v.fecha asc")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findById", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.id = :id")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByFecha", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByIdEstado", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.idEstado = :idEstado")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByDescEstado", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.descEstado = :descEstado")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByObservacion", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.observacion = :observacion")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByIdUsuario", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByNombre", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByApellido1", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.apellido1 = :apellido1")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByApellido2", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.apellido2 = :apellido2")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByNombreProveedor", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByIdTarea", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.idTarea = :idTarea")
    , @NamedQuery(name = "VistaReclamoCoordinacionEntity.findByResultado", query = "SELECT v FROM VistaReclamoCoordinacionEntity v WHERE v.resultado = :resultado")})

public class VistaReclamoCoordinacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "id_reclamo")
    private Integer idReclamo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "id_estado")
    private Integer idEstado;
    @Size(max = 500)
    @Column(name = "desc_estado")
    private String descEstado;
    @Size(max = 2000)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "apellido1")
    private String apellido1;
    @Size(max = 100)
    @Column(name = "apellido2")
    private String apellido2;
    @Size(max = 500)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Column(name = "id_tarea")
    private Integer idTarea;
    @Size(max = 500)
    @Column(name = "resultado")
    private String resultado;

    public VistaReclamoCoordinacionEntity() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
