/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VRutaTiempoEntity;
import cl.auter.patron.to.VRutaTiempoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VRutaTiempoDAO extends BaseDAO<VRutaTiempoEntity> {
     public VRutaTiempoDAO(Class<VRutaTiempoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
     
     
     public List<VRutaTiempoTO> getVRutaTiempoTodos(){
        List<VRutaTiempoTO> listaVRutaTiempoTO = new ArrayList<VRutaTiempoTO>();
        Query query = getEm().createNamedQuery("VRutaTiempoEntity.findAll");
        List<VRutaTiempoEntity> listaVRutaTiempoEntity = query.getResultList();
        for(VRutaTiempoEntity vRutaTiempoEntity : listaVRutaTiempoEntity)
        {
            listaVRutaTiempoTO.add(traspasar(vRutaTiempoEntity));
        }
        return listaVRutaTiempoTO;
     }
     
     public List<VRutaTiempoTO> getVRutaTiempobyEmpresa(BigDecimal idEmpresa){
        Query query = getEm().createNamedQuery("VRutaTiempoEntity.findByEmpresa");
        query.setParameter("empresa", idEmpresa);
        List<VRutaTiempoEntity> listaVRutaTiempoEntity = query.getResultList();
        List<VRutaTiempoTO> listaVRutaTiempoTO = new ArrayList<VRutaTiempoTO>();
        for(VRutaTiempoEntity vRutaTiempoEntity : listaVRutaTiempoEntity)
        {
            listaVRutaTiempoTO.add(traspasar(vRutaTiempoEntity));
        }
        return listaVRutaTiempoTO;
    }

    public VRutaTiempoTO traspasar(VRutaTiempoEntity vRutaTiempoEntity) {
        VRutaTiempoTO vRutaTiempoTO = new VRutaTiempoTO();
        vRutaTiempoTO.setIdRut(vRutaTiempoEntity.getIdRut());
        vRutaTiempoTO.setNombre(vRutaTiempoEntity.getNombre());
        vRutaTiempoTO.setLetrero(vRutaTiempoEntity.getLetrero());
        vRutaTiempoTO.setUltimacaptura(vRutaTiempoEntity.getUltimacaptura());
        vRutaTiempoTO.setUltimacapturastr(vRutaTiempoEntity.getUltimacapturastr());
        vRutaTiempoTO.setTpominimo(vRutaTiempoEntity.getTpominimo());
        vRutaTiempoTO.setTpomaximo(vRutaTiempoEntity.getTpomaximo());
        vRutaTiempoTO.setDistancia(vRutaTiempoEntity.getDistancia());
        vRutaTiempoTO.setSegcapturados(vRutaTiempoEntity.getSegcapturados());
        vRutaTiempoTO.setTpopropuesto(vRutaTiempoEntity.getTpopropuesto());
        vRutaTiempoTO.setVelX(vRutaTiempoEntity.getVelX());
        vRutaTiempoTO.setMinProp(vRutaTiempoEntity.getMinProp());
        vRutaTiempoTO.setColor(vRutaTiempoEntity.getColor());
        vRutaTiempoTO.setPtoalarma(vRutaTiempoEntity.getPtoalarma());
        vRutaTiempoTO.setPtocongestion(vRutaTiempoEntity.getPtocongestion());
        vRutaTiempoTO.setPuntosgps(vRutaTiempoEntity.getPuntosgps());        
        vRutaTiempoTO.setEmpresa(vRutaTiempoEntity.getEmpresa());
        return vRutaTiempoTO;
    }

    public VRutaTiempoEntity traspasar(VRutaTiempoTO vRutaTiempoTO) {
        VRutaTiempoEntity vRutaTiempoEntity = new VRutaTiempoEntity();
        vRutaTiempoEntity.setIdRut(vRutaTiempoTO.getIdRut());
        vRutaTiempoEntity.setNombre(vRutaTiempoTO.getNombre());
        vRutaTiempoEntity.setLetrero(vRutaTiempoTO.getLetrero());
        vRutaTiempoEntity.setUltimacaptura(vRutaTiempoTO.getUltimacaptura());
        vRutaTiempoEntity.setUltimacapturastr(vRutaTiempoTO.getUltimacapturastr());
        vRutaTiempoEntity.setTpominimo(vRutaTiempoTO.getTpominimo());
        vRutaTiempoEntity.setTpomaximo(vRutaTiempoTO.getTpomaximo());
        vRutaTiempoEntity.setDistancia(vRutaTiempoTO.getDistancia());
        vRutaTiempoEntity.setSegcapturados(vRutaTiempoTO.getSegcapturados());
        vRutaTiempoEntity.setTpopropuesto(vRutaTiempoTO.getTpopropuesto());
        vRutaTiempoEntity.setVelX(vRutaTiempoTO.getVelX());
        vRutaTiempoEntity.setMinProp(vRutaTiempoTO.getMinProp());
        vRutaTiempoEntity.setColor(vRutaTiempoTO.getColor());
        vRutaTiempoEntity.setPtoalarma(vRutaTiempoTO.getPtoalarma());
        vRutaTiempoEntity.setPtocongestion(vRutaTiempoTO.getPtocongestion());
        vRutaTiempoEntity.setPuntosgps(vRutaTiempoTO.getPuntosgps());        
        vRutaTiempoEntity.setEmpresa(vRutaTiempoTO.getEmpresa());
        return vRutaTiempoEntity;
    }
    
}
