/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import DatenKlassen.Element;
import DatenKlassen.Warentraeger;
import DatenbankSchnittestelle.Datenbankschnittstelle;
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
 * @author User
 */
@ApplicationScoped
public class WarentraegerCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    @Override
    public void update() {
        
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger, stoerung, zeitstempel, user_parameter, abstand_mm, montagezustand, RFID_inhalt");
        List<String> ids = rsMap.get("id_warentraeger");
        List<String> stoerung = rsMap.get("stoerung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> abstand_mm = rsMap.get("abstand_mm");
        List<String> montagezustand = rsMap.get("montagezustand");
        List<String> RFID_inhalt = rsMap.get("RFID_inhalt");
        
        Warentraeger warentraeger;
        for (int i=0;i<ids.size();i++){
            warentraeger = (Warentraeger)(state==true ? elements[0].get(Long.getLong(ids.get(i))) : elements[1].get(Long.getLong(ids.get(i))));                 //andersrum als bei getById
            
            warentraeger.setStoerung(Integer.getInteger(stoerung.get(i)));
            warentraeger.setZeitstempel(LocalTime.parse(zeitstempel.get(i)));
            warentraeger.setUser_Parameter(user_parameter.get(i));
            warentraeger.setAbstand_mm(Integer.getInteger(abstand_mm.get(i)));
            warentraeger.setMontagezustand(Integer.getInteger(montagezustand.get(i)));
            warentraeger.setrFID_inhalt(RFID_inhalt.get(i));
            warentraeger.setTransportbandIDs(this.readTransportband(Long.getLong(ids.get(i))));
            warentraeger.setTransportbandIDs(this.readTransportband(Long.getLong(ids.get(i))));
            
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        Map<Long,Element> allWarentraeger1=new HashMap<>();
        Map<Long,Element> allWarentraeger2=new HashMap<>();
        
        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger , bezeichnung , stoerung , zeitstempel, user_parameter, abstand_mm, montagezustand, RFID_inhalt from Artikel");
        List<String> ids = rsMap.get("id_warentraeger");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> stoerung = rsMap.get("stoerung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> abstand_mm = rsMap.get("abstand_mm");
        List<String> montagezustand = rsMap.get("montagezustand");
        List<String> RFID_inhalt = rsMap.get("RFID_inhalt");
        
        
        Warentraeger warentraeger1,warentraeger2;
        for (int i=0;i<ids.size();i++){
            warentraeger1 = new Warentraeger(Integer.getInteger(stoerung.get(i)), Integer.getInteger(abstand_mm.get(i)), Integer.getInteger(montagezustand.get(i)), RFID_inhalt.get(i), Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            warentraeger2 = new Warentraeger(Integer.getInteger(stoerung.get(i)), Integer.getInteger(abstand_mm.get(i)), Integer.getInteger(montagezustand.get(i)), RFID_inhalt.get(i), Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            warentraeger1.setTransportbandIDs(this.readTransportband(warentraeger1.getId()));
            warentraeger2.setTransportbandIDs(this.readTransportband(warentraeger2.getId()));
            
            warentraeger1.setSektorIDs(this.readSektor(warentraeger1.getId()));
            warentraeger2.setSektorIDs(this.readSektor(warentraeger2.getId()));
            
            allWarentraeger1.put(warentraeger1.getId(),(warentraeger1));
            allWarentraeger2.put(warentraeger2.getId(),(warentraeger2));
        }
        Map<Long,Element>[] elements = new Map[2];
        elements[0]=allWarentraeger1;
        elements[1]=allWarentraeger2;
        this.setElements(elements);
    }
    
    private List<Long> readTransportband(Long id){
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband from Transportband_Warentraeger where id_warentraeger="+id);
        List<String> ids = rsMap.get("id_transportband");
        List<Long> idsLong= new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readSektor(Long id){
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor from Sektor_Transportband where id_warentraeger="+id);
        List<String> ids = rsMap.get("id_sektor");
        List<Long> idsLong= new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
}
