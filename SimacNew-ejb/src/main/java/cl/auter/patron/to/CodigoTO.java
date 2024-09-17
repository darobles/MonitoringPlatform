
package cl.auter.patron.to;

import java.math.BigDecimal;



public class CodigoTO {

   private BigDecimal idCodigo;
   private String dominio;
   private String codigo1;  
   private String codigo2;    
   private String descripcion;
   
   //Se utiliza para manejar tabla permiso
   private String estilo;
   private boolean selecionado;
   //

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

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }  

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public String toString() {
        return "CodigoTO{" + "idCodigo=" + idCodigo + ", dominio=" + dominio + ", codigo1=" + codigo1 + ", codigo2=" + codigo2 + ", descripcion=" + descripcion + ", estilo=" + estilo + ", selecionado=" + selecionado + '}';
    }
    
}
