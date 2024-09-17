/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;


import cl.auter.patron.to.NodoTO;
import cl.auter.patron.to.VRutaTiempoTO;
import cl.auter.patron.to.VectorTO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author drobles
 */
@Local
public interface TDVFacadeLocal {
   /* 
    public List<NodoTO> listaNodoTO()throws Exception;
    
    public VectorTO getVectorById(BigDecimal id) throws Exception;
    
    public List<NodoTO> listaNodoByIdComuna(BigDecimal idCcomuna) throws Exception;
    
    public List<VRutaTiempoTO> getVRutaTiempoByEmpresa(BigDecimal idEmpresa) throws Exception;
    
    public List<VRutaTiempoTO> getVRutaTiempoTodos() throws Exception;

    public NodoTO getNodobyIdFabrica(BigDecimal idsen_dest);
    */
}
