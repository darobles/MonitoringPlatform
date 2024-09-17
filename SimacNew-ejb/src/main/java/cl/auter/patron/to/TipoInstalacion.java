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
public class TipoInstalacion {
    int codigo1;
    String imagen;
    String descripcion;

    public int getCodigo1() {
        return codigo1;
    }

    public void setCodigo1(int codigo1) {
        this.codigo1 = codigo1;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TIPO_INSTALACION{" + "codigo1=" + codigo1 + ", imagen=" + imagen + ", descripcion=" + descripcion + '}';
    }
    
}
