/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.convert;

import cl.auter.backing.base.BaseBacking;
import cl.auter.patron.to.ComunaTO;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rbaeza
 */
@FacesConverter(value="comunaConvertir")
public class ComunaConvertir  extends BaseBacking implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        ComunaTO comunaTO = new ComunaTO();
        comunaTO.setIdComuna(new BigDecimal(3));
        comunaTO.setNombre("PASO");
        return comunaTO;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return ((ComunaTO)value).getNombre();
        }
    }
    
}
