
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.patron.to.CodigoTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CodigoDAO extends BaseDAO<CodigoEntity>{

    public CodigoDAO(Class<CodigoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    public List<CodigoTO> buscarTodos(){
        List<CodigoTO> listaCodigo = new ArrayList<CodigoTO>();
        for (CodigoEntity codigoEntity: findAll()){
                listaCodigo.add(traspasar(codigoEntity));
        }
        return listaCodigo;
    }
    
    public List<CodigoTO> buscarPorDominio(String dominio){
        Query query = getEm().createNamedQuery("CodigoEntity.buscaPorDominio");
        query.setParameter("dominio", dominio);
        List<CodigoEntity> listaCodigoEntity = query.getResultList();
        List<CodigoTO> listaCodigo = new ArrayList();
        for (CodigoEntity codigoEntity: listaCodigoEntity ){
            listaCodigo.add(traspasar(codigoEntity));
        }
	return listaCodigo;
    }
    /**
     * Metodo que busca dentro de la base de datos sobre la tabla codigo en funcion de un Dominio y codigo dado
     * @param dominio
     * @param codigo1
     * @return 
     */
    public CodigoTO buscarPorDominioCodigo(String dominio,String codigo1){
        Query query = getEm().createNamedQuery("CodigoEntity.buscaPorDominioCodigo");
        query.setParameter("dominio", dominio);
        query.setParameter("codigo1", codigo1);
        CodigoEntity codigoEntity = (CodigoEntity)query.getSingleResult();
        traspasar(codigoEntity);
        return traspasar(codigoEntity);
        
    }
    public CodigoTO buscarPorDominioCodigo(String dominio,String codigo1,String codigo2){
        Query query = getEm().createNamedQuery("CodigoEntity.buscaPorDominioCodigos");
        query.setParameter("dominio", dominio);
        query.setParameter("codigo1", codigo1);
         query.setParameter("codigo2", codigo2);
        CodigoEntity codigoEntity = (CodigoEntity)query.getSingleResult();
        traspasar(codigoEntity);
        return traspasar(codigoEntity);
        
    }
    
    public CodigoTO traspasar(CodigoEntity comunaEntity){
        CodigoTO codigoTO = new CodigoTO();
        codigoTO.setIdCodigo(comunaEntity.getIdCodigo());
        codigoTO.setDominio(comunaEntity.getDominio());
        codigoTO.setCodigo1(comunaEntity.getCodigo1());
        codigoTO.setCodigo2(comunaEntity.getCodigo2());
        codigoTO.setDescripcion(comunaEntity.getDescripcion());
        return codigoTO;
    }
        
      public CodigoEntity traspasar(CodigoTO codigoTO){
        CodigoEntity codigoEntity = new CodigoEntity();
        codigoEntity.setIdCodigo(codigoTO.getIdCodigo());
        codigoEntity.setDominio(codigoTO.getDominio());
        codigoEntity.setCodigo1(codigoTO.getCodigo1());
        codigoEntity.setCodigo2(codigoTO.getCodigo2());
        codigoEntity.setDescripcion(codigoTO.getDescripcion());
        return codigoEntity;
    }
      
    public String traerFechaHoraActual(){
        String fechaActual = "";
        StringBuffer strsql=new StringBuffer();
        strsql.append("SELECT to_char(CURRENT_TIMESTAMP,'dd-MM-yyyy HH24:mi:ss')");
        Query query = getEm().createNativeQuery(strsql.toString());
        fechaActual = (String)query.getSingleResult();		
        return fechaActual;
    }
    public Date traerFechaHoraActualDate(){
        Date fecha = null;
        StringBuffer strsql=new StringBuffer();
        strsql.append("SELECT to_char(CURRENT_TIMESTAMP,'dd-MM-yyyy HH24:mi:ss')");
        Query query = getEm().createNativeQuery(strsql.toString());
        String fechaActual = (String)query.getSingleResult();		
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            fecha = formatoDelTexto.parse(fechaActual);
        } catch (ParseException ex) {
            Logger.getLogger(CodigoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;
    }
}
