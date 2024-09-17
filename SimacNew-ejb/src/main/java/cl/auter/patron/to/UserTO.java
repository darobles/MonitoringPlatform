/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Marco
 */
public class UserTO implements Serializable{
    private String identificador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String intAct;
    private Date fechaIngreso;
    private String fechaDescripcion;
    private String email;
    private String password;
    private String password2;
    private BigDecimal tipo;
    private int tipoInt;
    private String nombreEmpresa;
    private BigDecimal idComuna;
    private String rol;
    private String urlImagenComuna;
    private String latitud;
    private String longitud;
    private String nombre_tipo;

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getIntAct() {
        return intAct;
    }

    public void setIntAct(String intAct) {
        this.intAct = intAct;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getTipo() {
        return tipo;
    }

    public void setTipo(BigDecimal tipo) {
        this.tipo = tipo;
    }

    public int getTipoInt() {
        return tipo.intValue();
    }

    public void setTipoInt(int tipoInt) {
        this.tipoInt = tipoInt;
    }
    
    

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getFechaDescripcion() {
        return fechaDescripcion;
    }

    public void setFechaDescripcion(String fechaDescripcion) {
        this.fechaDescripcion = fechaDescripcion;
    }   

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUrlImagenComuna() {
        return urlImagenComuna;
    }

    public void setUrlImagenComuna(String urlImagenComuna) {
        this.urlImagenComuna = urlImagenComuna;
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

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }
    
    
    

    @Override
    public String toString() {
        return "UserTO{" + "identificador=" + identificador + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", intAct=" + intAct + ", fechaIngreso=" + fechaIngreso + ", fechaDescripcion=" + fechaDescripcion + ", email=" + email + ", password=" + password + ", password2=" + password2 + ", tipo=" + tipo + ", nombreEmpresa=" + nombreEmpresa + ", idComuna=" + idComuna + ", rol=" + rol + ", urlImagenComuna=" + urlImagenComuna + ", latitud=" + latitud + ", longitud=" + longitud + '}';
    }
        
}
