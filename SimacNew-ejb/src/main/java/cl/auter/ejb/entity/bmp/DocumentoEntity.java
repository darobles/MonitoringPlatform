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
import javax.persistence.Table;

/**
 *
 * @author Marco
 */
@Entity
@Table(name="DOCUMENTO")
@NamedQueries({ })
public class DocumentoEntity implements Serializable {
    @Id
    @Column(name = "IDDOC", nullable = false )
    private BigDecimal id;
    @Column(name = "TIPODOC", nullable = false )
    private String tipo;
    @Column(name = "DESCRIPCION", nullable = false )
    private String descripcion;
    @Column(name = "URL", nullable = false )
    private String url;
    @Column(name = "FECDOC", nullable = false )
    private Date fecha;
    @Column(name = "IDCRUCE", nullable = false )
    private BigDecimal idCruce;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }
    
    
    
}
