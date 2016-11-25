/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author User
 */
@ApplicationScoped
public class UpdaterThread implements Runnable{

    @Inject
    Updater updater;
    
    @Override
    public void run() {
        updater.update();
    }    
}
