/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.patron.to.CodigoTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rbaeza
 */
public interface ParametrosFacadeLocal {
      public List<CodigoTO> listaCodigos()throws Exception ;
      public List<CodigoTO> listaPorDominio(String dominio)throws Exception ;
      public CodigoTO buscarDominioCodigo(String dominio, String codigo)throws Exception ;
      public CodigoTO buscarDominioCodigo(String dominio, String codigo1,String codigo2)throws Exception ;
      public Date traerFechaHoraActual();
}
