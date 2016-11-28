/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Websockets.WebSocketConfig;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author user
 */
@ApplicationScoped
public class ArtikelSessionRegister implements WebSocketSessionRegister{

    @Override
    public void addSession(WebSocketConfig webSocketConfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(WebSocketConfig webSocketConfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateWebSockets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
