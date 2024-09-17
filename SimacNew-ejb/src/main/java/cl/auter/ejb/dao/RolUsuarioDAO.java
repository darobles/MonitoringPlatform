/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.RolUsuarioEntity;
import cl.auter.patron.to.RolUsuarioTO;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class RolUsuarioDAO extends BaseDAO<RolUsuarioEntity>{

    public RolUsuarioDAO(Class<RolUsuarioEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public RolUsuarioTO buscarRolUsuario(String idUsuario)throws Exception{
        Query query = getEm().createNamedQuery("RolUsuarioEntity.buscarRolUsuario");
        query.setParameter("idUsuario", idUsuario);
        RolUsuarioEntity rolUsuarioEntity = null;
        rolUsuarioEntity = (RolUsuarioEntity)query.getSingleResult();        
        return traspasar(rolUsuarioEntity);
    }
    
    public void guardar(RolUsuarioTO rolUsuarioTO)throws Exception{
        persist(traspasar(rolUsuarioTO));    
    }
    
    public void editar(RolUsuarioTO rolUsuarioTO)throws Exception{
        edit(traspasar(rolUsuarioTO));    
    }
    
    public void eliminar(RolUsuarioTO rolUsuarioTO)throws Exception{
        remove(traspasar(rolUsuarioTO));    
    }
    
    public boolean existeRolUsuarioTO(String identificador)throws Exception{
        Query query = getEm().createNamedQuery("RolUsuarioEntity.buscarRolUsuario");
        query.setParameter("idUsuario", identificador);
        RolUsuarioEntity rolUsuarioEntity = null;
        try{
            rolUsuarioEntity = (RolUsuarioEntity)query.getSingleResult();        
        }catch(Exception e){        
        }
        if(rolUsuarioEntity != null){
            return true;
        }else{
            return false;
        }
    }
    
    public RolUsuarioTO traspasar(RolUsuarioEntity rolUsuarioEntity){
        RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
        rolUsuarioTO.setId(rolUsuarioEntity.getId());
        rolUsuarioTO.setIdRol(rolUsuarioEntity.getIdRol());
        rolUsuarioTO.setIdUsuario(rolUsuarioEntity.getIdUsuario());
        rolUsuarioTO.setOpcion(rolUsuarioEntity.getOpcion());
        return rolUsuarioTO;
    }
    
    public RolUsuarioEntity traspasar(RolUsuarioTO rolUsuarioTO){
        RolUsuarioEntity rolUsuarioEntity = new RolUsuarioEntity();
        if(rolUsuarioTO.getId() != null){
            rolUsuarioEntity.setId(rolUsuarioTO.getId());
        }        
        rolUsuarioEntity.setIdRol(rolUsuarioTO.getIdRol());
        rolUsuarioEntity.setIdUsuario(rolUsuarioTO.getIdUsuario());
        rolUsuarioEntity.setOpcion(rolUsuarioTO.getOpcion());
        return rolUsuarioEntity;
    }
}
