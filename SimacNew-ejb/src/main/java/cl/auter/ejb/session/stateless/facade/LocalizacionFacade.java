package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.CodigoDAO;
import cl.auter.ejb.dao.ComunaDAO;
import cl.auter.ejb.dao.CruceDAO;
import cl.auter.ejb.dao.CruceDocumentoDAO;
import cl.auter.ejb.dao.DispositivoDAO;
import cl.auter.ejb.dao.FasePPRDAO;
import cl.auter.ejb.dao.InstalacionDAO;
import cl.auter.ejb.dao.PoligonoDAO;

import cl.auter.ejb.dao.RolUsuarioDAO;
import cl.auter.ejb.dao.SMADAO;
import cl.auter.ejb.dao.SolicitudDAO;
import cl.auter.ejb.dao.UsuarioDAO;
import cl.auter.ejb.dao.VistaApagadosMesDAO;
import cl.auter.ejb.dao.VistaComunaRegionDAO;
import cl.auter.ejb.dao.VistaContEstadoDAO;
import cl.auter.ejb.dao.VistaCruceDAO;
import cl.auter.ejb.dao.VistaInstalacionDAO;
import cl.auter.ejb.dao.VistaMovilDAO;
import cl.auter.ejb.dao.VistaMovilHistoricoDao;
import cl.auter.ejb.dao.VistaRegionesMonitoreoDAO;
import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.ejb.entity.bmp.ComunaEntity;
import cl.auter.ejb.entity.bmp.CruceDocumentoEntity;
import cl.auter.ejb.entity.bmp.CruceEntity;
import cl.auter.ejb.entity.bmp.DispositivoEntity;
import cl.auter.ejb.entity.bmp.FasePPREntity;
import cl.auter.ejb.entity.bmp.InstalacionEntity;
import cl.auter.ejb.entity.bmp.PoligonoComunalEntity;

import cl.auter.ejb.entity.bmp.RolUsuarioEntity;
import cl.auter.ejb.entity.bmp.SolicitudEntity;
import cl.auter.ejb.entity.bmp.UsuarioEntity;
import cl.auter.ejb.entity.bmp.VistaContEstadoEntity;
import cl.auter.ejb.entity.bmp.VistaCruceEntity;
import cl.auter.ejb.entity.bmp.VistaDispApaMesEntity;
import cl.auter.ejb.entity.bmp.VistaInstalacionEntity;
import cl.auter.ejb.entity.bmp.VistaMovilEntity;
import cl.auter.ejb.entity.bmp.VistaMovilHistoricoEntity;
import cl.auter.ejb.entity.bmp.VistaRegionComunaEntity;
import cl.auter.ejb.entity.bmp.VistaRegionesMonitoreoEntity;
import cl.auter.ejb.patron.bo.MantencionBO;
import cl.auter.ejb.patron.bo.MonitoreoOnlineBO;
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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LocalizacionFacade implements LocalizacionFacadeLocal {

    @PersistenceContext(unitName = "Georeferencia")
    private EntityManager entityManager;

    @Override
    public void guardaSolicitud(SolicitudTO solicitudTO) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        solicitudDAO.guardar(solicitudTO);
    }

    @Override
    public void eliminaSolicitud(SolicitudTO solicitudTO) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        solicitudDAO.eliminar(solicitudTO);
    }

    @Override
    public void editarSolicitud(SolicitudTO solicitudTO) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        solicitudDAO.editar(solicitudTO);
    }

    @Override
    public List<SolicitudTO> listaSolicitudes() throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarTodos();
    }

    @Override
    public List<SolicitudTO> buscarSolicitudPorComuna(BigDecimal idComuna) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarSolicitudPorComuna(idComuna);
    }

    @Override
    public List<SolicitudTO> buscarSolicitudesPorFecha(Date fecIni, Date fecEnd) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarSolicitudPorFecha(fecIni, fecEnd);
    }

    @Override
    public List<SolicitudTO> buscarSolComFecha(BigDecimal idComuna, Date fecIni, Date fecEnd) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarSolicitudComFec(idComuna, fecIni, fecEnd);
    }

    @Override
    public SolicitudTO buscarSolicitudPorId(BigDecimal idSolicitud) throws Exception {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarSolicitudPorId(idSolicitud);
    }

    @Override
    public List<SolicitudTO> listaSolicitudesPorTipoSolicitante(BigDecimal tipo, Date fecIni, Date fecEnd) {
        SolicitudDAO solicitudDAO = new SolicitudDAO(SolicitudEntity.class, entityManager);
        return solicitudDAO.buscarSolicitudPorTipoSolicitante(tipo, fecIni, fecEnd);
    }

    @Override
    public List<ComunaTO> listaComunas() throws Exception {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscarComunaCoordenada();
    }

    @Override
    public List<ComunaTO> listaComunasTodas() throws Exception {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscarTodos();
    }

    @Override
    public List<ComunaTO> listaComunasMonitoreo() throws Exception {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscarComunaMonitoreo();
    }

    @Override
    public List<CodigoTO> listaCodigos() throws Exception {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        return codigoDAO.buscarTodos();
    }

    @Override
    public List<VistaCruceTO> listaVistaCruces() throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaCrucesMonitoreoTodos();
    }

    @Override
    public VistaCruceTO vistaCrucesPorId(BigDecimal idCruce) throws Exception {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        return vistaCruceDAO.buscarCrucesPorId(idCruce);
    }

    @Override
    public List<VistaCruceHistoricoTO> listaVistaCrucesHistorico(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaCrucesHisotricoMonitoreo(idComuna, idCruce, fechaDesde, fechaHasta);
    }

    @Override
    public List<VistaCruceHistoricoTO> listaVistaCrucesHistoricoPorComuna(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaCrucesHistoricoPorComuna(idComuna, fechaDesde, fechaHasta);
    }

    @Override
    public List<VistaInstalacionTO> listaVistaInstalacionMonitoreo(CodigoTO[] tipoInstalacion) {
        MantencionBO mantencionBO = new MantencionBO((entityManager));
        return mantencionBO.listaVistaInstalacionMonitoreo(tipoInstalacion);
    }

    @Override
    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception {
        MantencionBO mantencionBO = new MantencionBO(entityManager);
        return mantencionBO.buscarInformeDiario(idComuna, idCruce, fechaDesde, fechaHasta);
    }

    @Override
    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) throws Exception {
        MantencionBO mantencionBO = new MantencionBO(entityManager);
        return mantencionBO.buscarInformeDiario(idComuna, fechaDesde, fechaHasta);
    }

    @Override
    public ComunaTO buscaComunaPorId(BigDecimal idComuna) throws Exception {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscaPorId(idComuna);
    }

    @Override
    public List<VistaCruceTO> listaVistaCrucesEstado(String estadoOperacional) {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.buscarPorEstado(estadoOperacional);
    }

    @Override
    public List<VistaCruceTO> listaVistaCrucesPorComuna(BigDecimal idComuna, List listaEstadoOperacional) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaCrucesMonitoreo(idComuna, listaEstadoOperacional);
    }

    @Override
    public List<VistaCruceTO> listaVistaCrucesPorComuna(BigDecimal idComuna) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaCrucesMonitoreo(idComuna);
    }

    @Override
    public List<VistaCruceTO> listaVistaCrucesNoEncendidos(BigDecimal idComuna) {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaVistaNoEncendidos(idComuna);
    }

    @Override
    public List<MovilTO> listaMovil(BigDecimal idComuna) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaMovil(idComuna);
    }

    @Override
    public List<VistaInstalacionTO> listaVistaInstalacionPorComuna(BigDecimal idComuna, CodigoTO[] tipoInstalacion) {
        MantencionBO mantencionBO = new MantencionBO((entityManager));
        return mantencionBO.listaVistaInstalaciones(idComuna, tipoInstalacion);
    }

    @Override
    public List<VistaDocumentoTO> listaDocumentoPorCruce(BigDecimal idCruce) throws Exception {
        MantencionBO mantencionBO = new MantencionBO((entityManager));
        return mantencionBO.listaInstalacionesDocumentos(idCruce);
    }

    @Override
    public CruceTO buscarCrucePorId(BigDecimal idCruce) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.buscarCrucePorId(idCruce);
    }

    @Override
    public boolean existeCruce(BigDecimal idCruce) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.existeCruce(idCruce);
    }

    @Override
    public void guardarCruce(CruceTO cruceTO) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        cruceDAO.guardar(cruceTO);
    }

    @Override
    public void editarCruce(CruceTO cruceTO) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        cruceDAO.editar(cruceTO);
    }

    @Override
    public void eliminarCruce(CruceTO cruceTO) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        cruceDAO.eliminar(cruceTO);
    }

    /*   @Override
    public BigDecimal paAddTask(AddTaskTO addTaskTO, String idComuna) throws Exception {
        SMADAO smaDAO = new SMADAO();
        return smaDAO.paAddTask(addTaskTO, idComuna);
    }

    @Override
    public DetalleTareaTO obtenerDetalleTarea(BigDecimal idTarea) throws Exception {
        SMADAO smaDAO = new SMADAO();
        return smaDAO.obtenerDetalleTarea(idTarea);
    }

    @Override
    public List<AtencionTareaTO> obtenerAtencionTarea(BigDecimal idTarea){
        SMADAO smaDAO = new SMADAO();
        return smaDAO.obtenerAtencionTarea(idTarea);
    }

    @Override
    public List<EstadisticaTO> obtenerEstadistica(BigDecimal idCruce, String idComuna, Date fechaDesde, Date fechaHasta) throws Exception {
        SMADAO smaDAO = new SMADAO();
        return smaDAO.obtenerEstadistica(idCruce, idComuna, fechaDesde, fechaHasta);
    }
    
    @Override
    public List<TiempoRespuestaTO> obtenerTiempoRespuesta(Date fechaDesde, Date fechaHasta)throws Exception{
        SMADAO smaDAO = new SMADAO();
        return smaDAO.obtenerTiemposRespuesta(fechaDesde, fechaHasta);
    }

    @Override
    public List<AtencionTareaTO> obtenerDetalleAtencion(String nombreEquipo, String tipoFalla, BigDecimal idCruce, String idComuna, Date fechaDesde, Date fechaHasta) throws Exception {
        SMADAO smaDAO = new SMADAO();
        return smaDAO.obtenerDetalleAtencion(nombreEquipo, tipoFalla, idCruce, idComuna, fechaDesde, fechaHasta);
    }
     */
    @Override
    public UserTO buscarUsuarioPorId(String identificador) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.buscarUsuarioPorId(identificador);
    }

    @Override
    public void editarUsuario(UserTO userTO) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.editar(userTO);
    }

    @Override
    public List<UserTO> listaUsuarioPorComuna(BigDecimal idComuna) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.listaUsuarioPorComuna(idComuna);
    }

    @Override
    public List<UserTO> listaUsuario() throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.listaUsuario();
    }

    @Override
    public boolean existeUsuario(String usuario) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        return usuarioDAO.existeUsuario(usuario);
    }

    @Override
    public void guardarUsuario(UserTO userTO) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.guardar(userTO);
    }

    @Override
    public void eliminarUsuario(UserTO userTO) throws Exception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.eliminar(userTO);
    }
    
   @Override
    public void actualizarComunaUsuario(BigDecimal idcomuna) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(UsuarioEntity.class, entityManager);
        usuarioDAO.actualizarComunaUsuario(idcomuna);
    }

    @Override
    public List<CruceDocumentoTO> buscarDocumentoPorIdCruce(BigDecimal idCruce) throws Exception {
        CruceDocumentoDAO cruceDocumentoDAO = new CruceDocumentoDAO(CruceDocumentoEntity.class, entityManager);
        return cruceDocumentoDAO.buscarDocumentoPorIdCruce(idCruce);
    }

    @Override
    public void editarInstalacion(InstalacionTO instalacion) throws Exception {
        InstalacionDAO instalacionDAO = new InstalacionDAO(InstalacionEntity.class, entityManager);
        instalacionDAO.editar(instalacion);
    }

    @Override
    public void guardarInstalacion(InstalacionTO instalacion) throws Exception {
        InstalacionDAO instalacionDAO = new InstalacionDAO(InstalacionEntity.class, entityManager);
        instalacionDAO.guardar(instalacion);
    }

    @Override
    public InstalacionTO buscarInstalacionPorId(BigDecimal idInstalacion) throws Exception {
        InstalacionDAO instalacionDAO = new InstalacionDAO(InstalacionEntity.class, entityManager);
        return instalacionDAO.buscarInstalacionPorId(idInstalacion);
    }

    @Override
    public List<InstalacionTO> buscarInstalacionPorIdCruce(BigDecimal idCruce) throws Exception {
        InstalacionDAO instalacionDAO = new InstalacionDAO(InstalacionEntity.class, entityManager);
        return instalacionDAO.buscarInstalacionPorIdCruce(idCruce);
    }

    @Override
    public List<InstalacionTO> buscarInstalacionPorIdComuna(BigDecimal idComuna) {
        VistaInstalacionDAO instalacionDAO = new VistaInstalacionDAO(VistaInstalacionEntity.class, entityManager);
        return instalacionDAO.buscarInstalacionPorIdComuna(idComuna);
    }

    @Override
    public List<CruceTO> buscarCrucePorIdComuna(BigDecimal idComuna) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.buscarCrucePorIdComuna(idComuna);
    }

    @Override
    public List<CruceTO> listaCruceTodos() throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.buscarCruceTodos();
    }

    @Override
    public List<CruceTO> listaCrucesComuna(BigDecimal IdComuna) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.buscarCrucePorIdComuna(IdComuna);
    }

    @Override
    public boolean existeDispositivo(BigDecimal idDispositivo) throws Exception {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.existeDispositivo(idDispositivo);
    }

    @Override
    public void eliminarInstalacion(InstalacionTO instalacionTO) throws Exception {
        InstalacionDAO instalacionDAO = new InstalacionDAO(InstalacionEntity.class, entityManager);
        instalacionDAO.eliminar(instalacionTO);
    }

    @Override
    public VistaInstalacionTO buscarVistaInstalacionPorIdCruce(BigDecimal idCruce) throws Exception {
        VistaInstalacionDAO vistaInstalacionDAO = new VistaInstalacionDAO(VistaInstalacionEntity.class, entityManager);
        return vistaInstalacionDAO.buscarVistaInstalacionPorIdCruce(idCruce);
    }

    @Override
    public VistaInstalacionTO buscarVistaInstalacionPorIdInstalacion(BigDecimal idInstalacion) throws Exception {
        VistaInstalacionDAO vistaInstalacionDAO = new VistaInstalacionDAO(VistaInstalacionEntity.class, entityManager);
        return vistaInstalacionDAO.buscarVistaInstalacionPorIdInstalacion(idInstalacion);
    }

    @Override
    public RolUsuarioTO buscarRolUsuario(String idUsuario) throws Exception {
        RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO(RolUsuarioEntity.class, entityManager);
        return rolUsuarioDAO.buscarRolUsuario(idUsuario);
    }

    @Override
    public void guardarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception {
        RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO(RolUsuarioEntity.class, entityManager);
        rolUsuarioDAO.guardar(rolUsuarioTO);
    }

    @Override
    public void editarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception {
        RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO(RolUsuarioEntity.class, entityManager);
        rolUsuarioDAO.editar(rolUsuarioTO);
    }

    @Override
    public void eliminarRolUsuario(RolUsuarioTO rolUsuarioTO) throws Exception {
        RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO(RolUsuarioEntity.class, entityManager);
        rolUsuarioDAO.eliminar(rolUsuarioTO);
    }

    @Override
    public CruceDocumentoTO buscarCruceDocumentoPorId(BigDecimal idDocumento) throws Exception {
        CruceDocumentoDAO cruceDocumentoDAO = new CruceDocumentoDAO(CruceDocumentoEntity.class, entityManager);
        return cruceDocumentoDAO.buscarCruceDocumentoPorId(idDocumento);
    }

    @Override
    public void eliminarDocumento(CruceDocumentoTO cruceDocumentoTO) throws Exception {
        CruceDocumentoDAO cruceDocumentoDAO = new CruceDocumentoDAO(CruceDocumentoEntity.class, entityManager);
        cruceDocumentoDAO.eliminar(cruceDocumentoTO);
    }

    @Override
    public void guardarDocumento(CruceDocumentoTO cruceDocumentoTO) throws Exception {
        CruceDocumentoDAO cruceDocumentoDAO = new CruceDocumentoDAO(CruceDocumentoEntity.class, entityManager);
        cruceDocumentoDAO.guardar(cruceDocumentoTO);
    }

    @Override
    public boolean existeRolUsuarioTO(String identificador) throws Exception {
        RolUsuarioDAO rolUsuarioDAO = new RolUsuarioDAO(RolUsuarioEntity.class, entityManager);
        return rolUsuarioDAO.existeRolUsuarioTO(identificador);
    }

    @Override
    public List<DispositivoTO> listaDispositivoTO() throws Exception {
        DispositivoDAO dispositivoDAO = new DispositivoDAO(DispositivoEntity.class, entityManager);
        return dispositivoDAO.buscarDispositivoTodos();
    }

    @Override
    public DispositivoTO buscarDispositivoPorId(BigDecimal idDispositivo) {
        DispositivoDAO dispositivoDAO = new DispositivoDAO(DispositivoEntity.class, entityManager);
        return dispositivoDAO.buscarDispositivoPorId(idDispositivo);
    }

    @Override
    public void guardarDispositivo(DispositivoTO dispositivoTO) throws Exception {
        DispositivoDAO dispositivoDAO = new DispositivoDAO(DispositivoEntity.class, entityManager);
        dispositivoDAO.guardar(dispositivoTO);
    }

    @Override
    public void editarDispositivo(DispositivoTO dispositivoTO) throws Exception {
        DispositivoDAO dispositivoDAO = new DispositivoDAO(DispositivoEntity.class, entityManager);
        dispositivoDAO.editar(dispositivoTO);
    }

    @Override
    public void eliminarDispositivo(DispositivoTO dispositivoTO) throws Exception {
        DispositivoDAO dispositivoDAO = new DispositivoDAO(DispositivoEntity.class, entityManager);
        dispositivoDAO.eliminar(dispositivoTO);
    }

    @Override
    public List<MovilTO> listaMovilTodos(BigDecimal idComuna) throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaMovilTodos(idComuna);
    }

    @Override
    public List<MovilTO> listaMovilTodos() throws Exception {
        MonitoreoOnlineBO monitoreoOnlineBO = new MonitoreoOnlineBO(entityManager);
        return monitoreoOnlineBO.listaMovilTodos();
    }

    @Override
    public List<VistaMovilTO> listaVistaMovilTodos() throws Exception {
        VistaMovilDAO dao = new VistaMovilDAO(VistaMovilEntity.class, entityManager);
        return dao.listaVistaMovilTodos();
    }

    @Override
    public List<VistaMovilTO> listaVistaMovilComuna(BigDecimal idComuna) throws Exception {
        VistaMovilDAO dao = new VistaMovilDAO(VistaMovilEntity.class, entityManager);
        return dao.listaVistaMovilComuna(idComuna);
    }

    @Override
    public List<VistaMovilHistoricoTO> listaVistaMovilHistorico(MovilTO movil, Date fechaDesde, Date fechaHasta) throws Exception {
        VistaMovilHistoricoDao vistaMovilHistoricoDao = new VistaMovilHistoricoDao(VistaMovilHistoricoEntity.class, entityManager);
        return vistaMovilHistoricoDao.buscarPorFechas(movil, fechaDesde, fechaHasta);
    }

    @Override
    public List<VistaMovilHistoricoTO> listaMovilCruce(BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception {
        VistaMovilHistoricoDao vistaMovilHistoricoDao = new VistaMovilHistoricoDao(VistaMovilHistoricoEntity.class, entityManager);
        return vistaMovilHistoricoDao.listaMovilCruce(idCruce, fechaDesde, fechaHasta);
    }

    @Override
    public List<MovilTO> listaMovilComuna(BigDecimal IdComuna) throws Exception {
        ComunaDAO comuna = new ComunaDAO(ComunaEntity.class, entityManager);
        return comuna.listaMovilComuna(IdComuna);
    }

    @Override
    public ComunaTO obtenerDatosContacto(BigDecimal idComuna) throws Exception {
        ComunaDAO comuna = new ComunaDAO(ComunaEntity.class, entityManager);
        return comuna.buscaPorId(idComuna);
    }

    @Override
    public List<CruceTO> buscarCrucesPorDimac(BigDecimal idDispositivo) {
        CruceDAO cruceDAO = new CruceDAO(CruceEntity.class, entityManager);
        return cruceDAO.buscarCrucePorDimac(idDispositivo);
    }

    @Override
    public List<FasePPRTO> obtenerFasesPorCruce(BigDecimal idCruce) {
        FasePPRDAO fasePPRDAO = new FasePPRDAO(FasePPREntity.class, entityManager);
        return fasePPRDAO.buscarFasesPorCruce(idCruce);
    }

    @Override
    public void guardarFasePPR(FasePPRTO fasePPRTO) {
        FasePPRDAO fasePPRDAO = new FasePPRDAO(FasePPREntity.class, entityManager);
        fasePPRDAO.guardar(fasePPRTO);
    }

    @Override
    public void editarFasePPR(FasePPRTO fasePPRTO) {
        FasePPRDAO fasePPRDAO = new FasePPRDAO(FasePPREntity.class, entityManager);
        fasePPRDAO.editar(fasePPRTO);
    }

    @Override
    public void eliminarFasePPR(FasePPRTO fasePPRTO) {
        FasePPRDAO fasePPRDAO = new FasePPRDAO(FasePPREntity.class, entityManager);
        fasePPRDAO.eliminar(fasePPRTO);
    }

    @Override
    public List<ComunaTO> listaComunasMonitoreoUOCT() {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscarComunaMonitoreoUOCT();
    }

    @Override
    public List<RegionTO> listaRegionTodas() {
        VistaRegionesMonitoreoDAO regionDAO = new VistaRegionesMonitoreoDAO(VistaRegionesMonitoreoEntity.class, entityManager);
        return regionDAO.buscarTodos();
    }

    @Override
    public List<ComunaTO> buscarComunasPorRegion(int id_region) {
        ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        return comunaDAO.buscarComunasPorRegion(id_region);
    }

    @Override
    public VistaContEstadoTO obtenerEstadosDimacTodos() {
        VistaContEstadoDAO contDAO = new VistaContEstadoDAO(VistaContEstadoEntity.class, entityManager);
        return contDAO.buscarTodos();
    }

    @Override
    public VistaContEstadoTO obtenerEstadosDimacPorComuna(BigDecimal idComuna) {
        VistaCruceDAO contDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        VistaContEstadoTO vista = new VistaContEstadoTO();
        vista.setEncendidos(contDAO.numEstadoComuna(idComuna, "1"));
        vista.setObservacion(contDAO.numEstadoComuna(idComuna, "2"));
        vista.setApagado(contDAO.numEstadoComuna(idComuna, "3"));
        vista.setInvalidos(contDAO.numEstadoComuna(idComuna, "4"));
        return vista;
    }

    @Override
    public List<VistaSolicitudTO> obtenerSolPend() {
        /* VistaSolicitudDAO dao = new VistaSolicitudDAO(VistaSolicitudesEntity.class, entityManager);
        return dao.buscarSolicitudPendiente(); */
        return null;
    }

    @Override
    public List<VistaSolicitudTO> obtenerSolPendPorComuna(BigDecimal idComuna) {
        /*  VistaSolicitudDAO dao = new VistaSolicitudDAO(VistaSolicitudesEntity.class, entityManager);
        return dao.buscarSolicitudPendienteComuna(idComuna); */
        return null;
    }

    @Override
    public List<VistaSolicitudPendienteTO> obtenerSolPendTodas() {
        /* VistaSolicitudesPendientesDAO dao = new VistaSolicitudesPendientesDAO(VistaSolicitudesPendientesEntity.class, entityManager);
        return dao.buscarTodos(); */
        return null;
    }

    @Override
    public List<VistaApagadosUltMesTO> listaApagadosMesTodos() {
        VistaApagadosMesDAO dao = new VistaApagadosMesDAO(VistaDispApaMesEntity.class, entityManager);
        return dao.buscarTodos();
    }

    @Override
    public List<VistaCruceTO> buscarPorEstado(String estado) {
        MonitoreoOnlineBO mon = new MonitoreoOnlineBO(entityManager);
        return mon.buscarPorEstado(estado);
    }

    @Override
    public List<VistaComunaRegionTO> listaVistaComunasReg() {
        VistaComunaRegionDAO dao = new VistaComunaRegionDAO(VistaRegionComunaEntity.class, entityManager);
        return dao.buscarTodos();

    }

    @Override
    public List<PoligonoTO> obtenerPoligonoPorComuna(int idComuna) {
        PoligonoDAO dao = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        return dao.buscarPoligonoComuna(idComuna);
    }

    @Override
    public void crearPoligonoPorComuna(PoligonoTO poligono) throws Exception {
        PoligonoDAO dao = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        dao.guardar(poligono);
    }

    @Override
    public void editarPoligonoPorComuna(PoligonoTO poligono) throws Exception {
        PoligonoDAO dao = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        dao.editar(poligono);
    }
    
    @Override
    public void eliminarPoligonoPorComuna(BigDecimal idcomuna) {
        PoligonoDAO dao = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        dao.removerPoligonoPorComuna(idcomuna);
    }
    
    @Override
    public void editarComuna(ComunaTO comunaTO){
        ComunaDAO dao = new ComunaDAO(ComunaEntity.class, entityManager);
        dao.editarComuna(comunaTO);
    }
    
    @Override
    public void crearComuna(ComunaTO comunaTO){
        ComunaDAO dao = new ComunaDAO(ComunaEntity.class, entityManager);
        dao.guardarComuna(comunaTO);
    }
    
    @Override
    public void eliminarComuna(ComunaTO comunaTO){
        ComunaDAO dao = new ComunaDAO(ComunaEntity.class, entityManager);
        dao.borrarComuna(comunaTO);
    }

}
