/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.patron.to.AddTaskTO;
import cl.auter.patron.to.AtencionTareaTO;
import cl.auter.patron.to.DetalleTareaTO;
import cl.auter.patron.to.EstadisticaTO;
import cl.auter.patron.to.TiempoRespuestaTO;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class SMADAO extends ConexionDAO{
  /*  public BigDecimal paAddTask(AddTaskTO addTaskTO, String idComuna)throws Exception {        
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {            
            conn = getSMAComuna(Integer.parseInt(idComuna));           
            cstmt = conn.prepareCall("{call pa_add_task(?,?,?,?,?,?,?,?,?,?,?)}");  
            cstmt.setBigDecimal(1, addTaskTO.getIdCruce());
            cstmt.setInt(2, addTaskTO.getFunInicial().intValue());
            cstmt.setTimestamp(3, new java.sql.Timestamp(addTaskTO.getFecha().getTime()));
            cstmt.setString(4, addTaskTO.getObservaciones());
            cstmt.setInt(5, addTaskTO.getIdTipoEquipo().intValue());
            cstmt.setInt(6, addTaskTO.getTipoFuente().intValue());
            cstmt.setInt(7, addTaskTO.getIdgrupo().intValue());
            cstmt.setString(8, addTaskTO.getNombreFuente());
            cstmt.setInt(9, addTaskTO.getTecnico().intValue());
            cstmt.setString(10, addTaskTO.getUrl());
            cstmt.setString(11, addTaskTO.getMail());            
            cstmt.executeQuery();              
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                           Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    cstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }        
        return buscarTicket(idComuna);
    }   
    
    public BigDecimal buscarTicket(String idComuna)throws Exception {
        BigDecimal ticket = BigDecimal.ONE;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {            
            conn = getSMAComuna(Integer.parseInt(idComuna));           
            pstmt = conn.prepareCall("SELECT MAX(idtarea) FROM TAREAS");                 
            rs = pstmt.executeQuery();            
            if(rs.next()){
                ticket = rs.getBigDecimal(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return ticket;        
    }
    
    public DetalleTareaTO obtenerDetalleTarea(BigDecimal idTarea)throws Exception {
        DetalleTareaTO detalleTareaTO = new DetalleTareaTO();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {            
            conn = getSMAComuna(0);           
            pstmt = conn.prepareCall("select idtarea,tipoequipo,funcionamientiini,idcruce,fecha_creacion_str," +
                                     " obspreliminar,tareaterminada,informadopor,nombrefuente,fecha_cierre_str"+
                                     " from vista_tarea_web where idtarea = ?");        
            pstmt.setInt(1, idTarea.intValue());                  
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            if(rs.next()){
                detalleTareaTO.setIdTarea(rs.getBigDecimal(1));
                detalleTareaTO.setTipoEquipo(rs.getString(2));
                detalleTareaTO.setFuncionamientiIni(rs.getString(3));
                detalleTareaTO.setIdCruce(rs.getBigDecimal(4));
                detalleTareaTO.setFechaCreacionStr(rs.getString(5));
                detalleTareaTO.setObsPreliminar(rs.getString(6));
                detalleTareaTO.setTareaTerminada(rs.getString(7));
                detalleTareaTO.setInformadoPor(rs.getString(8));
                detalleTareaTO.setNombreFuente(rs.getString(9));
                detalleTareaTO.setFechaCierreStr(rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return detalleTareaTO;
    }
    public List<AtencionTareaTO> obtenerAtencionTarea(BigDecimal idTarea) {
        List<AtencionTareaTO> listaAtencionTareaTO = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {            
            conn = getSMAComuna(0);           
            pstmt = conn.prepareCall("select distinct idatencion,func_al_llegar,func_al_salir,observacioncliente," +
                                     "fecha_llegada,fecha_salida,estado_atencion" +
                                     " from vista_atencion_web where idtarea=? order by fecha_llegada");        
            pstmt.setInt(1, idTarea.intValue());                  
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            
            while(rs.next()){
                AtencionTareaTO atencionTareaTO = new AtencionTareaTO();
                atencionTareaTO.setIdAtencion(rs.getBigDecimal(1));
                atencionTareaTO.setFuncAlLlegar(rs.getString(2));
                atencionTareaTO.setFuncAlSalir(rs.getString(3));
                atencionTareaTO.setObservacionCliente(rs.getString(4));
                atencionTareaTO.setFechaLlegada(rs.getString(5));
                atencionTareaTO.setFechaSalida(rs.getString(6));
                atencionTareaTO.setEstadoAtencion(rs.getString(7));
                listaAtencionTareaTO.add(atencionTareaTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return listaAtencionTareaTO;
    }
    
    public List<EstadisticaTO> obtenerEstadistica(BigDecimal idCruce,String idComuna, Date fechaDesde, Date fechaHasta)throws Exception {
        List<EstadisticaTO> listaEstadisticaTO = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try { 
            sql = "select w.tipoequipo as 'TipodeEquipo',w.funcionamientiini as 'TipoFalla',count(w.idtarea) as 'cantidad',AVG(datediff(mi, l.fecha_pri_llegada ,w.fechacierre))/60 as 'TiempoRespuesta'" +
                  " from vista_tarea_web w,cruce c,VISTA_TAREA_LLEGADA l" +
                  " where w.idcruce=c.idcruce" +
                  " and w.idtarea=l.idtarea" +
                  " and w.fechacreacion >= convert(varchar(10), ?, 103) + ' 00:00:00'" +
                  " AND w.fechacreacion < convert(varchar(10), ?, 103) + ' 23:59:59'" +
                  " and c.idcomuna = ?";             
            if(!idCruce.equals(BigDecimal.ZERO)){sql = sql + " AND c.IDCRUCE = ?";}                  
            sql = sql + " group by w.tipoequipo,w.funcionamientiini order by w.tipoequipo,w.funcionamientiini";           
            conn = getSMAComuna(Integer.parseInt(idComuna));           
            pstmt = conn.prepareCall(sql); 
            pstmt.setString(1, new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde));
            pstmt.setString(2, new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta));
            pstmt.setString(3, idComuna);
            if(!idCruce.equals(BigDecimal.ZERO)){pstmt.setInt(4, idCruce.intValue());}
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            while(rs.next()){
                EstadisticaTO estadisticaTO = new EstadisticaTO();
                estadisticaTO.setNombreEquipo(rs.getString(1));
                estadisticaTO.setTipoFalla(rs.getString(2));
                estadisticaTO.setCantidad(rs.getInt(3));
                estadisticaTO.setTiempoRespuesta(rs.getBigDecimal(4));
                listaEstadisticaTO.add(estadisticaTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return listaEstadisticaTO;
    }
    
     public List<TiempoRespuestaTO> obtenerTiemposRespuesta(Date fechaDesde, Date fechaHasta)throws Exception {
        List<TiempoRespuestaTO> estadistica = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try { 
            sql = "SELECT SUM(DATEDIFF(minute,w.fechacreacion, l.fecha_pri_llegada))/COUNT(*) AS tiempo_respuesta, COUNT(*) AS incidencias, co.nombre, c.idcomuna " +
                    "from vista_tarea_web w,cruce c, VISTA_TAREA_LLEGADA l, comuna co " +
                    "where w.idcruce=c.idcruce " +
                    "and w.idtarea=l.idtarea " +
                    "AND co.idcomuna = c.idcomuna " +
                    "and w.fechacreacion >= convert(varchar(10), ? , 103) + ' 00:00:00' " +
                    "AND w.fechacreacion < convert(varchar(10), ? , 103) + ' 23:59:59' " +
                    "GROUP BY c.idcomuna, co.nombre " +
                    "ORDER BY 1 desc";                    
            conn = getSMAComuna(1);           
            pstmt = conn.prepareCall(sql); 
            pstmt.setString(1, new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde));
            pstmt.setString(2, new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta));
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            while(rs.next()){
                TiempoRespuestaTO estadisticaTO = new TiempoRespuestaTO();
                estadisticaTO.setId_comuna(rs.getInt("IDCOMUNA"));
                estadisticaTO.setNombre(rs.getString("NOMBRE"));
                estadisticaTO.setTiempo_respuesta(rs.getInt("TIEMPO_RESPUESTA"));
                estadisticaTO.setIncidencias(rs.getInt("INCIDENCIAS"));
                estadistica.add(estadisticaTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return estadistica;
    }
    
     public List<AtencionTareaTO> obtenerDetalleAtencion(String nombreEquipo,String tipoFalla,BigDecimal idCruce,String idComuna, Date fechaDesde, Date fechaHasta)throws Exception {
        List<AtencionTareaTO> listaAtencionTareaTO = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try { 
            sql = "select w.idtarea,convert(varchar(10), c.idcruce) + ' - ' + REPLACE(c.calle1,'*',' ')  + ' CON ' + c.calle2 as 'Ubicacion', w.tipoequipo, w.funcionamientiini, w.fechacreacion,l.fecha_pri_llegada,w.fechacierre,w.obspreliminar " +
                  " from vista_tarea_web w,cruce c, VISTA_TAREA_LLEGADA l" +
                  " where w.idcruce=c.idcruce" +
                  " and w.idtarea=l.idtarea" +
                  " and w.fechacreacion >= convert(varchar(10), ?, 103) + ' 00:00:00'" +
                  " AND w.fechacreacion < convert(varchar(10), ?, 103) + ' 23:59:59'" +
                  " and c.idcomuna = ?" +            
                  " and w.tipoequipo=?" +
                  " and w.funcionamientiini=?";
            if(!idCruce.equals(BigDecimal.ZERO)){sql = sql + " AND c.IDCRUCE = ?";}
            sql = sql + " order by w.tipoequipo,w.funcionamientiini";                       
            conn = getSMAComuna(Integer.parseInt(idComuna));           
            pstmt = conn.prepareCall(sql); 
            pstmt.setString(1, new SimpleDateFormat("dd-MM-yyyy").format(fechaDesde));
            pstmt.setString(2, new SimpleDateFormat("dd-MM-yyyy").format(fechaHasta));
            pstmt.setString(3, idComuna);
            pstmt.setString(4, nombreEquipo);
            pstmt.setString(5, tipoFalla);            
            if(!idCruce.equals(BigDecimal.ZERO)){pstmt.setInt(6, idCruce.intValue());}
            pstmt.executeQuery();
            rs = pstmt.getResultSet();            
            while(rs.next()){
                AtencionTareaTO atencionTareaTO = new AtencionTareaTO();
                atencionTareaTO.setIdAtencion(rs.getBigDecimal(1));
                atencionTareaTO.setUbicacion(rs.getString(2));
                atencionTareaTO.setTipoEquipo(rs.getString(3));
                atencionTareaTO.setFuncAlLlegar(rs.getString(4));
                atencionTareaTO.setFechaLlegada(rs.getString(5));
                atencionTareaTO.setFechaSalida(rs.getString(6));
                atencionTareaTO.setFechaCierre(rs.getString(7));
                atencionTareaTO.setObservacionCliente(rs.getString(8));
                if(atencionTareaTO.getObservacionCliente() == null){
                    atencionTareaTO.setObservacionCliente("Sin información");
                }
                listaAtencionTareaTO.add(atencionTareaTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (rs != null){
                    try {
                            rs.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
            try {
                    pstmt.close();
            } catch (SQLException ex) {
                    // TODO Controlar exception
                    Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
            }                          
            if (conn != null){
                    try {
                            conn.close();
                    } catch (SQLException ex) {
                            // TODO Controlar exception
                            Logger.getLogger(SMADAO.class.getName()).log(Level.SEVERE, null, ex);
                    }       
            }
        }
        return listaAtencionTareaTO;
    }
*/
}
