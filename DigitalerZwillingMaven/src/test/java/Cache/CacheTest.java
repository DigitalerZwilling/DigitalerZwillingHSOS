/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import DatenKlassen.Element;
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
    abstract public void testGetById();
    
    @Test
    public void testGetAll(){
        Cache cache = this.getCache();
        for(Element element : cache.getAll()){
            
        }
    }
    
    @Test
    public void testCacheSwitch() throws DBErrorExeption{
        Cache cache = this.getCache();
        boolean state = cache.isState();
        cache.update();
        Assert.assertTrue("Cache Switch", state != cache.isState());
    }
    
    @Test
    abstract public void testAutoUpdate();
    
    abstract public Cache getCache();
}
