/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets.SessionRegister;

import Websockets.WebSocketConfig;

/**
 *
 * @author user
 */
public interface WebSocketSessionRegister {
    // fuer Websockets
    public void addSession(WebSocketConfig webSocketConfig);
    public void remove(WebSocketConfig webSocketConfig);
    
    // fuer updater
    public void updateWebSockets();
}
