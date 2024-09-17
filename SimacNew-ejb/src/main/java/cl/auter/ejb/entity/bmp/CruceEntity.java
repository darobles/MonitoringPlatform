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

@Entity
@Table(name = "CRUCE")
@SequenceGenerator(name="SEQ_CRUCE_SIMULADO",sequenceName="SEQ_CRUCE_SIMULADO", initialValue=1000000, allocationSize=1)
@NamedQueries({
    @NamedQuery(name = "CruceEntity.buscaPorComuna", query = "SELECT o FROM CruceEntity o WHERE o.idComuna = :idComuna order by o.idcruce")
    ,   @NamedQuery(name = "CruceEntity.buscaPorDimac", query = "SELECT o FROM CruceEntity o WHERE o.idDispositivo = :idDispositivo order by o.idcruce")
    ,   @NamedQuery(name = "CruceEntity.buscaTodos", query = "SELECT o FROM CruceEntity o order by o.idcruce asc")
    ,   @NamedQuery(name = "CruceEntity.buscarCrucePorId", query = "SELECT o FROM CruceEntity o WHERE o.idcruce = :idCruce ")
})
public class CruceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CRUCE_SIMULADO")
    @Column(name = "IDCRUCE", nullable = false)
    private BigDecimal idcruce;
    @Column(name = "CALLE1", nullable = false)
    private String calle1;
    @Column(name = "CALLE2", nullable = false)
    private String calle2;
    @Column(name = "LATITUD", nullable = false)
    private String latitud;
    @Column(name = "LONGITUD", nullable = false)
    private String longitud;
    @Column(name = "IDDISPOSITIVO", nullable = false)
    private BigDecimal idDispositivo;
    @Column(name = "IDCOMUNA", nullable = false)
    private BigDecimal idComuna;
    @Column(name = "CONTROLADOR", nullable = false)
    private String controlador;
    @Column(name = "RED", nullable = false)
    private String red;
    @Column(name = "JUNCTION", nullable = false)
    private String junction;
    @Column(name = "NUM_BIT_UTC", nullable = false)
    private BigDecimal numBitUtc;
    @Column(name = "PPR")
    private BigDecimal ppr;
    @Column(name = "DIRACTMANUAL")
    private BigDecimal dirActManual;
    @Column(name = "IMAGEN")
    private String imagen;

    public BigDecimal getIdcruce() {
        return idcruce;
    }

    public void setIdcruce(BigDecimal idcruce) {
        this.idcruce = idcruce;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
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

    public BigDecimal getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(BigDecimal idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getJunction() {
        return junction;
    }

    public void setJunction(String junction) {
        this.junction = junction;
    }

    public BigDecimal getNumBitUtc() {
        return numBitUtc;
    }

    public void setNumBitUtc(BigDecimal numBitUtc) {
        this.numBitUtc = numBitUtc;
    }

    public BigDecimal getPpr() {
        return ppr;
    }

    public void setPpr(BigDecimal ppr) {
        this.ppr = ppr;
    }

    public BigDecimal getDirActManual() {
        return dirActManual;
    }

    public void setDirActManual(BigDecimal dirActManual) {
        this.dirActManual = dirActManual;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "CruceEntity{" + "idcruce=" + idcruce + ", calle1=" + calle1 + ", calle2=" + calle2 + ", latitud=" + latitud + ", longitud=" + longitud + ", idDispositivo=" + idDispositivo + ", idComuna=" + idComuna + ", controlador=" + controlador + ", red=" + red + ", junction=" + junction + ", numBitUtc=" + numBitUtc + ", ppr=" + ppr + ", dirActManual=" + dirActManual + ", imagen=" + imagen + '}';
    }

}
