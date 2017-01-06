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
import DatenbankSchnittstelle.DatenbankSchnittstelle;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
    @Test
    @Override
    public void testUpdate(){
        System.out.println("update");
        System.out.println(cache);
    }

    /**
     * Test of updateAll method, of class ArtikelCache.
     */
    @Test
    @Override
    public void testUpdateAll(){
        System.out.println("updateAll");
        System.out.println(cache);
    }
    
    @Test
    @Override
    public void testGetById(){
        System.out.println("getById");
    }
    
    @Test
    @Override
    public void testGetAll(){
        System.out.println("getAll");
        System.out.println(cache.getAll());
    }

    @Override
    public Cache getCache() {
        return cache;
    }
    
}
