/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.InstalacionEntity;
import cl.auter.patron.to.InstalacionTO;
import cl.auter.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class InstalacionDAO extends BaseDAO<InstalacionEntity>{

    public InstalacionDAO(Class<InstalacionEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(InstalacionTO instalacionTO)throws Exception {
        InstalacionEntity instalacionEntity = traspasar(instalacionTO);
        persist(instalacionEntity);
    }
    
    public void editar(InstalacionTO instalacionTO)throws Exception {
        InstalacionEntity instalacionEntity = traspasar(instalacionTO);
        edit(instalacionEntity);
    }
    
    public void eliminar(InstalacionTO instalacionTO)throws Exception{
        InstalacionEntity instalacionEntity = traspasar(instalacionTO);
        remove(instalacionEntity);
    }
    
    public InstalacionTO buscarInstalacionPorId(BigDecimal idInstalacion)throws Exception {
        Query query = getEm().createNamedQuery("InstalacionEntity.buscarInstalacionPorId");
        query.setParameter("idInstalacion", idInstalacion);
        InstalacionEntity instalacionEntity = (InstalacionEntity)query.getSingleResult(); 
        return traspasar(instalacionEntity);
    }
    
    public List<InstalacionTO> buscarInstalacionPorIdCruce(BigDecimal idCruce)throws Exception {
        List<InstalacionTO> listaInstalacion = new ArrayList<InstalacionTO>();
        Query query = getEm().createNamedQuery("InstalacionEntity.buscarInstalacionPorIdCruce");
        query.setParameter("idCruce", idCruce);
        List<InstalacionEntity> listaInstalacionEntity = query.getResultList(); 
        for(InstalacionEntity instalacionEntity : listaInstalacionEntity){
            listaInstalacion.add(traspasar(instalacionEntity));
        }
        return listaInstalacion;
    }
        
    public InstalacionEntity traspasar(InstalacionTO instalacionTO){
        InstalacionEntity instalacionEntity = new InstalacionEntity();
        if(instalacionTO.getId() != null && !instalacionTO.getId().equals(new BigDecimal("0"))){
            instalacionEntity.setId(instalacionTO.getId());
        }        
        instalacionEntity.setIdCruce(instalacionTO.getIdCruce());
        instalacionEntity.setLatitud(instalacionTO.getLatitud());
        instalacionEntity.setLongitud(instalacionTO.getLongitud());
        instalacionEntity.setTipo(instalacionTO.getTipo());
        instalacionEntity.setEnlace(instalacionTO.getEnlace());
        instalacionEntity.setNumCabezales(instalacionTO.getNumCabezales());
        instalacionEntity.setNumEspScoot(instalacionTO.getNumEspScoot());   
        instalacionEntity.setNumEspLocal(instalacionTO.getNumEspLocal());
        instalacionEntity.setNumBotoneras(instalacionTO.getNumBotoneras());
        instalacionEntity.setNumHitElec(instalacionTO.getNumHitElec());
        instalacionEntity.setNumHitSolar(instalacionTO.getNumHitSolar());
        instalacionEntity.setUps(instalacionTO.getUps());
        instalacionEntity.setNumCtaRegres(instalacionTO.getNumCtaRegres());
        instalacionEntity.setNumSenSenTto(instalacionTO.getNumSenSenTto());
        instalacionEntity.setNumLetVms(instalacionTO.getNumLetVms());
        instalacionEntity.setBalizas(instalacionTO.getBalizas());
        instalacionEntity.setTipoCable(instalacionTO.getTipoCable());
        instalacionEntity.setModeloOtu(instalacionTO.getModeloOtu());
        if(instalacionTO.getObservacion() != null) {
            instalacionEntity.setObservacion(Util.convertFromUTF8(instalacionTO.getObservacion()));
        }
        instalacionEntity.setFechaActual(new Date());
        instalacionEntity.setEmpalme(instalacionTO.getEmpalme());    
        return instalacionEntity;
    }
    
      public InstalacionTO traspasar(InstalacionEntity instalacionEntity){
        InstalacionTO instalacionTO = new InstalacionTO();
        instalacionTO.setId(instalacionEntity.getId());
        instalacionTO.setIdCruce(instalacionEntity.getIdCruce());
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
        instalacionTO.setFechaActual(instalacionEntity.getFechaActual());
        if(instalacionEntity.getFechaActual() != null){
            instalacionTO.setFechaActualDesc(Util.fechaToString(instalacionEntity.getFechaActual()));
        }
        instalacionTO.setEmpalme(instalacionEntity.getEmpalme());    
        return instalacionTO;
    }   
}
