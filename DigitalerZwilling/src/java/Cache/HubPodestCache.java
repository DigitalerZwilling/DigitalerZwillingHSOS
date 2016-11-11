/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import DatenKlassen.HubPodest;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author User
 */
public class HubPodestCache extends Cache{

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static HubPodestCache instance;

    public static synchronized Cache getInstance(){
        if(HubPodestCache.instance == null) {
            HubPodestCache.instance = new HubPodestCache();
        }
        return instance;
    }
    
}
