/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.NodoEntity;
import cl.auter.patron.to.NodoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class NodoDAO extends BaseDAO<NodoEntity> {

    public NodoDAO(Class<NodoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<NodoTO> buscarTodos(){
        List<NodoTO> listaNodo = new ArrayList<NodoTO>();
        Query query = getEm().createNamedQuery("NodoEntity.findAll");
        List<NodoEntity> listaNodoEntity = query.getResultList(); 
        for (NodoEntity nodoEntity : listaNodoEntity) {
            listaNodo.add(traspasar(nodoEntity));
        }
        return listaNodo;
    }
    
    public List<NodoTO> buscarPorIdComuna(BigDecimal idComuna){
        List<NodoTO> listaNodo = new ArrayList<NodoTO>();
        Query query = getEm().createNamedQuery("NodoEntity.findByIdComuna");
        query.setParameter("idComuna", idComuna);
        List<NodoEntity> listaNodoEntity = query.getResultList(); 
        for (NodoEntity nodoEntity : listaNodoEntity) {
            listaNodo.add(traspasar(nodoEntity));
        }
        return listaNodo;
    }
    
    public NodoTO getNodoByIdFabrica(BigDecimal idsen_dest) {
         Query query = getEm().createNamedQuery("NodoEntity.findByIdfabrica");
         query.setParameter("idfabrica", idsen_dest);
         NodoEntity nodo = (NodoEntity)query.getSingleResult(); 
         return traspasar(nodo);
    }
    
    public NodoTO traspasar(NodoEntity nodoEntity) {
        NodoTO nodoTO = new NodoTO();
        nodoTO.setId(nodoEntity.getId());
        nodoTO.setIdCruce(new BigDecimal(-3));
        nodoTO.setIdFabrica(nodoEntity.getIdfabrica());
        nodoTO.setLatitud(nodoEntity.getLatitud());
        nodoTO.setLongitud(nodoEntity.getLongitud());
        nodoTO.setNombre(nodoEntity.getNombre());
        nodoTO.setIdMarca(nodoEntity.getIdMarca());
        nodoTO.setOdMatriz(nodoEntity.getOdMatriz());
        nodoTO.setMatrizMacro(nodoEntity.getMatrizMacro());
        return nodoTO;
    }

    public NodoEntity traspasar(NodoTO nodoTO) {
        NodoEntity nodoEntity = new NodoEntity();
        nodoEntity.setId(nodoTO.getId());
        nodoEntity.setIdfabrica(nodoTO.getIdFabrica());
        nodoEntity.setLatitud(nodoTO.getLatitud());
        nodoEntity.setLongitud(nodoTO.getLongitud());
        nodoEntity.setNombre(nodoTO.getNombre());
        nodoEntity.setIdMarca(nodoTO.getIdMarca());
        nodoEntity.setOdMatriz(nodoTO.getOdMatriz());
        nodoTO.setMatrizMacro(nodoTO.getMatrizMacro());
        return nodoEntity;
    }

    

}
