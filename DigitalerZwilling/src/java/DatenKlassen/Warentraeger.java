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
 * @author user
 */
public class Warentraeger extends Element{

    private int stoerung;
    private int abstand_mm;
    private int montagezustand;
    private String rFID_inhalt;
    private List<Long> artikelIDs;
    private List<Long> transportbandIDs;
    private List<Long> sektorIDs;

    public Warentraeger(int stoerung, int abstand_mm, int montagezustand, String rFID_inhalt, Long id, String bezeichnung, String user_Parameter, LocalTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.abstand_mm = abstand_mm;
        this.montagezustand = montagezustand;
        this.rFID_inhalt = rFID_inhalt;
    }

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getAbstand_mm() {
        return abstand_mm;
    }

    public void setAbstand_mm(int abstand_mm) {
        this.abstand_mm = abstand_mm;
    }

    public int getMontagezustand() {
        return montagezustand;
    }

    public void setMontagezustand(int montagezustand) {
        this.montagezustand = montagezustand;
    }

    public String getrFID_inhalt() {
        return rFID_inhalt;
    }

    public void setrFID_inhalt(String rFID_inhalt) {
        this.rFID_inhalt = rFID_inhalt;
    }

    public List<Long> getArtikelIDs() {
        return artikelIDs;
    }

    public void setArtikelIDs(List<Long> artikelIDs) {
        this.artikelIDs = artikelIDs;
    }

    public List<Long> getTransportbandIDs() {
        return transportbandIDs;
    }

    public void setTransportbandIDs(List<Long> transportbandIDs) {
        this.transportbandIDs = transportbandIDs;
    }

    public List<Long> getSektorIDs() {
        return sektorIDs;
    }

    public void setSektorIDs(List<Long> sektorIDs) {
        this.sektorIDs = sektorIDs;
    }
}
