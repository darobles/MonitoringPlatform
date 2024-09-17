package cl.auter.backing.filtro;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean (name = TicketFiltro.BEAN_NAME )
@ViewScoped

public class TicketFiltro implements Serializable{
    private static final long serialVersionUID = -5680001353441022183L;
    public static final String BEAN_NAME="ticketFiltro";
  
    private String ticketFilter;

    public String getTicketFilter() {
        return ticketFilter;
    }

    public void setTicketFilter(String ticketFilter) {
        this.ticketFilter = ticketFilter;
    }

   
   
   
}