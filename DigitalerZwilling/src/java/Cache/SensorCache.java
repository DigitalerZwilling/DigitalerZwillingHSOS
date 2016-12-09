/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import static Cache.Cache.state;
import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Sensor;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
import java.time.LocalDateTime;
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
public class SensorCache extends Cache {

    @Inject
    Datenbankschnittstelle datenbankSchnittstelle;

    @Override
    public void update() throws DBErrorExeption {
        try {
            Map<String, List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor, "
                    + "stoerung, zustand, user_parameter, zeitstempel FROM Sensor");

            List<String> id = rsMap.get("ID_SENSOR");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zustand = rsMap.get("ZUSTAND");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");

            for (int i = 0; i < id.size(); i++) {
                String ourTime = zeitstempel.get(i).replace(' ', 'T');
                Sensor sensor = (Sensor) (state == true ? elements[0].get(Long.parseLong(id.get(i))) : elements[0].get(Long.parseLong(id.get(i))));
                sensor.setStoerung((int) Long.parseLong(stoerung.get(i)));
                sensor.setUser_Parameter(userParameter.get(i));
                sensor.setZeitstempel(LocalDateTime.parse(ourTime));
                sensor.setZustand(Long.parseLong(zustand.get(i)) != 0);
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
            elements[0] = new HashMap<>();
            elements[1] = new HashMap<>();

            Map<String, List<String>> rsMap = this.datenbankSchnittstelle.datenbankAnfrage("SELECT id_sensor, "
                    + "stoerung, zustand, user_parameter, zeitstempel, bezeichnung, id_sektor, phy_adresse FROM Sensor");

            List<String> id = rsMap.get("ID_SENSOR");
            List<String> stoerung = rsMap.get("STOERUNG");
            List<String> zustand = rsMap.get("ZUSTAND");
            List<String> userParameter = rsMap.get("USER_PARAMETER");
            List<String> zeitstempel = rsMap.get("ZEITSTEMPEL");
            List<String> bezeichnung = rsMap.get("BEZEICHNUNG");
            List<String> idSektor = rsMap.get("ID_SEKTOR");
            List<String> phyAdress = rsMap.get("PHY_ADRESSE");

            for (int i = 0; i < id.size(); i++) {
                String ourTime = zeitstempel.get(i).replace(' ', 'T');
                elements[0].put(Long.parseLong(id.get(i)), new Sensor((int) Long.parseLong(stoerung.get(i)), phyAdress.get(i), Long.parseLong(zustand.get(i)) != 0, Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
                elements[1].put(Long.parseLong(id.get(i)), new Sensor((int) Long.parseLong(stoerung.get(i)), phyAdress.get(i), Long.parseLong(zustand.get(i)) != 0, Long.parseLong(idSektor.get(i)), Long.parseLong(id.get(i)), bezeichnung.get(i), userParameter.get(i), LocalDateTime.parse(ourTime)));
            }
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryExeption ex) {
            Logger.getLogger(SensorCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
