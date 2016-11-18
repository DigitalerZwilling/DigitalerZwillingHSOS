/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import DatenKlassen.Element;
import DatenKlassen.Transportband;
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
public class TransportbandCache extends Cache{

    @Override
    public void update() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_transportband,zeitstempel,user_parameter,stoerung,geschwindigkeit FROM Transportband");
        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> stoerung = rsMap.get("stoerung");
        List<String> geschwindigkeit = rsMap.get("geschwindigkeit");
        Transportband transportband;
        for (int i=0;i<ids.size();i++){
            transportband=(Transportband)(state==true?elements[0].get(Long.getLong(ids.get(i))):elements[1].get(Long.getLong(ids.get(i))));
            transportband.setStoerung(Integer.valueOf(stoerung.get(i)));
            transportband.setGeschwindigkeit(Integer.valueOf(geschwindigkeit.get(i)));
            transportband.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            transportband.setUser_Parameter(user_parameter.get(i));
        }
        
    }
    
    @Override
    public void updateAll() {
        Map<Long,Element> allTransportband1=new HashMap<>();
        Map<Long,Element> allTransportband2=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_transportband,bezeichnung,zeitstempel,user_parameter,stoerung,laenge,geschwindigkeit,id_sektor_vor,id_sektor_nach FROM Transportband");
        List<String> ids = rsMap.get("id_transportband");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> stoerung = rsMap.get("stoerung");
        List<String> laenge = rsMap.get("laenge");
        List<String> geschwindigkeit = rsMap.get("geschwindigkeit");
        
        List<String> ids_vor = rsMap.get("id_sektor_vor");
        List<String> ids_nach = rsMap.get("id_sektor_nach");
        
        Transportband transportband1,transportband2;
        for (int i=0;i<ids.size();i++){
            transportband1=new Transportband(Integer.valueOf(stoerung.get(i)),Integer.valueOf(laenge.get(i)),Integer.valueOf(geschwindigkeit.get(i)),Long.getLong(ids_vor.get(i)),Long.getLong(ids_nach.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            transportband2=new Transportband(Integer.valueOf(stoerung.get(i)),Integer.valueOf(laenge.get(i)),Integer.valueOf(geschwindigkeit.get(i)),Long.getLong(ids_vor.get(i)),Long.getLong(ids_nach.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            allTransportband1.put(transportband1.getId(),(transportband1));
            allTransportband2.put(transportband2.getId(),(transportband2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allTransportband1;
        m[1]=allTransportband2;
        this.setElements(m);
    }

    private static TransportbandCache instance;

    public static synchronized Cache getInstance(){
        if(TransportbandCache.instance == null) {
            TransportbandCache.instance = new TransportbandCache();
        }
        return instance;
    }
 
}
