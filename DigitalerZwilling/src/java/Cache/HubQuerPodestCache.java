/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.HubQuerPodest;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author User
 */
@ApplicationScoped
public class HubQuerPodestCache extends Cache{

    @Override
    public void update() {
        //--------------------------------------------------------------
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel FROM Hubquerpodest");
        List<String> id = rsMap.get("id_Hubquerpodest");
        List<String> userParameter = rsMap.get("user_parameter");
        List<String> motor = rsMap.get("motor");
        List<String> oben = rsMap.get("oben");
        List<String> mittig = rsMap.get("mittig");
        List<String> unten = rsMap.get("unten");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        for(int i = 0; i<id.size();i++){
            HubQuerPodest huQu = (HubQuerPodest) (state==true?elements[0].get(Long.getLong(id.get(i))):elements[0].get(Long.getLong(id.get(i))));
            huQu.setUser_Parameter(userParameter.get(i));
            huQu.setMotor(Boolean.getBoolean(motor.get(i)));
            huQu.setOben(Boolean.getBoolean(oben.get(i)));
            huQu.setMittig(Boolean.getBoolean(mittig.get(i)));
            huQu.setUnten(Boolean.getBoolean(unten.get(i)));
            huQu.setZeitstempel(LocalTime.parse(zeitstempel.get(i)));
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel, bezeichnung, id_sektor FROM Hubquerpodest");
        List<String> id = rsMap.get("id_Hubquerpodest");
        List<String> userParameter = rsMap.get("user_parameter");
        List<String> motor = rsMap.get("motor");
        List<String> oben = rsMap.get("oben");
        List<String> mittig = rsMap.get("mittig");
        List<String> unten = rsMap.get("unten");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> sektorId = rsMap.get("id_sektor");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        for(int i = 0; i<id.size();i++){
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();
            elements[0].put(Long.getLong(id.get(i)), new HubQuerPodest(Boolean.getBoolean(motor.get(i)), Boolean.getBoolean(oben.get(i)), Boolean.getBoolean(mittig.get(i)), Boolean.getBoolean(unten.get(i)), Long.getLong(sektorId.get(i)), Long.getLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalTime.parse(zeitstempel.get(i))));
            elements[1].put(Long.getLong(id.get(i)), new HubQuerPodest(Boolean.getBoolean(motor.get(i)), Boolean.getBoolean(oben.get(i)), Boolean.getBoolean(mittig.get(i)), Boolean.getBoolean(unten.get(i)), Long.getLong(sektorId.get(i)), Long.getLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalTime.parse(zeitstempel.get(i))));
        }
        updateGruppenIds();
    }
   
    private void updateGruppenIds(){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_Hubquerpodest1, id_hubquerpodest2 FROM Hubquerpodest_Hubquerpodest");
        List<String> id1 = rsMap.get("id_Hubquerpodest1");
        List<String> id2 = rsMap.get("id_Hubquerpodest2");
        for(int i=0;i<id1.size();i++){
            HubQuerPodest podest = HubQuerPodest.class.cast(elements[0].get(Long.getLong(id1.get(i))));
            podest.getGruppenIDs().add(Long.getLong(id2.get(i)));
        }
    }
}
