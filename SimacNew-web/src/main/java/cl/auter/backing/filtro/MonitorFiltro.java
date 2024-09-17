package cl.auter.backing.filtro;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean (name = MonitorFiltro.BEAN_NAME )
@ViewScoped

public class MonitorFiltro implements Serializable{
    private static final long serialVersionUID = -5680001353441022183L;
    public static final String BEAN_NAME="monitorFiltro";
    private String cruceFilter;
    private String nombreSolicitanteFilter;

    public String getNombreSolicitanteFilter() {
        return nombreSolicitanteFilter;
    }

    public void setNombreSolicitanteFilter(String nombreSolicitanteFilter) {
        this.nombreSolicitanteFilter = nombreSolicitanteFilter;
    }
   
    public String getCruceFilter() {
        return cruceFilter;
    }
 
    public void setCruceFilter(String cruceFilter) {
        this.cruceFilter = cruceFilter;
    }
 
   
}