package cl.auter.backing;

import cl.auter.backing.base.FiltroBacking;
import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import cl.auter.patron.to.VistaCruceTO;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfPage;
import com.lowagie.text.pdf.PdfPageEventHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.PrimeFaces;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.model.DefaultStreamedContent;

@ManagedBean(name = MonitoreoBacking.BEAN_NAME)
@ViewScoped
public class MonitoreoBacking extends FiltroBacking {

    private List<ComunaTO> listaComunasMonitoreo = new ArrayList();
    public static final String BEAN_NAME = "monitoreoBacking";
    public String codigoPag = "212";
    private int page = 1;
    private boolean pintarTabla;
    private String pintarExportar = "false";
    private boolean refrescoAutomatico = true;
    private boolean verificacion = false;
    private List<VistaCruceTO> listaCruce = new ArrayList();
    private VistaCruceTO cruceEstado = new VistaCruceTO();
    private List<CodigoTO> listaEstOperativo = new ArrayList(); //Todos los elementos; dimac, cruce, tdv
    private List<CodigoTO> listaEstOpeDimac = new ArrayList(); //Solo dimac
    private String bg_uoct = "#F8F5F3";
    private String styleBackground = "";
    private boolean mostrarInfoConf = true;
    private String disp = "none";
    private boolean verExportar = false;
    
    public void init() {
        
    }
    @PostConstruct
    public void carga() {
        validarPagina(codigoPag);
        boolean[] check2 = {true, true, true, true, true, true, true, true};
        setCheck1(check2);
        try {
            if(usurioAutenticado().getTipo().equals(new BigDecimal("242"))) //Usuario UOCT
            {
                listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreoUOCT();
                bg_uoct = "transparent";
                styleBackground = "url(../../imagenes/Watermark2.png)";
                mostrarInfoConf = false;
            }
            else{
                listaComunasMonitoreo = getLocalizacionFacadeLocal().listaComunasMonitoreo();
                listaComunasMonitoreo.add(getLocalizacionFacadeLocal().buscaComunaPorId(new BigDecimal("999")));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MonitoreoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        intervalo();
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        
        listaCruce = new ArrayList();
        getListaChequeados().clear();
        cargaEstadosOperacionales();
        if (!tipoRol.equals(new BigDecimal("241"))) {
            setPintarComuna("true");
            this.pintarTabla = false;
        } else {
            this.pintarTabla = true;
            setPintarComuna("false");
            agregaComunaUsuario();
            cargarLista();
        }
        listaEstOpeDimac.clear();
        try {
            listaEstOperativo = getParametrosFacadeLocal().listaPorDominio("EST_OPE_COLOR");
        } catch (Exception ex) {
            Logger.getLogger(MonitoreoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaEstOperativo.forEach((cod) -> {
            if(Integer.parseInt(cod.getCodigo1()) < 5 )
                listaEstOpeDimac.add(cod);
        });
    }

    public void refresco() {
        this.listaCruce.clear();
        cargarLista();
    }

    public List<CodigoTO> buscaDominio(String dominio) {
        List<CodigoTO> listaCodigos = new ArrayList();
        try {
            listaCodigos = getParametrosFacadeLocal().listaPorDominio(dominio);
            return listaCodigos;
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCodigos;
    }

    public void processChecked(AjaxBehaviorEvent e) {
        SelectBooleanCheckbox s = (SelectBooleanCheckbox) e.getComponent();
        String seleccion = s.getStyleClass();

        String[] id = seleccion.split("-");
        String codigo = id[1];

        boolean agregar = true;
        int indice = 0;
        for (String chequeado : getListaChequeados()) {
            if (chequeado.equals(codigo)) {
                getListaChequeados().remove(indice);
                agregar = false;
                break;
            }
            indice++;
        }
        if (agregar) {
            getListaChequeados().add(codigo);
        }
        refresco();
        PrimeFaces.current().ajax().update("form:table");
    }
    
    public void cargarCruce(VistaCruceTO cruce){
        try {
            cruceEstado = cruce;
        } catch (Exception ex) {
            Logger.getLogger(MonitoreoBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarLista() {
        this.pintarTabla = true;
        listaCruce.clear();
        BigDecimal tipoRol = usurioAutenticado().getTipo();
        disp =  "block";
        if (tipoRol.equals(new BigDecimal("2"))) {
            this.pintarExportar = "true";
        }
        cargarComunas();
        try {
            if (getComunaSeleccionadas() != null && getComunaSeleccionadas().length > 0 && getComunaSeleccionadas()[getComunaSeleccionadas().length-1].equals(new BigDecimal("999"))) {
                for (ComunaTO comunaTO : getLocalizacionFacadeLocal().listaComunasMonitoreo()) {
                    List<VistaCruceTO> listaCruceComunas = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(comunaTO.getIdComuna(), getListaChequeados());
                    listaCruce.addAll(listaCruceComunas);
                }
            } else {
                for (BigDecimal idComunaSeleccionadas : getComunaSeleccionadas()) {
                    List<VistaCruceTO> listaCruceComunas = getLocalizacionFacadeLocal().listaVistaCrucesPorComuna(idComunaSeleccionadas, getListaChequeados());
                    listaCruce.addAll(listaCruceComunas);
                }
            }
            verExportar = !listaCruce.isEmpty();
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.A4.rotate());
        pdf.open();
    }

    public class Rotate extends PdfPageEventHelper {

        protected PdfNumber rotation = PdfPage.PORTRAIT;

        public void setRotation(PdfNumber rotation) {
            this.rotation = rotation;
        }
    }

    public String getImagenPrime(int indice) {
        CodigoTO cod = new CodigoTO();
        if (getColores().isEmpty()) {
            cargaEstadosOperacionales();
        } else {
            for (CodigoTO codigo : getColores()) {
                if (codigo.getDescripcion().equals(listaEstOpeDimac.get(indice).getCodigo1())) {
                    cod = codigo;
                    break;
                }
            }
        }
        return cod.getCodigo2();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void cargarCruceEstado(BigDecimal idCruce) {
        cruceEstado = new VistaCruceTO();
        for (VistaCruceTO cruce : listaCruce) {
            if (cruce.getIdCruce().equals(idCruce)) {
                setCruceEstado(cruce);
                break;
            }
        }
    }

    public List<VistaCruceTO> getListaCruce() {
        return listaCruce;
    }

    public void setListaCruce(List<VistaCruceTO> listaCruce) {
        this.listaCruce = listaCruce;
    }

    public boolean getPintarTabla() {
        return pintarTabla;
    }

    public void setPintarTabla(boolean pintarTabla) {
        this.pintarTabla = pintarTabla;
    }

    public boolean isRefrescoAutomatico() {
        return refrescoAutomatico;
    }

    public void setRefrescoAutomatico(boolean refrescoAutomatico) {
        this.refrescoAutomatico = refrescoAutomatico;
    }

    public boolean isVerificacion() {
        return verificacion;
    }

    public void setVerificacion(boolean verificacion) {
        this.verificacion = verificacion;
    }

    public String getPintarExportar() {
        return pintarExportar;
    }

    public void setPintarExportar(String pintarExportar) {
        this.pintarExportar = pintarExportar;
    }

    public VistaCruceTO getCruceEstado() {
        return cruceEstado;
    }

    public void setCruceEstado(VistaCruceTO cruceEstado) {
        this.cruceEstado = cruceEstado;
    }

    public List<ComunaTO> getListaComunasMonitoreo() {
        return listaComunasMonitoreo;
    }

    public void setListaComunasMonitoreo(List<ComunaTO> listaComunasMonitoreo) {
        this.listaComunasMonitoreo = listaComunasMonitoreo;
    }

    public List<CodigoTO> getListaEstOperativo() {
        return listaEstOperativo;
    }

    public void setListaEstOperativo(List<CodigoTO> listaEstOperativo) {
        this.listaEstOperativo = listaEstOperativo;
    }

    public List<CodigoTO> getListaEstOpeDimac() {
        return listaEstOpeDimac;
    }

    public void setListaEstOpeDimac(List<CodigoTO> listaEstOpeDimac) {
        this.listaEstOpeDimac = listaEstOpeDimac;
    }

    JasperPrint jasperPrint;

    public void cargaJasper() throws JRException {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getCurrentInstance().getExternalContext().getContext();
        String reportPath = servletContext.getRealPath("/jasper/listaMonitoreo.jasper");
        Map parametros = new HashMap();
        parametros.put("Emisor", "AUTER");
        jasperPrint = JasperFillManager.fillReport(reportPath, parametros, new JRBeanCollectionDataSource(listaCruce));
    }

    public void imprimirPDF() throws IOException, JRException {
        cargaJasper();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=listaMonitoreo.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirExcel() throws JRException, IOException {
        getExcel();
    }

    public void getExcel() {
        ByteArrayOutputStream outputStream = null;
        try {
            byte[] bytes;
            outputStream = new ByteArrayOutputStream();
            Workbook libro = new HSSFWorkbook();
            Sheet hojaListaMonitoreo = libro.createSheet("Lista Monitoreo");
            Row header = hojaListaMonitoreo.createRow(0);
            header.createCell(0).setCellValue("ID Cruce");
            header.createCell(1).setCellValue("Ubicación");
            header.createCell(2).setCellValue("Comuna");
            header.createCell(3).setCellValue("Informado");
            header.createCell(4).setCellValue("Estado");
            header.createCell(5).setCellValue("Sensor 220");
            header.createCell(6).setCellValue("Sensor UPS");
            header.createCell(7).setCellValue("Sensor Luces");
            header.createCell(8).setCellValue("Sensor UTC");
            int indice = 1;
            for (VistaCruceTO vistaCruceTO : listaCruce) {
                Row row = hojaListaMonitoreo.createRow(indice);
                Cell identificador = row.createCell(0);
                identificador.setCellValue(vistaCruceTO.getIdCruce().toString());
                Cell ubicacion = row.createCell(1);
                ubicacion.setCellValue(vistaCruceTO.getUbicacion());
                Cell comuna = row.createCell(2);
                comuna.setCellValue(vistaCruceTO.getDescripcionComuna());
                Cell informado = row.createCell(3);
                informado.setCellValue(vistaCruceTO.getDescripcionFecha());
                Cell estado = row.createCell(4);
                estado.setCellValue(vistaCruceTO.getDescripcionEstadoOperativo());
                Cell sensor220 = row.createCell(5);
                sensor220.setCellValue(vistaCruceTO.getDescripcionValModo220());
                Cell sensorUps = row.createCell(6);
                sensorUps.setCellValue(vistaCruceTO.getDescripcionValModoUps());
                Cell sensorLam = row.createCell(7);
                sensorLam.setCellValue(vistaCruceTO.getDescripcionValModoLam());
                Cell sensorUtc = row.createCell(8);
                sensorUtc.setCellValue(vistaCruceTO.getDescripcionValModoUtc());
                indice++;
            }
            for (int i = 0; i < 10; i++) {
                hojaListaMonitoreo.autoSizeColumn(i);
            }
            hojaListaMonitoreo.createFreezePane(0, 1);
            libro.write(outputStream);
            bytes = outputStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            setDownload(new DefaultStreamedContent(inputStream, "application/xls", "listaMonitoreo.xls"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String getBg_uoct() {
        return bg_uoct;
    }

    public void setBg_uoct(String bg_uoct) {
        this.bg_uoct = bg_uoct;
    }

    public String getStyleBackground() {
        return styleBackground;
    }

    public void setStyleBackground(String styleBackground) {
        this.styleBackground = styleBackground;
    }

    public boolean isMostrarInfoConf() {
        return mostrarInfoConf;
    }

    public void setMostrarInfoConf(boolean mostrarInfoConf) {
        this.mostrarInfoConf = mostrarInfoConf;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public boolean isVerExportar() {
        return verExportar;
    }

    public void setVerExportar(boolean verExportar) {
        this.verExportar = verExportar;
    }
    
    
    
    
}
