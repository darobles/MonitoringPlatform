/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "VISTA_APAGADO_DIA_CF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VistaApagadoDiaCfEntity.findAll", query = "SELECT v FROM VistaApagadoDiaCfEntity v"),
    @NamedQuery(name = "VistaApagadoDiaCfEntity.findByDia", query = "SELECT v FROM VistaApagadoDiaCfEntity v WHERE v.dia = :dia"),
    @NamedQuery(name = "VistaApagadoDiaCfEntity.findByIddispositivo", query = "SELECT v FROM VistaApagadoDiaCfEntity v WHERE v.iddispositivo = :iddispositivo")})
public class VistaApagadoDiaCfEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "DIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dia;
    @Column(name = "IDDISPOSITIVO")
    private BigInteger iddispositivo;

    public VistaApagadoDiaCfEntity() {
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public BigInteger getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(BigInteger iddispositivo) {
        this.iddispositivo = iddispositivo;
    }
    
}
