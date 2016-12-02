/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;


import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author user
 */
public class Roboter extends Element{

    private int stoerung;
    private int x;
    private int y;
    private int z;
    private int ausrichtung;
    private List<Long> sektorID;
    private List<Long> gelenkeIDs;
    private List<Long> werkzeugID;

    public Roboter(int stoerung, int x, int y, int z, int ausrichtung, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ausrichtung = ausrichtung;

    }

    

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getAusrichtung() {
        return ausrichtung;
    }

    public void setAusrichtung(int ausrichtung) {
        this.ausrichtung = ausrichtung;
    }

    public List<Long> getId_Sektor() {
        return sektorID;
    }

    public void setId_Sektor(List<Long> id_Sektor) {
        this.sektorID = id_Sektor;
    }

    public List<Long> getId_Gelenke() {
        return gelenkeIDs;
    }

    public void setId_Gelenke(List<Long> id_Gelenke) {
        this.gelenkeIDs = id_Gelenke;
    }

    public List<Long> getId_Werkzeug() {
        return werkzeugID;
    }

    public void setId_Werkzeug(List<Long> id_Werkzeug) {
        this.werkzeugID = id_Werkzeug;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",\n";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",\n";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",\n";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",\n";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",\n";
        json += "\"stoerung\": " + stoerung + ",\n";
        json += "\"x\": " + x + ",\n";
        json += "\"y\": " + y + ",\n";
        json += "\"z\": " + z + ",\n";
        json += "\"ausrichtung\": " + ausrichtung + ",\n";
        
        json += "\"IsektorIDs\": \n{";
        for(int i=0;i<sektorID.size();i++){
            json += "\"" + i + "\": " + sektorID.get(i);
            if(i < (sektorID.size()-1))
                json += ",";
            
            json += '\n';
        }
        json += "},\n";
        
        json += "\"gelenkeIDs\": \n{";
        for(int i=0;i<gelenkeIDs.size();i++){
            json += "\"" + i + "\": " + gelenkeIDs.get(i);
            if(i < (gelenkeIDs.size()-1))
                json += ",";
            
            json += '\n';
        }
        json += "},\n";
        
        json += "\"werzeugIDs\": \n{";
        for(int i=0;i<werkzeugID.size();i++){
            json += "\"" + i + "\": " + werkzeugID.get(i);
            if(i < (werkzeugID.size()-1))
                json += ",";
            
            json += '\n';
        }
        json += "}\n}";
        
        return json;
    }    
    
}
