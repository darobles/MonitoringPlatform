/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Marco
 */
@Entity
@Table(name="ROL_USUARIO")
@SequenceGenerator(name="SEQ_ROL_USUARIO_GEN",sequenceName="SEQ_ROL_USUARIO", initialValue=1, allocationSize=1)
@NamedQueries({ 
    @NamedQuery(name = "RolUsuarioEntity.buscarRolUsuario",query = "SELECT o FROM RolUsuarioEntity o WHERE o.idUsuario = :idUsuario")
})
public class RolUsuarioEntity implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ROL_USUARIO_GEN")
    @Column(name = "IDE_REG", nullable = false )
    private BigDecimal id;
    @Column(name = "IDE_ROL", nullable = false )
    private String idRol;
    @Column(name = "IDE_USR", nullable = false )
    private String idUsuario;
    @Column(name = "OPCION", nullable = false )
    private String opcion;

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }  
}
