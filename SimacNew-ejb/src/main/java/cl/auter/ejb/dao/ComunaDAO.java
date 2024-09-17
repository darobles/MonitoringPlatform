package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.ComunaEntity;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.MovilTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ComunaDAO extends BaseDAO<ComunaEntity> {

    public ComunaDAO(Class<ComunaEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public void editarComuna(ComunaTO comuna)
    {
        ComunaEntity comunaEntity = traspasar(comuna);
        comunaEntity.setIdComuna(comuna.getIdComuna());
        edit(comunaEntity);
    }
    
    public void guardarComuna(ComunaTO comuna)
    {
        ComunaEntity comunaEntity = traspasar(comuna);
       // comunaEntity.setIdComuna(comuna.getIdComuna());
        System.out.println("Comuna Entyity " + comunaEntity.toString());
        persist(comunaEntity);
    }
    
    public void borrarComuna(ComunaTO comuna)
    {
        ComunaEntity comunaEntity = traspasar(comuna);
        comunaEntity.setIdComuna(comuna.getIdComuna());
        remove(comunaEntity);
    }

    public List<ComunaTO> buscarTodos() throws Exception {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaPorComuna");
        List<ComunaEntity> listaComunaEntity = query.getResultList();
        List<ComunaTO> listaComuna = new ArrayList<ComunaTO>();
        for (ComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;
    }

    public List<ComunaTO> buscarComunaCoordenada() {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaComunaCoordenada");
        List<ComunaEntity> listaComunaEntity = query.getResultList();
        List<ComunaTO> listaComuna = new ArrayList<ComunaTO>();
        for (ComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;

    }

    public List<ComunaTO> buscarComunaMonitoreo() {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaComunaMonitoreo");
        List<ComunaEntity> listaComunaEntity = query.getResultList();
        List<ComunaTO> listaComuna = new ArrayList<ComunaTO>();
        for (ComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;
    }

    public List<ComunaTO> buscarComunaMonitoreoUOCT() {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaComunaMonitoreoUOCT");
        List<ComunaEntity> listaComunaEntity = query.getResultList();
        List<ComunaTO> listaComuna = new ArrayList<ComunaTO>();
        for (ComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;
    }

    public ComunaTO buscaPorId(BigDecimal idComuna) throws Exception {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaPorId");
        query.setParameter("idComuna", idComuna);
        ComunaEntity comunaEntity = (ComunaEntity) query.getSingleResult();
        return traspasar(comunaEntity);
    }

    public List<MovilTO> listaMovilComuna(BigDecimal idComuna) {
        List<MovilTO> listaMovil = new ArrayList<MovilTO>();
        Query query = getEm().createNamedQuery("ComunaEntity.buscaPorId");
        query.setParameter("idComuna", idComuna);
        List<ComunaEntity> listaEntity = query.getResultList();
        for (ComunaEntity comunaEntity : listaEntity) {
            String[] moviles = comunaEntity.getMoviles().split(";");
            for (String movil : moviles) {
                MovilTO movilTO = new MovilTO();
                movilTO.setCodigoMovil(movil);
                listaMovil.add(movilTO);
            }
        }
        return listaMovil;
    }
    
    public List<ComunaTO> buscarComunasPorRegion(int id_region) {
        Query query = getEm().createNamedQuery("ComunaEntity.buscaComunaPorRegion");
        query.setParameter("idRegion", new BigDecimal(id_region));
        List<ComunaEntity> listaComunaEntity = query.getResultList();
        List<ComunaTO> listaComuna = new ArrayList<ComunaTO>();
        for (ComunaEntity comunaEntity : listaComunaEntity) {
            listaComuna.add(traspasar(comunaEntity));
        }
        return listaComuna;
    }

    public ComunaTO traspasar(ComunaEntity comunaEntity) {
        ComunaTO comunaTO = new ComunaTO();
        comunaTO.setIdComuna(comunaEntity.getIdComuna());
        comunaTO.setIdRegion(comunaEntity.getIdregion());
        comunaTO.setNombre(comunaEntity.getNombre());
        comunaTO.setUrlLogo(comunaEntity.getUrlLogo());
        comunaTO.setLatitud(comunaEntity.getLatitud());
        comunaTO.setLongitud(comunaEntity.getLongitud());
        comunaTO.setEmail(comunaEntity.getEmail());
        comunaTO.setTelefono(comunaEntity.getTelefono());
        return comunaTO;
    }

    public ComunaEntity traspasar(ComunaTO comunaTO) {
        ComunaEntity comunaEntity = new ComunaEntity();
        comunaEntity.setIdComuna(comunaTO.getIdComuna());
        comunaEntity.setIdregion(comunaTO.getIdRegion());
        comunaEntity.setNombre(comunaTO.getNombre());
        comunaEntity.setUrlLogo(comunaTO.getUrlLogo());
        comunaEntity.setLatitud(comunaTO.getLatitud());
        comunaEntity.setLongitud(comunaTO.getLongitud());
        comunaEntity.setEmail(comunaTO.getEmail());
        comunaEntity.setTelefono(comunaTO.getTelefono());
        comunaEntity.setZoom(new BigDecimal(comunaTO.getZoom()));
        comunaEntity.setInd_mon(comunaTO.getInd_mon());
        comunaEntity.setAutUoct(comunaTO.getAut_uoct());
        return comunaEntity;
    }



}
