/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.datos.Dimac;
import cl.auter.datos.ConsultaDimac;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author drobles
 */
@Local
public interface TerminalRemotoSBLocal {
    
    public List<ConsultaDimac> enviaComandos(List<ConsultaDimac> consultas);
    
    public List<ConsultaDimac> consultaRespuesta(int idDispositivo);

    public List<ConsultaDimac> consultaRespuestaIdCons(List<ConsultaDimac> consultas);
    
    public Dimac getDimac(int idDimac);
    
    public void eliminaConsulta(List<ConsultaDimac> consultas);
    
    public List<ConsultaDimac> consultaRespuestaManRem(List<ConsultaDimac> consultas);
    
}
