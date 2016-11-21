/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.Gelenk;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author chr
 */

//in bearbeitung
@ApplicationScoped
public class GelenkCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    @Override
    public void update() {

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_gelenk,gelenkstellung,zeitstempel,user_parameter from Gelenk");

        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> gelenkstellung = rsMap.get("gelenkstellung");
        Gelenk gelenk;
        for (int i=0;i<ids.size();i++){
            gelenk=(Gelenk)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
            gelenk.setGelenkstellung(Integer.valueOf(gelenkstellung.get(i)));
            gelenk.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            gelenk.setUser_Parameter(user_parameter.get(i));
            //gelenk.setId_Warentraeger(this.readWarentraeger(gelenk.getId()));
        }
    }

    @PostConstruct @Override
    public void updateAll() {
        Map<Long,Element> allGelenk1=new HashMap<>();
        Map<Long,Element> allGelenk2=new HashMap<>();

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_gelenk,bezeichnung,typ,nummer,gelenkstellung,zeitstempel,user_parameter from Artikel");

        List<String> ids = rsMap.get("id_gelenk");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> typ = rsMap.get("typ");
        List<String> nummer = rsMap.get("nummer");
        List<String> gelenkstellung = rsMap.get("gelenkstellung");
        
        Gelenk gelenk1,gelenk2;
        for (int i=0;i<ids.size();i++){
            gelenk1=new Gelenk(typ.get(i),Integer.valueOf(nummer.get(i)),Integer.valueOf(gelenkstellung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            gelenk2=new Gelenk(typ.get(i),Integer.valueOf(nummer.get(i)),Integer.valueOf(gelenkstellung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            gelenk1.setRoboterID(this.readRoboter(gelenk1.getId()));
            gelenk2.setRoboterID(this.readRoboter(gelenk2.getId()));
            
            allGelenk1.put(gelenk1.getId(),(gelenk1));
            allGelenk2.put(gelenk2.getId(),(gelenk2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allGelenk1;
        m[1]=allGelenk2;
        this.setElements(m);
    }
    Long readRoboter(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT roboter_id from Gelenk where id_gelenk="+id+" ");

        List<String> ids = rsMap.get("id_roboter");
        Long r_ids=null;
        for (String s : ids){
            r_ids=Long.parseLong(s);
        }
        return r_ids;
    }

    /*
    private static GelenkCache instance;

    public static synchronized Cache getInstance(){
        if(GelenkCache.instance == null) {
            GelenkCache.instance = new GelenkCache();
        }
        return instance;
    }
    */
    
    
}
