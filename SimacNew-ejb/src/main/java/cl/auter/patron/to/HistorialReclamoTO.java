/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.util.Date;

/**
 *
 * @author drobles
 */
public class HistorialReclamoTO {

    private Integer idHistorial;
    private Integer idReclamo;
    private Date fechaModificacion;
    private Integer idEstado;
    private String observacion;
    private Integer idUsuario;
    private Integer idTarea;
    private String descEstado;
    private UsuarioEnlaceTO usuario;
    private String fecModStr;

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

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public UsuarioEnlaceTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEnlaceTO usuario) {
        this.usuario = usuario;
    }

    public String getFecModStr() {
        return fecModStr;
    }

    public void setFecModStr(String fecModStr) {
        this.fecModStr = fecModStr;
    }
    
    
    

    @Override
    public String toString() {
        return "HistorialReclamoTO{" + "idHistorial=" + idHistorial + ", idReclamo=" + idReclamo + ", fechaModificacion=" + fechaModificacion + ", idEstado=" + idEstado + ", observacion=" + observacion + ", idUsuario=" + idUsuario + ", idTarea=" + idTarea + '}';
    }
    
}
