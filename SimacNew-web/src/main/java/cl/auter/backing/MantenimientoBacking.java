package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.AtencionArchivoTO;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.InformeDiarioTO;
import cl.auter.patron.to.VistaDocumentoTO;
import cl.auter.patron.to.VistaInstalacionTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean (name = MantenimientoBacking.BEAN_NAME )
@ViewScoped
public class MantenimientoBacking extends FiltroBacking{
    public static final String BEAN_NAME="mantenimientoBacking";
    public  String codigoPag = "215";
    private String comboHabilitada;
    private String pintarTabla;
    private String idComuna;
    private VistaInstalacionTO idCruce;  
    private VistaInstalacionTO cruce;  
    private Date fechaDesde;
    private Date fechaHasta;
    private List<VistaInstalacionTO> listaPuntos=new ArrayList<VistaInstalacionTO>();
    private List<InformeDiarioTO> listaInformeDiarioTO=new ArrayList<InformeDiarioTO>();
    private List<VistaDocumentoTO> listaDocumentos=new ArrayList<VistaDocumentoTO>();
    private InformeDiarioTO informeDiarioTO = new InformeDiarioTO();
    private List<ComunaTO> listaComunasMonitoreo = new ArrayList<ComunaTO>();
    private CodigoTO[] tipoInstalacion;
    private boolean verExportar = false;
    private List<AtencionArchivoTO> listaAtenArchivos = new ArrayList();
    private boolean verMtt = false;
    
    @PostConstruct
    public void carga(){
        tipoInstalacion = obtenerTipoInstalacion();
        cargaEstadosOperacionales();
           // listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreo();

        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))){
            this.comboHabilitada="false";
            this.pintarTabla="true";
        }else{
           this.idComuna= String.valueOf(usurioAutenticado().getIdComuna());
           this.comboHabilitada="true";
           this.listaPuntos.clear();
           try{
              this.listaPuntos=getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(this.idComuna),tipoInstalacion);           
           } catch (Exception ex) {
              Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
    
    public void buscar() {
        listaInformeDiarioTO.clear();
        if (validacionFormulario()){
            if(idComuna.equals("60"))
            {
                verMtt = true;
            }
            else{
                verMtt = false;
            }
            //Agregado Por CMANAN
            verMtt = true;
            try{
                if (idCruce !=null){
                    listaInformeDiarioTO=getLocalizacionFacadeLocal().buscarInformeDiario(new BigDecimal(idComuna),idCruce.getIdCruce(),fechaDesde, fechaHasta);
                    
                }else{
                    listaInformeDiarioTO=getLocalizacionFacadeLocal().buscarInformeDiario(new BigDecimal(idComuna),fechaDesde, fechaHasta);
                }
                verExportar = !listaInformeDiarioTO.isEmpty();
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            listaInformeDiarioTO.clear();
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
       /*   if(fechaHasta.compareTo(fechaActual)>0){
            context.addMessage("form1:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Fecha Hasta No puede Ser Mayor a la Fechas Actual", "Fecha Hassta No puede Ser Mayor a la Fechas Actual"));
            return false;
        }  */      
        if (idComuna == null || idComuna.equals("-1")){
            context.addMessage("form1:idComuna", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe Selecciona una Comuna", "Debe Seleccionar una Comuna"));
            return false;
        }
       return true;
    }
    
    public void startDateFilter(AjaxBehaviorEvent event) {
        //System.out.println(( (Calendar) event.getSource()).getValue().toString());
    }
    public void cambioValorAutoComplete(SelectEvent seleccionado){
         this.idCruce =(VistaInstalacionTO)seleccionado.getObject();
    }
    public void handleUnSelect(UnselectEvent e){
    }

    public void cambioComuna(ValueChangeEvent e) {
        String id=(String) e.getNewValue();
        idComuna = id;
        this.listaPuntos.clear();
        try{
            this.listaPuntos=getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(id),tipoInstalacion);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    JasperPrint jasperPrint;
    public void cargaJasper() throws JRException{                
        FacesContext context = FacesContext.getCurrentInstance();
        jasperPrint = new JasperPrint();
        ServletContext servletContext= (ServletContext) context.getExternalContext().getContext();
        String reportPath=servletContext.getRealPath("/jasper/InformeDiario.jasper");  
        Map parametros=new HashMap();
        parametros.put("Emisor","AUTER");
        jasperPrint=JasperFillManager.fillReport(reportPath, parametros,new JRBeanCollectionDataSource(listaInformeDiarioTO));
    }
    public void imprimirPDF() throws IOException, JRException {  
        cargaJasper();
        HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=InformeDiario.pdf");  
        ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);  
        FacesContext.getCurrentInstance().responseComplete(); 
    } 
    public void cargarCruce(BigDecimal id) {
        this.listaDocumentos.clear();
        try{
            this.listaDocumentos = getLocalizacionFacadeLocal().listaDocumentoPorCruce(id);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }            
        for(InformeDiarioTO informe : listaInformeDiarioTO){
            if(informe.getIdCruce().equals(id)){
                cruce = new VistaInstalacionTO();
                cruce.setIdCruce(id);
                cruce.setUbicacion(informe.getUbicacion());
                break;
            }
        }
    } 
    
    public void cargaDocAtencion(InformeDiarioTO monitor) {
        this.listaAtenArchivos.clear();
        try{
           // this.listaAtenArchivos = getSmaFacadeLocal().obtenerArchivos(monitor.getIdAtencion().intValue());
            cruce = new VistaInstalacionTO();
            cruce.setIdCruce(monitor.getIdCruce());
            cruce.setUbicacion(monitor.getUbicacion());            
        
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

    } 
    
    public void cargarEstado(BigDecimal idAtencion) {
        informeDiarioTO = new InformeDiarioTO();
        for(InformeDiarioTO informe : listaInformeDiarioTO){
            if(informe.getIdAtencion().equals(idAtencion)){
                setInformeDiarioTO(informe);
                break;
            }
        }
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

    public String getPintarTabla() {
        return pintarTabla;
    }

    public void setPintarTabla(String pintarTabla) {
        this.pintarTabla = pintarTabla;
    }

    public List<VistaInstalacionTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<VistaInstalacionTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

   

    public List<InformeDiarioTO> getListaInformeDiarioTO() {
        return listaInformeDiarioTO;
    }

    public void setListaInformeDiarioTO(List<InformeDiarioTO> listaInformeDiarioTO) {
        this.listaInformeDiarioTO = listaInformeDiarioTO;
    }     

    public List<VistaDocumentoTO> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<VistaDocumentoTO> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public InformeDiarioTO getInformeDiarioTO() {
        return informeDiarioTO;
    }

    public void setInformeDiarioTO(InformeDiarioTO informeDiarioTO) {
        this.informeDiarioTO = informeDiarioTO;
    }  

    public VistaInstalacionTO getCruce() {
        return cruce;
    }

    public void setCruce(VistaInstalacionTO cruce) {
        this.cruce = cruce;
    }   

    public List<ComunaTO> getListaComunasMonitoreo() {
        return listaComunasMonitoreo;
    }

    public void setListaComunasMonitoreo(List<ComunaTO> listaComunasMonitoreo) {
        this.listaComunasMonitoreo = listaComunasMonitoreo;
    }

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }

    public List<AtencionArchivoTO> getListaAtenArchivos() {
        return listaAtenArchivos;
    }

    public void setListaAtenArchivos(List<AtencionArchivoTO> listaAtenArchivos) {
        this.listaAtenArchivos = listaAtenArchivos;
    }

    public boolean isVerMtt() {
        return verMtt;
    }

    public void setVerMtt(boolean verMtt) {
        this.verMtt = verMtt;
    }
    
    
    
    
}
