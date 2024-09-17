/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.convert;

import cl.auter.backing.base.BaseBacking;
import cl.auter.patron.to.MovilTO;
import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author drobles
 */
@FacesConverter(value = "movilConvertir")
public class MovilConvertir extends BaseBacking implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            MovilTO movilTO = new MovilTO();
            movilTO.setIdMovil(new BigDecimal(-1));
            movilTO.setCodigoMovil(value);
            return movilTO;
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.equals("-1")) {
            return value.toString();
        } else {
            MovilTO movil = (MovilTO) value;
            return movil.getCodigoMovil() != null ? String.valueOf(movil.getCodigoMovil()) : null;
        }
    }

}
