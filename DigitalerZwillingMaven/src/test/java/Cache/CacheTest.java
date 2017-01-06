/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
import javax.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author florian
 */
public abstract class CacheTest {
    
    @Test
    abstract public void testUpdate();
    
    @Test
    abstract public void testUpdateAll();
    
    @Test
    public void testGetById(){
        
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
        if(state != getCache().isState())
            Assert.assertTrue("Auto-Update", state != getCache().isState());
        Thread.sleep(500);
        if(state != getCache().isState())
            Assert.assertTrue("Auto-Update", state != getCache().isState());
        Thread.sleep(500);
        if(state != getCache().isState())
            Assert.assertTrue("Auto-Update", state != getCache().isState());
        Thread.sleep(500);
        if(state != getCache().isState())
            Assert.assertTrue("Auto-Update", state != getCache().isState());
    }
    
    abstract public Cache getCache();
}
