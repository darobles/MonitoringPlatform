package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.VistaCruceDocumentoEntity;
import cl.auter.patron.to.VistaDocumentoTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class VistaDocumentoDAO extends BaseDAO<VistaCruceDocumentoEntity>{

    public VistaDocumentoDAO(Class<VistaCruceDocumentoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    public List<VistaDocumentoTO> buscarDocumentosPorInstalacion(BigDecimal IdCruce){
        List<VistaDocumentoTO> listaInstalaciones = new ArrayList<VistaDocumentoTO>();
        Query query = getEm().createNamedQuery("DocumentosPorInstalacion");
        query.setParameter(1,IdCruce);
        List<VistaCruceDocumentoEntity> listaEntity=query.getResultList();
        for (VistaCruceDocumentoEntity vistaCruceDocumentoEntity: listaEntity){
                listaInstalaciones.add(traspasar(vistaCruceDocumentoEntity));
        }
	return listaInstalaciones;
    }
    public VistaDocumentoTO traspasar(VistaCruceDocumentoEntity vistaEntity){
        VistaDocumentoTO vistaDocumentoTO = new VistaDocumentoTO(); 
        vistaDocumentoTO.setIdDoc(vistaEntity.getIdDoc());
        vistaDocumentoTO.setIdCruce(vistaEntity.getIdCruce());
        vistaDocumentoTO.setFechaDocumento(vistaEntity.getFechaDocumento());
        vistaDocumentoTO.setTipoDocumento(vistaEntity.getTipoDocumento());
        vistaDocumentoTO.setArchivo(vistaEntity.getArchivo());
        vistaDocumentoTO.setObservacion(vistaEntity.getObservacion());          
        vistaDocumentoTO.setUsrSub(vistaEntity.getUsrSub());
                vistaDocumentoTO.setNombreArchivo(vistaEntity.getNombreDoc());
        
        vistaDocumentoTO.setOT_AUTER(vistaEntity.getOtAuter());
        vistaDocumentoTO.setOTM(vistaEntity.getOtm());
        vistaDocumentoTO.setFecha_otm(vistaEntity.getFechaOtm());
        vistaDocumentoTO.setFec_rec_otm(vistaEntity.getFecRecOtm());
        vistaDocumentoTO.setFec_medicion(vistaEntity.getFecMedicion());
        vistaDocumentoTO.setTexto_simac(vistaEntity.getTextoSimac());
        vistaDocumentoTO.setDesc_medicion(vistaEntity.getDescMedicion());
        vistaDocumentoTO.setResultado(vistaEntity.getResultado());
        vistaDocumentoTO.setFec_ing_uoct(vistaEntity.getFecIngUoct());
        vistaDocumentoTO.setFec_apr_uoct(vistaEntity.getFecAprUoct());
        vistaDocumentoTO.setArchivo2(vistaEntity.getArchivo2());
        vistaDocumentoTO.setArchivo3(vistaEntity.getArchivo3());
        vistaDocumentoTO.setArchivo4(vistaEntity.getArchivo4());
        vistaDocumentoTO.setArchivo5(vistaEntity.getArchivo5());
        vistaDocumentoTO.setNombre_doc2(vistaEntity.getNombreDoc2());
        vistaDocumentoTO.setNombre_doc3(vistaEntity.getNombreDoc3());
        vistaDocumentoTO.setNombre_doc4(vistaEntity.getNombreDoc4());
        vistaDocumentoTO.setNombre_doc5(vistaEntity.getNombreDoc5());
        if(vistaDocumentoTO.getTipoDocumento().equals("4")) //4 mediciones, 5 justi, 6 proyectosu
        {
            vistaDocumentoTO.setNombreArchivo("Mediciones");
            vistaDocumentoTO.setNombreArchivo2("Informe de mediciones");
        }
        else if(vistaDocumentoTO.getTipoDocumento().equals("5")){
            vistaDocumentoTO.setNombreArchivo("Estudio de Justificación");
            vistaDocumentoTO.setNombreArchivo2("Informe de EJS");
            vistaDocumentoTO.setNombreArchivo3("Aprobación UOCT");
        }
        else if(vistaDocumentoTO.getTipoDocumento().equals("6")){
            vistaDocumentoTO.setNombreArchivo("Proyecto de semáforo");
            vistaDocumentoTO.setNombreArchivo2("Aprobación UOCT PROY");
            vistaDocumentoTO.setNombreArchivo3("Programación propuesta");
            vistaDocumentoTO.setNombreArchivo4("Aprobación UOCT TPOS");
            vistaDocumentoTO.setNombreArchivo5("Proyecto aprobado");
        }
        else if(vistaDocumentoTO.getTipoDocumento().equals("7")){
            vistaDocumentoTO.setNombreArchivo("Informe de Periodización");
            vistaDocumentoTO.setNombreArchivo2("Carta ingreso UOCT");
            vistaDocumentoTO.setNombreArchivo3("Carta Aprobación UOCT");
        }
        else if(vistaDocumentoTO.getTipoDocumento().equals("8")){
            vistaDocumentoTO.setNombreArchivo("Informe de Programación");
            vistaDocumentoTO.setNombreArchivo2("Programación");
            vistaDocumentoTO.setNombreArchivo3("Carta ingreso UOCT");
            vistaDocumentoTO.setNombreArchivo4("Carta Aprobación UOCT");
        }
        else if(vistaDocumentoTO.getTipoDocumento().equals("9")){
            vistaDocumentoTO.setNombreArchivo("Informe de SF");
            vistaDocumentoTO.setNombreArchivo2("Programación");
            vistaDocumentoTO.setNombreArchivo3("Carta ingreso UOCT");
            vistaDocumentoTO.setNombreArchivo4("Carta Aprobación UOCT");
        }
        else{
            vistaDocumentoTO.setNombreArchivo(vistaDocumentoTO.getArchivo());
        }
        
        return vistaDocumentoTO;
     }
}
