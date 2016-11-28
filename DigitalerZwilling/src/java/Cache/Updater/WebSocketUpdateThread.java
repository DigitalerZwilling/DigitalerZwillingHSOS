/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import javax.inject.Inject;

/**
 *
 * @author florian
 */
public class WebSocketUpdateThread implements Runnable{
    @Inject
    Updater updater;
    
    private boolean running;

    public boolean isRunning() {
        return running;
    }
    
    @Override
    public void run() {
        running = true;
        updater.updateSockets();
        running = false;
    } 
}
