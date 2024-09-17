package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.RolUsuarioTO;
import cl.auter.patron.to.UserTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.PrimeFaces;


@ManagedBean (name = UsuarioBacking.BEAN_NAME )
@ViewScoped
public class UsuarioBacking extends FiltroBacking implements Serializable {
    public static final String BEAN_NAME="usuarioBacking";
    public  String codigoPag = "222";
    private static final long serialVersionUID = 1L;
    
    
    private List<UserTO> listaUsuarios = new ArrayList();
    private List<CodigoTO> listaTipoEmpresa = new ArrayList<CodigoTO>();
    private List<CodigoTO> listaActivo = new ArrayList<CodigoTO>();
    private List<CodigoTO> listaMenu = new ArrayList<CodigoTO>();
    private String idComuna = "-1";
    private String comboHabilitada;
    private String claveAnterior;
    private UserTO usuarioTO = new UserTO();
    String idSeleccionado;
    private boolean verPopup = false;
    private boolean bloqIdUsuario = false;
    private boolean bloqCoordenadas = false;
    private int activeIndex;

    
     @PostConstruct
    public void carga(){
        activeIndex = 0;
        BigDecimal tipoRol = usurioAutenticado().getTipo();
         validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))){
           this.comboHabilitada="false";
           cargaUsuario();
        }else{
           this.idComuna= String.valueOf(usurioAutenticado().getIdComuna());
           this.comboHabilitada="true";
           cargaUsuarioPorComuna();          
        }
        cargaTipoEmpresa();
        cargaActivo();
        cargaMenu();
    }
    public void cargaMenu(){
        listaMenu.clear();
        try{
        listaMenu = getParametrosFacadeLocal().listaPorDominio("MENU_GEOREFERENCIA");
        listaMenu.addAll(getParametrosFacadeLocal().listaPorDominio("ROL_ACCESO"));
        for(CodigoTO codigoTO:listaMenu){
            codigoTO.setSelecionado(false);
            if(!codigoTO.getCodigo2().equals("00")){
                codigoTO.setEstilo("padding-left: 5em");
            }
        }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void seleccion(BigDecimal id) {
        for(CodigoTO codigoTO:listaMenu){
            if(codigoTO.getIdCodigo().equals(id)){
                if(codigoTO.getCodigo2().equals("00")){
                    if(codigoTO.isSelecionado()){
                        cargarHijos(codigoTO.getCodigo1(),true);
                    }else if(!codigoTO.isSelecionado()){
                        cargarHijos(codigoTO.getCodigo1(),false);
                    }
                }else{
                    cargaPadre(codigoTO.getCodigo1());
                }
            }            
        }
    }
    public void cargaPadre(String codigo){
        for(CodigoTO codigoTO:listaMenu){
           if(codigoTO.getCodigo1().equals(codigo) && codigoTO.getCodigo2().equals("00")){
               codigoTO.setSelecionado(true);
           }
        }    
    };
    public void cargarHijos(String codigo,boolean opcion){
        for(CodigoTO codigoTO:listaMenu){
            if(codigoTO.getCodigo1().equals(codigo)){
                codigoTO.setSelecionado(opcion);
            }
        }
    }
    
    public void cargaActivo(){
        listaActivo.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaActivo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(1));
        codigoTO.setDescripcion("SI");
        listaActivo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(0));
        codigoTO.setDescripcion("NO");
        listaActivo.add(codigoTO);
    }
    public void cargaTipoEmpresa(){
        listaTipoEmpresa.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaTipoEmpresa.add(codigoTO);
        try{
            listaTipoEmpresa.addAll(getParametrosFacadeLocal().listaPorDominio("TIPO_EMPRESA"));
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cambioComuna(ValueChangeEvent e) {
        idComuna=(String) e.getNewValue();
        cargaUsuarioPorComuna();
    }
    public void cargaUsuario(){
        if(idComuna.equals("-1")){
            this.idComuna = "-1";
            this.listaUsuarios.clear();
            try{
                this.listaUsuarios=getLocalizacionFacadeLocal().listaUsuario();
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
             }
        }else{
           cargaUsuarioPorComuna();
        }          
    }
    public void cargaUsuarioPorComuna(){
        this.listaUsuarios.clear();
        try{
            this.listaUsuarios=getLocalizacionFacadeLocal().listaUsuarioPorComuna(new BigDecimal(idComuna));
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void cambioEmpresa(ValueChangeEvent e){
        String id=(String) e.getNewValue().toString();
        if(id.equals("240")){
            this.usuarioTO.setNombreEmpresa("AUTER");
        }
    }
    public void cambioComunaUsuario(ValueChangeEvent e) {
        String id ="";
        try {
            id=(String) e.getNewValue().toString();
            if(!id.equals("-1")){
                ComunaTO comunaTO = new ComunaTO();
                comunaTO = getLocalizacionFacadeLocal().buscaComunaPorId(new BigDecimal(id));
                usuarioTO.setIdComuna(comunaTO.getIdComuna());
                usuarioTO.setLatitud(comunaTO.getLatitud());
                usuarioTO.setLongitud(comunaTO.getLongitud());   
                if(comunaTO.getLatitud() == null && comunaTO.getLongitud() == null){
                    bloqCoordenadas = false;
                }else{
                    bloqCoordenadas = true;
                }            
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar la comuna con el siguiente id: " + id);
        }
    }
    public void ingresarUsuario( ){
        activeIndex = 0;
        usuarioTO = new UserTO();
        for(CodigoTO codigoTO : listaMenu){
            codigoTO.setSelecionado(false);
        }
        bloqIdUsuario = false;
        verPopup= true;
    }
    public void editarUsuario( ) {
        activeIndex = 0;
	usuarioTO = new UserTO();
        try{
            usuarioTO = getLocalizacionFacadeLocal().buscarUsuarioPorId(idSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuarioTO.setPassword2(usuarioTO.getPassword());
        claveAnterior = usuarioTO.getPassword();
        if(usuarioTO.getIntAct().equals("NO")){
            usuarioTO.setIntAct("0");
        }
        if(usuarioTO.getIntAct().equals("SI")){
            usuarioTO.setIntAct("1");
        }       
        try {
            RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
            rolUsuarioTO = getLocalizacionFacadeLocal().buscarRolUsuario(usuarioTO.getIdentificador());
            if(rolUsuarioTO.getOpcion() != null){
                String[] permiso = rolUsuarioTO.getOpcion().split(";");
                for(CodigoTO codigoTO : listaMenu){
                    codigoTO.setSelecionado(false);
                    for(String menu : permiso ){
                        if(codigoTO.getIdCodigo().toString().equals(menu)){
                            codigoTO.setSelecionado(true);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("El usuario "+usuarioTO.getIdentificador()+" no tiene configurado permisos.");
        }
        
        bloqIdUsuario = true;
        verPopup= true;
    }
    public void actualizarUsuario( ){ 
        activeIndex = 0;
        String permisos = "";
        if(validarDatosUsuario()){             
            for(CodigoTO codigoTO :listaMenu){
                if(codigoTO.isSelecionado()){
                    permisos = permisos + codigoTO.getIdCodigo().toString() + ";";
                }
            }
            usuarioTO.setRol("USER");
            if(bloqIdUsuario){
                if(!usuarioTO.getPassword().equals(claveAnterior)){
                    usuarioTO.setPassword(Util.getMD5(usuarioTO.getPassword()));
                }
                try { 
                    getLocalizacionFacadeLocal().editarUsuario(usuarioTO);  
                    RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
                    rolUsuarioTO = getLocalizacionFacadeLocal().buscarRolUsuario(usuarioTO.getIdentificador());
                    rolUsuarioTO.setIdRol(usuarioTO.getTipo().toString());
                    rolUsuarioTO.setOpcion(permisos);
                    getLocalizacionFacadeLocal().editarRolUsuario(rolUsuarioTO);
                    addMessage("Usuario modificado exitosamente.");
                } catch (Exception ex) {
                    Logger.getLogger(UsuarioBacking.class.getName()).log(Level.SEVERE, null, ex);
                } 
                verPopup=false;
                usuarioTO = new UserTO();
                claveAnterior = "";
                cargaUsuario(); 
            }else{
                try {
                if(getLocalizacionFacadeLocal().existeUsuario(usuarioTO.getIdentificador())){
                    mostrarMensaje("El id de usuario ingresado ya existe.");
                }else{
                      
                    usuarioTO.setPassword(Util.getMD5(usuarioTO.getPassword()));                    
                    getLocalizacionFacadeLocal().guardarUsuario(usuarioTO);
                    RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
                    rolUsuarioTO.setIdRol(usuarioTO.getTipo().toString());
                    rolUsuarioTO.setIdUsuario(usuarioTO.getIdentificador());
                    rolUsuarioTO.setOpcion(permisos);                                      
                    getLocalizacionFacadeLocal().guardarRolUsuario(rolUsuarioTO);                    
                    addMessage("Usuario ingresado exitosamente.");                    
                    verPopup=false;
                    usuarioTO = new UserTO();
                    claveAnterior = "";
                    cargaUsuario(); 
                }
                } catch (Exception ex) {
                        Logger.getLogger(UsuarioBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }      
            
        }
    }
    
    public void eliminarUsuario(){
        try {
            usuarioTO = new UserTO();
            usuarioTO = getLocalizacionFacadeLocal().buscarUsuarioPorId(idSeleccionado); 
            if(getLocalizacionFacadeLocal().existeRolUsuarioTO(usuarioTO.getIdentificador().toUpperCase())){
                RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
                rolUsuarioTO = getLocalizacionFacadeLocal().buscarRolUsuario(usuarioTO.getIdentificador());
                getLocalizacionFacadeLocal().eliminarRolUsuario(rolUsuarioTO);
            }            
            getLocalizacionFacadeLocal().eliminarUsuario(usuarioTO);
            addMessage("Usuario eliminado exitosamente.");
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBacking.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No se puede eliminar el usuario.");
        }finally{            
            cargaUsuario();          
        }
    }
    public void addMessage(String summary) {
      PrimeFaces.current().ajax().update("form1:grid");
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public boolean validarDatosUsuario(){
        if(usuarioTO.getIdentificador().equals("")){
            mostrarMensaje("Debe ingresar Usuario.");
            return false;
        }
        if(usuarioTO.getNombre().equals("")){
            mostrarMensaje("Debe ingresar Nombre.");
            return false;
        }
        if(usuarioTO.getApellidoPaterno().equals("")){
            mostrarMensaje("Debe ingresar Apellido Paterno.");
            return false;
        }
        if(usuarioTO.getApellidoMaterno().equals("")){
            mostrarMensaje("Debe ingresar Apellido Materno.");
            return false;
        }
        if(usuarioTO.getIntAct().equals("-1")){
            mostrarMensaje("Debe ingresar Indicador Activo.");
            return false;
        }
        if(usuarioTO.getEmail().equals("")){
            mostrarMensaje("Debe ingresar Email.");
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_EMAIL); 
        Matcher matcher = pattern.matcher(usuarioTO.getEmail());
        if(!matcher.matches()){
            mostrarMensaje("Error de Formato Email.");
            return false;
        }          
        if(usuarioTO.getIdComuna().intValue() < 1){
            mostrarMensaje("Debe seleccionar la Comuna.");
            return false;
        }
        if(usuarioTO.getLatitud().equals("")){
            mostrarMensaje("Debe ingresar Latitud.");
            return false;
        }
        if(usuarioTO.getLongitud().equals("")){
            mostrarMensaje("Debe ingresar Longitud.");
            return false;
        }
        if(usuarioTO.getTipo().intValue() < 1){
            mostrarMensaje("Debe ingresar Tipo Empresa.");
            return false;
        }
        if(usuarioTO.getNombreEmpresa().equals("")){
            mostrarMensaje("Debe ingresar Nombre Empresa.");
            return false;
        }
        if(usuarioTO.getPassword().equals("")){
            mostrarMensaje("Debe ingresar Clave.");
            return false;
        }
        if(usuarioTO.getPassword().length() < 2){
            mostrarMensaje("La clave debe tener una longitud minima de tres caracteres.");
            return false;
        }
        if(usuarioTO.getPassword2().equals("")){
            mostrarMensaje("Debe ingresar reingresar clave.");
            return false;
        }
        if(!usuarioTO.getPassword().equals(usuarioTO.getPassword2())){
            mostrarMensaje("La clave no coincide.");
            return false;
        }
        if(usuarioTO.getIntAct().equals("0")){
            usuarioTO.setIntAct("NO");
        }
        if(usuarioTO.getIntAct().equals("1")){
            usuarioTO.setIntAct("SI");
        }
        return true;
    }
    
    public void mostrarMensaje(String mensaje){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:aCord:idUsuario", new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje, mensaje));
    }    

    public UserTO getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(UserTO usuarioTO) {
        this.usuarioTO = usuarioTO;
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

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public String getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(String idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    } 

    public List<UserTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UserTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public boolean isBloqIdUsuario() {
        return bloqIdUsuario;
    }

    public void setBloqIdUsuario(boolean bloqIdUsuario) {
        this.bloqIdUsuario = bloqIdUsuario;
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

    public List<CodigoTO> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<CodigoTO> listaMenu) {
        this.listaMenu = listaMenu;
    }    

    public String getClaveAnterior() {
        return claveAnterior;
    }

    public void setClaveAnterior(String claveAnterior) {
        this.claveAnterior = claveAnterior;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public boolean isBloqCoordenadas() {
        return bloqCoordenadas;
    }

    public void setBloqCoordenadas(boolean bloqCoordenadas) {
        this.bloqCoordenadas = bloqCoordenadas;
    }
    
}
