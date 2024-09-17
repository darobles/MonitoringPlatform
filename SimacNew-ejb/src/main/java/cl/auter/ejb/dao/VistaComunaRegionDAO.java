/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaRegionComunaEntity;
import cl.auter.patron.to.VistaComunaRegionTO;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaComunaRegionDAO extends BaseDAO<VistaRegionComunaEntity> {

    public VistaComunaRegionDAO(Class<VistaRegionComunaEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<VistaComunaRegionTO> buscarTodos(){
        List<VistaComunaRegionTO> listaComuna = new ArrayList();
        Query query = getEm().createNamedQuery("VistaRegionComunaEntity.findAll");
        List<VistaRegionComunaEntity> listaComunaEntity = query.getResultList();
        for (VistaRegionComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;
    }

    private VistaComunaRegionTO traspasar(VistaRegionComunaEntity comunaEntity) {
        VistaComunaRegionTO comunaTO = new VistaComunaRegionTO();
        comunaTO.setId_comuna(comunaEntity.getIdcomuna().intValue());
        comunaTO.setId_region(comunaEntity.getIdregion().intValue());
        comunaTO.setNombre_region(comunaEntity.getNombreRegion());
        comunaTO.setNombre_comuna(comunaEntity.getNombre());
        comunaTO.setUrl_logo(comunaEntity.getUrlLogo());
        comunaTO.setLatitud(Optional.ofNullable(comunaEntity.getLatitud()).orElse("").trim());
        comunaTO.setLongitud(Optional.ofNullable(comunaEntity.getLongitud()).orElse("").trim());
        comunaTO.setEmail(comunaEntity.getEmail());
        comunaTO.setTelefono(comunaEntity.getTelefono());
        if(comunaEntity.getIndMon() != null  && comunaEntity.getIndMon().equals("SI"))
        {
            comunaTO.setInd_mon("SI");
        }
        else{
            comunaTO.setInd_mon("NO");
        }
        comunaTO.setZoom(Optional.ofNullable(comunaEntity.getZoom()).orElse(BigInteger.ZERO).intValue());
        if(comunaEntity.getAutUoct()!= null  && comunaEntity.getAutUoct().equals("SI"))
        {
            comunaTO.setAut_uoct("SI");
        }
        else{
            comunaTO.setAut_uoct("NO");
        }
        return comunaTO;
    }
}
