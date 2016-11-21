/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import DatenKlassen.HubQuerPodest;
import DatenKlassen.Sensor;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.time.LocalDateTime;
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
public class SensorCache extends Cache{
    
    @Inject
    Datenbankschnittstelle datenbankSchnittstelle;

    @Override
    public void update() {
        Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor"+
                ",stoerung,zustand,user_parameter,zeitstempel from Sensor");
        
        List<String> id = rsMap.get("ID_SENSOR");
        List<String> stoerung = rsMap.get("STOERUNG");
        List<String> zustand = rsMap.get("ZUSTAND");
        List<String> userParameter = rsMap.get("USER_PARAMETER");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        
        for(int i=0;i<id.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            Sensor sensor = (Sensor) (state==true?elements[0].get(Long.parseLong(id.get(i))):elements[0].get(Long.parseLong(id.get(i))));
            sensor.setStoerung((int)Long.parseLong(stoerung.get(i)));
            sensor.setUser_Parameter(userParameter.get(i));
            sensor.setZeitstempel(LocalDateTime.parse(ourTime).toLocalTime());
            sensor.setZustand(Boolean.getBoolean(zustand.get(i)));
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        
        Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor"+
                ",stoerung,zustand,user_parameter,zeitstempel,bezeichnung,id_sektor,phy_adresse from Sensor");
        
        List<String> id = rsMap.get("ID_SENSOR");
        List<String> stoerung = rsMap.get("STOERUNG");
        List<String> zustand = rsMap.get("ZUSTAND");
        List<String> userParameter = rsMap.get("USER_PARAMETER");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
        List<String> idSektor = rsMap.get("ID_SEKTOR");
        List<String> phyAdress = rsMap.get("PHY_ADRESSE");
        
        for(int i=0;i<id.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            elements[0].put(Long.parseLong(id.get(i)), new Sensor((int)Long.parseLong(stoerung.get(i)), phyAdress.get(i), Boolean.getBoolean(zustand.get(i)), Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime).toLocalTime()));
            elements[1].put(Long.parseLong(id.get(i)), new Sensor((int)Long.parseLong(stoerung.get(i)), phyAdress.get(i), Boolean.getBoolean(zustand.get(i)), Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime).toLocalTime()));
        }
    }
}
