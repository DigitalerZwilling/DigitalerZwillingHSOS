/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Updater.Updater;
import DatenbankSchnittestelle.Datenbankschnittstelle;
import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import DatenKlassen.Artikel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import junit.framework.Assert;


/**
 *
 * @author user
 */


public class ArtikelCacheIT {
    private DbClass db;
    private String sqlStatement1="SELECT id_artikel,zeitstempel,user_parameter from Artikel";
    private String sqlStatement2="SELECT id_artikel,bezeichnung,zeitstempel,user_parameter from Artikel";
    private final Datenbankschnittstelle d;
    private final Updater u;
    //private String sqlStatement3=("SELECT id_warentraeger from Artikel_Warentraeger where id_artikel="+id);
    public ArtikelCacheIT() {
        db=new DbClass();
        d= Mockito.mock(Datenbankschnittstelle.class);
        u=Mockito.mock(Updater.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of update method, of class ArtikelCache.
     */
    @Test
    public void testUpdate() throws Exception {

        System.out.println("update");
        Mockito.when(d.datenbankAnfrage(sqlStatement1)).thenReturn(db.datenbankAnfrage(sqlStatement1));
        ArtikelCache instance = new ArtikelCache();
        try {
                //Mockito.verify(u).registerCache(instance);
                instance.getClass().getField("datenbankschnittstelle").set(Datenbankschnittstelle.class, d);
                instance.update();
                Assert.assertEquals("test5",instance.getById(1l).getBezeichnung());
                System.out.println("!!!!!!!!dfdsfds!!!!!!!!!!!!!!!!!");
                System.out.println(instance.getById(1l).toJson());
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ArtikelCacheIT.class.getName()).log(Level.SEVERE, null, ex);
            }
        
 
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateAll method, of class ArtikelCache.
     */
    @Test
    public void testUpdateAll()  {
        try {
            
            System.out.println("updateAll");
            ArtikelCache instance = new ArtikelCache();
            //final Datenbankschnittstelle d= Mockito.mock(Datenbankschnittstelle.class);
            
            Mockito.when(d.datenbankAnfrage(sqlStatement2)).thenReturn(db.datenbankAnfrage(sqlStatement2));
            try {
                //Mockito.verify(u).registerCache(instance);
                System.out.println("!!!!!!!!dfdsfds!!!!!!!!!!!!!!!!!1");
                instance.getClass().getField("datenbankschnittstelle").set(Datenbankschnittstelle.class, d);
                System.out.println("!!!!!!!!dfdsfds!!!!!!!!!!!!!!!!!2");
                instance.updateAll();
                System.out.println("!!!!!!!!dfdsfds!!!!!!!!!!!!!!!!!3");
                Assert.assertEquals(1,instance.getAll().size());
                System.out.println("!!!!!!!!dfdsfds!!!!!!!!!!!!!!!!!4");
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ArtikelCacheIT.class.getName()).log(Level.SEVERE, null, ex);
            }
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
            
            
            // TODO review the generated test code and remove the default call to fail.
            
            //fail("The test case is a prototype.");
        } catch (DBNotFoundExeption ex) {
            Logger.getLogger(ArtikelCacheIT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (QueryExeption ex) {
            Logger.getLogger(ArtikelCacheIT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
