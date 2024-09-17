/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.AtencionArchivoDAO;
import cl.auter.ejb.dao.VistaTareaWebDAO;
import cl.auter.ejb.entity.bmp.AtencionArchivoEntity;
import cl.auter.ejb.entity.bmp.VistaTareaWebEntity;
import cl.auter.patron.to.AtencionArchivoTO;
import cl.auter.patron.to.VistaTareaWebTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class SMAFacade implements SMAFacadeLocal {
   /* @PersistenceContext(unitName = "SMA2016")
    private EntityManager entityManager;
    
    @Override
    public VistaTareaWebTO obtenerTareaWeb(int id) {
        VistaTareaWebDAO dao = new VistaTareaWebDAO(VistaTareaWebEntity.class, entityManager);
        return dao.obtenerTareaWeb(id);
    }
    
    @Override
    public List<AtencionArchivoTO> obtenerArchivos(int id) {
        AtencionArchivoDAO dao = new AtencionArchivoDAO(AtencionArchivoEntity.class, entityManager);
        return dao.buscarArchivos(id);
    }

    */
}
