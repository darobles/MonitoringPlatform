package cl.auter.convert;

import cl.auter.ejb.session.stateless.facade.LocalizacionFacade;
import cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal;
import cl.auter.patron.to.VistaInstalacionTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean
@ViewScoped
@FacesConverter(value="instalacionConvertir")
public class InstalacionConvertir implements Converter, Serializable {
    //@EJB
   // public LocalizacionFacadeLocal localizacionFacadeLocal = new LocalizacionFacade();
    
    private LocalizacionFacadeLocal getSession() {  
        try {              
            //Servidor AUTER
            //return (LocalizacionFacadeLocal) new InitialContext().lookup("java:global/GeoreferenciaEAR/Georeferencia-ejb/LocalizacionFacade!cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal");
            return (LocalizacionFacadeLocal) new InitialContext().lookup("java:global/SimacNew-ear-1.0-SNAPSHOT/SimacNew-ejb-1.0-SNAPSHOT/LocalizacionFacade!cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal");
            //Servidor LOCAL
            //return (LocalizacionFacadeLocal) new InitialContext().lookup("java:global/Georeferencia-war/LocalizacionFacade!cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal");            
        } catch (NamingException ex) {  
            throw new IllegalArgumentException(ex);  
        }  
    } 
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if (value !=null && !value.equals("")){
           try {
           String valor = value.split("-")[1].trim();
           LocalizacionFacadeLocal localizacionFacadeLocal = getSession();    
           VistaInstalacionTO vistaInstalacionTO = localizacionFacadeLocal.buscarVistaInstalacionPorIdInstalacion(new BigDecimal(valor));      
           return vistaInstalacionTO;  
           } catch (Exception ex) {
               Logger.getLogger(InstalacionConvertir.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return  null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value == null || value.equals("")) {  
            return "";  
        } else {  
           VistaInstalacionTO vistaInstalacionTO =(VistaInstalacionTO) value;
           String  ubicacion=vistaInstalacionTO.getUbicacion();
           return ubicacion;  
        }
    }
}

