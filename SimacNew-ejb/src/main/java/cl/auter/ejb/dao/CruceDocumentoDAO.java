/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.CruceDocumentoEntity;
import cl.auter.patron.to.CruceDocumentoTO;
import cl.auter.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marco
 */
public class CruceDocumentoDAO extends BaseDAO<CruceDocumentoEntity>{

    public CruceDocumentoDAO(Class<CruceDocumentoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
    public List<CruceDocumentoTO> buscarDocumentoPorIdCruce(BigDecimal idCruce)throws Exception  {
        List<CruceDocumentoTO> listaDocumentos = new ArrayList();
        Query query = getEm().createNamedQuery("CruceDocumentoEntity.buscarDocumentoPorIdCruce");
        query.setParameter("idCruce", idCruce);
        List<CruceDocumentoEntity> listaDocumentoEntity = query.getResultList();  
        for(CruceDocumentoEntity cruceDocumentoEntity : listaDocumentoEntity){
            listaDocumentos.add(traspasar(cruceDocumentoEntity));
        }
        return listaDocumentos;
    }
    
    public CruceDocumentoTO buscarCruceDocumentoPorId(BigDecimal idDocumento) throws Exception {
        Query query = getEm().createNamedQuery("CruceDocumentoEntity.buscarDocumentoPorId");
        query.setParameter("idDocumento", idDocumento);
        List<CruceDocumentoEntity> listaDocumentoEntity = query.getResultList();
        if(!listaDocumentoEntity.isEmpty())
            return traspasar(listaDocumentoEntity.get(0));
        return new CruceDocumentoTO();
    }
    
    public void eliminar(CruceDocumentoTO cruceDocumentoTO) throws Exception {
        CruceDocumentoEntity cruceDocumentoEntity =traspasar(cruceDocumentoTO);
        remove(cruceDocumentoEntity);
    }
    
    public void guardar(CruceDocumentoTO cruceDocumentoTO) throws Exception {
        CruceDocumentoEntity cruceDocumentoEntity =traspasar(cruceDocumentoTO);
        persist(cruceDocumentoEntity);
    }
    
    public CruceDocumentoEntity traspasar(CruceDocumentoTO cruceDocumentoTO){
        CruceDocumentoEntity cruceDocumentoEntity = new CruceDocumentoEntity();
        cruceDocumentoEntity.setIdDoc(cruceDocumentoTO.getIdDoc());
        cruceDocumentoEntity.setIdCruce(cruceDocumentoTO.getIdCruce());
        cruceDocumentoEntity.setTipo(cruceDocumentoTO.getTipo());
        cruceDocumentoEntity.setFecha(cruceDocumentoTO.getFecha());
        cruceDocumentoEntity.setArchivo(cruceDocumentoTO.getArchivo());
        cruceDocumentoEntity.setObservacion(cruceDocumentoTO.getObservacion());
        cruceDocumentoEntity.setUsrSub(cruceDocumentoTO.getUsrSub());
        if(cruceDocumentoTO.getNombreArchivo() != null)
        {
            cruceDocumentoEntity.setNombreDoc(Util.convertFromUTF8(cruceDocumentoTO.getNombreArchivo()));
        }
        cruceDocumentoEntity.setOtAuter(cruceDocumentoTO.getOT_AUTER());
        cruceDocumentoEntity.setOtm(cruceDocumentoTO.getOTM());
        cruceDocumentoEntity.setFechaOtm(cruceDocumentoTO.getFecha_otm());
        cruceDocumentoEntity.setFecRecOtm(cruceDocumentoTO.getFec_rec_otm());
        cruceDocumentoEntity.setFecMedicion(cruceDocumentoTO.getFec_medicion());
        cruceDocumentoEntity.setDescMedicion(cruceDocumentoTO.getDesc_medicion());
        cruceDocumentoEntity.setResultado(cruceDocumentoTO.getResultado());
        cruceDocumentoEntity.setFecIngUoct(cruceDocumentoTO.getFec_ing_uoct());
        cruceDocumentoEntity.setFecAprUoct(cruceDocumentoTO.getFec_apr_uoct());
        cruceDocumentoEntity.setArchivo2(cruceDocumentoTO.getArchivo2());
        cruceDocumentoEntity.setArchivo3(cruceDocumentoTO.getArchivo3());
        cruceDocumentoEntity.setArchivo4(cruceDocumentoTO.getArchivo4());
        cruceDocumentoEntity.setArchivo5(cruceDocumentoTO.getArchivo5());
        cruceDocumentoEntity.setNombreDoc2(cruceDocumentoTO.getNombre_doc2());
        cruceDocumentoEntity.setNombreDoc3(cruceDocumentoTO.getNombre_doc3());
        cruceDocumentoEntity.setNombreDoc4(cruceDocumentoTO.getNombre_doc4());
        cruceDocumentoEntity.setNombreDoc5(cruceDocumentoTO.getNombre_doc5());
        System.out.println("CruceDocEnt " + cruceDocumentoEntity.toString());
        return cruceDocumentoEntity;    
    }
    
    public CruceDocumentoTO traspasar(CruceDocumentoEntity cruceDocumentoEntity){
        CruceDocumentoTO cruceDocumentoTO = new CruceDocumentoTO();
        cruceDocumentoTO.setIdDoc(cruceDocumentoEntity.getIdDoc());
        cruceDocumentoTO.setIdCruce(cruceDocumentoEntity.getIdCruce());
        cruceDocumentoTO.setTipo(cruceDocumentoEntity.getTipo());
        cruceDocumentoTO.setFecha(cruceDocumentoEntity.getFecha());
        cruceDocumentoTO.setFechaDesc(Util.fechaHoraToString(cruceDocumentoEntity.getFecha()));
        cruceDocumentoTO.setArchivo(cruceDocumentoEntity.getArchivo());
        cruceDocumentoTO.setObservacion(cruceDocumentoEntity.getObservacion());
        cruceDocumentoTO.setUsrSub(cruceDocumentoEntity.getUsrSub());
        cruceDocumentoTO.setNombreArchivo(cruceDocumentoEntity.getNombreDoc());
        
        cruceDocumentoTO.setOT_AUTER(cruceDocumentoEntity.getOtAuter());
        cruceDocumentoTO.setOTM(cruceDocumentoEntity.getOtm());
        cruceDocumentoTO.setFecha_otm(cruceDocumentoEntity.getFechaOtm());
        cruceDocumentoTO.setFec_rec_otm(cruceDocumentoEntity.getFecRecOtm());
        cruceDocumentoTO.setFec_medicion(cruceDocumentoEntity.getFecMedicion());
        cruceDocumentoTO.setDesc_medicion(cruceDocumentoEntity.getDescMedicion());
        cruceDocumentoTO.setResultado(cruceDocumentoEntity.getResultado());
        cruceDocumentoTO.setFec_ing_uoct(cruceDocumentoEntity.getFecIngUoct());
        cruceDocumentoTO.setFec_apr_uoct(cruceDocumentoEntity.getFecAprUoct());
        cruceDocumentoTO.setArchivo2(cruceDocumentoEntity.getArchivo2());
        cruceDocumentoTO.setArchivo3(cruceDocumentoEntity.getArchivo3());
        cruceDocumentoTO.setArchivo4(cruceDocumentoEntity.getArchivo4());
        cruceDocumentoTO.setArchivo5(cruceDocumentoEntity.getArchivo5());
        cruceDocumentoTO.setNombre_doc2(cruceDocumentoEntity.getNombreDoc2());
        cruceDocumentoTO.setNombre_doc3(cruceDocumentoEntity.getNombreDoc3());
        cruceDocumentoTO.setNombre_doc4(cruceDocumentoEntity.getNombreDoc4());
        cruceDocumentoTO.setNombre_doc5(cruceDocumentoEntity.getNombreDoc5());
        if(cruceDocumentoTO.getTipo().equals("4")) //4 mediciones, 5 justi, 6 proyectosu
        {
            cruceDocumentoTO.setNombreArchivo("Mediciones");
            cruceDocumentoTO.setNombreArchivo2("Informe de mediciones");
        }
        else if(cruceDocumentoTO.getTipo().equals("5")){
            cruceDocumentoTO.setNombreArchivo("Estudio de Justificación");
            cruceDocumentoTO.setNombreArchivo2("Informe de EJS");
            cruceDocumentoTO.setNombreArchivo3("Aprobación UOCT");
        }
        else if(cruceDocumentoTO.getTipo().equals("6")){
            cruceDocumentoTO.setNombreArchivo("Proyecto de semáforo");
            cruceDocumentoTO.setNombreArchivo2("Aprobación UOCT PROY");
            cruceDocumentoTO.setNombreArchivo3("Programación propuesta");
            cruceDocumentoTO.setNombreArchivo4("Aprobación UOCT TPOS");
            cruceDocumentoTO.setNombreArchivo5("Proyecto aprobado");
        }
        else if(cruceDocumentoTO.getTipo().equals("7")){
            cruceDocumentoTO.setNombreArchivo("Informe de Periodización");
            cruceDocumentoTO.setNombreArchivo2("Carta ingreso UOCT");
            cruceDocumentoTO.setNombreArchivo3("Carta Aprobación UOCT");
        }
        else if(cruceDocumentoTO.getTipo().equals("8")){
            cruceDocumentoTO.setNombreArchivo("Informe de Programación");
            cruceDocumentoTO.setNombreArchivo2("Programación");
            cruceDocumentoTO.setNombreArchivo3("Carta ingreso UOCT");
            cruceDocumentoTO.setNombreArchivo4("Carta Aprobación UOCT");
        }
        else if(cruceDocumentoTO.getTipo().equals("9")){
            cruceDocumentoTO.setNombreArchivo("Informe de SF");
            cruceDocumentoTO.setNombreArchivo2("Programación");
            cruceDocumentoTO.setNombreArchivo3("Carta ingreso UOCT");
            cruceDocumentoTO.setNombreArchivo4("Carta Aprobación UOCT");
        }
        else{
            cruceDocumentoTO.setNombreArchivo(cruceDocumentoTO.getArchivo());
        }
        
        return cruceDocumentoTO;    
    }
     
    
}
