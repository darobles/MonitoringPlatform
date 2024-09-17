package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VistaDocumentoTO implements Serializable{
    private BigDecimal idDoc;
    private BigDecimal idCruce;
    private Date fechaDocumento;
    private String tipoDocumento;
    private String archivo;
    private String observacion;          
    private String usrSub;
    private String descripcionFecha;
    private String descripcionTipo;
    private String nombreArchivo;
    private BigDecimal OT_AUTER;
    private String OTM;
    private Date fecha_otm;
    private Date fec_rec_otm;
    private Date fec_medicion;
    private String texto_simac;
    private String desc_medicion;
    private String medicion;
    private String resultado;
    private Date fec_ing_uoct;
    private Date fec_apr_uoct;
    private String archivo2; //ruta
    private String archivo3;
    private String archivo4;
    private String archivo5;
    private String nombreArchivo2; //texto para mostrar
    private String nombreArchivo3;
    private String nombreArchivo4;
    private String nombreArchivo5;
    private String nombre_doc2;
    private String nombre_doc3;
    private String nombre_doc4;
    private String nombre_doc5;

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

    public String getDescripcionFecha() {
        return descripcionFecha;
    }

    public void setDescripcionFecha(String descripcionFecha) {
        this.descripcionFecha = descripcionFecha;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public BigDecimal getOT_AUTER() {
        return OT_AUTER;
    }

    public void setOT_AUTER(BigDecimal OT_AUTER) {
        this.OT_AUTER = OT_AUTER;
    }

    public String getOTM() {
        return OTM;
    }

    public void setOTM(String OTM) {
        this.OTM = OTM;
    }

    public Date getFecha_otm() {
        return fecha_otm;
    }

    public void setFecha_otm(Date fecha_otm) {
        this.fecha_otm = fecha_otm;
    }

    public Date getFec_rec_otm() {
        return fec_rec_otm;
    }

    public void setFec_rec_otm(Date fec_rec_otm) {
        this.fec_rec_otm = fec_rec_otm;
    }

    public Date getFec_medicion() {
        return fec_medicion;
    }

    public void setFec_medicion(Date fec_medicion) {
        this.fec_medicion = fec_medicion;
    }

    public String getTexto_simac() {
        return texto_simac;
    }

    public void setTexto_simac(String texto_simac) {
        this.texto_simac = texto_simac;
    }

    public String getDesc_medicion() {
        return desc_medicion;
    }

    public void setDesc_medicion(String desc_medicion) {
        this.desc_medicion = desc_medicion;
    }

    public String getMedicion() {
        return medicion;
    }

    public void setMedicion(String medicion) {
        this.medicion = medicion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFec_ing_uoct() {
        return fec_ing_uoct;
    }

    public void setFec_ing_uoct(Date fec_ing_uoct) {
        this.fec_ing_uoct = fec_ing_uoct;
    }

    public Date getFec_apr_uoct() {
        return fec_apr_uoct;
    }

    public void setFec_apr_uoct(Date fec_apr_uoct) {
        this.fec_apr_uoct = fec_apr_uoct;
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

    public String getNombreArchivo2() {
        return nombreArchivo2;
    }

    public void setNombreArchivo2(String nombreArchivo2) {
        this.nombreArchivo2 = nombreArchivo2;
    }

    public String getNombreArchivo3() {
        return nombreArchivo3;
    }

    public void setNombreArchivo3(String nombreArchivo3) {
        this.nombreArchivo3 = nombreArchivo3;
    }

    public String getNombreArchivo4() {
        return nombreArchivo4;
    }

    public void setNombreArchivo4(String nombreArchivo4) {
        this.nombreArchivo4 = nombreArchivo4;
    }

    public String getNombreArchivo5() {
        return nombreArchivo5;
    }

    public void setNombreArchivo5(String nombreArchivo5) {
        this.nombreArchivo5 = nombreArchivo5;
    }

    public String getNombre_doc2() {
        return nombre_doc2;
    }

    public void setNombre_doc2(String nombre_doc2) {
        this.nombre_doc2 = nombre_doc2;
    }

    public String getNombre_doc3() {
        return nombre_doc3;
    }

    public void setNombre_doc3(String nombre_doc3) {
        this.nombre_doc3 = nombre_doc3;
    }

    public String getNombre_doc4() {
        return nombre_doc4;
    }

    public void setNombre_doc4(String nombre_doc4) {
        this.nombre_doc4 = nombre_doc4;
    }

    public String getNombre_doc5() {
        return nombre_doc5;
    }

    public void setNombre_doc5(String nombre_doc5) {
        this.nombre_doc5 = nombre_doc5;
    }
    
    
    
}
