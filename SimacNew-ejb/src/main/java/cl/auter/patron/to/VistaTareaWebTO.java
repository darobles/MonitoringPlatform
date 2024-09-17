/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.util.Date;

/**
 *
 * @author drobles
 */
public class VistaTareaWebTO {
    private int idtarea;
    private String tipoequipo;
    private String funcionamientiini;
    private int idcruce;
    private String fechaCreacionStr;
    private String obspreliminar;
    private String tareaterminada;
    private String informadoPor;
    private String nombrefuente;
    private String fechaCierreStr;
    private Date fechacreacion;
    private Date fechacierre;
    private short idtipoequipo;

    public int getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(int idtarea) {
        this.idtarea = idtarea;
    }

    public String getTipoequipo() {
        return tipoequipo;
    }

    public void setTipoequipo(String tipoequipo) {
        this.tipoequipo = tipoequipo;
    }

    public String getFuncionamientiini() {
        return funcionamientiini;
    }

    public void setFuncionamientiini(String funcionamientiini) {
        this.funcionamientiini = funcionamientiini;
    }

    public int getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(int idcruce) {
        this.idcruce = idcruce;
    }

    public String getFechaCreacionStr() {
        return fechaCreacionStr;
    }

    public void setFechaCreacionStr(String fechaCreacionStr) {
        this.fechaCreacionStr = fechaCreacionStr;
    }

    public String getObspreliminar() {
        return obspreliminar;
    }

    public void setObspreliminar(String obspreliminar) {
        this.obspreliminar = obspreliminar;
    }

    public String getTareaterminada() {
        return tareaterminada;
    }

    public void setTareaterminada(String tareaterminada) {
        this.tareaterminada = tareaterminada;
    }

    public String getInformadoPor() {
        return informadoPor;
    }

    public void setInformadoPor(String informadoPor) {
        this.informadoPor = informadoPor;
    }

    public String getNombrefuente() {
        return nombrefuente;
    }

    public void setNombrefuente(String nombrefuente) {
        this.nombrefuente = nombrefuente;
    }

    public String getFechaCierreStr() {
        return fechaCierreStr;
    }

    public void setFechaCierreStr(String fechaCierreStr) {
        this.fechaCierreStr = fechaCierreStr;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public short getIdtipoequipo() {
        return idtipoequipo;
    }

    public void setIdtipoequipo(short idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idtarea;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VistaTareaWebTO other = (VistaTareaWebTO) obj;
        if (this.idtarea != other.idtarea) {
            return false;
        }
        return true;
    }
    
    
    
}
