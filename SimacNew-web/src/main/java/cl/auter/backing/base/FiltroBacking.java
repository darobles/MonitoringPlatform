package cl.auter.backing.base;

import cl.auter.patron.to.CodigoTO;
import cl.auter.patron.to.ComunaTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FiltroBacking extends BaseBacking {

    public static final String BEAN_NAME = "filtroBacking";
    private boolean check = true;
    private boolean[] check1 = {true, true, true, true, true, true, true, true};

    private String intervalo;
    private List<String> listaChequeados = new ArrayList();
    private List<String> listaChequeadosDash = new ArrayList();
    private List<CodigoTO> colores = new ArrayList();
    private BigDecimal[] comunaSeleccionadas;
    private List<ComunaTO> listaComunaSeleccionadas = new ArrayList();
    private String pintarComuna;
    private String lstComunaSeleccionada;
    int id_region = 999;
    boolean verBuscarCruce = false;

    /**
     * Carga el Intervalo para los Filtros
     */
    public void intervalo() {
        CodigoTO codigoTO;
        try {
            codigoTO = parametrosFacadeLocal.buscarDominioCodigo("INTERVALO", "1");
            if (codigoTO != null) {
                if (codigoTO.getCodigo2() != null) {
                    try {
                        Integer.parseInt(codigoTO.getCodigo2());
                        intervalo = codigoTO.getCodigo2();
                        // intervalo="10";
                    } catch (NumberFormatException e) {
                        intervalo = "45";
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarComunas() {
        try {
            lstComunaSeleccionada = "";
            if (comunaSeleccionadas == null || comunaSeleccionadas.length == 0) {
                comunaSeleccionadas = new BigDecimal[1];
                comunaSeleccionadas[0] = new BigDecimal("999");
            }
            listaComunaSeleccionadas.clear();
            boolean todas = false;
            for (BigDecimal comunaSeleccionada : comunaSeleccionadas) {
                if (comunaSeleccionada.equals(new BigDecimal("999"))) { //todas uoct
                    listaComunaSeleccionadas.clear();
                    if (usurioAutenticado().getTipo().equals(new BigDecimal("242"))) {
                        listaComunaSeleccionadas.addAll(localizacionFacadeLocal.listaComunasMonitoreoUOCT());
                    } else {
                        if(id_region == 999) //Todas las regiones
                        {
                            listaComunaSeleccionadas.addAll(localizacionFacadeLocal.listaComunasMonitoreo());
                        }
                        else{
                            listaComunaSeleccionadas.addAll(localizacionFacadeLocal.buscarComunasPorRegion(id_region));
                        }
                    }
                    todas = true;
                    break;
                }
                listaComunaSeleccionadas.add(localizacionFacadeLocal.buscaComunaPorId(comunaSeleccionada));
            }
            if (todas) {
                
                comunaSeleccionadas = new BigDecimal[listaComunaSeleccionadas.size()];
                for (int i = 0; i < listaComunaSeleccionadas.size(); i++) {
                    comunaSeleccionadas[i] = listaComunaSeleccionadas.get(i).getIdComuna();
                }
                
            }
            for (ComunaTO comunaTO : listaComunaSeleccionadas) {
                lstComunaSeleccionada = lstComunaSeleccionada + comunaTO.getNombre();
                lstComunaSeleccionada = lstComunaSeleccionada + ",";
            }
            if (lstComunaSeleccionada.length() > 0) {
                verBuscarCruce = true;
                lstComunaSeleccionada = lstComunaSeleccionada.substring(0, lstComunaSeleccionada.length() - 1);
                lstComunaSeleccionada = lstComunaSeleccionada + ".";

            }

        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BigDecimal[] getComunaSeleccionadas() {
        return comunaSeleccionadas;
    }

    public void setComunaSeleccionadas(BigDecimal[] comunaSeleccionadas) {
        this.comunaSeleccionadas = comunaSeleccionadas;
    }

    public List<ComunaTO> getListaComunaSeleccionadas() {
        return listaComunaSeleccionadas;
    }

    public void setListaComunaSeleccionadas(List<ComunaTO> listaComunaSeleccionadas) {
        this.listaComunaSeleccionadas = listaComunaSeleccionadas;
    }

    public boolean existeComuna() {
        boolean existeComuna = false;
        if (comunaSeleccionadas != null) {
            existeComuna = true;
        }
        return existeComuna;
    }

    public void agregaComunaUsuario() {
        comunaSeleccionadas = new BigDecimal[1];
        comunaSeleccionadas[0] = usurioAutenticado().getIdComuna();

    }

    public String getPintarComuna() {
        return pintarComuna;
    }

    public void setPintarComuna(String pintarComuna) {
        this.pintarComuna = pintarComuna;
    }

    public void cargaEstadosOperacionales() {
        try {
            this.colores = obtenerEstadoImagen();
            for (CodigoTO codigoTO : colores) {
                listaChequeados.add(codigoTO.getDescripcion());
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargaEstadosOperacionalesDash() {
        try {
            this.colores = obtenerEstadoImagen();
            int i = 0;
            for (CodigoTO codigoTO : colores) {                
                if(i > 3)
                {
                    break;
                }
                listaChequeadosDash.add(codigoTO.getDescripcion());
                i++;
            }
        } catch (Exception ex) {
            Logger.getLogger(FiltroBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getCheck() {
        return this.check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getColor(int indice) {
        if (colores.isEmpty()) {
            cargaEstadosOperacionales();
        }
        return colores.get(indice).getCodigo1();
    }
    
    public String getColorDash(int indice) {
        if (colores.isEmpty()) {
            cargaEstadosOperacionalesDash();
        }
        return colores.get(indice).getCodigo1();
    }

    public String getImagen(int indice) {
        if (colores.isEmpty()) {
            cargaEstadosOperacionales();
        }
        return colores.get(indice).getCodigo2();
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public boolean[] getCheck1() {
        int i = 0;
        return check1;
    }

    public void setCheck1(boolean[] check1) {
        this.check1 = check1;
    }

    public String getLstComunaSeleccionada() {
        return lstComunaSeleccionada;
    }

    public void setLstComunaSeleccionada(String lstComunaSeleccionada) {
        this.lstComunaSeleccionada = lstComunaSeleccionada;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public List<String> getListaChequeados() {
        return listaChequeados;
    }

    public void setListaChequeados(List<String> listaChequeados) {
        this.listaChequeados = listaChequeados;
    }

    public List<CodigoTO> getColores() {
        return colores;
    }

    public void setColores(List<CodigoTO> colores) {
        this.colores = colores;
    }

    public boolean isVerBuscarCruce() {
        return verBuscarCruce;
    }

    public void setVerBuscarCruce(boolean verBuscarCruce) {
        this.verBuscarCruce = verBuscarCruce;
    }

    public List<String> getListaChequeadosDash() {
        return listaChequeadosDash;
    }

    public void setListaChequeadosDash(List<String> listaChequeadosDash) {
        this.listaChequeadosDash = listaChequeadosDash;
    }
    
    

}
