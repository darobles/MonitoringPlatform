/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

/**
 *
 * @author drobles
 */
public class MatrizMacro {
    int id_destino;
    String nombre_dest;
    int match;
    String porcentaje;
    String fecha;

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getNombre_dest() {
        return nombre_dest;
    }

    public void setNombre_dest(String nombre_dest) {
        this.nombre_dest = nombre_dest;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "MatrizMacro{" + "id_destino=" + id_destino + ", nombre_dest=" + nombre_dest + ", match=" + match + ", porcentaje=" + porcentaje + ", fecha=" + fecha + '}';
    }

}
