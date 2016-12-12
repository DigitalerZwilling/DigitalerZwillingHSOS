/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Singleton
public class SelfTimer {
    
    @Inject Updater updater;
    
    @Resource
    TimerService timerS;
    
    
    public void createTimer(int Startwert, int Intervall, String info){
        //timerS.
        System.out.println("imstimerS!!!!!!!!!!!!!!!!!!!!!");
        Timer timer = timerS.createTimer(Startwert, Intervall, info);
    }
    public void cancelTimer(String info){
        for (Timer timer : timerS.getTimers()) 
               if (timer.getInfo().equals(info))
                   timer.cancel();
    }
    
    @Timeout
    public void execute(Timer timer){
        updater.updateAll(timer);
    }
}
