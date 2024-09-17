/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.EnlaceEntity;
import cl.auter.patron.to.EnlaceTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class EnlaceDAO extends BaseDAO<EnlaceEntity> {

    public EnlaceDAO(Class<EnlaceEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void guardar(EnlaceTO enlaceTO) {
        EnlaceEntity enlaceEntity = traspasar(enlaceTO);
        persist(enlaceEntity);
    }

    public void editar(EnlaceTO enlaceTO) {
        EnlaceEntity enlaceEntity = traspasar(enlaceTO);
        edit(enlaceEntity);
    }

    public void eliminar(EnlaceTO enlaceTO) {
        EnlaceEntity enlaceEntity = traspasar(enlaceTO);
        remove(enlaceEntity);
    }
    public EnlaceTO buscarEnlacePorId(int idEnlace) {
        List<EnlaceTO> listaEnlaceTO = new ArrayList<>();
        Query query = getEm().createNamedQuery("EnlaceEntity.findByIdEnlace");
        query.setParameter("idEnlace", idEnlace);
        List<EnlaceEntity> listaEnlaceEntity = query.getResultList(); 
        for(EnlaceEntity cruceEntity : listaEnlaceEntity){
            listaEnlaceTO.add(traspasar(cruceEntity));
            break;
        }     
        if(listaEnlaceTO.isEmpty())
        {
            return null;
        }
        else
        {
            return  listaEnlaceTO.get(0);
        }
    }
    
    private EnlaceEntity traspasar(EnlaceTO enlaceTO) {
        EnlaceEntity enlaceEntity = new EnlaceEntity();
        enlaceEntity.setIdTipo(enlaceTO.getIdTipo());
        enlaceEntity.setNroServicio(enlaceTO.getNroServicio());
        enlaceEntity.setIdProveedor(enlaceTO.getIdProveedor());
        enlaceEntity.setIdCruce(enlaceTO.getIdCruce());
        enlaceEntity.setIdCaracteristica(enlaceTO.getIdCaracteristica());
        enlaceEntity.setFechaHabilitacion(enlaceTO.getFechaHabilitacion());
        enlaceEntity.setEquipoEnlace(enlaceTO.getEquipoEnlace());
        enlaceEntity.setUrlConfiguracion(enlaceTO.getUrlConfiguracion());
        enlaceEntity.setUrlPlanGeneral(enlaceTO.getUrlPlanGeneral());
        enlaceEntity.setIdUsuarioMod(enlaceTO.getIdUsuarioMod());
        enlaceEntity.setFechaActualizacion(enlaceTO.getFechaHabilitacion());
        return enlaceEntity;
    }
    
    private EnlaceTO traspasar(EnlaceEntity enlaceEntity) {
        EnlaceTO enlaceTO = new EnlaceTO();
        enlaceTO.setIdTipo(enlaceEntity.getIdTipo());
        enlaceTO.setNroServicio(enlaceEntity.getNroServicio());
        enlaceTO.setIdProveedor(enlaceEntity.getIdProveedor());
        enlaceTO.setIdCruce(enlaceEntity.getIdCruce());
        enlaceTO.setIdCaracteristica(enlaceEntity.getIdCaracteristica());
        enlaceTO.setFechaHabilitacion(enlaceEntity.getFechaHabilitacion());
        enlaceTO.setEquipoEnlace(enlaceEntity.getEquipoEnlace());
        enlaceTO.setUrlConfiguracion(enlaceEntity.getUrlConfiguracion());
        enlaceTO.setUrlPlanGeneral(enlaceEntity.getUrlPlanGeneral());
        enlaceTO.setIdUsuarioMod(enlaceEntity.getIdUsuarioMod());
        enlaceTO.setFechaActualizacion(enlaceEntity.getFechaHabilitacion());
        return enlaceTO;
    }
}
