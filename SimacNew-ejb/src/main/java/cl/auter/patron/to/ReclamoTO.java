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
public class ReclamoTO {

    private Integer idReclamo;
    private Integer idEnlace;
    private String idTicket;
    private Integer idCruce;
    private Date fechaIngreso;
    private Integer idEstado;
    private Date fechaTermino;
    private Integer idTipoFalla;
    private String observacion;
    private String idResponsable;
    private Integer idTipoAtencion;
    private EnlaceTO enlace;
    private ComunaTO comuna;
    private String descEstado;
    private String descProveedor;
    
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

    public EnlaceTO getEnlace() {
        return enlace;
    }

    public void setEnlace(EnlaceTO enlace) {
        this.enlace = enlace;
    }

    public ComunaTO getComuna() {
        return comuna;
    }

    public void setComuna(ComunaTO comuna) {
        this.comuna = comuna;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescProveedor() {
        return descProveedor;
    }

    public void setDescProveedor(String descProveedor) {
        this.descProveedor = descProveedor;
    }
    
    @Override
    public String toString() {
        return "ReclamoTO{" + "idReclamo=" + idReclamo + ", idEnlace=" + idEnlace + ", idTicket=" + idTicket + ", idCruce=" + idCruce + ", fechaIngreso=" + fechaIngreso + ", idEstado=" + idEstado + ", fechaTermino=" + fechaTermino + ", idTipoFalla=" + idTipoFalla + ", observacion=" + observacion + ", idResponsable=" + idResponsable + ", idTipoAtencion=" + idTipoAtencion + '}';
    }
    
}
