/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Marco
 */
public class ConexionDAO {
/*
    Connection getJNDIConnection() {
        String DATASOURCE_CONTEXT = "java:jboss/auterDS";
        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            if (initialContext == null) {

            }
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource.");
            }
        } catch (NamingException ex) {
            System.out.println("Cannot get connection: " + ex);
        } catch (SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }
    
        Connection getConTDV() {
        String DATASOURCE_CONTEXT = "java:jboss/tdv";
        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            if (initialContext == null) {

            }
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource.");
            }
        } catch (NamingException ex) {
            System.out.println("Cannot get connection: " + ex);
        } catch (SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }

    Connection getSMAComuna(int idComuna) {

        String DATASOURCE_CONTEXT = "java:jboss/auterDS";

        if (idComuna >= 10000 && idComuna < 20000) {

        } else if (idComuna >= 20000 && idComuna < 30000) {

        } else if (idComuna >= 30000 && idComuna < 40000) {

        } else if (idComuna >= 40000 && idComuna < 50000) {

        } else if (idComuna >= 50000 && idComuna < 60000) {

        } else if (idComuna >= 60000 && idComuna < 70000) {
            DATASOURCE_CONTEXT = "java:jboss/auterDSRancagua";
        } else if (idComuna >= 70000 && idComuna < 80000) {

        } else if (idComuna >= 80000 && idComuna < 90000) {

        } else if (idComuna >= 90000 && idComuna < 100000) {

        } else if (idComuna >= 100000 && idComuna < 110000) {

        } else if (idComuna >= 110000 && idComuna < 120000) {

        } else if (idComuna >= 120000 && idComuna < 130000) {

        } else {

        }

        Connection result = null;
        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource.");
            }
        } catch (NamingException ex) {
            System.out.println("Cannot get connection: " + ex);
        } catch (SQLException ex) {
            System.out.println("Cannot get connection: " + ex);
        }
        return result;
    }
*/
}
