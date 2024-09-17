/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.UsuarioEnlaceEntity;
import cl.auter.patron.to.UsuarioEnlaceTO;
import cl.auter.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class UsuarioEnlaceDAO extends BaseDAO<UsuarioEnlaceEntity>{

    public UsuarioEnlaceDAO(Class<UsuarioEnlaceEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(UsuarioEnlaceTO userTO)throws Exception {
        UsuarioEnlaceEntity usuarioEntity =traspasar(userTO);
        persist(usuarioEntity);			
    }
    
    public void editar(UsuarioEnlaceTO userTO)throws Exception {
        UsuarioEnlaceEntity usuarioEntity =traspasar(userTO);
        edit(usuarioEntity);			
    }
    
    public void eliminar(UsuarioEnlaceTO userTO)throws Exception{
        UsuarioEnlaceEntity usuarioEntity =traspasar(userTO);
        remove(usuarioEntity);			
    }
    
    public boolean existeUsuario(String usuario){
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuarioPorId");
        query.setParameter("identificador", Util.convertFromUTF8(usuario).toUpperCase());
        UsuarioEnlaceEntity usuarioEntity = null;
        try{
            usuarioEntity = (UsuarioEnlaceEntity)query.getSingleResult();        
        }catch(Exception e){        
        }
        if(usuarioEntity != null){
            return true;
        }else{
            return false;
        }
    }
    
    public UsuarioEnlaceTO buscarUsuarioPorId(int id) {
        Query query = getEm().createNamedQuery("UsuarioEnlaceEntity.findById");
        query.setParameter("id", id);
        UsuarioEnlaceEntity usuarioEntity = (UsuarioEnlaceEntity)query.getSingleResult();        
        return traspasar(usuarioEntity);
    }
     
    public List<UsuarioEnlaceTO> listaUsuarioPorComuna(int idComuna)  {
        List<UsuarioEnlaceTO> listaUsuario = new ArrayList();
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuarioPorIdComuna");
        query.setParameter("idComuna", idComuna);
        List<UsuarioEnlaceEntity> listaUsuarioEntity = query.getResultList();  
        for(UsuarioEnlaceEntity usuarioEntity : listaUsuarioEntity){
            listaUsuario.add(traspasar(usuarioEntity));
        }
        return listaUsuario;
    }  
    
    public List<UsuarioEnlaceTO> listaUsuario()  {
        List<UsuarioEnlaceTO> listaUsuario = new ArrayList();
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuario");
        List<UsuarioEnlaceEntity> listaUsuarioEntity = query.getResultList();  
        for(UsuarioEnlaceEntity usuarioEntity : listaUsuarioEntity){
            listaUsuario.add(traspasar(usuarioEntity));
        }
        return listaUsuario;
    }  
    
    public UsuarioEnlaceEntity traspasar(UsuarioEnlaceTO usuarioTO){
        UsuarioEnlaceEntity usuarioEntity = new UsuarioEnlaceEntity();
        usuarioEntity.setId(usuarioTO.getId());
        usuarioEntity.setIdeUsr(Util.convertFromUTF8(usuarioTO.getIdeUsr()).toUpperCase());
        usuarioEntity.setApePat(Util.convertFromUTF8(usuarioTO.getApePat()).toUpperCase());
        usuarioEntity.setApeMat(Util.convertFromUTF8(usuarioTO.getApeMat()).toUpperCase());
        usuarioEntity.setIndAct(usuarioTO.getIndAct());        
        usuarioEntity.setFecIng(new Date());
        usuarioEntity.setEmaUsr(Util.convertFromUTF8(usuarioTO.getEmaUsr()));        
        usuarioEntity.setPasUsr(usuarioTO.getPasUsr());
        usuarioEntity.setTipo(usuarioTO.getTipo());
        usuarioEntity.setNomEmp(Util.convertFromUTF8(usuarioTO.getNomEmp()));
        usuarioEntity.setRol(usuarioTO.getRol());
        return usuarioEntity;
    }
    
    public UsuarioEnlaceTO traspasar(UsuarioEnlaceEntity usuarioEntity){
        UsuarioEnlaceTO usuarioTO = new UsuarioEnlaceTO();
        usuarioTO.setId(usuarioEntity.getId());
        usuarioTO.setIdeUsr(Util.convertFromUTF8(usuarioEntity.getIdeUsr()).toUpperCase());
        usuarioTO.setApePat(Util.convertFromUTF8(usuarioEntity.getApePat()).toUpperCase());
        usuarioTO.setApeMat(Util.convertFromUTF8(usuarioEntity.getApeMat()).toUpperCase());
        usuarioTO.setIndAct(usuarioEntity.getIndAct());        
        usuarioTO.setFecIng(new Date());
        usuarioTO.setEmaUsr(Util.convertFromUTF8(usuarioEntity.getEmaUsr()));        
        usuarioTO.setPasUsr(usuarioEntity.getPasUsr());
        usuarioTO.setTipo(usuarioEntity.getTipo());
        usuarioTO.setNomEmp(Util.convertFromUTF8(usuarioEntity.getNomEmp()));
        usuarioTO.setRol(usuarioEntity.getRol());
        return usuarioTO;
    }    
}
