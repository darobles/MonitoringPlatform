package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.VistaCruceHistoricoTO;
import cl.auter.patron.to.VistaCruceTO;
import cl.auter.patron.to.VistaInstalacionTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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

@ManagedBean(name = HistoricoBacking.BEAN_NAME)
@ViewScoped
public class HistoricoBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "historicoBacking";
    private final String codigoPag = "213";
    private String comboHabilitada;
    private String idComuna;
    private VistaCruceTO idCruce;
    private Date fechaDesde;
    private Date fechaHasta;
    private List<VistaCruceTO> listaPuntos = new ArrayList();
    private List<VistaCruceHistoricoTO> listaCruceHistorico = new ArrayList();
    private List<ComunaTO> listaComunasMonitoreo = new ArrayList();
    private List<VistaInstalacionTO> listaInstalaciones = new ArrayList();
    private VistaInstalacionTO vistaInstalacionTO;
    private BigDecimal idCruceSeleccionado;
    private CodigoTO[] tipoInstalacion;
    private boolean validForm = false;
    private boolean verExportar = false;

    @PostConstruct
    public void carga() {
        cargaEstadosOperacionales();
        try {
            tipoInstalacion = obtenerTipoInstalacion();
            vistaInstalacionTO = new VistaInstalacionTO();
            ComunaTO comunaTO = new ComunaTO();
            comunaTO.setIdComuna(new BigDecimal(-1));
            comunaTO.setDescripcionComuna("-- Seleccionar una opcion --");
            comunaTO.setNombre("-- Seleccionar una opcion --");
            this.listaComunasMonitoreo.add(comunaTO);
            if (usurioAutenticado().getTipo().equals(new BigDecimal("242"))) //Usuario UOCT
            {
                listaComunasMonitoreo.addAll(getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT());
            } else {
                ComunaTO comunaTO2 = new ComunaTO();
                comunaTO2.setIdComuna(new BigDecimal("999"));
                comunaTO2.setDescripcionComuna("TODAS");
                comunaTO2.setNombre("TODAS");

                this.listaComunasMonitoreo.add(comunaTO2);
                for (ComunaTO comuna : getLocalizacionFacadeLocal().listaComunasMonitoreo()) {
                    listaComunasMonitoreo.add(comuna);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(HistoricoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina(codigoPag);
        if (!tipoRol.equals(new BigDecimal("241"))) {
            this.comboHabilitada = "false";
        } else {
            this.idComuna = String.valueOf(usurioAutenticado().getIdComuna());
            this.comboHabilitada = "true";
            this.listaPuntos.clear();
            try {
                this.listaPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(new BigDecimal(this.idComuna));

            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startDateFilter(SelectEvent event) {
        Date date = (Date) event.getObject();
        fechaDesde = date;
    }
    
    public void endDateFilter(SelectEvent event) {
        Date date = (Date) event.getObject();
        fechaHasta = date;
    }

    public void popupIdCruce() {
        for (VistaInstalacionTO instalacion : listaInstalaciones) {
            if (instalacion.getIdCruce().equals(idCruceSeleccionado)) {
                vistaInstalacionTO = instalacion;
                break;
            }

        }
    }

    public void buscar() {
        listaCruceHistorico.clear();
        BigDecimal cruce = BigDecimal.ZERO;
        if (idCruce != null) {
            cruce = idCruce.getIdCruce();
        }
        if (validForm) {
            try {
                if (cruce == null || cruce.equals(BigDecimal.ZERO)) {
                    //Muestra todas las de la comuna
                    listaCruceHistorico = getLocalizacionFacadeLocal().listaVistaCrucesHistoricoPorComuna(new BigDecimal(idComuna), fechaDesde, fechaHasta);
                } else {
                    listaCruceHistorico = getLocalizacionFacadeLocal().listaVistaCrucesHistorico(new BigDecimal(idComuna), cruce, fechaDesde, fechaHasta);
                }
                if (idComuna.equals("999")) {
                    //Carga todas las comunas con monitoreo
                    listaInstalaciones = getLocalizacionFacadeLocal().listaVistaInstalacionMonitoreo(tipoInstalacion);
                } else {
                    try {
                        //Carga solo la comuna asociada
                        listaInstalaciones = getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(idComuna), tipoInstalacion);
                    } catch (Exception ex) {
                        Logger.getLogger(HistoricoBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                verExportar = !listaCruceHistorico.isEmpty();
                
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validacionFormulario() {
        UIViewRoot uiv = FacesContext.getCurrentInstance().getViewRoot();
        UIInput inpFechaDesde = (UIInput) uiv.findComponent("config-form-tabs:config-form:idComuna");
        UIInput inpFechaHasta = (UIInput) uiv.findComponent("config-form-tabs:config-form:idHasta");
        UIInput inpidComuna = (UIInput) uiv.findComponent("config-form-tabs:config-form:idComuna");
        FacesContext context = FacesContext.getCurrentInstance();
        Date fechaActual = new Date();
        if (fechaDesde == null) {
            context.addMessage("config-form-tabs:config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar una fecha de inicio"));
            inpFechaDesde.setValid(false);
        } else {
            if (fechaDesde.compareTo(fechaActual) > 0) {
                context.addMessage("config-form-tabs:config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio no puede ser superior a hoy"));
                inpFechaDesde.setValid(false);
            }
            else{
                inpFechaDesde.setValid(true);
            }
        }
        if (fechaHasta == null) {
            context.addMessage("config-form-tabs:config-form:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe ingresar una feha de termino"));
            inpFechaHasta.setValid(false);
        } else {
            if (fechaDesde.compareTo(fechaHasta) > 0) {
            context.addMessage("config-form-tabs:config-form:idHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha de termino no puede ser mayor a la de inicio"));
            inpFechaHasta.setValid(false);
            } else {
                inpFechaHasta.setValid(true);
            }
        }
        if (idComuna == null || idComuna.equals("-1")) {

            context.addMessage("config-form-tabs:config-form:idComuna", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar una comuna"));
            inpidComuna.setValid(false);
        }
        else{
            Date cincoDias = new Date(fechaHasta.getTime() - 432000000);
            if (idComuna.equals("999") && fechaDesde.before(cincoDias)) {
                context.addMessage("config-form-tabs:config-form:idComuna", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!", "Busqueda máxima de 5 días para todas las comunas"));
                inpidComuna.setValid(false);
            }
            else{
                inpidComuna.setValid(true);
                
            }
        }
        if(inpFechaDesde.isValid() && inpFechaHasta.isValid() && inpidComuna.isValid())
        {
            PrimeFaces.current().executeScript("$('.layout-config-button').click()");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cargando!", "La busqueda puede tardar unos segundos."));
            PrimeFaces.current().ajax().update("form1:messages"); 
            PrimeFaces.current().ajax().update("config-form-tabs"); 
            validForm = true;
            return true;
        }
            
        else
        {
            validForm = false;
            return false;
        }
    }

    public void cambioValorAutoComplete(SelectEvent seleccionado) {
        this.idCruce = (VistaCruceTO) seleccionado.getObject();
    }

    public void cambioComuna(ValueChangeEvent e) {
        String id = (String) e.getNewValue();
        listaPuntos.clear();
        try {
            listaPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(new BigDecimal(id));
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<VistaCruceTO> filtroCruce(String calle) {
        List<VistaCruceTO> listaCruce = new ArrayList();
        //if (calle !=null && !calle.equals("") && calle.length()>2){
        if (calle != null && !calle.equals("")) {
            for (VistaCruceTO vistaCruceTO : listaPuntos) {
                if (vistaCruceTO.getUbicacion().toUpperCase().contains(calle.toUpperCase())) {
                    listaCruce.add(vistaCruceTO);
                }
            }
        }
        return listaCruce;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
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

    public List<VistaCruceHistoricoTO> getListaCruceHistorico() {
        return listaCruceHistorico;
    }

    public void setListaCruceHistorico(List<VistaCruceHistoricoTO> listaCruceHistorico) {
        this.listaCruceHistorico = listaCruceHistorico;
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public VistaCruceTO getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(VistaCruceTO idCruce) {
        this.idCruce = idCruce;
    }

    public List<VistaCruceTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<VistaCruceTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List<ComunaTO> getListaComunasMonitoreo() {
        return listaComunasMonitoreo;
    }

    public void setListaComunasMonitoreo(List<ComunaTO> listaComunasMonitoreo) {
        this.listaComunasMonitoreo = listaComunasMonitoreo;
    }

    public VistaInstalacionTO getVistaInstalacionTO() {
        return vistaInstalacionTO;
    }

    public void setVistaInstalacionTO(VistaInstalacionTO vistaInstalacionTO) {
        this.vistaInstalacionTO = vistaInstalacionTO;
    }

    public BigDecimal getIdCruceSeleccionado() {
        return idCruceSeleccionado;
    }

    public void setIdCruceSeleccionado(BigDecimal idCruceSeleccionado) {
        this.idCruceSeleccionado = idCruceSeleccionado;
    }

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }
    
    

}
