/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author drobles
 */
public class NodoTO {
    BigDecimal id;
    BigDecimal idCruce;
    BigDecimal idFabrica;
    BigDecimal latitud;
    BigDecimal longitud;
    String nombre;
    String idMarca;
    String odMatriz;
    String matrizMacro;
    List<OdMatriz> listaOdMatriz;
    List<MatrizMacro> listaMatrizMacro;
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }
    
    public BigDecimal getIdFabrica() {
        return idFabrica;
    }

    public void setIdFabrica(BigDecimal idFabrica) {
        this.idFabrica = idFabrica;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getOdMatriz() {
        return odMatriz;
    }
    
    public String getMatrizMacro() {
        return matrizMacro;
    }

    public void setMatrizMacro(String matrizMacro) {
        this.matrizMacro = matrizMacro;
    }
    
    public void setOdMatriz(String odMatriz) {
        this.odMatriz = odMatriz;
    }

    public List<OdMatriz> getListaOdMatriz() {
        return listaOdMatriz;
    }

    public void setListaOdMatriz(List<OdMatriz> listaOdMatriz) {
        this.listaOdMatriz = listaOdMatriz;
    }

    public List<MatrizMacro> getListaMatrizMacro() {
        return listaMatrizMacro;
    }

    public void setListaMatrizMacro(List<MatrizMacro> listaMatrizMacro) {
        this.listaMatrizMacro = listaMatrizMacro;
    }

    @Override
    public String toString() {
        return "NodoTO{" + "id=" + id + ", idCruce=" + idCruce + ", idFabrica=" + idFabrica + ", latitud=" + latitud + ", longitud=" + longitud + ", nombre=" + nombre + ", idMarca=" + idMarca + ", odMatriz=" + odMatriz + ", matrizMacro=" + matrizMacro + ", listaOdMatriz=" + listaOdMatriz + ", listaMatrizMacro=" + listaMatrizMacro + '}';
    }

}
