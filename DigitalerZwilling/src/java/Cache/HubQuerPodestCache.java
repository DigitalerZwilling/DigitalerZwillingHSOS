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
import javax.inject.Inject;

/**
 *
 * @author User
 */

@ApplicationScoped
public class HubQuerPodestCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    
    @Override
    public void update() {
        //--------------------------------------------------------------

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel FROM Hubquerpodest");

        List<String> id = rsMap.get("ID_HUBQUERPODEST");
        List<String> userParameter = rsMap.get("USER_PARAMETER");
        List<String> motor = rsMap.get("MOTOR");
        List<String> oben = rsMap.get("OBEN");
        List<String> mittig = rsMap.get("MITTIG");
        List<String> unten = rsMap.get("UNTEN");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        for(int i = 0; i<id.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            HubQuerPodest huQu = (HubQuerPodest) (state==true?elements[0].get(Long.parseLong(id.get(i))):elements[0].get(Long.parseLong(id.get(i))));
            huQu.setUser_Parameter(userParameter.get(i));
            huQu.setMotor(Long.parseLong(motor.get(i))!=0);
            huQu.setOben(Long.parseLong(oben.get(i))!=0);
            huQu.setMittig(Long.parseLong(mittig.get(i))!=0);
            huQu.setUnten(Long.parseLong(unten.get(i))!=0);
            huQu.setZeitstempel(LocalTime.parse(zeitstempel.get(i)));
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel, bezeichnung, id_sektor FROM Hubquerpodest");

        List<String> id = rsMap.get("ID_HUBQUERPODEST");
        List<String> userParameter = rsMap.get("USER_PARAMETER");
        List<String> motor = rsMap.get("MOTOR");
        List<String> oben = rsMap.get("OBEN");
        List<String> mittig = rsMap.get("MITTIG");
        List<String> unten = rsMap.get("UNTEN");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> sektorId = rsMap.get("ID_SEKTOR");
        List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        for(int i = 0; i<id.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            elements[0].put(Long.parseLong(id.get(i)), new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalTime.parse(zeitstempel.get(i))));
            elements[1].put(Long.parseLong(id.get(i)), new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalTime.parse(zeitstempel.get(i))));
        }
        updateGruppenIds();
    }
   
    private void updateGruppenIds(){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest1, id_hubquerpodest2 FROM Hubquerpodest_Hubquerpodest");

        List<String> id1 = rsMap.get("ID_HUBQUERPODEST1");
        List<String> id2 = rsMap.get("ID_HUBQUERPODEST2");
        for(int i=0;i<id1.size();i++){
            HubQuerPodest podest = HubQuerPodest.class.cast(elements[0].get(Long.parseLong(id1.get(i))));
            podest.getGruppenIDs().add(Long.parseLong(id2.get(i)));
        }
    }
}
