package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVIL")
public class MovilEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "IDMOVIL" , nullable = false)
    private BigDecimal idMovil;
    @Column(name = "CODIGOMOVIL" )
    private String codigoMovil;
    @Column(name = "LATITUD" )
    private String latitud;
    @Column(name = "LONGITUD" )
    private String longitud;
    @Column(name = "FECULTLEC")
    private Date fecultLec;
    @Column(name = "TIPO" )
    private String tipo;

    public BigDecimal getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(BigDecimal idMovil) {
        this.idMovil = idMovil;
    }

    public String getCodigoMovil() {
        return codigoMovil;
    }

    public void setCodigoMovil(String codigoMovil) {
        this.codigoMovil = codigoMovil;
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

    public Date getFecultLec() {
        return fecultLec;
    }

    public void setFecultLec(Date fecultLec) {
        this.fecultLec = fecultLec;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
  
}
