package Cache;

import static Cache.Cache.state;
import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
import DatenKlassen.Transportband;
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
    public void update() throws DBErrorExeption {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband,zeitstempel,user_parameter,stoerung,geschwindigkeit FROM Transportband");
            List<String> ids = rsMap.get("ID_TRANSPORTBAND");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> geschwindigkeit = rsMap.get("GESCHWINDIGKEIT");
            
            Transportband transportband;
            for (int i=0;i<ids.size();i++){
                transportband = (Transportband)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                
                String ourTime = zeitstempel.get(i).replace(' ', 'T');
                transportband.setZeitstempel(LocalDateTime.parse(ourTime));
                transportband.setStoerung(Integer.parseInt(stoerung.get(i)));
                transportband.setGeschwindigkeit(Integer.parseInt(geschwindigkeit.get(i)));
                transportband.setUser_Parameter(user_parameter.get(i));
                transportband.setWarentraegerIDs(this.readWarentraeger(transportband.getId()));
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
            Map<Long,Element> allTransportband1=new HashMap<>();
            Map<Long,Element> allTransportband2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_transportband,bezeichnung,zeitstempel,reihe,user_parameter,stoerung,laenge,geschwindigkeit,id_sektor_vor,id_sektor_nach FROM Transportband");
            List<String> ids = rsMap.get("ID_TRANSPORTBAND");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> reihe = rsMap.get("REIHE");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> laenge = rsMap.get("LAENGE");
            List<String> geschwindigkeit = rsMap.get("GESCHWINDIGKEIT");
            List<String> ids_vor = rsMap.get("ID_SEKTOR_VOR");
            List<String> ids_nach = rsMap.get("ID_SEKTOR_NACH");
            
            Transportband transportband1,transportband2;
            for (int i=0;i<ids.size();i++){
                String ourTime = zeitstempel.get(i).replace(' ', 'T');
                transportband1=new Transportband(Integer.parseInt(stoerung.get(i)),Integer.parseInt(laenge.get(i)),Integer.parseInt(geschwindigkeit.get(i)),Integer.parseInt(reihe.get(i)),Long.parseLong(ids_vor.get(i)),Long.parseLong(ids_nach.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                transportband2=new Transportband(Integer.parseInt(stoerung.get(i)),Integer.parseInt(laenge.get(i)),Integer.parseInt(geschwindigkeit.get(i)),Integer.parseInt(reihe.get(i)),Long.parseLong(ids_vor.get(i)),Long.parseLong(ids_nach.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                transportband1.setWarentraegerIDs(this.readWarentraeger(transportband1.getId()));
                transportband2.setWarentraegerIDs(this.readWarentraeger(transportband2.getId()));
                
                allTransportband1.put(transportband1.getId(),(transportband1));
                allTransportband2.put(transportband2.getId(),(transportband2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allTransportband1;
            m[1]=allTransportband2;
            this.setElements(m);
            
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(TransportbandCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("DB not found");
        } catch (QueryExeption ex) {
            Logger.getLogger(TransportbandCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("Query error");
        }
    }
    
    private List<Long> readWarentraeger(Long id) throws DBNotFoundExeption{
        List<Long> w_ids= new ArrayList<>();
        try {
            Map<String,List<String>> rsMap = this.datenbankschnittstelle.datenbankAnfrage("SELECT id_warentraeger from Transportband_Warentraeger where id_transportband="+id);
            List<String> ids = rsMap.get("ID_WARENTRAEGER");
            
            
            if(ids==null) return w_ids;
            for (String s : ids){
                w_ids.add(Long.parseLong(s));
            }
            
        } catch (QueryExeption ex) {
            Logger.getLogger(ArtikelCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w_ids;
    }
}
