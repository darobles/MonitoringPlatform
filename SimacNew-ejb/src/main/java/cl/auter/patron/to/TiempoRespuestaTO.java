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
public class TiempoRespuestaTO implements Serializable{
    int id_comuna;
    String nombre;
    int tiempo_respuesta;
    int incidencias;

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo_respuesta() {
        return tiempo_respuesta;
    }

    public void setTiempo_respuesta(int tiempo_respuesta) {
        this.tiempo_respuesta = tiempo_respuesta;
    }

    public int getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(int incidencias) {
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return "TiemposRespuestaTO{" + "id_comuna=" + id_comuna + ", nombre=" + nombre + ", tiempo_respuesta=" + tiempo_respuesta + ", incidencias=" + incidencias + '}';
    }
    
}
