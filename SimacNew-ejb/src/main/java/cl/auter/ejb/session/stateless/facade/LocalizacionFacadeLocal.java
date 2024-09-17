package cl.auter.ejb.session.stateless.facade;

import cl.auter.patron.to.AddTaskTO;
import cl.auter.patron.to.AtencionTareaTO;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.CruceDocumentoTO;
import cl.auter.patron.to.CruceTO;
import cl.auter.patron.to.DetalleTareaTO;
import cl.auter.patron.to.DispositivoTO;
import cl.auter.patron.to.EstadisticaTO;
import cl.auter.patron.to.FasePPRTO;
import cl.auter.patron.to.InformeDiarioTO;
import cl.auter.patron.to.InstalacionTO;
import cl.auter.patron.to.MovilTO;
import cl.auter.patron.to.PoligonoTO;
import cl.auter.patron.to.RegionTO;
import cl.auter.patron.to.RolUsuarioTO;
import cl.auter.patron.to.SolicitudTO;
import cl.auter.patron.to.TiempoRespuestaTO;
import cl.auter.patron.to.UserTO;
import cl.auter.patron.to.VistaApagadosUltMesTO;
import cl.auter.patron.to.VistaContEstadoTO;
import cl.auter.patron.to.VistaCruceHistoricoTO;
import cl.auter.patron.to.VistaCruceTO;
import cl.auter.patron.to.VistaDocumentoTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.patron.to.VistaMovilHistoricoTO;
import cl.auter.patron.to.VistaMovilTO;
import cl.auter.patron.to.VistaComunaRegionTO;
import cl.auter.patron.to.VistaSolicitudPendienteTO;
import cl.auter.patron.to.VistaSolicitudTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LocalizacionFacadeLocal {

    public void guardaSolicitud(SolicitudTO solicitudTO) throws Exception;

    public void eliminaSolicitud(SolicitudTO solicitudTO) throws Exception;

    public void editarSolicitud(SolicitudTO solicitudTO) throws Exception;

    public List<SolicitudTO> listaSolicitudes() throws Exception;

    public List<SolicitudTO> buscarSolicitudPorComuna(BigDecimal idComuna) throws Exception;

    public List<SolicitudTO> buscarSolComFecha(BigDecimal idComuna, Date fecIni, Date fecEnd) throws Exception;

    public List<SolicitudTO> buscarSolicitudesPorFecha(Date fecIni, Date fecEnd) throws Exception;

    public SolicitudTO buscarSolicitudPorId(BigDecimal idSolicitud) throws Exception;

    public List<ComunaTO> listaComunas() throws Exception;

    public List<ComunaTO> listaComunasTodas() throws Exception;

    public List<ComunaTO> listaComunasMonitoreo() throws Exception;

    public List<CodigoTO> listaCodigos() throws Exception;

    public List<VistaCruceTO> listaVistaCruces() throws Exception;

    public VistaCruceTO vistaCrucesPorId(BigDecimal idCruce) throws Exception;

    public List<CruceTO> listaCrucesComuna(BigDecimal IdComuna) throws Exception;

    public List<VistaCruceHistoricoTO> listaVistaCrucesHistorico(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception;

    public List<VistaCruceHistoricoTO> listaVistaCrucesHistoricoPorComuna(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) throws Exception;

    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception;

    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) throws Exception;

    public ComunaTO buscaComunaPorId(BigDecimal idComuna) throws Exception;

    public List<VistaCruceTO> listaVistaCrucesPorComuna(BigDecimal idComuna, List listaEstadoOperacional) throws Exception;

    public List<VistaCruceTO> listaVistaCrucesPorComuna(BigDecimal IdComuna) throws Exception;

    public List<VistaCruceTO> listaVistaCrucesEstado(String estadoOperacional);

    public List<VistaCruceTO> listaVistaCrucesNoEncendidos(BigDecimal idComuna);

    public List<MovilTO> listaMovil(BigDecimal IdComuna) throws Exception;

    public List<MovilTO> listaMovilTodos(BigDecimal IdComuna) throws Exception;

    public List<MovilTO> listaMovilTodos() throws Exception;

    public List<MovilTO> listaMovilComuna(BigDecimal IdComuna) throws Exception;

    public List<VistaMovilTO> listaVistaMovilTodos() throws Exception;

    public List<VistaMovilTO> listaVistaMovilComuna(BigDecimal idComuna) throws Exception;

    public List<VistaInstalacionTO> listaVistaInstalacionPorComuna(BigDecimal idComuna, CodigoTO[] tipoInstalacion);

    public VistaInstalacionTO buscarVistaInstalacionPorIdCruce(BigDecimal idCruce) throws Exception;

    public VistaInstalacionTO buscarVistaInstalacionPorIdInstalacion(BigDecimal idInstalacion) throws Exception;

    public List<VistaDocumentoTO> listaDocumentoPorCruce(BigDecimal idCruce) throws Exception;

    public CruceTO buscarCrucePorId(BigDecimal idCruce) throws Exception;

    public List<CruceTO> buscarCrucePorIdComuna(BigDecimal idComuna) throws Exception;

    public List<CruceTO> listaCruceTodos() throws Exception;

    public boolean existeCruce(BigDecimal idCruce) throws Exception;

    public void guardarCruce(CruceTO cruceTO) throws Exception;

    public void editarCruce(CruceTO cruceTO) throws Exception;

    public void eliminarCruce(CruceTO cruceTO) throws Exception;

    public List<UserTO> listaUsuario() throws Exception;

    public List<UserTO> listaUsuarioPorComuna(BigDecimal idComuna) throws Exception;

    public UserTO buscarUsuarioPorId(String identificador) throws Exception;

    public boolean existeUsuario(String usuario) throws Exception;

    public void guardarUsuario(UserTO userTO) throws Exception;

    public void editarUsuario(UserTO userTO) throws Exception;

    public void eliminarUsuario(UserTO userTO) throws Exception;
    
    public void actualizarComunaUsuario(BigDecimal idcomuna);
    /*
    public BigDecimal paAddTask(AddTaskTO addTaskTO, String idComuna)throws Exception;
    public DetalleTareaTO obtenerDetalleTarea(BigDecimal idTarea)throws Exception;
    public List<AtencionTareaTO> obtenerAtencionTarea(BigDecimal idTarea);
    
    public List<EstadisticaTO> obtenerEstadistica(BigDecimal idCruce,String idComuna, Date fechaDesde, Date fechaHasta)throws Exception;
    public List<TiempoRespuestaTO> obtenerTiempoRespuesta(Date fechaDesde, Date fechaHasta)throws Exception;
    public List<AtencionTareaTO> obtenerDetalleAtencion(String nombreEquipo,String tipoFalla ,BigDecimal idCruce,String idComuna, Date fechaDesde, Date fechaHasta)throws Exception;
     */
    public List<CruceDocumentoTO> buscarDocumentoPorIdCruce(BigDecimal idCruce) throws Exception;

    public CruceDocumentoTO buscarCruceDocumentoPorId(BigDecimal idDocumento) throws Exception;

    public void eliminarDocumento(CruceDocumentoTO cruceDocumentoTO) throws Exception;

    public void guardarDocumento(CruceDocumentoTO cruceDocumentoTO) throws Exception;

    public void guardarInstalacion(InstalacionTO instalacion) throws Exception;

    public void editarInstalacion(InstalacionTO instalacion) throws Exception;

    public InstalacionTO buscarInstalacionPorId(BigDecimal idInstalacion) throws Exception;

    public List<InstalacionTO> buscarInstalacionPorIdCruce(BigDecimal idCruce) throws Exception;

    public void eliminarInstalacion(InstalacionTO instalacionTO) throws Exception;

    public boolean existeDispositivo(BigDecimal idDispositivo) throws Exception;

    public RolUsuarioTO buscarRolUsuario(String idUsuario) throws Exception;

    public void guardarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception;

    public void editarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception;

    public void eliminarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception;

    public boolean existeRolUsuarioTO(String identificador) throws Exception;

    public List<DispositivoTO> listaDispositivoTO() throws Exception;

    public DispositivoTO buscarDispositivoPorId(BigDecimal idDispositivo);

    public void guardarDispositivo(DispositivoTO dispositivoTO) throws Exception;

    public void editarDispositivo(DispositivoTO dispositivoTO) throws Exception;

    public void eliminarDispositivo(DispositivoTO dispositivoTO) throws Exception;

    public List<VistaMovilHistoricoTO> listaVistaMovilHistorico(MovilTO movil, Date fechaDesde, Date fechaHasta) throws Exception;

    public List<VistaMovilHistoricoTO> listaMovilCruce(BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception;

    public ComunaTO obtenerDatosContacto(BigDecimal idComuna) throws Exception;

    public List<VistaInstalacionTO> listaVistaInstalacionMonitoreo(CodigoTO[] tipoInstalacion);

    public List<CruceTO> buscarCrucesPorDimac(BigDecimal idDispositivo);

    public List<FasePPRTO> obtenerFasesPorCruce(BigDecimal idCruce);

    public void guardarFasePPR(FasePPRTO fasePPRTO);

    public void editarFasePPR(FasePPRTO fasePPRTO);

    public void eliminarFasePPR(FasePPRTO fasePPRTO);

    public List<SolicitudTO> listaSolicitudesPorTipoSolicitante(BigDecimal tipo, Date fecIni, Date fecEnd);

    public List<ComunaTO> listaComunasMonitoreoUOCT();

    public List<RegionTO> listaRegionTodas();

    public List<ComunaTO> buscarComunasPorRegion(int id_region);

    public VistaContEstadoTO obtenerEstadosDimacTodos();

    public List<VistaSolicitudTO> obtenerSolPend();

    public List<VistaSolicitudTO> obtenerSolPendPorComuna(BigDecimal idComuna);

    public List<VistaSolicitudPendienteTO> obtenerSolPendTodas();

    public List<VistaApagadosUltMesTO> listaApagadosMesTodos();

    public List<VistaCruceTO> buscarPorEstado(String estado);

    public VistaContEstadoTO obtenerEstadosDimacPorComuna(BigDecimal idComuna);

    public List<InstalacionTO> buscarInstalacionPorIdComuna(BigDecimal idComuna);

    public List<VistaComunaRegionTO> listaVistaComunasReg();

    public List<PoligonoTO> obtenerPoligonoPorComuna(int idComuna);

    public void crearPoligonoPorComuna(PoligonoTO poligono) throws Exception;

    public void editarPoligonoPorComuna(PoligonoTO poligono) throws Exception;
    
   public void eliminarPoligonoPorComuna(BigDecimal idcomuna);

    public void editarComuna(ComunaTO comunaTO);
    
    public void crearComuna(ComunaTO comunaTO);
    
    public void eliminarComuna(ComunaTO comunaTO);

}
