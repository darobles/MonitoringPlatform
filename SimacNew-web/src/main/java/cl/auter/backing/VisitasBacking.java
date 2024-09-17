/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CruceMovilTO;
import cl.auter.patron.to.CruceTO;
import cl.auter.patron.to.MovilTO;
import cl.auter.patron.to.VistaMovilHistoricoTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author drobles
 */
@Named(value = VisitasBacking.BEAN_NAME)
@ViewScoped
public class VisitasBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "visitasBacking";
    private List<VistaMovilHistoricoTO> listaMoviles = new ArrayList();
    private List<CruceTO> listaCruces = new ArrayList();
    private List<CruceMovilTO> listaTabla = new ArrayList();
    private CruceTO cruce;
    private MovilTO movil;
    private Date fechaDesde;
    private Date horaDesde;
    private Date horaHasta;
    private String pintarTabla;
    private String idComuna;
    private List<CruceTO> listaPuntos = new ArrayList();
    private final  String codigoPag = "257";
    private boolean verExportar = false;

    @PostConstruct
    public void init() {
        validarPagina(codigoPag);
        try {
            idComuna = String.valueOf(usurioAutenticado().getIdComuna());
            if (idComuna.equals("999")) {
                listaPuntos = getLocalizacionFacadeLocal().listaCruceTodos();
            } else {
                listaPuntos = getLocalizacionFacadeLocal().buscarCrucePorIdComuna(new BigDecimal(idComuna));
            }
        } catch (Exception ex) {
            Logger.getLogger(VisitasBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        pintarTabla = "false";
        setTiempoInicial();
    }

    public void buscarMovil() {
        if (validacionFormulario()) {
            try {
                pintarTabla = "true";
                cargarMovil(cruce.getIdcruce());
                java.util.Calendar calFechaDesde = java.util.Calendar.getInstance();
                calFechaDesde.setTime(fechaDesde);
                java.util.Calendar calHoraDesde = java.util.Calendar.getInstance();
                calHoraDesde.setTime(horaDesde);
                java.util.Calendar calHoraHasta = java.util.Calendar.getInstance();
                calHoraHasta.setTime(horaHasta);

                java.util.Calendar calFechaFinalDesde = java.util.Calendar.getInstance();
                calFechaFinalDesde.set(java.util.Calendar.HOUR_OF_DAY, calHoraDesde.get(java.util.Calendar.HOUR_OF_DAY));
                calFechaFinalDesde.set(java.util.Calendar.MINUTE, calHoraDesde.get(java.util.Calendar.MINUTE));
                calFechaFinalDesde.set(java.util.Calendar.SECOND, 0);
                calFechaFinalDesde.set(java.util.Calendar.YEAR, calFechaDesde.get(java.util.Calendar.YEAR));
                calFechaFinalDesde.set(java.util.Calendar.MONTH, calFechaDesde.get(java.util.Calendar.MONTH));
                calFechaFinalDesde.set(java.util.Calendar.DAY_OF_MONTH, calFechaDesde.get(java.util.Calendar.DAY_OF_MONTH));

                java.util.Calendar calFechaFinalHasta = java.util.Calendar.getInstance();
                calFechaFinalHasta.set(java.util.Calendar.HOUR_OF_DAY, calHoraHasta.get(java.util.Calendar.HOUR_OF_DAY));
                calFechaFinalHasta.set(java.util.Calendar.MINUTE, calHoraHasta.get(java.util.Calendar.MINUTE));
                calFechaFinalHasta.set(java.util.Calendar.SECOND, 59);
                calFechaFinalHasta.set(java.util.Calendar.YEAR, calFechaDesde.get(java.util.Calendar.YEAR));
                calFechaFinalHasta.set(java.util.Calendar.MONTH, calFechaDesde.get(java.util.Calendar.MONTH));
                calFechaFinalHasta.set(java.util.Calendar.DAY_OF_MONTH, calFechaDesde.get(java.util.Calendar.DAY_OF_MONTH));
                listaMoviles = getLocalizacionFacadeLocal().listaMovilCruce(cruce.getIdcruce(), calFechaFinalDesde.getTime(), calFechaFinalHasta.getTime());
                listaTabla.clear();
                for(VistaMovilHistoricoTO movilHist: listaMoviles){
                    CruceMovilTO movilTabla = new CruceMovilTO();
                    movilTabla.setIdreg(movilHist.getId_Reg());
                    movilTabla.setCodigoMovil(movilHist.getCodigoMovil());
                    movilTabla.setLatitud(movilHist.getLatitud());
                    movilTabla.setLongitud(movilHist.getLongitud());
                    String hora = movilHist.getFecultlec().toString().substring(0,movilHist.getFecultlec().toString().length()-2);
                    movilTabla.setFecultLec(hora);
                    listaTabla.add(movilTabla);
                }
                verExportar = !listaTabla.isEmpty();
            } catch (Exception ex) {
                Logger.getLogger(VisitasBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cambiarHoraDesde(AjaxBehaviorEvent event) throws ParseException {

        DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = format.parse(((Calendar) event.getSource()).getValue().toString());
        horaDesde = date;
    }

    public void cambiarHoraHasta(AjaxBehaviorEvent event) throws ParseException {

        DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = format.parse(((Calendar) event.getSource()).getValue().toString());
        horaHasta = date;
    }

    public void startDateFilter(AjaxBehaviorEvent event) {
        //System.out.println(((Calendar) event.getSource()).getValue().toString());
        //System.out.println("Objeto " + event.toString());
    }

    private void setTiempoInicial() {
        java.util.Calendar calFechaDesde = java.util.Calendar.getInstance();
        fechaDesde = calFechaDesde.getTime();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 1);
        horaDesde = cal.getTime();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal2.set(java.util.Calendar.HOUR_OF_DAY, 23);
        cal2.set(java.util.Calendar.MINUTE, 59);
        cal2.set(java.util.Calendar.SECOND, 59);
        horaHasta = cal2.getTime();

    }

    public List<CruceTO> filtroCruce(String calle) {
        List<CruceTO> listaCruce = new ArrayList();
        listaCruce.clear();
        if (calle != null && !calle.equals("")) {
            for (CruceTO cruceTO : listaPuntos) {
                if (cruceTO.getUbicacion().toUpperCase().contains(calle.toUpperCase())) {
                    listaCruce.add(cruceTO);
                }
            }
        }
        return listaCruce;
    }

    public boolean validacionFormulario() {
        FacesContext context = FacesContext.getCurrentInstance();
        Date fechaActual = new Date();
        if (cruce == null || cruce.getIdcruce().equals(new BigDecimal("-1"))) {
            context.addMessage("config-form:idCruce", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un Cruce", "Debe ingresar un Cruce"));
            return false;
        }
        if (fechaDesde == null) {
            context.addMessage("config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha desde", "Debe ingresar una Fecha Desde"));
            return false;
        }
        if (fechaDesde.compareTo(fechaActual) > 0) {
            context.addMessage("config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha Desde No puede Ser Mayor a la Fechas Actual", "Fecha Desde No puede Ser Mayor a la Fechas Actual"));
            return false;
        }
        if (horaDesde.compareTo(horaHasta) > 0) {
            context.addMessage("config-form:idHoraHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hora Hasta no puede ser menor a Hora Desde", "Hora Hasta no puede ser menor a Hora Desde"));
            return false;
        }

        return true;
    }

    public void cambioValorAutoComplete(AjaxBehaviorEvent e) {
        AutoComplete autoComplete = ((AutoComplete) e.getSource());
        for (CruceTO cruceTO : listaPuntos) {
            if (cruceTO.getIdcruce().equals(new BigDecimal (autoComplete.getSubmittedValue().toString())) && !autoComplete.getSubmittedValue().toString().equals("")) {
                cruce = cruceTO;
                break;
            }
        }        
    }
    
    private void cargarMovil(BigDecimal idcruce) {
        for(CruceTO cruceTO: listaPuntos){
            if(cruceTO.getIdcruce().equals(idcruce)){
                cruce = cruceTO;
            }
        }
    }
    
    public List<VistaMovilHistoricoTO> getListaMovilesCruce() {
        return listaMoviles;
    }

    public void setListaMovilesCruce(List<VistaMovilHistoricoTO> listaMovilesCruce) {
        this.listaMoviles = listaMovilesCruce;
    }

    public List<CruceTO> getListaCruces() {
        return listaCruces;
    }

    public void setListaCruces(List<CruceTO> listaCruces) {
        this.listaCruces = listaCruces;
    }

    public CruceTO getCruce() {
        return cruce;
    }

    public void setCruce(CruceTO cruce) {
        this.cruce = cruce;
    }

    public MovilTO getMovil() {
        return movil;
    }

    public void setMovil(MovilTO movil) {
        this.movil = movil;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public String getPintarTabla() {
        return pintarTabla;
    }

    public void setPintarTabla(String pintarTabla) {
        this.pintarTabla = pintarTabla;
    }

    public List<CruceTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<CruceTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public List<VistaMovilHistoricoTO> getListaMoviles() {
        return listaMoviles;
    }

    public void setListaMoviles(List<VistaMovilHistoricoTO> listaMoviles) {
        this.listaMoviles = listaMoviles;
    }

    public List<CruceMovilTO> getListaTabla() {
        return listaTabla;
    }

    public void setListaTabla(List<CruceMovilTO> listaTabla) {
        this.listaTabla = listaTabla;
    }

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }
    
}
