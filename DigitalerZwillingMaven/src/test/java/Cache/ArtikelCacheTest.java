/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

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
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert("jdbc:derby://localhost:1527/db_DigitalerZwilling", "org.apache.derby.jdbc.ClientDriver", "db_user", "SB0222");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (BEZEICHNUNG) VALUES ('CacheTestArtikel1')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL (BEZEICHNUNG) VALUES ('CacheTestArtikel2')");
        datenbankTestInsert.datenbankUpdate("INSERT INTO WARENTRAEGER (BEZEICHNUNG, STOERUNG,"
                + "MONTAGEZUSTAND,RFID_INHALT,ABSTAND_MM) VALUES "
                + "('CacheTestWarentraeger1',0,100,'FOOBAR',42)");
        Map<String, List<String>> datenbankAnfrage = datenbankTestInsert.datenbankAnfrage("SELECT ID_ARTIKEL FROM ARTIKEL");
        List<String> id = datenbankAnfrage.get("ID_Artikel");
        List<String> bezeichnung = datenbankAnfrage.get("BEZEICHNUNG");
        for(int i=0;i<id.size();i++){
            if(bezeichnung.get(i).equals("CacheTestArtikel1"))
        }
        //datenbankTestInsert.datenbankUpdate("INSERT INTO ARTIKEL_WARENTRAEGER (ID_ARTIKEL, ID_WARENTRAEGER) VALUES (4242,4242)");
        datenbankTestInsert.close();
    }
    
    @After
    public void tearDown() throws DBNotFoundExeption, QueryExeption {
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert("jdbc:derby://localhost:1527/db_DigitalerZwilling", "org.apache.derby.jdbc.ClientDriver", "db_user", "SB0222");
        //datenbankTestInsert.datenbankUpdate("DELETE FROM ARTIKEL_WARENTRAEGER WHERE ID_ARTIKEL = 4242 AND ID_WARENTRAEGER = 4242");
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
    public void testUpdate() throws DBNotFoundExeption, QueryExeption {
        Element element1;
        Element element2;
        for(Element element: cache.getAll()){
            if(element.getBezeichnung().equals("CacheTestArtikel1'"))
                element1 = element;
            
            if(element.getBezeichnung().equals("CacheTestArtikel2'"))
                element2 = element;
        }
        
        DatenbankTestInsert datenbankTestInsert = new DatenbankTestInsert("jdbc:derby://localhost:1527/db_DigitalerZwilling", "org.apache.derby.jdbc.ClientDriver", "db_user", "SB0222");
        datenbankTestInsert.datenbankUpdate("UPDATE ARTIKEL SET BEZEICHNUNG='CacheTestArtikel1'");
        datenbankTestInsert.close();
    }

    @Override
    public void testUpdateAll() throws DBNotFoundExeption, QueryExeption {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
