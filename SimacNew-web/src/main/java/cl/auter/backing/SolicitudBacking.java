package cl.auter.backing;

import cl.auter.backing.base.BaseBacking;
import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.EmailTO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import cl.auter.patron.to.SolicitudTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean (name = SolicitudBacking.BEAN_NAME )
@ViewScoped
public class SolicitudBacking extends BaseBacking implements Serializable {
	public static final String BEAN_NAME="solicitudBacking";
        public  String codigoPag = "217";
	private static final long serialVersionUID = 1L;
	private String idComuna;
        private List<CodigoTO> listaCodigo = new ArrayList();
        private String nombreSolicitante;
        private String emailSolicitante;
        private String fonoSolicitante;
        private String observacionSolicitante;
        private Date fechaIngreso;
        private SolicitudTO solicitudTO;
        private Connection conn;
        private String estadoInicial = new String();
        private String comboHabilitada;
        private UploadedFile imagen;
        private byte[] inputImagen;
        
        
	@PostConstruct
	public void carga(){
            BigDecimal tipoRol = usurioAutenticado().getTipo();
            validarPagina(codigoPag);
            if (!tipoRol.equals(new BigDecimal("241"))){
                this.comboHabilitada="false";
               }else{
               this.idComuna= String.valueOf(usurioAutenticado().getIdComuna());
               this.comboHabilitada="true";;           
            } 
            solicitudTO = new SolicitudTO();
            try{
                this.setEstadoInicial((getParametrosFacadeLocal().buscarDominioCodigo("ESTADO_SOLICITUD", "1")).getCodigo2());
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
             }
	}
        /**
         * Metodo para guardar la Solicitud 
         */
       	public void guardarSolicitud(){                        
            if(validarCamposFormulario())  {
                try {
                    conn = getJNDIConnection();
                    Clob clob = conn.createClob();   
                    clob.setString(1, observacionSolicitante);
                    solicitudTO.setObservacion(clob);
                    conn.close();
                    solicitudTO.setEstadoActual(getEstadoInicial());
                    solicitudTO.setFechaIngreso(getParametrosFacadeLocal().traerFechaHoraActual());
                    solicitudTO.setNumTareaAsma(BigDecimal.ZERO);
                    solicitudTO.setUsrIngresado(usurioAutenticado().getNombre()+" "+usurioAutenticado().getApellidoPaterno()+" "+usurioAutenticado().getApellidoMaterno());                 
                    solicitudTO.setTipoSolicitante(usurioAutenticado().getTipo());
                    try{  
                        if (imagen != null && imagen.getFileName() != null && !imagen.getFileName().equals("") && inputImagen.length > 0) {
                            String ruta = getParametrosFacadeLocal().buscarDominioCodigo("RUTA_DOCUMENTO", "1").getDescripcion();
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
                            String nombreArchivo = fmt.format(new Date())+ imagen.getFileName().substring(imagen.getFileName().lastIndexOf('.'));
                            solicitudTO.setUrlAdjunto(ruta+nombreArchivo);              
                            Util.copyFile(ruta, nombreArchivo, inputImagen);
                        }                        
                        getLocalizacionFacadeLocal().guardaSolicitud(solicitudTO);
                    } catch (Exception ex) {
                        Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    try {
                        EmailTO emailTO = new EmailTO();
                        emailTO.setCuenta(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CUENTA").getCodigo2());
                        emailTO.setClave(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CLAVE").getCodigo2());
                        emailTO.setContenido(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CORREO_INGRESO").getCodigo2());
                        Util.mailCreacion(emailTO, solicitudTO);
                    } catch (Exception ex) {
                        Logger.getLogger(SolicitudBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    setObservacionSolicitante("");
                    solicitudTO = new SolicitudTO();
                   
                    addMessage("Solicitud Ingresada");
                } catch (SQLException ex) {
                    Logger.getLogger(SolicitudBacking.class.getName()).log(Level.SEVERE, null, ex);
                } 
            
            }
    }
        
    public boolean validarCamposFormulario(){
        if(idComuna.equals("-1")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Seleccionar una comuna.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }else{
            solicitudTO.setIdComuna(new BigDecimal(idComuna));
        }
        if(solicitudTO.getNombreSolicitante().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Ingresar el Nombre del Solicitante.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }        
        if(solicitudTO.getEmailSolicitante().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Ingresar el Email del Solicitante.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN_EMAIL); 
        Matcher matcher = pattern.matcher(solicitudTO.getEmailSolicitante());
        if(!matcher.matches()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Error de Formato Email.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }  
        if(solicitudTO.getFonoSolicitante().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Ingresar el Teléfono del Solicitante.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        if(solicitudTO.getAsunto().equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Ingresar el Asunto de la Solicitud.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        if(observacionSolicitante.equals("")){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Debe Ingresar la Observación de la Solicitud.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true;
    }
        
     Connection getJNDIConnection(){
            String DATASOURCE_CONTEXT = "java:jboss/georeferenciaDS";
            Connection result = null;
    try {
            Context initialContext = new InitialContext();
      if ( initialContext != null){
        
      } else {  }
      DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
      if (datasource != null) {
        result = datasource.getConnection();
      }
      else {
        System.out.println("Failed to lookup datasource.");
      }
    }
    catch ( NamingException | SQLException ex ) {
      System.out.println("Cannot get connection: " + ex);
    }
    return result;
    }  
    
    
     public void addMessage(String summary) {
         PrimeFaces.current().ajax().update("form1:grid");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);

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

    public List<CodigoTO> getListaCodigo() {
        return listaCodigo;
    }

    public void setListaCodigo(List<CodigoTO> listaCodigo) {
        this.listaCodigo = listaCodigo;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
     
    public void onCambioComuna() {
    }
    
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public SolicitudTO getSolicitudTO() {
        return solicitudTO;
    }

    public void setSolicitudTO(SolicitudTO solicitudTO) {
        this.solicitudTO = solicitudTO;
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

    public String getObservacionSolicitante() {
        return observacionSolicitante;
    }

    public void setObservacionSolicitante(String observacionSolicitante) {
        this.observacionSolicitante = observacionSolicitante;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    
    
    

}
