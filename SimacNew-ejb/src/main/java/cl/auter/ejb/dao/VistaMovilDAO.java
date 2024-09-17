/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.CodigoEntity;
import cl.auter.ejb.entity.bmp.PoligonoComunalEntity;
import cl.auter.ejb.entity.bmp.VistaMovilEntity;
import cl.auter.patron.to.PoligonoTO;
import cl.auter.patron.to.VistaMovilTO;
import cl.auter.util.Util;
import java.awt.Polygon;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author drobles
 */
public class VistaMovilDAO extends BaseDAO<VistaMovilEntity> {
    
    public VistaMovilDAO(Class<VistaMovilEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public List<VistaMovilTO> listaVistaMovilTodos() {
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, em);
        List<VistaMovilTO> listaComuna = new ArrayList<VistaMovilTO>();
        Query query = getEm().createNamedQuery("VistaMovilEntity.findAll");
        List<VistaMovilEntity> listaVistaMovilEntity = query.getResultList();
        for (VistaMovilEntity comunaEntity : listaVistaMovilEntity) {
            VistaMovilTO movilTO = traspasar(comunaEntity);
            try {
                movilTO.setIcono("../../imagenes/mapa/CamionetaTransparente.png");
                movilTO.setDescripcionFecha(Util.retornaFechaFormateadaString(movilTO.getFecultlec().toString()));
            } catch (Exception ex) {
                Logger.getLogger(VistaMovilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaComuna.add(movilTO);
        }
        return listaComuna;
    }
    public List<VistaMovilTO> listaVistaMovilComuna(BigDecimal idComuna) throws Exception {
        List<VistaMovilTO> listaVistaMovilcomuna = new ArrayList();
        Polygon poligono = new Polygon();
        CodigoDAO codigoDAO = new CodigoDAO(CodigoEntity.class, em);
        PoligonoDAO poligonoDAO = new PoligonoDAO(PoligonoComunalEntity.class, em);
        List<VistaMovilTO> listaVistaMovilTO = new ArrayList<VistaMovilTO>();
        Query query = getEm().createNamedQuery("VistaMovilEntity.findAll");
        List<VistaMovilEntity> listaVistaMovilEntity = query.getResultList();
        for (VistaMovilEntity comunaEntity : listaVistaMovilEntity) {
            VistaMovilTO movilTO = traspasar(comunaEntity);
            try {
                movilTO.setDescripcionFecha(Util.retornaFechaFormateadaString(movilTO.getFecultlec().toString()));
            } catch (Exception ex) {
                Logger.getLogger(VistaMovilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaVistaMovilTO.add(movilTO);
        }
        List<PoligonoTO> listaPoligono = poligonoDAO.buscarPoligonoComuna(idComuna.intValue());
        for (PoligonoTO poligonoTO : listaPoligono) {
            int x = poligonoTO.getLongitud().multiply(new BigDecimal(1000000)).intValue();
            int y = poligonoTO.getLatitud().multiply(new BigDecimal(1000000)).intValue();
            poligono.addPoint(x, y);
        }
        for(VistaMovilTO movilTO: listaVistaMovilTO)
        {
            int latitud = (new BigDecimal(movilTO.getLatitud()).multiply(new BigDecimal(1000000))).intValue();
            int longitud = (new BigDecimal(movilTO.getLongitud()).multiply(new BigDecimal(1000000))).intValue();
            boolean contenido = poligono.contains(longitud, latitud);
            //Si se encuentra dentro del poligono, entonces agregarlo a la lista definitivo de moviles.
            if (contenido) {
                movilTO.setIcono("../../imagenes/mapa/CamionetaTransparente.png");
                listaVistaMovilcomuna.add(movilTO);
            }
        }
        return listaVistaMovilcomuna;
    }
    

    public VistaMovilTO traspasar(VistaMovilEntity movilEntity) {
        VistaMovilTO movilTO = new VistaMovilTO();
        movilTO.setCodigomovil(movilEntity.getCodigomovil());
        movilTO.setFecultlec(movilEntity.getFecultlec());
        movilTO.setIdmovil(movilEntity.getIdmovil());
        movilTO.setLatitud(movilEntity.getLatitud().replace(",", "."));
        movilTO.setLongitud(movilEntity.getLongitud().replace(",", "."));
        movilTO.setTipo(movilEntity.getTipo());
        movilTO.setUltcruce(movilEntity.getUltcruce());
        return movilTO;
    }

    public VistaMovilEntity traspasar(VistaMovilTO movilTO) {
        VistaMovilEntity movilEntity = new VistaMovilEntity();
        movilEntity.setCodigomovil(movilTO.getCodigomovil());
        movilEntity.setFecultlec(movilTO.getFecultlec());
        movilEntity.setIdmovil(movilTO.getIdmovil());
        movilEntity.setLatitud(movilTO.getLatitud());
        movilEntity.setLongitud(movilTO.getLongitud());
        movilEntity.setTipo(movilTO.getTipo());
        movilEntity.setUltcruce(movilTO.getUltcruce());
        return movilEntity;
    }
}
