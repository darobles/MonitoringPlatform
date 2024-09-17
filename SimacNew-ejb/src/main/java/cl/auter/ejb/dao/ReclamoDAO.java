/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.ReclamoEntity;
import cl.auter.patron.to.ReclamoTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class ReclamoDAO extends BaseDAO<ReclamoEntity> {

    public ReclamoDAO(Class<ReclamoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void guardar(ReclamoTO reclamoTO) {
        ReclamoEntity reclamoEntity = traspasar(reclamoTO);
        persist(reclamoEntity);
    }

    public void editar(ReclamoTO reclamoTO) {
        ReclamoEntity reclamoEntity = traspasar(reclamoTO);
        edit(reclamoEntity);
    }

    public void eliminar(ReclamoTO reclamoTO) {
        ReclamoEntity reclamoEntity = traspasar(reclamoTO);
        remove(reclamoEntity);
    }

    public List<ReclamoTO> buscarReclamosTodos() {
        List<ReclamoTO> listaReclamo = new ArrayList();
        for (ReclamoEntity reclamoEntity : findAll()) {
            listaReclamo.add(traspasar(reclamoEntity));
        }
        return listaReclamo;
    }
    
    public ReclamoTO buscarReclamoPrId(int id) {
        Query query = getEm().createNamedQuery("ReclamoEntity.findByIdReclamo");
        query.setParameter("idReclamo", id);
        try{
            ReclamoEntity reclamoEntity = (ReclamoEntity)query.getSingleResult();
            return traspasar(reclamoEntity);
        }
        catch(NoResultException ex)
        {
            return null;
        }
    }

    private ReclamoEntity traspasar(ReclamoTO reclamoTO) {
        ReclamoEntity reclamoEntity = new ReclamoEntity();
        reclamoEntity.setIdReclamo(reclamoTO.getIdReclamo());
        reclamoEntity.setIdEnlace(reclamoTO.getIdEnlace());
        reclamoEntity.setIdTicket(reclamoTO.getIdTicket());
        reclamoEntity.setIdCruce(reclamoTO.getIdCruce());
        reclamoEntity.setFechaIngreso(reclamoTO.getFechaIngreso());
        reclamoEntity.setIdEstado(reclamoTO.getIdEstado());
        reclamoEntity.setFechaTermino(reclamoTO.getFechaTermino());
        reclamoEntity.setIdTipoFalla(reclamoTO.getIdTipoFalla());
        reclamoEntity.setObservacion(reclamoTO.getObservacion());
        reclamoEntity.setIdResponsable(reclamoTO.getIdResponsable());
        reclamoEntity.setIdTipoAtencion(reclamoTO.getIdTipoAtencion());
        return reclamoEntity;
    }

    private ReclamoTO traspasar(ReclamoEntity reclamoEntity) {
        ReclamoTO reclamoTO = new ReclamoTO();
        reclamoTO.setIdReclamo(reclamoEntity.getIdReclamo());
        reclamoTO.setIdEnlace(reclamoEntity.getIdEnlace());
        reclamoTO.setIdTicket(reclamoEntity.getIdTicket());
        reclamoTO.setIdCruce(reclamoEntity.getIdCruce());
        reclamoTO.setFechaIngreso(reclamoEntity.getFechaIngreso());
        reclamoTO.setIdEstado(reclamoEntity.getIdEstado());
        reclamoTO.setFechaTermino(reclamoEntity.getFechaTermino());
        reclamoTO.setIdTipoFalla(reclamoEntity.getIdTipoFalla());
        reclamoTO.setObservacion(reclamoEntity.getObservacion());
        reclamoTO.setIdResponsable(reclamoEntity.getIdResponsable());
        reclamoTO.setIdTipoAtencion(reclamoEntity.getIdTipoAtencion());
        return reclamoTO;
    }

   
}
