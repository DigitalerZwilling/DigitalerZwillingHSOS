/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenKlassen;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



/**
 *
 * @author chris
 */


public class Artikel extends Element{
    
    private List<Long> warentraegerIDs;
    
    public Artikel(Long id, String bezeichnung, String user_Parameter, LocalDateTime zeitstempel) {
        super(id, bezeichnung, user_Parameter, zeitstempel);
    }

    public List<Long> getId_Warentraeger() {
        return warentraegerIDs;
    }

    public void setId_Warentraeger(List<Long> id_Warentraeger) {
        this.warentraegerIDs = id_Warentraeger;
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
        
        json += "\"warentraegerIDs\": \n{";
        for(int i=0;i<warentraegerIDs.size();i++){
            json += "\"" + i + "\": " + warentraegerIDs.get(i);
            if(i < (warentraegerIDs.size()-1))
                json += ",";
            
            json += '\n';
        }
        json += "}\n}";
        
        return json;
    }
    
    
}
    
    
    

    