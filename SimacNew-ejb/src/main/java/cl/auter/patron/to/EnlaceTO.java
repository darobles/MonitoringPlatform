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
public class EnlaceTO {

    private Integer idEnlace;
    private Integer idTipo;
    private String nroServicio;
    private Integer idProveedor;
    private Integer idCruce;
    private Integer idCaracteristica;
    private Date fechaHabilitacion;
    private String equipoEnlace;
    private String urlConfiguracion;
    private String urlPlanGeneral;
    private Integer idUsuarioMod;
    private Date fechaActualizacion;
    private CruceTO cruce;
    
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

    public CruceTO getCruce() {
        return cruce;
    }

    public void setCruce(CruceTO cruce) {
        this.cruce = cruce;
    }
    
    

    @Override
    public String toString() {
        return "EnlaceTO{" + "idEnlace=" + idEnlace + ", idTipo=" + idTipo + ", nroServicio=" + nroServicio + ", idProveedor=" + idProveedor + ", idCruce=" + idCruce + ", idCaracteristica=" + idCaracteristica + ", fechaHabilitacion=" + fechaHabilitacion + ", equipoEnlace=" + equipoEnlace + ", urlConfiguracion=" + urlConfiguracion + ", urlPlanGeneral=" + urlPlanGeneral + ", idUsuarioMod=" + idUsuarioMod + ", fechaActualizacion=" + fechaActualizacion + '}';
    }
    
    
}
