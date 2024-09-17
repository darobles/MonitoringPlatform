/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.RegionEntity;
import cl.auter.patron.to.RegionTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class RegionDAO extends BaseDAO<RegionEntity> {

    public RegionDAO(Class<RegionEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<RegionTO> buscarTodos() {
        Query query = getEm().createNamedQuery("RegionEntity.findAll");
        List<RegionEntity> listaRegionEntity = query.getResultList();
        List<RegionTO> listaRegion = new ArrayList<RegionTO>();
        for (RegionEntity regionEntity : listaRegionEntity) {
            listaRegion.add(traspasar(regionEntity));
        }
        return listaRegion;
    }
    
    public RegionTO traspasar(RegionEntity regionEntity)
    {
        RegionTO regionTO = new RegionTO();
        regionTO.setId_region(regionEntity.getIdRegion().intValue());
        regionTO.setNombre(regionEntity.getNombre());
        return regionTO;
    }
 
}
