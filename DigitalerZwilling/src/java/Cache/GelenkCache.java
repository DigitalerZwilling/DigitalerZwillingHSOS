/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
import DatenKlassen.Gelenk;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void update() throws DBErrorExeption {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_gelenk,gelenkstellung,zeitstempel,user_parameter from Gelenk");
            
            List<String> ids = rsMap.get("id_artikel");
            List<String> zeitstempel = rsMap.get("zeitstempel");
            List<String> user_parameter = rsMap.get("user_parameter");
            List<String> gelenkstellung = rsMap.get("gelenkstellung");
            
            Gelenk gelenk;
            for (int i=0;i<ids.size();i++){
                gelenk=(Gelenk)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                gelenk.setZeitstempel(LocalDateTime.parse(ourTime));
                gelenk.setUser_Parameter(user_parameter.get(i));
                gelenk.setGelenkstellung(Integer.valueOf(gelenkstellung.get(i)));
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
    public void updateAll() throws DBErrorExeption {
        try {
            Map<Long,Element> allGelenk1=new HashMap<>();
            Map<Long,Element> allGelenk2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_gelenk,bezeichnung,typ,nummer,gelenkstellung,zeitstempel,user_parameter from Gelenk");
            List<String> ids = rsMap.get("ID_GELENK");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> typ = rsMap.get("TYP");
            List<String> nummer = rsMap.get("NUMMER");
            List<String> gelenkstellung = rsMap.get("GELENKSTELLUNG");
            
            Gelenk gelenk1,gelenk2;
            for (int i=0;i<ids.size();i++){
                String outTime=zeitstempel.get(i).replace(' ', 'T');
                gelenk1=new Gelenk(typ.get(i),Integer.valueOf(nummer.get(i)),Integer.valueOf(gelenkstellung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(outTime));
                gelenk2=new Gelenk(typ.get(i),Integer.valueOf(nummer.get(i)),Integer.valueOf(gelenkstellung.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(outTime));
                
                gelenk1.setRoboterID(this.readRoboter(gelenk1.getId()));
                gelenk2.setRoboterID(this.readRoboter(gelenk2.getId()));
                
                allGelenk1.put(gelenk1.getId(),(gelenk1));
                allGelenk2.put(gelenk2.getId(),(gelenk2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allGelenk1;
            m[1]=allGelenk2;
            this.setElements(m);
            
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(GelenkCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("DB not found");
        } catch (QueryExeption ex) {
            Logger.getLogger(GelenkCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("Query error");
        }
    }
    Long readRoboter(Long id) throws DBNotFoundExeption, QueryExeption{
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_roboter from Gelenk where id_gelenk="+id+" ");
        List<String> ids = rsMap.get("ID_ROBOTER");
        
        Long r_ids=null;
        if(ids==null) return r_ids;
        
        for (String s : ids){
            r_ids=Long.parseLong(s);
        }
        return r_ids;
    }
}
