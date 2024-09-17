package cl.auter.backing.ordernar;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.SortOrder;

 
@ManagedBean (name = SolicitanteOrdenar.BEAN_NAME )
@ViewScoped

public class SolicitanteOrdenar implements Serializable {
    private static final long serialVersionUID = -6237417487105926855L;
    public static final String BEAN_NAME="solicitanteOrdenar";
    
    private SortOrder nombreSolicitanteOrder = SortOrder.UNSORTED;
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
 
    
}