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
public class Transportband extends Element{
    
    
    private int stoerung;
    private int laenge;
    private int geschwindigkeit;
    private List<Long> warentraegerIDs;
    private Long vorSektorID;
    private Long nachSektorID;

    public Transportband(int stoerung, int laenge, int geschwindigkeit, Long vorSektorID, Long nachSektorID, Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
        this.stoerung = stoerung;
        this.laenge = laenge;
        this.geschwindigkeit = geschwindigkeit;
        this.vorSektorID = vorSektorID;
        this.nachSektorID = nachSektorID;
    }

    public int getStoerung() {
        return stoerung;
    }

    public void setStoerung(int stoerung) {
        this.stoerung = stoerung;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public List<Long> getWarentraegerIDs() {
        return warentraegerIDs;
    }

    public void setWarentraegerIDs(List<Long> warentraegerIDs) {
        this.warentraegerIDs = warentraegerIDs;
    }

    public Long getVorSektorID() {
        return vorSektorID;
    }

    public void setVorSektorID(Long vorSektorID) {
        this.vorSektorID = vorSektorID;
    }

    public Long getNachSektorID() {
        return nachSektorID;
    }

    public void setNachSektorID(Long nachSektorID) {
        this.nachSektorID = nachSektorID;
    }

    @Override
    public String toJson() {
        String json = new String();
        json += '{';
        json += "\"id\": " + id + ",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"user_Parameter\": \"" + user_Parameter + "\",";
        json += "\"bezeichnung\": \"" + bezeichnung + "\",";
        json += "\"zeitstempel\": \"" + zeitstempel.toString() + "\",";
        json += "\"stoerung\": " + stoerung + ",";
        json += "\"laenge\": " + laenge + ",";
        json += "\"geschwindigkeit\": " + geschwindigkeit + ",";
        
        json += "\"warentraegerIDs\": {";
        for(int i=0;i<warentraegerIDs.size();i++){
            json += warentraegerIDs.get(i);
            if(i < (warentraegerIDs.size()-1)){
                json += ",";
            }
        }
        json += "},";
        
        json += "\"vorSektorID\": " + vorSektorID + ",";
        json += "\"nachSektorID\": " + nachSektorID;
        json += '}';
        
        return json;
    }
    
}
