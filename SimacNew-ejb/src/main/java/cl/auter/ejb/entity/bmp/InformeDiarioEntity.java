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

@Entity
@Table(name="VISTA_INFORME_DIARIO")   
@NamedQueries({ 
                @NamedQuery(name = "InformeDiarioEntity.informeDiario",
                            query = "SELECT o FROM InformeDiarioEntity o WHERE o.idComuna = :idComuna and o.idCruce = :idCruce and o.fechaLlegada between to_date(:fechaDesde,'dd-mm-yyyy hh24:mi:ss') and to_date(:fechaHasta,'dd-mm-yyyy hh24:mi:ss') order by o.fechaLlegada asc"),
                @NamedQuery (name = "InformeDiarioEntity.informeDiarioSinCruce",
                            query = "SELECT o FROM InformeDiarioEntity o WHERE o.idComuna = :idComuna and o.fechaLlegada between to_date(:fechaDesde,'dd-mm-yyyy hh24:mi:ss') and to_date(:fechaHasta,'dd-mm-yyyy hh24:mi:ss') order by o.fechaLlegada asc")
                }        
)

public class InformeDiarioEntity implements Serializable{
   
    @Column(name = "IDATENCION")
    @Id
    private BigDecimal idAtencion;                        
    @Column(name = "IDCRUCE")
    private BigDecimal idCruce;                        
    @Column(name = "ID_COMUNA")
    private BigDecimal idComuna;                        
    @Column(name = "FECHALLEGADA")
    private Date fechaLlegada;
    @Column(name = "ESTADOINICIAL")	
    private String estadoInicial;
    @Column(name = "ESTADOFINAL")	
    private String estadoFinal;
    @Column(name = "ESTADOTAREA")
    private String estadoTarea;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "FECHASALIDA")
    private Date fechaSalida;                        
    @Column(name = "NOMBRETIPOORIGEN")
    private String nombreTipoOrigen;
    @Column(name = "UBICACION")
    private String ubicacion;
     @Column(name = "LLAMADA")
    private String llamada;


    public BigDecimal getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(BigDecimal idAtencion) {
        this.idAtencion = idAtencion;
    }

    public BigDecimal getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(BigDecimal idCruce) {
        this.idCruce = idCruce;
    }

    public BigDecimal getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(BigDecimal idComuna) {
        this.idComuna = idComuna;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombreTipoOrigen() {
        return nombreTipoOrigen;
    }

    public void setNombreTipoOrigen(String nombreTipoOrigen) {
        this.nombreTipoOrigen = nombreTipoOrigen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getLlamada() {
        return llamada;
    }

    public void setLlamada(String llamada) {
        this.llamada = llamada;
    }

    @Override
    public String toString() {
        return "InformeDiarioEntity{" + "idAtencion=" + idAtencion + ", idCruce=" + idCruce + ", idComuna=" + idComuna + ", fechaLlegada=" + fechaLlegada + ", estadoInicial=" + estadoInicial + ", estadoFinal=" + estadoFinal + ", estadoTarea=" + estadoTarea + ", observacion=" + observacion + ", fechaSalida=" + fechaSalida + ", nombreTipoOrigen=" + nombreTipoOrigen + ", ubicacion=" + ubicacion + ", llamada=" + llamada + '}';
    }
    
    

}
