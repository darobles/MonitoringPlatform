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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class ControladorRemotoBacking extends FiltroBacking implements Serializable {

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
    List<ConsultaDimac> consultasManRemActivas = new ArrayList();
    String respuestaComandos = "";
    private UploadedFile archivoLocal;
    int progreso = 0;
    int cantComandosEnviados = 0;
    double porcentaje = 0;
    int timeout = 150;
    boolean mostrarReiniciar = false;
    private int cols = 12;
    private boolean verDlgImg = false;
    private String updateTO = "";
    // private boolean accesoTerminal = false;
    // private boolean accesoPPR = false;

    @PostConstruct
    public void init() {
        //Carga los cruces asociados al dimac
        carga();
    }

    public void carga() {
        if (idDispositivo == null) {
            idDispositivo = new BigDecimal("-1");
        }
        if (idDispositivo != null && idDispositivo.intValue() > 1) {
            dimac = getLocalizacionFacadeLocal().buscarDispositivoPorId(idDispositivo);
            updateTO = "dlg-timeout-"+ dimac.getIdDispositivo();
            if (dimac.getIdDispositivo() != null) {
                listaCruces = getLocalizacionFacadeLocal().buscarCrucesPorDimac(dimac.getIdDispositivo());
                for (CruceTO cruceTO : listaCruces) {
                    List<FasePPRTO> fasesPPRTO = getLocalizacionFacadeLocal().obtenerFasesPorCruce(cruceTO.getIdcruce());
                    cruceTO.setFasesPPR(fasesPPRTO);
                    PPRFaseAux faseAux = new PPRFaseAux();
                    faseAux.setIdCruce(cruceTO.getIdcruce());
                    faseAux.setBtnDisable(false);
                    faseAux.setFaseActiva("");
                    listaFasesActivaCruce.add(faseAux);
                }
                if(listaCruces.size() > 1)
                {
                    cols = 6;
                }
                else{
                    cols = 12;
                }
                
            }
        }
        try {
            if (getLocalizacionFacadeLocal().buscarRolUsuario(usurioAutenticado().getIdentificador()).getOpcion().contains("294")) {
                mostrarReiniciar = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(CruceBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTimerCruce(CruceTO cruceTO) {
        cruceTO.setTiempoRestante(cruceTO.getTiempoRestante() - 1);
         PrimeFaces.current().executeScript("setProgressBar('#bar-" + cruceTO.getIdcruce() + "'," + cruceTO.getTiempoRestante() + ");");
        if (cruceTO.getTiempoRestante() <= 0) //Llego a cero
        {
           PrimeFaces.current().executeScript("PF('timer-" + cruceTO.getIdcruce() + "').stop()");
            revertir(cruceTO);
            cruceTO.setTiempoRestante(0);
            PrimeFaces.current().executeScript("setProgressBar('#bar-" + cruceTO.getIdcruce() + "'," + cruceTO.getTiempoRestante() + ");");
        }

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

    public void revertirTodo() {
        List<ConsultaDimac> consultas = new ArrayList();
        ConsultaDimac consultaPass = new ConsultaDimac();
        consultaPass.setIddispositivo(dimac.getIdDispositivo());
        consultaPass.setTipo("T");
        consultaPass.setCmd("PWD 231");
        consultaPass.setUsuario(usurioAutenticado().getIdentificador());
        consultas.add(consultaPass);
        for (CruceTO cruceTO : listaCruces) {
            for (PPRFaseAux faseAux : listaFasesActivaCruce) {
                if (faseAux.getIdCruce().equals(cruceTO.getIdcruce())) {
                    faseAux.setFaseActiva("");
                    faseAux.setBtnDisable(false);
                    break;
                }
            }
            if(cruceTO.isControlManualActivo())
            {
                ConsultaDimac consulta = new ConsultaDimac();
                consulta.setIddispositivo(new BigDecimal(cruceTO.getIdDispositivo()));
                consulta.setTipo("T");
                consulta.setCmd("SWI " + cruceTO.getDirActManual().toString() + " 0");
                consulta.setUsuario(usurioAutenticado().getIdentificador());
                consultas.add(consulta);
                for (CruceTO crucePA : listaCruces) {
                    if (crucePA.getIdcruce().equals(cruceTO.getIdcruce())) {
                        crucePA.setControlManualActivo(false);
                        break;
                    }
                }
            }
        }
        if(consultas.size() > 1)
        {
            terminalRemotoSB.enviaComandos(consultas);
        }

    }

    public void cambioModoTerminal(AjaxBehaviorEvent e) {
        SelectOneRadio selectOneRadio = ((SelectOneRadio) e.getSource());
        modo = selectOneRadio.getSubmittedValue().toString();
    }

    public void timeout() {
        disableInputComandos = false;
        PrimeFaces.current().executeScript("PF('poll-respuestas').stop()");
        PrimeFaces.current().executeScript("PF('timer-checker').stop(true)");
    }

    public void timeoutChecker() {
        respuestaComandos += "Sin respuesta desde el controlador.\n";
        timeout();
    }

    public void enviaComandoTerminal() {
        progreso = 0;
        List<ConsultaDimac> consultas = new ArrayList();
        if (!inputComandos.equals("")) {
            String[] splitComandos = inputComandos.split("\n");
            for (String str : splitComandos) {
                ConsultaDimac consulta = new ConsultaDimac();
                consulta.setCmd(str.trim().toUpperCase());
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

    public void consultaRespuestaManRem() {
        List<ConsultaDimac> removerManRem = new ArrayList();
        if (!consultasManRemActivas.isEmpty()) {
            List<ConsultaDimac> listaRespuestas = terminalRemotoSB.consultaRespuestaManRem(consultasManRemActivas);

            for (ConsultaDimac consulta : listaRespuestas) {
                if (consulta.getRespuesta() == null || consulta.getRespuesta().equals("")) {
                    long diff = consulta.getHoraBD().getTime() - consulta.getFechacmd().getTime();
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
                    if (seconds >= 60) {
                        //revertir todo
                        String req = "PF('dlg-timeout-" + dimac.getIdDispositivo() + "').show()";
                        PrimeFaces.current().executeScript(req);
                        consultasManRemActivas.clear();
                        //modal
                    }
                } else {
                    for (ConsultaDimac consManRem : consultasManRemActivas) {
                        if (consManRem.getIdconsulta().equals(consulta.getIdconsulta())) {
                            removerManRem.add(consManRem);
                            break;
                        }
                    }
                }
            }

        }
        for (ConsultaDimac cons : removerManRem) {
            consultasManRemActivas.remove(cons);
        }
        if (consultasManRemActivas.isEmpty()) {
            PrimeFaces.current().executeScript("PF('timeout-" + dimac.getIdDispositivo() + "').stop(true)");
        }
    }

    public void cancelar() {
        consultasActivas.clear();
        progreso = 0;
        timeout();
        respuestaComandos += respuestaComandos + "\nCancelado por usuario";
    }

    public void seleccionarFase(CruceTO cruce, BigDecimal fase) {
        cruce.setTiempoRestante(timeout);
        for (PPRFaseAux faseCruceAux : listaFasesActivaCruce) {
            if (faseCruceAux.getIdCruce().equals(cruce.getIdcruce())) {
                faseCruceAux.setFaseActiva(fase.toString());
                faseCruceAux.setBtnDisable(true);
                break;
            }
        }
        
        List<ConsultaDimac> consultas = new ArrayList();
        ConsultaDimac consulta = new ConsultaDimac();
        consulta.setIddispositivo(dimac.getIdDispositivo());
        consulta.setTipo("M");
        consulta.setUsuario(usurioAutenticado().getIdentificador());
        String manRem = "";
        for (FasePPRTO faseTO : cruce.getFasesPPR()) {
            if (faseTO.getFase().equals(fase)) {
                manRem = faseTO.getDirfase().toString();
            }
        }
        consulta.setCmd(manRem);
        consultas.add(consulta);
        List<ConsultaDimac> comandos = terminalRemotoSB.enviaComandos(consultas);
        for (ConsultaDimac consultaEnviada : comandos) {
            consultasManRemActivas.add(consultaEnviada);
        }
        for (CruceTO crucePA : listaCruces) {
            if (crucePA.getIdcruce().equals(cruce.getIdcruce())) {
                crucePA.setControlManualActivo(true);
                break;
            }
        }
    }

    //Vuelve a la configuración inicial.
    public void revertir(CruceTO cruceTO) {
        PrimeFaces.current().executeScript("setProgressBar('#bar-" + cruceTO.getIdcruce() + "'," + timeout + ");");
        for (PPRFaseAux faseAux : listaFasesActivaCruce) {
            if (faseAux.getIdCruce().equals(cruceTO.getIdcruce())) {
                faseAux.setFaseActiva("");
                faseAux.setBtnDisable(false);
                break;
            }
        }
        List<ConsultaDimac> consultas = new ArrayList();
        ConsultaDimac consultaPass = new ConsultaDimac();
        consultaPass.setIddispositivo(new BigDecimal(cruceTO.getIdDispositivo()));
        consultaPass.setTipo("T");
        consultaPass.setCmd("PWD 231");
        consultaPass.setUsuario(usurioAutenticado().getIdentificador());
        consultas.add(consultaPass);
        ConsultaDimac consulta = new ConsultaDimac();
        consulta.setIddispositivo(new BigDecimal(cruceTO.getIdDispositivo()));
        consulta.setUsuario(usurioAutenticado().getIdentificador());
        consulta.setTipo("T");
        consulta.setCmd("SWI " + cruceTO.getDirActManual().toString() + " 0");
        consultas.add(consulta);
        for (CruceTO crucePA : listaCruces) {
            if (crucePA.getIdcruce().equals(cruceTO.getIdcruce())) {
                crucePA.setControlManualActivo(false);
                break;
            }
        }
        terminalRemotoSB.enviaComandos(consultas);

    }

    public void reiniciarControlador() {
        List<ConsultaDimac> consultas = new ArrayList();
        ConsultaDimac consulta = new ConsultaDimac();
        consulta.setIddispositivo(idDispositivo);
        consulta.setTipo("T");
        consulta.setCmd("reset controlador");
        consulta.setUsuario(usurioAutenticado().getIdentificador());
        consultas.add(consulta);
       terminalRemotoSB.enviaComandos(consultas);
    }

    public String obtenerEstiloBoton(CruceTO cruce, String fase) {
        String estilo = "btn-fase-base";
        for (PPRFaseAux faseAux : listaFasesActivaCruce) {
            if (faseAux.getIdCruce().equals(cruce.getIdcruce())) {
                if (!faseAux.getFaseActiva().equals("")) {
                    if (faseAux.getFaseActiva().equals(fase)) {
                        estilo = "btn-fase-activo";
                    } else {
                        estilo = "btn-fase-base";
                    }
                }
            }
        }
        return estilo;
    }

    public void showImg(BigDecimal idcruce) {
        verDlgImg = true;
        PrimeFaces.current().ajax().update("form:imgDlg-" + idcruce);
        String req = "PF('imgDlg-" + idcruce + "').show()";
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
            carga();
        } else {
            System.out.println("Dimac no valido");
        }
        verTerminalRemoto = true;
        verPanelPolicialRemoto = true;
        verControladorRemoto = true;
        String req = "PF('popupPPR').show();";
        //PrimeFaces.current().executeScript(req);       
    }

    public void cerrarDialog() {
        revertirTodo();
        verTerminalRemoto = false;
        verPanelPolicialRemoto = false;
        verControladorRemoto = false;
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

    public int getCols() {
        return cols;
    }

    public boolean isVerDlgImg() {
        return verDlgImg;
    }

    public void setVerDlgImg(boolean verDlgImg) {
        this.verDlgImg = verDlgImg;
    }

    public String getUpdateTO() {
        return updateTO;
    }

    public void setUpdateTO(String updateTO) {
        this.updateTO = updateTO;
    }

    
    
    

}
