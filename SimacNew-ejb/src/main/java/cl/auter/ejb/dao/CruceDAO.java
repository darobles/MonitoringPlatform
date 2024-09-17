/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.CruceEntity;
import cl.auter.patron.to.CruceTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class CruceDAO extends BaseDAO<CruceEntity>{

    public CruceDAO(Class<CruceEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(CruceTO cruceTO)throws Exception {
        CruceEntity cruceEntity =traspasar(cruceTO);
        persist(cruceEntity);			
    }
    
    public void editar(CruceTO cruceTO)throws Exception {
        CruceEntity cruceEntity =traspasar(cruceTO);
        cruceEntity.setIdcruce(cruceTO.getIdcruce());
        edit(cruceEntity);			
    }
    
    public void eliminar(CruceTO cruceTO)throws Exception{
        CruceEntity cruceEntity =traspasar(cruceTO);
        cruceEntity.setIdcruce(cruceTO.getIdcruce());
        remove(cruceEntity);			
    }
    
    public boolean existeCruce(BigDecimal idCruce)throws Exception {
        Query query = getEm().createNamedQuery("CruceEntity.buscarCrucePorId");
        query.setParameter("idCruce", idCruce);
        CruceEntity cruceEntity = null;
        try{
            cruceEntity = (CruceEntity)query.getSingleResult();        
        }catch(Exception e){        
        }
        if(cruceEntity != null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean existeDispositivo(BigDecimal idDispositivo)throws Exception {
        StringBuffer strsql=new StringBuffer();
        strsql.append("select * from DISPOSITIVO where iddispositivo = ?");
        Query query = getEm().createNativeQuery(strsql.toString());
        query.setParameter(1, idDispositivo.intValue());
        Object result = null;
        try{
            result = query.getSingleResult();        
        }catch(Exception e){
        
        }
        if(result == null){
            return false;
        }
        return true;
    } //
    
    
    public CruceTO buscarCrucePorId(BigDecimal idCruce)throws Exception {
        Query query = getEm().createNamedQuery("CruceEntity.buscarCrucePorId");
        query.setParameter("idCruce", idCruce);
        CruceEntity cruceEntity = (CruceEntity)query.getSingleResult();        
        return traspasar(cruceEntity);
    }
    
    public List<CruceTO> buscarCrucePorIdComuna(BigDecimal idComuna)throws Exception  {
        List<CruceTO> listaCruceTO = new ArrayList<>();
        Query query = getEm().createNamedQuery("CruceEntity.buscaPorComuna");
        query.setParameter("idComuna", idComuna);
        List<CruceEntity> listaCruceEntity = query.getResultList(); 
        for(CruceEntity cruceEntity : listaCruceEntity){
            listaCruceTO.add(traspasar(cruceEntity));
        }        
        return  listaCruceTO;
    }
    
        public List<CruceTO> buscarCruceTodos()throws Exception  {
        List<CruceTO> listaCruceTO = new ArrayList<CruceTO>();
        Query query = getEm().createNamedQuery("CruceEntity.buscaTodos");
        List<CruceEntity> listaCruceEntity = query.getResultList(); 
        for(CruceEntity cruceEntity : listaCruceEntity){
            listaCruceTO.add(traspasar(cruceEntity));
        }        
        return  listaCruceTO;
    }
    
    public CruceTO traspasar(CruceEntity cruceEntity){
        CruceTO cruceTO = new CruceTO();
        cruceTO.setIdcruce(cruceEntity.getIdcruce());
        if(cruceTO.getIdcruce().intValue() < 1000000)
        {
            cruceTO.setTipo("SMA");
        }
        else{
            cruceTO.setTipo("Simulado");
        }
        cruceTO.setCalle1(cruceEntity.getCalle1());
        cruceTO.setCalle2(cruceEntity.getCalle2());
        cruceTO.setLatitud(cruceEntity.getLatitud());
        cruceTO.setLongitud(cruceEntity.getLongitud());
        if(cruceEntity.getIdDispositivo()==null){
        cruceTO.setIdDispositivo(null);
        }else{
            cruceTO.setIdDispositivo(cruceEntity.getIdDispositivo().toString());
        }        
        cruceTO.setIdComuna(cruceEntity.getIdComuna());
        cruceTO.setControlador(cruceEntity.getControlador());
        cruceTO.setRed(cruceEntity.getRed());
        cruceTO.setJunction(cruceEntity.getJunction());
        cruceTO.setNumBitUtc(cruceEntity.getNumBitUtc());        
        cruceTO.setUbicacion(cruceEntity.getCalle1().toUpperCase() +" CON "+ cruceEntity.getCalle2().toUpperCase() + "-" + cruceEntity.getIdcruce());
        if(cruceEntity.getPpr() != null && cruceEntity.getPpr().equals(new BigDecimal("1")))
        {
            cruceTO.setPpr(true);
        }
        else{
            cruceTO.setPpr(false);
        }
        
        cruceTO.setDirActManual(cruceEntity.getDirActManual());
        cruceTO.setImagen(cruceEntity.getImagen());
        return cruceTO;
    }
    public CruceEntity traspasar(CruceTO cruceTO){
        CruceEntity cruceEntity = new CruceEntity();
        cruceEntity.setCalle1(cruceTO.getCalle1());
        cruceEntity.setCalle2(cruceTO.getCalle2());
        cruceEntity.setLatitud(cruceTO.getLatitud());
        cruceEntity.setLongitud(cruceTO.getLongitud());
        if(cruceTO.getIdDispositivo() == null || cruceTO.getIdDispositivo().equals("")){
            cruceEntity.setIdDispositivo(null);
        }else{
            cruceEntity.setIdDispositivo(new BigDecimal(cruceTO.getIdDispositivo()));
        }
        cruceEntity.setIdComuna(cruceTO.getIdComuna());
        cruceEntity.setControlador(cruceTO.getControlador());
        cruceEntity.setRed(cruceTO.getRed());
        cruceEntity.setJunction(cruceTO.getJunction());
        cruceEntity.setNumBitUtc(cruceTO.getNumBitUtc());
        if(cruceTO.isPpr())
        {
            cruceEntity.setPpr(new BigDecimal("1"));
        }
        else{
            cruceEntity.setPpr(new BigDecimal("0"));
        }
        cruceEntity.setDirActManual(cruceTO.getDirActManual());
        cruceEntity.setImagen(cruceTO.getImagen());
        return cruceEntity;
    }

    public List<CruceTO> buscarCrucePorDimac(BigDecimal idDispositivo) {
        List<CruceTO> listaCruceTO = new ArrayList<>();
        Query query = getEm().createNamedQuery("CruceEntity.buscaPorDimac");
        query.setParameter("idDispositivo", idDispositivo);
        List<CruceEntity> listaCruceEntity = query.getResultList(); 
        for(CruceEntity cruceEntity : listaCruceEntity){
            listaCruceTO.add(traspasar(cruceEntity));
        }        
        return  listaCruceTO;
    }
    
}
