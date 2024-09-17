package cl.auter.convert;

import cl.auter.backing.AtencionesBacking;
import cl.auter.backing.base.BaseBacking;
import cl.auter.patron.to.SolicitudTO;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="solicitudConvertir")
public class SolicitudConvertir extends BaseBacking implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value !=null && !value.equals("")){
            AtencionesBacking atencionesBacking= buscarBean("atencionesBacking");
            int indice=0;
            for (SolicitudTO solicitudTO:  atencionesBacking.getListaSolicitudesReclamada()){
               if (solicitudTO.getIdSolicitud().equals(new BigDecimal(value))){
                   return atencionesBacking.getListaSolicitudesReclamada().get(indice);
               }     
               indice++;
            }
         }
         return null;
    }   

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
           SolicitudTO solicitudTO =(SolicitudTO) value;
           String idSolicitud=solicitudTO.getIdSolicitud()+"";
           return idSolicitud;  
        }
    }
    
}
