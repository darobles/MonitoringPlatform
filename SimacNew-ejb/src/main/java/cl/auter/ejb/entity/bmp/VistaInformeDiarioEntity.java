/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "VISTA_INFORME_DIARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaInformeDiarioEntity.findAll", query = "SELECT v FROM VistaInformeDiarioEntity v")
    , @NamedQuery(name = "VistaInformeDiarioEntity.findByFechasalida", query = "SELECT v FROM VistaInformeDiarioEntity v WHERE v.fechasalida = :fechasalida")
    , @NamedQuery(name = "VistaInformeDiarioEntity.findByUbicacion", query = "SELECT v FROM VistaInformeDiarioEntity v WHERE v.ubicacion = :ubicacion")
    , @NamedQuery(name = "VistaInformeDiarioEntity.informeDiario", query = "SELECT v FROM VistaInformeDiarioEntity v WHERE v.idComuna = :idComuna and v.idcruce = :idCruce and v.fechallegada > :fechaDesde and v.fechallegada < :fechaHasta order by v.fechallegada asc")
    , @NamedQuery(name = "VistaInformeDiarioEntity.informeDiarioSinCruce", query = "SELECT v FROM VistaInformeDiarioEntity v WHERE v.idComuna = :idComuna and v.fechallegada between to_date(:fechaDesde,'dd-MM-yyyy hh24:mi:ss') and to_date(:fechaHasta,'dd-MM-yyyy hh24:mi:ss')")  
    , @NamedQuery(name = "VistaInformeDiarioEntity.findByLlamada", query = "SELECT v FROM VistaInformeDiarioEntity v WHERE v.llamada = :llamada")})

public class VistaInformeDiarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @Id
    private BigDecimal id;
    @Column(name = "IDATENCION")
    private BigDecimal idatencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCRUCE")
    private BigDecimal idcruce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMUNA")
    private BigDecimal idComuna;
    @Column(name = "FECHALLEGADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechallegada;
    @Size(max = 100)
    @Column(name = "ESTADOINICIAL")
    private String estadoinicial;
    @Size(max = 100)
    @Column(name = "ESTADOFINAL")
    private String estadofinal;
    @Size(max = 100)
    @Column(name = "ESTADOTAREA")
    private String estadotarea;
    @Size(max = 1000)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "FECHASALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;
    @Size(max = 100)
    @Column(name = "NOMBRETIPOORIGEN")
    private String nombretipoorigen;
    @Size(max = 1005)
    @Column(name = "UBICACION")
    private String ubicacion;
    @Column(name = "LLAMADA")
    private String llamada;
    @Column(name = "TIPO")
    private String tipo;

    public VistaInformeDiarioEntity() {
    }

    public BigDecimal getIdatencion() {
        return idatencion;
    }

    public void setIdatencion(BigDecimal idatencion) {
        this.idatencion = idatencion;
    }

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public Date getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(Date fechallegada) {
        this.fechallegada = fechallegada;
    }

    public String getEstadoinicial() {
        return estadoinicial;
    }

    public void setEstadoinicial(String estadoinicial) {
        this.estadoinicial = estadoinicial;
    }

    public String getEstadofinal() {
        return estadofinal;
    }

    public void setEstadofinal(String estadofinal) {
        this.estadofinal = estadofinal;
    }

    public String getEstadotarea() {
        return estadotarea;
    }

    public void setEstadotarea(String estadotarea) {
        this.estadotarea = estadotarea;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public String getNombretipoorigen() {
        return nombretipoorigen;
    }

    public void setNombretipoorigen(String nombretipoorigen) {
        this.nombretipoorigen = nombretipoorigen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLlamada() {
        return llamada;
    }

    public void setLlamada(String llamada) {
        this.llamada = llamada;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
