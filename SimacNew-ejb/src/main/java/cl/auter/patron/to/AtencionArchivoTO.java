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
public class AtencionArchivoTO {

    private int id_atencion;
    private int id_archivo;
    private int tipo;
    private String tipo_nombre;
    private String archivo;
    private String archivo_origen;
    private Date fecha;
    private String rutaFinal;

    public int getId_atencion() {
        return id_atencion;
    }

    public void setId_atencion(int id_atencion) {
        this.id_atencion = id_atencion;
    }

    public int getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(int id_archivo) {
        this.id_archivo = id_archivo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipo_nombre() {
        return tipo_nombre;
    }

    public void setTipo_nombre(String tipo_nombre) {
        this.tipo_nombre = tipo_nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo_origen() {
        return archivo_origen;
    }

    public void setArchivo_origen(String archivo_origen) {
        this.archivo_origen = archivo_origen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRutaFinal() {
        return "/usr/local/municipal/mtt/" + archivo;
    }

    public void setRutaFinal(String rutaFinal) {
        this.rutaFinal = rutaFinal;
    }
    
    

    @Override
    public String toString() {
        return "AtencionArchivoTO{" + "id_atencion=" + id_atencion + ", id_archivo=" + id_archivo + ", tipo=" + tipo + ", archivo=" + archivo + ", archivo_origen=" + archivo_origen + ", fecha=" + fecha + '}';
    }
    
    
}
