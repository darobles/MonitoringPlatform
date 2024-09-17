/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author drobles
 */
public class FasePPRTO implements Serializable{
    BigDecimal id;
    BigDecimal idCruce;
    BigDecimal fase;
    BigDecimal dirfase;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public BigDecimal getFase() {
        return fase;
    }

    public void setFase(BigDecimal fase) {
        this.fase = fase;
    }

    public BigDecimal getDirfase() {
        return dirfase;
    }

    public void setDirfase(BigDecimal dirfase) {
        this.dirfase = dirfase;
    }

    @Override
    public String toString() {
        return "FasePPRTO{" + "id=" + id + ", idCruce=" + idCruce + ", fase=" + fase + ", dirfase=" + dirfase + '}';
    }
    
}
