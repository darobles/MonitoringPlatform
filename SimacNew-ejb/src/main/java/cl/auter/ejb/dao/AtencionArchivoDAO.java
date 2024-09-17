/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.AtencionArchivoEntity;
import cl.auter.patron.to.AtencionArchivoTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class AtencionArchivoDAO extends BaseDAO<AtencionArchivoEntity>{

    public AtencionArchivoDAO(Class<AtencionArchivoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<AtencionArchivoTO> buscarArchivos(int atencion){
        Query query = getEm().createNamedQuery("AtencionArchivoEntity.findByIdAtencion");
        query.setParameter("idAtencion", atencion);
        List<AtencionArchivoEntity> listaCodigoEntity = query.getResultList();
        List<AtencionArchivoTO> listaCodigo = new ArrayList();
        for (AtencionArchivoEntity codigoEntity: listaCodigoEntity ){
            listaCodigo.add(traspasar(codigoEntity));
        }
	return listaCodigo;
    }

    private AtencionArchivoTO traspasar(AtencionArchivoEntity archivoEntity) {
        AtencionArchivoTO archivoTO = new AtencionArchivoTO();
        archivoTO.setId_atencion(archivoEntity.getIdAtencion());
        archivoTO.setId_archivo(archivoEntity.getIdArchivo());
        archivoTO.setArchivo(archivoEntity.getArchivo());
        archivoTO.setFecha(archivoEntity.getFechaHora());
        archivoTO.setTipo(archivoEntity.getTipo());
        if(archivoEntity.getTipo() == 1)
        {
            archivoTO.setTipo_nombre("Checklist");
        }
        else{
           archivoTO.setTipo_nombre("Imagen"); 
        }
        archivoTO.setArchivo_origen(archivoEntity.getArchivoOrig());
        return archivoTO;
        
    }
}
