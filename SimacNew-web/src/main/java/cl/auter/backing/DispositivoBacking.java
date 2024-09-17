/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.CruceTO;
import cl.auter.patron.to.DispositivoTO;
import cl.auter.patron.to.FasePPRTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Marco
 */
@ManagedBean(name = DispositivoBacking.BEAN_NAME)
@ViewScoped
public class DispositivoBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "dispositivoBacking";
    public String codigoPag = "247";
    private List<DispositivoTO> listaDispositivoTO = new ArrayList();
    private String idComuna;
    BigDecimal idSeleccionado;
    private boolean verPopup = false;
    private DispositivoTO dispositivoTO = new DispositivoTO();
    private List<CodigoTO> listaActivo = new ArrayList();
    private List<CodigoTO> listaTipoMonitoreo = new ArrayList();
    private List<CodigoTO> listaEstadoOperativo = new ArrayList();
    private List<CruceTO> crucesAsociadosDimac = new ArrayList();
    private List<CodigoTO> dir_fases_ppr = new ArrayList();
    private List<BigDecimal> listDirActPPR = new ArrayList();
    private List<BigDecimal> listDirFasePPR = new ArrayList();
    private CruceTO cruce = new CruceTO();
    private List<FasePPRTO> listaEliminar = new ArrayList();
    private List<BigDecimal> listFasePPR = new ArrayList();
    private boolean mostrarPPR = false;
    private boolean permisoPPR = false;
    private int selectedTab = 0;
    
    @PostConstruct
    public void carga() {
        try {
            permisoPPR = getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("263");
        } catch (Exception ex) {
            Logger.getLogger(DispositivoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        validarPagina(codigoPag);
        cargaDispositivo();
        cargaEstadoOperativo();
        cargaActivo();
        cargaTipoMonitoreo();
        cargaDatosPPR();

    }
    
    public void cerrar(){
        verPopup=false;
    }

    public void carga2(String dispositivo) {
        idSeleccionado = new BigDecimal(dispositivo);

        try {
            dispositivoTO = getLocalizacionFacadeLocal().buscarDispositivoPorId(idSeleccionado);
        } catch (Exception ex) {
            Logger.getLogger(DispositivoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargaDatosPPR();
        editarDispositivo();
    }

    public void cargaDatosPPR() {
        try {
            for (CodigoTO cod : getParametrosFacadeLocal().listaPorDominio("DIR_ACTIVACION_CRUCE")) {
                listDirActPPR.add(new BigDecimal(cod.getCodigo2()));
            }
            List<CodigoTO> listAux = getParametrosFacadeLocal().listaPorDominio("DIR_FASE_PPR");
            Collections.sort(listAux, new Comparator<CodigoTO>() {
              @Override
              public int compare(CodigoTO cod1, CodigoTO cod2) {
                  return Integer.parseInt(cod1.getCodigo1()) - Integer.parseInt(cod2.getCodigo1());
              }
            });    
            for (CodigoTO cod : listAux) {
                dir_fases_ppr.add(cod);
                listFasePPR.add(new BigDecimal(cod.getCodigo1()));
                listDirFasePPR.add(new BigDecimal(cod.getCodigo2()));
            }

        } catch (Exception ex) {
            Logger.getLogger(DispositivoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaEstadoOperativo() {
        listaEstadoOperativo.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setCodigo1("");
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaEstadoOperativo.add(codigoTO);
        try {
            listaEstadoOperativo.addAll(obtenerEstadoOperativo());
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargaTipoMonitoreo() {
        listaTipoMonitoreo.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setCodigo1("-1");
        codigoTO.setCodigo2("-- Seleccionar una opcion --");
        listaTipoMonitoreo.add(codigoTO);
        try {
            listaTipoMonitoreo.addAll(getParametrosFacadeLocal().listaPorDominio("TIPO_MONITOREO"));
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void cargaActivo() {
        listaActivo.clear();
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(-1));
        codigoTO.setDescripcion("-- Seleccionar una opcion --");
        listaActivo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(1));
        codigoTO.setDescripcion("SI");
        listaActivo.add(codigoTO);
        codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(new BigDecimal(0));
        codigoTO.setDescripcion("NO");
        listaActivo.add(codigoTO);
    }

    public void remove(CruceTO cruce, FasePPRTO fase) {
        int i = 0;
        int remover = -1;
        for (FasePPRTO fasePPR : cruce.getFasesPPR()) {
            if (fase.getId() == null && fasePPR.getFase().equals(fase.getFase())) {
                remover = i;
                break;
            } else if (fase.getId() != null && fasePPR.getId().equals(fase.getId())) {
                remover = i;
                break;
            }
            i++;
        }
        if (remover != -1) {
            listaEliminar.add(cruce.getFasesPPR().get(remover));
            cruce.getFasesPPR().remove(remover);

        }
    }

    public void add(CruceTO cruceN) {
        
        List<FasePPRTO> listaAux = new ArrayList();
        //Ordena las fases de los cruce asociados al dimac
        for(CruceTO cruceTO: crucesAsociadosDimac)
        {
            //ordena ambos cruces
            Collections.sort(cruceTO.getFasesPPR(), new Comparator<FasePPRTO>() {
              @Override
              public int compare(FasePPRTO f1, FasePPRTO f2) {
                  return f1.getFase().intValue() - f2.getFase().intValue();
              }
          });  
          listaAux.addAll(cruceTO.getFasesPPR());
        }
        if(listaAux.size() >= 8)
        {
            mostrarMensajePPR("Ya tiene 8 fases ingresadas para este dimac.");
            return;
        }
        //Vuelve a ordenar las fases
        Collections.sort(listaAux, new Comparator<FasePPRTO>() {
              @Override
              public int compare(FasePPRTO f1, FasePPRTO f2) {
                  return f1.getFase().intValue() - f2.getFase().intValue();
              }
          });
        
        int nuevaFase = (listaAux.size() + 1);
        if (listaAux.isEmpty()) {
            nuevaFase = 1;
        } else if (listaAux.size() == 1) {
            nuevaFase = 2;
        } else {
            for (int i = 1; i < listaAux.size(); i++) {
                if(i == 1 && listaAux.get(0).getFase().intValue() != 1 )
                {
                    nuevaFase = 1;
                    break;
                }
                if (listaAux.get(i - 1).getFase().intValue() + 1 != listaAux.get(i).getFase().intValue()) {
                    //Falta el numero actual en la secuencia.
                    nuevaFase = listaAux.get(i - 1).getFase().intValue() + 1;
                    break;
                }
            }
        }
        
        //Configura el correlativo de la direccion de memoria de la fase (n + 1)
        BigDecimal dirFase = listDirFasePPR.get(0);
        for(CodigoTO cod : dir_fases_ppr)
        {
            if(cod.getCodigo1().equals(String.valueOf(nuevaFase)))
            {
                dirFase = new BigDecimal(cod.getCodigo2());
            }
        }
        
        FasePPRTO fase = new FasePPRTO();
        fase.setFase(new BigDecimal(nuevaFase));
        fase.setIdCruce(cruceN.getIdcruce());
        fase.setDirfase(dirFase);
        cruceN.getFasesPPR().add(fase);
    }

    public List<FasePPRTO> fasesPorCrucePPR(String idCruce) {
        List<FasePPRTO> listaFasesPorCruce = getLocalizacionFacadeLocal().obtenerFasesPorCruce(new BigDecimal(idCruce));
        return listaFasesPorCruce;
    }

    public void cargaDispositivo() {
        try {
            listaDispositivoTO.clear();
            listaDispositivoTO = getLocalizacionFacadeLocal().listaDispositivoTO();
        } catch (Exception ex) {
            Logger.getLogger(DispositivoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ingresarDispositivo() {
        dispositivoTO = new DispositivoTO();
        verPopup = true;
    }

    public void editarDispositivo() {

        try {
            if (dispositivoTO.getIdDispositivo() == null || !dispositivoTO.getIdDispositivo().equals(idSeleccionado)) {
                dispositivoTO = new DispositivoTO();
                dispositivoTO = getLocalizacionFacadeLocal().buscarDispositivoPorId(idSeleccionado);
            }
            crucesAsociadosDimac = getLocalizacionFacadeLocal().buscarCrucesPorDimac(dispositivoTO.getIdDispositivo());
            
            for (CruceTO cruceTO : crucesAsociadosDimac) {
                if(permisoPPR)
                {
                    mostrarPPR = cruceTO.isPpr();
                    if(!mostrarPPR)
                    {
                        selectedTab = 0;
                    }
                }
                cruceTO.setFasesPPR(getLocalizacionFacadeLocal().obtenerFasesPorCruce(cruceTO.getIdcruce()));
            }

            if (dispositivoTO.getActivo().equals("NO")) {
                dispositivoTO.setActivo("0");
            }
            if (dispositivoTO.getActivo().equals("SI")) {
                dispositivoTO.setActivo("1");
            }
            if (dispositivoTO.getIndUps() == null || dispositivoTO.getIndUps().equals("NO")) {
                dispositivoTO.setIndUps("0");
            }
            if (dispositivoTO.getIndUps().equals("SI")) {
                dispositivoTO.setIndUps("1");
            }
            if (dispositivoTO.getIndOtu() != null && dispositivoTO.getIndOtu().equals("NO")) {
                dispositivoTO.setIndOtu("0");
            }
            if (dispositivoTO.getIndOtu() != null && dispositivoTO.getIndOtu().equals("SI")) {
                dispositivoTO.setIndOtu("1");
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        verPopup = true;
    }

    public void eliminarDispositivo() {
        try {
            dispositivoTO = new DispositivoTO();
            dispositivoTO = getLocalizacionFacadeLocal().buscarDispositivoPorId(idSeleccionado);
            getLocalizacionFacadeLocal().eliminarDispositivo(dispositivoTO);
            addMessage("Dispositivo eliminado exitosamente.");
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
            addMessage("No se puede eliminar el Dispositivo.");
        } finally {
            cargaDispositivo();
        }
    }

    public void actualizarDispositivo() {
        if (validarDatosDispositivo()) {
            try {
                if (dispositivoTO.getIdDispositivo() == null) {
                    
                    getLocalizacionFacadeLocal().guardarDispositivo(dispositivoTO);
                    addMessage("Dispositivo ingresado exitosamente.");
                } else {
                    getLocalizacionFacadeLocal().editarDispositivo(dispositivoTO);
                    addMessage("Dispositivo modificado exitosamente.");
                }
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispositivoTO = new DispositivoTO();
            verPopup = false;
            cargaDispositivo();
        }
    }

    public void actualizarPPR() {
        if (validarCrucePPR()) {
            try {
                for (CruceTO cr : crucesAsociadosDimac) {
                    getLocalizacionFacadeLocal().editarCruce(cr);
                    for (FasePPRTO fase : cr.getFasesPPR()) {
                        if (fase.getId() == null) {
                            getLocalizacionFacadeLocal().guardarFasePPR(fase);
                        } else {
                            getLocalizacionFacadeLocal().editarFasePPR(fase);
                        }

                    }
                    if (!listaEliminar.isEmpty()) {
                        for (FasePPRTO fase : listaEliminar) {
                            getLocalizacionFacadeLocal().eliminarFasePPR(fase);
                        }
                    }
                }
                addMessagePPR("Dispositivo modfificado exitosamente.");
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            //crucesAsociadosDimac = new ArrayList();
            dispositivoTO = new DispositivoTO();
            verPopup = false;
            cargaDispositivo();
        }
    }

    public boolean validarCrucePPR() {
        Set<BigDecimal> seenValues = new HashSet();
        Set<BigDecimal> dirFases = new HashSet();
        for (CruceTO crucePPR : crucesAsociadosDimac) {
            for (FasePPRTO fase : crucePPR.getFasesPPR()) {
                String mensaje = "";
                if (seenValues.contains(fase.getFase())) {
                    mensaje = "La fase " + fase.getFase() + " se encuentra del cruce " + crucePPR.getIdcruce() + " se encuentra duplicada.";
                    mostrarMensajePPR(mensaje);
                    return false;
                } else {
                    seenValues.add(fase.getFase());
                }
                if (dirFases.contains(fase.getDirfase())) {
                    mensaje = "La direccion de memoria " + fase.getDirfase() + " se encuentra duplicada para la fase F" + fase.getFase() + " del cruce " + crucePPR.getIdcruce();
                    mostrarMensajePPR(mensaje);
                    return false;
                } else {
                    dirFases.add(fase.getDirfase());
                }
            }
        }
        return true;
    }

    public boolean validarDatosDispositivo() {
        if (dispositivoTO.getImei().equals("")) {
            mostrarMensaje("Debe ingresar Imei.");
            return false;
        }
        if (dispositivoTO.getNumSerie().equals("")) {
            mostrarMensaje("Debe ingresar Numero Serie.");
            return false;
        }
        if (dispositivoTO.getActivo().equals("-1")) {
            mostrarMensaje("Debe ingresar Indicador Activo.");
            return false;
        }
        if (dispositivoTO.getIndUps().equals("-1")) {
            mostrarMensaje("Debe ingresar Indicador UPS.");
            return false;
        }
        if (dispositivoTO.getIndOtu().equals("-1")) {
            mostrarMensaje("Debe ingresar Indicador OTU.");
            return false;
        }
        if (dispositivoTO.getNumTel().equals("")) {
            mostrarMensaje("Debe ingresar NºSIM NºTeléfono.");
            return false;
        }
        if (dispositivoTO.getActivo().equals("0")) {
            dispositivoTO.setActivo("NO");
        }
        if (dispositivoTO.getActivo().equals("1")) {
            dispositivoTO.setActivo("SI");
        }
        if (dispositivoTO.getIndUps().equals("0")) {
            dispositivoTO.setIndUps("NO");
        }
        if (dispositivoTO.getIndUps().equals("1")) {
            dispositivoTO.setIndUps("SI");
        }
        if (dispositivoTO.getIndOtu().equals("0")) {
            dispositivoTO.setIndOtu("NO");
        }
        if (dispositivoTO.getIndOtu().equals("1")) {
            dispositivoTO.setIndOtu("SI");
        }
        return true;
    }

    public void mostrarMensaje(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:tabview:id", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
    }

    public void mostrarMensajePPR(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("form1:tabview:ingresar", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
    }

    public void addMessagePPR(String summary) {
        FacesMessage message = new FacesMessage(summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(String summary) {
        PrimeFaces.current().ajax().update("form1:tabview:grid");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public List<DispositivoTO> getListaDispositivoTO() {
        return listaDispositivoTO;
    }

    public void setListaDispositivoTO(List<DispositivoTO> listaDispositivoTO) {
        this.listaDispositivoTO = listaDispositivoTO;
    }

    public BigDecimal getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setIdSeleccionado(BigDecimal idSeleccionado) {
        this.idSeleccionado = idSeleccionado;
    }

    public boolean isVerPopup() {
        return verPopup;
    }

    public void setVerPopup(boolean verPopup) {
        this.verPopup = verPopup;
    }

    public DispositivoTO getDispositivoTO() {
        return dispositivoTO;
    }

    public void setDispositivoTO(DispositivoTO dispositivoTO) {
        this.dispositivoTO = dispositivoTO;
    }

    public List<CodigoTO> getListaActivo() {
        return listaActivo;
    }

    public void setListaActivo(List<CodigoTO> listaActivo) {
        this.listaActivo = listaActivo;
    }

    public List<CodigoTO> getListaEstadoOperativo() {
        return listaEstadoOperativo;
    }

    public void setListaEstadoOperativo(List<CodigoTO> listaEstadoOperativo) {
        this.listaEstadoOperativo = listaEstadoOperativo;
    }

    public List<CruceTO> getCrucesAsociadosDimac() {
        return crucesAsociadosDimac;
    }

    public void setCrucesAsociadosDimac(List<CruceTO> crucesAsociadosDimac) {
        this.crucesAsociadosDimac = crucesAsociadosDimac;
    }

    public List<BigDecimal> getListDirActPPR() {
        return listDirActPPR;
    }

    public void setListDirActPPR(List<BigDecimal> listDirActPPR) {
        this.listDirActPPR = listDirActPPR;
    }

    public CruceTO getCruce() {
        return cruce;
    }

    public void setCruce(CruceTO cruce) {
        this.cruce = cruce;
    }

    public List<BigDecimal> getListDirFasePPR() {
        return listDirFasePPR;
    }

    public void setListDirFasePPR(List<BigDecimal> listDirFasePPR) {
        this.listDirFasePPR = listDirFasePPR;
    }

    public List<BigDecimal> getListFasePPR() {
        return listFasePPR;
    }

    public void setListFasePPR(List<BigDecimal> listFasePPR) {
        this.listFasePPR = listFasePPR;
    }

    public boolean isMostrarPPR() {
        return mostrarPPR;
    }

    public void setMostrarPPR(boolean mostrarPPR) {
        this.mostrarPPR = mostrarPPR;
    }

    public boolean isPermisoPPR() {
        return permisoPPR;
    }

    public void setPermisoPPR(boolean permisoPPR) {
        this.permisoPPR = permisoPPR;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public List<CodigoTO> getListaTipoMonitoreo() {
        return listaTipoMonitoreo;
    }

    public void setListaTipoMonitoreo(List<CodigoTO> listaTipoMonitoreo) {
        this.listaTipoMonitoreo = listaTipoMonitoreo;
    }
    
    
}
