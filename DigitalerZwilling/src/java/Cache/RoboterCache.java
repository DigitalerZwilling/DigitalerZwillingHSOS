/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Element;
import DatenKlassen.Roboter;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author User
 */
@ApplicationScoped
public class RoboterCache extends Cache{

    @Override
    public void update() {
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_roboter,stoerung,position_x,position_y,position_z,position_ausrichtung,zeitstempel,user_parameter from Roboter");
        List<String> ids = rsMap.get("id_roboter");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        List<String> stoerung = rsMap.get("stoerung");  //int
        
        List<String> x = rsMap.get("position_x");   //int
        List<String> y = rsMap.get("position_y");   //int
        List<String> z = rsMap.get("position_z");   //int
        List<String> ausrichtung = rsMap.get("position_ausrichtung");   //int
        
        Roboter roboter;
        for (int i=0;i<ids.size();i++){
            roboter=(Roboter)(state==true?elements[0].get(Long.getLong(ids.get(i))):elements[1].get(Long.getLong(ids.get(i))));
            roboter.setStoerung(Integer.getInteger(stoerung.get(i)));
            roboter.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            roboter.setUser_Parameter(user_parameter.get(i));
            
            roboter.setAusrichtung(Integer.getInteger(ausrichtung.get(i)));
            roboter.setX(Integer.getInteger(x.get(i)));
            roboter.setY(Integer.getInteger(y.get(i)));
            roboter.setZ(Integer.getInteger(z.get(i)));
            
            roboter.setId_Gelenke(this.readGelenke(roboter.getId()));
            roboter.setId_Sektor(this.readSektor(roboter.getId()));
            roboter.setId_Werkzeug(this.readWerkzeug(roboter.getId()));
            
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        Map<Long,Element> allRoboter1=new HashMap<>();
        Map<Long,Element> allRoboter2=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_roboter,bezeichnung,stoerung,position_x,position_y,position_z,position_ausrichtung,zeitstempel,user_parameter from Roboter");
        List<String> ids = rsMap.get("id_roboter");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        
        List<String> stoerung = rsMap.get("stoerung");  //int
        
        List<String> x = rsMap.get("position_x");   //int
        List<String> y = rsMap.get("position_y");   //int
        List<String> z = rsMap.get("position_z");   //int
        List<String> ausrichtung = rsMap.get("position_ausrichtung");   //int
        
        Roboter roboter1,roboter2;
        for (int i=0;i<ids.size();i++){
            roboter1=new Roboter(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            roboter2=new Roboter(Integer.getInteger(stoerung.get(i)),Integer.getInteger(x.get(i)),Integer.getInteger(y.get(i)),Integer.getInteger(z.get(i)),Integer.getInteger(ausrichtung.get(i)),Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
            roboter1.setId_Gelenke(this.readGelenke(roboter1.getId()));
            roboter2.setId_Gelenke(this.readGelenke(roboter2.getId()));
            
            roboter1.setId_Sektor(this.readSektor(roboter1.getId()));
            roboter2.setId_Sektor(this.readSektor(roboter2.getId()));
            
            roboter1.setId_Werkzeug(this.readWerkzeug(roboter1.getId()));
            roboter2.setId_Werkzeug(this.readWerkzeug(roboter2.getId()));
            
            allRoboter1.put(roboter1.getId(),(roboter1));
            allRoboter2.put(roboter2.getId(),(roboter2));
            
            
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allRoboter1;
        m[1]=allRoboter2;
        this.setElements(m);
    }
    
    List<Long> readSektor(Long id){
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_sektor from Roboter_Sektor where id_roboter="+id);
        List<String> ids = rsMap.get("id_sektor");
        List<Long> s_ids= new ArrayList<>();
        for (String s : ids){
            s_ids.add(Long.getLong(s));
        }
        return s_ids;
    }
    List<Long> readGelenke(Long id){
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_gelenk from Gelenk where id_roboter="+id);
        List<String> ids = rsMap.get("id_gelenk");
        List<Long> g_ids= new ArrayList<>();
        for (String s : ids){
            g_ids.add(Long.getLong(s));
        }
        return g_ids;
    }
    List<Long> readWerkzeug(Long id){
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_werkzeug from Roboter_Werkzeug where id_roboter="+id);
        List<String> ids = rsMap.get("id_werkzeug");
        List<Long> werk_ids= new ArrayList<>();
        for (String s : ids){
            werk_ids.add(Long.getLong(s));
        }
        return werk_ids;
    }

  
    
}
