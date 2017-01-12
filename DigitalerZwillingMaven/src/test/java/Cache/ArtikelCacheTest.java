/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import Cache.Updater.CacheUpdateThread;
import Cache.Updater.SelfTimer;
import Cache.Updater.Updater;
import Cache.Updater.WebSocketUpdateThread;
import DatenKlassen.Element;
import DatenbankSchnittstelle.DatenbankSchnittstelle;
import DatenbankSchnittstelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittstelle.Exeption.QueryExeption;
import DatenbankTestInsert.DatenbankTestInsert;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author florian
 */
@RunWith(Arquillian.class)
public class ArtikelCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(ArtikelCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    ArtikelCache cache;
    
    /**
     * Test of update method, of class ArtikelCache.
     */
    
    @Before
    public void setUp() throws DBNotFoundExeption, QueryExeption {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (BEZEICHNUNG) VALUES ('CacheTestArtikel1')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (BEZEICHNUNG) VALUES ('CacheTestArtikel2')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "('CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        Map<String, List<String>> datenbankAnfrage = datenbankTestInsert.
                datenbankAnfrage("SELECT ID_ARTIKEL,BEZEICHNUNG FROM ARTIKEL WHERE BEZEICHNUNG LIKE 'CacheTestArtikel1'");
        List<String> id = datenbankAnfrage.get("ID_ARTIKEL");
        List<String> bezeichnung = datenbankAnfrage.get("BEZEICHNUNG");
        Long artikelId = new Long(0);
        for(int i=0;i<id.size();i++){
            if(bezeichnung.get(i).equals("CacheTestArtikel1")){
                artikelId = Long.parseLong(id.get(i));
            }
        }
        
        datenbankAnfrage = datenbankTestInsert.
                datenbankAnfrage("SELECT ID_WARENTRAEGER,BEZEICHNUNG FROM WARENTRAEGER WHERE BEZEICHNUNG LIKE 'CacheTestWarentraeger1'");
        id = datenbankAnfrage.get("ID_WARENTRAEGER");
        bezeichnung = datenbankAnfrage.get("BEZEICHNUNG");
        Long warentraegerId = new Long(0);
        for(int i=0;i<id.size();i++){
            if(bezeichnung.get(i).equals("CacheTestWarentraeger1")){
                warentraegerId = Long.parseLong(id.get(i));
            }
        }
        
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL_WARENTRAEGER (ID_ARTIKEL, ID_WARENTRAEGER) VALUES ("+artikelId+","+warentraegerId+")");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundExeption, QueryExeption {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        Map<String, List<String>> datenbankAnfrage = datenbankTestInsert.
                datenbankAnfrage("SELECT ID_ARTIKEL,BEZEICHNUNG FROM ARTIKEL WHERE BEZEICHNUNG LIKE 'CacheTestArtikel1'");
        List<String> id = datenbankAnfrage.get("ID_ARTIKEL");
        List<String> bezeichnung = datenbankAnfrage.get("BEZEICHNUNG");
        for(int i=0;i<id.size();i++){
            if(bezeichnung.get(i).equals("CacheTestArtikel1")){
                datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL_WARENTRAEGER WHERE ID_ARTIKEL="+id.get(i));
            }
        }
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL WHERE BEZEICHNUNG LIKE 'CacheTestArtikel1'");
        datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL WHERE BEZEICHNUNG LIKE 'CacheTestArtikel2'");
        datenbankTestInsert.datenbankUpdate("DELETE FROM WARENTRAEGER WHERE BEZEICHNUNG LIKE 'CacheTestWarentraeger1'");
        datenbankTestInsert.close();
    }

    @Override
    public Cache getCache() {
        return cache;
    }

    @Override
    public void testUpdate() throws DBNotFoundExeption, QueryExeption, DBErrorExeption {
        Element element1;
        Element element2;
        for(Element element: cache.getAll()){
            if(element.getBezeichnung().equals("CacheTestArtikel1'"))
                element1 = element;
            
            if(element.getBezeichnung().equals("CacheTestArtikel2'"))
                element2 = element;
        }
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert();
        datenbankTestInsert.datenbankUpdate("UPDATE ARTIKEL SET BEZEICHNUNG='CacheTestArtikel12' WHERE BEZEICHNUNG LIKE 'CacheTestArtikel1'");
        datenbankTestInsert.datenbankUpdate("UPDATE ARTIKEL SET BEZEICHNUNG='CacheTestArtikel22' WHERE BEZEICHNUNG LIKE 'CacheTestArtikel2'");
        datenbankTestInsert.close();
        
        cache.update();
        boolean found1 = false, found2 = false;
        
        for(Element element: cache.getAll()){
            if(element.getBezeichnung().equals("CacheTestArtikel12'"))
                found1 = true;
            
            if(element.getBezeichnung().equals("CacheTestArtikel22'"))
                found2 = true;
        }
        
        System.out.println("1" + found1);
        System.out.println("2" + found2);
        assertTrue(found1 & found2);
    }

    @Override
    public void testUpdateAll() throws DBNotFoundExeption, QueryExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
