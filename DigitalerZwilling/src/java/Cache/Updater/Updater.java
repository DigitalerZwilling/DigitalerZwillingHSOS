/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import Cache.Cache;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Updater {
    
    private Updater(){}
    
    private static final List<Cache> caches = new ArrayList<>();
    
    static void update(){
        for(Cache cache: caches){
            cache.update();
        }
    }
    
    public static void registerCache(Cache cache){
        caches.add(cache);
    }
}
