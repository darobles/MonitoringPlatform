package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.VistaCruceTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import cl.auter.patron.to.VistaDocumentoTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.patron.to.VistaMovilTO;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named(value = MapaInstalacionesBacking.BEAN_NAME)
@ViewScoped
public class MapaInstalacionesBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "mapaInstalacionesBean";
    private final String codigoPag = "214";
    private String centro;
    private String tamanoMapa = "12";
    private VistaInstalacionTO idCruce;
    private MapModel marcadores = new DefaultMapModel();
    private String pintarMapa;
    private Marker marker = null;
    private List<VistaInstalacionTO> listaPuntos = new ArrayList();
    private List<VistaDocumentoTO> listaDocumentos = new ArrayList();
    private String rendTipo = "false";
    List<VistaMovilTO> listaVistaMoviles = new ArrayList();
    List<ComunaTO> listaComunas = new ArrayList();
    CodigoTO[] tipoInstalacion;
    private String bg_uoct = "#F8F5F3";
    private String styleBackground = "";
    VistaDocumentoTO detCruceDocumentoTO = new VistaDocumentoTO();
    boolean verPopupDetalle = false;

    @PostConstruct
    public void init() {
        try {
            listaComunas.clear();
            centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
            tipoInstalacion = obtenerTipoInstalacion();
            ComunaTO comunaTO = new ComunaTO();
            comunaTO.setIdComuna(new BigDecimal(-1));
            comunaTO.setDescripcionComuna("-- Seleccionar una opcion --");
            comunaTO.setNombre("-- Seleccionar una opcion --");
            if (usurioAutenticado().getTipo().equals(new BigDecimal("242"))) //Usuario UOCT
            {
                listaComunas.addAll(getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT());
                bg_uoct = "transparent";
                styleBackground = "url(../../imagenes/Watermark.png)";
                setVerBuscarCruce(true);
            } else {
                this.listaComunas.add(comunaTO);
                this.listaComunas.addAll(getLocalizacionFacadeLocal().listaComunasTodas());
            }
            listaVistaMoviles = getLocalizacionFacadeLocal().listaVistaMovilTodos();
            BigDecimal tipoRol = usurioAutenticado().getTipo();
            validarPagina(codigoPag);
            if (!tipoRol.equals(new BigDecimal("241"))) {
                this.pintarMapa = "false";
                setPintarComuna("true");
            } else {
                setVerBuscarCruce(true);
                this.pintarMapa = "true";
                setPintarComuna("false");
                agregaComunaUsuario();
                this.generacionMapa();
            }
        } catch (Exception e) {
            redirectIndex();
        }
    }

    /**
     * Metodo para poder realizar la busqueda de Cruces
     *
     * @param calle
     * @return
     */
    public List<VistaInstalacionTO> filtroCruce(String calle) {
        List<VistaInstalacionTO> listaInstalacion = new ArrayList();
        listaInstalacion.clear();
        //if (calle !=null && !calle.equals("") && calle.length()>2){
        if (calle != null && !calle.equals("")) {
            for (VistaInstalacionTO vistaInstalacionTO : listaPuntos) {
                if (vistaInstalacionTO.getDatoFiltro().toUpperCase().contains(calle.toUpperCase())) {
                    listaInstalacion.add(vistaInstalacionTO);
                }
            }
        }
        return listaInstalacion;
    }

    public void generacionMapa() {
        this.pintarMapa = "true";
        marcadores = new DefaultMapModel();
        this.listaPuntos.clear();
        if (existeComuna()) {
            // Carga Puntos como ID de Cruces por Comuna
            for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                try {
                    this.listaPuntos = getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(idComunaSeleccionadas, tipoInstalacion);
                } catch (Exception ex) {
                    Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (VistaInstalacionTO vistaInstalacionTO : listaPuntos) {
                    if (vistaInstalacionTO.getLatitud() != null && vistaInstalacionTO.getLongitud() != null) {
                        LatLng coordenadas = new LatLng(Double.parseDouble(vistaInstalacionTO.getLatitud()), Double.parseDouble(vistaInstalacionTO.getLongitud()));
                        Marker marcador = new Marker(coordenadas, vistaInstalacionTO.getIdCruce().toString());
                        marcador.setId(vistaInstalacionTO.getIdCruce().toString());
                        marcador.setIcon(vistaInstalacionTO.getImagen());
                        marcador.setData(vistaInstalacionTO);
                        marcadores.addOverlay(marcador);
                    }
                }
            }
            if (usurioAutenticado().getIdComuna().equals(new BigDecimal("999"))) {
                try {
                    for (VistaMovilTO movilTO : listaVistaMoviles) {
                        LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                        Marker marcador = new Marker(coordenadas, "M-" + movilTO.getCodigomovil());
                        marcador.setId("M-" + movilTO.getCodigomovil());
                        marcador.setIcon(movilTO.getIcono());
                        VistaCruceTO vistaCruceTO = new VistaCruceTO();
                        vistaCruceTO.setIdCruce(new BigDecimal(-1));
                        vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                        vistaCruceTO.setActivo(movilTO.getCodigomovil());
                        marcador.setData(vistaCruceTO);
                        marcadores.addOverlay(marcador);
                    }
                } catch (NumberFormatException ex) {
                    Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
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
                        List<VistaMovilTO> lista = getLocalizacionFacadeLocal().listaVistaMovilComuna(idComunaSeleccionadas);
                        for (VistaMovilTO movilTO : lista) {
                            LatLng coordenadas = new LatLng(Double.parseDouble(movilTO.getLatitud()), Double.parseDouble(movilTO.getLongitud()));
                            Marker marcador = new Marker(coordenadas, "M-" + movilTO.getCodigomovil());
                            marcador.setId("M-" + movilTO.getCodigomovil());
                            marcador.setIcon(movilTO.getIcono());
                            VistaCruceTO vistaCruceTO = new VistaCruceTO();
                            vistaCruceTO.setIdCruce(new BigDecimal(-1));
                            vistaCruceTO.setDescripcionFecha(movilTO.getDescripcionFecha());
                            vistaCruceTO.setActivo(movilTO.getCodigomovil());
                            marcador.setData(vistaCruceTO);
                            marcadores.addOverlay(marcador);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.centro = this.usurioAutenticado().getLatitud() + "," + this.usurioAutenticado().getLongitud();
            }
            this.tamanoMapa = "14";
        }

    }

    public void cargarMapa() {
        cargarComunas();
        generacionMapa();
    }

    /**
     * Metodo para realizar la busqueda cuando hemos realizado el nuestro filtro
     */
    public void buscar() {
        if (this.idCruce != null && !this.idCruce.getUbicacion().equals("")) {
            zoomCruce(this.idCruce.getIdCruce());
        }
    }

    /**
     * Metodo implementado para hacer zoom a sobre un cruce especifico
     *
     * @param seleccionado
     */
    public void cambioValorAutoComplete(SelectEvent seleccionado) {
        zoomCruce(((VistaInstalacionTO) seleccionado.getObject()).getIdCruce());
    }

    /**
     * Metodo generico para buscar cruce y hacer zoom
     *
     * @param idCruce
     */
    public void zoomCruce(BigDecimal idCruce) {
        for (VistaInstalacionTO vistaCruceTO : listaPuntos) {
            if (vistaCruceTO.getIdCruce().equals(idCruce)) {
                this.tamanoMapa = "18";
                this.centro = vistaCruceTO.getLatitud() + "," + vistaCruceTO.getLongitud();
                break;
            }
        }
    }

    /**
     * Realiza la carga del punto seleccionado
     *
     * @param e
     */
    public void onToggleSelect(ToggleSelectEvent e) {
        // System.out.println("e " + e.toString());
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        this.marker = (Marker) event.getOverlay();
        if (this.marker.getData().getClass() == VistaInstalacionTO.class) {
            VistaInstalacionTO vistaInstalacionTO = (VistaInstalacionTO) this.marker.getData();
            if (vistaInstalacionTO.getTipo().equals("1")) { //semaforo
                rendTipo = "true";
            } else {
                rendTipo = "false";
            }
            this.listaDocumentos.clear();
            try {
                this.listaDocumentos = getLocalizacionFacadeLocal().listaDocumentoPorCruce(vistaInstalacionTO.getIdCruce());
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void verDetalleDoc(VistaDocumentoTO doc) {
        detCruceDocumentoTO = doc;
        System.out.println("doc " + doc.toString());
        verPopupDetalle = true;
    }

    public void cerrarDetalle() {
        verPopupDetalle = false;
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

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
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

    public List<VistaDocumentoTO> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<VistaDocumentoTO> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public String getRendTipo() {
        return rendTipo;
    }

    public void setRendTipo(String rendTipo) {
        this.rendTipo = rendTipo;
    }

    public List<ComunaTO> getListaComunas() {
        return listaComunas;
    }

    public void setListaComunas(List<ComunaTO> listaComunas) {
        this.listaComunas = listaComunas;
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

    public VistaDocumentoTO getDetCruceDocumentoTO() {
        return detCruceDocumentoTO;
    }

    public void setDetCruceDocumentoTO(VistaDocumentoTO detCruceDocumentoTO) {
        this.detCruceDocumentoTO = detCruceDocumentoTO;
    }

    public boolean isVerPopupDetalle() {
        return verPopupDetalle;
    }

    public void setVerPopupDetalle(boolean verPopupDetalle) {
        this.verPopupDetalle = verPopupDetalle;
    }

}
