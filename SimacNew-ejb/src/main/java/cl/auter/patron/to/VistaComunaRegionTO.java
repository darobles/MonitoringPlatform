/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;

/**
 *
 * @author drobles
 */
public class VistaComunaRegionTO implements Serializable{
    
    private int id_comuna;
    private String descripcion;    
    private int id_region;
    private String nombre_region;
    private String nombre_comuna;
    private String url_logo;
    private String latitud;
    private String longitud;
    private String telefono;
    private String email;
    private String ind_mon;
    private int zoom;
    private String aut_uoct;

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    

    public String getNombre_region() {
        return nombre_region;
    }

    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }

    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInd_mon() {
        return ind_mon;
    }

    public void setInd_mon(String ind_mon) {
        this.ind_mon = ind_mon;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getAut_uoct() {
        return aut_uoct;
    }

    public void setAut_uoct(String aut_uoct) {
        this.aut_uoct = aut_uoct;
    }

    @Override
    public String toString() {
        return "VistaComunaRegionTO{" + "id_comuna=" + id_comuna + ", descripcion=" + descripcion + ", id_region=" + id_region + ", nombre_region=" + nombre_region + ", nombre_comuna=" + nombre_comuna + ", url_logo=" + url_logo + ", latitud=" + latitud + ", longitud=" + longitud + ", telefono=" + telefono + ", email=" + email + ", ind_mon=" + ind_mon + ", zoom=" + zoom + ", aut_uoct=" + aut_uoct + '}';
    }

    

}
