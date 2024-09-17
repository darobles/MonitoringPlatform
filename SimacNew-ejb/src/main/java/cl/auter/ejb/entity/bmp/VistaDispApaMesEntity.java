/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "VISTA_DISP_APAGADOS_ULT_MES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaDispApaMesEntity.findAll", query = "SELECT v FROM VistaDispApaMesEntity v order by v.numApagados desc"),
    @NamedQuery(name = "VistaDispApaMesEntity.findByIdregion", query = "SELECT v FROM VistaDispApaMesEntity v WHERE v.idregion = :idregion"),
    @NamedQuery(name = "VistaDispApaMesEntity.findByNombre", query = "SELECT v FROM VistaDispApaMesEntity v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "VistaDispApaMesEntity.findByIdcomuna", query = "SELECT v FROM VistaDispApaMesEntity v WHERE v.idcomuna = :idcomuna"),
    @NamedQuery(name = "VistaDispApaMesEntity.findByNumApagados", query = "SELECT v FROM VistaDispApaMesEntity v WHERE v.numApagados = :numApagados")})
public class VistaDispApaMesEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "IDREGION")
    private BigInteger idregion;
    @Size(max = 500)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "IDCOMUNA")
    @Id
    private BigInteger idcomuna;
    @Column(name = "NUM_APAGADOS")
    private BigInteger numApagados;

    public VistaDispApaMesEntity() {
    }

    public BigInteger getIdregion() {
        return idregion;
    }

    public void setIdregion(BigInteger idregion) {
        this.idregion = idregion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(BigInteger idcomuna) {
        this.idcomuna = idcomuna;
    }

    public BigInteger getNumApagados() {
        return numApagados;
    }

    public void setNumApagados(BigInteger numApagados) {
        this.numApagados = numApagados;
    }
    
}
