/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.UserTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Marco
 */
@ManagedBean(name = CambioClaveBacking.BEAN_NAME)
@ViewScoped
public class CambioClaveBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "cambioClaveBacking";

    private UserTO usuarioTO = new UserTO();
    private String claveActual = "";
    private String nuevaClave = "";
    private String nuevaClave2 = "";
    private List<CodigoTO> listaTipoEmpresa = new ArrayList();
    private List<CodigoTO> listaActivo = new ArrayList();

    @PostConstruct
    public void carga() {
        try {
            String identificador = usurioAutenticado().getIdentificador();
            usuarioTO = new UserTO();
            usuarioTO = getLocalizacionFacadeLocal().buscarUsuarioPorId(identificador);
            listaTipoEmpresa.addAll(getParametrosFacadeLocal().listaPorDominio("TIPO_EMPRESA"));
        } catch (Exception ex) {
            Logger.getLogger(CambioClaveBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String obtenerComuna(){
        try {
            return getLocalizacionFacadeLocal().buscaComunaPorId(usuarioTO.getIdComuna()).getNombre();
        } catch (Exception ex) {
            Logger.getLogger(CambioClaveBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String obtenerTipoEmpresa(){
        for(CodigoTO cod: listaTipoEmpresa){
            if(cod.getIdCodigo().equals(usuarioTO.getTipo()))
            {
                return cod.getDescripcion();
            }
        }
        return "";
    }
    
    public void aceptar() {
        if (validarCampos()) {
            try {
                usuarioTO.setPassword(Util.getMD5(nuevaClave));
                getLocalizacionFacadeLocal().editarUsuario(usuarioTO);
                FacesContext.getCurrentInstance().addMessage("form1:messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Clave modificada exitosamente."));
                claveActual = "";
                nuevaClave = "";
                nuevaClave2 = "";
            } catch (Exception ex) {
                Logger.getLogger(CambioClaveBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validarCampos() {
        UIViewRoot uiv = FacesContext.getCurrentInstance().getViewRoot();
        UIInput inpClaveActual = (UIInput) uiv.findComponent("form1:idClaveActual");
        UIInput inpNuevaClave = (UIInput) uiv.findComponent("form1:idClaveNueva");
        UIInput inpNuevaClave2 = (UIInput) uiv.findComponent("form1:idClaveNueva2");
        if (claveActual == null || claveActual.equals("")) {
            FacesContext.getCurrentInstance().addMessage("form1:idClaveActual", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar Clave actual."));
            inpClaveActual.setValid(false);
        }
        else{
            inpClaveActual.setValid(true);
        }
        if (nuevaClave == null || nuevaClave.equals("")) {
            FacesContext.getCurrentInstance().addMessage("form1:idClaveNueva", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar una clave."));
            inpNuevaClave.setValid(false);
        }
        else{
            if (nuevaClave.length() < 2) {
                FacesContext.getCurrentInstance().addMessage("form1:idClaveNueva", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La nueva clave debe tener una longitud minima de tres caracteres."));
                inpNuevaClave.setValid(false);
            }
            else{
                inpNuevaClave.setValid(true);
            }
        }
        if (nuevaClave2 == null || nuevaClave2.equals("")) {
            FacesContext.getCurrentInstance().addMessage("form1:idClaveNueva2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar una clave."));
            inpNuevaClave2.setValid(false);
        }
        else{
            if (!nuevaClave.equals(nuevaClave2)) {
                FacesContext.getCurrentInstance().addMessage("form1:idClaveNueva2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La nueva clave no coincide."));
                inpNuevaClave2.setValid(false);
            }
            else{
                inpNuevaClave2.setValid(true);
            }
         }
        
        if(inpNuevaClave.isValid() && inpNuevaClave2.isValid() && inpClaveActual.isValid())
        {
            if (!usuarioTO.getPassword().equals(Util.getMD5(claveActual))) {
                FacesContext.getCurrentInstance().addMessage("form1:idClaveActual", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La clave actual no coincide."));
                inpClaveActual.setValid(false);
                return false;
            }
            return true;
        }            
        else
        {
            return false;
        }
            
    }

    public void addMessage(String summary) {
        //PrimeFaces.current().ajax().update("form1:pnlPass");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getNuevaClave2() {
        return nuevaClave2;
    }

    public void setNuevaClave2(String nuevaClave2) {
        this.nuevaClave2 = nuevaClave2;
    }

    public UserTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UserTO usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public List<CodigoTO> getListaTipoEmpresa() {
        return listaTipoEmpresa;
    }

    public void setListaTipoEmpresa(List<CodigoTO> listaTipoEmpresa) {
        this.listaTipoEmpresa = listaTipoEmpresa;
    }

    public List<CodigoTO> getListaActivo() {
        return listaActivo;
    }

    public void setListaActivo(List<CodigoTO> listaActivo) {
        this.listaActivo = listaActivo;
    }
    
    

}
