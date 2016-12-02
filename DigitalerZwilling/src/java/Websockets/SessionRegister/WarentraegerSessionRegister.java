/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Cache.WarentraegerCache;
import Websockets.WebSocketConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@ApplicationScoped
public class WarentraegerSessionRegister extends WebSocketSessionRegister{

    @Inject private WarentraegerCache warentraegerCache;
    
    @Override
    public void updateWebSockets() {
        for (WebSocketConfig session : this.sessions ){
            try {
                if(session.getIstListe()){
                    session.getSession().getBasicRemote().sendText(this.warentraegerCache.getAll().toString());  //mit toJson versehen
                }
                else{
                    session.getSession().getBasicRemote().sendText(this.warentraegerCache.getById(session.getId()).toString());  //mit toJson versehen
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ArtikelSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
