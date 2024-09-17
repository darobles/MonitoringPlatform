/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.PrimeFaces;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

/**
 *
 * @author drobles
 */
@ManagedBean(name = RecorridoBacking.BEAN_NAME)
@ViewScoped
public class RecorridoBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "recorridoBacking";
    public String codigoPag = "252";
    private MovilTO movil;
    private String codigomovil;
    private Date fechaDesde;
    private List<MovilTO> listaMoviles = new ArrayList();
    private List<VistaMovilHistoricoTO> listaMovilHistorico = new ArrayList();
    private List<VistaMovilHistoricoTO> listaCrucesVisitados;
    private MapModel polylineModel;
    private String pintarMapa;
    private String seleccion;
    private boolean pintarTabla;
    private Date horaDesde = new Date();
    private Date horaHasta = new Date();
    private List<CruceTO> listaCrucesTotales = new ArrayList();
    private BigDecimal idComuna;
    private String centro;
    private boolean verMensaje1 = false;
    private boolean verMensaje2 = false;
    private boolean verMensaje3 = false;
    private boolean verMensaje4 = false;
    private boolean verExportar = false;
    private boolean formularioValido = false;

    @PostConstruct
    public void init() {
        try {
            validarPagina(codigoPag);
            centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
            setTiempoInicial();
            seleccion = "Mapa";
            idComuna = usurioAutenticado().getIdComuna();
            listaCrucesTotales = new ArrayList();
            listaCrucesTotales = getLocalizacionFacadeLocal().listaCruceTodos();
            if (idComuna.equals(new BigDecimal("999"))) {
                listaMoviles = getLocalizacionFacadeLocal().listaMovilTodos();
            } else {
                listaMoviles = getLocalizacionFacadeLocal().listaMovilComuna(idComuna);
            }
            pintarTabla = false;
            pintarMapa = "true";
        } catch (Exception ex) {
            Logger.getLogger(RecorridoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<MovilTO> buscarMovil(String movil) {
        List<MovilTO> listaCruce = new ArrayList();
        listaCruce.clear();
        if (movil != null) {
            for (MovilTO vistaCruceTO : listaMoviles) {
                if (vistaCruceTO.getCodigoMovil().toUpperCase().contains(movil.toUpperCase())) {
                    listaCruce.add(vistaCruceTO);
                }
            }
        }
        return listaCruce;
    }

    public void buscarRutaMovil() {
        if (formularioValido) {
            try {
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
                listaMovilHistorico = getLocalizacionFacadeLocal().listaVistaMovilHistorico(movil, calFechaFinalDesde.getTime(), calFechaFinalHasta.getTime());
                generarMapa();
            } catch (Exception ex) {
                Logger.getLogger(HistoricoBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            verExportar = !listaMovilHistorico.isEmpty() && pintarTabla;
        } else {
            listaMovilHistorico.clear();
        }
    }

    public void startDateFilter(AjaxBehaviorEvent event) {
        //System.out.println(((Calendar) event.getSource()).getValue().toString());
        //System.out.println("Objeto " + event.toString());
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

    public boolean validacionFormulario() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot uiv = FacesContext.getCurrentInstance().getViewRoot();
        UIInput inpIdMovil = (UIInput) uiv.findComponent("config-form-tabs:config-form:idMovil");
        UIInput inpFechaDesde = (UIInput) uiv.findComponent("config-form-tabs:config-form:idDesde");
        UIInput inpHoraHasta = (UIInput) uiv.findComponent("config-form-tabs:config-form:idHoraHasta");
        
        Date fechaActual = new Date();
        if (movil == null || movil.getCodigoMovil().equals("-1")) {
            context.addMessage("config-form-tabs:config-form:idMovil", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Seleccionae un Móvil", "Debe Seleccionar un Móvil"));
            verMensaje1 = true;
            inpIdMovil.setValid(false);
        } else {
            inpIdMovil.setValid(true);
        }
        if (fechaDesde == null) {
            context.addMessage("config-form-tabs:config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha desde", "Debe ingresar una Fecha Desde"));
            inpFechaDesde.setValid(false);
            verMensaje2 = true;
        } else {
            if (fechaDesde.compareTo(fechaActual) > 0) {
                context.addMessage("config-form-tabs:config-form:idDesde", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha Desde No puede Ser Mayor a la Fechas Actual", "Fecha Desde No puede Ser Mayor a la Fechas Actual"));
                inpFechaDesde.setValid(false);
                verMensaje2 = true;
            }
            else{
             inpFechaDesde.setValid(true);
            }
        }        
        if (horaDesde.compareTo(horaHasta) > 0) {
            context.addMessage("config-form-tabs:config-form:idHoraHasta", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hora Hasta no puede ser menor a Hora Desde", "Hora Hasta no puede ser menor a Hora Desde"));
            inpHoraHasta.setValid(false);
            verMensaje3 = true;
        } else {
            inpHoraHasta.setValid(true);
        }
        
        if(inpIdMovil.isValid() && inpFechaDesde.isValid() && inpHoraHasta.isValid())
        {
            PrimeFaces.current().executeScript("$('.layout-config-button').click()");
            //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cargando!", "La busqueda puede tardar unos segundos."));
            PrimeFaces.current().ajax().update("form1:messages"); 
            PrimeFaces.current().ajax().update("config-form-tabs"); 
            formularioValido = true;
            return true;
        }
        else{
            return false;
        }
        
    }
    

    public void cambioMovil(AjaxBehaviorEvent e) {
        SelectOneMenu selectOneMenu = ((SelectOneMenu) e.getSource());
        for (MovilTO movilTO : listaMoviles) {
            if (selectOneMenu != null && movilTO.getCodigoMovil().equals(selectOneMenu.getSubmittedValue().toString())) {
                movil = movilTO;
                break;
            }
        }
    }

    public void cambioSeleccion(AjaxBehaviorEvent e) {
        SelectOneRadio selectOneRadio = ((SelectOneRadio) e.getSource());
        seleccion = selectOneRadio.getSubmittedValue().toString();
        if (seleccion.equals("Tabla")) {
            pintarMapa = "false";
            pintarTabla = true;
        } else if (seleccion.equals("Mapa")) {
            pintarTabla = false;
            pintarMapa = "true";
        }
    }

    private void generarMapa() {
        if (seleccion.equals("Mapa")) {
            pintarMapa = "true";
        }
        polylineModel = new DefaultMapModel();
        listaCrucesVisitados = new ArrayList();
        if (listaMovilHistorico != null && listaMovilHistorico.size() > 0) {
            Polyline polyline = new Polyline();
            for (VistaMovilHistoricoTO movilHistorico : listaMovilHistorico) {
                listaCrucesVisitados.add(movilHistorico);
                LatLng coord1 = new LatLng(Double.parseDouble(movilHistorico.getLatitud()), Double.parseDouble(movilHistorico.getLongitud()));
                polyline.getPaths().add(coord1);
            }
            polyline.setStrokeWeight(4);
            polyline.setStrokeColor("#000000");
            polyline.setStrokeOpacity(0.7);
            polylineModel.addOverlay(polyline);
            LatLng centroNew = GetCentrePointFromListOfLocations(polyline.getPaths());
            centro = centroNew.getLat() + "," + centroNew.getLng();
            generarPuntoCruces();

        }
    }
    
    public LatLng GetCentrePointFromListOfLocations(List<LatLng> coordList) {
        int total = coordList.size();

        double X = 0;
        double Y = 0;
        double Z = 0;

        for (LatLng location : coordList) {
            double lat = location.getLat() * Math.PI / 180;
            double lon = location.getLng() * Math.PI / 180;

            double x = Math.cos(lat) * Math.cos(lon);
            double y = Math.cos(lat) * Math.sin(lon);
            double z = Math.sin(lat);

            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;

        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);

        LatLng tempLocation = new LatLng(Lat * 180 / Math.PI, Lon * 180 / Math.PI);
        return tempLocation;
    }

    private void generarPuntoCruces() {
        int aux = 0;
        boolean existe = false;
        for (VistaMovilHistoricoTO movilHist : listaCrucesVisitados) {
            LatLng coord1;
            for (CruceTO cruceTO : listaCrucesTotales) {
                if (cruceTO.getIdcruce().toBigInteger().equals(movilHist.getUltCruce())) {
                    if (cruceTO.getLatitud() != null && cruceTO.getLongitud() != null) {
                        coord1 = new LatLng(Double.parseDouble(cruceTO.getLatitud()), Double.parseDouble(cruceTO.getLongitud()));
                        String label = "Cruce: " + movilHist.getUltCruce() + "\nHora: " + movilHist.getFecultlec().toString().substring(0, movilHist.getFecultlec().toString().length() - 2);
                        Marker marker = new Marker(coord1);
                        existe = false;
                        for (Marker markerAux : polylineModel.getMarkers()) {
                            if (markerAux.getLatlng().getLat() == marker.getLatlng().getLat() && marker.getLatlng().getLng() == markerAux.getLatlng().getLng()) {
                                if (!markerAux.getTitle().contains(movilHist.getFecultlec().toString().substring(0, movilHist.getFecultlec().toString().length() - 2)) && !markerAux.equals(polylineModel.getMarkers().get(0))) {
                                    markerAux.setTitle(markerAux.getTitle() + "\n           " + movilHist.getFecultlec().toString().substring(0, movilHist.getFecultlec().toString().length() - 2));
                                }
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            if (polylineModel.getMarkers().size() > 0) {
                                marker.setIcon(calcularTangente(polylineModel.getMarkers().get(aux), marker));
                                marker.setTitle(label);
                                aux++;
                            } else {
                                String label2 = "Inicio recorrido \n" + label;
                                marker.setTitle(label2);
                                marker.setIcon("../../imagenes/mapa/circuloVerde.png");
                            }

                            if (!idComuna.equals(cruceTO.getIdComuna()) && !idComuna.equals(new BigDecimal("999")) && !polylineModel.getMarkers().isEmpty()) {
                                marker.setVisible(false);
                            }
                            polylineModel.addOverlay(marker);
                        }
                    }
                }
            }
        }
    }

    public String calcularTangente(Marker markerAnterior, Marker markerActual) {
        String imagen = null;
        //Double tangente = (/);

        Double angulo = Math.toDegrees(Math.atan2((markerActual.getLatlng().getLat() - markerAnterior.getLatlng().getLat()), (markerActual.getLatlng().getLng() - markerAnterior.getLatlng().getLng())));
        if (angulo > -22.5 && angulo <= 22.5) {
            imagen = "../../imagenes/mapa/flechas/flecha0.png";
        } else if (angulo > 22.5 && angulo <= 67.5) {
            imagen = "../../imagenes/mapa/flechas/flecha45.png";
        } else if (angulo > 67.5 && angulo <= 112.5) {
            imagen = "../../imagenes/mapa/flechas/flecha90.png";
        } else if (angulo > 112.5 && angulo <= 157.5) {
            imagen = "../../imagenes/mapa/flechas/flecha135.png";
        } else if ((angulo > 157.5 && angulo <= 180) || (angulo >= -180 && angulo < -157.5)) {
            imagen = "../../imagenes/mapa/flechas/flecha180.png";
        } else if (angulo < -112.5 && angulo >= -157.5) {
            imagen = "../../imagenes/mapa/flechas/flecha225.png";
        } else if (angulo < -67.5 && angulo >= -112.5) {
            imagen = "../../imagenes/mapa/flechas/flecha270.png";
        } else if (angulo < -22.5 && angulo >= -67.5) {
            imagen = "../../imagenes/mapa/flechas/flecha315.png";
        } else {
            System.out.println("angulo " + angulo + " es invalido.");
        }
        return imagen;
    }

    public List<MovilTO> getListaMoviles() {
        return listaMoviles;
    }

    public void setListaMoviles(List<MovilTO> listaMoviles) {
        this.listaMoviles = listaMoviles;
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

    public List<VistaMovilHistoricoTO> getListaMovilHistorico() {
        return listaMovilHistorico;
    }

    public void setListaMovilHistorico(List<VistaMovilHistoricoTO> listaMovilHistorico) {
        this.listaMovilHistorico = listaMovilHistorico;
    }

    public String getCodigomovil() {
        return codigomovil;
    }

    public void setCodigomovil(String codigomovil) {
        this.codigomovil = codigomovil;
    }

    public String getPintarMapa() {
        return pintarMapa;
    }

    public void setPintarMapa(String pintarMapa) {
        this.pintarMapa = pintarMapa;
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public boolean getPintarTabla() {
        return pintarTabla;
    }

    public void setPintarTabla(boolean pintarTabla) {
        this.pintarTabla = pintarTabla;
    }

    public MapModel getPolylineModel() {
        return polylineModel;
    }

    public List<VistaMovilHistoricoTO> getListaCrucesVisitados() {
        return listaCrucesVisitados;
    }

    public void setListaCrucesVisitados(List<VistaMovilHistoricoTO> listaCrucesVisitados) {
        this.listaCrucesVisitados = listaCrucesVisitados;
    }

    public List<CruceTO> getListaCrucesTotales() {
        return listaCrucesTotales;
    }

    public void setListaCrucesTotales(List<CruceTO> listaCrucesTotales) {
        this.listaCrucesTotales = listaCrucesTotales;
    }

    public void onPolylineSelect(OverlaySelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Polyline Selected", null));
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
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

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
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

    public String getCodigoPag() {
        return codigoPag;
    }

    public void setCodigoPag(String codigoPag) {
        this.codigoPag = codigoPag;
    }

    public boolean isVerMensaje1() {
        return verMensaje1;
    }

    public void setVerMensaje1(boolean verMensaje1) {
        this.verMensaje1 = verMensaje1;
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

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }
    
}
