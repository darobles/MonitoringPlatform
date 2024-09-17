/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author drobles
 */
public class Conexion {

    public Connection getConectionOracle() {
        try {
            String strDSName = "java:jboss/georeferenciaDS";
            Context ctx = new InitialContext();
            DataSource ds = (javax.sql.DataSource) ctx.lookup(strDSName);
            return ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
