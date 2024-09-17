package cl.auter.backing.ordernar;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.SortOrder;


 
@ManagedBean (name = MonitorOrdenar.BEAN_NAME )
@ViewScoped

public class MonitorOrdenar implements Serializable {
    private static final long serialVersionUID = -6237417487105926855L;
    public static final String BEAN_NAME="monitorOrdenar";
    
    private SortOrder nombreSolicitanteOrder = SortOrder.UNSORTED;
    private SortOrder cruceOrder = SortOrder.UNSORTED;
    public void sortByCruce() {
        if (cruceOrder.equals(SortOrder.ASCENDING)) {
            setCruceOrder(SortOrder.DESCENDING);
        } else {
            setCruceOrder(SortOrder.ASCENDING);
        }
    }
      public void sortByNombreSolicitante() {
        if (nombreSolicitanteOrder.equals(SortOrder.ASCENDING)) {
            setNombreSolicitanteOrder(SortOrder.DESCENDING);
        } else {
            setNombreSolicitanteOrder(SortOrder.ASCENDING);
        }
    }

    public SortOrder getNombreSolicitanteOrder() {
        return nombreSolicitanteOrder;
    }

    public void setNombreSolicitanteOrder(SortOrder nombreSolicitanteOrder) {
        this.nombreSolicitanteOrder = nombreSolicitanteOrder;
    }
      
      
    public SortOrder getCruceOrder() {
        return cruceOrder;
    }
    public void setCruceOrder(SortOrder cruceOrder) {
        this.cruceOrder = cruceOrder;
    }
 
    
}