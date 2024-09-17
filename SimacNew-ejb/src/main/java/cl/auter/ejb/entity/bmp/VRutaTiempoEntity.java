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

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "V_RUTA_TIEMPO_MUNICIPAL")
@NamedQueries({
    @NamedQuery(name = "VRutaTiempoEntity.findAll", query = "SELECT v FROM VRutaTiempoEntity v"),
    @NamedQuery(name = "VRutaTiempoEntity.findByEmpresa", query = "SELECT v FROM VRutaTiempoEntity v WHERE v.empresa = :empresa")})

public class VRutaTiempoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RUT")
    @Id
    private BigDecimal idRut;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "LETRERO")
    private BigDecimal letrero;
    @Column(name = "ULTIMACAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimacaptura;
    @Size(max = 19)
    @Column(name = "ULTIMACAPTURASTR")
    private String ultimacapturastr;
    @Column(name = "TPOMINIMO")
    private BigDecimal tpominimo;
    @Column(name = "TPOMAXIMO")
    private BigDecimal tpomaximo;
    @Column(name = "DISTANCIA")
    private BigDecimal distancia;
    @Column(name = "SEGCAPTURADOS")
    private BigDecimal segcapturados;
    @Column(name = "TPOPROPUESTO")
    private BigDecimal tpopropuesto;
    @Column(name = "VEL_X")
    private BigDecimal velX;
    @Size(max = 4000)
    @Column(name = "MIN_PROP")
    private String minProp;
    @Size(max = 4000)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "PTOALARMA")
    private BigDecimal ptoalarma;
    @Column(name = "PTOCONGESTION")
    private BigDecimal ptocongestion;
    @Size(max = 4000)
    @Column(name = "PUNTOSGPS")
    private String puntosgps;
    @Column(name = "EMPRESA")
    private BigDecimal empresa;
    
    public BigDecimal getIdRut() {
        return idRut;
    }

    public void setIdRut(BigDecimal idRut) {
        this.idRut = idRut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getLetrero() {
        return letrero;
    }

    public void setLetrero(BigDecimal letrero) {
        this.letrero = letrero;
    }

    public Date getUltimacaptura() {
        return ultimacaptura;
    }

    public void setUltimacaptura(Date ultimacaptura) {
        this.ultimacaptura = ultimacaptura;
    }

    public String getUltimacapturastr() {
        return ultimacapturastr;
    }

    public void setUltimacapturastr(String ultimacapturastr) {
        this.ultimacapturastr = ultimacapturastr;
    }

    public BigDecimal getTpominimo() {
        return tpominimo;
    }

    public void setTpominimo(BigDecimal tpominimo) {
        this.tpominimo = tpominimo;
    }

    public BigDecimal getTpomaximo() {
        return tpomaximo;
    }

    public void setTpomaximo(BigDecimal tpomaximo) {
        this.tpomaximo = tpomaximo;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getSegcapturados() {
        return segcapturados;
    }

    public void setSegcapturados(BigDecimal segcapturados) {
        this.segcapturados = segcapturados;
    }

    public BigDecimal getTpopropuesto() {
        return tpopropuesto;
    }

    public void setTpopropuesto(BigDecimal tpopropuesto) {
        this.tpopropuesto = tpopropuesto;
    }

    public BigDecimal getVelX() {
        return velX;
    }

    public void setVelX(BigDecimal velX) {
        this.velX = velX;
    }

    public String getMinProp() {
        return minProp;
    }

    public void setMinProp(String minProp) {
        this.minProp = minProp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPtoalarma() {
        return ptoalarma;
    }

    public void setPtoalarma(BigDecimal ptoalarma) {
        this.ptoalarma = ptoalarma;
    }

    public BigDecimal getPtocongestion() {
        return ptocongestion;
    }

    public void setPtocongestion(BigDecimal ptocongestion) {
        this.ptocongestion = ptocongestion;
    }

    public String getPuntosgps() {
        return puntosgps;
    }

    public void setPuntosgps(String puntosgps) {
        this.puntosgps = puntosgps;
    }

    public BigDecimal getEmpresa() {
        return empresa;
    }

    public void setEmpresa(BigDecimal empresa) {
        this.empresa = empresa;
    }
    
}
