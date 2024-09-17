/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author drobles
 */
public class CrucePPR {
    BigDecimal idCruce;
    BigDecimal dirAct;
    List<BigDecimal> listaFases;

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public BigDecimal getDirAct() {
        return dirAct;
    }

    public void setDirAct(BigDecimal dirAct) {
        this.dirAct = dirAct;
    }

    public List<BigDecimal> getListaFases() {
        return listaFases;
    }

    public void setListaFases(List<BigDecimal> listaFases) {
        this.listaFases = listaFases;
    }

    @Override
    public String toString() {
        return "CrucePPR{" + "idCruce=" + idCruce + ", dirAct=" + dirAct + ", listaFases=" + listaFases + '}';
    }
    
}
