package cl.auter.convert;

import cl.auter.backing.DashboardBacking;
import cl.auter.backing.HistoricoBacking;
import cl.auter.backing.MapaPrimeBacking;
import cl.auter.backing.base.BaseBacking;
import cl.auter.patron.to.VistaCruceTO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rbaeza
 */
@FacesConverter(value = "puntoConvertir")
public class PuntoConvertir extends BaseBacking implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<VistaCruceTO> listaPuntos = new ArrayList();
        if (value != null && !value.equals("")) {
            VistaCruceTO vistaCruce = new VistaCruceTO();
            if (component.getId().equals("idCompleto")) {
                MapaPrimeBacking mapaPrimeBacking = buscarBean("mapaPrimeBacking");
                if (mapaPrimeBacking.getListaPuntos() != null & mapaPrimeBacking.getListaPuntos().size() > 0) {
                    listaPuntos = mapaPrimeBacking.getListaPuntos();
                }
            } else if(component.getId().equals("idCompletoHis")) { //idCompletoHis
                HistoricoBacking historicoBacking = buscarBean("historicoBacking");
                if (historicoBacking.getListaPuntos() != null & historicoBacking.getListaPuntos().size() > 0) {
                    listaPuntos = historicoBacking.getListaPuntos();
                }
            }
            else{
                DashboardBacking dashboardBacking = buscarBean("dashboardBacking");
                if (dashboardBacking.getListaPuntos() != null & dashboardBacking.getListaPuntos().size() > 0) {
                    listaPuntos = dashboardBacking.getListaPuntos();
                }
            }

            for (VistaCruceTO vistaCruceTO : listaPuntos) {
                if (vistaCruceTO.getUbicacion().equals(value)) {
                    vistaCruce.setIdCruce(vistaCruceTO.getIdCruce());
                    vistaCruce.setLatitud(vistaCruceTO.getLatitud());
                    vistaCruce.setLongitud(vistaCruceTO.getLongitud());
                    vistaCruce.setImagenEstadoOperacional(vistaCruceTO.getImagenEstadoOperacional());
                    vistaCruce.setUbicacion(vistaCruceTO.getUbicacion());
                }
            }
            return vistaCruce;

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            VistaCruceTO vistaCruceTO = (VistaCruceTO) value;
            String ubicacion = vistaCruceTO.getUbicacion();
            return ubicacion;
        }
    }
}
