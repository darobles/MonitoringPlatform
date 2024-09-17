package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.PoligonoComunalEntity;
import cl.auter.patron.to.PoligonoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Rodrigo Baeza O.
 */
public class PoligonoDAO extends BaseDAO<PoligonoComunalEntity> {

    public PoligonoDAO(Class<PoligonoComunalEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public void guardar(PoligonoTO cruceTO) throws Exception {
        PoligonoComunalEntity cruceEntity = traspasar(cruceTO);
        persist(cruceEntity);
    }

    public void editar(PoligonoTO cruceTO) throws Exception {
        PoligonoComunalEntity cruceEntity = traspasar(cruceTO);
        cruceEntity.setIdpoligono(cruceTO.getIdPoligono());
        edit(cruceEntity);
    }

    public void removerPoligono(PoligonoTO poligonoTO) {
        PoligonoComunalEntity comunaEntity = traspasar(poligonoTO);
        comunaEntity.setIdpoligono(poligonoTO.getIdPoligono());
        remove(comunaEntity);
    }
    
    public boolean removerPoligonoPorComuna(BigDecimal idcomuna) {
        StringBuffer strsql=new StringBuffer();
        strsql.append("delete from poligono_comunal where idcomuna = ?");
        Query query = getEm().createNativeQuery(strsql.toString());
        query.setParameter(1, idcomuna.intValue());
        Object result = null;
        try{
            result = query.executeUpdate();
        }catch(Exception e){
        
        }
        if(result == null){
            return false;
        }
        return true;
    } //
    

    public List<PoligonoTO> buscarPoligonoComuna(int idComuna) {
        List<PoligonoTO> listaPoligonoTO = new ArrayList();
        Query query = getEm().createNamedQuery("PoligonoComunalEntity.findByIdcomuna");
        query.setParameter("idcomuna", idComuna);
        List<PoligonoComunalEntity> listaPoligono = query.getResultList();
        for (PoligonoComunalEntity poligonoEntity : listaPoligono) {
            listaPoligonoTO.add(traspasar(poligonoEntity));
        }
        return listaPoligonoTO;
    }

    public PoligonoTO traspasar(PoligonoComunalEntity poligonoEntity) {
        PoligonoTO poligonoTO = new PoligonoTO();
        poligonoTO.setIdPoligono(poligonoEntity.getIdpoligono());
        poligonoTO.setIdComuna(poligonoEntity.getIdcomuna());
        poligonoTO.setLongitud(poligonoEntity.getLongitud());
        poligonoTO.setLatitud(poligonoEntity.getLatitud());
        poligonoTO.setOrden(poligonoEntity.getOrden());
        return poligonoTO;
    }

    public PoligonoComunalEntity traspasar(PoligonoTO poligonoTO) {
        PoligonoComunalEntity poligonoEntity = new PoligonoComunalEntity();
        // poligonoEntity.setIdPoligono(poligonoTO.getIdPoligono());
        poligonoEntity.setIdcomuna(poligonoTO.getIdComuna());
        poligonoEntity.setLongitud(poligonoTO.getLongitud());
        poligonoEntity.setLatitud(poligonoTO.getLatitud());
        poligonoEntity.setOrden(poligonoTO.getOrden());
        return poligonoEntity;
    }
}
