/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.session.stateless.facade;

import cl.auter.daos.TerminalRemotoDAO;
import cl.auter.datos.Dimac;
import cl.auter.datos.ConsultaDimac;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author drobles
 */
@Stateless
public class TerminalRemotoSB implements TerminalRemotoSBLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    int end = 0x03;

    @Override
    public List<ConsultaDimac> enviaComandos(List<ConsultaDimac> consultas) {
        //Guarda BD
        TerminalRemotoDAO dao = new TerminalRemotoDAO();
        //Carga los ids de consulta
        consultas = dao.guardaComandos(consultas);
        return consultas;

    }

    @Override
    public List<ConsultaDimac> consultaRespuesta(int idDispositivo) {
        //Consulta todas las respuestas para un dispositivo especifico
        TerminalRemotoDAO dao = new TerminalRemotoDAO();
        return dao.getRespuestas(idDispositivo);
    }
    
    @Override
    public List<ConsultaDimac> consultaRespuestaIdCons(List<ConsultaDimac> consultas) {
       TerminalRemotoDAO dao = new TerminalRemotoDAO();
       return dao.getRespuestasConsultas(consultas); 
    }

    @Override
    public Dimac getDimac(int idDimac) {
        TerminalRemotoDAO dao = new TerminalRemotoDAO();
        Dimac dimac = dao.getDimac(idDimac);
        return dimac;
    }
    
    @Override
    public void eliminaConsulta(List<ConsultaDimac> consultas){
        TerminalRemotoDAO dao = new TerminalRemotoDAO();
        dao.setStatusConsulta(consultas);
    }
    
    @Override
    public List<ConsultaDimac> consultaRespuestaManRem(List<ConsultaDimac> consultas) {
       TerminalRemotoDAO dao = new TerminalRemotoDAO();
       return dao.getRespuestaManRem(consultas); 
    }
}
