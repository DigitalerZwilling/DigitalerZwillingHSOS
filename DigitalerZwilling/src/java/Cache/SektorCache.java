/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.Sektor;
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
public class SektorCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    @Override
    public void update() {
        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor,stoerung,zeitstempel,user_parameter FROM Sektor");
        List<String> ids = rsMap.get("ID_SEKTOR");
        List<String> zeitstempel = rsMap.get("ZEITSTEMEPL");
        List<String> user_parameter = rsMap.get("USER_PARAMETER");
        List<String> stoerung = rsMap.get("STOERUNG");  //int
        Sektor sektor;
        for (int i=0;i<ids.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            sektor=(Sektor)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
            sektor.setStoerung(Integer.getInteger(stoerung.get(i)));
            sektor.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            sektor.setUser_Parameter(user_parameter.get(i));
            
            sektor.setWarentraegerIDs(this.readWarentraeger(sektor.getId()));
            sektor.setRoboterIDs(this.readRoboter(sektor.getId()));
            
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        Map<Long,Element> allSektor1=new HashMap<>();
        Map<Long,Element> allSektor2=new HashMap<>();

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor,stoerung,position_x,position_y,position_z,position_ausrichtung, int ausrichtung,bezeichnung,zeitstempel,user_parameter FROM Sektor");

        List<String> ids = rsMap.get("ID_SEKTOR");
        List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> user_parameter = rsMap.get("USER_PARAMETER");
        
        List<String> stoerung = rsMap.get("STOERUNG");  //int
        
        List<String> x = rsMap.get("POSITION_X");   //int
        List<String> y = rsMap.get("POSITION_Y");   //int
        List<String> z = rsMap.get("POSITION_Z");   //int
        List<String> ausrichtung = rsMap.get("POSITION_AUSRICHTUNG");   //int
        
        Sektor sektor1,sektor2;
        for (int i=0;i<ids.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            sektor1=new Sektor(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            sektor2=new Sektor(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
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

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT warentraeger_id FROM Sektor_Warentraeger WHERE id_sektor="+id);

        List<String> ids = rsMap.get("ID_WARENTRAEGER");
        List<Long> idsLong = new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readVorTransportband(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband WHERE id_sektor_nach="+id);
        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    private List<Long> readNachTransportband(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband WHERE id_sektor_vor="+id);
        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    private List<Long> readSensor(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sensor FROM Sensor WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_SENSOR");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    private List<Long> readHubPodest(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest FROM Hubpodest WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_HUBPODEST");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    private List<Long> readQuerHubPodest(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubquerpodest FROM Hubquerpodest WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_HUBQUERPODEST");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    private List<Long> readRoboter(Long id){

        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter FROM Roboter_Sektor WHERE id_sektor="+id);
        List<String> ids = rsMap.get("ID_ROBOTER");
        List<Long> idsLong= new ArrayList<>();

        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
}
