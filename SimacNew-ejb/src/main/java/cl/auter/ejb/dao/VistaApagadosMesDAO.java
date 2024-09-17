/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

/**
 *
 * @author Daniel
 */

import cl.auter.ejb.entity.bmp.VistaDispApaMesEntity;
import cl.auter.patron.to.VistaApagadosUltMesTO;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaApagadosMesDAO extends BaseDAO<VistaDispApaMesEntity> {

    public VistaApagadosMesDAO(Class<VistaDispApaMesEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<VistaApagadosUltMesTO> buscarTodos(){
        List<VistaApagadosUltMesTO> listaInstalacion = new ArrayList();
        Query query = getEm().createNamedQuery("VistaDispApaMesEntity.findAll");
        List<VistaDispApaMesEntity> listaInstalacionEntity = query.setMaxResults(10).getResultList();
        for (VistaDispApaMesEntity cruceEntity : listaInstalacionEntity) {
            listaInstalacion.add(traspasar(cruceEntity));
        }
        return listaInstalacion;
    }
    
    public List<VistaApagadosUltMesTO> buscarPorRegion(int id_region){
        List<VistaApagadosUltMesTO> listaInstalacion = new ArrayList();
        Query query = getEm().createNamedQuery("VistaDispApaMesEntity.findByIdregion");
        query.setParameter("idregion", BigInteger.valueOf(id_region));
        List<VistaDispApaMesEntity> listaInstalacionEntity = query.getResultList();
        for (VistaDispApaMesEntity cruceEntity : listaInstalacionEntity) {
            listaInstalacion.add(traspasar(cruceEntity));
        }
        return listaInstalacion;
    }

    private VistaApagadosUltMesTO traspasar(VistaDispApaMesEntity cruceEntity) {
        VistaApagadosUltMesTO vista = new VistaApagadosUltMesTO();
        vista.setId_region(cruceEntity.getIdregion().intValue());
        vista.setId_city(cruceEntity.getIdcomuna().intValue());
        vista.setCity_name(cruceEntity.getNombre().trim());
        vista.setNum_turn_off(cruceEntity.getNumApagados().intValue());
        return vista;
    }
    
}
