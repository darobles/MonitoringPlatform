package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VISTA_CRUCE_DISPOSITIVO")
@NamedQueries({
    @NamedQuery(name = "CrucesPorComunaYIdDispositivo", query = "select c from VistaCruceEntity c where c.idDispositivo is not null and c.idComuna = :idComuna and  c.estadoOperativo IN :lista")
    ,@NamedQuery(name = "CrucesPorComuna", query = "select c from VistaCruceEntity c where c.idDispositivo is not null and c.idComuna = :idComuna ")
    ,@NamedQuery(name = "CrucesPorEstadoLista", query = "select c from VistaCruceEntity c where c.idDispositivo is not null and  c.estadoOperativo IN :lista ")
    ,@NamedQuery(name = "CrucesTodos", query = "select c from VistaCruceEntity c where c.idDispositivo is not null")
    ,@NamedQuery(name = "CrucesNoEncendidos", query = "select c from VistaCruceEntity c where c.estadoOperativo != '1' and c.estadoOperativo != '4' ")
    ,@NamedQuery(name = "CrucesNoEncendidosPorComuna", query = "select c from VistaCruceEntity c where c.estadoOperativo != '1' and c.idComuna = :idComuna")
    ,@NamedQuery(name = "CrucesCantPorComuna", query = "select c from VistaCruceEntity c where c.estadoOperativo = :estado and c.idComuna = :idComuna")
    ,@NamedQuery(name = "CrucesPorId", query = "select c from VistaCruceEntity c where c.idCruce = :idCruce")
    ,@NamedQuery(name = "CrucesPorEstado", query = "select c from VistaCruceEntity c where c.estadoOperativo = :estado order by c.idCruce asc ")    
})
public class VistaCruceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "IDDISPOSITIVO")
    private BigDecimal idDispositivo;
    @Id
    @Column(name = "IDCRUCE")
    private BigDecimal idCruce;

    @Column(name = "UBICACION")
    private String ubicacion;

    @Column(name = "CONTROLADOR")
    private String controlador;

    @Column(name = "ACTIVO")
    private String activo;

    @Column(name = "FECHAACT")
    private Date fechaAct;

    @Column(name = "ESTADOOPERATIVO")
    private String estadoOperativo;

    @Column(name = "VALMODO220")
    private BigDecimal valModo220;

    @Column(name = "VALMODOUPS")
    private BigDecimal valModoUps;

    @Column(name = "VALMODOUTC")
    private BigDecimal valModoUtc;
    
    @Column(name = "VALMODOUTC4")
    private BigDecimal valModoUtc4;
    
    @Column(name = "VALMODOLAM")
    private BigDecimal valModoLam;

    @Column(name = "RED")
    private String red;

    @Column(name = "LONGITUD")
    private String longitud;

    @Column(name = "LATITUD")
    private String latitud;

    @Column(name = "CODIGO_SISTEMA")
    private String codigoSistema;

    @Column(name = "IDCOMUNA", nullable = false)
    private BigDecimal idComuna;
    
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "OBSERVACION")
    private String observacion;

    @Column(name = "NUM_EQUI_MON")
    private String numeroEquipo;

    @Column(name = "DIRIP")
    private String direccionIP;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "TIPO_MONITOREO", nullable = false)
    private BigDecimal tipoMonitoreo;

    @Column(name = "PPR", nullable = false)
    private BigDecimal ppr;

    @Column(name = "IND_LAM_FAULT", nullable = false)
    private BigDecimal indLamFault;

    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
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

    public BigDecimal getValModoUtc() {
        return valModoUtc;
    }

    public void setValModoUtc(BigDecimal valModoUtc) {
        this.valModoUtc = valModoUtc;
    }

    public BigDecimal getValModoUtc4() {
        return valModoUtc4;
    }

    public void setValModoUtc4(BigDecimal valModoUtc4) {
        this.valModoUtc4 = valModoUtc4;
    }
    
    public BigDecimal getValModoLam() {
        return valModoLam;
    }

    public void setValModoLam(BigDecimal valModoLam) {
        this.valModoLam = valModoLam;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroEquipo() {
        return numeroEquipo;
    }

    public void setNumeroEquipo(String numeroEquipo) {
        this.numeroEquipo = numeroEquipo;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getTipoMonitoreo() {
        return tipoMonitoreo;
    }

    public void setTipoMonitoreo(BigDecimal tipoMonitoreo) {
        this.tipoMonitoreo = tipoMonitoreo;
    }

    public BigDecimal getPpr() {
        return ppr;
    }

    public void setPpr(BigDecimal ppr) {
        this.ppr = ppr;
    }

    public BigDecimal getIndLamFault() {
        return indLamFault;
    }

    public void setIndLamFault(BigDecimal indLamFault) {
        this.indLamFault = indLamFault;
    }
    
    

}
