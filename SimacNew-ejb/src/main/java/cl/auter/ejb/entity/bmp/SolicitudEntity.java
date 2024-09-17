package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
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

@Entity
@Table(name="SOLICITUD")
@SequenceGenerator(name="SEQ_SOLICITUD_GEN",sequenceName="SEQ_SOLICITUD", initialValue=1, allocationSize=1)
@NamedQueries({ 
    @NamedQuery(name = "SolicitudEntity.buscarSolicitud",query = "SELECT DISTINCT o FROM SolicitudEntity o order by o.idSolicitud desc "),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudPorId",query = "SELECT DISTINCT o FROM SolicitudEntity o where o.idSolicitud = :idsolicitud "),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudUOCT",query = "SELECT DISTINCT o FROM SolicitudEntity o where o.tipoSolicitante  = :tipoSolicitante or o.numReclamo > :numero and o.fechaIngreso between :fecIni and :fecEnd order by o.idSolicitud desc "),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudPorTipoSolicitante",query = "SELECT DISTINCT o FROM SolicitudEntity o where o.tipoSolicitante  = :tipoSolicitante and o.fechaIngreso between :fecIni and :fecEnd order by o.idSolicitud desc"),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudPorComunaFecha",query = "SELECT o FROM SolicitudEntity o where o.idComuna  = :idComuna and o.fechaIngreso between :fecIni and :fecEnd order by o.idSolicitud desc "),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudPorFecha",query = "SELECT o FROM SolicitudEntity o where o.fechaIngreso between :fecIni and :fecEnd order by o.idSolicitud desc "),
    @NamedQuery(name = "SolicitudEntity.buscarSolicitudPorComuna",query = "SELECT DISTINCT o FROM SolicitudEntity o where o.idComuna  = :idComuna order by o.idSolicitud desc ")                
})
public class SolicitudEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SOLICITUD_GEN")
    @Column(name = "IDSOLICITUD", nullable = false )
    private BigDecimal idSolicitud;
    @Column(name = "NOMBRESOLICITANTE", nullable = false )
    private String nombreSolicitante;
    @Column(name = "EMAILSOLICITANTE", nullable = false )
    private String emailSolicitante;
    @Column(name = "FONOSOLICITANTE", nullable = false )
    private String fonoSolicitante;
    @Column(name = "OBSERVACION", nullable = false )
    private Clob observacion;
    @Column(name = "URLADJUNTO", nullable = false )
    private String urlAdjunto;
    @Column(name = "ESTADOACTUAL", nullable = false )
    private String estadoActual;     
    @Column(name = "FECING", nullable = false )
    private Date fechaIngreso; 
    @Column(name = "USR_ING", nullable = false )
    private String usrIngresado;
    @Column(name = "NUMTAREASMA", nullable = false )
    private BigDecimal numTareaAsma;
    @Column(name = "ASUNTO", nullable = false )
    private String asunto;
    @Column(name = "IDCOMUNA", nullable = false )
    private BigDecimal idComuna;
    @Column(name = "MOTIVO_RECHAZO", nullable = false )
    private Clob motivoRechazo;
    @Column(name = "TIPO_SOLICITANTE", nullable = false )
    private BigDecimal tipoSolicitante;
    @Column(name = "NUMRECLAMO", nullable = false )
    private BigDecimal numReclamo;

    public BigDecimal getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(BigDecimal idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    public String getFonoSolicitante() {
        return fonoSolicitante;
    }

    public void setFonoSolicitante(String fonoSolicitante) {
        this.fonoSolicitante = fonoSolicitante;
    }

    public Clob getObservacion() {
        return observacion;
    }

    public void setObservacion(Clob observacion) {
        this.observacion = observacion;
    }

    public String getUrlAdjunto() {
        return urlAdjunto;
    }

    public void setUrlAdjunto(String urlAdjunto) {
        this.urlAdjunto = urlAdjunto;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsrIngresado() {
        return usrIngresado;
    }

    public void setUsrIngresado(String usrIngresado) {
        this.usrIngresado = usrIngresado;
    }

    public BigDecimal getNumTareaAsma() {
        return numTareaAsma;
    }

    public void setNumTareaAsma(BigDecimal numTareaAsma) {
        this.numTareaAsma = numTareaAsma;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public Clob getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(Clob motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public BigDecimal getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(BigDecimal tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public BigDecimal getNumReclamo() {
        return numReclamo;
    }

    public void setNumReclamo(BigDecimal numReclamo) {
        this.numReclamo = numReclamo;
    }
    
}
