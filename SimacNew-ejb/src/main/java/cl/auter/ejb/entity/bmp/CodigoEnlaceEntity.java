
package cl.auter.ejb.entity.bmp;

import java.io.Serializable;
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
                @NamedQuery(name = "CodigoEnlaceEntity.buscaPorDominio",
                            query = "SELECT o FROM CodigoEnlaceEntity o WHERE o.dominio=:dominio order by o.codigo1,o.codigo2"),
                @NamedQuery(name = "CodigoEnlaceEntity.buscaPorDominioCodigo",
                            query = "SELECT o FROM CodigoEnlaceEntity o WHERE o.dominio=:dominio and o.codigo1=:codigo1"),
                @NamedQuery(name = "CodigoEnlaceEntity.buscaPorDominioCodigos",
                            query = "SELECT o FROM CodigoEnlaceEntity o WHERE o.dominio=:dominio and o.codigo1=:codigo1 and o.codigo2=:codigo2")
              })

public class CodigoEnlaceEntity implements Serializable {
   
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID_CODIGO" , nullable = false)  
  private Integer idCodigo;
 
  @Column(name = "DOMINIO" )
  private String dominio;
  @Column(name = "CODIGO1" )
  private String codigo1;  
  @Column(name = "CODIGO2" )
  private String codigo2;    
  @Column(name = "DESCRIPCION" )
  private String descripcion;   

    public int getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
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
