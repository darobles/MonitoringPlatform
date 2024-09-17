
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
import javax.persistence.Table;

@Entity
@Table(name="CODIGO")    
@NamedQueries({ 
                @NamedQuery(name = "CodigoEntity.buscaPorDominio",
                            query = "SELECT o FROM CodigoEntity o WHERE o.dominio=:dominio order by o.codigo1,o.codigo2 asc"),
                @NamedQuery(name = "CodigoEntity.buscaPorDominioCodigo",
                            query = "SELECT o FROM CodigoEntity o WHERE o.dominio=:dominio and o.codigo1=:codigo1"),
                @NamedQuery(name = "CodigoEntity.buscaPorDominioCodigos",
                            query = "SELECT o FROM CodigoEntity o WHERE o.dominio=:dominio and o.codigo1=:codigo1 and o.codigo2=:codigo2")
              })

public class CodigoEntity implements Serializable {
   
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  @Column(name = "IDCODIGO" , nullable = false)  
  private BigDecimal idCodigo;
 
  @Column(name = "DOMINIO" )
  private String dominio;
  @Column(name = "CODIGO1" )
  private String codigo1;  
  @Column(name = "CODIGO2" )
  private String codigo2;    
  @Column(name = "DESCRIPCION" )
  private String descripcion;   

    public BigDecimal getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(BigDecimal idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getCodigo1() {
        return codigo1;
    }

    public void setCodigo1(String codigo1) {
        this.codigo1 = codigo1;
    }

    public String getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(String codigo2) {
        this.codigo2 = codigo2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
  
  
  
}
