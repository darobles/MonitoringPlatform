/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.CodigoEnlaceDAO;
import cl.auter.ejb.dao.EnlaceDAO;
import cl.auter.ejb.dao.HistorialReclamoDAO;
import cl.auter.ejb.dao.ReclamoDAO;
import cl.auter.ejb.dao.UsuarioEnlaceDAO;
import cl.auter.ejb.dao.VistaReclamoCoordDAO;
import cl.auter.ejb.entity.bmp.CodigoEnlaceEntity;
import cl.auter.ejb.entity.bmp.EnlaceEntity;
import cl.auter.ejb.entity.bmp.HistorialReclamoEntity;
import cl.auter.ejb.entity.bmp.ReclamoEntity;
import cl.auter.ejb.entity.bmp.UsuarioEnlaceEntity;
import cl.auter.ejb.entity.bmp.VistaReclamoCoordinacionEntity;
import cl.auter.patron.to.CodigoEnlaceTO;
import cl.auter.patron.to.EnlaceTO;
import cl.auter.patron.to.HistorialReclamoTO;
import cl.auter.patron.to.ReclamoTO;
import cl.auter.patron.to.UsuarioEnlaceTO;
import cl.auter.patron.to.VistaReclamoCoordinacionTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author drobles
 */
@Stateless
public class EnlaceFacade implements EnlaceFacadeLocal {
   /* @PersistenceContext(unitName = "Enlace")
    private EntityManager entityManager;
    
    @Override
    public List<ReclamoTO> obtenerReclamosTodos() {
        ReclamoDAO dao = new ReclamoDAO(ReclamoEntity.class, entityManager);
        return dao.buscarReclamosTodos();
    }
    
    @Override
    public ReclamoTO obtenerReclamoPorId(int id){
        ReclamoDAO dao = new ReclamoDAO(ReclamoEntity.class, entityManager);
        return dao.buscarReclamoPrId(id);
    }
    
    @Override
    public EnlaceTO buscarEnlacePorId(int idEnlace)
    {
        EnlaceDAO dao = new EnlaceDAO(EnlaceEntity.class, entityManager);
        return dao.buscarEnlacePorId(idEnlace);
    }
    
    @Override
    public CodigoEnlaceTO buscarDominioCodigo(String dominio, String codigo){
        CodigoEnlaceDAO codigoDAO = new CodigoEnlaceDAO(CodigoEnlaceEntity.class, entityManager);
        return codigoDAO.buscarPorDominioCodigo(dominio, codigo);
    }
    
    @Override
    public List<HistorialReclamoTO> buscarReclamosHistoricos(Integer idReclamo){
        HistorialReclamoDAO dao = new HistorialReclamoDAO(HistorialReclamoEntity.class, entityManager);
        return dao.buscarHistorialReclamo(idReclamo);
    }
    
    @Override
    public UsuarioEnlaceTO buscarUsuarioPorId(int id)
    {
        UsuarioEnlaceDAO dao = new UsuarioEnlaceDAO(UsuarioEnlaceEntity.class, entityManager);
        return dao.buscarUsuarioPorId(id);
    }
    
    @Override
    public List<VistaReclamoCoordinacionTO> buscarCoordReclamoPorIdRec(Integer idReclamo){
        VistaReclamoCoordDAO dao = new VistaReclamoCoordDAO(VistaReclamoCoordinacionEntity.class,entityManager);
        return dao.buscarReclamoCoordId(idReclamo);
    }
*/
}
