/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "VISTA_CONT_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaContEstadoEntity.findAll", query = "SELECT v FROM VistaContEstadoEntity v"),
    @NamedQuery(name = "VistaContEstadoEntity.findById", query = "SELECT v FROM VistaContEstadoEntity v WHERE v.id = :id"),
    @NamedQuery(name = "VistaContEstadoEntity.findByEncendidos", query = "SELECT v FROM VistaContEstadoEntity v WHERE v.encendidos = :encendidos"),
    @NamedQuery(name = "VistaContEstadoEntity.findByObservacion", query = "SELECT v FROM VistaContEstadoEntity v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "VistaContEstadoEntity.findByApagado", query = "SELECT v FROM VistaContEstadoEntity v WHERE v.apagado = :apagado"),
    @NamedQuery(name = "VistaContEstadoEntity.findByInvalidos", query = "SELECT v FROM VistaContEstadoEntity v WHERE v.invalidos = :invalidos")})
public class VistaContEstadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Column(name = "ENCENDIDOS")
    private BigInteger encendidos;
    @Column(name = "OBSERVACION")
    private BigInteger observacion;
    @Column(name = "APAGADO")
    private BigInteger apagado;
    @Column(name = "INVALIDOS")
    private BigInteger invalidos;

    public VistaContEstadoEntity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getEncendidos() {
        return encendidos;
    }

    public void setEncendidos(BigInteger encendidos) {
        this.encendidos = encendidos;
    }

    public BigInteger getObservacion() {
        return observacion;
    }

    public void setObservacion(BigInteger observacion) {
        this.observacion = observacion;
    }

    public BigInteger getApagado() {
        return apagado;
    }

    public void setApagado(BigInteger apagado) {
        this.apagado = apagado;
    }

    public BigInteger getInvalidos() {
        return invalidos;
    }

    public void setInvalidos(BigInteger invalidos) {
        this.invalidos = invalidos;
    }
    
}
