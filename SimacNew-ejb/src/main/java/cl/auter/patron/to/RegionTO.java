/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class RegionTO implements Serializable{
    private int id_region;
    private String nombre;
    private List<ComunaTO> comunas;

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ComunaTO> getComunas() {
        return comunas;
    }

    public void setComunas(List<ComunaTO> comunas) {
        this.comunas = comunas;
    }
    
    @Override
    public String toString() {
        return "RegionTO{" + "id_region=" + id_region + ", nombre=" + nombre + '}';
    }
    
    
}
