package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.MovilEntity;
import cl.auter.patron.to.MovilTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

public class MovilDAO  extends BaseDAO<MovilEntity>{

    public MovilDAO(Class<MovilEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<MovilTO> buscarTodos(){
        List<MovilTO> listaMovil = new ArrayList();
        for (MovilEntity movilEntity: findAll()){
                listaMovil.add(traspasar(movilEntity));
        }
        return listaMovil;
    }
  
	
    public MovilTO traspasar(MovilEntity movilEntity){
        MovilTO movilTO = new MovilTO();
        movilTO.setCodigoMovil(movilEntity.getCodigoMovil());
        movilTO.setFecultLec(movilEntity.getFecultLec());
        movilTO.setIdMovil(movilEntity.getIdMovil());
        movilTO.setLatitud(movilEntity.getLatitud().replace(",", "."));
        movilTO.setLongitud(movilEntity.getLongitud().replace(",", "."));
        movilTO.setTipo(movilEntity.getTipo());       
        return movilTO;
    }
        
    public MovilEntity traspasar(MovilTO movilTO){
        MovilEntity movilEntity = new MovilEntity();
            movilEntity.setCodigoMovil(movilTO.getCodigoMovil());
            movilEntity.setFecultLec(movilTO.getFecultLec());
            movilEntity.setIdMovil(movilTO.getIdMovil());
            movilEntity.setLatitud(movilTO.getLatitud());
            movilEntity.setLongitud(movilTO.getLongitud());
            movilEntity.setTipo(movilTO.getTipo());   
        return movilEntity;
    }
}
