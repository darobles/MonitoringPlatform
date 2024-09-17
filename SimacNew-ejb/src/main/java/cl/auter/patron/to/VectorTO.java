/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class VectorTO {

    BigDecimal id;
    BigInteger idfabrica;
    Date actualizacion;
    BigDecimal avgtime;
    BigDecimal distancia;
    BigDecimal speed;
    BigInteger nodoinicial;
    BigInteger nodofinal;
    String idMarca;
    String trayecto;
    BigInteger pcinicial;
    BigInteger pcfinal;
    BigInteger pcminicial;
    BigInteger pcmfinal;
    Date fpcminicial;
    Date fpcmfinal;
    BigInteger movil;
    BigInteger numLec;
    Date fecSis;
    String infMailErr;
    BigDecimal newValAlg;
    BigDecimal antValAlg;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getIdfabrica() {
        return idfabrica;
    }

    public void setIdfabrica(BigInteger idfabrica) {
        this.idfabrica = idfabrica;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public BigDecimal getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(BigDecimal avgtime) {
        this.avgtime = avgtime;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigInteger getNodoinicial() {
        return nodoinicial;
    }

    public void setNodoinicial(BigInteger nodoinicial) {
        this.nodoinicial = nodoinicial;
    }

    public BigInteger getNodofinal() {
        return nodofinal;
    }

    public void setNodofinal(BigInteger nodofinal) {
        this.nodofinal = nodofinal;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(String trayecto) {
        this.trayecto = trayecto;
    }

    public BigInteger getPcinicial() {
        return pcinicial;
    }

    public void setPcinicial(BigInteger pcinicial) {
        this.pcinicial = pcinicial;
    }

    public BigInteger getPcfinal() {
        return pcfinal;
    }

    public void setPcfinal(BigInteger pcfinal) {
        this.pcfinal = pcfinal;
    }

    public BigInteger getPcminicial() {
        return pcminicial;
    }

    public void setPcminicial(BigInteger pcminicial) {
        this.pcminicial = pcminicial;
    }

    public BigInteger getPcmfinal() {
        return pcmfinal;
    }

    public void setPcmfinal(BigInteger pcmfinal) {
        this.pcmfinal = pcmfinal;
    }

    public Date getFpcminicial() {
        return fpcminicial;
    }

    public void setFpcminicial(Date fpcminicial) {
        this.fpcminicial = fpcminicial;
    }

    public Date getFpcmfinal() {
        return fpcmfinal;
    }

    public void setFpcmfinal(Date fpcmfinal) {
        this.fpcmfinal = fpcmfinal;
    }

    public BigInteger getMovil() {
        return movil;
    }

    public void setMovil(BigInteger movil) {
        this.movil = movil;
    }

    public BigInteger getNumLec() {
        return numLec;
    }

    public void setNumLec(BigInteger numLec) {
        this.numLec = numLec;
    }

    public Date getFecSis() {
        return fecSis;
    }

    public void setFecSis(Date fecSis) {
        this.fecSis = fecSis;
    }

    public String getInfMailErr() {
        return infMailErr;
    }

    public void setInfMailErr(String infMailErr) {
        this.infMailErr = infMailErr;
    }

    public BigDecimal getNewValAlg() {
        return newValAlg;
    }

    public void setNewValAlg(BigDecimal newValAlg) {
        this.newValAlg = newValAlg;
    }

    public BigDecimal getAntValAlg() {
        return antValAlg;
    }

    public void setAntValAlg(BigDecimal antValAlg) {
        this.antValAlg = antValAlg;
    }

    @Override
    public String toString() {
        return "VectorTO{" + "id=" + id + ", idfabrica=" + idfabrica + ", actualizacion=" + actualizacion + ", avgtime=" + avgtime + ", distancia=" + distancia + ", speed=" + speed + ", nodoinicial=" + nodoinicial + ", nodofinal=" + nodofinal + ", idMarca=" + idMarca + ", trayecto=" + trayecto + ", pcinicial=" + pcinicial + ", pcfinal=" + pcfinal + ", pcminicial=" + pcminicial + ", pcmfinal=" + pcmfinal + ", fpcminicial=" + fpcminicial + ", fpcmfinal=" + fpcmfinal + ", movil=" + movil + ", numLec=" + numLec + ", fecSis=" + fecSis + ", infMailErr=" + infMailErr + ", newValAlg=" + newValAlg + ", antValAlg=" + antValAlg + '}';
    }
    
}
