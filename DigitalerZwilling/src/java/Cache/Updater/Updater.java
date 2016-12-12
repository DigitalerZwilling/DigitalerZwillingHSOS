/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import Cache.Cache;
import Cache.Exeption.DBErrorExeption;
import Websockets.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
//import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
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
    
    @EJB
    SelfTimer sT;
    /*@Resource
    private TimerService timerService;
    
    private Timer timer;*/
    
    public Updater(){
        caches = new ArrayList<>();
        webSockets = new ArrayList<>();
        //timer = timerService.createTimer(500, 500, "New Updater interval Timer");
        System.out.println("vor erstellt!!!!!!!!!!!!!!!!!!!!!");
    }
    @PostConstruct
    public void init(){
        //timer = timerService.createTimer(0, 500, "New Updater interval Timer");
        System.out.println("vor erstellt!!!!!!!!!!!!!!!!!!!!!");
        sT.cancelTimer("New Updater interval Timer");
        System.out.println("vor erstellt!!!!!!!!!!!!!!!!!!!!!");
        sT.createTimer(1000, 1000, "New Updater interval Timer");
        System.out.println("erstellt!!!!!!!!!!!!!!!!!!!!!");
        //timerService.crea
    }
    
    Updater(int ms){
        caches = new ArrayList<>();
        webSockets = new ArrayList<>();
        //timer = timerService.createTimer(ms, ms, "New Updater interval Timer");
    }
    
    public void updateWebSockets(){
        for(WebSocket webSocket: webSockets){
            webSocket.update();
        }
    }
    
    public void updateCaches(){
        for(Cache cache: caches){
            try {
                cache.update();
            } catch (DBErrorExeption ex) {
                java.util.logging.Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //@Timeout
    public void updateAll(Timer timer){
        System.out.println("timer!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
    
    public void addWebSocket(WebSocket webSocket){
        this.webSockets.add(webSocket);
    }
    
    public void removeWebSocket(WebSocket webSocket){
      this.webSockets.remove(webSocket);
    }
    @PreDestroy
    public void end(){
        sT.cancelTimer("New Updater interval Timer");
    }
}
