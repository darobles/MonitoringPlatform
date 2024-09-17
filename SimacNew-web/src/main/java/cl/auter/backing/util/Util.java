package cl.auter.backing.util;

import cl.auter.patron.to.EmailTO;
import cl.auter.patron.to.SolicitudTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static int random(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String retornaFechaActualFormato() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    public static String retornaMes(String fecha) {
        //   10/04/2015
        String mes = fecha.substring(3, 5);
        return mes;
    }

    public static String retornaAno(String fecha) {
        //   10/04/2015
        String mes = fecha.substring(6, 10);
        return mes;
    }

    public static String deDateAString(Date date) {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    public static String deSqlDateAString(java.sql.Date date) {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(date);
        return s;
    }

    public static String retornaHoraActualFormato() {
        DateFormat dateFormatHora = new SimpleDateFormat("HHmm");
        Date date2 = new Date();
        String hora = dateFormatHora.format(date2);
        return hora;
    }

    public static String retornaFechaFormateadaString(String fechaString) {
        String fechaIni = fechaString;
        String ano = fechaIni.substring(0, 4);
        String mes = fechaIni.substring(4, 6);
        String dia = fechaIni.substring(6, 8);
        fechaString = dia + "-" + mes + "-" + ano;
        return fechaString;
    }

    public static String retornaHoraFormateadaString(String horaString) {
        String horaIni = horaString;
        String hora = horaIni.substring(0, 2);
        String min = horaIni.substring(2, 4);
        horaString = hora + ":" + min;
        return horaString;
    }

    public static void mailCreacion(EmailTO emailTO, SolicitudTO solicitudTO) { //No funciona la cuentta gmail
     /*   HtmlEmail emailH = new HtmlEmail();
        emailH.setHostName("smtp.googlemail.com");
        emailH.setSmtpPort(465);
        emailH.setSSLOnConnect(true);
        emailH.setAuthenticator(new DefaultAuthenticator(emailTO.getCuenta(), emailTO.getClave()));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            emailH.addTo(solicitudTO.getEmailSolicitante(), solicitudTO.getNombreSolicitante());
            emailH.setFrom(emailTO.getCuenta(), "AUTOMATICA Y REGULACION S.A");
            emailH.setSubject("INGRESO SOLICITUD AUTER S.A");
            emailH.setHtmlMsg(replaceMail(emailTO.getContenido(), dateFormat.format(solicitudTO.getFechaIngreso()), solicitudTO.getIdSolicitud().toString()));
            emailH.setTextMsg("Your email client does not support HTML messages");
            emailH.send();
        } catch (EmailException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }

    public static String replaceMail(String mail, String fecha, String id) {
        return mail.replace("_ID_", id).replace("_FECHA-HORA_", fecha);
    }

    /**
     * Metodo para crear un archvio con entra en memoria
     *
     * @param destino
     * @param nombreArchivo
     * @param in
     * @return
     */
    public static boolean copyFile(String destino, String nombreArchivo, InputStream in) {
        try {
            System.out.println("Destino " + destino + nombreArchivo + " " + in.available());
            OutputStream out = new FileOutputStream(new File(destino + nombreArchivo));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean copyFile(String destino, String nombreArchivo, byte[] in) {
        try (FileOutputStream fos = new FileOutputStream(new File(destino + nombreArchivo))) {
            fos.write(in);            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     * En el caso de error la clave por defecto es : 123
     *
     * @param input
     * @return
     */
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] enc = md.digest();
            String md5Sum = Base64.getEncoder().encodeToString(enc);
            return md5Sum;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ICy5YqxZB1uWSwcVLSNLcA==";
    }

    public static boolean deleteFile(String origen) {
        try {
            File archivo = new File(origen);
            return archivo.delete();
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
