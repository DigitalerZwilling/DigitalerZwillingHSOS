/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Artikel;
import DatenKlassen.Element;
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
public class ArtikelCache extends Cache{
    

    @Override
    public void update() {
        //Map<String,List<String>> rsMap;
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_artikel,zeitstempel,user_parameter from Artikel");
        List<String> ids = rsMap.get("id_artikel");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Artikel artikel;
        for (int i=0;i<ids.size();i++){
            //artikel=Artikel.class.cast(this.getById(Long.getLong(ids.get(i))));
            //artikel=Artikel.class.cast(state==true?elements[1].get(Long.getLong(ids.get(i))):elements[0].get(Long.getLong(ids.get(i))));
            artikel=(Artikel)(state==true?elements[0].get(Long.getLong(ids.get(i))):elements[1].get(Long.getLong(ids.get(i))));                 //andersrum als bei getById
            artikel.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            artikel.setUser_Parameter(user_parameter.get(i));
            artikel.setId_Warentraeger(this.readWarentraeger(artikel.getId()));
        }
    }

    @PostConstruct @Override
    public void updateAll() {
        Map<Long,Element> allArtikel1=new HashMap<>();
        Map<Long,Element> allArtikel2=new HashMap<>();
        
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_artikel,bezeichnung,zeitstempel,user_parameter FROM Artikel");
        List<String> ids = rsMap.get("id_artikel");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Artikel artikel1,artikel2;
        for (int i=0;i<ids.size();i++){
            artikel1=new Artikel(Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            artikel2=new Artikel(Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            
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
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_warentraeger FROM Artikel_Warentraeger WHERE id_artikel="+id);
        List<String> ids = rsMap.get("id_warentraeger");
        List<Long> w_ids= new ArrayList<>();
        for (String s : ids){
            w_ids.add(Long.getLong(s));
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
