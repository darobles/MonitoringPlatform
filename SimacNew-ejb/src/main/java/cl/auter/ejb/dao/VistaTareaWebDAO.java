/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaTareaWebEntity;
import cl.auter.patron.to.VistaTareaWebTO;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaTareaWebDAO extends BaseDAO<VistaTareaWebEntity> {

    public VistaTareaWebDAO(Class<VistaTareaWebEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public VistaTareaWebTO obtenerTareaWeb(int id) {
        Query query = getEm().createNamedQuery("VistaTareaWebEntity.findByIdtarea");
        query.setParameter("idtarea", id);
        try{
            VistaTareaWebEntity vistaTareaWeb = (VistaTareaWebEntity)query.getSingleResult();
            return traspasar(vistaTareaWeb);
        }
        catch(NoResultException ex)
        {
            return null;
        }
        
    }

    public VistaTareaWebTO traspasar(VistaTareaWebEntity vistaTareaWebEntity)
    {
        VistaTareaWebTO vistaTareaWebTO = new VistaTareaWebTO();
        vistaTareaWebTO.setIdtarea(vistaTareaWebEntity.getIdtarea());
        vistaTareaWebTO.setTipoequipo(vistaTareaWebEntity.getTipoequipo());
        vistaTareaWebTO.setFuncionamientiini(vistaTareaWebEntity.getFuncionamientiini());
        vistaTareaWebTO.setIdcruce(vistaTareaWebEntity.getIdcruce());
        vistaTareaWebTO.setFechaCierreStr(vistaTareaWebEntity.getFechaCierreStr());
        vistaTareaWebTO.setFechaCreacionStr(vistaTareaWebEntity.getFechaCreacionStr());
        vistaTareaWebTO.setObspreliminar(vistaTareaWebEntity.getObspreliminar());
        vistaTareaWebTO.setTareaterminada(vistaTareaWebEntity.getTareaterminada());
        vistaTareaWebTO.setInformadoPor(vistaTareaWebEntity.getInformadoPor());
        vistaTareaWebTO.setNombrefuente(vistaTareaWebEntity.getNombrefuente());
        vistaTareaWebTO.setFechacreacion(vistaTareaWebEntity.getFechacreacion());
        vistaTareaWebTO.setFechacierre(vistaTareaWebEntity.getFechacreacion());
        vistaTareaWebTO.setIdtipoequipo(vistaTareaWebEntity.getIdtipoequipo());
        return vistaTareaWebTO;
    }
  
    
}
