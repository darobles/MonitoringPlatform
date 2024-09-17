/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.DispositivoEntity;
import cl.auter.patron.to.DispositivoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Marco
 */
public class DispositivoDAO extends BaseDAO<DispositivoEntity>{

    public DispositivoDAO(Class<DispositivoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void guardar(DispositivoTO dispositivoTO)throws Exception {
        persist(traspasar(dispositivoTO));
    }
    
    public void editar(DispositivoTO dispositivoTO)throws Exception {
        edit(traspasar(dispositivoTO));
    }
    
    public void eliminar(DispositivoTO dispositivoTO)throws Exception{
        remove(traspasar(dispositivoTO));
    }
    
    public DispositivoTO buscarDispositivoPorId(BigDecimal idDispositivo){
        Query query = getEm().createNamedQuery("DispositivoEntity.buscarDispositivoPorId");
        query.setParameter("idDispositivo", idDispositivo);
        DispositivoEntity dispositivoEntity = (DispositivoEntity)query.getSingleResult(); 
        return traspasar(dispositivoEntity);
    }
    
    public List<DispositivoTO> buscarDispositivoTodos()throws Exception {
        List<DispositivoTO> listaDispositivoTO = new ArrayList<DispositivoTO>();
        Query query = getEm().createNamedQuery("DispositivoEntity.buscarDispositivoTodos");
        List<DispositivoEntity> listaDispositivoEntity = query.getResultList();
        for(DispositivoEntity dispositivoEntity : listaDispositivoEntity){
            listaDispositivoTO.add(traspasar(dispositivoEntity));
        }
        return listaDispositivoTO;
    }
    
    public DispositivoEntity traspasar(DispositivoTO dispositivoTO){
        DispositivoEntity dispositivoEntity = new DispositivoEntity();
        if(dispositivoTO.getIdDispositivo() != null){
            dispositivoEntity.setIdDispositivo(dispositivoTO.getIdDispositivo());
        }
        dispositivoEntity.setImei(dispositivoTO.getImei());
        dispositivoEntity.setDirip(dispositivoTO.getDirip());
        dispositivoEntity.setDyndns(dispositivoTO.getDyndns());
        dispositivoEntity.setUbicacion(dispositivoTO.getUbicacion());
        dispositivoEntity.setNumSerie(dispositivoTO.getNumSerie());
        dispositivoEntity.setComentario(dispositivoTO.getComentario());
        dispositivoEntity.setActivo(dispositivoTO.getActivo());
        dispositivoEntity.setFechaAct(new Date());
        dispositivoEntity.setEstadoOperativo(dispositivoTO.getEstadoOperativo());
        dispositivoEntity.setValModo220(dispositivoTO.getValModo220());
        dispositivoEntity.setValModoUps(dispositivoTO.getValModoUps());
        dispositivoEntity.setValModoUtc1(dispositivoTO.getValModoUtc1());
        dispositivoEntity.setValModoLam(dispositivoTO.getValModoLam());
        dispositivoEntity.setIndUps(dispositivoTO.getIndUps());
        dispositivoEntity.setValModoUtc2(dispositivoTO.getValModoUtc2());
        dispositivoEntity.setValModoUtc3(dispositivoTO.getValModoUtc3());
        dispositivoEntity.setValModoUtc4(dispositivoTO.getValModoUtc4());
        dispositivoEntity.setNumTel(dispositivoTO.getNumTel());
        dispositivoEntity.setIndOtu(dispositivoTO.getIndOtu());
        if(dispositivoTO.isResetCtr())
        {
            dispositivoEntity.setIndResetCtr(new BigDecimal("1"));
        }
        else{
            dispositivoEntity.setIndResetCtr(new BigDecimal("0"));
        }
        if(dispositivoTO.isIndLam())
        {
            dispositivoEntity.setIndLamFault(new BigDecimal("1"));
        }
        else{
            dispositivoEntity.setIndLamFault(new BigDecimal("0"));
        }
        dispositivoEntity.setTipoMonitoreo(dispositivoTO.getTipoMonitoreo());
        return dispositivoEntity;
    }  
    
    public DispositivoTO traspasar(DispositivoEntity dispositivoEntity){
        DispositivoTO dispositivoTO = new DispositivoTO();
        dispositivoTO.setIdDispositivo(dispositivoEntity.getIdDispositivo());
        dispositivoTO.setImei(dispositivoEntity.getImei());
        dispositivoTO.setDirip(dispositivoEntity.getDirip());
        dispositivoTO.setDyndns(dispositivoEntity.getDyndns());
        dispositivoTO.setUbicacion(dispositivoEntity.getUbicacion());
        dispositivoTO.setNumSerie(dispositivoEntity.getNumSerie());
        dispositivoTO.setComentario(dispositivoEntity.getComentario());
        dispositivoTO.setActivo(dispositivoEntity.getActivo());
        dispositivoTO.setFechaAct(dispositivoEntity.getFechaAct());
        dispositivoTO.setEstadoOperativo(dispositivoEntity.getEstadoOperativo());
        dispositivoTO.setValModo220(dispositivoEntity.getValModo220());
        dispositivoTO.setValModoUps(dispositivoEntity.getValModoUps());
        dispositivoTO.setValModoUtc1(dispositivoEntity.getValModoUtc1());
        dispositivoTO.setValModoLam(dispositivoEntity.getValModoLam());
        dispositivoTO.setIndUps(dispositivoEntity.getIndUps());
        dispositivoTO.setValModoUtc2(dispositivoEntity.getValModoUtc2());
        dispositivoTO.setValModoUtc3(dispositivoEntity.getValModoUtc3());
        dispositivoTO.setValModoUtc4(dispositivoEntity.getValModoUtc4());
        dispositivoTO.setNumTel(dispositivoEntity.getNumTel());
        dispositivoTO.setIndOtu(dispositivoEntity.getIndOtu());
        dispositivoTO.setIndResetCtr(dispositivoEntity.getIndResetCtr());
        if(dispositivoTO.getIndResetCtr().equals(new BigDecimal("1")))
        {
            dispositivoTO.setResetCtr(true);
        }
        else{
            dispositivoTO.setResetCtr(false);
        }
        if(dispositivoEntity.getIndLamFault() != null && dispositivoEntity.getIndLamFault().equals(new BigDecimal("1")))
        {
            dispositivoTO.setIndLam(true);
        }
        else{
            dispositivoTO.setIndLam(false);
        }
        dispositivoTO.setTipoMonitoreo(dispositivoEntity.getTipoMonitoreo());
        return dispositivoTO;
    }   
}
