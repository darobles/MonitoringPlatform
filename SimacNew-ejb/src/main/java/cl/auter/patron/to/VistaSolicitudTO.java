/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author drobles
 */
public class VistaSolicitudTO implements Serializable {

    private int id_solicitud;
    private String nombre_sol;
    private String email;
    private String fono;
    private String observacion;
    private String url_adjunto;
    private String estado_actual;
    private Date fec_ing;
    private String usr_ing;
    private int num_tarea_sma;
    private String asunto;
    private int idcomuna;
    private String nombre;
    private String motivo_rechazo;
    private int tipo_solicitante;
    private int ind_ate;
    private int num_reclamo;

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getNombre_sol() {
        return nombre_sol;
    }

    public void setNombre_sol(String nombre_sol) {
        this.nombre_sol = nombre_sol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUrl_adjunto() {
        return url_adjunto;
    }

    public void setUrl_adjunto(String url_adjunto) {
        this.url_adjunto = url_adjunto;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public Date getFec_ing() {
        return fec_ing;
    }

    public void setFec_ing(Date fec_ing) {
        this.fec_ing = fec_ing;
    }

    public String getUsr_ing() {
        return usr_ing;
    }

    public void setUsr_ing(String usr_ing) {
        this.usr_ing = usr_ing;
    }

    public int getNum_tarea_sma() {
        return num_tarea_sma;
    }

    public void setNum_tarea_sma(int num_tarea_sma) {
        this.num_tarea_sma = num_tarea_sma;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(int idcomuna) {
        this.idcomuna = idcomuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo_rechazo() {
        return motivo_rechazo;
    }

    public void setMotivo_rechazo(String motivo_rechazo) {
        this.motivo_rechazo = motivo_rechazo;
    }

    public int getTipo_solicitante() {
        return tipo_solicitante;
    }

    public void setTipo_solicitante(int tipo_solicitante) {
        this.tipo_solicitante = tipo_solicitante;
    }

    public int getInd_ate() {
        return ind_ate;
    }

    public void setInd_ate(int ind_ate) {
        this.ind_ate = ind_ate;
    }

    public int getNum_reclamo() {
        return num_reclamo;
    }

    public void setNum_reclamo(int num_reclamo) {
        this.num_reclamo = num_reclamo;
    }

    @Override
    public String toString() {
        return "VistaSolicitudTO{" + "id_solicitud=" + id_solicitud + ", nombre_sol=" + nombre_sol + ", email=" + email + ", fono=" + fono + ", observacion=" + observacion + ", url_adjunto=" + url_adjunto + ", estado_actual=" + estado_actual + ", fec_ing=" + fec_ing + ", usr_ing=" + usr_ing + ", num_tarea_sma=" + num_tarea_sma + ", asunto=" + asunto + ", idcomuna=" + idcomuna + ", nombre=" + nombre + ", motivo_rechazo=" + motivo_rechazo + ", tipo_solicitante=" + tipo_solicitante + ", ind_ate=" + ind_ate + ", num_reclamo=" + num_reclamo + '}';
    }
   
}
