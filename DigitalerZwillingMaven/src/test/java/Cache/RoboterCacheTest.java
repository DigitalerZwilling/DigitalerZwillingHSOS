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
public class RoboterCacheTest extends CacheTest{
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClasses(RoboterCache.class,Updater.class,CacheUpdateThread.class,WebSocketUpdateThread.class,DatenbankSchnittstelle.class,SelfTimer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    RoboterCache cache;

    /**
     * Test of update method, of class RoboterCache.
     */
    @Test
    @Override
    public void testUpdate(){
        System.out.println("update");
    }

    /**
     * Test of updateAll method, of class RoboterCache.
     */
    @Test
    @Override
    public void testUpdateAll(){
        System.out.println("updateAll");
    }

    @Override
    public void testGetById() {
    }

    @Override
    public void testGetAll() {
    }

    @Override
    public void testCacheSwitch() {
    }

    @Override
    public void testAutoUpdate() {
    }

    @Override
    public Cache getCache() {
        return cache;
    }
    
}
