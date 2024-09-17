/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaContEstadoEntity;
import cl.auter.patron.to.VistaContEstadoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class VistaContEstadoDAO extends BaseDAO<VistaContEstadoEntity> {

    public VistaContEstadoDAO(Class<VistaContEstadoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public VistaContEstadoTO buscarTodos() {
        Query query = getEm().createNamedQuery("VistaContEstadoEntity.findAll");
        List<VistaContEstadoEntity> listaContEntity = query.getResultList();
        List<VistaContEstadoTO> listaCont = new ArrayList();
        for (VistaContEstadoEntity contEntity : listaContEntity) {
            listaCont.add(traspasar(contEntity));
        }
        if(!listaCont.isEmpty())
            return listaCont.get(0);
        return new VistaContEstadoTO();
    }
    

    
    public VistaContEstadoTO traspasar(VistaContEstadoEntity contEntity)
    {
        VistaContEstadoTO contTO = new VistaContEstadoTO();
        contTO.setId(contEntity.getId().intValue());
        contTO.setEncendidos(contEntity.getEncendidos().intValue());
        contTO.setApagado(contEntity.getApagado().intValue());
        contTO.setObservacion(contEntity.getObservacion().intValue());
        contTO.setInvalidos(contEntity.getInvalidos().intValue());
        return contTO;
    }


 
}
