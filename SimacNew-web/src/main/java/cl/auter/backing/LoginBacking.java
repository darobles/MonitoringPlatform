package cl.auter.backing;

import cl.auter.backing.base.BaseBacking;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.RolUsuarioTO;
import cl.auter.patron.to.UserTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = LoginBacking.BEAN_NAME)
@SessionScoped
public class LoginBacking extends BaseBacking implements Serializable {

    public static final String BEAN_NAME = "loginBacking";
    private static final long serialVersionUID = 1L;
    private String errorUsuario = "";
    private String errorClave = "";
    private String nombreUsuario = "";

    private RolUsuarioTO rolUsuarioTO;
    private UserTO usuarioAutenticado;

    private String telefono;
    private String email;
    private String logo_chico = "/imagenes/logo-chico2.png";
    private String telefonoMantenimiento = "";
    /**
     * Metodo una creado para realizar la carga inicial en backing
     */
    @PostConstruct
    public void carga() {
        this.errorUsuario = "";
        this.errorClave = "";        
        getTelefono();
        System.out.println("Usuario " + usuarioAutenticado.getIdentificador() + " inicia sesión simac-new a las: " + new Date() );
    }
    
    public UserTO getUsuario() {
        
        return usuarioAutenticado;
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/documentation.xhtml?faces-redirect=true";
    }

    public UserTO getUsuarioAutenticado() {
        if(usuarioAutenticado == null)
        {
            getTelefono();
        }
        return usuarioAutenticado;
    }
    
    public String obtTelefonoMan() {
        try {
            ComunaTO com = getLocalizacionFacadeLocal().buscaComunaPorId(usuarioAutenticado.getIdComuna());
            telefonoMantenimiento = com.getTelefono();
            if (telefonoMantenimiento == null || telefonoMantenimiento.equals("")) {
                telefonoMantenimiento = getLocalizacionFacadeLocal().buscaComunaPorId(new BigDecimal("999")).getTelefono().trim().replaceAll(" ", "");
            } else {
                telefonoMantenimiento = telefonoMantenimiento.trim().replaceAll(" ", "");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return telefonoMantenimiento;
    }

    public void setUsuarioAutenticado(UserTO usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
    
    public boolean isRol(String rol){
        String[] items = rolUsuarioTO.getOpcion().split(";");
        boolean existe = false;
        for (String item : items) {
            if (item.equals(rol)) {
                existe = true;
                break;
            }
        }
        if(existe)
        {
            return true;
        }
        return false;
    }

    public String getErrorUsuario() {
        return errorUsuario;
    }

    public void setErrorUsuario(String errorUsuario) {
        this.errorUsuario = errorUsuario;
    }

    public String getErrorClave() {
        return errorClave;
    }

    public void setErrorClave(String errorClave) {
        this.errorClave = errorClave;
    }

    public RolUsuarioTO getRolUsuarioTO() {
        return rolUsuarioTO;
    }

    public void setRolUsuarioTO(RolUsuarioTO rolUsuarioTO) {
        this.rolUsuarioTO = rolUsuarioTO;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefono() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = request.getRemoteUser();
        if (username != null) {
            try {
                if (usuarioAutenticado == null) {
                    usuarioAutenticado = new UserTO();
                    usuarioAutenticado = getLocalizacionFacadeLocal().buscarUsuarioPorId(username.toUpperCase());
                    if(usuarioAutenticado.getTipo().equals(new BigDecimal("242")))
                    {
                        logo_chico = "/imagenes/logo-chico4.png";
                    }
                }
                try {
                    String url = getLocalizacionFacadeLocal().buscaComunaPorId(usuarioAutenticado.getIdComuna()).getUrlLogo();
                    if (url != null) {
                        if (!url.equals("")) {
                            usuarioAutenticado.setUrlImagenComuna(url);
                        } else {
                            usuarioAutenticado.setUrlImagenComuna("/imagenes/logo-auter.png");
                        }
                    } else {
                        usuarioAutenticado.setUrlImagenComuna("/imagenes/logo-auter.png");
                    }
                    ComunaTO comuna = getLocalizacionFacadeLocal().obtenerDatosContacto(usuarioAutenticado.getIdComuna());
                    ComunaTO comunaAux = getLocalizacionFacadeLocal().obtenerDatosContacto(new BigDecimal("999"));
                    telefono = comuna.getTelefono();
                    email = comuna.getEmail();
                    if (comuna.getEmail() == null || comuna.getEmail().equals("")) {
                        email = comunaAux.getEmail();
                    }
                    if (comuna.getTelefono() == null || comuna.getTelefono().equals("")) {
                        telefono = comunaAux.getTelefono();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    if (rolUsuarioTO == null) {
                        rolUsuarioTO = getLocalizacionFacadeLocal().buscarRolUsuario(this.usuarioAutenticado.getIdentificador());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo_chico() {
        return logo_chico;
    }

    public void setLogo_chico(String logo_chico) {
        this.logo_chico = logo_chico;
    }

    public String getTelefonoMantenimiento() {
        return telefonoMantenimiento;
    }

    public void setTelefonoMantenimiento(String telefonoMantenimiento) {
        this.telefonoMantenimiento = telefonoMantenimiento;
    }
    
    
    
    
}
