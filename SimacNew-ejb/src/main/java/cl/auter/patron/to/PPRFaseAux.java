/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;

/**
 *
 * @author drobles
 */
public class PPRFaseAux {
    BigDecimal idCruce;
    String faseActiva;
    boolean btnDisable;

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public String getFaseActiva() {
        return faseActiva;
    }

    public void setFaseActiva(String faseActiva) {
        this.faseActiva = faseActiva;
    }

    public boolean isBtnDisable() {
        return btnDisable;
    }

    public void setBtnDisable(boolean btnDisable) {
        this.btnDisable = btnDisable;
    }
    
    
    
}
