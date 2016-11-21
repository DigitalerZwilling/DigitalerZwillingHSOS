/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Artikel;
import DatenKlassen.Element;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class ArtikelCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    

    @Override
    public void update() {
        //Map<String,List<String>> rsMap;
        //Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_artikel,zeitstempel,user_parameter from Artikel");
        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_artikel,zeitstempel,user_parameter from Artikel");
        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Artikel artikel;
        for (int i=0;i<ids.size();i++){
            //artikel=Artikel.class.cast(this.getById(Long.parseLong(ids.get(i))));
            //artikel=Artikel.class.cast(state==true?elements[1].get(Long.parseLong(ids.get(i))):elements[0].get(Long.parseLong(ids.get(i))));
            artikel=(Artikel)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));                 //andersrum als bei getById
            artikel.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            artikel.setUser_Parameter(user_parameter.get(i));
            artikel.setId_Warentraeger(this.readWarentraeger(artikel.getId()));
        }
    }

    @PostConstruct @Override
    public void updateAll() {
        Map<Long,Element> allArtikel1=new HashMap<>();
        Map<Long,Element> allArtikel2=new HashMap<>();
        

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_artikel,bezeichnung,zeitstempel,user_parameter from Artikel");
        
        List<String> ids = rsMap.get("ID_ARTIKEL");
        List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> user_parameter = rsMap.get("USER_PARAMETER");

        Artikel artikel1,artikel2;
        System.out.println(zeitstempel.get(0));
        for (int i=0;i<ids.size();i++){
            
            
            //artikel1=new Artikel(Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            String t=zeitstempel.get(i).replace(' ', 'T');
            
            artikel1=new Artikel(Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(t));
            artikel2=new Artikel(Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(t));
          
            artikel1.setId_Warentraeger(this.readWarentraeger(artikel1.getId()));
            artikel2.setId_Warentraeger(this.readWarentraeger(artikel2.getId()));
            
            allArtikel1.put(artikel1.getId(),(artikel1));
            allArtikel2.put(artikel2.getId(),(artikel2));
        }
        
        Map<Long,Element>[] m = new Map[2];
        m[0]=allArtikel1;
        m[1]=allArtikel2;
        this.setElements(m);
    }


    private List<Long> readWarentraeger(Long id){
        
        Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger from Artikel_Warentraeger where id_artikel="+id);
        
        List<String> ids = rsMap.get("ID_WARENTRAEGER");
        List<Long> w_ids= new ArrayList<>();
        if(ids==null) return w_ids;
        for (String s : ids){
            w_ids.add(Long.parseLong(s));
        }
        return w_ids;
    }

   /* private static ArtikelCache instance;

    public static synchronized Cache getInstance(){
        if(ArtikelCache.instance == null) {
            ArtikelCache.instance = new ArtikelCache();
        }
        return instance;
    }
    */
}
