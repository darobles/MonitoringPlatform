package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal;
import cl.auter.patron.to.AtencionTareaTO;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.EstadisticaTO;
import cl.auter.patron.to.TipoInstalacion;
import cl.auter.patron.to.VistaInstalacionTO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;

@ManagedBean (name = EstadisticaBacking.BEAN_NAME )
@ViewScoped
public class EstadisticaBacking extends FiltroBacking{
    public static final String BEAN_NAME="estadisticaBacking";
    public  String codigoPag = "220";
    @EJB
    public LocalizacionFacadeLocal localFacade; 
    
    private Date fechaDesde;
    private Date fechaHasta;
    private String comboHabilitada;
    private String idComuna;    
    private VistaInstalacionTO idCruce;
    private String verColTiempoRespuesta; 

    private List<SelectItem> listaFallasSelect = new ArrayList();
    private List<ComunaTO> listaComunasSelect = new ArrayList();    
    private List<VistaInstalacionTO> listaPuntos=new ArrayList();
  
    private int page = 1;
    private CodigoTO[] tipoInstalacion;
    private List<EstadisticaTO> listaEstadisticaTO = new ArrayList();
    private List<AtencionTareaTO> listaAtencionesTO = new ArrayList();
    private boolean verExportar = false;
    
    @PostConstruct
    public void carga(){
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina(codigoPag);
        tipoInstalacion = obtenerTipoInstalacion();
        if (!tipoRol.equals(new BigDecimal("241"))){
            this.comboHabilitada="false";
            this.verColTiempoRespuesta="true";
        }else{
           this.idComuna= String.valueOf(usurioAutenticado().getIdComuna());
           this.comboHabilitada="true";
           this.verColTiempoRespuesta="false";
           this.listaPuntos.clear();
           try{
                this.listaPuntos=getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(this.idComuna),tipoInstalacion);           
           } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
           }
        }       
    }
    public void startDateFilter(AjaxBehaviorEvent event) {
    }
    
    public void cambioValorAutoComplete(SelectEvent seleccionado){
         this.idCruce =(VistaInstalacionTO)seleccionado.getObject();
    }
    public List<VistaInstalacionTO> filtroCruce(String calle) {
        List<VistaInstalacionTO> listaCruce = new ArrayList<VistaInstalacionTO>();
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
    public void cambioComuna(ValueChangeEvent e) {
        String id=(String) e.getNewValue();
        this.listaPuntos.clear();
        try{
            this.listaPuntos=localFacade.listaVistaInstalacionPorComuna(new BigDecimal(id),tipoInstalacion);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void buscar() {
      listaEstadisticaTO.clear();
      BigDecimal cruce = BigDecimal.ZERO;
      if(validacionFormulario()){
            if(idCruce != null){
                cruce = idCruce.getIdCruce();
            }
            try{
               // listaEstadisticaTO = localFacade.obtenerEstadistica(cruce,idComuna,fechaDesde,fechaHasta);
                verExportar = !listaEstadisticaTO.isEmpty();
                 System.out.println("Ver ex " + verExportar);
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     public boolean validacionFormulario(){
        FacesContext context = FacesContext.getCurrentInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaActual = new Date();
        if (fechaDesde == null){
            context.addMessage("form1:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe ingresar una fecha desde", "Debe ingresar una Fecha Desde"));
            return false;
        }
        if (fechaHasta == null){
            context.addMessage("form1:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe ingresar una Fecha Hasta", "Debe ingresar una Fecha Hasta"));
            return false;
        }
        if(fechaDesde.compareTo(fechaHasta)>0){
            context.addMessage("form1:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Fecha Desde No puede Ser Mayor a la Fechas Hasta", "Fecha Desde No puede Ser Mayor a la Fechas Hasta"));
            return false;
        }
        if(fechaDesde.compareTo(fechaActual)>0){
            context.addMessage("form1:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Fecha Desde No puede Ser Mayor a la Fechas Actual", "Fecha Desde No puede Ser Mayor a la Fechas Actual"));
            return false;
        }
        if(fechaHasta.compareTo(fechaActual)>0){
            context.addMessage("form1:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Fecha Hasta No puede Ser Mayor a la Fechas Actual", "Fecha Hassta No puede Ser Mayor a la Fechas Actual"));
            return false;
        }
        long diferencia= ((fechaHasta.getTime() - fechaDesde.getTime())/3600000)/24;
        long dias = Long.parseLong(buscaDominio("DIAS_BUSQUEDA_ESTADISTICA","01","01"));
        if(diferencia > dias){
            context.addMessage("form1:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR ,"El plazo maximo de estadisticas no debe superar los "+ dias +" días", "El plazo maximo de estadisticas no debe superar los "+ dias +" días"));
            return false;
        }
        if (idComuna == null || idComuna.equals("-1")){
            context.addMessage("form1:idComuna", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe Seleccionar una Comuna", "Debe Seleccionar una Comuna"));
            return false;
        }
       return true;
    }
     
    public String buscaDominio(String dominio, String codigo1, String codigo2){
        try{
        CodigoTO codigoTO=getParametrosFacadeLocal().buscarDominioCodigo(dominio,codigo1,codigo2); 
        return codigoTO.getDescripcion();
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
     
    public void cargarDetalle(String nombreEquipo, String tipoFalla) {
        listaAtencionesTO.clear();
        BigDecimal cruce = BigDecimal.ZERO;
        if(idCruce != null){
            cruce = idCruce.getIdCruce();
        }
        try{
//            listaAtencionesTO = localFacade.obtenerDetalleAtencion(nombreEquipo,tipoFalla,cruce,idComuna,fechaDesde,fechaHasta);            
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public List<EstadisticaTO> getListaEstadisticaTO() {
        return listaEstadisticaTO;
    }

    public void setListaEstadisticaTO(List<EstadisticaTO> listaEstadisticaTO) {
        this.listaEstadisticaTO = listaEstadisticaTO;
    }
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<SelectItem> getListaFallasSelect() {
        return listaFallasSelect;
    }

    public void setListaFallasSelect(List<SelectItem> listaFallasSelect) {
        this.listaFallasSelect = listaFallasSelect;
    }

    public List<ComunaTO> getListaComunasSelect() {
        return listaComunasSelect;
    }

    public void setListaComunasSelect(List<ComunaTO> listaComunasSelect) {
        this.listaComunasSelect = listaComunasSelect;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public VistaInstalacionTO getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(VistaInstalacionTO idCruce) {
        this.idCruce = idCruce;
    }

    public List<VistaInstalacionTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<VistaInstalacionTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }  

    public String getVerColTiempoRespuesta() {
        return verColTiempoRespuesta;
    }

    public void setVerColTiempoRespuesta(String verColTiempoRespuesta) {
        this.verColTiempoRespuesta = verColTiempoRespuesta;
    }

    public List<AtencionTareaTO> getListaAtencionesTO() {
        return listaAtencionesTO;
    }

    public void setListaAtencionesTO(List<AtencionTareaTO> listaAtencionesTO) {
        this.listaAtencionesTO = listaAtencionesTO;
    }

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }
    
    
    
}
