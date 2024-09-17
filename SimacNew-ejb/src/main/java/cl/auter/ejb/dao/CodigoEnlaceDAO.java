/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.CodigoEnlaceEntity;
import cl.auter.patron.to.CodigoEnlaceTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class CodigoEnlaceDAO extends BaseDAO<CodigoEnlaceEntity>{

    public CodigoEnlaceDAO(Class<CodigoEnlaceEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    public List<CodigoEnlaceTO> buscarTodos(){
        List<CodigoEnlaceTO> listaCodigo = new ArrayList<CodigoEnlaceTO>();
        for (CodigoEnlaceEntity codigoEntity: findAll()){
                listaCodigo.add(traspasar(codigoEntity));
        }
        return listaCodigo;
    }
    
    public List<CodigoEnlaceTO> buscarPorDominio(String dominio){
        Query query = getEm().createNamedQuery("CodigoEnlaceEntity.buscaPorDominio");
        query.setParameter("dominio", dominio);
        List<CodigoEnlaceEntity> listaCodigoEnlaceEntity = query.getResultList();
        List<CodigoEnlaceTO> listaCodigo = new ArrayList<CodigoEnlaceTO>();
        for (CodigoEnlaceEntity codigoEntity: listaCodigoEnlaceEntity ){
            listaCodigo.add(traspasar(codigoEntity));
        }
	return listaCodigo;
    }
    /**
     * Metodo que busca dentro de la base de datos sobre la tabla codigo en funcion de un Dominio y codigo dado
     * @param dominio
     * @param codigo1
     * @return 
     */
    public CodigoEnlaceTO buscarPorDominioCodigo(String dominio,String codigo1){
        Query query = getEm().createNamedQuery("CodigoEnlaceEntity.buscaPorDominioCodigo");
        query.setParameter("dominio", dominio);
        query.setParameter("codigo1", codigo1);
        CodigoEnlaceEntity codigoEntity = (CodigoEnlaceEntity)query.getSingleResult();
        traspasar(codigoEntity);
        return traspasar(codigoEntity);
        
    }
    public CodigoEnlaceTO buscarPorDominioCodigo(String dominio,String codigo1,String codigo2){
        Query query = getEm().createNamedQuery("CodigoEnlaceEntity.buscaPorDominioCodigos");
        query.setParameter("dominio", dominio);
        query.setParameter("codigo1", codigo1);
         query.setParameter("codigo2", codigo2);
        CodigoEnlaceEntity codigoEntity = (CodigoEnlaceEntity)query.getSingleResult();
        traspasar(codigoEntity);
        return traspasar(codigoEntity);
        
    }
    
    public CodigoEnlaceTO traspasar(CodigoEnlaceEntity comunaEntity){
        CodigoEnlaceTO codigoTO = new CodigoEnlaceTO();
        codigoTO.setId_codigo(comunaEntity.getIdCodigo());
        codigoTO.setDominio(comunaEntity.getDominio());
        codigoTO.setCodigo1(comunaEntity.getCodigo1());
        codigoTO.setCodigo2(comunaEntity.getCodigo2());
        codigoTO.setDescripcion(comunaEntity.getDescripcion());
        return codigoTO;
    }
        
      public CodigoEnlaceEntity traspasar(CodigoEnlaceTO codigoTO){
        CodigoEnlaceEntity codigoEntity = new CodigoEnlaceEntity();
        codigoEntity.setIdCodigo(codigoTO.getId_codigo());
        codigoEntity.setDominio(codigoTO.getDominio());
        codigoEntity.setCodigo1(codigoTO.getCodigo1());
        codigoEntity.setCodigo2(codigoTO.getCodigo2());
        codigoEntity.setDescripcion(codigoTO.getDescripcion());
        return codigoEntity;
    }
    
}
