/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Cache.ArtikelCache;
import Websockets.WebSocketConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;

/**
 *
 * @author user
 */
@ApplicationScoped
public class ArtikelSessionRegister extends WebSocketSessionRegister{
    @Inject ArtikelCache artikelCache;

    @Override   //mit Timer annotation versehen
    public void updateWebSockets() {
        for (WebSocketConfig session : this.sessions ){
            if (session.istRegistriert()){
                try {
                    if(session.getIstListe()){
                        session.getSession().getBasicRemote().sendText(this.artikelCache.getAll().toString());  //mit toJson versehen
                    }
                    else{
                        session.getSession().getBasicRemote().sendText(this.artikelCache.getById(session.getId()).toString());  //mit toJson versehen
                    }
  
                } catch (IOException ex) {
                    Logger.getLogger(ArtikelSessionRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
