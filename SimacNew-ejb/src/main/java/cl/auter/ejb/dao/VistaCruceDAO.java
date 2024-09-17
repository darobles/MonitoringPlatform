
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaCruceEntity;
import cl.auter.patron.to.VistaContEstadoTO;
import cl.auter.patron.to.VistaCruceTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class VistaCruceDAO extends BaseDAO<VistaCruceEntity>{

    public VistaCruceDAO(Class<VistaCruceEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    
    public List<VistaCruceTO> buscarTodos()throws Exception {
        List<VistaCruceTO> listaCodigo = new ArrayList<>();
        for (VistaCruceEntity codigoEntity: findAll()){
                listaCodigo.add(traspasar(codigoEntity));
        }
        return listaCodigo;
    }
    public List<VistaCruceTO> buscarCrucesPorComunaYIdDispositivo(BigDecimal IdComuna, List listaEstadoOperacional)throws Exception {
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesPorComunaYIdDispositivo");
        query.setParameter("idComuna",IdComuna);
        query.setParameter("lista",listaEstadoOperacional);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
            listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    public List<VistaCruceTO> buscarCrucesPorListEstado(List listaEstadoOperacional)throws Exception {
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesPorEstadoLista");
        query.setParameter("lista",listaEstadoOperacional);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
            listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    public VistaCruceTO buscarCrucesPorId(BigDecimal idCruce)throws Exception {
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesPorId");
        query.setParameter("idCruce",idCruce);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
            listaCruces.add(traspasar(cruceEntity));
        }
        if(!listaCruces.isEmpty())
            return listaCruces.get(0);
	return new VistaCruceTO();
    }
    
    public List<VistaCruceTO> buscarNoEncendidos(){
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesNoEncendidos");
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
            listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    public List<VistaCruceTO> buscarNoEncendidosPorComuna(BigDecimal idComuna){
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesNoEncendidosPorComuna");
        query.setParameter("idComuna",idComuna);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
            listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    public List<VistaCruceTO> buscarCrucesPorComunaYIdDispositivo(BigDecimal IdComuna)throws Exception {
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesPorComuna");
        query.setParameter("idComuna",IdComuna);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
                listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    
    public List<VistaCruceTO> buscarPorEstado(String estado) {
        List<VistaCruceTO> listaCruces = new ArrayList<>();
        Query query = getEm().createNamedQuery("CrucesPorEstado");
        query.setParameter("estado",estado);
        List<VistaCruceEntity> listaEntity=query.getResultList();
        for (VistaCruceEntity cruceEntity: listaEntity){
                listaCruces.add(traspasar(cruceEntity));
        }
	return listaCruces;
    }
    
    public int numEstadoComuna(BigDecimal idComuna, String estado) {
        Query query = getEm().createNamedQuery("CrucesCantPorComuna");
        query.setParameter("estado", estado);
        query.setParameter("idComuna",idComuna);
        List<VistaCruceEntity> listaContEntity = query.getResultList();        
        return listaContEntity.size();
    }
    
    public VistaCruceTO traspasar(VistaCruceEntity vistaEntity){
        VistaCruceTO vistaTO = new VistaCruceTO();
        vistaTO.setIdDispositivo(vistaEntity.getIdDispositivo());
        vistaTO.setIdCruce(vistaEntity.getIdCruce());
        vistaTO.setUbicacion(vistaEntity.getUbicacion());
        vistaTO.setControlador(vistaEntity.getControlador());
        vistaTO.setActivo(vistaEntity.getActivo());
        vistaTO.setEstadoOperativo(vistaEntity.getEstadoOperativo());
        vistaTO.setFechaAct(vistaEntity.getFechaAct());
        vistaTO.setValModo220(vistaEntity.getValModo220());
        vistaTO.setValModoLam(vistaEntity.getValModoLam());
        vistaTO.setValModoUps(vistaEntity.getValModoUps());
        vistaTO.setValModoUtc(vistaEntity.getValModoUtc());
        vistaTO.setValModoUtc4(vistaEntity.getValModoUtc4());
        vistaTO.setRed(vistaEntity.getRed());
        vistaTO.setLongitud(vistaEntity.getLongitud());
        vistaTO.setLatitud(vistaEntity.getLatitud());
        vistaTO.setCodigoSistema(vistaEntity.getCodigoSistema());
        vistaTO.setIdComuna(vistaEntity.getIdComuna());
        vistaTO.setObservacion(vistaEntity.getObservacion());
        vistaTO.setNumeroEquipo(vistaEntity.getNumeroEquipo());
        vistaTO.setDireccionIP(vistaEntity.getDireccionIP());
        vistaTO.setTelefono(vistaEntity.getTelefono());
        vistaTO.setTipoMonitoreo(vistaEntity.getTipoMonitoreo().intValue());
        if(vistaTO.getTipoMonitoreo() == 1)
        {
            vistaTO.setTipo_monitoreo_desc("Semáforo");
        }
        else{
            vistaTO.setTipo_monitoreo_desc("N/A");
        }
        vistaTO.setDescripcionComuna(vistaEntity.getNombre());
        if(vistaEntity.getPpr().equals(new BigDecimal("1")))
        {
            vistaTO.setPpr(true);
        }
        else{
            vistaTO.setPpr(false);
        }
        if(vistaEntity.getIndLamFault().equals(new BigDecimal("1")))
        {
            vistaTO.setIndLamFault(true);
        }
        else{
            vistaTO.setIndLamFault(false);
        }        
        return vistaTO;
    }

}
