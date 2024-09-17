package cl.auter.ejb.patron.bo;

import cl.auter.ejb.dao.CodigoDAO;
import cl.auter.ejb.dao.InformeDiarioDAO;
import cl.auter.ejb.dao.VistaDocumentoDAO;
import cl.auter.ejb.dao.VistaInstalacionDAO;
import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.ejb.entity.bmp.VistaCruceDocumentoEntity;
import cl.auter.ejb.entity.bmp.VistaInformeDiarioEntity;
import cl.auter.ejb.entity.bmp.VistaInstalacionEntity;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.InformeDiarioTO;
import cl.auter.patron.to.VistaDocumentoTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.util.Util;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class MantencionBO {
    private final EntityManager entityManager;
    public MantencionBO (EntityManager entityManager){
        this.entityManager=entityManager;
    }
    public List<VistaInstalacionTO> listaVistaInstalaciones(BigDecimal idComuna, CodigoTO[] tipoInstalacion ) {
        VistaInstalacionDAO vistaInstalacionDAO = new VistaInstalacionDAO(VistaInstalacionEntity.class, entityManager);
        CodigoDAO  codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        List<VistaInstalacionTO> listaVistaInstalaciones=vistaInstalacionDAO.buscarInstalacionPor(idComuna);
        for (VistaInstalacionTO vistaInstalacionTO : listaVistaInstalaciones){
            String tipo = vistaInstalacionTO.getTipo();
            if(tipo == null || tipo.equals("")){
                tipo = "1";
            }
            vistaInstalacionTO.setDescripcionTipo(tipoInstalacion[Integer.parseInt(tipo)-1].getDescripcion());
            vistaInstalacionTO.setImagen(tipoInstalacion[Integer.parseInt(tipo)-1].getCodigo2());
            vistaInstalacionTO.setUbicacion(vistaInstalacionTO.getIdCruce()+" - "+ vistaInstalacionTO.getIdInstalacion() +" - "+ vistaInstalacionTO.getUbicacion());

        }
        return listaVistaInstalaciones;
    }
    
    public List<VistaInstalacionTO> listaVistaInstalacionMonitoreo(CodigoTO[] tipoInstalacion){
        VistaInstalacionDAO vistaInstalacionDAO = new VistaInstalacionDAO(VistaInstalacionEntity.class, entityManager);
        CodigoDAO  codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        List<VistaInstalacionTO> listaVistaInstalaciones=vistaInstalacionDAO.buscarInstalacionMonitoreo();
        for (VistaInstalacionTO vistaInstalacionTO : listaVistaInstalaciones){
            String tipo = vistaInstalacionTO.getTipo();
            if(tipo == null || tipo.equals("")){
                tipo = "1";
            }
            vistaInstalacionTO.setDescripcionTipo(tipoInstalacion[Integer.parseInt(tipo)-1].getDescripcion());
            vistaInstalacionTO.setImagen(tipoInstalacion[Integer.parseInt(tipo)-1].getCodigo2());
            vistaInstalacionTO.setUbicacion(vistaInstalacionTO.getIdCruce()+" - "+ vistaInstalacionTO.getIdInstalacion() +" - "+ vistaInstalacionTO.getUbicacion());
        }
        return listaVistaInstalaciones;
    }
    
    public List<VistaDocumentoTO> listaInstalacionesDocumentos(BigDecimal idCruce)throws Exception {
        VistaDocumentoDAO vistaDocumentoDAO = new VistaDocumentoDAO(VistaCruceDocumentoEntity.class, entityManager);
        CodigoDAO  codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        
        List<VistaDocumentoTO> listaVistaDocumento=vistaDocumentoDAO.buscarDocumentosPorInstalacion(idCruce);
        for (VistaDocumentoTO vistaDocumentoTO :listaVistaDocumento ){
            CodigoTO codigoTO=codigoDAO.buscarPorDominioCodigo("TIPO_DOCUMENTO", vistaDocumentoTO.getTipoDocumento());
            vistaDocumentoTO.setDescripcionTipo(codigoTO.getDescripcion());
            vistaDocumentoTO.setDescripcionFecha(Util.retornaFechaFormateadaString(vistaDocumentoTO.getFechaDocumento().toString()));
        }
        return listaVistaDocumento;
    }
    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna,BigDecimal idCruce, Date fechaDesde, Date fechaHasta)throws Exception {
        InformeDiarioDAO informeDiarioDAO = new InformeDiarioDAO(VistaInformeDiarioEntity.class, entityManager);
        List<InformeDiarioTO> listaInformeDiarioTO=informeDiarioDAO.buscarInformeDiarioCruceQuery(idComuna, idCruce,fechaDesde, fechaHasta);        
	return listaInformeDiarioTO;
     }
    
    public List<InformeDiarioTO> buscarInformeDiario(BigDecimal idComuna, Date fechaDesde, Date fechaHasta)throws Exception {
        InformeDiarioDAO informeDiarioDAO = new InformeDiarioDAO(VistaInformeDiarioEntity.class, entityManager);
        List<InformeDiarioTO> listaInformeDiarioTO=informeDiarioDAO.buscarInformeDiarioQuery(idComuna, fechaDesde, fechaHasta);        
	return listaInformeDiarioTO;
     }


}
