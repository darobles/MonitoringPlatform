/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

/**
 *
 * @author Daniel
 */
public class VistaContEstadoTO {
    
    private int id;
    private int encendidos;
    private int observacion;
    private int apagado;
    private int invalidos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEncendidos() {
        return encendidos;
    }

    public void setEncendidos(int encendidos) {
        this.encendidos = encendidos;
    }

    public int getObservacion() {
        return observacion;
    }

    public void setObservacion(int observacion) {
        this.observacion = observacion;
    }

    public int getApagado() {
        return apagado;
    }

    public void setApagado(int apagado) {
        this.apagado = apagado;
    }

    public int getInvalidos() {
        return invalidos;
    }

    public void setInvalidos(int invalidos) {
        this.invalidos = invalidos;
    }

    @Override
    public String toString() {
        return "VistaContEstadoTO{" + "id=" + id + ", encendidos=" + encendidos + ", observacion=" + observacion + ", apagado=" + apagado + ", invalidos=" + invalidos + '}';
    }
    
}
