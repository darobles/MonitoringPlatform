package cl.auter.backing.filtro;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean (name = SolicitanteFiltro.BEAN_NAME )
@ViewScoped

public class SolicitanteFiltro implements Serializable{
    private static final long serialVersionUID = -5680001353441022183L;
    public static final String BEAN_NAME="solicitanteFiltro";
  
    private String nombreSolicitanteFilter;

    public String getNombreSolicitanteFilter() {
        return nombreSolicitanteFilter;
    }

    public void setNombreSolicitanteFilter(String nombreSolicitanteFilter) {
        this.nombreSolicitanteFilter = nombreSolicitanteFilter;
    }
   
   
}