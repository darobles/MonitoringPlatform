/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author drobles
 */
@Entity
@Table(name = "NODO")
@NamedQueries({
    @NamedQuery(name = "NodoEntity.findAll", query = "SELECT n FROM NodoEntity n"),
    @NamedQuery(name = "NodoEntity.findById", query = "SELECT n FROM NodoEntity n WHERE n.id = :id"),
    @NamedQuery(name = "NodoEntity.findByIdMarca", query = "SELECT n FROM NodoEntity n WHERE n.idMarca = :idMarca"),
    @NamedQuery(name = "NodoEntity.findByIdfabrica", query = "SELECT n FROM NodoEntity n WHERE n.idfabrica = :idfabrica"),
    @NamedQuery(name = "NodoEntity.findByLatitud", query = "SELECT n FROM NodoEntity n WHERE n.latitud = :latitud"),
    @NamedQuery(name = "NodoEntity.findByLongitud", query = "SELECT n FROM NodoEntity n WHERE n.longitud = :longitud"),
    @NamedQuery(name = "NodoEntity.findByNombre", query = "SELECT n FROM NodoEntity n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "NodoEntity.findByOdMatriz", query = "SELECT n FROM NodoEntity n WHERE n.odMatriz = :odMatriz")})
public class NodoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "ID_MARCA")
    private String idMarca;
    @Column(name = "IDFABRICA")
    private BigDecimal idfabrica;
    @Column(name = "LATITUD")
    private BigDecimal latitud;
    @Column(name = "LONGITUD")
    private BigDecimal longitud;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2000)
    @Column(name = "OD_MATRIZ")
    private String odMatriz;
    @Size(max = 2000)
    @Column(name = "MATRIZ_MACRO")
    private String matrizMacro;
    
    public NodoEntity() {
    }

    public NodoEntity(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public BigDecimal getIdfabrica() {
        return idfabrica;
    }

    public void setIdfabrica(BigDecimal idfabrica) {
        this.idfabrica = idfabrica;
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

    public String getOdMatriz() {
        return odMatriz;
    }

    public void setOdMatriz(String odMatriz) {
        this.odMatriz = odMatriz;
    }

    public String getMatrizMacro() {
        return matrizMacro;
    }

    public void setMatrizMacro(String matrizMacro) {
        this.matrizMacro = matrizMacro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodoEntity)) {
            return false;
        }
        NodoEntity other = (NodoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.auter.ejb.dao.NodoEntity[ id=" + id + " ]";
    }
    
}
