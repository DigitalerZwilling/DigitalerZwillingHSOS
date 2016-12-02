/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Websockets.WebSocketConfig;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author user
 */
public abstract class WebSocketSessionRegister {
    protected List<WebSocketConfig> sessions = new ArrayList<>();
    // fuer Websockets
    public void addSession(WebSocketConfig webSocketConfig){
        this.sessions.add(webSocketConfig);
    };
    public void remove(WebSocketConfig webSocketConfig){
      this.updateWebSockets();
      this.sessions.remove(webSocketConfig);
    };
    // fuer updater
    abstract public void updateWebSockets();
}
