
package cl.auter.backing.base;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean (name = SeleccionBacking.BEAN_NAME )
@SessionScoped
public class SeleccionBacking {
    public static final String BEAN_NAME="seleccionBacking";
    List<SelectItem> listaCalles = new ArrayList<SelectItem>();
    
    @PostConstruct
    public void carga(){
        SelectItem calle = new SelectItem();
        calle.setLabel("APOQUINDO");
        calle.setValue("01");
        listaCalles.add(calle);
    }
    public List<SelectItem> getListaCalles() {
        return listaCalles;
    }

    public void setListaCalles(List<SelectItem> listaCalles) {
        this.listaCalles = listaCalles;
    }
    
}
