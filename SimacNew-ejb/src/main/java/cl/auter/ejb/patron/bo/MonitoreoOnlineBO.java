package cl.auter.ejb.patron.bo;

import cl.auter.ejb.dao.CodigoDAO;
import cl.auter.ejb.dao.MovilDAO;
import cl.auter.ejb.dao.PoligonoDAO;
import cl.auter.ejb.dao.VistaCruceDAO;
import cl.auter.ejb.dao.VistaCruceHistoricoDAO;
import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.ejb.entity.bmp.MovilEntity;
import cl.auter.ejb.entity.bmp.PoligonoComunalEntity;
import cl.auter.ejb.entity.bmp.VistaCruceEntity;
import cl.auter.ejb.entity.bmp.VistaCruceHistoricoEntity;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.MovilTO;
import cl.auter.patron.to.PoligonoTO;
import cl.auter.patron.to.VistaCruceHistoricoTO;
import cl.auter.patron.to.VistaCruceTO;
import cl.auter.util.Util;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

public class MonitoreoOnlineBO {

    private EntityManager entityManager;
    protected Map<String, String> mapaEstadoOperacionalColores = new HashMap<String, String>();
    protected Map<String, String> mapaEstadoOperacional = new HashMap<String, String>();
    protected Map<String, String> mapaValorModo220 = new HashMap<String, String>();
    protected Map<String, String> mapaValorModoUps = new HashMap<String, String>();
    protected Map<String, String> mapaValorModoUtc = new HashMap<String, String>();
    protected Map<String, String> mapaValorModoLam = new HashMap<String, String>();
    protected Map<String, String> mapaImagenEstadoOperacional = new HashMap<String, String>();

    public MonitoreoOnlineBO(EntityManager entityManager) {
        this.entityManager = entityManager;
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        List<CodigoTO> listaCodOperativo = obtenerEstadoOperativo();
        for (CodigoTO codigoTO : listaCodOperativo) {
            mapaEstadoOperacionalColores.put(codigoTO.getCodigo1(), codigoTO.getCodigo2());
            mapaEstadoOperacional.put(codigoTO.getCodigo1(), codigoTO.getDescripcion());
            mapaImagenEstadoOperacional.put(codigoTO.getCodigo1(), codigoTO.getCodigo2());
        }
        for (CodigoTO codigoTO : codigoDAO.buscarPorDominio("EST_VALMOD220")) {
            mapaValorModo220.put(codigoTO.getCodigo1(), codigoTO.getDescripcion());
        }
        for (CodigoTO codigoTO : codigoDAO.buscarPorDominio("EST_VALMODUPS")) {
            mapaValorModoUps.put(codigoTO.getCodigo1(), codigoTO.getDescripcion());
        }
        for (CodigoTO codigoTO : codigoDAO.buscarPorDominio("EST_VALMODUTC")) {
            mapaValorModoUtc.put(codigoTO.getCodigo1(), codigoTO.getDescripcion());
        }
        for (CodigoTO codigoTO : codigoDAO.buscarPorDominio("EST_VALMODLAM")) {
            mapaValorModoLam.put(codigoTO.getCodigo1(), codigoTO.getDescripcion());
        }

    }

    public List<VistaCruceTO> listaVistaCrucesMonitoreo(BigDecimal idComuna, List listaEstadoOperacional) throws Exception {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        List<VistaCruceTO> listaVistaCruces = new ArrayList();
        if (listaEstadoOperacional != null && listaEstadoOperacional.size() > 0) {
            listaVistaCruces.addAll(vistaCruceDAO.buscarCrucesPorComunaYIdDispositivo(idComuna, listaEstadoOperacional));
            for (VistaCruceTO vistaCruce : listaVistaCruces) {
                //vistaCruce.setDescripcionComuna(descripcionComuna);
                vistaCruce.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
                if (vistaCruce.getEstadoOperativo().equals("4")) //si es invalido, cambiar los valores por sin info
                {
                    vistaCruce.setDescripcionValModo220("Sin info");
                    vistaCruce.setDescripcionValModoUps("Sin info");
                    vistaCruce.setDescripcionValModoUtc("Sin info");
                    vistaCruce.setDescripcionValModoLam("Sin info");
                } else {
                    if (vistaCruce.getValModo220() != null) {
                        vistaCruce.setDescripcionValModo220(mapaValorModo220.get(vistaCruce.getValModo220().toString()));
                    } else {
                        vistaCruce.setDescripcionValModo220("Sin info");
                    }
                    if (vistaCruce.getValModoUps() != null) {
                        vistaCruce.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruce.getValModoUps().toString()));
                    } else {
                        vistaCruce.setDescripcionValModoUps("Sin info");
                    }
                    if (vistaCruce.getValModoUtc() != null) {
                        vistaCruce.setDescripcionValModoUtc(mapaValorModoUtc.get(String.valueOf(vistaCruce.getValModoUtc())));
                    } else {
                        vistaCruce.setDescripcionValModoUtc("Sin info");
                    }
                    if (vistaCruce.getValModoLam() != null) {
                        vistaCruce.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruce.getValModoLam().toString()));
                    } else {
                        vistaCruce.setDescripcionValModoLam("Sin info");
                    }

                }
                vistaCruce.setImagenEstadoOperacional(mapaImagenEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
                if (vistaCruce.isIndLamFault() && vistaCruce.getValModoUtc4().equals(new BigDecimal("1"))) {
                    vistaCruce.setImagenEstadoOperacional(vistaCruce.getImagenEstadoOperacional().substring(0, vistaCruce.getImagenEstadoOperacional().length() - 4) + "LF.png");
                }
                vistaCruce.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruce.getFechaAct().toString()));
                vistaCruce.setColorEstadoOperativo(mapaEstadoOperacionalColores.get(vistaCruce.getEstadoOperativo()));
                vistaCruce.setUbicacion(vistaCruce.getUbicacion() + " - " + vistaCruce.getIdCruce());
                if (vistaCruce.getControlador() == null) {
                    vistaCruce.setControlador("SIN INFORMACION");
                }
                if (vistaCruce.getActivo() == null) {
                    vistaCruce.setActivo("SIN INFORMACION");
                }
                if (vistaCruce.getValModo220() == null) {
                    vistaCruce.setValModo220(BigDecimal.ZERO);
                }
                if (vistaCruce.getValModoUps() == null) {
                    vistaCruce.setValModoUps(BigDecimal.ZERO);
                }
                if (vistaCruce.getValModoLam() == null) {
                    vistaCruce.setValModoLam(BigDecimal.ZERO);
                }

            }
        }
        return listaVistaCruces;
    }

    public List<VistaCruceTO> listaVistaCrucesMonitoreo(BigDecimal idComuna) throws Exception {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        // ComunaDAO comunaDAO = new ComunaDAO(ComunaEntity.class, entityManager);
        // String descripcionComuna = comunaDAO.buscaPorId(idComuna).getNombre();
        List<VistaCruceTO> listaVistaCruces = vistaCruceDAO.buscarCrucesPorComunaYIdDispositivo(idComuna);
        for (VistaCruceTO vistaCruce : listaVistaCruces) {
            // vistaCruce.setDescripcionComuna(descripcionComuna);
            vistaCruce.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.getEstadoOperativo().equals("4")) //si es invalido, cambiar los valores por sin info
            {
                vistaCruce.setDescripcionValModo220("Sin info");
                vistaCruce.setDescripcionValModoUps("Sin info");
                vistaCruce.setDescripcionValModoUtc("Sin info");
                vistaCruce.setDescripcionValModoLam("Sin info");
            } else {
                if (vistaCruce.getValModo220() != null) {
                    vistaCruce.setDescripcionValModo220(mapaValorModo220.get(vistaCruce.getValModo220().toString()));
                } else {
                    vistaCruce.setDescripcionValModo220("Sin info");
                }
                if (vistaCruce.getValModoUps() != null) {
                    vistaCruce.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruce.getValModoUps().toString()));
                } else {
                    vistaCruce.setDescripcionValModoUps("Sin info");
                }
                if (vistaCruce.getValModoUtc() != null) {
                    vistaCruce.setDescripcionValModoUtc(mapaValorModoUtc.get(String.valueOf(vistaCruce.getValModoUtc())));
                } else {
                    vistaCruce.setDescripcionValModoUtc("Sin info");
                }
                if (vistaCruce.getValModoLam() != null) {
                    vistaCruce.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruce.getValModoLam().toString()));
                } else {
                    vistaCruce.setDescripcionValModoLam("Sin info");
                }

            }
            vistaCruce.setImagenEstadoOperacional(mapaImagenEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.isIndLamFault() && vistaCruce.getValModoUtc4().equals(new BigDecimal("1"))) {
                vistaCruce.setImagenEstadoOperacional(vistaCruce.getImagenEstadoOperacional().substring(0, vistaCruce.getImagenEstadoOperacional().length() - 4) + "LF.png");
            }
            vistaCruce.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruce.getFechaAct().toString()));
            vistaCruce.setColorEstadoOperativo(mapaEstadoOperacionalColores.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setUbicacion(vistaCruce.getUbicacion() + " - " + vistaCruce.getIdCruce());
            if (vistaCruce.getControlador() == null) {
                vistaCruce.setControlador("SIN INFORMACION");
            }
            if (vistaCruce.getActivo() == null) {
                vistaCruce.setActivo("SIN INFORMACION");
            }
            if (vistaCruce.getValModo220() == null) {
                vistaCruce.setValModo220(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoUps() == null) {
                vistaCruce.setValModoUps(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoLam() == null) {
                vistaCruce.setValModoLam(BigDecimal.ZERO);
            }

        }
        return listaVistaCruces;
    }

    public List<VistaCruceTO> listaVistaCrucesMonitoreoTodos() throws Exception {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        List<VistaCruceTO> listaVistaCruces = vistaCruceDAO.buscarTodos();
        for (VistaCruceTO vistaCruce : listaVistaCruces) {
            vistaCruce.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.getEstadoOperativo().equals("4")) //si es invalido, cambiar los valores por sin info
            {
                vistaCruce.setDescripcionValModo220("Sin info");
                vistaCruce.setDescripcionValModoUps("Sin info");
                vistaCruce.setDescripcionValModoUtc("Sin info");
                vistaCruce.setDescripcionValModoLam("Sin info");
            } else {
                if (vistaCruce.getValModo220() != null) {
                    vistaCruce.setDescripcionValModo220(mapaValorModo220.get(vistaCruce.getValModo220().toString()));
                } else {
                    vistaCruce.setDescripcionValModo220("Sin info");
                }
                if (vistaCruce.getValModoUps() != null) {
                    vistaCruce.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruce.getValModoUps().toString()));
                } else {
                    vistaCruce.setDescripcionValModoUps("Sin info");
                }
                if (vistaCruce.getValModoUtc() != null) {
                    vistaCruce.setDescripcionValModoUtc(mapaValorModoUtc.get(String.valueOf(vistaCruce.getValModoUtc())));
                } else {
                    vistaCruce.setDescripcionValModoUtc("Sin info");
                }
                if (vistaCruce.getValModoLam() != null) {
                    vistaCruce.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruce.getValModoLam().toString()));
                } else {
                    vistaCruce.setDescripcionValModoLam("Sin info");
                }

            }
            vistaCruce.setImagenEstadoOperacional(mapaImagenEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.isIndLamFault() && vistaCruce.getValModoUtc4().equals(new BigDecimal("1"))) {
                vistaCruce.setImagenEstadoOperacional(vistaCruce.getImagenEstadoOperacional().substring(0, vistaCruce.getImagenEstadoOperacional().length() - 4) + "LF.png");
            }
            vistaCruce.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruce.getFechaAct().toString()));
            vistaCruce.setColorEstadoOperativo(mapaEstadoOperacionalColores.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setUbicacion(vistaCruce.getUbicacion() + " - " + vistaCruce.getIdCruce());
            if (vistaCruce.getControlador() == null) {
                vistaCruce.setControlador("SIN INFORMACION");
            }
            if (vistaCruce.getActivo() == null) {
                vistaCruce.setActivo("SIN INFORMACION");
            }
            if (vistaCruce.getValModo220() == null) {
                vistaCruce.setValModo220(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoUps() == null) {
                vistaCruce.setValModoUps(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoLam() == null) {
                vistaCruce.setValModoLam(BigDecimal.ZERO);
            }

        }
        return listaVistaCruces;
    }

    public List<VistaCruceTO> buscarPorEstado(String estado) {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        List<VistaCruceTO> listaVistaCruces = vistaCruceDAO.buscarPorEstado(estado);
        for (VistaCruceTO vistaCruce : listaVistaCruces) {
            vistaCruce.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.getEstadoOperativo().equals("4")) //si es invalido, cambiar los valores por sin info
            {
                vistaCruce.setDescripcionValModo220("Sin info");
                vistaCruce.setDescripcionValModoUps("Sin info");
                vistaCruce.setDescripcionValModoUtc("Sin info");
                vistaCruce.setDescripcionValModoLam("Sin info");
            } else {
                if (vistaCruce.getValModo220() != null) {
                    vistaCruce.setDescripcionValModo220(mapaValorModo220.get(vistaCruce.getValModo220().toString()));
                } else {
                    vistaCruce.setDescripcionValModo220("Sin info");
                }
                if (vistaCruce.getValModoUps() != null) {
                    vistaCruce.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruce.getValModoUps().toString()));
                } else {
                    vistaCruce.setDescripcionValModoUps("Sin info");
                }
                if (vistaCruce.getValModoUtc() != null) {
                    vistaCruce.setDescripcionValModoUtc(mapaValorModoUtc.get(String.valueOf(vistaCruce.getValModoUtc())));
                } else {
                    vistaCruce.setDescripcionValModoUtc("Sin info");
                }
                if (vistaCruce.getValModoLam() != null) {
                    vistaCruce.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruce.getValModoLam().toString()));
                } else {
                    vistaCruce.setDescripcionValModoLam("Sin info");
                }

            }
            vistaCruce.setImagenEstadoOperacional(mapaImagenEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruce.getFechaAct().toString()));
            vistaCruce.setColorEstadoOperativo(mapaEstadoOperacionalColores.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setUbicacion(vistaCruce.getUbicacion() + " - " + vistaCruce.getIdCruce());
            if (vistaCruce.getControlador() == null) {
                vistaCruce.setControlador("SIN INFORMACION");
            }
            if (vistaCruce.getActivo() == null) {
                vistaCruce.setActivo("SIN INFORMACION");
            }
            if (vistaCruce.getValModo220() == null) {
                vistaCruce.setValModo220(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoUps() == null) {
                vistaCruce.setValModoUps(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoLam() == null) {
                vistaCruce.setValModoLam(BigDecimal.ZERO);
            }
        }
        return listaVistaCruces;
    }

    public List<VistaCruceTO> listaVistaNoEncendidos(BigDecimal idComuna) {
        VistaCruceDAO vistaCruceDAO = new VistaCruceDAO(VistaCruceEntity.class, entityManager);
        List<VistaCruceTO> listaVistaCruces = new ArrayList();
        if (idComuna.equals(new BigDecimal("0"))) {
            listaVistaCruces = vistaCruceDAO.buscarNoEncendidos();
        } else {
            listaVistaCruces = vistaCruceDAO.buscarNoEncendidosPorComuna(idComuna);
        }

        for (VistaCruceTO vistaCruce : listaVistaCruces) {
            vistaCruce.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            if (vistaCruce.getEstadoOperativo().equals("4")) //si es invalido, cambiar los valores por sin info
            {
                vistaCruce.setDescripcionValModo220("Sin info");
                vistaCruce.setDescripcionValModoUps("Sin info");
                vistaCruce.setDescripcionValModoUtc("Sin info");
                vistaCruce.setDescripcionValModoLam("Sin info");
            } else {
                if (vistaCruce.getValModo220() != null) {
                    vistaCruce.setDescripcionValModo220(mapaValorModo220.get(vistaCruce.getValModo220().toString()));
                } else {
                    vistaCruce.setDescripcionValModo220("Sin info");
                }
                if (vistaCruce.getValModoUps() != null) {
                    vistaCruce.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruce.getValModoUps().toString()));
                } else {
                    vistaCruce.setDescripcionValModoUps("Sin info");
                }
                if (vistaCruce.getValModoUtc() != null) {
                    vistaCruce.setDescripcionValModoUtc(mapaValorModoUtc.get(String.valueOf(vistaCruce.getValModoUtc())));
                } else {
                    vistaCruce.setDescripcionValModoUtc("Sin info");
                }
                if (vistaCruce.getValModoLam() != null) {
                    vistaCruce.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruce.getValModoLam().toString()));
                } else {
                    vistaCruce.setDescripcionValModoLam("Sin info");
                }

            }
            vistaCruce.setImagenEstadoOperacional(mapaImagenEstadoOperacional.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruce.getFechaAct().toString()));
            vistaCruce.setColorEstadoOperativo(mapaEstadoOperacionalColores.get(vistaCruce.getEstadoOperativo()));
            vistaCruce.setUbicacion(vistaCruce.getUbicacion() + " - " + vistaCruce.getIdCruce());
            if (vistaCruce.getControlador() == null) {
                vistaCruce.setControlador("SIN INFORMACION");
            }
            if (vistaCruce.getActivo() == null) {
                vistaCruce.setActivo("SIN INFORMACION");
            }
            if (vistaCruce.getValModo220() == null) {
                vistaCruce.setValModo220(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoUps() == null) {
                vistaCruce.setValModoUps(BigDecimal.ZERO);
            }
            if (vistaCruce.getValModoLam() == null) {
                vistaCruce.setValModoLam(BigDecimal.ZERO);
            }
        }
        return listaVistaCruces;
    }

    public List<VistaCruceHistoricoTO> listaVistaCrucesHisotricoMonitoreo(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) throws Exception {
        VistaCruceHistoricoDAO vistaCruceHistDAO = new VistaCruceHistoricoDAO(VistaCruceHistoricoEntity.class, entityManager);
        List<VistaCruceHistoricoTO> listaVistaCruceHistorico = vistaCruceHistDAO.buscarComportamiento(idComuna, idCruce, fechaDesde, fechaHasta);
        for (VistaCruceHistoricoTO vistaCruceHistoricoTO : listaVistaCruceHistorico) {
            vistaCruceHistoricoTO.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruceHistoricoTO.getEstadoOperativo()));
            if (vistaCruceHistoricoTO.getEstadoOperativo().equals("4")) {
                vistaCruceHistoricoTO.setDescripcionValModo220("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoUps("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoUtc("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoLam("Sin Info");
            } else {
                vistaCruceHistoricoTO.setDescripcionValModo220(mapaValorModo220.get(vistaCruceHistoricoTO.getValModo220().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruceHistoricoTO.getValModoUps().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoUtc(mapaValorModoUtc.get(vistaCruceHistoricoTO.getValModoUtc().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruceHistoricoTO.getValModoLam().toString()));
            }
            vistaCruceHistoricoTO.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruceHistoricoTO.getFechaAct().toString()));
        }
        return listaVistaCruceHistorico;
    }

    public List<VistaCruceHistoricoTO> listaVistaCrucesHistoricoPorComuna(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) {
        VistaCruceHistoricoDAO vistaCruceHistDAO = new VistaCruceHistoricoDAO(VistaCruceHistoricoEntity.class, entityManager);
        List<VistaCruceHistoricoTO> listaVistaCruceHistorico = vistaCruceHistDAO.buscarComportamientoPorComuna(idComuna, fechaDesde, fechaHasta);
        for (VistaCruceHistoricoTO vistaCruceHistoricoTO : listaVistaCruceHistorico) {
            vistaCruceHistoricoTO.setDescripcionEstadoOperativo(mapaEstadoOperacional.get(vistaCruceHistoricoTO.getEstadoOperativo()));
            if (vistaCruceHistoricoTO.getEstadoOperativo().equals("4")) {
                vistaCruceHistoricoTO.setDescripcionValModo220("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoUps("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoUtc("Sin Info");
                vistaCruceHistoricoTO.setDescripcionValModoLam("Sin Info");
            } else {

                vistaCruceHistoricoTO.setDescripcionValModo220(mapaValorModo220.get(vistaCruceHistoricoTO.getValModo220().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoUps(mapaValorModoUps.get(vistaCruceHistoricoTO.getValModoUps().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoUtc(mapaValorModoUtc.get(vistaCruceHistoricoTO.getValModoUtc().toString()));
                vistaCruceHistoricoTO.setDescripcionValModoLam(mapaValorModoLam.get(vistaCruceHistoricoTO.getValModoLam().toString()));
            }
            vistaCruceHistoricoTO.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaCruceHistoricoTO.getFechaAct().toString()));
        }
        return listaVistaCruceHistorico;
    }

    public List<CodigoTO> obtenerEstadoOperativo() {
        List<CodigoTO> listaEstadoOperativo = new ArrayList();
        CodigoTO codigoTO1 = new CodigoTO();
        codigoTO1.setIdCodigo(new BigDecimal("140"));
        codigoTO1.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO1.setCodigo1("1");
        codigoTO1.setCodigo2("../../imagenes/mapa/operativo/circuloVerde.png");
        codigoTO1.setDescripcion("Encendido");
        listaEstadoOperativo.add(codigoTO1);

        CodigoTO codigoTO2 = new CodigoTO();
        codigoTO2.setIdCodigo(new BigDecimal("141"));
        codigoTO2.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO2.setCodigo1("2");
        codigoTO2.setCodigo2("../../imagenes/mapa/operativo/circuloAzul.png");
        codigoTO2.setDescripcion("Encendido con Obs.");
        listaEstadoOperativo.add(codigoTO2);

        CodigoTO codigoTO3 = new CodigoTO();
        codigoTO3.setIdCodigo(new BigDecimal("142"));
        codigoTO3.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO3.setCodigo1("3");
        codigoTO3.setCodigo2("../../imagenes/mapa/operativo/circuloRojo.png");
        codigoTO3.setDescripcion("Apagado");
        listaEstadoOperativo.add(codigoTO3);

        CodigoTO codigoTO4 = new CodigoTO();
        codigoTO4.setIdCodigo(new BigDecimal("223"));
        codigoTO4.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO4.setCodigo1("4");
        codigoTO4.setCodigo2("../../imagenes/mapa/operativo/circuloGris.png");
        codigoTO4.setDescripcion("Inválido");
        listaEstadoOperativo.add(codigoTO4);

        CodigoTO codigoTO5 = new CodigoTO();
        codigoTO5.setIdCodigo(new BigDecimal("260"));
        codigoTO5.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO5.setCodigo1("5");
        codigoTO5.setCodigo2("../../imagenes/mapa/operativo/tn_marker.png");
        codigoTO5.setDescripcion("TDV");
        listaEstadoOperativo.add(codigoTO5);

        CodigoTO codigoTO6 = new CodigoTO();
        codigoTO6.setIdCodigo(new BigDecimal("265"));
        codigoTO6.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO6.setCodigo1("6");
        codigoTO6.setCodigo2("../../imagenes/mapa/operativo/CamionetaTransparente.png");
        codigoTO6.setDescripcion("Movil");
        listaEstadoOperativo.add(codigoTO6);

        return listaEstadoOperativo;
    }

    /**
     * Realiza la carga de Moviles dependiendo de la comuna, para tal efecto se
     * buscar los Poligonos de la Comunas y se procede a recorrer los Moviles
     * aplicando la logica para ver si estan contenidos.
     *
     * @param idComuna
     * @return
     */
    public List<MovilTO> listaMovil(BigDecimal idComuna) throws Exception {
        List<MovilTO> listaMovilComuna = new ArrayList<MovilTO>();
        MovilDAO movilDAO = new MovilDAO(MovilEntity.class, entityManager);
        // VistaMovilDAO movilDAO = new VistaMovilDAO(VistaMovilEntity.class, entityManager);
        PoligonoDAO poligonoDAO = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        List<MovilTO> listaMovil = movilDAO.buscarTodos();
        List<PoligonoTO> listaPoligono = poligonoDAO.buscarPoligonoComuna(idComuna.intValue());
        Polygon poligono = new Polygon();
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        String fechaActual = codigoDAO.traerFechaHoraActual();
        double tiempoIntervalo = Double.parseDouble(codigoDAO.buscarPorDominioCodigo("INTERVALO_MOVIL", "1").getCodigo2());

        for (PoligonoTO poligonoTO : listaPoligono) {
            int x = poligonoTO.getLongitud().multiply(new BigDecimal(1000000)).intValue();
            int y = poligonoTO.getLatitud().multiply(new BigDecimal(1000000)).intValue();
            poligono.addPoint(x, y);
        }
        for (MovilTO movilTO : listaMovil) {
            int latitud = (new BigDecimal(movilTO.getLatitud()).multiply(new BigDecimal(1000000))).intValue();
            int longitud = (new BigDecimal(movilTO.getLongitud()).multiply(new BigDecimal(1000000))).intValue();
            boolean contenido = poligono.contains(longitud, latitud);
            if (contenido) {
                movilTO.setDescripcionFecha(Util.retornaFechaFormateadaString(movilTO.getFecultLec().toString()));
                if (tiempoIntervalo >= diferenciaEntreFechas(fechaActual, movilTO.getDescripcionFecha())) {
                    movilTO.setIcono("../../imagenes/mapa/operativo/CamionetaTransparente.png");
                } else {
                    movilTO.setIcono("../../imagenes/mapa/operativo/vacio.png");
                }
                if (!movilTO.getCodigoMovil().equals("DVTS-99") && !movilTO.getCodigoMovil().equals("BPKD-74")) {
                    listaMovilComuna.add(movilTO);
                }
            }
        }
        return listaMovilComuna;
    }

    public List<MovilTO> listaMovilTodos(BigDecimal idComuna) throws Exception {
        List<MovilTO> listaMovilComuna = new ArrayList<MovilTO>();
        MovilDAO movilDAO = new MovilDAO(MovilEntity.class, entityManager);
        //VistaMovilDAO movilDAO = new VistaMovilDAO(VistaMovilEntity.class, entityManager);
        PoligonoDAO poligonoDAO = new PoligonoDAO(PoligonoComunalEntity.class, entityManager);
        List<MovilTO> listaMovil = movilDAO.buscarTodos();
        List<PoligonoTO> listaPoligono = poligonoDAO.buscarPoligonoComuna(idComuna.intValue());
        Polygon poligono = new Polygon();
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        String fechaActual = codigoDAO.traerFechaHoraActual();
        double tiempoIntervalo = Double.parseDouble(codigoDAO.buscarPorDominioCodigo("INTERVALO_MOVIL", "1").getCodigo2());
        for (PoligonoTO poligonoTO : listaPoligono) {
            int x = poligonoTO.getLongitud().multiply(new BigDecimal(1000000)).intValue();
            int y = poligonoTO.getLatitud().multiply(new BigDecimal(1000000)).intValue();
            poligono.addPoint(x, y);
        }
        for (MovilTO movilTO : listaMovil) {
            movilTO.setIcono("../../imagenes/mapa/operativo/vacio.png");
            int latitud = (new BigDecimal(movilTO.getLatitud()).multiply(new BigDecimal(1000000))).intValue();
            int longitud = (new BigDecimal(movilTO.getLongitud()).multiply(new BigDecimal(1000000))).intValue();
            boolean contenido = poligono.contains(longitud, latitud);
            if (contenido) {
                movilTO.setDescripcionFecha(Util.retornaFechaFormateadaString(movilTO.getFecultLec().toString()));
                //movilTO.setIcono(codigoDAO.buscarPorDominioCodigo("SISTEMA_GEOREFERENCIA", "01").getCodigo2());
                if (tiempoIntervalo >= diferenciaEntreFechas(fechaActual, movilTO.getDescripcionFecha())) {
                    movilTO.setIcono("../../imagenes/mapa/operativo/CamionetaTransparente.png");
                } else {
                    movilTO.setIcono("../../imagenes/mapa/operativo/vacio.png");
                }
            }
            if (!movilTO.getCodigoMovil().equals("DVTS-99") && !movilTO.getCodigoMovil().equals("BPKD-74")) {
                listaMovilComuna.add(movilTO);
            }

        }
        return listaMovilComuna;
    }

    public List<MovilTO> listaMovilTodos() throws Exception {
        List<MovilTO> listaMovilComuna = new ArrayList<MovilTO>();
        MovilDAO movilDAO = new MovilDAO(MovilEntity.class, entityManager);
        //VistaMovilDAO movilDAO = new VistaMovilDAO(VistaMovilEntity.class, entityManager);
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        String fechaActual = codigoDAO.traerFechaHoraActual();
        double tiempoIntervalo = Double.parseDouble(codigoDAO.buscarPorDominioCodigo("INTERVALO_MOVIL", "1").getCodigo2());
        List<MovilTO> listaMovil = movilDAO.buscarTodos();
        for (MovilTO movilTO : listaMovil) {
            movilTO.setDescripcionFecha(Util.retornaFechaFormateadaString(movilTO.getFecultLec().toString()));
            if (tiempoIntervalo >= diferenciaEntreFechas(fechaActual, movilTO.getDescripcionFecha())) {
                movilTO.setIcono("../../imagenes/mapa/operativo/CamionetaTransparente.png");
            } else {
                movilTO.setIcono("../../imagenes/mapa/operativo/vacio.png");
            }
            if (!movilTO.getCodigoMovil().equals("DVTS-99") && !movilTO.getCodigoMovil().equals("BPKD-74")) {
                listaMovilComuna.add(movilTO);
            }
        }
        return listaMovilComuna;
    }

    public static double diferenciaEntreFechas(String fechaActual, String fechaActualizacion) {
        GregorianCalendar gregorianCalendarInicial = new GregorianCalendar();
        GregorianCalendar gregorianCalendarFinal = new java.util.GregorianCalendar();
        gregorianCalendarFinal.set(Integer.parseInt(fechaActual.substring(6, 10)), Integer.parseInt(fechaActual.substring(3, 5)) - 1, Integer.parseInt(fechaActual.substring(0, 2)), Integer.parseInt(fechaActual.substring(11, 13)), Integer.parseInt(fechaActual.substring(14, 16)), Integer.parseInt(fechaActual.substring(17, 19)));
        gregorianCalendarInicial.set(Integer.parseInt(fechaActualizacion.substring(6, 10)), Integer.parseInt(fechaActualizacion.substring(3, 5)) - 1, Integer.parseInt(fechaActualizacion.substring(0, 2)), Integer.parseInt(fechaActualizacion.substring(11, 13)), Integer.parseInt(fechaActualizacion.substring(14, 16)), Integer.parseInt(fechaActualizacion.substring(17, 19)));
        long diferencia = gregorianCalendarFinal.getTime().getTime() - gregorianCalendarInicial.getTime().getTime();
        double minutos = diferencia / (1000 * 60);
        return minutos;
    }

}
