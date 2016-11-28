/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import Cache.Cache;
import WebSockets.WebSocket;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author User
 */
@ApplicationScoped
public class Updater {
    private final List<Cache> caches;
    private final List<WebSocket> webSockets;
    
    @Inject
    private WebSocketUpdateThread webSocketUpdateThread;
    private Thread webSocketThread;
    
    @Inject
    private CacheUpdateThread cacheUpdateThread;
    private Thread cacheThraed;
    
    @Resource
    private ManagedThreadFactory managedThreadFactory;
    
    Updater(){
        caches = new ArrayList<>();
        webSockets = new ArrayList<>();
    }
    
    public void updateSockets(){
        for(WebSocket webSocket: webSockets){
            webSocket.update();
        }
    }
    
    public void updateCaches(){
        for(Cache cache: caches){
            cache.update();
        }
    }
    
    public void updateAll(){
        for(Cache cache: caches){
            cache.toggleState();
        }
        
        if(!cacheUpdateThread.isRunning())
            cacheThraed = managedThreadFactory.newThread(cacheUpdateThread);
        else
            Logger.getLogger("TIMEOUT: Cache update takes to long...");
        if(!webSocketUpdateThread.isRunning())
            webSocketThread = managedThreadFactory.newThread(webSocketUpdateThread);
        else
            Logger.getLogger("TIMEOUT: WebSocket update takes to long...");
    }
    
    public void registerCache(Cache cache){
        caches.add(cache);
    }
    
    public void registerWebSocket(WebSocket webSocket){
        webSockets.add(webSocket);
    }
    
    public boolean unRegisterWebSocket(WebSocket webSocket){
        return webSockets.remove(webSocket);
    }
}
