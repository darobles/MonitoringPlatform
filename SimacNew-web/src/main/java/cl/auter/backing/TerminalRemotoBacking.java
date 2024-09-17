/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.datos.ConsultaDimac;
import cl.auter.ejb.session.stateless.facade.TerminalRemotoSBLocal;
import cl.auter.patron.to.CruceTO;
import cl.auter.patron.to.DispositivoTO;
import cl.auter.patron.to.FasePPRTO;
import cl.auter.patron.to.PPRFaseAux;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.primefaces.PrimeFaces;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author drobles
 */
@ManagedBean
@ViewScoped
public class TerminalRemotoBacking extends FiltroBacking implements Serializable {
/*
    @EJB
    TerminalRemotoSBLocal terminalRemotoSB;

    List<CruceTO> listaCruces = new ArrayList();
    DispositivoTO dimac = new DispositivoTO();
    CruceTO cruce;
    BigDecimal idDispositivo;
    boolean verControladorRemoto = false; //Muestra el dialog
    boolean verTerminalRemoto = false; //pestaña termianl remoto
    boolean verPanelPolicialRemoto = false; // pestaña panel policial
    List<PPRFaseAux> listaFasesActivaCruce = new ArrayList();
    int width = 380;
    //Variables Terminal
    String modo = "Terminal";
    String inputComandos = "";
    boolean disableInputComandos = false;
    List<ConsultaDimac> consultasActivas = new ArrayList();
    String respuestaComandos = "";
    private UploadedFile archivoLocal;
    int progreso = 0;
    int cantComandosEnviados = 0;
    double porcentaje = 0;
    int timeout = 150;
    boolean mostrarReiniciar = false;

    public void init() {
        //Carga los cruces asociados al dimac
        if (idDispositivo == null) {
            idDispositivo = new BigDecimal("1");
        }
        if (idDispositivo != null) {
            dimac = getLocalizacionFacadeLocal().buscarDispositivoPorId(idDispositivo);
        }
        try {
            if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("294") && dimac.getIndResetCtr().equals(new BigDecimal("1"))) {
                mostrarReiniciar = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void carga() {
        if (idDispositivo == null) {
            idDispositivo = new BigDecimal("1");
        }
        if (idDispositivo != null) {
            dimac = getLocalizacionFacadeLocal().buscarDispositivoPorId(idDispositivo);

        }
        try {
            if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("294") && dimac.getIndResetCtr().equals(new BigDecimal("1"))) {
                mostrarReiniciar = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equals("tabTerminal")) {
            width = 380;
        } else if (event.getTab().getId().equals("tabPPRDimac")) {
            width = 950;
        } else {
            width = 380;
        }
    }

    public List<FasePPRTO> getFases(CruceTO cruce) {
        return cruce.getFasesPPR();
    }

    public void cambioModoTerminal(AjaxBehaviorEvent e) {
        SelectOneRadio selectOneRadio = ((SelectOneRadio) e.getSource());
        modo = selectOneRadio.getSubmittedValue().toString();
    }

    public void timeout() {
        disableInputComandos = false;
        PrimeFaces.current().executeScript("PF('poll-respuestas').stop()");
         PrimeFaces.current().executeScript("PF('timer-checker').stop(true)");
        progreso = 0;
         PrimeFaces.current().executeScript("PF('pbAjax').cancel()");
    }

    public void timeoutChecker() {
        respuestaComandos += "Sin respuesta del servicio Telnet del Dimac.\n";
        consultasActivas.clear();
        timeout();
        addMessage("Sin respuesta del servicio Telnet del Dimac.");
    }

    public void enviaComandoTerminal() {
        progreso = 0;
        List<ConsultaDimac> consultas = new ArrayList();
        if (!inputComandos.equals("")) {
            String[] splitComandos = inputComandos.split("\n");
            for (String str : splitComandos) {
                ConsultaDimac consulta = new ConsultaDimac();
                consulta.setCmd(str.trim().toUpperCase());
                consulta.setUsuario(usurioAutenticado().getIdentificador());
                consulta.setIddispositivo(idDispositivo);
                if (modo.equals("Terminal")) {
                    consulta.setTipo("T");
                } else if (modo.equals("Dimac")) {
                    consulta.setTipo("G");
                } else {
                    consulta.setTipo("ManRem");
                }
                consultas.add(consulta);
            }
        }
        if (!consultas.isEmpty()) {
            consultasActivas.clear();
            disableInputComandos = true;
            consultasActivas = terminalRemotoSB.enviaComandos(consultas);
            cantComandosEnviados = consultasActivas.size();
            porcentaje = (double) (100 / cantComandosEnviados);
            progreso = (int) porcentaje - 1;
        }
    }

    public void limpiarRespuestas() {
        respuestaComandos = "";
    }

    public void limpiarComandos() {
        inputComandos = "";
    }

    public void consultaRespuesta() {
        if (consultasActivas.isEmpty()) {
            timeout();
        } else {
            List<ConsultaDimac> listaRespuestas = terminalRemotoSB.consultaRespuestaIdCons(consultasActivas);
            progreso = (int) (porcentaje) * (cantComandosEnviados - consultasActivas.size() + 1) - 1;
            for (ConsultaDimac respuesta : listaRespuestas) {
                if (respuesta.getRespuesta() != null && !respuesta.getRespuesta().trim().equals("")) {
                    respuestaComandos += respuesta.getCmd() + ":\n" + respuesta.getRespuesta().trim() + "\n\n";
                    ConsultaDimac removerConsulta = new ConsultaDimac();
                    for (ConsultaDimac conActiva : consultasActivas) {
                        if (conActiva.getIdconsulta().equals(respuesta.getIdconsulta())) {
                            removerConsulta = conActiva;
                            break;
                        }
                    }
                    consultasActivas.remove(removerConsulta);
                    PrimeFaces.current().executeScript("reiniciarTimer();");
                    progreso = (int) (porcentaje) * (cantComandosEnviados - consultasActivas.size());
                    if (consultasActivas.isEmpty()) {
                         PrimeFaces.current().executeScript("PF('pbAjax').setValue(100);");
                        progreso = 100;
                    }
                }
            }
        }
    }

    public void cancelar() {
        consultasActivas.clear();
        progreso = 0;
        timeout();
        respuestaComandos += respuestaComandos + "\nCancelado por usuario";
    }

    public void reiniciarControlador() {
        List<ConsultaDimac> consultas = new ArrayList();
        ConsultaDimac consulta = new ConsultaDimac();
        consulta.setIddispositivo(idDispositivo);
        consulta.setTipo("T");
        consulta.setUsuario(usurioAutenticado().getIdentificador());
        consulta.setCmd("reset controlador");
        consultas.add(consulta);
        terminalRemotoSB.enviaComandos(consultas);
    }

    public String obtenerEstiloBoton(CruceTO cruce, String faseSeleccionada) {
        String estilo = "btn-fase-base";
        for (PPRFaseAux faseAux : listaFasesActivaCruce) {
            if (faseAux.getIdCruce().equals(cruce.getIdcruce())) {
                if (!faseAux.getFaseActiva().equals("")) {
                    if (faseAux.getFaseActiva().equals(faseSeleccionada)) {
                        estilo = "btn-fase-activo";
                    } else {
                        estilo = "btn-fase-base";
                    }
                }
            }
        }

        return estilo;
    }

    public void showImg(CruceTO cruce) {
         PrimeFaces.current().ajax().update("form:dlgImg");
        String req = "PF('dlgImg-" + cruce.getIdcruce() + "').show()";
         PrimeFaces.current().executeScript(req);
    }

    public void setDisable(CruceTO cruceTO) {
        for (PPRFaseAux faseAux : listaFasesActivaCruce) {
            if (faseAux.getIdCruce().equals(cruceTO.getIdcruce())) {
                faseAux.setBtnDisable(false);
                break;
            }
        }
    }

    public boolean getDisable(CruceTO cruceTO, String faseSeleccionada) {
        for (PPRFaseAux faseAux : listaFasesActivaCruce) {
            if (faseAux.getIdCruce().equals(cruceTO.getIdcruce())) {
                return faseAux.isBtnDisable();
            }
        }
        return false;
    }

    public void mostrarPPR() {
        if (idDispositivo != null) {
            dimac = getLocalizacionFacadeLocal().buscarDispositivoPorId(idDispositivo);
            if (dimac.getIdDispositivo() != null) {
                listaCruces = getLocalizacionFacadeLocal().buscarCrucesPorDimac(dimac.getIdDispositivo());
                for (CruceTO cruceTO : listaCruces) {
                    cruceTO.setFasesPPR(getLocalizacionFacadeLocal().obtenerFasesPorCruce(cruceTO.getIdcruce()));
                }

            }
        } else {
            System.out.println("Dimac no valido");
        }
        verTerminalRemoto = true;
        verPanelPolicialRemoto = true;
        verControladorRemoto = true;
    }

    public void cerrarDialog() {
        verTerminalRemoto = false;
        verPanelPolicialRemoto = false;
        verControladorRemoto = false;
        mostrarReiniciar = false;
        idDispositivo = null;
        dimac = null;
        listaFasesActivaCruce.clear();
        inputComandos = "";
        respuestaComandos = "";
    }

    public List<CruceTO> getListaCruces() {
        return listaCruces;
    }

    public void setListaCruces(List<CruceTO> listaCruces) {
        this.listaCruces = listaCruces;
    }

    public DispositivoTO getDimac() {
        return dimac;
    }

    public void setDimac(DispositivoTO dimac) {
        this.dimac = dimac;
    }

    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public boolean isVerTerminalRemoto() {
        return verTerminalRemoto;
    }

    public void setVerTerminalRemoto(boolean verTerminalRemoto) {
        this.verTerminalRemoto = verTerminalRemoto;
    }

    public boolean isVerPanelPolicialRemoto() {
        return verPanelPolicialRemoto;
    }

    public void setVerPanelPolicialRemoto(boolean verPanelPolicialRemoto) {
        this.verPanelPolicialRemoto = verPanelPolicialRemoto;
    }

    public boolean isVerControladorRemoto() {
        return verControladorRemoto;
    }

    public void setVerControladorRemoto(boolean verControladorRemoto) {
        this.verControladorRemoto = verControladorRemoto;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getInputComandos() {
        return inputComandos;
    }

    public void setInputComandos(String inputComandos) {
        this.inputComandos = inputComandos;
    }

    public boolean isDisableInputComandos() {
        return disableInputComandos;
    }

    public void setDisableInputComandos(boolean disableInputComandos) {
        this.disableInputComandos = disableInputComandos;
    }

    public String getRespuestaComandos() {
        return respuestaComandos;
    }

    public void setRespuestaComandos(String respuestaComandos) {
        this.respuestaComandos = respuestaComandos;
    }

    public UploadedFile getArchivoLocal() {
        return archivoLocal;
    }

    public void setArchivoLocal(UploadedFile archivoLocal) {
        this.archivoLocal = archivoLocal;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public boolean isMostrarReiniciar() {
        return mostrarReiniciar;
    }

    public void setMostrarReiniciar(boolean mostrarReiniciar) {
        this.mostrarReiniciar = mostrarReiniciar;
    }
*/
}
