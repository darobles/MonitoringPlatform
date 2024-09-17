/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.FasePPREntity;
import cl.auter.patron.to.FasePPRTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class FasePPRDAO extends BaseDAO<FasePPREntity> {
    
    public FasePPRDAO(Class<FasePPREntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(FasePPRTO fasePPRTO) {
        FasePPREntity fasePPREntity = traspasar(fasePPRTO);
        persist(fasePPREntity);        
    }
    
    public void editar(FasePPRTO fasePPRTO) {
        FasePPREntity fasePPREntity = traspasar(fasePPRTO);
        edit(fasePPREntity);        
    }
    
    public void eliminar(FasePPRTO fasePPRTO){
        FasePPREntity fasePPREntity = traspasar(fasePPRTO);
        remove(fasePPREntity);        
    }
    
    public boolean existeCruce(BigDecimal id) {
        Query query = getEm().createNamedQuery("FasePPREntity.findById");
        query.setParameter("id", id);
        FasePPREntity fasePPREntity = null;
        try {
            fasePPREntity = (FasePPREntity) query.getSingleResult();            
        } catch (Exception e) {            
        }
        if (fasePPREntity != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<FasePPRTO> buscarFasesPorCruce(BigDecimal idCruce) {
        List<FasePPRTO> listaFasePPRTO = new ArrayList<>();
        Query query = getEm().createNamedQuery("FasePPREntity.findByIdcruce");
        query.setParameter("idcruce", idCruce);
        List<FasePPREntity> listaCruceEntity = query.getResultList();     
        for (FasePPREntity fasePPREntity : listaCruceEntity) {
            listaFasePPRTO.add(traspasar(fasePPREntity));
        }        
        return listaFasePPRTO;
    }
    
    public List<FasePPRTO> buscarFasePPRTodos() {
        List<FasePPRTO> listaFasePPRTO = new ArrayList<>();
        Query query = getEm().createNamedQuery("FasePPREntity.findAll");
        List<FasePPREntity> listaFasePPREntity = query.getResultList();        
        for (FasePPREntity fasePPREntity : listaFasePPREntity) {
            listaFasePPRTO.add(traspasar(fasePPREntity));
        }        
        return listaFasePPRTO;
    }
    
    public FasePPRTO traspasar(FasePPREntity fasePPREntity) {
        FasePPRTO fasePPRTO = new FasePPRTO();
        fasePPRTO.setId(fasePPREntity.getId());
        fasePPRTO.setDirfase(fasePPREntity.getDirfase());
        fasePPRTO.setFase(fasePPREntity.getFase());
        fasePPRTO.setIdCruce(fasePPREntity.getIdcruce());
        return fasePPRTO;
    }

    public FasePPREntity traspasar(FasePPRTO fasePPRTO) {
        FasePPREntity fasePPREntity = new FasePPREntity();
        if(fasePPRTO.getId() != null)
        {
            fasePPREntity.setId(fasePPRTO.getId());
        }
        fasePPREntity.setDirfase(fasePPRTO.getDirfase());
        fasePPREntity.setFase(fasePPRTO.getFase());
        fasePPREntity.setIdcruce(fasePPRTO.getIdCruce());
        return fasePPREntity;
    }
    
}
