
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.SolicitudEntity;
import cl.auter.patron.to.SolicitudTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;



public class SolicitudDAO extends BaseDAO<SolicitudEntity>{
	
	public SolicitudDAO(Class<SolicitudEntity> entityClass, EntityManager em) {
            super(entityClass, em);
	}
	public void guardar(SolicitudTO solicitudTO)throws Exception {
            SolicitudEntity solicitudEntity =traspasar(solicitudTO);
            persist(solicitudEntity);
            solicitudTO.setIdSolicitud(solicitudEntity.getIdSolicitud());
	}
	public void eliminar(SolicitudTO solicitudTO)throws Exception {
            SolicitudEntity estadoEntity =traspasar(solicitudTO);
            remove(estadoEntity);
			
	}
	public void editar(SolicitudTO solicitudTO)throws Exception {
            SolicitudEntity estadoEntity =traspasar(solicitudTO);
            edit(estadoEntity);
			
	}
	
	public List<SolicitudTO> buscarTodos()throws Exception {
            List<SolicitudTO> listaSolicitud = new ArrayList();
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitud");        
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            for (SolicitudEntity solicitudEntity: listaSolicitudEntity){
                    listaSolicitud.add(traspasar(solicitudEntity));
            }
            return listaSolicitud;
	}
        
        public List<SolicitudTO> buscarSolicitudPorComuna(BigDecimal idComuna)throws Exception {
            List<SolicitudTO> listaSolicitud = new ArrayList<SolicitudTO>();
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudPorComuna"); 
            query.setParameter("idComuna", idComuna);
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            for (SolicitudEntity solicitudEntity: listaSolicitudEntity){
                    listaSolicitud.add(traspasar(solicitudEntity));
            }
            return listaSolicitud;
	}
        
        public List<SolicitudTO> buscarSolicitudComFec(BigDecimal idComuna, Date fecIni, Date fecEnd)throws Exception {
            List<SolicitudTO> listaSolicitud = new ArrayList();
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudPorComunaFecha"); 
            query.setParameter("idComuna", idComuna);
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecEnd", fecEnd);
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            for (SolicitudEntity solicitudEntity: listaSolicitudEntity){
                    listaSolicitud.add(traspasar(solicitudEntity));
            }
            return listaSolicitud;
	}
        
        public List<SolicitudTO> buscarSolicitudPorFecha(Date fecIni, Date fecEnd)throws Exception {
            List<SolicitudTO> listaSolicitud = new ArrayList();
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudPorFecha"); 
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecEnd", fecEnd);
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            for (SolicitudEntity solicitudEntity: listaSolicitudEntity){
                    listaSolicitud.add(traspasar(solicitudEntity));
            }
            return listaSolicitud;
	}
        
        public SolicitudTO buscarSolicitudPorId(BigDecimal idSolicitud)throws Exception {
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudPorId"); 
            query.setParameter("idsolicitud", idSolicitud);
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            if(!listaSolicitudEntity.isEmpty())
                return traspasar(listaSolicitudEntity.get(0));
            else 
                return new SolicitudTO();
	}
        
        public List<SolicitudTO> buscarSolicitudPorTipoSolicitante(BigDecimal tipo,Date fecIni, Date fecEnd) {
            List<SolicitudTO> listaSolicitud = new ArrayList();            
            Query query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudPorTipoSolicitante"); 
            if(tipo.equals(new BigDecimal("242")))
            {                
                query = getEm().createNamedQuery("SolicitudEntity.buscarSolicitudUOCT");
                query.setParameter("numero", new BigDecimal("0"));
            }
            query.setParameter("tipoSolicitante", tipo);
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecEnd", fecEnd);
            List<SolicitudEntity> listaSolicitudEntity = query.getResultList();
            for (SolicitudEntity solicitudEntity: listaSolicitudEntity){
                    listaSolicitud.add(traspasar(solicitudEntity));
            }
            return listaSolicitud;
        }
	
	public SolicitudTO traspasar(SolicitudEntity solicitudEntity){
            SolicitudTO solicitudTO = new SolicitudTO();
            solicitudTO.setIdSolicitud(solicitudEntity.getIdSolicitud());
            solicitudTO.setEmailSolicitante(solicitudEntity.getEmailSolicitante());
            solicitudTO.setAsunto(solicitudEntity.getAsunto());
            solicitudTO.setEstadoActual(solicitudEntity.getEstadoActual());
            solicitudTO.setFechaIngreso(solicitudEntity.getFechaIngreso());
            solicitudTO.setFonoSolicitante(solicitudEntity.getFonoSolicitante());
            solicitudTO.setNombreSolicitante(solicitudEntity.getNombreSolicitante());
            solicitudTO.setNumTareaAsma(solicitudEntity.getNumTareaAsma());
            solicitudTO.setObservacion(solicitudEntity.getObservacion());
            solicitudTO.setUrlAdjunto(solicitudEntity.getUrlAdjunto());
            solicitudTO.setUsrIngresado(solicitudEntity.getUsrIngresado());
            solicitudTO.setIdComuna(solicitudEntity.getIdComuna());
            solicitudTO.setMotivoRechazo(solicitudEntity.getMotivoRechazo());
            solicitudTO.setTipoSolicitante(solicitudEntity.getTipoSolicitante());
            solicitudTO.setNumReclamo(solicitudEntity.getNumReclamo());
            return solicitudTO;
	}
	public SolicitudEntity traspasar(SolicitudTO solicitudTO){
            SolicitudEntity solicitudEntity = new SolicitudEntity();
            solicitudEntity.setIdSolicitud(solicitudTO.getIdSolicitud());
            solicitudEntity.setEmailSolicitante(solicitudTO.getEmailSolicitante());
            solicitudEntity.setAsunto(solicitudTO.getAsunto());
            solicitudEntity.setEstadoActual(solicitudTO.getEstadoActual());
            solicitudEntity.setFechaIngreso(solicitudTO.getFechaIngreso());
            solicitudEntity.setFonoSolicitante(solicitudTO.getFonoSolicitante());
            solicitudEntity.setNombreSolicitante(solicitudTO.getNombreSolicitante());
            solicitudEntity.setNumTareaAsma(solicitudTO.getNumTareaAsma());
            solicitudEntity.setObservacion(solicitudTO.getObservacion());
            solicitudEntity.setUrlAdjunto(solicitudTO.getUrlAdjunto());
            solicitudEntity.setUsrIngresado(solicitudTO.getUsrIngresado());
            solicitudEntity.setIdComuna(solicitudTO.getIdComuna());
            solicitudEntity.setMotivoRechazo(solicitudTO.getMotivoRechazo());
            solicitudEntity.setTipoSolicitante(solicitudTO.getTipoSolicitante());
            solicitudEntity.setNumReclamo(solicitudEntity.getNumReclamo());
            return solicitudEntity;
	}

    
}
