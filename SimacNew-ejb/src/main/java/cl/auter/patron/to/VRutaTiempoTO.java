/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class VRutaTiempoTO {
    
    private BigDecimal idRut;
    private String nombre;
    private BigDecimal letrero;
    private Date ultimacaptura;
    private String ultimacapturastr;
    private BigDecimal tpominimo;
    private BigDecimal tpomaximo;
    private BigDecimal distancia;
    private BigDecimal segcapturados;
    private BigDecimal tpopropuesto;
    private BigDecimal velX;
    private String minProp;
    private String color;
    private BigDecimal ptoalarma;
    private BigDecimal ptocongestion;
    private String puntosgps;
    private BigDecimal empresa;
    private int ordenColor;
    
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

    public int getOrdenColor() {
        return ordenColor;
    }

    public void setOrdenColor(int ordenColor) {
        this.ordenColor = ordenColor;
    }
    
    

    @Override
    public String toString() {
        return "VRutaMovilDAO{" + "idRut=" + idRut + ", nombre=" + nombre + ", letrero=" + letrero + ", ultimacaptura=" + ultimacaptura + ", ultimacapturastr=" + ultimacapturastr + ", tpominimo=" + tpominimo + ", tpomaximo=" + tpomaximo + ", distancia=" + distancia + ", segcapturados=" + segcapturados + ", tpopropuesto=" + tpopropuesto + ", velX=" + velX + ", minProp=" + minProp + ", color=" + color + ", ptoalarma=" + ptoalarma + ", ptocongestion=" + ptocongestion + ", puntosgps=" + puntosgps + ", empresa=" + empresa + '}';
    }
    
}
