/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.HistorialReclamoEntity;
import cl.auter.patron.to.HistorialReclamoTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class HistorialReclamoDAO extends BaseDAO<HistorialReclamoEntity> {

    public HistorialReclamoDAO(Class<HistorialReclamoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void guardar(HistorialReclamoTO historialReclamoTO) {
        HistorialReclamoEntity historialReclamoEntity = traspasar(historialReclamoTO);
        persist(historialReclamoEntity);
    }

    public void editar(HistorialReclamoTO historialReclamoTO) {
        HistorialReclamoEntity historialReclamoEntity = traspasar(historialReclamoTO);
        edit(historialReclamoEntity);
    }

    public void eliminar(HistorialReclamoTO historialReclamoTO) {
        HistorialReclamoEntity historialReclamoEntity = traspasar(historialReclamoTO);
        remove(historialReclamoEntity);
    }
     public List<HistorialReclamoTO> buscarHistorialReclamo(int idReclamo) {
        List<HistorialReclamoTO> listaHistorialReclamo = new ArrayList<>();
        Query query = getEm().createNamedQuery("HistorialReclamoEntity.findByIdReclamo");
        query.setParameter("idReclamo", idReclamo);
        List<HistorialReclamoEntity> listaEnlaceEntity = query.getResultList(); 
        for(HistorialReclamoEntity cruceEntity : listaEnlaceEntity){
            listaHistorialReclamo.add(traspasar(cruceEntity));
        }     
        return listaHistorialReclamo;
    }
    public HistorialReclamoEntity traspasar(HistorialReclamoTO historialReclamoTO) {
        HistorialReclamoEntity historialReclamoEntity = new HistorialReclamoEntity();
        historialReclamoEntity.setIdHistorial(historialReclamoTO.getIdHistorial());
        historialReclamoEntity.setIdReclamo(historialReclamoTO.getIdReclamo());
        historialReclamoEntity.setFechaModificacion(historialReclamoTO.getFechaModificacion());
        historialReclamoEntity.setIdEstado(historialReclamoTO.getIdEstado());
        historialReclamoEntity.setObservacion(historialReclamoTO.getObservacion());
        historialReclamoEntity.setIdUsuario(historialReclamoTO.getIdUsuario());
        historialReclamoEntity.setIdTarea(historialReclamoTO.getIdTarea());
        return historialReclamoEntity;
    }
    
    public HistorialReclamoTO traspasar(HistorialReclamoEntity historialReclamoEntity) {
        HistorialReclamoTO historialReclamoTO = new HistorialReclamoTO();
        historialReclamoTO.setIdHistorial(historialReclamoEntity.getIdHistorial());
        historialReclamoTO.setIdReclamo(historialReclamoEntity.getIdReclamo());
        historialReclamoTO.setFechaModificacion(historialReclamoEntity.getFechaModificacion());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        historialReclamoTO.setFecModStr(sdf.format(historialReclamoTO.getFechaModificacion()));
        historialReclamoTO.setIdEstado(historialReclamoEntity.getIdEstado());
        historialReclamoTO.setObservacion(historialReclamoEntity.getObservacion());
        historialReclamoTO.setIdUsuario(historialReclamoEntity.getIdUsuario());
        historialReclamoTO.setIdTarea(historialReclamoEntity.getIdTarea());
        return historialReclamoTO;
    }
}
