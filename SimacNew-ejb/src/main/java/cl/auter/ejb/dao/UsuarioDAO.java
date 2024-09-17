/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.UsuarioEntity;
import cl.auter.patron.to.UserTO;
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
public class UsuarioDAO extends BaseDAO<UsuarioEntity>{

    public UsuarioDAO(Class<UsuarioEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(UserTO userTO)throws Exception {
        UsuarioEntity usuarioEntity =traspasar(userTO);
        persist(usuarioEntity);			
    }
    
    public void editar(UserTO userTO)throws Exception {
        UsuarioEntity usuarioEntity =traspasar(userTO);
        edit(usuarioEntity);			
    }
    
    public void eliminar(UserTO userTO)throws Exception{
        UsuarioEntity usuarioEntity =traspasar(userTO);
        remove(usuarioEntity);			
    }
    
    public boolean actualizarComunaUsuario(BigDecimal idcomuna) {
        StringBuffer strsql=new StringBuffer();
        strsql.append("update usuario set idcomuna = 0 where idcomuna = ?");
        Query query = getEm().createNativeQuery(strsql.toString());
        query.setParameter(1, idcomuna.intValue());
        Object result = null;
        try{
            result = query.executeUpdate();
        }catch(Exception e){
        
        }
        if(result == null){
            return false;
        }
        return true;
    } //
    
    public boolean existeUsuario(String usuario)throws Exception {
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuarioPorId");
        query.setParameter("identificador", Util.convertFromUTF8(usuario).toUpperCase());
        UsuarioEntity usuarioEntity = null;
        try{
            usuarioEntity = (UsuarioEntity)query.getSingleResult();        
        }catch(Exception e){        
        }
        if(usuarioEntity != null){
            return true;
        }else{
            return false;
        }
    }
    
    public UserTO buscarUsuarioPorId(String identificador)throws Exception {
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuarioPorId");
        query.setParameter("identificador", identificador);
        UsuarioEntity usuarioEntity = (UsuarioEntity)query.getSingleResult();        
        return traspasar(usuarioEntity);
    }
     
    public List<UserTO> listaUsuarioPorComuna(BigDecimal idComuna)throws Exception  {
        List<UserTO> listaUsuario = new ArrayList<UserTO>();
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuarioPorIdComuna");
        query.setParameter("idComuna", idComuna);
        List<UsuarioEntity> listaUsuarioEntity = query.getResultList();  
        for(UsuarioEntity usuarioEntity : listaUsuarioEntity){
            listaUsuario.add(traspasar(usuarioEntity));
        }
        return listaUsuario;
    }  
    
    public List<UserTO> listaUsuario()throws Exception  {
        List<UserTO> listaUsuario = new ArrayList<UserTO>();
        Query query = getEm().createNamedQuery("UsuarioEntity.buscarUsuario");
        List<UsuarioEntity> listaUsuarioEntity = query.getResultList();  
        for(UsuarioEntity usuarioEntity : listaUsuarioEntity){
            listaUsuario.add(traspasar(usuarioEntity));
        }
        return listaUsuario;
    }  
    
    public UsuarioEntity traspasar(UserTO usuarioTO){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setIdentificador(Util.convertFromUTF8(usuarioTO.getIdentificador()).toUpperCase());
        usuarioEntity.setNombre(Util.convertFromUTF8(usuarioTO.getNombre()).toUpperCase());
        usuarioEntity.setApellidoPaterno(Util.convertFromUTF8(usuarioTO.getApellidoPaterno()).toUpperCase());
        usuarioEntity.setApellidoMaterno(Util.convertFromUTF8(usuarioTO.getApellidoMaterno()).toUpperCase());
        usuarioEntity.setIntAct(usuarioTO.getIntAct());        
        usuarioEntity.setFechaIngreso(new Date());
        usuarioEntity.setEmail(Util.convertFromUTF8(usuarioTO.getEmail()));        
        usuarioEntity.setPassword(usuarioTO.getPassword());
        usuarioEntity.setTipo(usuarioTO.getTipo());
        usuarioEntity.setNombreEmpresa(Util.convertFromUTF8(usuarioTO.getNombreEmpresa()));
        usuarioEntity.setIdComuna(usuarioTO.getIdComuna());
        usuarioEntity.setRol(usuarioTO.getRol());
        usuarioEntity.setLatitud(usuarioTO.getLatitud());
        usuarioEntity.setLongitud(usuarioTO.getLongitud());
        return usuarioEntity;
    }
    
    public UserTO traspasar(UsuarioEntity usuarioEntity){
        UserTO userTO = new UserTO();
        userTO.setIdentificador(usuarioEntity.getIdentificador());
        userTO.setNombre(usuarioEntity.getNombre());
        userTO.setApellidoPaterno(usuarioEntity.getApellidoPaterno());
        userTO.setApellidoMaterno(usuarioEntity.getApellidoMaterno());
        userTO.setIntAct(usuarioEntity.getIntAct());
        if(usuarioEntity.getFechaIngreso() != null){
            userTO.setFechaDescripcion(Util.fechaToString(usuarioEntity.getFechaIngreso()));
        }        
        userTO.setEmail(usuarioEntity.getEmail());
        userTO.setPassword(usuarioEntity.getPassword());
        userTO.setTipo(usuarioEntity.getTipo());
        userTO.setNombreEmpresa(usuarioEntity.getNombreEmpresa());
        userTO.setIdComuna(usuarioEntity.getIdComuna());
        userTO.setRol(usuarioEntity.getRol());
        userTO.setLatitud(usuarioEntity.getLatitud());
        userTO.setLongitud(usuarioEntity.getLongitud());
        if(userTO.getTipo().equals(new BigDecimal("240"))) //auter
        {
            userTO.setNombre_tipo("AUTER");
        }
        else if(userTO.getTipo().equals(new BigDecimal("241"))) //Municipal
        {
            userTO.setNombre_tipo("Municipal");
        }
        else if(userTO.getTipo().equals(new BigDecimal("242"))) //uoct
        { 
            userTO.setNombre_tipo("UOCT");

        }
        return userTO;
    }    
}
