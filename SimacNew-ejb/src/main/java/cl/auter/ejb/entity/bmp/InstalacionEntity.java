/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Marco
 */
@Entity
@Table(name="INSTALACION")
@SequenceGenerator(name="SEQ_INSTALACION_GEN",sequenceName="SEQ_INSTALACION", initialValue=1, allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "InstalacionEntity.buscarInstalacionPorId",query = "SELECT o FROM InstalacionEntity o WHERE o.id = :idInstalacion "),
    @NamedQuery(name = "InstalacionEntity.buscarInstalacionPorIdCruce",query = "SELECT o FROM InstalacionEntity o WHERE o.idCruce = :idCruce ")
})
public class InstalacionEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INSTALACION_GEN")
    @Column(name = "ID_INSTALACION", nullable = false )
    private BigDecimal id;
    @Column(name = "ID_CRUCE", nullable = false )
    private BigDecimal idCruce;
    @Column(name = "LATITUD", nullable = false )
    private String latitud;
    @Column(name = "LONGITUD", nullable = false )
    private String longitud;
    @Column(name = "TIPO", nullable = false )
    private String tipo;
    @Column(name = "ENLACE", nullable = false )
    private String enlace;
    @Column(name = "NUM_CABEZALES", nullable = false )
    private BigDecimal numCabezales;
    @Column(name = "NUM_ESP_SCOOT", nullable = false )
    private BigDecimal numEspScoot;
    @Column(name = "NUM_ESP_LOCAL", nullable = false )
    private BigDecimal numEspLocal;
    @Column(name = "NUM_BOTONERAS", nullable = false )
    private BigDecimal numBotoneras;
    @Column(name = "NUM_HIT_ELEC", nullable = false )
    private BigDecimal numHitElec;
    @Column(name = "NUM_HIT_SOLAR", nullable = false )
    private BigDecimal numHitSolar;
    @Column(name = "UPS", nullable = false )
    private String ups;
    @Column(name = "NUM_CTA_REGRES", nullable = false )
    private BigDecimal numCtaRegres;
    @Column(name = "NUM_SEN_SEN_TTO", nullable = false )
    private BigDecimal numSenSenTto;
    @Column(name = "NUM_LET_VMS", nullable = false )
    private BigDecimal numLetVms;
    @Column(name = "BALIZAS", nullable = false )
    private BigDecimal Balizas;
    @Column(name = "TIPO_CABLE", nullable = false )
    private String tipoCable;
    @Column(name = "MODELO_OTU", nullable = false )
    private String modeloOtu;
    @Column(name = "OBSERVACION", nullable = false )
    private String observacion;
    @Column(name = "FEC_ACT", nullable = false )
    private Date fechaActual;
    @Column(name = "EMPALME", nullable = false )
    private String empalme;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public BigDecimal getNumCabezales() {
        return numCabezales;
    }

    public void setNumCabezales(BigDecimal numCabezales) {
        this.numCabezales = numCabezales;
    }

    public BigDecimal getNumEspScoot() {
        return numEspScoot;
    }

    public void setNumEspScoot(BigDecimal numEspScoot) {
        this.numEspScoot = numEspScoot;
    }

    public BigDecimal getNumEspLocal() {
        return numEspLocal;
    }

    public void setNumEspLocal(BigDecimal numEspLocal) {
        this.numEspLocal = numEspLocal;
    }

    public BigDecimal getNumBotoneras() {
        return numBotoneras;
    }

    public void setNumBotoneras(BigDecimal numBotoneras) {
        this.numBotoneras = numBotoneras;
    }

    public BigDecimal getNumHitElec() {
        return numHitElec;
    }

    public void setNumHitElec(BigDecimal numHitElec) {
        this.numHitElec = numHitElec;
    }

    public BigDecimal getNumHitSolar() {
        return numHitSolar;
    }

    public void setNumHitSolar(BigDecimal numHitSolar) {
        this.numHitSolar = numHitSolar;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public BigDecimal getNumCtaRegres() {
        return numCtaRegres;
    }

    public void setNumCtaRegres(BigDecimal numCtaRegres) {
        this.numCtaRegres = numCtaRegres;
    }

    public BigDecimal getNumSenSenTto() {
        return numSenSenTto;
    }

    public void setNumSenSenTto(BigDecimal numSenSenTto) {
        this.numSenSenTto = numSenSenTto;
    }

    public BigDecimal getNumLetVms() {
        return numLetVms;
    }

    public void setNumLetVms(BigDecimal numLetVms) {
        this.numLetVms = numLetVms;
    }

    public BigDecimal getBalizas() {
        return Balizas;
    }

    public void setBalizas(BigDecimal Balizas) {
        this.Balizas = Balizas;
    }

    public String getTipoCable() {
        return tipoCable;
    }

    public void setTipoCable(String tipoCable) {
        this.tipoCable = tipoCable;
    }

    public String getModeloOtu() {
        return modeloOtu;
    }

    public void setModeloOtu(String modeloOtu) {
        this.modeloOtu = modeloOtu;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getEmpalme() {
        return empalme;
    }

    public void setEmpalme(String empalme) {
        this.empalme = empalme;
    }    
}
