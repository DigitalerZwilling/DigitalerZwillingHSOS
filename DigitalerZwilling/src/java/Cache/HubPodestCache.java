/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.HubPodest;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class HubPodestCache extends Cache{

    @Override
    public void update() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_hubpodest,oben,unten,zeitstempel,user_parameter FROM hubpodest");
        List<String> ids = rsMap.get("id_hubpodest");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> oben = rsMap.get("oben");
        List<String> unten = rsMap.get("unten");
        
        HubPodest hubpodest;
        for (int i=0;i<ids.size();i++){
            hubpodest=(HubPodest)(state==true?elements[0].get(Long.getLong(ids.get(i))):elements[1].get(Long.getLong(ids.get(i))));
            hubpodest.setOben(Boolean.getBoolean(oben.get(i)));
            hubpodest.setUnten(Boolean.getBoolean(unten.get(i)));
            hubpodest.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            hubpodest.setUser_Parameter(user_parameter.get(i));
            //gelenk.setId_Warentraeger(this.readWarentraeger(hubpodest.getId()));
        }
    }

    @Override
    public void updateAll() {
        Map<Long,Element> allHuPo1=new HashMap<>();
        Map<Long,Element> allHuPo2=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_hubpodest,bezeichnung,oben,unten,id_sektor,zeitstempel,user_parameter FROM hubpodest");
        List<String> ids = rsMap.get("id_hubpodest");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> oben = rsMap.get("oben");
        List<String> unten = rsMap.get("unten");
        List<String> sektor = rsMap.get("id_sektor");
        
        HubPodest hupo1,hupo2;
        for (int i=0;i<ids.size();i++){
            hupo1=new HubPodest(Boolean.getBoolean(oben.get(i)),Boolean.getBoolean(unten.get(i)),Long.getLong(sektor.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            hupo2=new HubPodest(Boolean.getBoolean(oben.get(i)),Boolean.getBoolean(unten.get(i)),Long.getLong(sektor.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            
            
            allHuPo1.put(hupo1.getId(),(hupo1));
            allHuPo2.put(hupo2.getId(),(hupo2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allHuPo1;
        m[1]=allHuPo2;
        this.setElements(m);
    }
    

    private static HubPodestCache instance;

    public static synchronized Cache getInstance(){
        if(HubPodestCache.instance == null) {
            HubPodestCache.instance = new HubPodestCache();
        }
        return instance;
    }
    
}
