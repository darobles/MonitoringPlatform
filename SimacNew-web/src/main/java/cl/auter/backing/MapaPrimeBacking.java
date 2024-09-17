package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.MovilTO;
import cl.auter.patron.to.RegionTO;
import cl.auter.patron.to.VRutaTiempoTO;
import cl.auter.patron.to.VistaCruceTO;
import cl.auter.patron.to.VistaMovilTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

/**
 * Esta Clase permite poder visualizar un mapa con las instalaciones y
 * camionetas que AUTER tiene Es parte del conjunto de clases que pertenence al
 * Modelo MVC de JSF
 *
 * @author Rodrigo Baeza
 */
@Named(value = MapaPrimeBacking.BEAN_NAME)
@ViewScoped
public class MapaPrimeBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "mapaPrimeBacking";
    //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
    private String codigoPag = "211";
    private String centro;
    private String tamanoMapa = "12";
    private VistaCruceTO idCruce;
    private MapModel marcadores = new DefaultMapModel();
    private String pintarMapa;
    private Marker marker = null;
    private Polyline polyline = null;
    private List<VistaCruceTO> listaPuntos = new ArrayList();
    private List<ComunaTO> listaComunasMonitoreo = new ArrayList();
    private boolean accesoTerminal = false;
    private String poliBool = "false";
    private String bg_uoct = "#e8eaf6";
    private boolean accesoPPR = false;
    private int auxCabecera = 0;
    private int contPoly = 0;
    private String fecha_MatrizMacro;
    private boolean ver_moviles = false;
    //private List<CodigoTO> listaEstOperativo = new ArrayList(); //Todos los elementos; dimac, cruce, tdv
    private List<CodigoTO> listaEstOpeDimac = new ArrayList(); //Solo dimac
    private int numColsMenu = 0;
    private boolean verControladorRemoto = false;
    private boolean verInfMonitoreo = false;
    private String styleBackground = "";
    private boolean mostrarInfoConf = true;
    List<String> listaEstado = new ArrayList();
    List<RegionTO> regiones = new ArrayList();
    RegionTO region = new RegionTO();
    boolean verFiltroRegion = false;
    String panelStyle = "without-selectall";
    

    private List<CodigoTO> listaEstadoOperativo = new ArrayList();

    public void init() {
        try {
            listaEstadoOperativo = obtenerEstadoOperativo();
            boolean[] check2 = {true, true, true, true, true, true, true, true};
            setCheck1(check2);
            idCruce = null;
            BigDecimal tipoRol = usurioAutenticado().getTipo();

            if (tipoRol.equals(new BigDecimal("242"))) //Usuario UOCT
            {
                listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT();
                ComunaTO comunaDummy = new ComunaTO();
                comunaDummy.setIdComuna(new BigDecimal("999"));
                comunaDummy.setIdRegion(new BigDecimal("0"));
                comunaDummy.setNombre("TODAS");
                listaComunasMonitoreo.add(comunaDummy);
                
                bg_uoct = "transparent";
                styleBackground = "url(../../imagenes/Watermark2.png)";
                mostrarInfoConf = false;
                verFiltroRegion = true;
                //setVerBuscarCruce(true);
            } else {
                if(!tipoRol.equals(new BigDecimal("240"))) //con region, distinto usuario auter
                {
                    listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreo();
                     setVerBuscarCruce(true); 
                }
                else{
                    verInfMonitoreo = true;
                    verFiltroRegion = true;
                }
            }
            listaPuntos.clear();
            for (CodigoTO cod : listaEstadoOperativo) {
                if (Integer.parseInt(cod.getCodigo1()) < 5) {
                    listaEstOpeDimac.add(cod);
                }
            }
            regiones = getLocalizacionFacadeLocal().listaRegionTodas();
            RegionTO regionAux = new RegionTO();
            regionAux.setId_region(999);
            regionAux.setNombre("Todas");
            regiones.add(regionAux);
            getListaChequeados().clear();
            cargaEstadosOperacionales();
            getListaComunaSeleccionadas().clear();
            setLstComunaSeleccionada("");
            setComunaSeleccionadas(null);
            if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("298")) {
                ver_moviles = true;
            } else {
                //remueve de la lista de los chequeados tdv.
                CodigoTO remv = new CodigoTO();
                for (CodigoTO codigo : listaEstadoOperativo) {
                    if (codigo.getIdCodigo().equals(new BigDecimal("265"))) {
                        remv = codigo;
                        getListaChequeados().remove(codigo.getCodigo1());

                    }
                }
                listaEstadoOperativo.remove(remv);
            }

            if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("264")) {
               // ver_tdv = "true";
            } else {
                //remueve de la lista de los chequeados tdv.
                CodigoTO remv = new CodigoTO();
                for (CodigoTO codigo : listaEstadoOperativo) {
                    if (codigo.getIdCodigo().equals(new BigDecimal("260"))) {
                        remv = codigo;
                        getListaChequeados().remove(codigo.getCodigo1());
                        break;
                    }
                }
                listaEstadoOperativo.remove(remv);
            }
            numColsMenu = listaEstadoOperativo.size();
            if (!tipoRol.equals(new BigDecimal("241"))) {
                this.pintarMapa = "false";
                setPintarComuna("true");
            } else {
                this.pintarMapa = "true";
                setVerBuscarCruce(true);
                setPintarComuna("false");
                agregaComunaUsuario();
                cargarComunas();
                this.generacionMapa();
            }

            for (CodigoTO codigo : listaEstadoOperativo) {
                if (Integer.parseInt(codigo.getCodigo1()) <= 4) {
                    listaEstado.add(codigo.getCodigo1());
                } else if (Integer.parseInt(codigo.getCodigo1()) == 5) {
                    if (ver_moviles) {
                        listaEstado.add(codigo.getCodigo1());
                    }
                } 

            }
            this.poliBool = pintarMapa;
            try {
                if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("251")) {
                    accesoTerminal = true;
                }
                if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("263")) {
                    accesoPPR = true;
                }
            } catch (Exception ex) {
                Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Lista Est ");
            for(CodigoTO cod: listaEstadoOperativo)
            {
                System.out.println("Cod " + cod.toString());
            }
            this.refresco();
        } catch (Exception e) {
            System.out.println(e);
            redirectIndex();
        }
        
    }

    @PostConstruct
    public void carga() {
        try {
            centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
            idCruce = null;
            intervalo();
            BigDecimal tipoRol = usurioAutenticado().getTipo();
            validarPagina(codigoPag);
            cargaEstadosOperacionales();
            if (!tipoRol.equals(new BigDecimal("241"))) {
                this.pintarMapa = "false";
                setPintarComuna("true");
                if(tipoRol.equals(new BigDecimal("240")))
                {
                    panelStyle = "";
                }
                    
            } else {
                this.pintarMapa = "true";
                setPintarComuna("false");
                setVerBuscarCruce(true);  
                agregaComunaUsuario();
                cargarComunas();
                this.generacionMapa();
            }
            
        } catch (Exception e) {
             System.out.println("Error " + e);
            redirectIndex();
        }
      //  PrimeFaces.current().ajax().update("rmt"); 
    }

    public List<CodigoTO> buscaDominio(String dominio) {
        List<CodigoTO> listaCodigos = new ArrayList<>();
        try {
            listaCodigos = getParametrosFacadeLocal().listaPorDominio(dominio);
            return listaCodigos;
        } catch (Exception ex) {
            Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCodigos;
    }

    /**
     * Metodo para trabajar refresco automatico y carga de nuevos marcadores
     *
     */
    public void refresco() {
        int indice = 0;
        PrimeRequestContext.getCurrentInstance().getCallbackParams().clear();
        if (pintarMapa.equals("true") && marcadores != null) {
            if (existeComuna()) {
                for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                    try {

                        List<VistaCruceTO> listaNuevosPuntos = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas, listaEstado);
                        //Para los cruces
                        MapModel marcadoresAuxiliar = marcadores;
                        for (VistaCruceTO vistaCruceTO : listaNuevosPuntos) {
                            for (int i = 0; i < marcadores.getMarkers().size(); i++) {
                                if (vistaCruceTO.getIdCruce().toString().equals(marcadores.getMarkers().get(i).getTitle().split(" - ")[0].trim())) {
                                    if (existeEstadoOperacional(vistaCruceTO.getEstadoOperativo())) {
                                        marcadoresAuxiliar.getMarkers().get(i).setIcon(vistaCruceTO.getImagenEstadoOperacional());
                                        marcadoresAuxiliar.getMarkers().get(i).setTitle(vistaCruceTO.getIdCruce().toString() + " - " + vistaCruceTO.getDescripcionEstadoOperativo());
                                        if (vistaCruceTO.isIndLamFault() && Integer.parseInt(vistaCruceTO.getEstadoOperativo()) < 3 && vistaCruceTO.getValModoUtc4().equals(new BigDecimal("1"))) {
                                            marcadoresAuxiliar.getMarkers().get(i).setTitle(vistaCruceTO.getIdCruce().toString() + " - " + "con Lámpara Fundida");
                                        }
                                    } else {
                                        marcadoresAuxiliar.getMarkers().get(i).setIcon("../../imagenes/mapa/operativo/vacio.png");
                                    }

                                    marcadoresAuxiliar.getMarkers().get(i).setData(vistaCruceTO);
                                    PrimeFaces.current().ajax().addCallbackParam("marker" + i, marcadores.getMarkers().get(i));
                                    PrimeFaces.current().ajax().addCallbackParam("position" + i, marcadores.getMarkers().get(i).getLatlng());
                                    indice++;
                                    break;
                                }
                            }
                        }
                        marcadores = marcadoresAuxiliar;
                        //Refresca nodo
                        
                        listaNuevosPuntos.clear();
                    } catch (Exception ex) {
                        Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Carga los Moviles
                //Por cada movil pintarlo transparente
                if (ver_moviles) {
                    if (usurioAutenticado().getIdComuna().equals(new BigDecimal("999"))) {
                        try {
                            List<VistaMovilTO> listaMovilesRef = getLocalizacionFacadeLocal().listaVistaMovilTodos();
                            int contador = 0;
                            for (int i = indice; i < marcadores.getMarkers().size(); i++) {
                                contador = 0;
                                for (VistaMovilTO movilTO : listaMovilesRef) {
                                    if (("M-" + movilTO.getCodigomovil()).equals(marcadores.getMarkers().get(i).getTitle())) {
                                        LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                                        VistaCruceTO vistaCruceTO = new VistaCruceTO();
                                        vistaCruceTO.setIdCruce(new BigDecimal(-1));
                                        vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                                        vistaCruceTO.setActivo(movilTO.getCodigomovil());
                                        marcadores.getMarkers().get(i).setLatlng(coordenadas);
                                        if (!getListaChequeados().contains("5")) {
                                            marcadores.getMarkers().get(i).setIcon("../../imagenes/mapa/operativo/vacio.png");
                                        } else {
                                            marcadores.getMarkers().get(i).setIcon(movilTO.getIcono());
                                        }
                                        marcadores.getMarkers().get(i).setData(vistaCruceTO);
                                        PrimeFaces.current().ajax().addCallbackParam("marker" + (i), marcadores.getMarkers().get(i));
                                        PrimeFaces.current().ajax().addCallbackParam("position" + (i), marcadores.getMarkers().get(i).getLatlng());
                                        break;
                                    }
                                    //Llego al final y no habia match entre la vista y el marcador
                                    if (contador == listaMovilesRef.size() - 1) {
                                        LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                                        marcadores.getMarkers().get(i).setLatlng(coordenadas);
                                        marcadores.getMarkers().get(i).setIcon("../../imagenes/mapa/operativo/vacio.png");
                                        VistaCruceTO vistaCruceTO = new VistaCruceTO();
                                        vistaCruceTO.setIdCruce(new BigDecimal(-1));
                                        vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                                        vistaCruceTO.setActivo(movilTO.getCodigomovil());
                                        marcadores.getMarkers().get(i).setData(vistaCruceTO);
                                        PrimeFaces.current().ajax().addCallbackParam("marker" + (i), marcadores.getMarkers().get(i));
                                        PrimeFaces.current().ajax().addCallbackParam("position" + (i), marcadores.getMarkers().get(i).getLatlng());
                                    }
                                    contador++;
                                }
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                            try {
                                List<VistaMovilTO> listaMovilesRef = getLocalizacionFacadeLocal().listaVistaMovilComuna(idComunaSeleccionadas);
                                boolean existe = false;
                                int largo = marcadores.getMarkers().size();
                                MapModel marcadoresAuxiliar = marcadores;
                                for (int i = indice; i < largo; i++) {
                                    existe = false;
                                    for (VistaMovilTO movilTO : listaMovilesRef) {
                                        VistaCruceTO vistaCruceTO = new VistaCruceTO();
                                        vistaCruceTO.setIdCruce(new BigDecimal(-1));
                                        vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                                        vistaCruceTO.setActivo(movilTO.getCodigomovil());
                                        if (("M-" + movilTO.getCodigomovil()).equals(marcadoresAuxiliar.getMarkers().get(i).getTitle())) {
                                            LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                                            marcadoresAuxiliar.getMarkers().get(i).setLatlng(coordenadas);
                                            if (!getListaChequeados().contains("5")) {
                                                marcadoresAuxiliar.getMarkers().get(i).setIcon("../../imagenes/mapa/operativo/vacio.png");
                                            } else {
                                                marcadoresAuxiliar.getMarkers().get(i).setIcon(movilTO.getIcono());
                                            }
                                            marcadoresAuxiliar.getMarkers().get(i).setData(vistaCruceTO);
                                            PrimeFaces.current().ajax().addCallbackParam("marker" + (i), marcadoresAuxiliar.getMarkers().get(i));
                                            PrimeFaces.current().ajax().addCallbackParam("position" + (i), marcadoresAuxiliar.getMarkers().get(i).getLatlng());
                                            existe = true;
                                            break;
                                        }
                                        
                                    }
                                    if (!existe) {
                                        marcadoresAuxiliar.getMarkers().get(i).setIcon("../../imagenes/mapa/operativo/vacio.png");
                                        //  marcadoresAuxiliar.getMarkers().get(i).setData(vistaCruceTO);
                                        PrimeFaces.current().ajax().addCallbackParam("marker" + (i), marcadoresAuxiliar.getMarkers().get(i));
                                        PrimeFaces.current().ajax().addCallbackParam("position" + (i), marcadoresAuxiliar.getMarkers().get(i).getLatlng());
                                    }
                                }
                                marcadores = marcadoresAuxiliar;
                            } catch (Exception ex) {
                                System.out.println("Marcadores 66 " + marcadores.getMarkers().size());
                                Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void onToggleSelect(ToggleSelectEvent e){
        
    }
    
    public void onRegionChange(ValueChangeEvent e) {
        if (!e.getNewValue().toString().equals("0")) //Ninguna
        {
            if (e.getNewValue().toString().equals("999")) {
                try {
                    listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreo();
                    
                } catch (Exception ex) {
                    Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                listaComunasMonitoreo = getLocalizacionFacadeLocal().buscarComunasPorRegion(Integer.parseInt(e.getNewValue().toString()));
            }
            if(listaComunasMonitoreo.size() > 1)
            {
                ComunaTO comunaDummy = new ComunaTO();
                comunaDummy.setIdComuna(new BigDecimal("999"));
                comunaDummy.setIdRegion(new BigDecimal("0"));
                comunaDummy.setNombre("TODAS");
                listaComunasMonitoreo.add(comunaDummy);
            }
        }

    }

    public boolean existeEstadoOperacional(String estado) {
        for (String estadoOpe : getListaChequeados()) {
            if (estadoOpe.equals(estado)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para poder realizar la busqueda de Cruces
     *
     * @param calle
     * @return
     */
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

    public void generacionMapa() {
        this.pintarMapa = "true";
        marcadores = new DefaultMapModel();
        this.listaPuntos.clear();
        this.polyline = new Polyline();
        if (existeComuna()) {
            // Carga Puntos como ID de Cruces por Comuna
            for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                try {

                    /*if (ver_tdv.equals("true")) {
                        generarTDV(idComunaSeleccionadas);
                        generarNodos();
                    } */
                    //this.listaPuntos.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas, listaChequeados));
                    this.listaPuntos.addAll(getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas));
                } catch (Exception ex) {
                    Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (VistaCruceTO vistaCruceTO : listaPuntos) {
                try {
                    if(vistaCruceTO.getLatitud() != null && vistaCruceTO.getLongitud() != null)
                    {
                        LatLng coordenadas = new LatLng(Double.parseDouble(vistaCruceTO.getLatitud()), Double.parseDouble(vistaCruceTO.getLongitud()));
                        Marker marcador = new Marker(coordenadas, vistaCruceTO.getIdCruce().toString());
                        marcador.setId(vistaCruceTO.getIdCruce().toString());
                        marcador.setIcon(vistaCruceTO.getImagenEstadoOperacional());
                        marcador.setData(vistaCruceTO);
                        marcadores.addOverlay(marcador);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ver_moviles) {
                if (usurioAutenticado().getIdComuna().equals(new BigDecimal("999"))) {
                    try {
                        //Lista Todos los moviles
                        for (MovilTO movilTO : getLocalizacionFacadeLocal().listaMovilTodos()) {
                            LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                            Marker marcador = new Marker(coordenadas, "M-" + movilTO.getCodigoMovil());
                            marcador.setId("M-" + movilTO.getCodigoMovil());
                            marcador.setIcon(movilTO.getIcono());
                            VistaCruceTO vistaCruceTO = new VistaCruceTO();
                            vistaCruceTO.setIdCruce(new BigDecimal(-1));
                            vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                            vistaCruceTO.setActivo(movilTO.getCodigoMovil());
                            marcador.setData(vistaCruceTO);
                            marcadores.addOverlay(marcador);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (getComunaSeleccionadas().length > 0) {
                        try {
                            ComunaTO primeraComuna = getLocalizacionFacadeLocal().buscaComunaPorId(getComunaSeleccionadas()[0]);

                            if (primeraComuna.getLatitud() != null && primeraComuna.getLongitud() != null && !primeraComuna.getLatitud().equals("") && !primeraComuna.getLongitud().equals("")) {
                                this.centro = primeraComuna.getLatitud() + "," + primeraComuna.getLongitud();
                            } else {
                                this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } else {
                        this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
                    }                    
                } else {
                    for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                    try {
                        List<MovilTO> moviles = getLocalizacionFacadeLocal().listaMovil(idComunaSeleccionadas);
                        for (MovilTO movilTO : moviles) {
                            LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                            Marker marcador = new Marker(coordenadas, "M-" + movilTO.getCodigoMovil());
                            marcador.setId("M-" + movilTO.getCodigoMovil());
                            marcador.setIcon(movilTO.getIcono());
                            VistaCruceTO vistaCruceTO = new VistaCruceTO();
                            vistaCruceTO.setIdCruce(new BigDecimal(-1));
                            vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                            vistaCruceTO.setActivo(movilTO.getCodigoMovil());
                            marcador.setData(vistaCruceTO);
                            marcadores.addOverlay(marcador);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                    this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
                }
            } else {
                if (usurioAutenticado().getIdComuna().equals(new BigDecimal("999"))) {
                    try {
                        ComunaTO primeraComuna = getLocalizacionFacadeLocal().buscaComunaPorId(getComunaSeleccionadas()[0]);

                        if (primeraComuna.getLatitud() != null && primeraComuna.getLongitud() != null && !primeraComuna.getLatitud().equals("") && !primeraComuna.getLongitud().equals("")) {
                            this.centro = primeraComuna.getLatitud() + "," + primeraComuna.getLongitud();
                        } else {
                            this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
                }
            }
            this.tamanoMapa = "14";
        }
    }

    /*
    public void generarTDV(BigDecimal idComuna) {
        if (getListaChequeados().contains("5")) {
            try {
                List<VRutaTiempoTO> listavRutaTiempo = getTdvFacadeLocal().getVRutaTiempoByEmpresa(new BigDecimal("6"));
                for (VRutaTiempoTO vRutaTiempo : listavRutaTiempo) {
                    if (vRutaTiempo.getPuntosgps() != null && !vRutaTiempo.getPuntosgps().equals("")) {
                        String[] split = vRutaTiempo.getPuntosgps().split(",");
                        Polyline polylineVRuta = new Polyline();
                        Polyline polylineVRuta2 = new Polyline(); //Para crear el borde
                        for (int i = 0; i < split.length; i++) {
                            LatLng lat = new LatLng(Double.parseDouble(split[i]), Double.parseDouble(split[i + 1]));

                            polylineVRuta.getPaths().add(lat);
                            polylineVRuta2.getPaths().add(lat);
                            i++;
                        }
                        if (polylineVRuta.getPaths().size() > 0) //Si la la lista de polylines tiene al menos 1 elemento, se configura y agrega a los marcadores
                        {
                            //Configuracion borde
                            polylineVRuta2.setStrokeColor("#000000");
                            polylineVRuta2.setData(vRutaTiempo);
                            polylineVRuta2.setStrokeOpacity(1.0);
                            polylineVRuta2.setStrokeWeight(8);

                            //Configuracion linea
                            String color = vRutaTiempo.getColor();
                            polylineVRuta.setStrokeColor(getTDVColor(color));
                            polylineVRuta.setData(vRutaTiempo);
                            polylineVRuta.setStrokeWeight(6);
                            polylineVRuta.setStrokeOpacity(1.0);

                            marcadores.addOverlay(polylineVRuta2);
                            marcadores.addOverlay(polylineVRuta);
                        }
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generarNodos() {
        if (getListaChequeados().contains("5")) {
            try {
                List<NodoTO> listaNodoTO = getTdvFacadeLocal().listaNodoTO();
                for (NodoTO nodoTO : listaNodoTO) {
                    if (nodoTO.getLatitud() != null && nodoTO.getLongitud() != null && nodoTO.getOdMatriz() != null) {
                        //Genera Lista OD Matriz
                        List<OdMatriz> listaMatrices = new ArrayList();
                        String[] aux = nodoTO.getOdMatriz().split("#");
                        for (int j = 0; j < aux.length; j++) {
                            String[] infoOdMatriz = aux[j].split(";");
                            OdMatriz odMatriz = new OdMatriz();
                            odMatriz.setIdsen_source(Integer.parseInt(infoOdMatriz[0]));
                            odMatriz.setIdsen_dest(Integer.parseInt(infoOdMatriz[1]));
                            odMatriz.setPorcentaje(round(Float.parseFloat(infoOdMatriz[2]) * 100, 2));
                            odMatriz.setTime(infoOdMatriz[3]);
                            NodoTO nodoDest = getTdvFacadeLocal().getNodobyIdFabrica(BigDecimal.valueOf(odMatriz.getIdsen_dest()));
                            odMatriz.setNombreDestino(nodoDest.getIdFabrica() + " - " + nodoDest.getNombre());
                            listaMatrices.add(odMatriz);
                        }
                        nodoTO.setListaOdMatriz(listaMatrices);
                        //Genera Lista Matriz Macro
                        List<MatrizMacro> listaMatrizMacro = new ArrayList();
                        String[] auxMatMacroGlobal = nodoTO.getMatrizMacro().split("#");
                        fecha_MatrizMacro = auxMatMacroGlobal[1];
                        String[] auxMatMacro = auxMatMacroGlobal[0].split(";");
                        for (int i = 0; i < auxMatMacro.length; i++) {
                            String[] infoMatrizMacro = auxMatMacro[i].split(",");
                            MatrizMacro matrizMacro = new MatrizMacro();
                            matrizMacro.setId_destino(Integer.parseInt(infoMatrizMacro[0]));
                            matrizMacro.setMatch(Integer.parseInt(infoMatrizMacro[1]));
                            matrizMacro.setPorcentaje(infoMatrizMacro[2]);
                            if (matrizMacro.getId_destino() != 0) {
                                NodoTO nodoDest = getTdvFacadeLocal().getNodobyIdFabrica(BigDecimal.valueOf(matrizMacro.getId_destino()));
                                matrizMacro.setNombre_dest(nodoDest.getIdFabrica() + " - " + nodoDest.getNombre());
                            } else {
                                matrizMacro.setNombre_dest("Total:");
                            }
                            matrizMacro.setFecha(auxMatMacroGlobal[1]);
                            listaMatrizMacro.add(matrizMacro);

                        }
                        nodoTO.setListaMatrizMacro(listaMatrizMacro);

                        LatLng coord = new LatLng(nodoTO.getLatitud().doubleValue(), nodoTO.getLongitud().doubleValue());
                        Marker marcador = new Marker(coord, nodoTO.getIdFabrica() + " - " + nodoTO.getNombre());
                        marcador.setId(nodoTO.getNombre() + nodoTO.getId().toString());
                        marcador.setIcon("/AuterOnLine/imagenes/tn_20.png");
                        marcador.setData(nodoTO);
                        marcadores.addOverlay(marcador);
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(MapaPrimeBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    */

    public void cargarMapa() {
        cargarComunas();
        generacionMapa();
        PrimeFaces.current().executeScript("rutaMap('test','0');");
        refresco(); 
    }

    /**
     * Metodo para realizar la busqueda cuando hemos realizado el nuestro filtro
     */
    public void buscar() {
        if (this.idCruce != null && idCruce != null && idCruce.getUbicacion() != null && !this.idCruce.getUbicacion().equals("")) {
            zoomCruce(this.idCruce.getIdCruce());
        }
    }

    /**
     * Metodo implementado para hacer zoom a sobre un cruce especifico
     *
     * @param seleccionado
     */
    public void cambioValorAutoComplete(SelectEvent seleccionado) {
        zoomCruce(((VistaCruceTO) seleccionado.getObject()).getIdCruce());
    }

    /**
     * Metodo generico para buscar cruce y hacer zoom
     *
     * @param idCruce
     */
    public void zoomCruce(BigDecimal idCruce) {
        for (VistaCruceTO vistaCruceTO : listaPuntos) {
            if (vistaCruceTO.getIdCruce().equals(idCruce)) {
                this.tamanoMapa = "18";
                this.centro = vistaCruceTO.getLatitud() + "," + vistaCruceTO.getLongitud();
                break;
            }
        }
    }

    /**
     * Metodo para el Manejo de Cambio de un Check
     *
     * @param codigoTo
     */
    public void processChecked(CodigoTO codigoTo) {
        boolean agregar = true;
        int indice = 0;
        for (String chequeado : getListaChequeados()) {
            if (chequeado.equals(codigoTo.getCodigo1())) {
                getListaChequeados().remove(indice);
                agregar = false;
                break;
            }
            indice++;
        }
        if (agregar) {
            getListaChequeados().add(codigoTo.getCodigo1());
        }
        refresco();
    }

    public String url(String str1, String str2) {

        return str1 + "?dispositivo=" + str2;
    }

    public float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Realiza la carga del punto seleccionado
     *
     * @param event
     */
    public void onMarkerSelect(OverlaySelectEvent event) {

        if (event.getOverlay().getClass().equals(org.primefaces.model.map.Polyline.class)) {
            poliBool = "false";
            this.polyline = (Polyline) event.getOverlay();
            VRutaTiempoTO vr = (VRutaTiempoTO) polyline.getData();
            String str = "<DIV class=\"infoRutas\" style=\"width: 256px; height: 100px;\">"
                    + "<DIV style=\"width: 256px; png: 4px 0px; background-color: #f1f1f1; text-align: center; font-weight: bold; clear: both;\">" + vr.getNombre() + "</DIV>"
                    + "<DIV style=\"clear: both; width: 256px; padding-top: 6px;\"><DIV style=\"float: left; width: 140px;\">Tiempo de viaje promedio:</DIV><DIV style=\"float: left; width: 116px; text-align: right; font-weight: bold;\">" + vr.getMinProp() + "</DIV></DIV>"
                    + "<DIV style=\"clear: both; width: 256px; padding-top: 4px;\"><DIV style=\"float: left; width: 140px;\">Velocidad promedio:</DIV><DIV style=\"float: left; width: 116px; text-align: right; font-weight: bold;\">" + vr.getVelX() + " km/hr</DIV></DIV>"
                    + "<DIV style=\"clear: both; width: 256px; padding-top: 4px;\"><DIV style=\"float: left; width: 90px;\">&Uacute;ltima captura:</DIV><DIV style=\"float: left; width: 166px; text-align: right; font-weight: bold;\">" + vr.getUltimacaptura() + "</DIV></DIV>"
                    + "</DIV>";
            PrimeFaces.current().executeScript("rutaMap('" + str + "','" + polyline.getId() + "');");

        } else {
            poliBool = "true";
            this.marker = (Marker) event.getOverlay();
        }

    }

    public MapModel getMarcadores() {
        return marcadores;
    }

    public void setMarcadores(MapModel marcadores) {
        this.marcadores = marcadores;
    }

    public String getTamanoMapa() {
        return tamanoMapa;
    }

    public void setTamanoMapa(String tamanoMapa) {
        this.tamanoMapa = tamanoMapa;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getPintarMapa() {
        return pintarMapa;
    }

    public void setPintarMapa(String pintarMapa) {
        this.pintarMapa = pintarMapa;
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

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<ComunaTO> getListaComunasMonitoreo() {
        return listaComunasMonitoreo;
    }

    public void setListaComunasMonitoreo(List<ComunaTO> listaComunasMonitoreo) {
        this.listaComunasMonitoreo = listaComunasMonitoreo;
    }

    public boolean getAccesoTerminal() {
        return accesoTerminal;
    }

    public void setAccesoTerminal(boolean accesoTerminal) {
        this.accesoTerminal = accesoTerminal;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public String getPoliBool() {
        return poliBool;
    }

    public void setPoliBool(String poliBool) {
        this.poliBool = poliBool;
    }

    public int getAuxCabecera() {
        return auxCabecera;
    }

    public void setAuxCabecera(int auxCabecera) {
        this.auxCabecera = auxCabecera;
    }

    public int getContPoly() {
        return contPoly;
    }

    public void setContPoly(int contPoly) {
        this.contPoly = contPoly;
    }

    public String getCodigoPag() {
        return codigoPag;
    }

    public void setCodigoPag(String codigoPag) {
        this.codigoPag = codigoPag;
    }

    public String getFecha_MatrizMacro() {
        return fecha_MatrizMacro;
    }

    public void setFecha_MatrizMacro(String fecha_MatrizMacro) {
        this.fecha_MatrizMacro = fecha_MatrizMacro;
    }

    public boolean getAccesoPPR() {
        return accesoPPR;
    }

    public void setAccesoPPR(boolean accesoPPR) {
        this.accesoPPR = accesoPPR;
    }


    public int getNumColsMenu() {
        return numColsMenu;
    }

    public void setNumColsMenu(int numColsMenu) {
        this.numColsMenu = numColsMenu;
    }

    public String getImagenPrime(int indice) {
        CodigoTO cod = new CodigoTO();
        if (getColores().isEmpty()) {
            cargaEstadosOperacionales();
        } else {
            for (CodigoTO codigo : getColores()) {
                if (codigo.getDescripcion().equals(listaEstadoOperativo.get(indice).getCodigo1())) {
                    cod = codigo;
                    break;
                }
            }
        }
        return cod.getCodigo2();
    }

    public List<CodigoTO> getListaEstadoOperativo() {
        return listaEstadoOperativo;
    }

    public void setListaEstadoOperativo(List<CodigoTO> listaEstadoOperativo) {
        this.listaEstadoOperativo = listaEstadoOperativo;
    }

    public List<CodigoTO> getListaEstOpeDimac() {
        return listaEstOpeDimac;
    }

    public void setListaEstOpeDimac(List<CodigoTO> listaEstOpeDimac) {
        this.listaEstOpeDimac = listaEstOpeDimac;
    }

    public boolean isVerControladorRemoto() {
        return verControladorRemoto;
    }

    public void setVerControladorRemoto(boolean verControladorRemoto) {
        this.verControladorRemoto = verControladorRemoto;
    }

    public boolean isVerInfMonitoreo() {
        return verInfMonitoreo;
    }

    public void setVerInfMonitoreo(boolean verInfMonitoreo) {
        this.verInfMonitoreo = verInfMonitoreo;
    }

    public String getBg_uoct() {
        return bg_uoct;
    }

    public void setBg_uoct(String bg_uoct) {
        this.bg_uoct = bg_uoct;
    }

    public String getStyleBackground() {
        return styleBackground;
    }

    public void setStyleBackground(String styleBackground) {
        this.styleBackground = styleBackground;
    }

    public boolean isMostrarInfoConf() {
        return mostrarInfoConf;
    }

    public void setMostrarInfoConf(boolean mostrarInfoConf) {
        this.mostrarInfoConf = mostrarInfoConf;
    }

    public boolean isVer_moviles() {
        return ver_moviles;
    }

    public void setVer_moviles(boolean ver_moviles) {
        this.ver_moviles = ver_moviles;
    }

    public List<RegionTO> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<RegionTO> regiones) {
        this.regiones = regiones;
    }

    public RegionTO getRegion() {
        return region;
    }

    public void setRegion(RegionTO region) {
        this.region = region;
    }

    public boolean isVerFiltroRegion() {
        return verFiltroRegion;
    }

    public void setVerFiltroRegion(boolean verFiltroRegion) {
        this.verFiltroRegion = verFiltroRegion;
    }

    public String getPanelStyle() {
        return panelStyle;
    }

    public void setPanelStyle(String panelStyle) {
        this.panelStyle = panelStyle;
    }

    

}
