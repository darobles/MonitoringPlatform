/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Marco
 */
public class CruceTO implements Serializable{
    
    private BigDecimal idcruce;
    private String calle1;
    private String calle2;
    private String latitud;
    private String longitud;
    private String idDispositivo;
    private BigDecimal idComuna;
    private String controlador;
    private String red;
    private String junction;
    private BigDecimal numBitUtc;
    private String ubicacion;
    private BigDecimal dirActManual;
    private boolean ppr;
    private String imagen;
    private List<FasePPRTO> fasesPPR;
    private String nombreImagen;
    private boolean controlManualActivo;
    private int tiempoRestante;
    private String tipo;
    
    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getJunction() {
        return junction;
    }

    public void setJunction(String junction) {
        this.junction = junction;
    }

    public BigDecimal getNumBitUtc() {
        return numBitUtc;
    }

    public void setNumBitUtc(BigDecimal numBitUtc) {
        this.numBitUtc = numBitUtc;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }   

    public boolean isPpr() {
        return ppr;
    }

    public void setPpr(boolean ppr) {
        this.ppr = ppr;
    }
    
    public BigDecimal getDirActManual() {
        return dirActManual;
    }

    public void setDirActManual(BigDecimal dirActManual) {
        this.dirActManual = dirActManual;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<FasePPRTO> getFasesPPR() {
        return fasesPPR;
    }

    public void setFasesPPR(List<FasePPRTO> fasesPPR) {
        this.fasesPPR = fasesPPR;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public boolean isControlManualActivo() {
        return controlManualActivo;
    }

    public void setControlManualActivo(boolean controlManualActivo) {
        this.controlManualActivo = controlManualActivo;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "CruceTO{" + "idcruce=" + idcruce + ", calle1=" + calle1 + ", calle2=" + calle2 + ", latitud=" + latitud + ", longitud=" + longitud + ", idDispositivo=" + idDispositivo + ", idComuna=" + idComuna + ", controlador=" + controlador + ", red=" + red + ", junction=" + junction + ", numBitUtc=" + numBitUtc + ", ubicacion=" + ubicacion + ", dirActManual=" + dirActManual + ", ppr=" + ppr + ", imagen=" + imagen + ", fasesPPR=" + fasesPPR + ", nombreImagen=" + nombreImagen + ", controlManualActivo=" + controlManualActivo + ", tiempoRestante=" + tiempoRestante + '}';
    }
    
}
