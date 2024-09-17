package cl.auter.patron.to;

import java.io.Serializable;

public class MonitoreoTO implements Serializable{
    private String cruce;
    private String calleUno;
    private String calleDos;        
    private String ultimaRevision;        
    private String estado;  

    public String getCruce() {
        return cruce;
    }

    public void setCruce(String cruce) {
        this.cruce = cruce;
    }

    public String getCalleUno() {
        return calleUno;
    }

    public void setCalleUno(String calleUno) {
        this.calleUno = calleUno;
    }

    public String getCalleDos() {
        return calleDos;
    }

    public void setCalleDos(String calleDos) {
        this.calleDos = calleDos;
    }

    public String getUltimaRevision() {
        return ultimaRevision;
    }

    public void setUltimaRevision(String ultimaRevision) {
        this.ultimaRevision = ultimaRevision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
