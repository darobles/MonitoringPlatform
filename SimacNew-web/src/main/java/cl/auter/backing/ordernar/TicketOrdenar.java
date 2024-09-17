package cl.auter.backing.ordernar;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.SortOrder;

 
@ManagedBean (name = TicketOrdenar.BEAN_NAME )
@ViewScoped

public class TicketOrdenar implements Serializable {
    private static final long serialVersionUID = -6237417487105926855L;
    public static final String BEAN_NAME="ticketOrdenar";
    
    private SortOrder ticketOrder = SortOrder.UNSORTED;
     public void sortByTicket() {
        if (ticketOrder.equals(SortOrder.ASCENDING)) {
            setTicketOrder(SortOrder.DESCENDING);
        } else {
            setTicketOrder(SortOrder.ASCENDING);
        }
    }

    public SortOrder getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(SortOrder ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

  
 
    
}