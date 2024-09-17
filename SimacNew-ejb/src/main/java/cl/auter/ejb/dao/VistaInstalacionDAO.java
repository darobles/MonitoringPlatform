package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.InstalacionEntity;
import cl.auter.ejb.entity.bmp.VistaInstalacionEntity;
import cl.auter.patron.to.InstalacionTO;
import cl.auter.patron.to.VistaInstalacionTO;
import cl.auter.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author rbaeza
 */
public class VistaInstalacionDAO extends BaseDAO<VistaInstalacionEntity>{

    public VistaInstalacionDAO(Class<VistaInstalacionEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<VistaInstalacionTO> buscarInstalacionPor(BigDecimal IdComuna) {
        List<VistaInstalacionTO> listaInstalaciones = new ArrayList();
        Query query = getEm().createNamedQuery("VistaInstalacionEntity.findByIdcomuna");
        query.setParameter("idcomuna",IdComuna);
        List<VistaInstalacionEntity> listaEntity=query.getResultList();
        for (VistaInstalacionEntity instalacionEntity: listaEntity){
                listaInstalaciones.add(traspasar(instalacionEntity));
        }
	return listaInstalaciones;
    }
    
    public List<InstalacionTO> buscarInstalacionPorIdComuna(BigDecimal IdComuna) {
        List<InstalacionTO> listaInstalaciones = new ArrayList();
        Query query = getEm().createNamedQuery("VistaInstalacionEntity.findByIdcomuna");
        query.setParameter("idcomuna",IdComuna);
        List<VistaInstalacionEntity> listaEntity=query.getResultList();
        for (VistaInstalacionEntity instalacionEntity: listaEntity){
                listaInstalaciones.add(traspasarIns(instalacionEntity));
        }
	return listaInstalaciones;
    }
    
    
    
    public List<VistaInstalacionTO> buscarInstalacionMonitoreo() {
        List<VistaInstalacionTO> listaInstalaciones = new ArrayList<VistaInstalacionTO>();
        Query query = getEm().createNamedQuery("VistaInstalacionEntity.InstalacionPorMonitoreo");
        List<VistaInstalacionEntity> listaEntity=query.getResultList();
        for (VistaInstalacionEntity instalacionEntity: listaEntity){
                listaInstalaciones.add(traspasar(instalacionEntity));
        }
	return listaInstalaciones;
    }
    
    public VistaInstalacionTO buscarVistaInstalacionPorIdCruce(BigDecimal idCruce){
        Query query = getEm().createNamedQuery("VistaInstalacionEntity.findByIdcruce");
        query.setParameter("idcruce",idCruce);
        VistaInstalacionEntity vistaInstalacionEntity = (VistaInstalacionEntity)query.getSingleResult();
        return traspasar(vistaInstalacionEntity);
    }
    
    public VistaInstalacionTO buscarVistaInstalacionPorIdInstalacion(BigDecimal idInstalacion) {
        System.out.println("id instalacion: " + idInstalacion);
        Query query = getEm().createNamedQuery("VistaInstalacionEntity.findByIdInstalacion");
        query.setParameter("idInstalacion",idInstalacion);
        List<VistaInstalacionEntity> listaEntity=query.getResultList();
        if(!listaEntity.isEmpty())
            return traspasar(listaEntity.get(0));
        return new VistaInstalacionTO();
    }
    
    
    public VistaInstalacionTO traspasar(VistaInstalacionEntity vistaInstalacionEntity){
        VistaInstalacionTO vistaTO = new VistaInstalacionTO();
        vistaTO.setIdCruce(vistaInstalacionEntity.getIdcruce());
        vistaTO.setIdComuna(vistaInstalacionEntity.getIdcomuna());
        vistaTO.setNombreComuna(vistaInstalacionEntity.getNomComuna());
        vistaTO.setUbicacion(vistaInstalacionEntity.getUbicacion());
        vistaTO.setCodigoSistema(vistaInstalacionEntity.getCodigoSistema());
        vistaTO.setIdInstalacion(vistaInstalacionEntity.getIdInstalacion());
        vistaTO.setEmpalme(vistaInstalacionEntity.getEmpalme());
        vistaTO.setLatitud(vistaInstalacionEntity.getLatitud());
        vistaTO.setLongitud(vistaInstalacionEntity.getLongitud());                        
        vistaTO.setTipo(vistaInstalacionEntity.getTipo());
        vistaTO.setEnlace(vistaInstalacionEntity.getEnlace());
        vistaTO.setNumCabezales(vistaInstalacionEntity.getNumCabezales());
        vistaTO.setNumEspScoot(vistaInstalacionEntity.getNumEspScoot());     
        vistaTO.setNumEspLocal(vistaInstalacionEntity.getNumEspLocal());
        vistaTO.setNumBotoneras(vistaInstalacionEntity.getNumBotoneras());
        vistaTO.setNumHitElec(vistaInstalacionEntity.getNumHitElec());        
        vistaTO.setNumHitSolar(vistaInstalacionEntity.getNumHitSolar());
        vistaTO.setUps(vistaInstalacionEntity.getUps());
        vistaTO.setNumCtaRegres(vistaInstalacionEntity.getNumCtaRegres());
        vistaTO.setNumSenSenTto(vistaInstalacionEntity.getNumSenSenTto());
        vistaTO.setNumLetVms(vistaInstalacionEntity.getNumLetVms());
        vistaTO.setBalizas(vistaInstalacionEntity.getBalizas());
        vistaTO.setTipoCable(vistaInstalacionEntity.getTipoCable());
        vistaTO.setModeloOtu(vistaInstalacionEntity.getModeloOtu());
        vistaTO.setObservacion(vistaInstalacionEntity.getObservacion());
        vistaTO.setFechaActualizacion(vistaInstalacionEntity.getFecAct());
        vistaTO.setDatoFiltro(vistaInstalacionEntity.getDatoFiltro());
        vistaTO.setRed(vistaInstalacionEntity.getRed());
        vistaTO.setControlador(vistaInstalacionEntity.getControlador());
        return vistaTO;
    }
    
    public InstalacionTO traspasarIns(VistaInstalacionEntity instalacionEntity){
        InstalacionTO instalacionTO = new InstalacionTO();
        instalacionTO.setId(instalacionEntity.getIdInstalacion());
        instalacionTO.setIdCruce(instalacionEntity.getIdcruce());
        instalacionTO.setLatitud(instalacionEntity.getLatitud());
        instalacionTO.setLongitud(instalacionEntity.getLongitud());
        instalacionTO.setTipo(instalacionEntity.getTipo());
        instalacionTO.setEnlace(instalacionEntity.getEnlace());
        instalacionTO.setNumCabezales(instalacionEntity.getNumCabezales());
        instalacionTO.setNumEspScoot(instalacionEntity.getNumEspScoot());   
        instalacionTO.setNumEspLocal(instalacionEntity.getNumEspLocal());
        instalacionTO.setNumBotoneras(instalacionEntity.getNumBotoneras());
        instalacionTO.setNumHitElec(instalacionEntity.getNumHitElec());
        instalacionTO.setNumHitSolar(instalacionEntity.getNumHitSolar());
        instalacionTO.setUps(instalacionEntity.getUps());
        instalacionTO.setNumCtaRegres(instalacionEntity.getNumCtaRegres());
        instalacionTO.setNumSenSenTto(instalacionEntity.getNumSenSenTto());
        instalacionTO.setNumLetVms(instalacionEntity.getNumLetVms());
        instalacionTO.setBalizas(instalacionEntity.getBalizas());
        instalacionTO.setTipoCable(instalacionEntity.getTipoCable());
        instalacionTO.setModeloOtu(instalacionEntity.getModeloOtu());
        instalacionTO.setObservacion(instalacionEntity.getObservacion());
        instalacionTO.setFechaActual(instalacionEntity.getFecAct());
        if(instalacionEntity.getFecAct()!= null){
            instalacionTO.setFechaActualDesc(Util.fechaToString(instalacionEntity.getFecAct()));
        }
        instalacionTO.setEmpalme(instalacionEntity.getEmpalme());    
        return instalacionTO;
    }   


}
