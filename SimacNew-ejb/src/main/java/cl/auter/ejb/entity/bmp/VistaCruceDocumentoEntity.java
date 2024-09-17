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
import javax.persistence.Temporal;
@Entity
@Table(name="VISTA_CRUCE_DOCUMENTO")   
@NamedQueries({
    @NamedQuery(name = "DocumentosPorInstalacion", query = "select c from VistaCruceDocumentoEntity c where c.idCruce = ? ")
    })
public class VistaCruceDocumentoEntity implements Serializable {
private static final long serialVersionUID = 1L;

    @Column(name = "ID_DOC" )    
    @Id
    private BigDecimal idDoc;
    @Column(name = "IDCRUCE" )
    private BigDecimal idCruce;
    @Column(name = "FEC_DOC" )
    private Date fechaDocumento;
    @Column(name = "TIP_DOC" )
    private String tipoDocumento;
    @Column(name = "ARCHIVO" )
    private String archivo;
    @Column(name = "OBSERVACION" )
    private String observacion;          
    @Column(name = "USR_SUB" )
    private String usrSub;
        @Column(name = "NOMBRE_DOC", nullable = false)
    private String nombreDoc;
    @Column(name = "OT_AUTER", nullable = false)
    private BigDecimal otAuter;
    @Column(name = "OTM", nullable = false)
    private String otm;
    @Column(name = "FECHA_OTM", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaOtm;
    @Column(name = "FEC_REC_OTM", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecRecOtm;
    @Column(name = "FEC_MEDICION", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecMedicion;
    @Column(name = "TEXTO_SIMAC", nullable = false)
    private String textoSimac;
    @Column(name = "DESC_MEDICION", nullable = false)
    private String descMedicion;
    @Column(name = "RESULTADO", nullable = false)
    private String resultado;
    @Column(name = "FEC_ING_UOCT", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecIngUoct;
    @Column(name = "FEC_APR_UOCT", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecAprUoct;
    @Column(name = "ARCHIVO2", nullable = false)
    private String archivo2;
    @Column(name = "ARCHIVO3", nullable = false)
    private String archivo3;
    @Column(name = "ARCHIVO4", nullable = false)
    private String archivo4;
    @Column(name = "ARCHIVO5", nullable = false)
    private String archivo5;
    @Column(name = "NOMBRE_DOC2", nullable = false)
    private String nombreDoc2;
    @Column(name = "NOMBRE_DOC3", nullable = false)
    private String nombreDoc3;
    @Column(name = "NOMBRE_DOC4", nullable = false)
    private String nombreDoc4;
    @Column(name = "NOMBRE_DOC5", nullable = false)
    private String nombreDoc5;
    
    public BigDecimal getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(BigDecimal idDoc) {
        this.idDoc = idDoc;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsrSub() {
        return usrSub;
    }

    public void setUsrSub(String usrSub) {
        this.usrSub = usrSub;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public BigDecimal getOtAuter() {
        return otAuter;
    }

    public void setOtAuter(BigDecimal otAuter) {
        this.otAuter = otAuter;
    }

    public String getOtm() {
        return otm;
    }

    public void setOtm(String otm) {
        this.otm = otm;
    }

    public Date getFechaOtm() {
        return fechaOtm;
    }

    public void setFechaOtm(Date fechaOtm) {
        this.fechaOtm = fechaOtm;
    }

    public Date getFecRecOtm() {
        return fecRecOtm;
    }

    public void setFecRecOtm(Date fecRecOtm) {
        this.fecRecOtm = fecRecOtm;
    }

    public Date getFecMedicion() {
        return fecMedicion;
    }

    public void setFecMedicion(Date fecMedicion) {
        this.fecMedicion = fecMedicion;
    }

    public String getTextoSimac() {
        return textoSimac;
    }

    public void setTextoSimac(String textoSimac) {
        this.textoSimac = textoSimac;
    }

    public String getDescMedicion() {
        return descMedicion;
    }

    public void setDescMedicion(String descMedicion) {
        this.descMedicion = descMedicion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecIngUoct() {
        return fecIngUoct;
    }

    public void setFecIngUoct(Date fecIngUoct) {
        this.fecIngUoct = fecIngUoct;
    }

    public Date getFecAprUoct() {
        return fecAprUoct;
    }

    public void setFecAprUoct(Date fecAprUoct) {
        this.fecAprUoct = fecAprUoct;
    }

    public String getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(String archivo2) {
        this.archivo2 = archivo2;
    }

    public String getArchivo3() {
        return archivo3;
    }

    public void setArchivo3(String archivo3) {
        this.archivo3 = archivo3;
    }

    public String getArchivo4() {
        return archivo4;
    }

    public void setArchivo4(String archivo4) {
        this.archivo4 = archivo4;
    }

    public String getArchivo5() {
        return archivo5;
    }

    public void setArchivo5(String archivo5) {
        this.archivo5 = archivo5;
    }

    public String getNombreDoc2() {
        return nombreDoc2;
    }

    public void setNombreDoc2(String nombreDoc2) {
        this.nombreDoc2 = nombreDoc2;
    }

    public String getNombreDoc3() {
        return nombreDoc3;
    }

    public void setNombreDoc3(String nombreDoc3) {
        this.nombreDoc3 = nombreDoc3;
    }

    public String getNombreDoc4() {
        return nombreDoc4;
    }

    public void setNombreDoc4(String nombreDoc4) {
        this.nombreDoc4 = nombreDoc4;
    }

    public String getNombreDoc5() {
        return nombreDoc5;
    }

    public void setNombreDoc5(String nombreDoc5) {
        this.nombreDoc5 = nombreDoc5;
    }
    
    

}
