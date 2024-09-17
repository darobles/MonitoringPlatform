/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.datos;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author drobles
 */
public class ConsultaDimac {
    BigDecimal idconsulta;
    BigDecimal iddispositivo;
    String cmd;
    Timestamp fechacmd;
    String respuesta;
    Timestamp fecharespuesta;
    String usuario;
    BigDecimal revisado;
    String tipo;
    Timestamp horaBD;

    public BigDecimal getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(BigDecimal idconsulta) {
        this.idconsulta = idconsulta;
    }

    public BigDecimal getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(BigDecimal iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Timestamp getFechacmd() {
        return fechacmd;
    }

    public void setFechacmd(Timestamp fechacmd) {
        this.fechacmd = fechacmd;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Timestamp getFecharespuesta() {
        return fecharespuesta;
    }

    public void setFecharespuesta(Timestamp fecharespuesta) {
        this.fecharespuesta = fecharespuesta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getRevisado() {
        return revisado;
    }

    public void setRevisado(BigDecimal revisado) {
        this.revisado = revisado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getHoraBD() {
        return horaBD;
    }

    public void setHoraBD(Timestamp horaBD) {
        this.horaBD = horaBD;
    }

    @Override
    public String toString() {
        return "ConsultaDimac{" + "idconsulta=" + idconsulta + ", iddispositivo=" + iddispositivo + ", cmd=" + cmd + ", fechacmd=" + fechacmd + ", respuesta=" + respuesta + ", fecharespuesta=" + fecharespuesta + ", usuario=" + usuario + ", revisado=" + revisado + ", tipo=" + tipo + ", horaBD=" + horaBD + '}';
    }
    
    
}
