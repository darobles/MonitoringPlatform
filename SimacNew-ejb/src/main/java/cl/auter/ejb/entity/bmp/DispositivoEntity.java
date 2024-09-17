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
@Table(name="DISPOSITIVO")
@SequenceGenerator(name="SEQ_DISPOSITIVO_GEN",sequenceName="SEQ_DISPOSITIVO", initialValue=1, allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "DispositivoEntity.buscarDispositivoPorId",query = "SELECT o FROM DispositivoEntity o WHERE o.idDispositivo = :idDispositivo "),
    @NamedQuery(name = "DispositivoEntity.buscarDispositivoTodos",query = "SELECT o FROM DispositivoEntity o order by o.idDispositivo desc")
})
public class DispositivoEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DISPOSITIVO_GEN")
    @Column(name = "IDDISPOSITIVO", nullable = false )
    private BigDecimal idDispositivo;
    @Column(name = "IMEI", nullable = false )
    private String imei;      
    @Column(name = "DIRIP", nullable = false )
    private String dirip;
    @Column(name = "DYNDNS", nullable = false )
    private String dyndns;
    @Column(name = "UBICACION", nullable = false )
    private String ubicacion;
    @Column(name = "NUMSERIE", nullable = false )
    private String numSerie;
    @Column(name = "COMENTARIO", nullable = false )
    private String comentario;
    @Column(name = "ACTIVO", nullable = false )
    private String activo;
    @Column(name = "FECHAACT", nullable = false )
    private Date fechaAct;
    @Column(name = "ESTADOOPERATIVO", nullable = false )
    private String estadoOperativo;
    @Column(name = "VALMODO220", nullable = false )
    private BigDecimal valModo220;
    @Column(name = "VALMODOUPS", nullable = false )
    private BigDecimal valModoUps;        
    @Column(name = "VALMODOUTC1", nullable = false )
    private BigDecimal valModoUtc1;
    @Column(name = "VALMODOLAM", nullable = false )
    private BigDecimal valModoLam;        
    @Column(name = "IND_UPS", nullable = false )
    private String indUps;
    @Column(name = "VALMODOUTC2", nullable = false )
    private BigDecimal valModoUtc2;
    @Column(name = "VALMODOUTC3", nullable = false )
    private BigDecimal valModoUtc3;
    @Column(name = "VALMODOUTC4", nullable = false )
    private BigDecimal valModoUtc4;
    @Column(name = "NUMTEL", nullable = false )
    private String numTel;
    @Column(name = "IND_OTU", nullable = false )
    private String indOtu;
    @Column(name = "IND_RESET_CTR", nullable = false )
    private BigDecimal indResetCtr;
    @Column(name = "TIPO_MONITOREO", nullable = false )
    private BigDecimal tipoMonitoreo;
    @Column(name = "IND_LAM_FAULT", nullable = false )
    private BigDecimal indLamFault;

    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDirip() {
        return dirip;
    }

    public void setDirip(String dirip) {
        this.dirip = dirip;
    }

    public String getDyndns() {
        return dyndns;
    }

    public void setDyndns(String dyndns) {
        this.dyndns = dyndns;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaAct() {
        return fechaAct;
    }

    public void setFechaAct(Date fechaAct) {
        this.fechaAct = fechaAct;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public BigDecimal getValModo220() {
        return valModo220;
    }

    public void setValModo220(BigDecimal valModo220) {
        this.valModo220 = valModo220;
    }

    public BigDecimal getValModoUps() {
        return valModoUps;
    }

    public void setValModoUps(BigDecimal valModoUps) {
        this.valModoUps = valModoUps;
    }

    public BigDecimal getValModoUtc1() {
        return valModoUtc1;
    }

    public void setValModoUtc1(BigDecimal valModoUtc1) {
        this.valModoUtc1 = valModoUtc1;
    }

    public BigDecimal getValModoLam() {
        return valModoLam;
    }

    public void setValModoLam(BigDecimal valModoLam) {
        this.valModoLam = valModoLam;
    }

    public String getIndUps() {
        return indUps;
    }

    public void setIndUps(String indUps) {
        this.indUps = indUps;
    }

    public BigDecimal getValModoUtc2() {
        return valModoUtc2;
    }

    public void setValModoUtc2(BigDecimal valModoUtc2) {
        this.valModoUtc2 = valModoUtc2;
    }

    public BigDecimal getValModoUtc3() {
        return valModoUtc3;
    }

    public void setValModoUtc3(BigDecimal valModoUtc3) {
        this.valModoUtc3 = valModoUtc3;
    }

    public BigDecimal getValModoUtc4() {
        return valModoUtc4;
    }

    public void setValModoUtc4(BigDecimal valModoUtc4) {
        this.valModoUtc4 = valModoUtc4;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getIndOtu() {
        return indOtu;
    }

    public void setIndOtu(String indOtu) {
        this.indOtu = indOtu;
    }

    public BigDecimal getIndResetCtr() {
        return indResetCtr;
    }

    public void setIndResetCtr(BigDecimal indResetCtr) {
        this.indResetCtr = indResetCtr;
    }

    public BigDecimal getTipoMonitoreo() {
        return tipoMonitoreo;
    }

    public void setTipoMonitoreo(BigDecimal tipoMonitoreo) {
        this.tipoMonitoreo = tipoMonitoreo;
    }

    public BigDecimal getIndLamFault() {
        return indLamFault;
    }

    public void setIndLamFault(BigDecimal indLamFault) {
        this.indLamFault = indLamFault;
    }
    
}
