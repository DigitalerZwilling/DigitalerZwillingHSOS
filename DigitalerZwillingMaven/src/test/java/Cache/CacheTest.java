/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import Cache.Exeption.ElementNotFoundExeption;
import DatenKlassen.Element;
import DatenbankSchnittstelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittstelle.Exeption.QueryExeption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author florian
 */
public abstract class CacheTest {
    
    @Test
    abstract public void testUpdate() throws DBNotFoundExeption, QueryExeption, DBErrorExeption;
    
    @Test
    abstract public void testUpdateAll() throws DBNotFoundExeption, QueryExeption;
    
    @Test
    public void testGetById(){
        System.out.println("testGetByID");
        for(Long i=new Long(0);i<= Long.MAX_VALUE;i++){
            try {
                getCache().getById(i);
            } catch (ElementNotFoundExeption ex) {
                Assert.assertTrue("Elmenet not Found", true);
                return;
            }
        }
        Assert.assertTrue("Elmenet not Found", false);
    }
    
    @Test
    public void testGetAll(){
        for(Element element : getCache().getAll()){
            
        }
    }
    
    @Test
    public void testAutoUpdate() throws InterruptedException{
        System.out.println("testAutoUpdate");
        boolean state = getCache().isState();
        /*while(state == getCache().isState())
            System.out.println("wait....");*/
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 0.5", state != getCache().isState());
            System.out.println("Auto-Update 0.5");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 1.0", state != getCache().isState());
            System.out.println("Auto-Update 1.0");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 1.5", state != getCache().isState());
            System.out.println("Auto-Update 1.5");
            return;
        }
        
        Thread.sleep(500);
        if(state != getCache().isState()){
            Assert.assertTrue("Auto-Update 2.0", state != getCache().isState());
            System.out.println("Auto-Update 2.0");
            return;
        }
        
        Assert.assertTrue("Auto-Update", state != getCache().isState());
        System.out.println("Auto-Update --> FAIL!!!");
    }
    
    abstract public Cache getCache();
}
