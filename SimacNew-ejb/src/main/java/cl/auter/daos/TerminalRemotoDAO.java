/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.daos;

import cl.auter.conexion.Conexion;
import cl.auter.datos.Dimac;
import cl.auter.datos.ConsultaDimac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drobles
 */
public class TerminalRemotoDAO {

    public List<ConsultaDimac> guardaComandos(List<ConsultaDimac> consultas) {
        Connection conn = null;
        Conexion bd = new Conexion();
        try {
            conn = bd.getConectionOracle();
            PreparedStatement stmt = null;
            try {
                String query = "INSERT INTO TELNETGEMALTO(idconsulta,iddispositivo, CMD, FECHACMD, TIPO,USUARIO) VALUES(?,?,?,SYSDATE,?,?)";
                stmt = conn.prepareStatement(query);
                for (ConsultaDimac consulta : consultas) {

                    consulta = getID_Consulta(consulta);
                    stmt.setBigDecimal(1, consulta.getIdconsulta());
                    stmt.setBigDecimal(2, consulta.getIddispositivo());
                    stmt.setString(3, consulta.getCmd());
                    stmt.setString(4, consulta.getTipo());
                    stmt.setString(5, consulta.getUsuario());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            } catch (SQLException e) {
                Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, e);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return consultas;
    }

    public List<ConsultaDimac> getRespuestas(int id_dispositivo) {
        List<ConsultaDimac> respuestas = new ArrayList();
        Conexion bd = new Conexion();
        Connection conn = bd.getConectionOracle();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            //Consulta por todas las respuestas asociadas a un dispositivo que no han sido informadas
            rs = stmt.executeQuery("select t.id_consulta, t.fecha, respuesta, informado\n"
                    + "  from ter_respuesta t, ter_consulta c\n"
                    + " where c.id_consulta = t.id_consulta\n"
                    + "   and c.id_dispositivo =" + id_dispositivo + "\n"
                    + "   and t.REVISADO = 0");
            while (rs.next()) {
                ConsultaDimac respuesta = new ConsultaDimac();
                respuesta.setIdconsulta(rs.getBigDecimal("ID_CONSULTA"));
                respuesta.setRevisado(rs.getBigDecimal("REVISADO"));
                respuesta.setRespuesta(rs.getString("RESPUESTA"));
                respuesta.setFecharespuesta(rs.getTimestamp("FECHARESPUESTA"));
                respuestas.add(respuesta);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuestas;
    }

    //Cambia el status de leido de la respuesta
    public void setLeidas(List<ConsultaDimac> respuestas) {

    }

    public ConsultaDimac getID_Consulta(ConsultaDimac consulta) {
        Conexion bd = new Conexion();
        Connection conn = bd.getConectionOracle();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT seq_telnetGemalto.nextval FROM DUAL");
            while (rs.next()) {
                consulta.setIdconsulta(rs.getBigDecimal(1));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return consulta;
    }

    public Dimac getDimac(int idDimac) {
        Conexion bd = new Conexion();
        Dimac dimac = new Dimac();
        Connection conn = bd.getConectionOracle();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select iddispositivo, imei, dirip, dyndns, ubicacion, numserie, comentario, activo, fechaact, estadooperativo, valmodo220, valmodoups, valmodoutc1, valmodolam, ind_ups, valmodoutc2, valmodoutc3, valmodoutc4, numtel, ind_otu from dispositivo where iddispositivo = " + idDimac);
            while (rs.next()) {
                dimac.setIdDispositivo(rs.getInt("iddispositivo"));
                dimac.setImei(rs.getString("imei"));
                dimac.setDirip(rs.getString("dirip"));
                dimac.setUbicacion(rs.getString("ubicacion"));
                dimac.setNumserie(rs.getString("numserie"));
                dimac.setComentario(rs.getString("comentario"));
                dimac.setActivo(rs.getString("activo"));
                dimac.setFechaact(rs.getDate("fechaact"));
                dimac.setEstadooperativo(rs.getInt("estadooperativo"));
                dimac.setValModo220(rs.getInt("valmodo220"));
                dimac.setValModoUps(rs.getInt("valmodoups"));
                dimac.setValModoUtc1(rs.getInt("valmodoutc1"));
                dimac.setValModoLam(rs.getInt("valmodolam"));
                dimac.setInd_ups(rs.getString("ind_ups"));
                dimac.setValModoUtc1(rs.getInt("valmodoutc2"));
                dimac.setValModoUtc1(rs.getInt("valmodoutc3"));
                dimac.setValModoUtc1(rs.getInt("valmodoutc4"));
                dimac.setInd_otu(rs.getString("ind_otu"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dimac;
    }

    public void setStatusConsulta(List<ConsultaDimac> consultas) {
        Connection conn = null;
        Conexion bd = new Conexion();
        try {
            conn = bd.getConectionOracle();
            PreparedStatement stmt = null;
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                String query = "update TELNETGEMALTO set status= -1 where IDCONSULTA = ?";
                stmt = conn.prepareStatement(query);
                for (ConsultaDimac consulta : consultas) {
                    stmt.setBigDecimal(1, consulta.getIdconsulta());
                    stmt.addBatch();
                }
                stmt.executeBatch();

            } catch (SQLException e) {
                Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, e);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public List<ConsultaDimac> getRespuestasConsultas(List<ConsultaDimac> consultas) {
        List<ConsultaDimac> respuestas = new ArrayList();
        Conexion bd = new Conexion();
        Connection conn = bd.getConectionOracle();
        Statement stmt;
        ResultSet rs;
        try {
            for (ConsultaDimac consulta : consultas) {
                stmt = conn.createStatement();
                //Consulta por todas las respuestas asociadas a un dispositivo que no han sido informadas
                String query = "select cmd,respuesta, fecharespuesta, revisado from telnetgemalto where idconsulta = " + consulta.getIdconsulta() + " and respuesta != ' '";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ConsultaDimac respuesta = new ConsultaDimac();
                    respuesta.setIdconsulta(consulta.getIdconsulta());
                    respuesta.setCmd(consulta.getCmd());
                    respuesta.setFechacmd(consulta.getFechacmd());
                    respuesta.setIddispositivo(consulta.getIddispositivo());
                    respuesta.setRevisado(rs.getBigDecimal("REVISADO"));
                    respuesta.setRespuesta(rs.getString("RESPUESTA"));
                    respuesta.setFecharespuesta(rs.getTimestamp("fecharespuesta"));
                    respuestas.add(respuesta);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuestas;
    }
    
    public List<ConsultaDimac> getRespuestaManRem(List<ConsultaDimac> consultas) {
        List<ConsultaDimac> respuestas = new ArrayList();
        Conexion bd = new Conexion();
        Connection conn = bd.getConectionOracle();
        Statement stmt;
        ResultSet rs;
        try {
            for (ConsultaDimac consulta : consultas) {
                stmt = conn.createStatement();
                //Consulta por todas las respuestas asociadas a un dispositivo que no han sido informadas
                String query = "select cmd,fechacmd,respuesta, fecharespuesta, revisado,sysdate from telnetgemalto where idconsulta = " + consulta.getIdconsulta() ;
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ConsultaDimac respuesta = new ConsultaDimac();
                    respuesta.setIdconsulta(consulta.getIdconsulta());
                    respuesta.setCmd(consulta.getCmd());
                    respuesta.setFechacmd(rs.getTimestamp("fechacmd"));
                    respuesta.setIddispositivo(consulta.getIddispositivo());
                    respuesta.setRevisado(rs.getBigDecimal("REVISADO"));
                    respuesta.setRespuesta(rs.getString("RESPUESTA"));
                    respuesta.setFecharespuesta(rs.getTimestamp("fecharespuesta"));
                    respuesta.setHoraBD(rs.getTimestamp("SYSDATE"));
                    respuestas.add(respuesta);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TerminalRemotoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return respuestas;
    }
   
}
