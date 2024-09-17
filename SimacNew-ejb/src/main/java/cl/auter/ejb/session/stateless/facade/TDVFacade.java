/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.NodoDAO;
import cl.auter.ejb.dao.VRutaTiempoDAO;
import cl.auter.ejb.dao.VectorDAO;
import cl.auter.ejb.entity.bmp.NodoEntity;
import cl.auter.ejb.entity.bmp.VRutaTiempoEntity;
import cl.auter.ejb.entity.bmp.VectorEntity;
import cl.auter.patron.to.NodoTO;
import cl.auter.patron.to.VRutaTiempoTO;
import cl.auter.patron.to.VectorTO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class TDVFacade implements TDVFacadeLocal{
   /* @PersistenceContext(unitName = "TDV")
    private EntityManager entityManager;

    @Override
    public List<NodoTO> listaNodoTO() throws Exception {
        NodoDAO nodoDAO = new NodoDAO(NodoEntity.class, entityManager);
        List<NodoTO> listaNodos = nodoDAO.buscarTodos();
        return listaNodos;
    } 
    
    @Override
    public List<NodoTO> listaNodoByIdComuna(BigDecimal idCcomuna) throws Exception {
        NodoDAO nodoDAO = new NodoDAO(NodoEntity.class, entityManager);
        List<NodoTO> listaNodos = nodoDAO.buscarPorIdComuna(idCcomuna);
        return listaNodos;
    } 

    @Override
    public NodoTO getNodobyIdFabrica(BigDecimal idsen_dest) {
         NodoDAO nodoDAO = new NodoDAO(NodoEntity.class, entityManager);
         return nodoDAO.getNodoByIdFabrica(idsen_dest);
    }
    
    
    
    @Override
    public VectorTO getVectorById(BigDecimal id) throws Exception {
        VectorDAO vectorDAO = new VectorDAO(VectorEntity.class, entityManager);
        VectorTO vector = vectorDAO.buscarVectorPorId(id);
        return vector;
    }
    
    @Override
    public List<VRutaTiempoTO> getVRutaTiempoByEmpresa(BigDecimal idEmpresa) throws Exception{
        VRutaTiempoDAO vRutaTiempoDAO = new VRutaTiempoDAO(VRutaTiempoEntity.class, entityManager);
        return vRutaTiempoDAO.getVRutaTiempobyEmpresa(idEmpresa);
    }

    @Override
    public List<VRutaTiempoTO> getVRutaTiempoTodos() throws Exception {
        VRutaTiempoDAO vRutaTiempoDAO = new VRutaTiempoDAO(VRutaTiempoEntity.class, entityManager);
        return vRutaTiempoDAO.getVRutaTiempoTodos();
    }
    
*/
    
}
