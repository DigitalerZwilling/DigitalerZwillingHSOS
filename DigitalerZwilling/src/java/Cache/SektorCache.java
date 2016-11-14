/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.Sektor;
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
public class SektorCache extends Cache{

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAll() {
        Map<Long,Element> allSektor1=new HashMap<>();
        Map<Long,Element> allSektor2=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_sektor,stoerung,bezeichnung,oben,unten,id_sektor,zeitstempel,user_parameter from hubpodest");
        List<String> ids = rsMap.get("id_sektor");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> stoerung = rsMap.get("stoerung");  //int
        
        List<String> x = rsMap.get("position_x");   //int
        List<String> y = rsMap.get("position_y");   //int
        List<String> z = rsMap.get("position_z");   //int
        List<String> ausrichtung = rsMap.get("position_ausrichtung");   //int
        
        Sektor sektor1,sektor2;
        for (int i=0;i<ids.size();i++){
            sektor1=new Sektor(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            sektor2=new Sektor(Boolean.getBoolean(oben.get(i)),Boolean.getBoolean(unten.get(i)),Long.getLong(sektor.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            
            
            allSektor1.put(sektor1.getId(),(sektor1));
            allSektor2.put(sektor2.getId(),(sektor2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allSektor1;
        m[1]=allSektor2;
        this.setElements(m);
    }

    private static SektorCache instance;

    public static synchronized Cache getInstance(){
        if(SektorCache.instance == null) {
            SektorCache.instance = new SektorCache();
        }
        return instance;
    }
    
}
