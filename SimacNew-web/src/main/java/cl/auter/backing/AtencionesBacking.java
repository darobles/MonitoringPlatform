package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.backing.util.Util;
import cl.auter.patron.to.AddTaskTO;
import cl.auter.patron.to.AtencionTareaTO;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.DetalleTareaTO;
import cl.auter.patron.to.EmailTO;
import cl.auter.patron.to.SolicitudTO;
import cl.auter.patron.to.SolicitudTableTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.patron.to.VistaReclamoCoordinacionTO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.primefaces.PrimeFaces;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = AtencionesBacking.BEAN_NAME)
@ViewScoped
public class AtencionesBacking extends FiltroBacking implements Serializable {

    public static final String BEAN_NAME = "atencionesBacking";
    public String codigoPag = "218";
    private static final long serialVersionUID = 1L;

    private List<SolicitudTO> listaSolicitudes = new ArrayList();
    private List<SolicitudTO> listaSolicitudesIngresada = new ArrayList();
    private List<SolicitudTO> listaSolicitudesReclamada = new ArrayList();
    private List<CodigoTO> listaCodigo = new ArrayList();
    private List<ComunaTO> listaComunasSelect = new ArrayList();

    private SolicitudTO solicitudTO = new SolicitudTO();
    private boolean verPopupDetalle = false;
    private boolean verAtencionesTareaReclamo = false;
    private String convertClob = new String();
    private BigDecimal numeroTicket;
    private String motivoRechazo = new String();
    private String comboHabilitada;

    boolean verPopupRechazar = false;
    boolean verPopupAceptar = false;
    boolean verPopupReasignar = false;
    boolean verPopupTab = false;

    private VistaInstalacionTO idCruce;
    private BigDecimal cruce;
    private List<VistaInstalacionTO> listaPuntos = new ArrayList();
    private String idComuna;
    private SolicitudTO idAtencion;

    private BigDecimal idTareaReclamoSeleccionado;

    DetalleTareaTO detalleTareaTO = new DetalleTareaTO();
    List<AtencionTareaTO> listaAtencionTareaTO = new ArrayList();
    private CodigoTO[] tipoInstalacion;
    List<VistaReclamoCoordinacionTO> listaReclamosHistoricos = new ArrayList();
    String alto = "";
    BigDecimal idSeleccionado;
    List<SolicitudTableTO> listaSolicitudesTabla = new ArrayList();

    Date fechaDesde = new Date();
    Date fechaHasta = new Date();
    Calendar fecIni = new GregorianCalendar();
    Calendar fecEnd = new GregorianCalendar();

    @PostConstruct
    public void carga() {
        try {
            verPopupRechazar = false;
            tipoInstalacion = obtenerTipoInstalacion();
            listaCodigo = getLocalizacionFacadeLocal().listaCodigos();
            listaComunasSelect = getLocalizacionFacadeLocal().listaComunasTodas();
            BigDecimal tipoRol = usurioAutenticado().getTipo();
            validarPagina(codigoPag);
            this.idComuna = String.valueOf(usurioAutenticado().getIdComuna());
            if (!tipoRol.equals(new BigDecimal("241"))) {
                this.comboHabilitada = "false";
            } else {
                this.comboHabilitada = "true";
                this.listaPuntos.clear();
                this.listaPuntos = getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(this.idComuna), tipoInstalacion);
            }
            Date today = new Date();

            fecIni.setTime(today);
            fecIni.add(Calendar.DAY_OF_MONTH, -30);
            fecIni.set(Calendar.HOUR_OF_DAY, 0);
            fecIni.set(Calendar.MINUTE, 0);
            fecIni.set(Calendar.SECOND, 0);
            fecIni.set(Calendar.MILLISECOND, 1);

            fecEnd.set(Calendar.HOUR_OF_DAY, 23);
            fecEnd.set(java.util.Calendar.MINUTE, 59);
            fecEnd.set(java.util.Calendar.SECOND, 59);
            fecEnd.set(java.util.Calendar.MILLISECOND, 59);
            cargaSolicitud(fecIni.getTime(), fecEnd.getTime());
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agrandar() {
        PrimeFaces.current().executeScript("reziseDlg();");
    }

    public void cargaSolicitud(Date fecIni, Date fecEnd) {
        listaSolicitudes.clear();
        listaSolicitudesTabla.clear();
        try {
            if (this.idComuna.equals("999")) {
                if (usurioAutenticado().getTipo().equals(new BigDecimal("240"))) //Si es usuario Auter o uoct
                {
                    listaSolicitudes = getLocalizacionFacadeLocal().buscarSolicitudesPorFecha(fecIni, fecEnd);
                } else {
                    listaSolicitudes = getLocalizacionFacadeLocal().listaSolicitudesPorTipoSolicitante(usurioAutenticado().getTipo(), fecIni, fecEnd);
                }
            } else {
                listaSolicitudes = getLocalizacionFacadeLocal().buscarSolComFecha(new BigDecimal(this.idComuna), fecIni, fecEnd);
            }
        } catch (Exception ex) {
            Logger.getLogger(AtencionesBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (SolicitudTO solicitudIter : listaSolicitudes) {
            solicitudIter.setVerEstado(false);
            solicitudIter.setVerTareaReclamo(false);
            solicitudIter.setVerLinkSolicitud(true);
            solicitudIter.setVerReclamo(true);
            for (ComunaTO comunaTO : listaComunasSelect) {
                if (solicitudIter.getIdComuna().equals(comunaTO.getIdComuna())) {
                    solicitudIter.setDescripcionComuna(comunaTO.getNombre());
                }
            }
            for (CodigoTO codigoTO : listaCodigo) {
                if (codigoTO.getDominio().equals("ESTADO_SOLICITUD")) {
                    if (solicitudIter.getEstadoActual().equals(codigoTO.getCodigo2())) {
                        solicitudIter.setDescripcionEstadoActual(codigoTO.getDescripcion());
                    }
                }
            }
            if (solicitudIter.getEstadoActual().equals("I")) {
                listaSolicitudesIngresada.add(solicitudIter);
                solicitudIter.setVerBotonAceptar(true);
                solicitudIter.setVerBotonReasignar(true);
                solicitudIter.setVerBotonRechazar(true);
            }
            if (solicitudIter.getEstadoActual().equals("A")) {
                solicitudIter.setVerEstado(true);
            }
            if (solicitudIter.getNumTareaAsma() == null || solicitudIter.getNumTareaAsma().equals(BigDecimal.ZERO)) {
                solicitudIter.setVerLinkSolicitud(false);
            }
            if (solicitudIter.getNumReclamo() == null || solicitudIter.getNumReclamo().equals(BigDecimal.ZERO)) {
                solicitudIter.setVerReclamo(false);
            }
            solicitudIter.setFechaIngresoFormat(Util.deDateAString(solicitudIter.getFechaIngreso()));

            SolicitudTableTO sol = new SolicitudTableTO();
            sol.setIdSolicitud(solicitudIter.getIdSolicitud());
            sol.setDescripcionEstadoActual(solicitudIter.getDescripcionEstadoActual());
            sol.setDescripcionComuna(solicitudIter.getDescripcionComuna());
            sol.setNombreSolicitante(solicitudIter.getNombreSolicitante());
            sol.setEmailSolicitante(solicitudIter.getEmailSolicitante());
            sol.setAsunto(solicitudIter.getAsunto());
            sol.setVerEstado(solicitudIter.isVerEstado());
            sol.setFechaIngresoFormat(solicitudIter.getFechaIngresoFormat());
            sol.setNumTareaAsma(solicitudIter.getNumTareaAsma());
            sol.setNumReclamo(solicitudIter.getNumReclamo());
            listaSolicitudesTabla.add(sol);
        }
    }

    public void eliminaSolicitud() {
        try {
            for (SolicitudTO solicitudIterTO : listaSolicitudes) {
                if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                    solicitudTO = solicitudIterTO;
                }
            }
            getLocalizacionFacadeLocal().eliminaSolicitud(solicitudTO);
            listaSolicitudes = getLocalizacionFacadeLocal().listaSolicitudes();
            solicitudTO = new SolicitudTO();
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaBusqueda() {
        FacesContext context = FacesContext.getCurrentInstance();
        PrimeFaces.current().executeScript("$('.layout-config-button').click()");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargando!", "La busqueda puede tardar unos segundos."));
        PrimeFaces.current().ajax().update("form1:messages");
        PrimeFaces.current().ajax().update("config-form-tabs");
        PrimeFaces.current().ajax().update("form1:table");
    }

    public void buscar() {
        Calendar calFechaDesde = Calendar.getInstance();
        calFechaDesde.setTime(fechaDesde);
        Calendar calFechaHasta = Calendar.getInstance();
        calFechaHasta.setTime(fechaHasta);

        fecIni.set(java.util.Calendar.HOUR_OF_DAY, 0);
        fecIni.set(java.util.Calendar.MINUTE, 0);
        fecIni.set(java.util.Calendar.SECOND, 0);
        fecIni.set(java.util.Calendar.YEAR, calFechaDesde.get(Calendar.YEAR));
        fecIni.set(java.util.Calendar.MONTH, calFechaDesde.get(Calendar.MONTH));
        fecIni.set(java.util.Calendar.DAY_OF_MONTH, calFechaDesde.get(Calendar.DAY_OF_MONTH));

        fecEnd.set(java.util.Calendar.HOUR_OF_DAY, 23);
        fecEnd.set(java.util.Calendar.MINUTE, 59);
        fecEnd.set(java.util.Calendar.SECOND, 59);
        fecEnd.set(java.util.Calendar.YEAR, calFechaHasta.get(java.util.Calendar.YEAR));
        fecEnd.set(java.util.Calendar.MONTH, calFechaHasta.get(java.util.Calendar.MONTH));
        fecEnd.set(java.util.Calendar.DAY_OF_MONTH, calFechaHasta.get(Calendar.DAY_OF_MONTH));
        cargaSolicitud(fecIni.getTime(), fecEnd.getTime());

    }

    public void detalleSolicitudPopup() {
        verPopupAceptar = true;
        convertClob = "";
        for (SolicitudTO solicitudIterTO : listaSolicitudes) {
            if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
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

    public void rechazarSolicitudPopup() {
        verPopupRechazar = true;
        for (SolicitudTO solicitudIterTO : listaSolicitudes) {
            if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                solicitudTO = solicitudIterTO;
            }
        }
        convertClob = clobToString(solicitudTO.getObservacion());
        verPopupRechazar = true;
    }

    public void reasignarSolicitudPopup() {
        verPopupReasignar = true;
        listaSolicitudesReclamada = new ArrayList();
        for (SolicitudTO solicitudIterTO : listaSolicitudes) {
            if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                solicitudTO = solicitudIterTO;
            }
            if (solicitudIterTO.getEstadoActual().equals("R")) {
                listaSolicitudesReclamada.add(solicitudIterTO);
            }
        }
        convertClob = clobToString(solicitudTO.getObservacion());
    }

    public String clobToString(Clob campo) {
        try {
            return campo.getSubString(1, (int) campo.length());
        } catch (SQLException ex) {
            Logger.getLogger(AtencionesBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void reclamarSolicitud() {
        if (validarCamposReclamar()) {
            listaSolicitudes.clear();

            AddTaskTO addTaskTO = new AddTaskTO();
            addTaskTO.setIdCruce(cruce);
            addTaskTO.setFunInicial(new BigDecimal(28));
            addTaskTO.setFecha(new Date());
            addTaskTO.setObservaciones(clobToString(solicitudTO.getObservacion()));
            addTaskTO.setIdTipoEquipo(new BigDecimal(1));
            addTaskTO.setTipoFuente(new BigDecimal(1));
            addTaskTO.setIdgrupo(new BigDecimal(1));
            addTaskTO.setNombreFuente("Sistema WEB");
            addTaskTO.setTecnico(new BigDecimal(1));
            addTaskTO.setUrl("municipal.auter.cl");
            addTaskTO.setMail(solicitudTO.getEmailSolicitante());
            try {
                //solicitudTO.setNumTareaAsma(getLocalizacionFacadeLocal().paAddTask(addTaskTO, idComuna));
                solicitudTO.setEstadoActual("R");
                getLocalizacionFacadeLocal().editarSolicitud(solicitudTO);
            } catch (Exception ex) {
                Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                EmailTO emailTO = new EmailTO();
                emailTO.setCuenta(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CUENTA").getCodigo2());
                emailTO.setClave(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CLAVE").getCodigo2());
                emailTO.setContenido(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CORREO_RECLAMAR").getCodigo2());
                Util.mailCreacion(emailTO, solicitudTO);
            } catch (Exception ex) {
                Logger.getLogger(SolicitudBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            solicitudTO = new SolicitudTO();
            cargaSolicitud(fecIni.getTime(), fecEnd.getTime());
            verPopupRechazar = false;
            verPopupAceptar = false;
            verPopupReasignar = false;
            verPopupTab = false;
        }

    }

    public boolean validarCamposReclamar() {
        if (cruce == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("form1:idCompleto", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un cruce. "));
            return false;
        }
        return true;
    }

    public void reasignarSolicitud() {
        try {
            for (SolicitudTO solicitudIterTO : listaSolicitudes) {
                if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                    solicitudTO = solicitudIterTO;
                    solicitudTO.setNumTareaAsma(idAtencion.getNumTareaAsma());
                    solicitudTO.setEstadoActual("R");
                    getLocalizacionFacadeLocal().editarSolicitud(solicitudTO);
                    try {
                        EmailTO emailTO = new EmailTO();
                        emailTO.setCuenta(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CUENTA").getCodigo2());
                        emailTO.setClave(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CLAVE").getCodigo2());
                        emailTO.setContenido(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CORREO_REASIGNAR").getCodigo2());
                        Util.mailCreacion(emailTO, solicitudTO);
                    } catch (Exception ex) {
                        Logger.getLogger(SolicitudBacking.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    solicitudTO = new SolicitudTO();
                    cargaSolicitud(fecIni.getTime(), fecEnd.getTime());
                    break;
                }
            }

            verPopupReasignar = false;
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void anularSolicitud() {
        try {
            for (SolicitudTO solicitudIterTO : listaSolicitudes) {
                if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                    solicitudTO = solicitudIterTO;
                }
            }
            solicitudTO.setEstadoActual("A");
            Connection conn;
            conn = getJNDIConnection();
            Clob clob = conn.createClob();
            clob.setString(1, motivoRechazo);
            conn.close();
            solicitudTO.setMotivoRechazo(clob);
            getLocalizacionFacadeLocal().editarSolicitud(solicitudTO);
            try {
                EmailTO emailTO = new EmailTO();
                emailTO.setCuenta(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CUENTA").getCodigo2());
                emailTO.setClave(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CLAVE").getCodigo2());
                emailTO.setContenido(getParametrosFacadeLocal().buscarDominioCodigo("MAIL_AUTER", "CORREO_ANULAR").getCodigo2());
                Util.mailCreacion(emailTO, solicitudTO);
            } catch (Exception ex) {
                Logger.getLogger(SolicitudBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
            solicitudTO = new SolicitudTO();
            cargaSolicitud(fecIni.getTime(), fecEnd.getTime());
            verPopupRechazar = false;
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Connection getJNDIConnection() {
        String DATASOURCE_CONTEXT = "java:jboss/georeferenciaDS";
        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource.");
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }

    public List<VistaInstalacionTO> filtroCruce(String calle) {
        List<VistaInstalacionTO> listaCruce = new ArrayList();
        listaCruce.clear();
        //if (calle !=null && !calle.equals("") && calle.length()>2){
        if (calle != null && !calle.equals("")) {
            for (VistaInstalacionTO vistaInstalacionTO : listaPuntos) {
                if (vistaInstalacionTO.getUbicacion().toUpperCase().contains(calle.toUpperCase())) {
                    listaCruce.add(vistaInstalacionTO);
                }
            }
        }
        return listaCruce;
    }

    //carga Solicitud
    public void popupIdSolicitud() {
        for (SolicitudTO solicitudIterTO : listaSolicitudes) {
            if (solicitudIterTO.getIdSolicitud().equals(idSeleccionado)) {
                solicitudTO = solicitudIterTO;
                // solicitudTO.setVerTareaReclamo(false);
                listaReclamosHistoricos.clear();
                if (solicitudTO.getNumReclamo() != null) {
                   /* solicitudTO.setReclamo(getEnlaceFacadeLocal().obtenerReclamoPorId(solicitudTO.getNumReclamo().intValue()));
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
                    */
                }
                if (!solicitudTO.getListaTareaReclamo().isEmpty()) {
                    solicitudTO.setVerTareaReclamo(true);
                }
                break;
            }
        }
        if (solicitudTO.getUrlAdjunto() != null) {
            solicitudTO.setVerDescargaAdjunto(true);
        }
        try {
            if (solicitudTO.getObservacion() != null) {
                solicitudTO.setDescripcionObservacion(convertClob = clobToString(solicitudTO.getObservacion()));
            } else {
                solicitudTO.setDescripcionObservacion("");
            }
            /*if (!solicitudTO.getNumTareaAsma().equals(BigDecimal.ZERO)) {
                detalleTareaTO = getLocalizacionFacadeLocal().obtenerDetalleTarea(solicitudTO.getNumTareaAsma());
                listaAtencionTareaTO = getLocalizacionFacadeLocal().obtenerAtencionTarea(solicitudTO.getNumTareaAsma());
            } */
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verAtencionesReclamo() {
        listaAtencionTareaTO.clear();
        /*if (idTareaReclamoSeleccionado != null) {
            listaAtencionTareaTO.addAll(getLocalizacionFacadeLocal().obtenerAtencionTarea(idTareaReclamoSeleccionado));
        } */
        verAtencionesTareaReclamo = true;
    }

    public void cargarEstado(BigDecimal idSolicitud) {
        solicitudTO = new SolicitudTO();
        int c = -1;
        for (SolicitudTO solicitud : listaSolicitudes) {
            if (solicitud.getIdSolicitud().equals(idSolicitud)) {
                setSolicitudTO(solicitud);
                convertClob = clobToString(solicitudTO.getMotivoRechazo());
                break;
            }
        }
    }

    public void startDateFilter(AjaxBehaviorEvent event) {
        //System.out.println(((Calendar) event.getSource()).getValue().toString());
        //System.out.println("Objeto " + event.toString());
    }

    public void cambioComuna(ValueChangeEvent e) {
        String id = (String) e.getNewValue();
        this.listaPuntos.clear();
        try {
            this.listaPuntos = getLocalizacionFacadeLocal().listaVistaInstalacionPorComuna(new BigDecimal(id), tipoInstalacion);
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cambioSolicitud(AjaxBehaviorEvent e) {
        SelectOneMenu selectOneMenu = ((SelectOneMenu) e.getSource());
        for (SolicitudTO solicitudIterTO : listaSolicitudesReclamada) {
            if (solicitudIterTO.getIdSolicitud().equals(new BigDecimal(selectOneMenu.getSubmittedValue().toString()))) {
                idAtencion = solicitudIterTO;
                break;
            }
        }
    }

    public void cambioValorAutoComplete(SelectEvent seleccionado) {
        setIdCruce((VistaInstalacionTO) seleccionado.getObject());
        cruce = idCruce.getIdCruce();
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

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public boolean isVerPopupRechazar() {
        return verPopupRechazar;
    }

    public void setVerPopupRechazar(boolean verPopupRechazar) {
        this.verPopupRechazar = verPopupRechazar;
    }

    public BigDecimal getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(BigDecimal numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getConvertClob() {
        return convertClob;
    }

    public void setConvertClob(String convertClob) {
        this.convertClob = convertClob;
    }

    public boolean isVerPopupDetalle() {
        return verPopupDetalle;
    }

    public void setVerPopupDetalle(boolean verPopupDetalle) {
        this.verPopupDetalle = verPopupDetalle;
    }

    public SolicitudTO getSolicitudTO() {
        return solicitudTO;
    }

    public void setSolicitudTO(SolicitudTO solicitudTO) {
        this.solicitudTO = solicitudTO;
    }

    public List<SolicitudTableTO> getListaSolicitudesTabla() {
        return listaSolicitudesTabla;
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

    public List<CodigoTO> getListaCodigo() {
        return listaCodigo;
    }

    public void setListaCodigo(List<CodigoTO> listaCodigo) {
        this.listaCodigo = listaCodigo;
    }

    public List<ComunaTO> getListaComunasSelect() {
        return listaComunasSelect;
    }

    public void setListaComunasSelect(List<ComunaTO> listaComunasSelect) {
        this.listaComunasSelect = listaComunasSelect;
    }

    public boolean isVerPopupTab() {
        return verPopupTab;
    }

    public void setVerPopupTab(boolean verPopupTab) {
        this.verPopupTab = verPopupTab;
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

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public String getComboHabilitada() {
        return comboHabilitada;
    }

    public void setComboHabilitada(String comboHabilitada) {
        this.comboHabilitada = comboHabilitada;
    }

    public SolicitudTO getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(SolicitudTO idAtencion) {
        this.idAtencion = idAtencion;
    }

    public List<SolicitudTO> getListaSolicitudesIngresada() {
        return listaSolicitudesIngresada;
    }

    public void setListaSolicitudesIngresada(List<SolicitudTO> listaSolicitudesIngresada) {
        this.listaSolicitudesIngresada = listaSolicitudesIngresada;
    }

    public List<SolicitudTO> getListaSolicitudesReclamada() {
        return listaSolicitudesReclamada;
    }

    public void setListaSolicitudesReclamada(List<SolicitudTO> listaSolicitudesReclamada) {
        this.listaSolicitudesReclamada = listaSolicitudesReclamada;
    }

    public DetalleTareaTO getDetalleTareaTO() {
        return detalleTareaTO;
    }

    public void setDetalleTareaTO(DetalleTareaTO detalleTareaTO) {
        this.detalleTareaTO = detalleTareaTO;
    }

    public List<AtencionTareaTO> getListaAtencionTareaTO() {
        return listaAtencionTareaTO;
    }

    public void setListaAtencionTareaTO(List<AtencionTareaTO> listaAtencionTareaTO) {
        this.listaAtencionTareaTO = listaAtencionTareaTO;
    }

    public BigDecimal getCruce() {
        return cruce;
    }

    public void setCruce(BigDecimal cruce) {
        this.cruce = cruce;
    }

    public List<VistaReclamoCoordinacionTO> getListaReclamosHistoricos() {
        return listaReclamosHistoricos;
    }

    public void setListaReclamosHistoricos(List<VistaReclamoCoordinacionTO> listaReclamosHistoricos) {
        this.listaReclamosHistoricos = listaReclamosHistoricos;
    }

    public boolean isVerAtencionesTareaReclamo() {
        return verAtencionesTareaReclamo;
    }

    public void setVerAtencionesTareaReclamo(boolean verAtencionesTareaReclamo) {
        this.verAtencionesTareaReclamo = verAtencionesTareaReclamo;
    }

    public BigDecimal getIdTareaReclamoSeleccionado() {
        return idTareaReclamoSeleccionado;
    }

    public void setIdTareaReclamoSeleccionado(BigDecimal idTareaReclamoSeleccionado) {
        this.idTareaReclamoSeleccionado = idTareaReclamoSeleccionado;
    }

    public String getAlto() {
        return alto;
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

}
