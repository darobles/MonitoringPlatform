/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.CruceDocumentoTO;
import cl.auter.patron.to.CruceTO;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Marco
 */
@ManagedBean(name = DocumentoBacking.BEAN_NAME)
@ViewScoped
public class DocumentoBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "documentoBacking";
    public String codigoPag = "239";
    private String idComuna;
    private String comboHabilitada;
    private List<CruceTO> listaPuntos = new ArrayList();
    private List<CruceDocumentoTO> listaDocumentos = new ArrayList();
    private CruceDocumentoTO cruceDocumentoTO = new CruceDocumentoTO();
    private CruceDocumentoTO detCruceDocumentoTO = new CruceDocumentoTO();
    private List<CodigoTO> listaTipoDcto = new ArrayList();
    BigDecimal idSeleccionado;
    private CruceTO cruceTO = new CruceTO();
    private boolean verPopup = false;
    private boolean verPopupEditar = false;
    private boolean bloqIdCruce = false;
    private UploadedFile archivo1;
    private UploadedFile archivo2;
    private UploadedFile archivo3;
    private UploadedFile archivo4;
    private UploadedFile archivo5;
    private byte[] inputArchivo;
    private byte[] inputArchivo1;
    private byte[] inputArchivo2;
    private byte[] inputArchivo3;
    private byte[] inputArchivo4;
    private byte[] inputArchivo5;
    private boolean verGrProyectos = false;
    private boolean verGrJustificacion = false;
    private boolean verGrMediciones = false;
    private boolean verGrid = false;
    private boolean verGrEstPer = false;
    private boolean verGrEstPro = false;
    private boolean verGrEstSinFina = false;
    private boolean verPopupDetalle = false;
    private String nombreArchivo1 = "";
    private String nombreArchivo2 = "";
    private String nombreArchivo3 = "";
    private String nombreArchivo4 = "";
    private String nombreArchivo5 = "";

    @PostConstruct
    public void carga() {
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))) {
            this.comboHabilitada = "false";
        } else {
            this.idComuna = String.valueOf(usurioAutenticado().getIdComuna());
            this.comboHabilitada = "true";
            this.listaPuntos.clear();
            try {
                this.listaPuntos = getLocalizacionFacadeLocal().buscarCrucePorIdComuna(new BigDecimal(this.idComuna));
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cargaTipoDocumento();
    }

    public void cargaTipoDocumento() {
        listaTipoDcto.clear();
        try {
            listaTipoDcto = getParametrosFacadeLocal().listaPorDominio("TIPO_DOCUMENTO");
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioComuna(ValueChangeEvent e) {
        String id = (String) e.getNewValue();
        this.listaPuntos.clear();
        try {
            this.listaPuntos = getLocalizacionFacadeLocal().buscarCrucePorIdComuna(new BigDecimal(id));
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarDocumento() {
        cruceTO = new CruceTO();
        try {
            cruceTO = getLocalizacionFacadeLocal().buscarCrucePorId(idSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargaDocumentoPorId();
        bloqIdCruce = true;
        verGrid = true;
        verPopupEditar = true;
    }

    public void crearDocumento() {
        cruceTO = new CruceTO();
        verGrid = true;
        verPopup = true;
    }

    public void uploadFiles(FileUploadEvent event) {

        String id = (String) event.getComponent().getAttributes().get("id");
        String tipo = (String) event.getComponent().getAttributes().get("tipo");
        try {
            switch (tipo) {
                //archivo comun
                case "1":
                    archivo1 = event.getFile();
                    inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                    nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                // mediciones
                case "2":
                    if (id.equals("fuMediciones1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuMediciones2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    }
                    break;
                // Justificacion
                case "3":
                    if (id.equals("fuJusti1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuJusti2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuJusti3")) {
                        archivo3 = event.getFile();
                        inputArchivo3 = IOUtils.toByteArray(archivo3.getInputstream());
                        nombreArchivo3 = FilenameUtils.getName(event.getFile().getFileName());
                    }
                // Proyectos
                case "4":
                    if (id.equals("fuProy1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuProy2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuProy3")) {
                        archivo3 = event.getFile();
                        inputArchivo3 = IOUtils.toByteArray(archivo3.getInputstream());
                        nombreArchivo3 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuProy4")) {
                        archivo4 = event.getFile();
                        inputArchivo4 = IOUtils.toByteArray(archivo4.getInputstream());
                        nombreArchivo4 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuProy5")) {
                        archivo5 = event.getFile();
                        inputArchivo5 = IOUtils.toByteArray(archivo5.getInputstream());
                        nombreArchivo5 = FilenameUtils.getName(event.getFile().getFileName());
                    }
                case "5":
                    if (id.equals("fuEstPer1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuEstPer2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    } 
                case "6":
                    if (id.equals("fuEstPro1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuEstPro2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    } 
                case "7":
                    if (id.equals("fuSinFin1")) {
                        archivo1 = event.getFile();
                        inputArchivo1 = IOUtils.toByteArray(archivo1.getInputstream());
                        nombreArchivo1 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuSinFin2")) {
                        archivo2 = event.getFile();
                        inputArchivo2 = IOUtils.toByteArray(archivo2.getInputstream());
                        nombreArchivo2 = FilenameUtils.getName(event.getFile().getFileName());
                    } else if (id.equals("fuSinFin3")) {
                        archivo3 = event.getFile();
                        inputArchivo3 = IOUtils.toByteArray(archivo3.getInputstream());
                        nombreArchivo3 = FilenameUtils.getName(event.getFile().getFileName());
                    }
                default:
                    break;
            }

            FacesMessage msg = new FacesMessage("Preparado", "El archivo " + event.getFile().getFileName() + " esta listo para ser cargado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException ex) {
            Logger.getLogger(DocumentoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargaDocumentoPorId() {
        listaDocumentos.clear();
        try {
            listaDocumentos = getLocalizacionFacadeLocal().buscarDocumentoPorIdCruce(idSeleccionado);
            for (CruceDocumentoTO cruceDocumentoTO : listaDocumentos) {
                cruceDocumentoTO.setTipoDesc(getParametrosFacadeLocal().buscarDominioCodigo("TIPO_DOCUMENTO", cruceDocumentoTO.getTipo()).getDescripcion());
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeTipo() {
        verGrJustificacion = false;
        verGrMediciones = false;
        verGrProyectos = false;
        verGrid = false;
        verGrEstPer = false;
        verGrEstPro = false;
        verGrEstSinFina = false;
        inputArchivo1 = null;
        inputArchivo2 = null;
        inputArchivo3 = null;
        inputArchivo4 = null;
        inputArchivo5 = null;
        nombreArchivo1 = "";
        nombreArchivo2 = "";
        nombreArchivo3 = "";
        nombreArchivo4 = "";
        nombreArchivo5 = "";
        if (cruceDocumentoTO.getTipo().equals("1") || cruceDocumentoTO.getTipo().equals("2") || cruceDocumentoTO.getTipo().equals("3") || cruceDocumentoTO.getTipo().equals("99")) {
            verGrid = true;
        } else if (cruceDocumentoTO.getTipo().equals("4")) {
            verGrMediciones = true;
        } else if (cruceDocumentoTO.getTipo().equals("5")) {
            verGrJustificacion = true;
        } else if (cruceDocumentoTO.getTipo().equals("6")) {
            verGrProyectos = true;
        } else if (cruceDocumentoTO.getTipo().equals("7")) {
            verGrEstPer = true;
        }else if (cruceDocumentoTO.getTipo().equals("8")) {
            verGrEstPro = true;
        }else if (cruceDocumentoTO.getTipo().equals("9")) {
            verGrEstSinFina = true;
        }
    }

    public void actualizarDocumentoCruce() {

    }

    public boolean validarDatosCruce() {
        return true;
    }

    public void verDetalleDoc(CruceDocumentoTO doc) {
        detCruceDocumentoTO = doc;
        verPopupDetalle = true;
    }

    public void cerrarDetalle() {
        verPopupDetalle = false;
    }

    public void cerrarCrear() {
        verPopup = false;
        verGrJustificacion = false;
        verGrMediciones = false;
        verGrProyectos = false;
        verGrEstPer = false;
        verGrEstPer = false;
        verGrEstPro = false;
        verGrEstSinFina = false;
        verGrid = false;
        archivo1 = null;
        archivo2 = null;
        archivo3 = null;
        archivo4 = null;
        archivo5 = null;
        nombreArchivo1 = "";
        nombreArchivo2 = "";
        nombreArchivo3 = "";
        nombreArchivo4 = "";
        nombreArchivo5 = "";
        cruceDocumentoTO = new CruceDocumentoTO();
    }

    public void mostrarMensaje(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
    }

    public void eliminarDocumento(BigDecimal idDoc) {
        try {
            CruceDocumentoTO cruceDocumentoEliTO = getLocalizacionFacadeLocal().buscarCruceDocumentoPorId(idDoc);
            if (!cruceDocumentoEliTO.getIdDoc().equals(new BigDecimal("0"))) {
                getLocalizacionFacadeLocal().eliminarDocumento(cruceDocumentoEliTO);
                if (eliminarArchivo(cruceDocumentoEliTO.getArchivo())) {
                    System.out.println("Archivo " + cruceDocumentoEliTO.getNombreArchivo() + " subido por " + usurioAutenticado().getIdentificador());
                } else {
                    System.out.println("El archivo " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
                }
                if (!cruceDocumentoEliTO.getArchivo2().equals("") && eliminarArchivo(cruceDocumentoEliTO.getArchivo2())) {
                    System.out.println("Archivo " + cruceDocumentoEliTO.getNombre_doc2() + " subido por " + usurioAutenticado().getIdentificador());
                } else {
                    System.out.println("El archivo " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
                }
                if (!cruceDocumentoEliTO.getArchivo3().equals("") && eliminarArchivo(cruceDocumentoEliTO.getArchivo3())) {
                    System.out.println("Archivo " + cruceDocumentoEliTO.getNombre_doc3() + " subido por " + usurioAutenticado().getIdentificador());
                } else {
                    System.out.println("El archivo " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
                }
                if (!cruceDocumentoEliTO.getArchivo4().equals("") && eliminarArchivo(cruceDocumentoEliTO.getArchivo4())) {
                    System.out.println("Archivo " + cruceDocumentoEliTO.getNombre_doc4() + " subido por " + usurioAutenticado().getIdentificador());
                } else {
                    System.out.println("El archivo " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
                }
                if (!cruceDocumentoEliTO.getArchivo5().equals("") && eliminarArchivo(cruceDocumentoEliTO.getArchivo5())) {
                    System.out.println("Archivo " + cruceDocumentoEliTO.getNombre_doc5() + " subido por " + usurioAutenticado().getIdentificador());
                } else {
                    System.out.println("El archivo " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
                }
            } else {
                System.out.println("El documento " + cruceDocumentoEliTO.getIdDoc() + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
            }
        } catch (Exception ex) {
            Logger.getLogger(DocumentoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargaDocumentoPorId();
    }

    public void guardarDocumento() {
        try {
            String ruta = getParametrosFacadeLocal().buscarDominioCodigo("RUTA_DOCUMENTO", "1").getDescripcion();
            //String ruta = "C:\\anprFTP\\";
            cruceDocumentoTO.setUsrSub(usurioAutenticado().getIdentificador());
            cruceDocumentoTO.setFecha(new Date());
            cruceDocumentoTO.setIdCruce(idSeleccionado);
            if (verGrid) { //caso documetno normal 1 archivo
                if (archivo1 != null && archivo1.getFileName() != null && !archivo1.getFileName().equals("") && inputArchivo1.length > 0) {
                    System.out.println("Archivo " + archivo1.getFileName() + " subido por " + usurioAutenticado().getIdentificador());
                    String nombreArchivo = archivo1.getFileName();
                    if (Util.copyFile(ruta, nombreArchivo, inputArchivo1)) {
                        cruceDocumentoTO.setUsrSub(usurioAutenticado().getIdentificador());
                        cruceDocumentoTO.setFecha(new Date());
                        cruceDocumentoTO.setArchivo(ruta + archivo1.getFileName());
                        cruceDocumentoTO.setNombreArchivo(archivo1.getFileName());
                        getLocalizacionFacadeLocal().guardarDocumento(cruceDocumentoTO);
                        System.out.println("Carga Completa");
                    } else {
                        System.out.println("Archivo " + archivo1.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo1 = null;
                    cruceDocumentoTO = new CruceDocumentoTO();

                } else {
                    System.out.println("invalido");
                    mostrarMensaje("Debe seleccionar un archivo.");
                    FacesContext.getCurrentInstance().addMessage("form1:inpGen", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un archivo.", "Debe seleccionar un archivo."));
                    return;
                }
            } else if (verGrMediciones || verGrEstPer || verGrEstPro) //mediciones 2 archivos
            {
                if (archivo1 != null && archivo1.getFileName() != null && !archivo1.getFileName().equals("") && inputArchivo1.length > 0) {
                    if (Util.copyFile(ruta, archivo1.getFileName(), inputArchivo1)) {
                        cruceDocumentoTO.setArchivo(ruta + archivo1.getFileName());
                        cruceDocumentoTO.setNombreArchivo(archivo1.getFileName());
                        System.out.println("Carga Completa " + archivo1.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo1.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo1 = null;
                }
                if (archivo2 != null && archivo2.getFileName() != null && !archivo2.getFileName().equals("") && inputArchivo2.length > 0) {
                    if (Util.copyFile(ruta, archivo2.getFileName(), inputArchivo2)) {
                        cruceDocumentoTO.setArchivo2(ruta + archivo2.getFileName());
                        cruceDocumentoTO.setNombre_doc2(archivo2.getFileName());
                        System.out.println("Carga Completa " + archivo2.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo2.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo2 = null;
                }
                getLocalizacionFacadeLocal().guardarDocumento(cruceDocumentoTO);
                cruceDocumentoTO = new CruceDocumentoTO();
            } else if (verGrJustificacion || verGrEstSinFina) { // 3 archivos
                if (archivo1 != null && archivo1.getFileName() != null && !archivo1.getFileName().equals("") && inputArchivo1.length > 0) {
                    if (Util.copyFile(ruta, archivo1.getFileName(), inputArchivo1)) {
                        cruceDocumentoTO.setArchivo(ruta + archivo1.getFileName());
                        cruceDocumentoTO.setNombreArchivo(archivo1.getFileName());
                        System.out.println("Carga Completa " + archivo1.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo1.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo1 = null;
                }
                if (archivo2 != null && archivo2.getFileName() != null && !archivo2.getFileName().equals("") && inputArchivo2.length > 0) {
                    if (Util.copyFile(ruta, archivo2.getFileName(), inputArchivo2)) {
                        cruceDocumentoTO.setArchivo2(ruta + archivo2.getFileName());
                        cruceDocumentoTO.setNombre_doc2(archivo2.getFileName());
                        System.out.println("Carga Completa " + archivo2.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo2.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo2 = null;
                }
                if (archivo3 != null && archivo3.getFileName() != null && !archivo3.getFileName().equals("") && inputArchivo3.length > 0) {
                    if (Util.copyFile(ruta, archivo3.getFileName(), inputArchivo3)) {
                        cruceDocumentoTO.setArchivo3(ruta + archivo3.getFileName());
                        cruceDocumentoTO.setNombre_doc3(archivo3.getFileName());
                        System.out.println("Carga Completa " + archivo3.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo3.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo3 = null;
                }
                getLocalizacionFacadeLocal().guardarDocumento(cruceDocumentoTO);
                cruceDocumentoTO = new CruceDocumentoTO();
            } else if (verGrProyectos) { // 5 archivos
                if (archivo1 != null && archivo1.getFileName() != null && !archivo1.getFileName().equals("") && inputArchivo1.length > 0) {
                    if (Util.copyFile(ruta, archivo1.getFileName(), inputArchivo1)) {
                        cruceDocumentoTO.setArchivo(ruta + archivo1.getFileName());
                        cruceDocumentoTO.setNombreArchivo(archivo1.getFileName());
                        System.out.println("Carga Completa " + archivo1.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo1.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo1 = null;
                }
                if (archivo2 != null && archivo2.getFileName() != null && !archivo2.getFileName().equals("") && inputArchivo2.length > 0) {
                    if (Util.copyFile(ruta, archivo2.getFileName(), inputArchivo2)) {
                        cruceDocumentoTO.setArchivo2(ruta + archivo2.getFileName());
                        cruceDocumentoTO.setNombre_doc2(archivo2.getFileName());
                        System.out.println("Carga Completa " + archivo2.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo2.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo2 = null;
                }
                if (archivo3 != null && archivo3.getFileName() != null && !archivo3.getFileName().equals("") && inputArchivo3.length > 0) {
                    if (Util.copyFile(ruta, archivo3.getFileName(), inputArchivo3)) {
                        cruceDocumentoTO.setArchivo3(ruta + archivo3.getFileName());
                        cruceDocumentoTO.setNombre_doc3(archivo3.getFileName());
                        System.out.println("Carga Completa " + archivo3.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo3.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo3 = null;
                }
                if (archivo4 != null && archivo4.getFileName() != null && !archivo4.getFileName().equals("") && inputArchivo4.length > 0) {
                    if (Util.copyFile(ruta, archivo4.getFileName(), inputArchivo4)) {
                        cruceDocumentoTO.setArchivo4(ruta + archivo4.getFileName());
                        cruceDocumentoTO.setNombre_doc4(archivo4.getFileName());
                        System.out.println("Carga Completa " + archivo4.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo4.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo4 = null;
                }
                if (archivo5 != null && archivo5.getFileName() != null && !archivo5.getFileName().equals("") && inputArchivo5.length > 0) {
                    if (Util.copyFile(ruta, archivo5.getFileName(), inputArchivo5)) {
                        cruceDocumentoTO.setArchivo5(ruta + archivo5.getFileName());
                        cruceDocumentoTO.setNombre_doc5(archivo5.getFileName());
                        System.out.println("Carga Completa " + archivo5.getFileName());
                    } else {
                        System.out.println("Archivo " + archivo5.getFileName() + " no pudo ser subido por " + usurioAutenticado().getIdentificador());
                    }
                    archivo5 = null;
                }
                getLocalizacionFacadeLocal().guardarDocumento(cruceDocumentoTO);
                cruceDocumentoTO = new CruceDocumentoTO();
                FacesMessage msg = new FacesMessage("Subido", "El documento ha sido cargado con exito.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception ex) {
            Logger.getLogger(DocumentoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargaDocumentoPorId();
        cerrarCrear();
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public List<CruceTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<CruceTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public BigDecimal getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(BigDecimal idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

    public CruceTO getCruceTO() {
        return cruceTO;
    }

    public void setCruceTO(CruceTO cruceTO) {
        this.cruceTO = cruceTO;
    }

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public boolean isBloqIdCruce() {
        return bloqIdCruce;
    }

    public void setBloqIdCruce(boolean bloqIdCruce) {
        this.bloqIdCruce = bloqIdCruce;
    }

    public List<CruceDocumentoTO> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CruceDocumentoTO> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public CruceDocumentoTO getCruceDocumentoTO() {
        return cruceDocumentoTO;
    }

    public void setCruceDocumentoTO(CruceDocumentoTO cruceDocumentoTO) {
        this.cruceDocumentoTO = cruceDocumentoTO;
    }

    public List<CodigoTO> getListaTipoDcto() {
        return listaTipoDcto;
    }

    public void setListaTipoDcto(List<CodigoTO> listaTipoDcto) {
        this.listaTipoDcto = listaTipoDcto;
    }

    public byte[] getInputArchivo() {
        return inputArchivo;
    }

    public void setInputArchivo(byte[] inputArchivo) {
        this.inputArchivo = inputArchivo;
    }

    public boolean isVerPopupEditar() {
        return verPopupEditar;
    }

    public void setVerPopupEditar(boolean verPopupEditar) {
        this.verPopupEditar = verPopupEditar;
    }

    public boolean isVerGrProyectos() {
        return verGrProyectos;
    }

    public void setVerGrProyectos(boolean verGrProyectos) {
        this.verGrProyectos = verGrProyectos;
    }

    public boolean isVerGrJustificacion() {
        return verGrJustificacion;
    }

    public void setVerGrJustificacion(boolean verGrJustificacion) {
        this.verGrJustificacion = verGrJustificacion;
    }

    public boolean isVerGrMediciones() {
        return verGrMediciones;
    }

    public void setVerGrMediciones(boolean verGrMediciones) {
        this.verGrMediciones = verGrMediciones;
    }

    public boolean isVerGrid() {
        return verGrid;
    }

    public void setVerGrid(boolean verGrid) {
        this.verGrid = verGrid;
    }

    public boolean isVerPopupDetalle() {
        return verPopupDetalle;
    }

    public void setVerPopupDetalle(boolean verPopupDetalle) {
        this.verPopupDetalle = verPopupDetalle;
    }

    public CruceDocumentoTO getDetCruceDocumentoTO() {
        return detCruceDocumentoTO;
    }

    public void setDetCruceDocumentoTO(CruceDocumentoTO detCruceDocumentoTO) {
        this.detCruceDocumentoTO = detCruceDocumentoTO;
    }

    public String getNombreArchivo1() {
        return nombreArchivo1;
    }

    public void setNombreArchivo1(String nombreArchivo1) {
        this.nombreArchivo1 = nombreArchivo1;
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

    public boolean isVerGrEstPer() {
        return verGrEstPer;
    }

    public void setVerGrEstPer(boolean verGrEstPer) {
        this.verGrEstPer = verGrEstPer;
    }

    public boolean isVerGrEstPro() {
        return verGrEstPro;
    }

    public void setVerGrEstPro(boolean verGrEstPro) {
        this.verGrEstPro = verGrEstPro;
    }

    public boolean isVerGrEstSinFina() {
        return verGrEstSinFina;
    }

    public void setVerGrEstSinFina(boolean verGrEstSinFina) {
        this.verGrEstSinFina = verGrEstSinFina;
    }
    
    
    
}
