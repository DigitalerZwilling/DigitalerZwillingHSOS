package Cache;

import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
import DatenKlassen.HubPodest;
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
 * @author User
 */
@ApplicationScoped
public class HubPodestCache extends Cache{
    @Inject private Datenbankschnittstelle datenbankschnittstelle;
    
    @Override
    public void update() throws DBErrorExeption {
        try {
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest,oben,unten,zeitstempel,user_parameter from hubpodest");
            List<String> ids = rsMap.get("ID_HUBPODEST");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> oben = rsMap.get("OBEN");
            List<String> unten = rsMap.get("UNTEN");
            
            HubPodest hubpodest;
            for (int i=0;i<ids.size();i++){
                hubpodest=(HubPodest)(state==true?elements[0].get(Long.parseLong(ids.get(i))):elements[1].get(Long.parseLong(ids.get(i))));
                
                String outTime=zeitstempel.get(i).replace(' ', 'T');
                hubpodest.setZeitstempel(LocalDateTime.parse(outTime));
                hubpodest.setOben(Boolean.getBoolean(oben.get(i)));
                hubpodest.setUnten(Boolean.getBoolean(unten.get(i)));
                hubpodest.setUser_Parameter(user_parameter.get(i));
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
            Map<Long,Element> allHuPo1=new HashMap<>();
            Map<Long,Element> allHuPo2=new HashMap<>();
            
            Map<String,List<String>> rsMap= this.datenbankschnittstelle.datenbankAnfrage("SELECT id_hubpodest,bezeichnung,oben,unten,id_sektor,zeitstempel,user_parameter from hubpodest");
            List<String> ids = rsMap.get("ID_HUBPODEST");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> user_parameter = rsMap.get("USER_PARAMETER");
            List<String> oben = rsMap.get("OBEN");
            List<String> unten = rsMap.get("UNTEN");
            List<String> sektor = rsMap.get("ID_SEKTOR");
            
            HubPodest hupo1,hupo2;
            for (int i=0;i<ids.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                
                hupo1=new HubPodest(Boolean.getBoolean(oben.get(i)),Boolean.getBoolean(unten.get(i)),Long.parseLong(sektor.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                hupo2=new HubPodest(Boolean.getBoolean(oben.get(i)),Boolean.getBoolean(unten.get(i)),Long.parseLong(sektor.get(i)),Long.parseLong(ids.get(i)),bezeichnung.get(i),user_parameter.get(i),LocalDateTime.parse(ourTime));
                
                allHuPo1.put(hupo1.getId(),(hupo1));
                allHuPo2.put(hupo2.getId(),(hupo2));
            }   
            
            Map<Long,Element>[] m = new Map[2];
            m[0]=allHuPo1;
            m[1]=allHuPo2;
            this.setElements(m);
            
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(HubPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("DB not found");
        } catch (QueryExeption ex) {
            Logger.getLogger(HubPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("Query error");
        }
    }
}
