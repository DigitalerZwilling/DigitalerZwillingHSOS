package Cache;

import Cache.Exeption.DBErrorExeption;
import DatenKlassen.HubQuerPodest;
import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author User
 */

@ApplicationScoped
public class HubQuerPodestCache extends Cache{
    
    @Override
    public void update() throws DBErrorExeption {
        try {            
            Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel FROM Hubquerpodest");
            
            List<String> id = rsMap.get("ID_HUBQUERPODEST");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> motor = rsMap.get("MOTOR");
            List<String> oben = rsMap.get("OBEN");
            List<String> mittig = rsMap.get("MITTIG");
            List<String> unten = rsMap.get("UNTEN");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            
            for(int i = 0; i<id.size();i++){
                HubQuerPodest huQu = (HubQuerPodest) (state==true?elements[0].get(Long.parseLong(id.get(i))):elements[0].get(Long.parseLong(id.get(i))));
                
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                huQu.setZeitstempel(LocalDateTime.parse(ourTime));
                huQu.setUser_Parameter(userParameter.get(i));
                huQu.setMotor(Long.parseLong(motor.get(i))!=0);
                huQu.setOben(Long.parseLong(oben.get(i))!=0);
                huQu.setMittig(Long.parseLong(mittig.get(i))!=0);
                huQu.setUnten(Long.parseLong(unten.get(i))!=0);
                
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
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();
            
            Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest, user_parameter, motor, oben, mittig, unten, zeitstempel, bezeichnung, id_sektor FROM Hubquerpodest");
            
            List<String> id = rsMap.get("ID_HUBQUERPODEST");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> motor = rsMap.get("MOTOR");
            List<String> oben = rsMap.get("OBEN");
            List<String> mittig = rsMap.get("MITTIG");
            List<String> unten = rsMap.get("UNTEN");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> sektorId = rsMap.get("ID_SEKTOR");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            
            for(int i = 0; i<id.size();i++){
                String ourTime=zeitstempel.get(i).replace(' ', 'T');
                
                elements[0].put(Long.parseLong(id.get(i)), new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
                elements[1].put(Long.parseLong(id.get(i)), new HubQuerPodest(Long.parseLong(motor.get(i))!=0, Long.parseLong(oben.get(i))!=0, Long.parseLong(mittig.get(i))!=0, Long.parseLong(unten.get(i))!=0, Long.parseLong(sektorId.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
            }
            
            updateGruppenIds();
            
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(HubQuerPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("DB not found");
        } catch (QueryExeption ex) {
            Logger.getLogger(HubQuerPodestCache.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBErrorExeption("Query error");
        }
    }
   
    private void updateGruppenIds() throws DBNotFoundExeption, QueryExeption{
        Map<String,List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_Hubquerpodest1, id_hubquerpodest2 FROM Hubquerpodest_Hubquerpodest");
        List<String> id1 = rsMap.get("ID_HUBQUERPODEST1");
        List<String> id2 = rsMap.get("ID_HUBQUERPODEST2");
        
        if(id1==null || id2==null) return;
        for(int i=0;i<id1.size();i++){
            HubQuerPodest podest = (HubQuerPodest) (state==true?elements[0].get(Long.parseLong(id1.get(i))):elements[0].get(Long.parseLong(id1.get(i))));
            podest.getGruppenIDs().add(Long.parseLong(id2.get(i)));
        }
    }
}
