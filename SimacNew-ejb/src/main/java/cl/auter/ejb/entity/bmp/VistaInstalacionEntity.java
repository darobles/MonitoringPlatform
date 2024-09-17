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
@Table(name = "VISTA_INSTALACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaInstalacionEntity.findAll", query = "SELECT v FROM VistaInstalacionEntity v")
    , @NamedQuery(name = "VistaInstalacionEntity.InstalacionPorMonitoreo", query = "select c from VistaInstalacionEntity c, ComunaEntity d where c.idcomuna = d.idComuna and d.ind_mon = 'SI' order by c.idcruce")
    , @NamedQuery(name = "VistaInstalacionEntity.findByIdcruce", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.idcruce = :idcruce")
    , @NamedQuery(name = "VistaInstalacionEntity.findByIdcomuna", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.idcomuna = :idcomuna")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNomComuna", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.nomComuna = :nomComuna")
    , @NamedQuery(name = "VistaInstalacionEntity.findByUbicacion", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.ubicacion = :ubicacion")
    , @NamedQuery(name = "VistaInstalacionEntity.findByCodigoSistema", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.codigoSistema = :codigoSistema")
    , @NamedQuery(name = "VistaInstalacionEntity.findByIdInstalacion", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.idInstalacion = :idInstalacion")
    , @NamedQuery(name = "VistaInstalacionEntity.findByEmpalme", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.empalme = :empalme")
    , @NamedQuery(name = "VistaInstalacionEntity.findByLatitud", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.latitud = :latitud")
    , @NamedQuery(name = "VistaInstalacionEntity.findByLongitud", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.longitud = :longitud")
    , @NamedQuery(name = "VistaInstalacionEntity.findByTipo", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "VistaInstalacionEntity.findByEnlace", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.enlace = :enlace")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumCabezales", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numCabezales = :numCabezales")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumEspScoot", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numEspScoot = :numEspScoot")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumEspLocal", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numEspLocal = :numEspLocal")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumBotoneras", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numBotoneras = :numBotoneras")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumHitElec", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numHitElec = :numHitElec")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumHitSolar", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numHitSolar = :numHitSolar")
    , @NamedQuery(name = "VistaInstalacionEntity.findByUps", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.ups = :ups")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumCtaRegres", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numCtaRegres = :numCtaRegres")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumSenSenTto", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numSenSenTto = :numSenSenTto")
    , @NamedQuery(name = "VistaInstalacionEntity.findByNumLetVms", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.numLetVms = :numLetVms")
    , @NamedQuery(name = "VistaInstalacionEntity.findByBalizas", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.balizas = :balizas")
    , @NamedQuery(name = "VistaInstalacionEntity.findByTipoCable", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.tipoCable = :tipoCable")
    , @NamedQuery(name = "VistaInstalacionEntity.findByModeloOtu", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.modeloOtu = :modeloOtu")
    , @NamedQuery(name = "VistaInstalacionEntity.findByObservacion", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.observacion = :observacion")
    , @NamedQuery(name = "VistaInstalacionEntity.findByFecAct", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.fecAct = :fecAct")
    , @NamedQuery(name = "VistaInstalacionEntity.findByDatoFiltro", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.datoFiltro = :datoFiltro")
    , @NamedQuery(name = "VistaInstalacionEntity.findByRed", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.red = :red")
    , @NamedQuery(name = "VistaInstalacionEntity.findByControlador", query = "SELECT v FROM VistaInstalacionEntity v WHERE v.controlador = :controlador")})

public class VistaInstalacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCRUCE")
    private BigDecimal idcruce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOMUNA")
    private BigDecimal idcomuna;
    @Size(max = 500)
    @Column(name = "NOM_COMUNA")
    private String nomComuna;
    @Size(max = 1005)
    @Column(name = "UBICACION")
    private String ubicacion;
    @Size(max = 500)
    @Column(name = "CODIGO_SISTEMA")
    private String codigoSistema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INSTALACION")
    @Id
    private BigDecimal idInstalacion;
    @Size(max = 500)
    @Column(name = "EMPALME")
    private String empalme;
    @Size(max = 100)
    @Column(name = "LATITUD")
    private String latitud;
    @Size(max = 100)
    @Column(name = "LONGITUD")
    private String longitud;
    @Size(max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 500)
    @Column(name = "ENLACE")
    private String enlace;
    @Column(name = "NUM_CABEZALES")
    private BigDecimal numCabezales;
    @Column(name = "NUM_ESP_SCOOT")
    private BigDecimal numEspScoot;
    @Column(name = "NUM_ESP_LOCAL")
    private BigDecimal numEspLocal;
    @Column(name = "NUM_BOTONERAS")
    private BigDecimal numBotoneras;
    @Column(name = "NUM_HIT_ELEC")
    private BigDecimal numHitElec;
    @Column(name = "NUM_HIT_SOLAR")
    private BigDecimal numHitSolar;
    @Size(max = 100)
    @Column(name = "UPS")
    private String ups;
    @Column(name = "NUM_CTA_REGRES")
    private BigDecimal numCtaRegres;
    @Column(name = "NUM_SEN_SEN_TTO")
    private BigDecimal numSenSenTto;
    @Column(name = "NUM_LET_VMS")
    private BigDecimal numLetVms;
    @Column(name = "BALIZAS")
    private BigDecimal balizas;
    @Size(max = 1000)
    @Column(name = "TIPO_CABLE")
    private String tipoCable;
    @Size(max = 1000)
    @Column(name = "MODELO_OTU")
    private String modeloOtu;
    @Size(max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "FEC_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecAct;
    @Size(max = 4000)
    @Column(name = "DATO_FILTRO")
    private String datoFiltro;
    @Size(max = 100)
    @Column(name = "RED")
    private String red;
    @Size(max = 500)
    @Column(name = "CONTROLADOR")
    private String controlador;

    public VistaInstalacionEntity() {
    }

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public BigDecimal getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(BigDecimal idcomuna) {
        this.idcomuna = idcomuna;
    }

    public String getNomComuna() {
        return nomComuna;
    }

    public void setNomComuna(String nomComuna) {
        this.nomComuna = nomComuna;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public BigDecimal getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(BigDecimal idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public String getEmpalme() {
        return empalme;
    }

    public void setEmpalme(String empalme) {
        this.empalme = empalme;
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
        return balizas;
    }

    public void setBalizas(BigDecimal balizas) {
        this.balizas = balizas;
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

    public Date getFecAct() {
        return fecAct;
    }

    public void setFecAct(Date fecAct) {
        this.fecAct = fecAct;
    }

    public String getDatoFiltro() {
        return datoFiltro;
    }

    public void setDatoFiltro(String datoFiltro) {
        this.datoFiltro = datoFiltro;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }
    
}
