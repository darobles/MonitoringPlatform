package cl.auter.backing;

import static cl.auter.backing.CruceBacking.BEAN_NAME;
import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.CruceDocumentoTO;
import cl.auter.patron.to.CruceTO;
import cl.auter.patron.to.InstalacionTO;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named(value = BEAN_NAME)
@ViewScoped
public class CruceBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "cruceBacking";
    private String codigoPag = "236";
    private String idComuna;
    private String comboHabilitada;
    private List<CruceTO> listaCruce = new ArrayList<>();
    BigDecimal idSeleccionado;
    private CruceTO cruceTO = new CruceTO();
    private boolean verPopupCruce = false;
    private boolean ppr = false;
    private UploadedFile imagen;
    private byte[] inputImagen;
    String ruta = "";
    boolean eliminar = false;
    boolean permisoPPR = false;
    boolean verCruce = false;
    boolean verInfoId = false;
    boolean verMensaje2 = false;
    boolean verMensaje3 = false;
    boolean verMensaje4 = false;
    private double latMap = -33.451959;
    private double longMap = -70.630915;
    private MapModel marcadores;
    private String centro = latMap + "," + longMap;
    private String titulo = "Nuevo Cruce";
    private boolean editar = false;
    
    @PostConstruct
    public void carga() {
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        imagen = null;
        // validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))) {
            this.comboHabilitada = "false";
        } else {
            this.idComuna = String.valueOf(usurioAutenticado().getIdComuna());
            this.comboHabilitada = "true";
            //cargarCrucePorIdComuna();
        }
        marcadores = new DefaultMapModel();
        try {
            permisoPPR = getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("263");
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String obtenerNombreArchivo(String rutaImagen) {
        if (ruta.equals("")) {
            try {
                ruta = getParametrosFacadeLocal().buscarDominioCodigo("RUTA_MAPA_CRUCE", "1").getCodigo2();
            } catch (Exception ex) {
                Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (eliminar == true) {
            return "";
        }
        if (rutaImagen == null || rutaImagen.equals("")) {
            return "Sin imagen";
        }
        return rutaImagen.replace(ruta, "");
    }

    public void cargarCrucePorIdComuna() {
        this.listaCruce.clear();
        try {
            if (this.idComuna.equals("999")) {
                this.listaCruce = getLocalizacionFacadeLocal().listaCruceTodos();
            } else {
                this.listaCruce = getLocalizacionFacadeLocal().buscarCrucePorIdComuna(new BigDecimal(this.idComuna));
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioComuna(ValueChangeEvent e) {
        this.idComuna = (String) e.getNewValue();
        cargarCrucePorIdComuna();
    }

    public void cambioComunaCruce(ValueChangeEvent e) {
        if (e.getNewValue() != null) {
            BigDecimal id = new BigDecimal(e.getNewValue().toString());
            cruceTO.setIdComuna(id);
        }
    }

    public void ingresarCruce() {
        titulo ="Nuevo Cruce";
        cruceTO = new CruceTO();
        verInfoId = false;
        editar = true;
        verPopupCruce = true;
    }

    public void mostrarInfoCruce(BigDecimal idcruce) {
        cruceTO = new CruceTO();
        
        try {
            cruceTO = getLocalizacionFacadeLocal().buscarCrucePorId(idcruce);
            marcadores.getMarkers().clear();
            if(cruceTO.getLatitud() != null && !cruceTO.getLatitud().equals("") && cruceTO.getLongitud() != null && !cruceTO.getLongitud().equals(""))
            {
                LatLng coords = new LatLng(Double.parseDouble(cruceTO.getLatitud()), Double.parseDouble(cruceTO.getLongitud()));
                marcadores.addOverlay(new Marker(coords, cruceTO.getUbicacion()));
                centro = cruceTO.getLatitud() + "," + cruceTO.getLongitud();
            }
            titulo = "Cruce - " + cruceTO.getUbicacion();
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        verInfoId = true;
        verCruce = true;
        verPopupCruce = true;
    }

    public void editarCruce(BigDecimal idcruce) {
        cruceTO = new CruceTO();
        try {
            
            cruceTO = getLocalizacionFacadeLocal().buscarCrucePorId(idcruce);
            titulo ="Editar Cruce " + cruceTO.getUbicacion();
            marcadores.getMarkers().clear();
            if(cruceTO.getLatitud() != null && !cruceTO.getLatitud().equals("") && cruceTO.getLongitud() != null && !cruceTO.getLongitud().equals(""))
            {
                LatLng coords = new LatLng(Double.parseDouble(cruceTO.getLatitud()), Double.parseDouble(cruceTO.getLongitud()));
                marcadores.addOverlay(new Marker(coords, cruceTO.getUbicacion()));
                centro = cruceTO.getLatitud() + "," + cruceTO.getLongitud();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        editar = true;
        verInfoId = true;
        verPopupCruce = true;
    }

    public void eliminarCruce(BigDecimal idcruce) {
        try {
            cruceTO = new CruceTO();
            cruceTO = getLocalizacionFacadeLocal().buscarCrucePorId(idcruce);
            if (cruceTO.getIdcruce() != null) {
                List<CruceDocumentoTO> docsAsociados = getLocalizacionFacadeLocal().buscarDocumentoPorIdCruce(cruceTO.getIdcruce());
                for (CruceDocumentoTO doc : docsAsociados) {
                    getLocalizacionFacadeLocal().eliminarDocumento(doc);
                }
                List<InstalacionTO> instalaciones = getLocalizacionFacadeLocal().buscarInstalacionPorIdCruce(cruceTO.getIdcruce());
                for (InstalacionTO insta : instalaciones) {
                    getLocalizacionFacadeLocal().eliminarInstalacion(insta);
                }
            }
            getLocalizacionFacadeLocal().eliminarCruce(cruceTO);
            addMessageInfo("Cruce eliminado exitosamente.");
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
            addMessageError("No se puede eliminar el cruce.");
        } finally {
            cargarCrucePorIdComuna();
        }
    }
    
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centro = center.getLat() + "," + center.getLng();
            cruceTO.setLatitud(String.valueOf(center.getLat())); 
            cruceTO.setLongitud(String.valueOf(center.getLng())); 
            marcadores.getMarkers().clear();
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                marcadores.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
            for(Marker premarker : marcadores.getMarkers()) {
                premarker.setDraggable(true);
            }
        }
    }
    
    public void onMarkerDrag(MarkerDragEvent event){
        Marker  marker = event.getMarker();
        cruceTO.setLatitud(String.valueOf(marker.getLatlng().getLat())); 
        cruceTO.setLongitud(String.valueOf(marker.getLatlng().getLng()));
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        try {
            imagen = uploadedFile;
            inputImagen = IOUtils.toByteArray(imagen.getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarCruce() {
        if (validarDatosCruce()) {
            if (editar) {
                try {
                    if (cruceTO.getIdDispositivo() != null && !cruceTO.getIdDispositivo().equals("")) {
                        for (CruceTO cr : getLocalizacionFacadeLocal().buscarCrucesPorDimac(new BigDecimal(cruceTO.getIdDispositivo()))) {
                            if (!cr.getIdcruce().equals(cruceTO.getIdcruce())) {
                                cr.setPpr(cruceTO.isPpr());
                                getLocalizacionFacadeLocal().editarCruce(cr);
                            }
                        }
                    }
                    try {
                        if (imagen != null && imagen.getFileName() != null && !imagen.getFileName().equals("")) {
                            if (ruta.equals("")) {
                                ruta = getParametrosFacadeLocal().buscarDominioCodigo("RUTA_MAPA_CRUCE", "1").getCodigo2();
                            }
                            String nombreArchivo = "Cruce-" + cruceTO.getIdcruce() + imagen.getFileName().substring(imagen.getFileName().lastIndexOf('.'));
                           
                            if (imagen != null && imagen.getFileName() != null && !imagen.getFileName().equals("") && inputImagen.length > 0) {
                                if (Util.copyFile(ruta, nombreArchivo, inputImagen)) {
                                    System.out.println("Archivo " + nombreArchivo + " subido con exito");
                                     cruceTO.setImagen(ruta + nombreArchivo);
                                } else {
                                    System.out.println("Se produjo un error al subir el archivo " + nombreArchivo);
                                }
                            }
                        }
                        if (eliminar == true) {
                            if (eliminarArchivo(cruceTO.getImagen())) {
                                cruceTO.setImagen("");
                            }

                        }
                        getLocalizacionFacadeLocal().editarCruce(cruceTO);

                    } catch (IOException ex) {
                        Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    

                } catch (Exception ex) {
                    Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagen = null;
                eliminar = false;
                addMessageInfo("Cruce modificado exitosamente.");
                verPopupCruce = false;
                cruceTO = new CruceTO();
                editar = false;
            } else {
                try {
                    if (getLocalizacionFacadeLocal().existeCruce(cruceTO.getIdcruce())) {
                        addMessageError("El id de cruce ingresado ya existe.");
                    } else {
                        getLocalizacionFacadeLocal().guardarCruce(cruceTO);
                        addMessageInfo("Cruce ingresado exitosamente.");
                        verPopupCruce = false;
                        cruceTO = new CruceTO();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cargarCrucePorIdComuna();

        }
        //return "cruce.xhtml?faces-redirect=true";
    }

    public String getImage(CruceTO cruce) {
        if (ruta.equals("")) {
            try {
                ruta = getParametrosFacadeLocal().buscarDominioCodigo("RUTA_MAPA_CRUCE", "1").getCodigo2();
            } catch (Exception ex) {
                Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String url = "../../imagenes/PPR/";
        String urlFinal = url + "img_no_disponible.jpg";
        if (cruce.getImagen() != null && !cruce.getImagen().equals("")) {
            url = "http://imagenesmunicipal.auter.cl/imagenes/cruces/";
            urlFinal = url + cruce.getImagen().replace(ruta, "");
        }
        System.out.println("Ruta Final: " + urlFinal);
        return urlFinal;
    }

    public boolean validarDatosCruce() {
        UIViewRoot uiv = FacesContext.getCurrentInstance().getViewRoot();
        UIInput idcalle1 = (UIInput) uiv.findComponent("form1:idCalle1");
        UIInput idcalle2 = (UIInput) uiv.findComponent("form1:idCalle2");
        UIInput idcruce = (UIInput) uiv.findComponent("form1:idCruce");
        UIInput idcomuna = (UIInput) uiv.findComponent("form1:idComunaPopUp");
        UIInput iddispositivo = (UIInput) uiv.findComponent("form1:idDispositivo");
        verMensaje4 = !idcomuna.isValid();
        if (cruceTO.getCalle1() == null || cruceTO.getCalle1().equals("")) {
            FacesContext.getCurrentInstance().addMessage("form1:idCalle1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar calle 1."));
            idcalle1.setValid(false);
        } else {
            idcalle1.setValid(true);
        }
        if (cruceTO.getCalle2().equals("")) {
            FacesContext.getCurrentInstance().addMessage("form1:idCalle2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar calle 2."));
            idcalle2.setValid(false);
        } else {
            idcalle2.setValid(true);
        }
        verMensaje2 = !idcalle1.isValid() || !idcalle1.isValid();
        if (cruceTO.getIdDispositivo().equals("")) {
            cruceTO.setIdDispositivo(null);
            iddispositivo.setValid(true);
        } else {
            try {
                if (!getLocalizacionFacadeLocal().existeDispositivo(new BigDecimal(cruceTO.getIdDispositivo()))) {
                    FacesContext.getCurrentInstance().addMessage("form1:idDispositivo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El Id de dispositivo Ingresado no esta Registrado."));
                    iddispositivo.setValid(false);
                } else {
                    iddispositivo.setValid(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        verMensaje3 = !iddispositivo.isValid();
        return !(!idcruce.isValid() || !idcalle1.isValid() || !idcomuna.isValid() || !idcalle1.isValid() || !idcalle2.isValid() || !iddispositivo.isValid());
    }

    public void eliminarArch() {
        eliminar = true;
    }

    public void cerrar() {
        verCruce = false;
        verPopupCruce = false;
        eliminar = false;
    }

    public void addMessageInfo(String summary) {
        FacesMessage message = new FacesMessage(summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessageError(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:idCruce", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
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

    public List<CruceTO> getListaCruce() {
        return listaCruce;
    }

    public void setListaCruce(List<CruceTO> listaCruce) {
        this.listaCruce = listaCruce;
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

   
    
    public boolean isPpr() {
        return ppr;
    }

    public void setPpr(boolean ppr) {
        this.ppr = ppr;
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isVerPopupCruce() {
        return verPopupCruce;
    }

    public void setVerPopupCruce(boolean verPopupCruce) {
        this.verPopupCruce = verPopupCruce;
    }

    public boolean isPermisoPPR() {
        return permisoPPR;
    }

    public void setPermisoPPR(boolean permisoPPR) {
        this.permisoPPR = permisoPPR;
    }

    public boolean isVerCruce() {
        return verCruce;
    }

    public void setVerCruce(boolean verCruce) {
        this.verCruce = verCruce;
    }

    public boolean isVerInfoId() {
        return verInfoId;
    }

    public void setVerInfoId(boolean verInfoId) {
        this.verInfoId = verInfoId;
    }
    
    
    
    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public boolean isVerMensaje3() {
        return verMensaje3;
    }

    public void setVerMensaje3(boolean verMensaje3) {
        this.verMensaje3 = verMensaje3;
    }

    public boolean isVerMensaje4() {
        return verMensaje4;
    }

    public void setVerMensaje4(boolean verMensaje4) {
        this.verMensaje4 = verMensaje4;
    }
   
    public double getLatMap() {
        return latMap;
    }

    public void setLatMap(double latMap) {
        this.latMap = latMap;
    }

    public double getLongMap() {
        return longMap;
    }

    public void setLongMap(double longMap) {
        this.longMap = longMap;
    }

    public MapModel getMarcadores() {
        return marcadores;
    }

    public void setMarcadores(MapModel marcadores) {
        this.marcadores = marcadores;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
    
    
}
