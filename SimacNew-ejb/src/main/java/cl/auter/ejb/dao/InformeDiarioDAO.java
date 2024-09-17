/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaInformeDiarioEntity;
import cl.auter.patron.to.InformeDiarioTO;
import cl.auter.util.Util;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author rbaeza
 */
public class InformeDiarioDAO extends BaseDAO<VistaInformeDiarioEntity> {

    public InformeDiarioDAO(Class<VistaInformeDiarioEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<InformeDiarioTO> buscarInformeDiarioQuery(BigDecimal idComuna, Date desde, Date hasta) {
        List<InformeDiarioTO> listaVistaInformeDiario = new ArrayList<InformeDiarioTO>();
        String fechaDesde = new SimpleDateFormat("dd-MM-yyyy").format(desde);
        String fechaHasta = new SimpleDateFormat("dd-MM-yyyy").format(hasta);

        StringBuilder consulta = new StringBuilder();
        consulta.append("select * from VISTA_INFORME_DIARIO");
        consulta.append(" where fechallegada between to_date('");
        consulta.append(fechaDesde);
        consulta.append(" 00:00:00','dd-mm-yyyy hh24:mi:ss') and to_date('");
        consulta.append(fechaHasta);
        consulta.append(" 23:59:59','dd-mm-yyyy hh24:mi:ss')");
        consulta.append(" and id_comuna =");
        consulta.append(idComuna);
        consulta.append(" order by fechallegada asc");
        Query query = getEm().createNativeQuery(consulta.toString());
        List<Object[]> otraLista = query.getResultList();
        for (Object[] fila : otraLista) {
            InformeDiarioTO vistaTO = new InformeDiarioTO();
            vistaTO.setIdAtencion((BigDecimal) fila[0]);
            vistaTO.setIdCruce((BigDecimal) fila[1]);
            vistaTO.setIdComuna((BigDecimal) fila[2]);
            vistaTO.setFechaLlegada((Date) fila[3]);
            vistaTO.setEstadoInicial((String) fila[4]);
            vistaTO.setEstadoFinal((String) fila[5]);
            vistaTO.setEstadoTarea((String) fila[6]);
            vistaTO.setObservacion((String) fila[7]);
            vistaTO.setFechaSalida((Date) fila[8]);
            vistaTO.setNombreTipoOrigen((String) fila[9]);
            vistaTO.setUbicacion((String) fila[10]);
            vistaTO.setLlamada(String.valueOf(fila[11]));
            vistaTO.setTipo(String.valueOf(fila[12]));
            String fechaLlegadaFormateada = "";
            String fechaSalidaFormateada = "";
            if (vistaTO.getFechaLlegada() != null) {
                fechaLlegadaFormateada = Util.retornaFechaFormateadaString(vistaTO.getFechaLlegada().toString());
            }
            if (vistaTO.getFechaSalida() != null) {
                fechaSalidaFormateada = Util.retornaFechaFormateadaString(vistaTO.getFechaSalida().toString());
            }
            vistaTO.setFechaLlegadaFormateada(fechaLlegadaFormateada);
            vistaTO.setFechaSalidaFormateada(fechaSalidaFormateada);
            listaVistaInformeDiario.add(vistaTO);
        }
        return listaVistaInformeDiario;
    }
    
    public List<InformeDiarioTO> buscarInformeDiarioCruceQuery(BigDecimal idComuna,BigDecimal idCruce, Date desde, Date hasta) {
        List<InformeDiarioTO> listaVistaInformeDiario = new ArrayList<InformeDiarioTO>();
        String fechaDesde = new SimpleDateFormat("dd-MM-yyyy").format(desde);
        String fechaHasta = new SimpleDateFormat("dd-MM-yyyy").format(hasta);

        StringBuilder consulta = new StringBuilder();
        consulta.append("select * from VISTA_INFORME_DIARIO");
        consulta.append(" where fechallegada between to_date('");
        consulta.append(fechaDesde);
        consulta.append(" 00:00:00','dd-mm-yyyy hh24:mi:ss') and to_date('");
        consulta.append(fechaHasta);
        consulta.append(" 23:59:59','dd-mm-yyyy hh24:mi:ss')");
        consulta.append(" and idCruce =");
        consulta.append(idCruce);
        consulta.append(" and id_comuna =");
        consulta.append(idComuna);
        consulta.append(" order by fechallegada asc");
        Query query = getEm().createNativeQuery(consulta.toString());
        List<Object[]> otraLista = query.getResultList();
        for (Object[] fila : otraLista) {
            InformeDiarioTO vistaTO = new InformeDiarioTO();
            vistaTO.setIdAtencion((BigDecimal) fila[0]);
            vistaTO.setIdCruce((BigDecimal) fila[1]);
            vistaTO.setIdComuna((BigDecimal) fila[2]);
            vistaTO.setFechaLlegada((Date) fila[3]);
            vistaTO.setEstadoInicial((String) fila[4]);
            vistaTO.setEstadoFinal((String) fila[5]);
            vistaTO.setEstadoTarea((String) fila[6]);
            vistaTO.setObservacion((String) fila[7]);
            vistaTO.setFechaSalida((Date) fila[8]);
            vistaTO.setNombreTipoOrigen((String) fila[9]);
            vistaTO.setUbicacion((String) fila[10]);
            vistaTO.setLlamada(String.valueOf(fila[11]));
            vistaTO.setTipo(String.valueOf(fila[12]));
            String fechaLlegadaFormateada = "";
            String fechaSalidaFormateada = "";
            if (vistaTO.getFechaLlegada() != null) {
                fechaLlegadaFormateada = Util.retornaFechaFormateadaString(vistaTO.getFechaLlegada().toString());
            }
            if (vistaTO.getFechaSalida() != null) {
                fechaSalidaFormateada = Util.retornaFechaFormateadaString(vistaTO.getFechaSalida().toString());
            }
            vistaTO.setFechaLlegadaFormateada(fechaLlegadaFormateada);
            vistaTO.setFechaSalidaFormateada(fechaSalidaFormateada);
            listaVistaInformeDiario.add(vistaTO);
        }
        return listaVistaInformeDiario;
    }
    
    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, BigDecimal idCruce, Date desde, Date hasta) {
        List<InformeDiarioTO> listaInformeDiarioTO = new ArrayList();
        Query query = getEm().createNamedQuery("VistaInformeDiarioEntity.informeDiario");
        String fechaDesde = new SimpleDateFormat("dd-MM-yyyy").format(desde);
        String fechaHasta = new SimpleDateFormat("dd-MM-yyyy").format(hasta);
        query.setParameter("idComuna", idComuna);
        query.setParameter("idCruce", idCruce);
        query.setParameter("fechaDesde", fechaDesde + " 00:00:00");
        query.setParameter("fechaHasta", fechaHasta + " 23:59:59");
        List<VistaInformeDiarioEntity> listaInformeDiarioEntity = query.getResultList();
        for (VistaInformeDiarioEntity informeDiarioEntity : listaInformeDiarioEntity) {
            listaInformeDiarioTO.add(traspasar(informeDiarioEntity));
        }
        return listaInformeDiarioTO;
    }

    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, Date desde, Date hasta) {
        List<InformeDiarioTO> listaInformeDiarioTO = new ArrayList();
        Query query = getEm().createNamedQuery("VistaInformeDiarioEntity.informeDiarioSinCruce");
        String fechaDesde = new SimpleDateFormat("dd-MM-yyyy").format(desde);
        String fechaHasta = new SimpleDateFormat("dd-MM-yyyy").format(hasta);
        query.setParameter("idComuna", idComuna);
        query.setParameter("fechaDesde", fechaDesde + " 00:00:00");
        query.setParameter("fechaHasta", fechaHasta + " 23:59:59");
        List<VistaInformeDiarioEntity> listaInformeDiarioEntity = query.getResultList();
        for (VistaInformeDiarioEntity informeDiarioEntity : listaInformeDiarioEntity) {
            InformeDiarioTO inf = traspasar(informeDiarioEntity);
            listaInformeDiarioTO.add(inf);
        }

        return listaInformeDiarioTO;
    }

    public InformeDiarioTO traspasar(VistaInformeDiarioEntity informeDiarioEntity) {
        String fechaLlegadaFormateada = "";
        String fechaSalidaFormateada = "";
        InformeDiarioTO informeDiarioTO = new InformeDiarioTO();
        informeDiarioTO.setIdAtencion(informeDiarioEntity.getIdatencion());
        informeDiarioTO.setIdComuna(informeDiarioEntity.getIdComuna());
        informeDiarioTO.setIdCruce(informeDiarioEntity.getIdcruce());
        if (informeDiarioEntity.getFechallegada() != null) {
            fechaLlegadaFormateada = Util.retornaFechaFormateadaString(informeDiarioEntity.getFechallegada().toString());
        }
        if (informeDiarioEntity.getFechasalida() != null) {
            fechaSalidaFormateada = Util.retornaFechaFormateadaString(informeDiarioEntity.getFechasalida().toString());
        }
        informeDiarioTO.setFechaLlegadaFormateada(fechaLlegadaFormateada);
        informeDiarioTO.setFechaSalidaFormateada(fechaSalidaFormateada);
        informeDiarioTO.setEstadoInicial(informeDiarioEntity.getEstadoinicial());
        informeDiarioTO.setEstadoFinal(informeDiarioEntity.getEstadofinal());
        informeDiarioTO.setEstadoTarea(informeDiarioEntity.getEstadotarea());
        informeDiarioTO.setObservacion(informeDiarioEntity.getObservacion());
        informeDiarioTO.setUbicacion(informeDiarioEntity.getUbicacion());
        informeDiarioTO.setNombreTipoOrigen(informeDiarioEntity.getNombretipoorigen());
        informeDiarioTO.setLlamada(informeDiarioEntity.getLlamada());
        informeDiarioTO.setTipo(informeDiarioEntity.getTipo());
        return informeDiarioTO;
    }
}
