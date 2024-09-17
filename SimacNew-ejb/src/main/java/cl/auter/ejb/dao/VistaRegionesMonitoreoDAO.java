/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.RegionEntity;
import cl.auter.ejb.entity.bmp.VistaRegionesMonitoreoEntity;
import cl.auter.patron.to.RegionTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class VistaRegionesMonitoreoDAO extends BaseDAO<VistaRegionesMonitoreoEntity>{
     public VistaRegionesMonitoreoDAO(Class<VistaRegionesMonitoreoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<RegionTO> buscarTodos() {
        Query query = getEm().createNamedQuery("VistaRegionesMonitoreoEntity.findAll");
        List<VistaRegionesMonitoreoEntity> listaRegionEntity = query.getResultList();
        List<RegionTO> listaRegion = new ArrayList();
        for (VistaRegionesMonitoreoEntity regionEntity : listaRegionEntity) {
            listaRegion.add(traspasar(regionEntity));
        }
        return listaRegion;
    }
    
    public RegionTO traspasar(VistaRegionesMonitoreoEntity regionEntity)
    {
        RegionTO regionTO = new RegionTO();
        regionTO.setId_region(regionEntity.getIdRegion().intValue());
        regionTO.setNombre(regionEntity.getNombre());
        return regionTO;
    }

}
