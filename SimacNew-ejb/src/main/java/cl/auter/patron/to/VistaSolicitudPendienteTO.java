/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class VistaSolicitudPendienteTO implements Serializable{

    private int id_region;
    private int id_comuna;
    private String nombre_comuna;
    private int num_sol;

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }

    public String getNombre_comuna() {
        return nombre_comuna;
    }

    public void setNombre_comuna(String nombre_comuna) {
        this.nombre_comuna = nombre_comuna;
    }

    public int getNum_sol() {
        return num_sol;
    }

    public void setNum_sol(int num_sol) {
        this.num_sol = num_sol;
    }

    @Override
    public String toString() {
        return "VistaSolicitudPendienteTO{" + "id_region=" + id_region + ", id_comuna=" + id_comuna + ", nombre_comuna=" + nombre_comuna + ", num_sol=" + num_sol + '}';
    }
    
}
