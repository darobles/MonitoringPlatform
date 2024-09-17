/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaMovilHistoricoEntity;
import cl.auter.patron.to.MovilTO;
import cl.auter.patron.to.VistaMovilHistoricoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaMovilHistoricoDao extends BaseDAO<VistaMovilHistoricoEntity>{
    
    public VistaMovilHistoricoDao(Class<VistaMovilHistoricoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    public List<VistaMovilHistoricoTO> buscarPorFechas(MovilTO movil,Date fechaDesde, Date fechaHasta)throws Exception {
        List<VistaMovilHistoricoTO> listaMoviles = new ArrayList<VistaMovilHistoricoTO>();
        Query query = getEm().createNamedQuery("VistaMovilHistoricoEntity.MovilesPorFecha");
        query.setParameter("codigomovil", movil.getCodigoMovil());
        query.setParameter("fechaDesde",fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        List<VistaMovilHistoricoEntity> listaEntity=query.getResultList();
        for (VistaMovilHistoricoEntity instalacionEntity: listaEntity){
                listaMoviles.add(traspasar(instalacionEntity));
        }
	return listaMoviles;
    }
            
            
    public VistaMovilHistoricoTO traspasar(VistaMovilHistoricoEntity vistaMovilHistoricoEntity)
    {
        VistaMovilHistoricoTO vistaMovilHistoricoTO = new VistaMovilHistoricoTO();
        vistaMovilHistoricoTO.setId_Reg(vistaMovilHistoricoEntity.getIdReg());
        vistaMovilHistoricoTO.setCodigoMovil(vistaMovilHistoricoEntity.getCodigomovil());
        vistaMovilHistoricoTO.setLatitud(vistaMovilHistoricoEntity.getLatitud());
        vistaMovilHistoricoTO.setLongitud(vistaMovilHistoricoEntity.getLongitud());
        vistaMovilHistoricoTO.setFechorloc(vistaMovilHistoricoEntity.getFechorloc());
        vistaMovilHistoricoTO.setFecultlec(vistaMovilHistoricoEntity.getFecultlec());
        vistaMovilHistoricoTO.setUltCruce(vistaMovilHistoricoEntity.getUltcruce());
        return vistaMovilHistoricoTO;
    }

    public List<VistaMovilHistoricoTO> listaMovilCruce(BigDecimal idCruce, Date fechaDesde, Date fechaHasta) {
        List<VistaMovilHistoricoTO> listaMoviles = new ArrayList<VistaMovilHistoricoTO>();
        Query query = getEm().createNamedQuery("VistaMovilHistoricoEntity.listaMovilCruce");
        query.setParameter("idCruce", idCruce.toBigInteger());
        query.setParameter("fechaDesde",fechaDesde);
        query.setParameter("fechaHasta", fechaHasta);
        List<VistaMovilHistoricoEntity> listaEntity=query.getResultList();
        for (VistaMovilHistoricoEntity instalacionEntity: listaEntity){
                listaMoviles.add(traspasar(instalacionEntity));
        }
	return listaMoviles;
    }
    
}
