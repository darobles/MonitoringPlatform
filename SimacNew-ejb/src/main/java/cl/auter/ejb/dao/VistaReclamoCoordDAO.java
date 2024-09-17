/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaReclamoCoordinacionEntity;
import cl.auter.patron.to.VistaReclamoCoordinacionTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaReclamoCoordDAO extends BaseDAO<VistaReclamoCoordinacionEntity> {

    public VistaReclamoCoordDAO(Class<VistaReclamoCoordinacionEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<VistaReclamoCoordinacionTO> buscarReclamoCoordId(int idReclamo) {
        List<VistaReclamoCoordinacionTO> listaHistorialReclamo = new ArrayList();
        Query query = getEm().createNamedQuery("VistaReclamoCoordinacionEntity.findByIdReclamo");
        query.setParameter("idReclamo", idReclamo);
        List<VistaReclamoCoordinacionEntity> listaEnlaceEntity = query.getResultList(); 
        for(VistaReclamoCoordinacionEntity recCoord : listaEnlaceEntity){
            listaHistorialReclamo.add(traspasar(recCoord));
        }  
        if(!listaHistorialReclamo.isEmpty())
        {
            listaHistorialReclamo.get(0).setDesc_tipo("Ingresa Reclamo");
        }
        
        return listaHistorialReclamo;
    }

    public VistaReclamoCoordinacionTO traspasar(VistaReclamoCoordinacionEntity vistaReclamoCoordEntity) {
        VistaReclamoCoordinacionTO vistaReclamoCoordTO = new VistaReclamoCoordinacionTO();
        vistaReclamoCoordTO.setTipo(vistaReclamoCoordEntity.getTipo());
        if(vistaReclamoCoordTO.getTipo().equals("C"))
        {
            vistaReclamoCoordTO.setDesc_tipo("Cordina Apertura");
        }
        else if(vistaReclamoCoordTO.getTipo().equals("R"))
        {
            vistaReclamoCoordTO.setDesc_tipo("Cambia Estado");
        }
        else{
            vistaReclamoCoordTO.setDesc_tipo("Sin información");
        }
        vistaReclamoCoordTO.setIdReclamo(vistaReclamoCoordEntity.getIdReclamo());
        vistaReclamoCoordTO.setId(vistaReclamoCoordEntity.getId());
        vistaReclamoCoordTO.setIdEstado(vistaReclamoCoordEntity.getIdEstado());
        vistaReclamoCoordTO.setDescEstado(vistaReclamoCoordEntity.getDescEstado());
        vistaReclamoCoordTO.setObservacion(vistaReclamoCoordEntity.getObservacion());
        vistaReclamoCoordTO.setIdUsuario(vistaReclamoCoordEntity.getIdUsuario());
        vistaReclamoCoordTO.setNombre(vistaReclamoCoordEntity.getNombre());
        vistaReclamoCoordTO.setFecha(vistaReclamoCoordEntity.getFecha());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        vistaReclamoCoordTO.setFecha_str(sdf.format(vistaReclamoCoordEntity.getFecha()));
        vistaReclamoCoordTO.setApellido1(vistaReclamoCoordEntity.getApellido1());
        vistaReclamoCoordTO.setApellido2(vistaReclamoCoordEntity.getApellido2());
        vistaReclamoCoordTO.setNombreProveedor(vistaReclamoCoordEntity.getNombreProveedor());
        vistaReclamoCoordTO.setResultado(vistaReclamoCoordEntity.getResultado());
        vistaReclamoCoordTO.setIdTarea(vistaReclamoCoordEntity.getIdTarea());
        return vistaReclamoCoordTO;
    }
}
