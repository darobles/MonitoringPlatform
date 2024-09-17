package cl.auter.util;

import cl.auter.ejb.dao.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
/**
     * Realiza el formato de la fecha 
     * @param fechaString
     * @return 
     */
    public static String retornaFechaFormateadaString (String fechaString){
        String fechaIni= fechaString;
        String ano=fechaIni.substring(0,4);	
        String mes=fechaIni.substring(5,7);
        String dia=fechaIni.substring(8,10);
        String hora = fechaIni.substring(10, 19);
        fechaString=dia+"-"+mes+"-"+ano+""+hora;
        return fechaString;
    }
    public static String agregarEspacios(String campo){
        String valorConEspacios="";
        if (campo !=null && campo.length()<70){
            valorConEspacios=campo;
            for (int i=0;i<(70 - campo.length());i++){
               valorConEspacios=valorConEspacios+" &nbsp;"; 
            }
        }
        return valorConEspacios;
    }
    public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    public static String fechaToString(Date fecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSalida = "";
        fechaSalida = formatoDelTexto.format(fecha);
        return fechaSalida;                
    }
    
    public static String fechaHoraToString(Date fecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaSalida = "";
        fechaSalida = formatoDelTexto.format(fecha);
        return fechaSalida;                
    }
        
    public static Date fechaToDate(String fecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaSalida = null;
        try {
            fechaSalida = formatoDelTexto.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaSalida;                
    }
    
    
}
