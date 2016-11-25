/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import DatenKlassen.Element;
import DatenKlassen.Transportband;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import java.time.LocalDateTime;
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
public class TransportbandCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    @Override
    public void update() {

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband,zeitstempel,user_parameter,stoerung,geschwindigkeit FROM Gelenk");

        List<String> ids = rsMap.get("ID_ARTIKEL");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> user_parameter = rsMap.get("USER_PARAMETER");
        List<String> stoerung = rsMap.get("STOERUNG");
        List<String> geschwindigkeit = rsMap.get("GESCHWINDIGKEIT");
        Transportband transportband;
        for (int i=0;i<ids.size();i++){
            String ourTime=zeitstempel.get(i).replace(' ', 'T');
            transportband=(Transportband)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
            transportband.setStoerung(Integer.valueOf(stoerung.get(i)));
            transportband.setGeschwindigkeit(Integer.valueOf(geschwindigkeit.get(i)));
            transportband.setZeitstempel(LocalDateTime.parse(zeitstempel.get(i)));
            transportband.setUser_Parameter(user_parameter.get(i));
        }
        
    }
    
    @Override
    @PostConstruct
    public void updateAll() {
        Map<Long,Element> allTransportband1=new HashMap<>();
        Map<Long,Element> allTransportband2=new HashMap<>();

        Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband,bezeichnung,zeitstempel,user_parameter,stoerung,laenge,geschwindigkeit,id_sektor_vor,id_sektor_nach FROM Transportband");

        List<String> ids = rsMap.get("ID_TRANSPORTBAND");
        List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
        List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
        List<String> user_parameter = rsMap.get("USER_PARAMETER");
        
        List<String> stoerung = rsMap.get("STOERUNG");
        List<String> laenge = rsMap.get("LAENGE");
        List<String> geschwindigkeit = rsMap.get("GESCHWINDIGKEIT");
        
        List<String> ids_vor = rsMap.get("ID_SEKTOR_VOR");
        List<String> ids_nach = rsMap.get("ID_SEKTOR_NACH");
        
        Transportband transportband1,transportband2;
        for (int i=0;i<ids.size();i++){
            transportband1=new Transportband(Integer.valueOf(stoerung.get(i)),Integer.valueOf(laenge.get(i)),Integer.valueOf(geschwindigkeit.get(i)),Long.parseLong(ids_vor.get(i)),Long.parseLong(ids_nach.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(zeitstempel.get(i)));
            transportband2=new Transportband(Integer.valueOf(stoerung.get(i)),Integer.valueOf(laenge.get(i)),Integer.valueOf(geschwindigkeit.get(i)),Long.parseLong(ids_vor.get(i)),Long.parseLong(ids_nach.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(zeitstempel.get(i)));
            
            allTransportband1.put(transportband1.getId(),(transportband1));
            allTransportband2.put(transportband2.getId(),(transportband2));
        }
        Map<Long,Element>[] m = new Map[2];
        m[0]=allTransportband1;
        m[1]=allTransportband2;
        this.setElements(m);
    }
}
