/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;

import java.time.LocalTime;
import java.util.List;


/**
 *
 * @author chris
 */
public class Gelenk extends Element{
    String typ;
    int nummer;
    int gelenkstellung;
    Long roboterID;

    public Gelenk(String typ, int nummer, int gelenkstellung, Long id, String bezeichnung, String user_Parameter, LocalTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.typ = typ;
        this.nummer = nummer;
        this.gelenkstellung = gelenkstellung;
        this.roboterID = roboterID;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getGelenkstellung() {
        return gelenkstellung;
    }

    public void setGelenkstellung(int gelenkstellung) {
        this.gelenkstellung = gelenkstellung;
    }

    public Long getRoboterID() {
        return roboterID;
    }

    public void setRoboterID(Long roboterID) {
        this.roboterID = roboterID;
    }

    
    
    
    
}