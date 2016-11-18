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
import java.util.ArrayList;
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
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_sektor,stoerung,zeitstempel,user_parameter from Sektor");
        List<String> ids = rsMap.get("id_sektor");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> stoerung = rsMap.get("stoerung");  //int
        Sektor sektor;
        for (int i=0;i<ids.size();i++){
            sektor=(Sektor)(state==true?elements[0].get(Long.getLong(ids.get(i))):elements[1].get(Long.getLong(ids.get(i))));
            sektor.setStoerung(Integer.getInteger(stoerung.get(i)));
            sektor.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            sektor.setUser_Parameter(user_parameter.get(i));
            
            sektor.setWarentraegerIDs(this.readWarentraeger(sektor.getId()));
            sektor.setRoboterIDs(this.readRoboter(sektor.getId()));
            
        }
    }

    @Override
    public void updateAll() {
        Map<Long,Element> allSektor1=new HashMap<>();
        Map<Long,Element> allSektor2=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_sektor,stoerung,position_x,position_y,position_z,position_ausrichtung,bezeichnung,zeitstempel,user_parameter from Sektor");
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
            sektor2=new Sektor(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            sektor1.setWarentraegerIDs(this.readWarentraeger(sektor1.getId()));
            sektor2.setWarentraegerIDs(this.readWarentraeger(sektor2.getId()));
            
            sektor1.setNachTransportbandIDs(this.readNachTransportband(sektor1.getId()));
            sektor2.setNachTransportbandIDs(this.readNachTransportband(sektor2.getId()));
            
            sektor1.setVorTransportbandIDs(this.readVorTransportband(sektor1.getId()));
            sektor2.setVorTransportbandIDs(this.readVorTransportband(sektor2.getId()));
            
            sektor1.setSensorIDs(this.readSensor(sektor1.getId()));
            sektor2.setSensorIDs(this.readSensor(sektor2.getId()));
            
            sektor1.setRoboterIDs(this.readRoboter(sektor1.getId()));
            sektor2.setRoboterIDs(this.readRoboter(sektor2.getId()));
            
            sektor1.setHubpodestIDs(this.readHubPodest(sektor1.getId()));
            sektor2.setHubpodestIDs(this.readHubPodest(sektor2.getId()));
            
            sektor1.setHubquerpodestIDs(this.readQuerHubPodest(sektor1.getId()));
            sektor2.setHubquerpodestIDs(this.readQuerHubPodest(sektor2.getId()));
            
            allSektor1.put(sektor1.getId(),(sektor1));
            allSektor2.put(sektor2.getId(),(sektor2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allSektor1;
        m[1]=allSektor2;
        this.setElements(m);
    }
    
    private List<Long> readWarentraeger(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_warentraeger from Sektor_Warentraeger where id_sektor="+id);
        List<String> ids = rsMap.get("id_warentraeger");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readVorTransportband(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_transportband from Transportband where id_sektor_nach="+id);
        List<String> ids = rsMap.get("id_transportband");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    private List<Long> readNachTransportband(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_transportband from Transportband where id_sektor_vor="+id);
        List<String> ids = rsMap.get("id_transportband");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    private List<Long> readSensor(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_sensor from Sensor where id_sektor="+id);
        List<String> ids = rsMap.get("id_sensor");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    private List<Long> readHubPodest(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_hubpodest from Hubpodest where id_sektor="+id);
        List<String> ids = rsMap.get("id_hubpodest");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    private List<Long> readQuerHubPodest(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_hubquerpodest from Hubquerpodest where id_sektor="+id);
        List<String> ids = rsMap.get("id_hubquerpodest");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }
    private List<Long> readRoboter(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_roboter from Roboter_Sektor where id_sektor="+id);
        List<String> ids = rsMap.get("id_roboter");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.getLong(s));
        }
        return idsLong;
    }

    private static SektorCache instance;

    public static synchronized Cache getInstance(){
        if(SektorCache.instance == null) {
            SektorCache.instance = new SektorCache();
        }
        return instance;
    }
    
}
