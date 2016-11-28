/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
import DatenKlassen.Warentraeger;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void update() throws DBErrorExeption {
        

        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger, stoerung, zeitstempel, user_parameter, abstand_mm, montagezustand, RFID_inhalt FROM Warentraeger");
            
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> abstand_mm = rsMap.get("ABSTAND_MM");
            List<String> montagezustand = rsMap.get("MONTAGEZUSTAND");
            List<String> RFID_inhalt = rsMap.get("RFID_INHALT");
            
            Warentraeger warentraeger;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                warentraeger = (Warentraeger)(state==true ? elements[0].get(Long.parseLong(ids.get(i))) : elements[1].get(Long.parseLong(ids.get(i))));                 //andersrum als bei getById
                
                warentraeger.setStoerung(Integer.getInteger(stoerung.get(i)));
                warentraeger.setZeitstempel(LocalDateTime.parse(zeitstempel.get(i)));
                warentraeger.setUser_Parameter(user_parameter.get(i));
                warentraeger.setAbstand_mm(Integer.getInteger(abstand_mm.get(i)));
                warentraeger.setMontagezustand(Integer.getInteger(montagezustand.get(i)));
                warentraeger.setrFID_inhalt(RFID_inhalt.get(i));
                warentraeger.setTransportbandIDs(this.readTransportband(Long.parseLong(ids.get(i))));
                warentraeger.setTransportbandIDs(this.readTransportband(Long.parseLong(ids.get(i))));
                
            }
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("DB not found");
        } catch (QueryExeption ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("Query error");
        }
    }

    @Override
    @PostConstruct
    public void updateAll() {
        try {
            Map<Long,Element> allWarentraeger1=new HashMap<>();
            Map<Long,Element> allWarentraeger2=new HashMap<>();
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger , bezeichnung , stoerung , zeitstempel, user_parameter, abstand_mm, montagezustand, RFID_inhalt FROM Warentraeger");
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> abstand_mm = rsMap.get("ABSTAND_MM");
            List<String> montagezustand = rsMap.get("MONTAGEZUSTAND");
            List<String> RFID_inhalt = rsMap.get("RFID_INHALT");
            Warentraeger warentraeger1,warentraeger2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                warentraeger1 = new Warentraeger(Integer.getInteger(stoerung.get(i)), Integer.getInteger(abstand_mm.get(i)), Integer.getInteger(montagezustand.get(i)), RFID_inhalt.get(i), Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(zeitstempel.get(i)));
                warentraeger2 = new Warentraeger(Integer.getInteger(stoerung.get(i)), Integer.getInteger(abstand_mm.get(i)), Integer.getInteger(montagezustand.get(i)), RFID_inhalt.get(i), Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(zeitstempel.get(i)));
                
                warentraeger1.setTransportbandIDs(this.readTransportband(warentraeger1.getId()));
                warentraeger2.setTransportbandIDs(this.readTransportband(warentraeger2.getId()));
                
                warentraeger1.setSektorIDs(this.readSektor(warentraeger1.getId()));
                warentraeger2.setSektorIDs(this.readSektor(warentraeger2.getId()));
                
                allWarentraeger1.put(warentraeger1.getId(),(warentraeger1));
                allWarentraeger2.put(warentraeger2.getId(),(warentraeger2));
            }   Map<Long,Element>[] elements = new Map[2];
            elements[0]=allWarentraeger1;
            elements[1]=allWarentraeger2;
            this.setElements(elements);
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryExeption ex) {
            Logger.getLogger(WarentraegerCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Long> readTransportband(Long id) throws DBNotFoundExeption, QueryExeption{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband FROM Transportband_Warentraeger WHERE id_warentraeger="+id);
        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        List<Long> idsLong= new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
    
    private List<Long> readSektor(Long id) throws DBNotFoundExeption, QueryExeption{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_sektor FROM Sektor_Transportband WHERE id_warentraeger="+id);
        List<String> ids = rsMap.get("ID_SEKTOR");
        List<Long> idsLong= new ArrayList<>();
        for (String s : ids){
            idsLong.add(Long.parseLong(s));
        }
        return idsLong;
    }
}
