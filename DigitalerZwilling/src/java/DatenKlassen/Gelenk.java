/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;

import java.time.LocalDateTime;


/**
 *
 * @author chris
 */
public class Gelenk extends Element{
    private String typ;
    private int nummer;
    private int gelenkstellung;
    private Long roboterID;

    public Gelenk(String typ, int nummer, int gelenkstellung, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.typ = typ;
        this.nummer = nummer;
        this.gelenkstellung = gelenkstellung;
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

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",\n";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        json += "\"typ\": \"" + typ + "\",";
        json += "\"nummer\": " + nummer + ",";
        json += "\"gelenkstellung\": " + gelenkstellung + ",";
        json += "\"roboterID\": " + roboterID;
        json += "}";
        
        return json;
    }
    
}