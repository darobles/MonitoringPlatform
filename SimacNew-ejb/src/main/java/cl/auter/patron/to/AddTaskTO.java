/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Marco
 */
public class AddTaskTO implements Serializable{
    
    public BigDecimal idCruce;
    public BigDecimal funInicial;
    public Date fecha;
    public String observaciones;
    public BigDecimal idTipoEquipo;
    public BigDecimal tipoFuente;
    public BigDecimal idgrupo;
    public String nombreFuente;
    public BigDecimal tecnico;
    public String url;
    public String mail;

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public BigDecimal getFunInicial() {
        return funInicial;
    }

    public void setFunInicial(BigDecimal funInicial) {
        this.funInicial = funInicial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(BigDecimal idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public BigDecimal getTipoFuente() {
        return tipoFuente;
    }

    public void setTipoFuente(BigDecimal tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    public BigDecimal getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(BigDecimal idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public BigDecimal getTecnico() {
        return tecnico;
    }

    public void setTecnico(BigDecimal tecnico) {
        this.tecnico = tecnico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
  
  
    
}
