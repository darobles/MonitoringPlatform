package cl.auter.ejb.session.stateless.facade;

import cl.auter.ejb.dao.CodigoDAO;
import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.patron.to.CodigoTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParametrosFacade implements ParametrosFacadeLocal {
    @PersistenceContext(unitName = "Georeferencia")
    private EntityManager entityManager;

    @Override
    public List<CodigoTO> listaCodigos()  {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        return codigoDAO.buscarTodos();
    }

    @Override
    public List<CodigoTO> listaPorDominio(String dominio) {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
	return codigoDAO.buscarPorDominio(dominio);
    }
    @Override
    public CodigoTO buscarDominioCodigo(String dominio, String codigo) {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        return codigoDAO.buscarPorDominioCodigo(dominio, codigo);
    }
    @Override
    public CodigoTO buscarDominioCodigo(String dominio, String codigo1,String codigo2) {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        return codigoDAO.buscarPorDominioCodigo(dominio, codigo1,codigo2);
    }

    @Override
    public Date traerFechaHoraActual() {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, entityManager);
        return codigoDAO.traerFechaHoraActualDate();
    }
}
