/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.InstalacionTO;
import cl.auter.patron.to.VistaInstalacionTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Marco
 */
@ManagedBean (name = InstalacionBacking.BEAN_NAME )
@ViewScoped
public class InstalacionBacking extends FiltroBacking implements Serializable {
    
    public static final String BEAN_NAME="instalacionBacking";
    private final String codigoPag = "238";
    private String comboHabilitada;
    private BigDecimal idComuna;
    private List<VistaInstalacionTO> listaPuntos=new ArrayList();
    private VistaInstalacionTO idCruce;
    private List<InstalacionTO> listaInstalacion = new ArrayList(); 
    private InstalacionTO instalacionTO = new InstalacionTO();
    private List<CodigoTO> listaTipoInstalacion = new ArrayList();
    private CodigoTO[] tipoInstalacion;
    private boolean verPopup = false;
    BigDecimal idSeleccionado;
    boolean validForm = false;
    
    @PostConstruct
    public void carga(){
        tipoInstalacion = obtenerTipoInstalacion();
        cargaEstadosOperacionales();
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))){
            this.comboHabilitada="false";
        }else{
           this.idComuna= usurioAutenticado().getIdComuna();
           this.comboHabilitada="true";
           cargaInstalacionPorComuna();
        }
        cargaTipoInstalacion();
    }
    public void cargaTipoInstalacion(){
        listaTipoInstalacion.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setCodigo1("-1");
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaTipoInstalacion.add(codigoTO);
        try{
            for(CodigoTO cod: obtenerTipoInstalacion())
            {
                listaTipoInstalacion.add(cod);
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargaInstalacionPorComuna(){
        this.listaPuntos.clear();     
        try{
            this.listaPuntos=getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(idComuna,tipoInstalacion);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscar() {  
        if(validForm)
        {
            verPopup = false;
            listaInstalacion.clear(); 
            cargaInstalacionPorId();
        }
    }
    
    public boolean validarFormulario(){
        UIViewRoot uiv = FacesContext.getCurrentInstance().getViewRoot();
        UIInput inpComuna = (UIInput) uiv.findComponent("config-form-tabs:config-form:idComuna");
        UIInput inpCruce = (UIInput) uiv.findComponent("config-form-tabs:config-form:idCompleto");
        if((idComuna == null || idComuna.equals("-1")) && idCruce == null){   
            inpComuna.setValid(false);
            inpCruce.setValid(false);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("config-form-tabs:config-form:idCompleto", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar una comuna válida","Debe seleccionar una comuna válida"));           
            validForm = false;
            return false;
        }else{
            inpCruce.setValid(true);
            inpComuna.setValid(true);
            PrimeFaces.current().executeScript("$('.layout-config-button').click()");
            PrimeFaces.current().ajax().update("form1:messages"); 
            PrimeFaces.current().ajax().update("config-form-tabs"); 
            validForm = true;
            return true;
            
        }
    }
    
    public void ingresarInstalacion(){
        instalacionTO = new InstalacionTO();
        instalacionTO.setId(BigDecimal.ZERO);
        verPopup = true;
    }
    
    public void actualizarInstalacion(){
        if(validarDatosInstalacion()){
            try{
                if(instalacionTO.getId() == null || instalacionTO.getId().equals(new BigDecimal("0"))){
                    getLocalizacionFacadeLocal().guardarInstalacion(instalacionTO);
                    addMessage("Instalación ingresado exitosamente.");
                }else{
                    getLocalizacionFacadeLocal().editarInstalacion(instalacionTO);
                    addMessage("Instalación modfificada exitosamente.");
                }
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            instalacionTO = new InstalacionTO();
            verPopup = false;
            cargaInstalacionPorId();            
        }        
    }
    
    public boolean validarDatosInstalacion(){
        if(instalacionTO.getIdCruce().intValue() < 1){
            mostrarMensaje("Debe Ingresar Id Cruce.");
            return false;
        }else{
            try{
                if(!getLocalizacionFacadeLocal().existeCruce(instalacionTO.getIdCruce())){
                    mostrarMensaje("El cruce ingresado no se encuentra registrado en el sistema");
                    return false;
                }
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(instalacionTO.getTipo().equals("-1")){
            mostrarMensaje("Debe Ingresar Tipo.");
            return false;
        }
        return true;
    }
    
    public void mostrarMensaje(String mensaje){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:id", new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje, mensaje));
    }
    
    public void cambioValorAutoComplete(SelectEvent seleccionado){
         this.idCruce =(VistaInstalacionTO)seleccionado.getObject();
    }
    
    public void cambioComuna(ValueChangeEvent e) {
        idComuna= (BigDecimal) e.getNewValue();
        cargaInstalacionPorComuna();
    } 
    
    public List<VistaInstalacionTO> filtroCruce(String calle) {
        List<VistaInstalacionTO> listaCruce = new ArrayList();
        listaCruce.clear();
        //if (calle !=null && !calle.equals("") && calle.length()>2){
        if (calle !=null && !calle.equals("")){
            for (VistaInstalacionTO vistaInstalacionTO :listaPuntos ){
                if (vistaInstalacionTO.getUbicacion().toUpperCase().contains(calle.toUpperCase()) ){
                    listaCruce.add(vistaInstalacionTO);
                }
            }
        }
        return listaCruce;
    }
    public void cargaInstalacionPorId(){
        listaInstalacion.clear();
        try{
            if(idCruce == null || idCruce.getIdCruce().equals(new BigDecimal("-1")))
            {
                listaInstalacion = getLocalizacionFacadeLocal().buscarInstalacionPorIdComuna(idComuna);
            }
            else
                listaInstalacion = getLocalizacionFacadeLocal().buscarInstalacionPorIdCruce(idCruce.getIdCruce());
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(InstalacionTO instalacion : listaInstalacion){
            for(CodigoTO codigo : listaTipoInstalacion){
                if(instalacion.getTipo().equals(codigo.getCodigo1())){
                    instalacion.setTipoDescripcion(codigo.getDescripcion());
                    break;
                }
            }
        }
    }
    public void eliminarInstalacion(){
        try {
            instalacionTO = new InstalacionTO();
            instalacionTO = getLocalizacionFacadeLocal().buscarInstalacionPorId(idSeleccionado);         
            getLocalizacionFacadeLocal().eliminarInstalacion(instalacionTO);
            addMessage("Instalación eliminada exitosamente.");
        } catch (Exception ex) {
            Logger.getLogger(InstalacionBacking.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No se puede eliminar la instalación.");            
        }finally{
            cargaInstalacionPorId();
        }        
    }
    
    public void addMessage(String summary) {
      PrimeFaces.current().ajax().update("form1:grid");
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void editarInstalacion( ) {
        instalacionTO = new InstalacionTO();
        try{
            instalacionTO = getLocalizacionFacadeLocal().buscarInstalacionPorId(idSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        verPopup= true;
        cargaInstalacionPorId();
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public List<VistaInstalacionTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<VistaInstalacionTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public VistaInstalacionTO getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(VistaInstalacionTO idCruce) {
        this.idCruce = idCruce;
    }   

    public List<InstalacionTO> getListaInstalacion() {
        return listaInstalacion;
    }

    public void setListaInstalacion(List<InstalacionTO> listaInstalacion) {
        this.listaInstalacion = listaInstalacion;
    }

    public BigDecimal getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(BigDecimal idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }   

    public InstalacionTO getInstalacionTO() {
        return instalacionTO;
    }

    public void setInstalacionTO(InstalacionTO instalacionTO) {
        this.instalacionTO = instalacionTO;
    }

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public List<CodigoTO> getListaTipoInstalacion() {
        return listaTipoInstalacion;
    }

    public void setListaTipoInstalacion(List<CodigoTO> listaTipoInstalacion) {
        this.listaTipoInstalacion = listaTipoInstalacion;
    }    
    
    
}
