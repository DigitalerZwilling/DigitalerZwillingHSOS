/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.Werkzeug;
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
public class WerkzeugCache extends Cache{

    @Override
    public void update() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_werkzeug,zeitstempel,user_parameter,zustand FROM Werkzeug");
        List<String> ids_w = rsMap.get("id_werkzeug");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> zustand = rsMap.get("zustand");
        Werkzeug werkzeug;
        for (int i=0;i<ids_w.size();i++){
            werkzeug=(Werkzeug)(state==true?elements[0].get(Long.getLong(ids_w.get(i))):elements[1].get(Long.getLong(ids_w.get(i))));                 //andersrum als bei getById
            werkzeug.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            werkzeug.setUser_Parameter(user_parameter.get(i));
            werkzeug.setZustand(Integer.valueOf(zustand.get(i)));
        }
    }

    @Override
    public void updateAll() {
        Map<Long,Element> allWerkzeug1=new HashMap<>();
        Map<Long,Element> allWerkzeug2=new HashMap<>();
        
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_werkzeug,bezeichnung,zeitstempel,user_parameter,zustand FROM Werkzeug");
        List<String> ids = rsMap.get("id_werkzeug");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> zustand = rsMap.get("zustand");
        
        Werkzeug werkzeug1,werkzeug2;
        for (int i=0;i<ids.size();i++){
            werkzeug1=new Werkzeug(Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)),Integer.valueOf(zustand.get(i)));
            werkzeug2=new Werkzeug(Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)),Integer.valueOf(zustand.get(i)));
            
            werkzeug1.setRoboterID(this.readRoboter(werkzeug1.getId()));
            werkzeug2.setRoboterID(this.readRoboter(werkzeug2.getId()));
            
            allWerkzeug1.put(werkzeug1.getId(),(werkzeug1));
            allWerkzeug2.put(werkzeug2.getId(),(werkzeug2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allWerkzeug1;
        m[1]=allWerkzeug2;
        this.setElements(m);
    }

    Long readRoboter(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_roboter FROM Roboter_Werkzeug WHERE id_werkzeug="+id+" ");
        List<String> ids = rsMap.get("id_roboter");
        Long r_ids=null;
        for (String s : ids){
            r_ids=Long.getLong(s);
        }
        return r_ids;
    }
    
    private static WerkzeugCache instance;

    public static synchronized Cache getInstance(){
        if(WerkzeugCache.instance == null) {
            WerkzeugCache.instance = new WerkzeugCache();
        }
        return instance;
    }
}
