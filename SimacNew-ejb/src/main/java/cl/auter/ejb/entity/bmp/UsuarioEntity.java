/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Marco
 */
@Entity
@Table(name="USUARIO")
@NamedQueries({
    @NamedQuery(name = "UsuarioEntity.buscarUsuarioPorId",query = "SELECT o FROM UsuarioEntity o WHERE o.identificador = :identificador order by o.identificador asc"),
    @NamedQuery(name = "UsuarioEntity.buscarUsuarioPorIdComuna",query = "SELECT o FROM UsuarioEntity o WHERE o.idComuna = :idComuna order by o.identificador asc"),
    @NamedQuery(name = "UsuarioEntity.buscarUsuario",query = "SELECT o FROM UsuarioEntity o order by o.identificador asc")
})
public class UsuarioEntity implements Serializable{
    private String identificador;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String intAct;
    private Date fechaIngreso;
    private String email;
    private String password;
    private BigDecimal tipo;
    private String nombreEmpresa;
    private BigDecimal idComuna;
    private String rol;
    private String latitud;
    private String longitud;
    
    @Id
    @Column(name = "IDE_USR", nullable = false )
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    @Column(name = "NOM_USR", nullable = false )
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Column(name = "APE_PAT", nullable = false )
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    @Column(name = "APE_MAT", nullable = false )
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    @Column(name = "IND_ACT", nullable = false )
    public String getIntAct() {
        return intAct;
    }

    public void setIntAct(String intAct) {
        this.intAct = intAct;
    }
    @Column(name = "FEC_ING", nullable = false )
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    @Column(name = "EMA_USR", nullable = false )
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "PAS_USR", nullable = false )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "TIPO", nullable = false )
    public BigDecimal getTipo() {
        return tipo;
    }

    public void setTipo(BigDecimal tipo) {
        this.tipo = tipo;
    }
    @Column(name = "NOM_EMP", nullable = false )
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    @Column(name = "IDCOMUNA", nullable = false )
    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }   
    @Column(name = "ROL", nullable = false )
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    @Column(name = "LATITUD", nullable = false )
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    @Column(name = "LONGITUD", nullable = false )
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    
    
    
}
