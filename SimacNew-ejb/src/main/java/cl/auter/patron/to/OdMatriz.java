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
public class OdMatriz {
   int idsen_source;
   int idsen_dest;
   float porcentaje;
   String time;
   String nombreDestino;

    public int getIdsen_source() {
        return idsen_source;
    }

    public void setIdsen_source(int idsen_source) {
        this.idsen_source = idsen_source;
    }

    public int getIdsen_dest() {
        return idsen_dest;
    }

    public void setIdsen_dest(int idsen_dest) {
        this.idsen_dest = idsen_dest;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    @Override
    public String toString() {
        return "OdMatriz{" + "idsen_source=" + idsen_source + ", idsen_dest=" + idsen_dest + ", porcentaje=" + porcentaje + ", time=" + time + ", nombreDestino=" + nombreDestino + '}';
    }
    
    
}