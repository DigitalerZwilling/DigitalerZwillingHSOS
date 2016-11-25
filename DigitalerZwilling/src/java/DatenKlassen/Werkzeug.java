/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;

import java.time.LocalDateTime;


/**
 *
 * @author user
 */
public class Werkzeug extends Element{
    int zustand;
    Long roboterID;
    
    public Werkzeug(Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel, int zustand) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.zustand = zustand;
        this.roboterID = roboterID;
    }

    public int getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }

    public Long getRoboterID() {
        return roboterID;
    }

    public void setRoboterID(Long roboterID) {
        this.roboterID = roboterID;
    }
}
