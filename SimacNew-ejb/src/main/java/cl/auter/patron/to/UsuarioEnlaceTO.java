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
public class UsuarioEnlaceTO {

    private Integer id;
    private String ideUsr;
    private String nomUsr;
    private String apePat;
    private String apeMat;
    private String indAct;
    private Date fecIng;
    private String emaUsr;
    private String pasUsr;
    private Integer tipo;
    private String nomEmp;
    private String rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdeUsr() {
        return ideUsr;
    }

    public void setIdeUsr(String ideUsr) {
        this.ideUsr = ideUsr;
    }

    public String getNomUsr() {
        return nomUsr;
    }

    public void setNomUsr(String nomUsr) {
        this.nomUsr = nomUsr;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getIndAct() {
        return indAct;
    }

    public void setIndAct(String indAct) {
        this.indAct = indAct;
    }

    public Date getFecIng() {
        return fecIng;
    }

    public void setFecIng(Date fecIng) {
        this.fecIng = fecIng;
    }

    public String getEmaUsr() {
        return emaUsr;
    }

    public void setEmaUsr(String emaUsr) {
        this.emaUsr = emaUsr;
    }

    public String getPasUsr() {
        return pasUsr;
    }

    public void setPasUsr(String pasUsr) {
        this.pasUsr = pasUsr;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioEnlaceTO{" + "id=" + id + ", ideUsr=" + ideUsr + ", nomUsr=" + nomUsr + ", apePat=" + apePat + ", apeMat=" + apeMat + ", indAct=" + indAct + ", fecIng=" + fecIng + ", emaUsr=" + emaUsr + ", pasUsr=" + pasUsr + ", tipo=" + tipo + ", nomEmp=" + nomEmp + ", rol=" + rol + '}';
    }
    
}
