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
@Table(name = "VISTA_TAREA_WEB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaTareaWebEntity.findAll", query = "SELECT v FROM VistaTareaWebEntity v")
    , @NamedQuery(name = "VistaTareaWebEntity.findByIdtarea", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.idtarea = :idtarea")
    , @NamedQuery(name = "VistaTareaWebEntity.findByTipoequipo", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.tipoequipo = :tipoequipo")
    , @NamedQuery(name = "VistaTareaWebEntity.findByFuncionamientiini", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.funcionamientiini = :funcionamientiini")
    , @NamedQuery(name = "VistaTareaWebEntity.findByIdcruce", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.idcruce = :idcruce")
    , @NamedQuery(name = "VistaTareaWebEntity.findByFechaCreacionStr", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.fechaCreacionStr = :fechaCreacionStr")
    , @NamedQuery(name = "VistaTareaWebEntity.findByObspreliminar", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.obspreliminar = :obspreliminar")
    , @NamedQuery(name = "VistaTareaWebEntity.findByTareaterminada", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.tareaterminada = :tareaterminada")
    , @NamedQuery(name = "VistaTareaWebEntity.findByInformadoPor", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.informadoPor = :informadoPor")
    , @NamedQuery(name = "VistaTareaWebEntity.findByNombrefuente", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.nombrefuente = :nombrefuente")
    , @NamedQuery(name = "VistaTareaWebEntity.findByFechaCierreStr", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.fechaCierreStr = :fechaCierreStr")
    , @NamedQuery(name = "VistaTareaWebEntity.findByFechacreacion", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.fechacreacion = :fechacreacion")
    , @NamedQuery(name = "VistaTareaWebEntity.findByFechacierre", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.fechacierre = :fechacierre")
    , @NamedQuery(name = "VistaTareaWebEntity.findByIdtipoequipo", query = "SELECT v FROM VistaTareaWebEntity v WHERE v.idtipoequipo = :idtipoequipo")})

public class VistaTareaWebEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTAREA")
    @Id
    private int idtarea;
    @Size(max = 25)
    @Column(name = "tipoequipo")
    private String tipoequipo;
    @Size(max = 25)
    @Column(name = "funcionamientiini")
    private String funcionamientiini;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCRUCE")
    private int idcruce;
    @Size(max = 61)
    @Column(name = "fecha_creacion_str")
    private String fechaCreacionStr;
    @Size(max = 200)
    @Column(name = "OBSPRELIMINAR")
    private String obspreliminar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tareaterminada")
    private String tareaterminada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "informadoPor")
    private String informadoPor;
    @Size(max = 20)
    @Column(name = "NOMBREFUENTE")
    private String nombrefuente;
    @Size(max = 61)
    @Column(name = "fecha_cierre_str")
    private String fechaCierreStr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacion;
    @Column(name = "FECHACIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacierre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTIPOEQUIPO")
    private short idtipoequipo;

    public VistaTareaWebEntity() {
    }

    public int getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(int idtarea) {
        this.idtarea = idtarea;
    }

    public String getTipoequipo() {
        return tipoequipo;
    }

    public void setTipoequipo(String tipoequipo) {
        this.tipoequipo = tipoequipo;
    }

    public String getFuncionamientiini() {
        return funcionamientiini;
    }

    public void setFuncionamientiini(String funcionamientiini) {
        this.funcionamientiini = funcionamientiini;
    }

    public int getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(int idcruce) {
        this.idcruce = idcruce;
    }

    public String getFechaCreacionStr() {
        return fechaCreacionStr;
    }

    public void setFechaCreacionStr(String fechaCreacionStr) {
        this.fechaCreacionStr = fechaCreacionStr;
    }

    public String getObspreliminar() {
        return obspreliminar;
    }

    public void setObspreliminar(String obspreliminar) {
        this.obspreliminar = obspreliminar;
    }

    public String getTareaterminada() {
        return tareaterminada;
    }

    public void setTareaterminada(String tareaterminada) {
        this.tareaterminada = tareaterminada;
    }

    public String getInformadoPor() {
        return informadoPor;
    }

    public void setInformadoPor(String informadoPor) {
        this.informadoPor = informadoPor;
    }

    public String getNombrefuente() {
        return nombrefuente;
    }

    public void setNombrefuente(String nombrefuente) {
        this.nombrefuente = nombrefuente;
    }

    public String getFechaCierreStr() {
        return fechaCierreStr;
    }

    public void setFechaCierreStr(String fechaCierreStr) {
        this.fechaCierreStr = fechaCierreStr;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public short getIdtipoequipo() {
        return idtipoequipo;
    }

    public void setIdtipoequipo(short idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }
    
}
