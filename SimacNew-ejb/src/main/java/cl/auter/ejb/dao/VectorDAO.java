/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VectorEntity;
import cl.auter.patron.to.VectorTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VectorDAO extends BaseDAO<VectorEntity> {
     public VectorDAO(Class<VectorEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public VectorTO buscarVectorPorId(BigDecimal id){
        List<VectorTO> listaVector = new ArrayList<VectorTO>();
        Query query = getEm().createNamedQuery("VectorEntity.findById");
        query.setParameter("id", id);
        VectorEntity vectorEntity = (VectorEntity) query.getSingleResult();
        return traspasar(vectorEntity);
    }

    public VectorTO traspasar(VectorEntity vectorEntity) {
        VectorTO vectorTO = new VectorTO();
        vectorTO.setId(vectorEntity.getId());
        vectorTO.setActualizacion(vectorEntity.getActualizacion());
        vectorTO.setAvgtime(vectorEntity.getAvgtime());
        vectorTO.setDistancia(vectorEntity.getDistancia());
        vectorTO.setSpeed(vectorEntity.getSpeed());
        vectorTO.setNodoinicial(vectorEntity.getNodoinicial());
        vectorTO.setNodofinal(vectorEntity.getNodofinal());
        vectorTO.setIdMarca(vectorEntity.getIdMarca());
        vectorTO.setTrayecto(vectorEntity.getTrayecto());
        vectorTO.setPcinicial(vectorEntity.getPcinicial());
        vectorTO.setPcfinal(vectorEntity.getPcfinal());
        vectorTO.setPcminicial(vectorEntity.getPcminicial());
        vectorTO.setPcmfinal(vectorEntity.getPcmfinal());
        vectorTO.setFpcminicial(vectorEntity.getFpcminicial());
        vectorTO.setFpcmfinal(vectorEntity.getFpcmfinal());
        vectorTO.setMovil(vectorEntity.getMovil());        
         vectorTO.setNumLec(vectorEntity.getNumLec());
        vectorTO.setFecSis(vectorEntity.getFecSis());
        vectorTO.setInfMailErr(vectorEntity.getInfMailErr());
        vectorTO.setNewValAlg(vectorEntity.getNewValAlg());
        vectorTO.setAntValAlg(vectorEntity.getAntValAlg());
        return vectorTO;
    }

    public VectorEntity traspasar(VectorTO vectorTO) {
        VectorEntity vectorEntity = new VectorEntity();
        vectorEntity.setId(vectorTO.getId());
        vectorEntity.setActualizacion(vectorTO.getActualizacion());
        vectorEntity.setAvgtime(vectorTO.getAvgtime());
        vectorEntity.setDistancia(vectorTO.getDistancia());
        vectorEntity.setSpeed(vectorTO.getSpeed());
        vectorEntity.setNodoinicial(vectorTO.getNodoinicial());
        vectorEntity.setNodofinal(vectorTO.getNodofinal());
        vectorEntity.setIdMarca(vectorTO.getIdMarca());
        vectorEntity.setTrayecto(vectorTO.getTrayecto());
        vectorEntity.setPcinicial(vectorTO.getPcinicial());
        vectorEntity.setPcfinal(vectorTO.getPcfinal());
        vectorEntity.setPcminicial(vectorTO.getPcminicial());
        vectorEntity.setPcmfinal(vectorTO.getPcmfinal());
        vectorEntity.setFpcminicial(vectorTO.getFpcminicial());
        vectorEntity.setFpcmfinal(vectorTO.getFpcmfinal());
        vectorEntity.setMovil(vectorTO.getMovil());        
        vectorEntity.setNumLec(vectorTO.getNumLec());
        vectorEntity.setFecSis(vectorTO.getFecSis());
        vectorEntity.setInfMailErr(vectorTO.getInfMailErr());
        vectorEntity.setNewValAlg(vectorTO.getNewValAlg());
        vectorEntity.setAntValAlg(vectorTO.getAntValAlg());
        return vectorEntity;
    }
}
