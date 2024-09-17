/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "VISTA_REGIONES_MONITOREO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaRegionesMonitoreoEntity.findAll", query = "SELECT v FROM VistaRegionesMonitoreoEntity v")
    , @NamedQuery(name = "VistaRegionesMonitoreoEntity.findByIdRegion", query = "SELECT v FROM VistaRegionesMonitoreoEntity v WHERE v.idRegion = :idRegion")
    , @NamedQuery(name = "VistaRegionesMonitoreoEntity.findByNombre", query = "SELECT v FROM VistaRegionesMonitoreoEntity v WHERE v.nombre = :nombre")})
public class VistaRegionesMonitoreoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGION")
    @Id
    private BigInteger idRegion;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;

    public VistaRegionesMonitoreoEntity() {
    }

    public BigInteger getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(BigInteger idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
