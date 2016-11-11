/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Gelenk;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chr
 */

//in bearbeitung
public class GelenkCache extends Cache{

    @Override
    public void update() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_gelenk,gelenkstellung,zeitstempel,user_parameter from Gelenk");
        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Gelenk gelenk;
        for (int i=0;i<ids.size();i++){
            gelenk=Gelenk.class.cast(this.getById(Long.getLong(ids.get(i))));
            gelenk.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            gelenk.setUser_Parameter(user_parameter.get(i));
            //gelenk.setId_Warentraeger(this.readWarentraeger(gelenk.getId()));
        }
    }

    @Override
    public void updateAll() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_gelenk,typ,nummer,gelenkstellung,zeitstempel,user_parameter from Artikel");
        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Gelenk gelenk;
        for (int i=0;i<ids.size();i++){
            gelenk=Gelenk.class.cast(this.getById(Long.getLong(ids.get(i))));
            gelenk.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            gelenk.setUser_Parameter(user_parameter.get(i));
            //gelenk.setId_Warentraeger(this.readWarentraeger(gelenk.getId()));
        }
    }

    private static GelenkCache instance;

    public static synchronized Cache getInstance(){
        if(GelenkCache.instance == null) {
            GelenkCache.instance = new GelenkCache();
        }
        return instance;
    }

    
    
}
