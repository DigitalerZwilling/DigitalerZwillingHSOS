/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.Artikel;
import DatenKlassen.Element;
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
            artikel=Artikel.class.cast(this.getById(Long.getLong(ids.get(i))));
            artikel.setZeitstempel(LocalTime.parse(zeitstempel.get(i))); // Ueberpruefen
            artikel.setUser_Parameter(user_parameter.get(i));
            artikel.setId_Warentraeger(this.readWarentraeger(artikel.getId()));
        }
    }

    @Override
    public void updateAll() {
        Map<Long,Element> allArtikel=new HashMap<>();
        Map<String,List<String>> rsMap= Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT id_artikel,bezeichnung,zeitstempel,user_parameter from Artikel");
        List<String> ids = rsMap.get("id_artikel");
        List<String> bezeichnung = rsMap.get("bezeichnung");
        List<String> zeitstempel = rsMap.get("zeitstempel");
        List<String> user_parameter = rsMap.get("user_parameter");
        Artikel artikel;
        for (int i=0;i<ids.size();i++){
            artikel=new Artikel(Long.getLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalTime.parse(zeitstempel.get(i)));
            artikel.setId_Warentraeger(this.readWarentraeger(artikel.getId()));
            allArtikel.put(artikel.getId(),(artikel));
        }
        Map<Long,Element>[] m = new Map[2];
        m[1]=allArtikel;
        m[2]=allArtikel;
        this.setElements(m);
    }

    @Override
    public void parseResultSet(ResultSet rs,int identifier) {
        
        switch (identifier){
            
            case 0:    ;//fuer update all
                break;
            case 1:    ;//fuer update erste sql-Anfrage
                break;
            case 2: ;//fuer 2. update sql Abfrage
                break;
            default:    ;//fuer Fehlerfaelle
                break;
        }
    
            
            
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Long> readWarentraeger(Long id){
        Map<String,List<String>> rsMap = Datenbankschnittstelle.getInstance().datenbankAnfrage("SELECT warentraeger_id from Artikel_Warentraeger where id_artikel="+id+" and id_warentraeger=id_warentraeger");
        List<String> ids = rsMap.get("id_warentraeger");
        List<Long> w_ids= new ArrayList<>();
        for (String s : ids){
            w_ids.add(Long.getLong(s));
        }
        return w_ids;
    }
    
}
