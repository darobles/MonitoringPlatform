package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaCruceHistoricoEntity;
import cl.auter.patron.to.VistaCruceHistoricoTO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class VistaCruceHistoricoDAO extends BaseDAO<VistaCruceHistoricoEntity> {

    public VistaCruceHistoricoDAO(Class<VistaCruceHistoricoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<VistaCruceHistoricoTO> buscarTodos() {
        List<VistaCruceHistoricoTO> listaCodigo = new ArrayList();
        for (VistaCruceHistoricoEntity codigoEntity : findAll()) {
            listaCodigo.add(traspasar(codigoEntity));
        }
        return listaCodigo;
    }

    public List<VistaCruceHistoricoTO> buscarComportamiento(BigDecimal idComuna, BigDecimal idCruce, Date fechaDesde, Date fechaHasta) {
        List<VistaCruceHistoricoTO> listaVistaCruceHistorico = new ArrayList();
        String desde = new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde);
        String hasta = new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta);
        Calendar calendar = new GregorianCalendar(2019,0,14,14,00,00);
        StringBuilder consulta = new StringBuilder();
        int i = 0;
        if(fechaDesde.before(calendar.getTime()) )
        {
            consulta.append("select iddispositivo,\n" +
                            "idcruce,\n" +
                            "ubicacion,\n" +
                            "controlador,\n" +
                            "activo,\n" +
                            "fechaact,\n" +
                            "estadooperativo,\n" +
                            "valmodo220,\n" +
                            "valmodoups,\n" +
                            "valmodoutc,\n" +
                            "valmodolam,\n" +
                            "red,\n" +
                            "longitud,\n" +
                            "latitud,\n" +
                            "junction,\n" +
                            "idcomuna,\n" +
                            "nombre_comuna from VISTA_CRUCE_DISPOSITIVO_HIS");
        }
        else{
            i = 1;
            consulta.append("select * from VISTA_CRUCE_DISPOSITIVO_HIS2");
        }
        consulta.append(" where fechaact between to_date('");
        consulta.append(desde);
        consulta.append(" 00:00:00','dd-mm-yyyy hh24:mi:ss') and to_date('");
        consulta.append(hasta);
        consulta.append(" 23:59:59','dd-mm-yyyy hh24:mi:ss')");
        consulta.append(" and idcruce =");
        consulta.append(idCruce);
        consulta.append(" and idcomuna =");
        consulta.append(idComuna);
        consulta.append(" order by idcruce,fechaact,ubicacion desc");
        Query query = getEm().createNativeQuery(consulta.toString());
        List<Object[]> otraLista = query.getResultList();
        for (Object[] fila : otraLista) {
            VistaCruceHistoricoTO vistaTO = new VistaCruceHistoricoTO();
            vistaTO.setIdDispositivo((BigDecimal) fila[0+i]);
            vistaTO.setIdCruce((BigDecimal) fila[1+i]);
            vistaTO.setUbicacion((String) fila[2+i]);
            vistaTO.setControlador((String) fila[3+i]);
            vistaTO.setActivo((String) fila[4+i]);
            vistaTO.setFechaAct((Date) fila[5+i]);
            vistaTO.setEstadoOperativo((String) fila[6+i]);
            vistaTO.setValModo220((BigDecimal) fila[7+i]);
            vistaTO.setValModoUps((BigDecimal) fila[8+i]);
            vistaTO.setValModoUtc((BigDecimal) fila[9+i]);
            vistaTO.setValModoLam((BigDecimal) fila[10+i]);
            vistaTO.setRed((String) fila[11+i]);
            vistaTO.setLongitud((String) fila[12+i]);
            vistaTO.setLatitud((String) fila[13+i]);
            vistaTO.setJunction((String) fila[14+i]);
            vistaTO.setIdComuna((BigDecimal) fila[15+i]);
            vistaTO.setNombre_comuna((String) fila[16+i]);
            listaVistaCruceHistorico.add(vistaTO);
        }
        return listaVistaCruceHistorico;
    }

    public List<VistaCruceHistoricoTO> buscarComportamientoPorComuna(BigDecimal idComuna, Date fechaDesde, Date fechaHasta) {
        List<VistaCruceHistoricoTO> listaVistaCruceHistorico = new ArrayList<VistaCruceHistoricoTO>();
        String desde = new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde);
        String hasta = new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta);

        StringBuilder consulta = new StringBuilder();
        consulta.append("select iddispositivo,\n" +
                            "idcruce,\n" +
                            "ubicacion,\n" +
                            "controlador,\n" +
                            "activo,\n" +
                            "fechaact,\n" +
                            "estadooperativo,\n" +
                            "valmodo220,\n" +
                            "valmodoups,\n" +
                            "valmodoutc,\n" +
                            "valmodolam,\n" +
                            "red,\n" +
                            "longitud,\n" +
                            "latitud,\n" +
                            "junction,\n" +
                            "idcomuna,\n" +
                            "nombre_comuna from VISTA_CRUCE_DISPOSITIVO_HIS");
        consulta.append(" where fechaact between to_date('");
        consulta.append(desde);
        consulta.append(" 00:00:00','dd-mm-yyyy hh24:mi:ss') and to_date('");
        consulta.append(hasta);
        consulta.append(" 23:59:59','dd-mm-yyyy hh24:mi:ss')");
        if (!idComuna.equals(new BigDecimal("999"))) {
            consulta.append(" and idcomuna =");
            consulta.append(idComuna);
        }
        consulta.append(" order by idcruce,fechaact,ubicacion desc");
        Query query = getEm().createNativeQuery(consulta.toString());
        List<Object[]> otraLista = query.getResultList();
        for (Object[] fila : otraLista) {
            VistaCruceHistoricoTO vistaTO = new VistaCruceHistoricoTO();
            vistaTO.setIdDispositivo((BigDecimal) fila[0]);
            vistaTO.setIdCruce((BigDecimal) fila[1]);
            vistaTO.setUbicacion((String) fila[2]);
            vistaTO.setControlador((String) fila[3]);
            vistaTO.setActivo((String) fila[4]);
            vistaTO.setFechaAct((Date) fila[5]);
            vistaTO.setEstadoOperativo((String) fila[6]);
            vistaTO.setValModo220((BigDecimal) fila[7]);
            vistaTO.setValModoUps((BigDecimal) fila[8]);
            vistaTO.setValModoUtc((BigDecimal) fila[9]);
            vistaTO.setValModoLam((BigDecimal) fila[10]);
            vistaTO.setRed((String) fila[11]);
            vistaTO.setLongitud((String) fila[12]);
            vistaTO.setLatitud((String) fila[13]);
            vistaTO.setJunction((String) fila[14]);
            vistaTO.setIdComuna((BigDecimal) fila[15]);
            vistaTO.setNombre_comuna((String) fila[16]);
            listaVistaCruceHistorico.add(vistaTO);
        }
        return listaVistaCruceHistorico;
    }

    public VistaCruceHistoricoTO traspasar(VistaCruceHistoricoEntity vistaEntity) {
        VistaCruceHistoricoTO vistaTO = new VistaCruceHistoricoTO();
        vistaTO.setIdDispositivo(vistaEntity.getIddispositivo());
        vistaTO.setIdCruce(vistaEntity.getIdcruce());
        vistaTO.setUbicacion(vistaEntity.getUbicacion());
        vistaTO.setControlador(vistaEntity.getControlador());
        vistaTO.setActivo(vistaEntity.getActivo());
        vistaTO.setEstadoOperativo(vistaEntity.getEstadoOperativo());
        vistaTO.setFechaAct(vistaEntity.getFechaAct());
        vistaTO.setValModo220(vistaEntity.getValmodo220());
        vistaTO.setValModoLam(vistaEntity.getValmodolam());
        vistaTO.setValModoUps(vistaEntity.getValmodoups());
        vistaTO.setValModoUtc(vistaEntity.getValmodoutc());
        vistaTO.setRed(vistaEntity.getRed());
        vistaTO.setLongitud(vistaEntity.getLongitud());
        vistaTO.setLatitud(vistaEntity.getLatitud());
        vistaTO.setJunction(vistaEntity.getJunction());
        vistaTO.setIdComuna(vistaEntity.getIdcomuna());
        vistaTO.setNombre_comuna(vistaEntity.getNombre_comuna());
        return vistaTO;
    }
}
