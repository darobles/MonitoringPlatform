/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import static cl.auter.backing.ComunaBacking.BEAN_NAME;
import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.PoligonoTO;
import cl.auter.patron.to.RegionTO;
import cl.auter.patron.to.VistaComunaRegionTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author drobles
 */
@Named(value = BEAN_NAME)
@ViewScoped
public class ComunaBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "comunaBacking";
    VistaComunaRegionTO comunaTO = new VistaComunaRegionTO();
    private List<CodigoTO> listaMonitoreo = new ArrayList<CodigoTO>();
    List<VistaComunaRegionTO> listaComunas = new ArrayList();
    List<PoligonoTO> listaPoligono = new ArrayList();
    boolean verPopup = false;
    boolean bloqIdComuna = false;
    private int activeIndex;
    private List<RegionTO> listaRegiones = new ArrayList();
    private MapModel marcadores;
    private String centro = "-33.447340, -70.660699";
    Polygon polygon = new Polygon();
    int indice_marker = -1;
    boolean nuevo = false;
    boolean verPoligono = false;

    @PostConstruct
    public void carga() {
        cargaDropdown();
        activeIndex = 0;
        try {
            marcadores = new DefaultMapModel();
            listaRegiones = getLocalizacionFacadeLocal().listaRegionTodas();
            listaComunas = getLocalizacionFacadeLocal().listaVistaComunasReg();
            completarMarkers();
            polygon.setStrokeColor("#FF9900");
            polygon.setFillColor("#FF9900");
            polygon.setStrokeOpacity(0.3);
            polygon.setFillOpacity(0.3);

        } catch (Exception ex) {
            Logger.getLogger(ComunaBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaDropdown() {
        listaMonitoreo.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaMonitoreo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(1));
        codigoTO.setDescripcion("SI");
        listaMonitoreo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(0));
        codigoTO.setDescripcion("NO");
        listaMonitoreo.add(codigoTO);
    }

    public void posMarcador(int indice) {
        if (comunaTO.getLatitud() != null && (listaPoligono.get(indice).getLatitud().doubleValue() <= 0 || listaPoligono.get(indice).getLatitud().doubleValue() >= 0)) {
            System.out.println("New Poligono");
            LatLng coords = new LatLng(Double.valueOf(comunaTO.getLatitud()), Double.valueOf(comunaTO.getLongitud()));
            //marcadores.addOverlay(new Marker(coords, "P-" + indice));
            marcadores.getMarkers().get(indice).setDraggable(true);
            marcadores.getMarkers().get(indice).setLatlng(coords);
            //marcadores.addOverlay(polygon);
        } 
    }

    public void obtenerPunto(MarkerDragEvent event) {
        Marker marker = event.getMarker();
        int pos = Integer.parseInt(marker.getTitle().split("-")[1]) - 1;
        System.out.println("Drag ");
        listaPoligono.get(pos).setLatitud(new BigDecimal(marker.getLatlng().getLat()));
        listaPoligono.get(pos).setLongitud(new BigDecimal(marker.getLatlng().getLng()));
        polygon.getPaths().clear();
        marcadores.getPolygons().clear();
        int i = 0;
        for (PoligonoTO poligonoTO : listaPoligono) {

            if (!poligonoTO.isDummy()) {
                LatLng coords = new LatLng(poligonoTO.getLatitud().doubleValue(), poligonoTO.getLongitud().doubleValue());
                polygon.getPaths().add(coords);
            }
            else{
                LatLng coords = new LatLng(Double.parseDouble(comunaTO.getLatitud()), Double.parseDouble(comunaTO.getLongitud()));
                polygon.getPaths().add(coords);
            }
            i++;
        }
        marcadores.addOverlay(polygon);
        PrimeFaces.current().ajax().addCallbackParam("markerP", polygon);
        LatLng[] auxLatLng = new LatLng[polygon.getPaths().size()];
        for(int j = 0; j < auxLatLng.length; j++)
        {
            auxLatLng[j] = polygon.getPaths().get(j);
            PrimeFaces.current().ajax().addCallbackParam("positionP"+j, polygon.getPaths().get(j));
        }
    }

    public void actualizarComuna() {
        listaPoligono = getLocalizacionFacadeLocal().obtenerPoligonoPorComuna(comunaTO.getId_comuna());

    }

    public List<VistaComunaRegionTO> getListaComunas() {
        return listaComunas;
    }

    public void setListaComunas(List<VistaComunaRegionTO> listaComunas) {
        this.listaComunas = listaComunas;
    }

    public void eliminarComuna() {
        ComunaTO comuna = new ComunaTO();
        comuna.setIdComuna(BigDecimal.valueOf(comunaTO.getId_comuna()));
        comuna.setNombre(comunaTO.getNombre_comuna());
        comuna.setEmail(comunaTO.getEmail());
        comuna.setIdRegion(BigDecimal.valueOf(comunaTO.getId_region()));
        comuna.setLatitud(comunaTO.getLatitud());
        comuna.setLongitud(comunaTO.getLongitud());
        comuna.setUrlLogo(comunaTO.getUrl_logo());
        comuna.setTelefono(comunaTO.getTelefono());
        comuna.setInd_mon(comunaTO.getInd_mon());
        comuna.setZoom(comunaTO.getZoom());
        comuna.setAut_uoct(comunaTO.getAut_uoct());
        getLocalizacionFacadeLocal().eliminarPoligonoPorComuna(comuna.getIdComuna());
        getLocalizacionFacadeLocal().actualizarComunaUsuario(comuna.getIdComuna());
        getLocalizacionFacadeLocal().eliminarComuna(comuna);
        listaComunas = getLocalizacionFacadeLocal().listaVistaComunasReg();
    }

    public void completarMarkers() {
        marcadores.getMarkers().clear();
        List<PoligonoTO> aux = new ArrayList();
        polygon.getPaths().clear();
        for (int i = 0; i < 7; i++) {
            PoligonoTO pol = new PoligonoTO();
            boolean dummy = true;
            if (listaPoligono.size() > 0) {
                for (PoligonoTO poly : listaPoligono) {
                    if (poly.getOrden() - 1 == i) {
                        pol.setIdPoligono(poly.getIdPoligono());
                        pol.setIdComuna(poly.getIdComuna());
                        pol.setLatitud(poly.getLatitud());
                        pol.setLongitud(poly.getLongitud());
                        pol.setOrden(poly.getOrden());
                        pol.setDummy(false);
                        dummy = false;
                        LatLng coords = new LatLng(pol.getLatitud().doubleValue(), pol.getLongitud().doubleValue());
                        polygon.getPaths().add(coords);

                        break;

                    }
                }
            }
            if (dummy) {
                if(comunaTO.getId_comuna() != 0 && !comunaTO.getLatitud().equals("") && !comunaTO.getLongitud().equals(""))
                {
                    pol.setIdComuna(comunaTO.getId_comuna());
                    pol.setLatitud(new BigDecimal(comunaTO.getLatitud()));
                    pol.setLongitud(new BigDecimal(comunaTO.getLongitud()));
                    LatLng coords = new LatLng(pol.getLatitud().doubleValue(), pol.getLongitud().doubleValue());
                    polygon.getPaths().add(coords);
                    pol.setDummy(false);
                }
                else{
                    pol.setIdComuna(0);
                    pol.setLatitud(BigDecimal.ZERO);
                    pol.setLongitud(BigDecimal.ZERO);        
                }
                pol.setOrden(i + 1);
                
            }

            aux.add(pol);

            LatLng coords = new LatLng(pol.getLatitud().doubleValue(), pol.getLongitud().doubleValue());
            marcadores.addOverlay(new Marker(coords, "P-" + pol.getOrden()));
            PrimeFaces.current().ajax().addCallbackParam("marker" + i, marcadores.getMarkers().get(i));
            PrimeFaces.current().ajax().addCallbackParam("position" + i, marcadores.getMarkers().get(i).getLatlng());

        }
        marcadores.addOverlay(polygon);
        PrimeFaces.current().ajax().addCallbackParam("markerP", polygon);
        LatLng[] auxLatLng = new LatLng[polygon.getPaths().size()];
        for(int j = 0; j < auxLatLng.length; j++)
        {
            auxLatLng[j] = polygon.getPaths().get(j);
            PrimeFaces.current().ajax().addCallbackParam("positionP"+j, polygon.getPaths().get(j));
        }
        
        listaPoligono.clear();
        listaPoligono.addAll(aux);
    }
    
    public void ingresarComuna(){
        nuevo = true;
        comunaTO = new VistaComunaRegionTO();
        verPoligono = false;
        verPopup = true;
    }

    public void editarComuna() {
        nuevo = false;
        activeIndex = 0;
        verPopup = true;
        listaPoligono.clear();
        if(comunaTO.getLatitud() != null && comunaTO.getLongitud() != null && !comunaTO.getLatitud().equals("") && !comunaTO.getLongitud().equals("") )
        {
            verPoligono = true;
            listaPoligono = getLocalizacionFacadeLocal().obtenerPoligonoPorComuna(comunaTO.getId_comuna());
        }        
        completarMarkers();

        for (Marker premarker : marcadores.getMarkers()) {
            premarker.setDraggable(true);
        }
        centro = comunaTO.getLatitud() + "," + comunaTO.getLongitud();
        bloqIdComuna = true;

    }

    public void modificarComuna() {
        
        //Editar comuna
        System.out.println("Comuna " + comunaTO.toString());
        ComunaTO comuna = new ComunaTO();
        comuna.setIdComuna(BigDecimal.valueOf(comunaTO.getId_comuna()));
        comuna.setNombre(comunaTO.getNombre_comuna());
        comuna.setEmail(comunaTO.getEmail());
        comuna.setIdRegion(BigDecimal.valueOf(comunaTO.getId_region()));
        comuna.setLatitud(comunaTO.getLatitud());
        comuna.setLongitud(comunaTO.getLongitud());
        comuna.setUrlLogo(comunaTO.getUrl_logo());
        comuna.setTelefono(comunaTO.getTelefono());
        comuna.setInd_mon(comunaTO.getInd_mon());
        comuna.setZoom(comunaTO.getZoom());
        comuna.setAut_uoct(comunaTO.getAut_uoct());
        if(nuevo)
        {
            System.out.println("Crear comuna");
            getLocalizacionFacadeLocal().crearComuna(comuna);
        }
        else{
            getLocalizacionFacadeLocal().editarComuna(comuna);
        }
        
        
        if (validarPoligono()) {
            for (PoligonoTO poligono : listaPoligono) {
                if (poligono.getIdPoligono() == 0) {
                    try {
                        getLocalizacionFacadeLocal().crearPoligonoPorComuna(poligono);
                    } catch (Exception ex) {
                        Logger.getLogger(ComunaBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        getLocalizacionFacadeLocal().editarPoligonoPorComuna(poligono);
                    } catch (Exception ex) {
                        Logger.getLogger(ComunaBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            
        }
        listaComunas = getLocalizacionFacadeLocal().listaVistaComunasReg();
        verPopup = false;
        verPoligono = false;
        nuevo = false;
    }

    public boolean validarPoligono() {
        boolean valido = true;
        for (PoligonoTO poligono : listaPoligono) {
            if (poligono.getLatitud().doubleValue() == 0.0 || poligono.getLongitud().doubleValue() == 0.0) {
                valido = false;
                break;
            }
        }
        return valido;
    }

    public VistaComunaRegionTO getComunaTO() {
        return comunaTO;
    }

    public void setComunaTO(VistaComunaRegionTO comunaTO) {
        this.comunaTO = comunaTO;
    }

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<CodigoTO> getListaMonitoreo() {
        return listaMonitoreo;
    }

    public void setListaMonitoreo(List<CodigoTO> listaMonitoreo) {
        this.listaMonitoreo = listaMonitoreo;
    }

    public boolean isBloqIdComuna() {
        return bloqIdComuna;
    }

    public void setBloqIdComuna(boolean bloqIdComuna) {
        this.bloqIdComuna = bloqIdComuna;
    }

    public List<RegionTO> getListaRegiones() {
        return listaRegiones;
    }

    public void setListaRegiones(List<RegionTO> listaRegiones) {
        this.listaRegiones = listaRegiones;
    }

    public List<PoligonoTO> getListaPoligono() {
        return listaPoligono;
    }

    public void setListaPoligono(List<PoligonoTO> listaPoligono) {
        this.listaPoligono = listaPoligono;
    }

    public MapModel getMarcadores() {
        return marcadores;
    }

    public void setMarcadores(MapModel marcadores) {
        this.marcadores = marcadores;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public int getIndice_marker() {
        return indice_marker;
    }

    public void setIndice_marker(int indice_marker) {
        this.indice_marker = indice_marker;
    }

    public boolean isVerPoligono() {
        return verPoligono;
    }

    public void setVerPoligono(boolean verPoligono) {
        this.verPoligono = verPoligono;
    }
    
    

}
