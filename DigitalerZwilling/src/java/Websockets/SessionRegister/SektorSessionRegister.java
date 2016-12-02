/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Cache.Exeption.ElementNotFoundExeption;
import Cache.SektorCache;
import Websockets.WebSocketConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author user
 */
public class SektorSessionRegister extends WebSocketSessionRegister{
    @Inject private SektorCache sektorCache;
    
    @Override
    public void updateWebSockets() {
        for (WebSocketConfig session : this.sessions ){
            if (session.istRegistriert()){
                try {
                    if(session.getIstListe()){
                        session.getSession().getBasicRemote().sendText(this.sektorCache.getAll().toString());  //mit toJson versehen
                    }
                    else{
                        session.getSession().getBasicRemote().sendText(this.sektorCache.getById(session.getId()).toString());  //mit toJson versehen
                    }
                
                } catch (IOException ex) {
                    Logger.getLogger(ArtikelSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ElementNotFoundExeption ex) {
                    Logger.getLogger(SektorSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
