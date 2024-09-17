/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.patron.to;

/**
 *
 * @author Daniel
 */
public class VistaApagadosUltMesTO {

    private int id_region;
    private String city_name;
    private int id_city;
    private int num_turn_off;

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getId_city() {
        return id_city;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }

    public int getNum_turn_off() {
        return num_turn_off;
    }

    public void setNum_turn_off(int num_turn_off) {
        this.num_turn_off = num_turn_off;
    }

    @Override
    public String toString() {
        return "VistaApagadosUltMes{" + "id_region=" + id_region + ", city_name=" + city_name + ", id_city=" + id_city + ", num_turn_off=" + num_turn_off + '}';
    }
    
}
