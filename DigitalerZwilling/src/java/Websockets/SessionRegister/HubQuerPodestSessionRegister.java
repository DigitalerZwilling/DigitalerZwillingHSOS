/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Cache.Exeption.ElementNotFoundExeption;
//import Cache.HubQuerPodestCache;
import Websockets.WebSocketConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author user
 */
public class HubQuerPodestSessionRegister extends WebSocketSessionRegister{

    @Override
    public void updateWebSockets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*    @Inject private HubQuerPodestCache hubQuerPodestCache;
    
    @Override
    public void updateWebSockets() {
        for (WebSocketConfig session : this.sessions ){
            if (session.istRegistriert()){
                try {
                    if(session.getIstListe()){
                        session.getSession().getBasicRemote().sendText(this.hubQuerPodestCache.getAll().toString());  //mit toJson versehen
                    }
                    else{
                        session.getSession().getBasicRemote().sendText(this.hubQuerPodestCache.getById(session.getId()).toString());  //mit toJson versehen
                    }
                
                } catch (IOException ex) {
                    Logger.getLogger(ArtikelSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (ElementNotFoundExeption ex) {
                    Logger.getLogger(HubQuerPodestSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/
}
