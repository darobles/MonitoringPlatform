package cl.auter.backing.base;

import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.RolUsuarioTO;
import cl.auter.patron.to.UserTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = ParametrosBacking.BEAN_NAME )
@SessionScoped
public class ParametrosBacking  extends  BaseBacking {
    public static final String BEAN_NAME="parametrosBacking";
    private List<ComunaTO> listaComunas = new ArrayList<ComunaTO>();
    private UserTO usuarioAutentificadoTO = new UserTO();
    private RolUsuarioTO rolUsuarioTO = new RolUsuarioTO();
    private List<CodigoTO> listaMenu = new ArrayList();
 
    @PostConstruct
    public void init(){
        try{
            ComunaTO comunaTO = new ComunaTO();
            comunaTO.setIdComuna(new BigDecimal(-1));
            comunaTO.setDescripcionComuna("Seleccionar comuna");
            comunaTO.setNombre("Seleccionar comuna");
            this.listaComunas.add(comunaTO);
            this.listaComunas.addAll(localizacionFacadeLocal.listaComunasTodas());
           
            listaMenu.clear();
            listaMenu = parametrosFacadeLocal.listaPorDominio("MENU_GEOREFERENCIA");
        } catch (Exception ex) {
           Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Devuelve la lista de Codigos segun un Dominio dado
     * @param dominio
     * @return 
     */
    public List<CodigoTO> buscaDominio(String dominio){
        List<CodigoTO> listaCodigos = new ArrayList<CodigoTO>();
        try{            
            listaCodigos=parametrosFacadeLocal.listaPorDominio(dominio); 
            return listaCodigos;
        } catch (Exception ex) {
           Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCodigos;
    }
    
    public boolean verMenu(String codigo1, String codigo2){
        String idMenu = "";
        rolUsuarioTO = rolUsuarioAutenticado();
        if(rolUsuarioTO.getOpcion() != null){
            String[] permiso = rolUsuarioTO.getOpcion().split(";");
            for(CodigoTO codigoTO : listaMenu){
                if(codigoTO.getCodigo1().equals(codigo1) && codigoTO.getCodigo2().equals(codigo2)){
                    idMenu = codigoTO.getIdCodigo().toString();
                    break;
                }
            }            
            for(String idPermitido : permiso){
                if(idPermitido.equals(idMenu)){
                    return true;
                }
            }
        }
        return false;
    }

    public List<ComunaTO> getListaComunas() {
        return listaComunas;
    }

    public void setListaComunas(List<ComunaTO> listaComunas) {
        this.listaComunas = listaComunas;
    }
     /**
     * Devuelve un codigo por Dominio, codigo1, codigo2
     * @param dominio
     * @param codigo1
     * @return 
     */
    public String buscaDominio(String dominio, String codigo1, String codigo2){
        try{
        CodigoTO codigoTO=parametrosFacadeLocal.buscarDominioCodigo(dominio,codigo1,codigo2); 
        return codigoTO.getDescripcion();
        } catch (Exception ex) {
           Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String buscaIdDominio(String dominio, String codigo1, String codigo2){
        try{
        CodigoTO codigoTO=parametrosFacadeLocal.buscarDominioCodigo(dominio,codigo1,codigo2); 
        return codigoTO.getIdCodigo().toString();
        } catch (Exception ex) {
           Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public UserTO getUsuarioAutentificadoTO() {
        return usuarioAutentificadoTO;
    }

    public void setUsuarioAutentificadoTO(UserTO usuarioAutentificadoTO) {
        this.usuarioAutentificadoTO = usuarioAutentificadoTO;
    }  

    public RolUsuarioTO getRolUsuarioTO() {
        return rolUsuarioTO;
    }

    public void setRolUsuarioTO(RolUsuarioTO rolUsuarioTO) {
        this.rolUsuarioTO = rolUsuarioTO;
    }   

    public List<CodigoTO> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<CodigoTO> listaMenu) {
        this.listaMenu = listaMenu;
    }
    
}