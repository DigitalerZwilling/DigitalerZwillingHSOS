/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import Cache.Cache;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@ApplicationScoped
public class Updater {
    private final List<Cache> caches;
    
    @Inject
    UpdaterThread updaterThread;
    
    @Resource
    private ManagedThreadFactory managedThreadFactory;
    
    Updater(){
        caches = new ArrayList<>();
    }
    
    void update(){
        for(Cache cache: caches){
            cache.update();
        }
    }
    
    public void registerCache(Cache cache){
        caches.add(cache);
    }
    
    public void runTask(){
        Thread newThread = managedThreadFactory.newThread(updaterThread);
        newThread.start();
    }
}
