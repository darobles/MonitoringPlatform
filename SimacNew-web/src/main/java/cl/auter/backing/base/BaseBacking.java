/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.backing.base;

import cl.auter.backing.LoginBacking;
import cl.auter.backing.util.Util;
import cl.auter.ejb.session.stateless.facade.LocalizacionFacadeLocal;
import cl.auter.ejb.session.stateless.facade.ParametrosFacadeLocal;
import cl.auter.patron.to.CodigoTO;

import cl.auter.patron.to.RolUsuarioTO;
import cl.auter.patron.to.UserTO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;

public class BaseBacking {

    @EJB
    LocalizacionFacadeLocal localizacionFacadeLocal;
    @EJB
    ParametrosFacadeLocal parametrosFacadeLocal;
    /*@EJB
    TDVFacadeLocal tdvFacadeLocal;
    @EJB
    EnlaceFacadeLocal enlaceFacadeLocal;
    @EJB
    SMAFacadeLocal smaFacadeLocal;*/

    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private DefaultStreamedContent download;

    /**
     * Busca un componente por el ID
     *
     * @param id
     * @return
     */
    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }

    /**
     * Busca un Componente por ID dentro de Otro Compoenente
     *
     * @param base
     * @param id
     * @return
     */
    public List<CodigoTO> obtenerEstadoOperativo() {
        List<CodigoTO> listaEstadoOperativo = new ArrayList();
        CodigoTO codigoTO1 = new CodigoTO();
        codigoTO1.setIdCodigo(new BigDecimal("140"));
        codigoTO1.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO1.setCodigo1("1");
        codigoTO1.setCodigo2("../../imagenes/mapa/operativo/circuloVerde.png");
        codigoTO1.setDescripcion("Encendido");
        listaEstadoOperativo.add(codigoTO1);

        CodigoTO codigoTO2 = new CodigoTO();
        codigoTO2.setIdCodigo(new BigDecimal("141"));
        codigoTO2.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO2.setCodigo1("2");
        codigoTO2.setCodigo2("../../imagenes/mapa/operativo/circuloAzul.png");
        codigoTO2.setDescripcion("Encendido con Obs.");
        listaEstadoOperativo.add(codigoTO2);

        CodigoTO codigoTO3 = new CodigoTO();
        codigoTO3.setIdCodigo(new BigDecimal("142"));
        codigoTO3.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO3.setCodigo1("3");
        codigoTO3.setCodigo2("../../imagenes/mapa/operativo/circuloRojo.png");
        codigoTO3.setDescripcion("Apagado");
        listaEstadoOperativo.add(codigoTO3);

        CodigoTO codigoTO4 = new CodigoTO();
        codigoTO4.setIdCodigo(new BigDecimal("223"));
        codigoTO4.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO4.setCodigo1("4");
        codigoTO4.setCodigo2("../../imagenes/mapa/operativo/circuloGris.png");
        codigoTO4.setDescripcion("Inválido");
        listaEstadoOperativo.add(codigoTO4);
/*
        CodigoTO codigoTO5 = new CodigoTO();
        codigoTO5.setIdCodigo(new BigDecimal("260"));
        codigoTO5.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO5.setCodigo1("5");
        codigoTO5.setCodigo2("../../imagenes/mapa/operativo/tn_marker.png");
        codigoTO5.setDescripcion("TDV");
        listaEstadoOperativo.add(codigoTO5);
*/
        CodigoTO codigoTO6 = new CodigoTO();
        codigoTO6.setIdCodigo(new BigDecimal("265"));
        codigoTO6.setDominio("EST_OPE_DISPOSITIVO");
        codigoTO6.setCodigo1("5");
        codigoTO6.setCodigo2("../../imagenes/mapa/operativo/CamionetaTransparente.png");
        codigoTO6.setDescripcion("Movil");
        listaEstadoOperativo.add(codigoTO6);

        return listaEstadoOperativo;
    }

    public List<CodigoTO> obtenerEstadoImagen() {
        List<CodigoTO> listaEstadoOperativo = new ArrayList();
        CodigoTO codigoTO1 = new CodigoTO();
        codigoTO1.setIdCodigo(new BigDecimal("140"));
        codigoTO1.setDominio("EST_OPE_IMG");
        codigoTO1.setCodigo1("C-1");
        codigoTO1.setCodigo2("../../imagenes/mapa/operativo/circuloVerde.png");
        codigoTO1.setDescripcion("1");
        listaEstadoOperativo.add(codigoTO1);

        CodigoTO codigoTO2 = new CodigoTO();
        codigoTO2.setIdCodigo(new BigDecimal("141"));
        codigoTO2.setDominio("EST_OPE_IMG");
        codigoTO2.setCodigo1("C-2");
        codigoTO2.setCodigo2("../../imagenes/mapa/operativo/circuloAzul.png");
        codigoTO2.setDescripcion("2");
        listaEstadoOperativo.add(codigoTO2);

        CodigoTO codigoTO3 = new CodigoTO();
        codigoTO3.setIdCodigo(new BigDecimal("142"));
        codigoTO3.setDominio("EST_OPE_IMG");
        codigoTO3.setCodigo1("C-3");
        codigoTO3.setCodigo2("../../imagenes/mapa/operativo/circuloRojo.png");
        codigoTO3.setDescripcion("3");
        listaEstadoOperativo.add(codigoTO3);

        CodigoTO codigoTO4 = new CodigoTO();
        codigoTO4.setIdCodigo(new BigDecimal("223"));
        codigoTO4.setDominio("EST_OPE_IMG");
        codigoTO4.setCodigo1("C-4");
        codigoTO4.setCodigo2("../../imagenes/mapa/operativo/circuloGris.png");
        codigoTO4.setDescripcion("4");
        listaEstadoOperativo.add(codigoTO4);
        
        /*
        CodigoTO codigoTO5 = new CodigoTO();
        codigoTO5.setIdCodigo(new BigDecimal("260"));
        codigoTO5.setDominio("EST_OPE_IMG");
        codigoTO5.setCodigo1("C-5");
        codigoTO5.setCodigo2("../../imagenes/mapa/operativo/tn_marker.png");
        codigoTO5.setDescripcion("5");
        listaEstadoOperativo.add(codigoTO5);
*/
        CodigoTO codigoTO6 = new CodigoTO();
        codigoTO6.setIdCodigo(new BigDecimal("265"));
        codigoTO6.setDominio("EST_OPE_IMG");
        codigoTO6.setCodigo1("C-5");
        codigoTO6.setCodigo2("../../imagenes/mapa/operativo/CamionetaTransparente.png");
        codigoTO6.setDescripcion("5");
        listaEstadoOperativo.add(codigoTO6);
        
        return listaEstadoOperativo;
    }

    public CodigoTO[] obtenerTipoInstalacion() {
        
        try {
            
            List<CodigoTO> listaCodigo = parametrosFacadeLocal.listaPorDominio("TIPO_INSTALACION");
            listaCodigo.sort((CodigoTO o1, CodigoTO o2) -> { //Ordena los datos desde la bd
                Integer a = Integer.parseInt(o1.getCodigo1());
                Integer b = Integer.parseInt(o2.getCodigo1());
                return a.compareTo(b);
            });
            CodigoTO[] array = new CodigoTO[listaCodigo.size()];  
            CodigoTO[] aux = listaCodigo.toArray(array);
            return listaCodigo.toArray(array); // fill the array
        } catch (Exception ex) {
            Logger.getLogger(BaseBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent kid = null;
        UIComponent result = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Metodo que permite recuperar un Bean que este dentro un ambito
     *
     * @param <T>
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T buscarBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    public UserTO usurioAutenticado() {
        LoginBacking loginBacking = buscarBean("loginBacking");
        return loginBacking.getUsuarioAutenticado();
    }

    public RolUsuarioTO rolUsuarioAutenticado() {
        LoginBacking loginBacking = buscarBean("loginBacking");
        return loginBacking.getRolUsuarioTO();
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.A4.rotate());
        pdf.open();
    }

    public void prepDownload(String nombreArchivo) {
        if (nombreArchivo != null && !nombreArchivo.equals("")) {
            InputStream input = null;
            try {
                File file = new File(nombreArchivo);
                if (file.exists()) {
                    input = new FileInputStream(file);
                    MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                    setDownload(new DefaultStreamedContent(input, mimeTypesMap.getContentType(file), file.getName()));
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El archivo que intenta descargar no se encuentra en el repositorio."));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BaseBacking.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El archivo que intenta descargar no se encuentra en el repositorio."));
            } finally {
             
            }
        }
    }

    public boolean eliminarArchivo(String nombre) {
        try {
            boolean eli = Util.deleteFile(nombre);
            if(eli)
                System.out.println("Usuario " + usurioAutenticado().getIdentificador() + " eliminara " + nombre);
            else{
                System.out.println("El archivo " + nombre + " no pudo ser eliminado por " + usurioAutenticado().getIdentificador());
            }
            return eli;
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("ERROR : Guardar Documento ");
        }
        return false;
    }

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public void validarPagina(String codigo) {
        String[] items = rolUsuarioAutenticado().getOpcion().split(";");
        boolean existe = false;
        for (String item : items) {
            if (item.equals(codigo)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            try {
                if(codigo.equals("211")) //dashboard-mapa
                {
                   FacesContext.getCurrentInstance().getExternalContext().redirect("/semaforos/paginas/index.xhtml");   
                }
                else{
                   FacesContext.getCurrentInstance().getExternalContext().redirect("/semaforos/access.xhtml"); 
                }                
            } catch (IOException ex) {
                Logger.getLogger(BaseBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    public void redirectIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/AuterOnLine/paginas/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BaseBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LocalizacionFacadeLocal getLocalizacionFacadeLocal() {
        return localizacionFacadeLocal;
    }

    public void setLocalizacionFacadeLocal(LocalizacionFacadeLocal localizacionFacadeLocal) {
        this.localizacionFacadeLocal = localizacionFacadeLocal;
    }

    public ParametrosFacadeLocal getParametrosFacadeLocal() {
        return parametrosFacadeLocal;
    }

    public void setParametrosFacadeLocal(ParametrosFacadeLocal parametrosFacadeLocal) {
        this.parametrosFacadeLocal = parametrosFacadeLocal;
    }
    /*
    public TDVFacadeLocal getTdvFacadeLocal() {
        return tdvFacadeLocal;
    }

    public void setTdvFacadeLocal(TDVFacadeLocal tdvFacadeLocal) {
        this.tdvFacadeLocal = tdvFacadeLocal;
    }

    public EnlaceFacadeLocal getEnlaceFacadeLocal() {
        return enlaceFacadeLocal;
    }

    public void setEnlaceFacadeLocal(EnlaceFacadeLocal enlaceFacadeLocal) {
        this.enlaceFacadeLocal = enlaceFacadeLocal;
    }

    public SMAFacadeLocal getSmaFacadeLocal() {
        return smaFacadeLocal;
    }

    public void setSmaFacadeLocal(SMAFacadeLocal smaFacadeLocal) {
        this.smaFacadeLocal = smaFacadeLocal;
    }
    */
}
