/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.AtencionTareaTO;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.DetalleTareaTO;
import cl.auter.patron.to.RegionTO;
import cl.auter.patron.to.SolicitudTO;
import cl.auter.patron.to.TiempoRespuestaTO;
import cl.auter.patron.to.VistaApagadosUltMesTO;
import cl.auter.patron.to.VistaContEstadoTO;
import cl.auter.patron.to.VistaCruceTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.patron.to.VistaReclamoCoordinacionTO;
import cl.auter.patron.to.VistaSolicitudPendienteTO;
import cl.auter.patron.to.VistaSolicitudTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author drobles
 */
@Named(value = DashboardBacking.BEAN_NAME)
@ViewScoped
public class DashboardBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "dashboardBacking";
    VistaContEstadoTO contEstDimac = new VistaContEstadoTO();
    List<VistaSolicitudTO> listaSolicitudesPendientes = new ArrayList();
    List<VistaSolicitudPendienteTO> solPendientesTotal = new ArrayList();
    List<VistaApagadosUltMesTO> listaApagadosMes = new ArrayList();
    PieChartModel solPendComChart;
    HorizontalBarChartModel apagadosMesChart = new HorizontalBarChartModel();
    BarChartModel barModel;
    DonutChartModel donutModel1;
    List<VistaCruceTO> listaPuntos = new ArrayList();
    String centro = "";
    List<VistaCruceTO> listaDetalleEstado = new ArrayList();
    private MapModel marcadores = new DefaultMapModel();
    List<TiempoRespuestaTO> listaTiempoRespuesta = new ArrayList();
    //popup
    private Marker marker = null;
    private boolean mostrarInfoConf = true;
    private boolean verInfMonitoreo = false;

    private String titulo = "";
    private boolean verFiltroBusqueda = true;
    private boolean verFiltroRegion = false;
    private List<ComunaTO> listaComunasMonitoreo = new ArrayList();
    List<RegionTO> regiones = new ArrayList();
    private VistaCruceTO idCruce;
    private String tamanoMapa = "12";
    String panelStyle = "without-selectall";
    private boolean fitBounds = false;
    private List<CodigoTO> listaEstadoOperativo = new ArrayList();

    //atenciones
    boolean verAtencionesTareaReclamo = false;
    boolean verPopupRechazar = false;
    boolean verPopupAceptar = false;
    boolean verPopupReasignar = false;
    boolean verPopupTab = false;
    List<AtencionTareaTO> listaAtencionTareaTO = new ArrayList();
    private SolicitudTO solicitudTO;
    DetalleTareaTO detalleTareaTO = new DetalleTareaTO();
    List<VistaReclamoCoordinacionTO> listaReclamosHistoricos = new ArrayList();
    private BigDecimal idTareaReclamoSeleccionado;
    private CodigoTO[] tipoInstalacion;
    private List<VistaInstalacionTO> listaPuntosInstalacion = new ArrayList();
    private boolean comboHabilitada = false;
    private List<SolicitudTO> listaSolicitudes = new ArrayList();
    BigDecimal idSeleccionado;
    private String convertClob = new String();

    @PostConstruct
    public void init() {
        listaEstadoOperativo = obtenerEstadoOperativo();

        List<CodigoTO> listaRem = new ArrayList();
        int i = 0;
        for (CodigoTO cod : listaEstadoOperativo) {
            if (i > 3) {
                listaRem.add(cod);
            }
            i++;
        }
        listaEstadoOperativo.removeAll(listaRem);
        cargaEstadosOperacionalesDash();

        //boolean[] check2 = {true, true, true, true, true, true, true, true};
        //setCheck1(check2);
        //busca estadisticas
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        validarPagina("211");
        regiones = getLocalizacionFacadeLocal().listaRegionTodas();
        RegionTO regionAux = new RegionTO();
        regionAux.setId_region(999);
        regionAux.setNombre("Todas");
        regiones.add(regionAux);

        if (tipoRol.equals(new BigDecimal("240"))) //usuario auter
        {
            panelStyle = "";
            verFiltroRegion = true;
            listaSolicitudesPendientes = getLocalizacionFacadeLocal().obtenerSolPend();
            verInfMonitoreo = true;
            listaApagadosMes = getLocalizacionFacadeLocal().listaApagadosMesTodos();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            try {
                listaPuntos = getLocalizacionFacadeLocal().listaVistaCruces();
               // listaTiempoRespuesta = getLocalizacionFacadeLocal().obtenerTiempoRespuesta(cal.getTime(), new Date());
            } catch (Exception ex) {
                Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            solPendientesTotal = getLocalizacionFacadeLocal().obtenerSolPendTodas();
            contEstDimac = getLocalizacionFacadeLocal().obtenerEstadosDimacTodos();
        } else if (tipoRol.equals(new BigDecimal("241"))) {
            try {
                //Municipal
                setVerBuscarCruce(true);
                BigDecimal[] big = new BigDecimal[1];
                big[0] = usurioAutenticado().getIdComuna();
                setComunaSeleccionadas(big);
                verFiltroBusqueda = false;
                listaPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(usurioAutenticado().getIdComuna());
                listaSolicitudesPendientes = getLocalizacionFacadeLocal().obtenerSolPendPorComuna(usurioAutenticado().getIdComuna());
                contEstDimac = getLocalizacionFacadeLocal().obtenerEstadosDimacPorComuna(usurioAutenticado().getIdComuna());
            } catch (Exception ex) {
                Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //UOCT
            listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT();
            for (ComunaTO comuna : getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT()) {
                try {
                    listaPuntos.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(comuna.getIdComuna()));
                    listaSolicitudesPendientes.addAll(getLocalizacionFacadeLocal().obtenerSolPendPorComuna(comuna.getIdComuna()));
                    VistaContEstadoTO aux = getLocalizacionFacadeLocal().obtenerEstadosDimacPorComuna(comuna.getIdComuna());
                    contEstDimac.setEncendidos(contEstDimac.getEncendidos() + aux.getEncendidos());
                    contEstDimac.setApagado(contEstDimac.getApagado() + aux.getApagado());
                    contEstDimac.setObservacion(contEstDimac.getObservacion() + aux.getObservacion());
                    contEstDimac.setInvalidos(contEstDimac.getInvalidos() + aux.getInvalidos());
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
        cargarComunas();
        //crearChartPend();
        crearApagadosMesChart();
        //createDonutModels();
        crearMapa();
        //refresco();
    }

    public void crearMapa() {
        for (VistaCruceTO vistaCruceTO : listaPuntos) {
            try {
                if (vistaCruceTO.getLatitud() != null && vistaCruceTO.getLongitud() != null) {
                    LatLng coordenadas = new LatLng(Double.parseDouble(vistaCruceTO.getLatitud()), Double.parseDouble(vistaCruceTO.getLongitud()));
                    Marker marcador = new Marker(coordenadas, vistaCruceTO.getIdCruce().toString());
                    if (existeEstadoOperacional(vistaCruceTO.getEstadoOperativo())) {
                        marcador.setId(vistaCruceTO.getIdCruce().toString());

                        marcador.setIcon(vistaCruceTO.getImagenEstadoOperacional().substring(3, vistaCruceTO.getImagenEstadoOperacional().length()));
                        marcador.setData(vistaCruceTO);
                        marcador.setTitle(vistaCruceTO.getIdCruce().toString() + " - " + vistaCruceTO.getDescripcionEstadoOperativo());
                        if (vistaCruceTO.isIndLamFault() && Integer.parseInt(vistaCruceTO.getEstadoOperativo()) < 3 && vistaCruceTO.getValModoUtc4().equals(new BigDecimal("1"))) {
                            marcador.setTitle(vistaCruceTO.getIdCruce().toString() + " - " + "con Lámpara Fundida");
                        }

                    } else {
                        marcador.setIcon("../../imagenes/mapa/operativo/vacio.png");
                    }
                    marcadores.addOverlay(marcador);
                }
            } catch (Exception ex) {
                Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void cargarMapa() {
        cargarComunas();
        listaPuntos.clear();
        marcadores.getMarkers().clear();
        contEstDimac.setApagado(0);
        contEstDimac.setEncendidos(0);
        contEstDimac.setObservacion(0);
        contEstDimac.setInvalidos(0);
        if (getComunaSeleccionadas().length > 0) {
            for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                try {
                    List<VistaCruceTO> listaPuntosCom = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas);
                    this.listaPuntos.addAll(listaPuntosCom);
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if (usurioAutenticado().getTipo().equals(new BigDecimal("240"))) //usuario auter
            {
                try {
                    listaPuntos = getLocalizacionFacadeLocal().listaVistaCruces();
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (usurioAutenticado().getTipo().equals(new BigDecimal("241"))) {
                try {
                    listaPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(usurioAutenticado().getIdComuna());
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { //UOCT
                for (ComunaTO comuna : getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT()) {
                    try {
                        listaPuntos.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(comuna.getIdComuna()));
                    } catch (Exception ex) {
                        Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        fitBounds = true;
        for (VistaCruceTO vistaCruceTO : listaPuntos) {
            try {
                if (vistaCruceTO.getLatitud() != null && vistaCruceTO.getLongitud() != null) {
                    LatLng coordenadas = new LatLng(Double.parseDouble(vistaCruceTO.getLatitud()), Double.parseDouble(vistaCruceTO.getLongitud()));
                    Marker marcador = new Marker(coordenadas, vistaCruceTO.getIdCruce().toString());
                    if (existeEstadoOperacional(vistaCruceTO.getEstadoOperativo())) {
                        marcador.setId(vistaCruceTO.getIdCruce().toString());
                        marcador.setIcon(vistaCruceTO.getImagenEstadoOperacional().substring(3, vistaCruceTO.getImagenEstadoOperacional().length()));
                        marcador.setData(vistaCruceTO);
                        marcador.setTitle(vistaCruceTO.getIdCruce().toString() + " - " + vistaCruceTO.getDescripcionEstadoOperativo());
                        if (vistaCruceTO.isIndLamFault() && Integer.parseInt(vistaCruceTO.getEstadoOperativo()) < 3 && vistaCruceTO.getValModoUtc4().equals(new BigDecimal("1"))) {
                            marcador.setTitle(vistaCruceTO.getIdCruce().toString() + " - " + "con Lámpara Fundida");
                        }
                    } else {
                        marcador.setIcon("../../imagenes/mapa/operativo/vacio.png");
                    }
                    marcadores.addOverlay(marcador);
                    switch (vistaCruceTO.getEstadoOperativoInt()) {
                        case 1:
                            contEstDimac.setEncendidos(contEstDimac.getEncendidos() + 1);
                            break;
                        case 2:
                            contEstDimac.setObservacion(contEstDimac.getObservacion() + 1);
                            break;
                        case 3:
                            contEstDimac.setApagado(contEstDimac.getApagado() + 1);
                            break;
                        case 4:
                            contEstDimac.setInvalidos(contEstDimac.getInvalidos() + 1);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        refresco();
    }

    public void refresco() {
        PrimeRequestContext.getCurrentInstance().getCallbackParams().clear();
        if (existeComuna()) {
            contEstDimac.setApagado(0);
            contEstDimac.setEncendidos(0);
            contEstDimac.setObservacion(0);
            contEstDimac.setInvalidos(0);
            for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                try {
                    List<VistaCruceTO> listaNuevosPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas);
                    //Para los cruces 
                    MapModel marcadoresAuxiliar = marcadores;
                    for (VistaCruceTO vistaCruceTO : listaNuevosPuntos) {
                        for (int i = 0; i < marcadores.getMarkers().size(); i++) {
                            if (vistaCruceTO.getIdCruce().toString().equals(marcadores.getMarkers().get(i).getTitle().split(" - ")[0].trim())) {
                                if (existeEstadoOperacional(vistaCruceTO.getEstadoOperativo())) {
                                    marcadoresAuxiliar.getMarkers().get(i).setIcon(vistaCruceTO.getImagenEstadoOperacional().substring(3, vistaCruceTO.getImagenEstadoOperacional().length()));
                                    marcadoresAuxiliar.getMarkers().get(i).setTitle(vistaCruceTO.getIdCruce().toString() + " - " + vistaCruceTO.getDescripcionEstadoOperativo());
                                    if (vistaCruceTO.isIndLamFault() && Integer.parseInt(vistaCruceTO.getEstadoOperativo()) < 3 && vistaCruceTO.getValModoUtc4().equals(new BigDecimal("1"))) {
                                        marcadoresAuxiliar.getMarkers().get(i).setTitle(vistaCruceTO.getIdCruce().toString() + " - " + "con Lámpara Fundida");
                                    }
                                } else {
                                    marcadoresAuxiliar.getMarkers().get(i).setIcon("../imagenes/mapa/operativo/vacio.png");
                                }

                                marcadoresAuxiliar.getMarkers().get(i).setData(vistaCruceTO);
                                PrimeFaces.current().ajax().addCallbackParam("marker" + i, marcadores.getMarkers().get(i));
                                PrimeFaces.current().ajax().addCallbackParam("position" + i, marcadores.getMarkers().get(i).getLatlng());
                                break;
                            }
                        }
                        switch (vistaCruceTO.getEstadoOperativoInt()) {
                            case 1:
                                contEstDimac.setEncendidos(contEstDimac.getEncendidos() + 1);
                                break;
                            case 2:
                                contEstDimac.setObservacion(contEstDimac.getObservacion() + 1);
                                break;
                            case 3:
                                contEstDimac.setApagado(contEstDimac.getApagado() + 1);
                                break;
                            case 4:
                                contEstDimac.setInvalidos(contEstDimac.getInvalidos() + 1);
                                break;
                            default:
                                break;
                        }
                    }
                    marcadores = marcadoresAuxiliar;
                    listaNuevosPuntos.clear();

                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean existeEstadoOperacional(String estado) {
        for (String estadoOpe : getListaChequeadosDash()) {
            if (estadoOpe.equals(estado)) {
                return true;
            }
        }
        return false;
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

    public void verAtencionesReclamo() {
        listaAtencionTareaTO.clear();
       /* if (idTareaReclamoSeleccionado != null) {
            listaAtencionTareaTO.addAll(getLocalizacionFacadeLocal().obtenerAtencionTarea(idTareaReclamoSeleccionado));
        } */
        verAtencionesTareaReclamo = true;
    }

   /*  public void popupIdSolicitud() {
        try {            
            solicitudTO = getLocalizacionFacadeLocal().buscarSolicitudPorId(idSeleccionado);
            listaReclamosHistoricos.clear();
            if (solicitudTO.getNumReclamo() != null) {
                solicitudTO.setReclamo(getEnlaceFacadeLocal().obtenerReclamoPorId(solicitudTO.getNumReclamo().intValue()));
                if (solicitudTO.getReclamo() != null && solicitudTO.getReclamo().getIdEnlace() != null) {
                    solicitudTO.getReclamo().setDescEstado(getEnlaceFacadeLocal().buscarDominioCodigo("ESTADO_RECLAMO", String.valueOf(solicitudTO.getReclamo().getIdEstado())).getCodigo2());
                    solicitudTO.getReclamo().setEnlace(getEnlaceFacadeLocal().buscarEnlacePorId(solicitudTO.getReclamo().getIdEnlace()));
                    try {
                        solicitudTO.getReclamo().getEnlace().setCruce(getLocalizacionFacadeLocal().buscarCrucePorId(new BigDecimal(solicitudTO.getReclamo().getEnlace().getIdCruce())));
                        solicitudTO.getReclamo().setComuna(getLocalizacionFacadeLocal().buscaComunaPorId(solicitudTO.getReclamo().getEnlace().getCruce().getIdComuna()));
                    } catch (Exception ex) {
                        Logger.getLogger(AtencionesBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    solicitudTO.getReclamo().setDescProveedor(getEnlaceFacadeLocal().buscarDominioCodigo("PROVEEDOR", String.valueOf(solicitudTO.getReclamo().getEnlace().getIdProveedor())).getCodigo2());
                    listaReclamosHistoricos = getEnlaceFacadeLocal().buscarCoordReclamoPorIdRec(solicitudTO.getReclamo().getIdReclamo());
                    solicitudTO.getListaTareaReclamo().clear();
                    for (VistaReclamoCoordinacionTO hist : listaReclamosHistoricos) {
                        
                        if (hist.getIdTarea() != null && hist.getIdTarea() > 0) {
                            if (!solicitudTO.getListaTareaReclamo().contains(getSmaFacadeLocal().obtenerTareaWeb(hist.getIdTarea()))) {
                                
                                solicitudTO.getListaTareaReclamo().add(getSmaFacadeLocal().obtenerTareaWeb(hist.getIdTarea()));
                            }
                            
                        }
                    }
                }
                
            }
            if (!solicitudTO.getListaTareaReclamo().isEmpty()) {
                solicitudTO.setVerTareaReclamo(true);
            }
            if (solicitudTO.getUrlAdjunto() != null) {
                solicitudTO.setVerDescargaAdjunto(true);
            }
            try {
                solicitudTO.setDescripcionObservacion(convertClob = clobToString(solicitudTO.getObservacion()));
                if (!solicitudTO.getNumTareaAsma().equals(BigDecimal.ZERO)) {
                    detalleTareaTO = getLocalizacionFacadeLocal().obtenerDetalleTarea(solicitudTO.getNumTareaAsma());
                    listaAtencionTareaTO = getLocalizacionFacadeLocal().obtenerAtencionTarea(solicitudTO.getNumTareaAsma());
                }
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */

    public String clobToString(Clob campo) {
        try {
            return campo.getSubString(1, (int) campo.length());
        } catch (SQLException ex) {
            Logger.getLogger(AtencionesBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void detalleSolicitudPopup() {
        verPopupAceptar = true;
        convertClob = "";
        for (SolicitudTO solicitudIterTO : listaSolicitudes) {
            if (solicitudIterTO.getIdSolicitud() == idSeleccionado) {
                solicitudTO = solicitudIterTO;
            }
        }
        int c = -1;
        try {
            InputStream in = solicitudTO.getObservacion().getAsciiStream();
            Reader read = new InputStreamReader(in);
            StringWriter write = new StringWriter();
            while ((c = read.read()) != -1) {
                write.write(c);
            }
            write.flush();
            convertClob = write.toString();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(AtencionesBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processChecked(CodigoTO codigoTo) {
        boolean agregar = true;
        int indice = 0;

        for (String chequeado : getListaChequeadosDash()) {
            if (chequeado.equals(codigoTo.getCodigo1())) {
                getListaChequeadosDash().remove(indice);
                agregar = false;
                break;
            }
            indice++;
        }
        if (agregar) {
            getListaChequeadosDash().add(codigoTo.getCodigo1());
        }
        //cargarMapa();
        refresco();
    }

    private void createDonutModels() {
        donutModel1 = new DonutChartModel();
        // donutModel1.setTitle("Solicitudes pendientes por comuna");
        donutModel1.setSliceMargin(5);
        donutModel1.setExtender("removeLegend");
        donutModel1.setDataLabelFormatString("%s");
        donutModel1.setShowDataLabels(true);
        donutModel1.setDataFormat("value");
        donutModel1.setShadow(false);
        Map<String, Number> circle1 = new LinkedHashMap();
        for (VistaSolicitudPendienteTO sol : solPendientesTotal) {
            circle1.put(sol.getNombre_comuna(), sol.getNum_sol());

        }
        donutModel1.addCircle(circle1);
    }

    public void warn() {
        FacesContext.getCurrentInstance().addMessage("pnl1", new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Esta vista es estática, debe refrescar la pantalla."));
        FacesContext.getCurrentInstance().addMessage("gMapWV", new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", "Este mapa es estático, debe ir al menú <a href='/semaforos/paginas/online/mapaPrime.xhtml'>Semaforo/Mapa Online</a> para tener una vista que se actualiza automáticamente."));

    }

    private void crearChartPend() {
       /* solPendComChart = new PieChartModel();

        for (VistaSolicitudPendienteTO sol : solPendientesTotal) {
            solPendComChart.set(sol.getNombre_comuna(), sol.getNum_sol());
        }

        solPendComChart.setTitle("Solicitudes pendientes");
        solPendComChart.setLegendPosition("w");
        solPendComChart.setExtender("skinPie"); */
    }

    public void cargaDetalleCruces(String estado) {
        listaDetalleEstado.clear();
        List<String> listaEstado = new ArrayList();

        if (estado.equals("1")) {
            titulo = "Cruces encendidos";
        } else if (estado.equals("2")) {
            titulo = "Cruces con Observaciones";
        } else if (estado.equals("3")) {
            titulo = "Cruces apagados";
        } else {
            titulo = "Cruces inválidos";
        }

        listaEstado.add(estado);
        if (getComunaSeleccionadas().length == 0) {
            switch (usurioAutenticado().getTipoInt()) {
                case 240:
                    listaDetalleEstado.addAll(getLocalizacionFacadeLocal().buscarPorEstado(estado));
                    break;
                case 241:
                    try {
                    listaDetalleEstado.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(usurioAutenticado().getIdComuna(), listaEstado));
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                default:
                    for (ComunaTO com : getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT()) {
                        try {
                            listaDetalleEstado.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(com.getIdComuna(), listaEstado));
                        } catch (Exception ex) {
                            Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
            }

        } else {
            for (BigDecimal idcomuna : getComunaSeleccionadas()) {
                try {
                    listaDetalleEstado.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idcomuna, listaEstado));
                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void crearApagadosMesChart() {
        ChartSeries cc = new ChartSeries();
        cc.setLabel("");
        for (int i = listaApagadosMes.size() - 1; i >= 0; i--) {
            cc.set(listaApagadosMes.get(i).getCity_name(), listaApagadosMes.get(i).getNum_turn_off());
        }
        //cc.setData(groupData);
        apagadosMesChart.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,800080,000080,0000FF,008080,008000,A30303");
        apagadosMesChart.addSeries(cc);
        apagadosMesChart.setDatatipFormat("%s");
        apagadosMesChart.setTitle("");
        apagadosMesChart.setLegendPosition("e");
        apagadosMesChart.setStacked(false);
        apagadosMesChart.setAnimate(true);
        apagadosMesChart.setExtender("skinApa");
        Axis xAxis = apagadosMesChart.getAxis(AxisType.X);
        xAxis.setLabel("Cantidad ");

        Axis yAxis = apagadosMesChart.getAxis(AxisType.Y);
        yAxis.setLabel("Comuna");
    }

    public void onRegionChange(ValueChangeEvent e) {
        if (!e.getNewValue().toString().equals("0")) //Ninguna
        {
            setComunaSeleccionadas(new BigDecimal[0]);
            listaComunasMonitoreo.clear();
            if (e.getNewValue().toString().equals("999")) {
                try {
                    listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreo();

                } catch (Exception ex) {
                    Logger.getLogger(DashboardBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                listaComunasMonitoreo = getLocalizacionFacadeLocal().buscarComunasPorRegion(Integer.parseInt(e.getNewValue().toString()));
            }
            if (listaComunasMonitoreo.size() > 1) {
                ComunaTO comunaDummy = new ComunaTO();
                comunaDummy.setIdComuna(new BigDecimal("999"));
                comunaDummy.setIdRegion(new BigDecimal("0"));
                comunaDummy.setNombre("TODAS");
                listaComunasMonitoreo.add(comunaDummy);
            }
        }

    }

    public List<VistaCruceTO> filtroCruce(String calle) {
        List<VistaCruceTO> listaCruce = new ArrayList();
        listaCruce.clear();
        if (calle != null && !calle.equals("")) {
            for (VistaCruceTO vistaCruceTO : listaPuntos) {
                if (vistaCruceTO.getUbicacion().toUpperCase().contains(calle.toUpperCase())) {
                    listaCruce.add(vistaCruceTO);
                }
            }
        }

        return listaCruce;
    }

    public void cambioValorAutoComplete(SelectEvent seleccionado) {
        fitBounds = false;
        zoomCruce(((VistaCruceTO) seleccionado.getObject()).getIdCruce());
    }

    public void zoomCruce(BigDecimal idCruce) {
        for (VistaCruceTO vistaCruceTO : listaPuntos) {
            if (vistaCruceTO.getIdCruce().equals(idCruce)) {
                this.tamanoMapa = "18";
                this.centro = vistaCruceTO.getLatitud() + "," + vistaCruceTO.getLongitud();
                break;
            }
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {

        this.marker = (Marker) event.getOverlay();

    }

    public void onToggleSelect(ToggleSelectEvent e) {

    }

    public void buscar() {
        if (this.idCruce != null && idCruce != null && idCruce.getUbicacion() != null && !this.idCruce.getUbicacion().equals("")) {
            fitBounds = false;
            zoomCruce(this.idCruce.getIdCruce());
        }
    }

    public VistaContEstadoTO getContEstDimac() {
        return contEstDimac;
    }

    public void setContEstDimac(VistaContEstadoTO contEstDimac) {
        this.contEstDimac = contEstDimac;
    }

    public List<VistaSolicitudTO> getListaSolicitudesPendientes() {
        return listaSolicitudesPendientes;
    }

    public void setListaSolicitudesPendientes(List<VistaSolicitudTO> listaSolicitudesPendientes) {
        this.listaSolicitudesPendientes = listaSolicitudesPendientes;
    }

    public List<VistaSolicitudPendienteTO> getSolPendientesTotal() {
        return solPendientesTotal;
    }

    public void setSolPendientesTotal(List<VistaSolicitudPendienteTO> solPendientesTotal) {
        this.solPendientesTotal = solPendientesTotal;
    }

    public PieChartModel getSolPendComChart() {
        return solPendComChart;
    }

    public void setSolPendComChart(PieChartModel solPendComChart) {
        this.solPendComChart = solPendComChart;
    }

    public HorizontalBarChartModel getApagadosMesChart() {
        return apagadosMesChart;
    }

    public void setApagadosMesChart(HorizontalBarChartModel apagadosMesChart) {
        this.apagadosMesChart = apagadosMesChart;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public DonutChartModel getDonutModel1() {
        return donutModel1;
    }

    public void setDonutModel1(DonutChartModel donutModel1) {
        this.donutModel1 = donutModel1;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public MapModel getMarcadores() {
        return marcadores;
    }

    public void setMarcadores(MapModel marcadores) {
        this.marcadores = marcadores;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public boolean isMostrarInfoConf() {
        return mostrarInfoConf;
    }

    public void setMostrarInfoConf(boolean mostrarInfoConf) {
        this.mostrarInfoConf = mostrarInfoConf;
    }

    public boolean isVerInfMonitoreo() {
        return verInfMonitoreo;
    }

    public void setVerInfMonitoreo(boolean verInfMonitoreo) {
        this.verInfMonitoreo = verInfMonitoreo;
    }

    public List<VistaCruceTO> getListaDetalleEstado() {
        return listaDetalleEstado;
    }

    public void setListaDetalleEstado(List<VistaCruceTO> listaDetalleEstado) {
        this.listaDetalleEstado = listaDetalleEstado;
    }

    public List<TiempoRespuestaTO> getListaTiempoRespuesta() {
        return listaTiempoRespuesta;
    }

    public void setListaTiempoRespuesta(List<TiempoRespuestaTO> listaTiempoRespuesta) {
        this.listaTiempoRespuesta = listaTiempoRespuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isVerFiltroBusqueda() {
        return verFiltroBusqueda;
    }

    public void setVerFiltroBusqueda(boolean verFiltroBusqueda) {
        this.verFiltroBusqueda = verFiltroBusqueda;
    }

    public boolean isVerFiltroRegion() {
        return verFiltroRegion;
    }

    public void setVerFiltroRegion(boolean verFiltroRegion) {
        this.verFiltroRegion = verFiltroRegion;
    }

    public List<ComunaTO> getListaComunasMonitoreo() {
        return listaComunasMonitoreo;
    }

    public void setListaComunasMonitoreo(List<ComunaTO> listaComunasMonitoreo) {
        this.listaComunasMonitoreo = listaComunasMonitoreo;
    }

    public List<RegionTO> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<RegionTO> regiones) {
        this.regiones = regiones;
    }

    public VistaCruceTO getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(VistaCruceTO idCruce) {
        this.idCruce = idCruce;
    }

    public String getTamanoMapa() {
        return tamanoMapa;
    }

    public void setTamanoMapa(String tamanoMapa) {
        this.tamanoMapa = tamanoMapa;
    }

    public String getPanelStyle() {
        return panelStyle;
    }

    public void setPanelStyle(String panelStyle) {
        this.panelStyle = panelStyle;
    }

    public List<VistaCruceTO> getListaPuntos() {
        return listaPuntos;
    }

    public void setListaPuntos(List<VistaCruceTO> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public boolean isFitBounds() {
        return fitBounds;
    }

    public void setFitBounds(boolean fitBounds) {
        this.fitBounds = fitBounds;
    }

    public List<CodigoTO> getListaEstadoOperativo() {
        return listaEstadoOperativo;
    }

    public void setListaEstadoOperativo(List<CodigoTO> listaEstadoOperativo) {
        this.listaEstadoOperativo = listaEstadoOperativo;
    }

    public boolean isVerAtencionesTareaReclamo() {
        return verAtencionesTareaReclamo;
    }

    public void setVerAtencionesTareaReclamo(boolean verAtencionesTareaReclamo) {
        this.verAtencionesTareaReclamo = verAtencionesTareaReclamo;
    }

    public List<AtencionTareaTO> getListaAtencionTareaTO() {
        return listaAtencionTareaTO;
    }

    public void setListaAtencionTareaTO(List<AtencionTareaTO> listaAtencionTareaTO) {
        this.listaAtencionTareaTO = listaAtencionTareaTO;
    }

    public boolean isVerPopupRechazar() {
        return verPopupRechazar;
    }

    public void setVerPopupRechazar(boolean verPopupRechazar) {
        this.verPopupRechazar = verPopupRechazar;
    }

    public boolean isVerPopupAceptar() {
        return verPopupAceptar;
    }

    public void setVerPopupAceptar(boolean verPopupAceptar) {
        this.verPopupAceptar = verPopupAceptar;
    }

    public boolean isVerPopupReasignar() {
        return verPopupReasignar;
    }

    public void setVerPopupReasignar(boolean verPopupReasignar) {
        this.verPopupReasignar = verPopupReasignar;
    }

    public boolean isVerPopupTab() {
        return verPopupTab;
    }

    public void setVerPopupTab(boolean verPopupTab) {
        this.verPopupTab = verPopupTab;
    }

    public SolicitudTO getSolicitudTO() {
        return solicitudTO;
    }

    public void setSolicitudTO(SolicitudTO solicitudTO) {
        this.solicitudTO = solicitudTO;
    }

    public DetalleTareaTO getDetalleTareaTO() {
        return detalleTareaTO;
    }

    public void setDetalleTareaTO(DetalleTareaTO detalleTareaTO) {
        this.detalleTareaTO = detalleTareaTO;
    }

    public List<VistaReclamoCoordinacionTO> getListaReclamosHistoricos() {
        return listaReclamosHistoricos;
    }

    public void setListaReclamosHistoricos(List<VistaReclamoCoordinacionTO> listaReclamosHistoricos) {
        this.listaReclamosHistoricos = listaReclamosHistoricos;
    }

    public BigDecimal getIdTareaReclamoSeleccionado() {
        return idTareaReclamoSeleccionado;
    }

    public void setIdTareaReclamoSeleccionado(BigDecimal idTareaReclamoSeleccionado) {
        this.idTareaReclamoSeleccionado = idTareaReclamoSeleccionado;
    }

    public CodigoTO[] getTipoInstalacion() {
        return tipoInstalacion;
    }

    public void setTipoInstalacion(CodigoTO[] tipoInstalacion) {
        this.tipoInstalacion = tipoInstalacion;
    }

    public List<VistaInstalacionTO> getListaPuntosInstalacion() {
        return listaPuntosInstalacion;
    }

    public void setListaPuntosInstalacion(List<VistaInstalacionTO> listaPuntosInstalacion) {
        this.listaPuntosInstalacion = listaPuntosInstalacion;
    }

    public boolean isComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(boolean comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public List<SolicitudTO> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<SolicitudTO> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public BigDecimal getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(BigDecimal idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

}
