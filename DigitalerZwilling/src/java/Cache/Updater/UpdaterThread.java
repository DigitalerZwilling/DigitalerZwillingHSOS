/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import Cache.Updater.Updater;

/**
 *
 * @author User
 */
public class UpdaterThread implements Runnable{

    @Override
    public void run() {
        Updater.update();
    }
    
}