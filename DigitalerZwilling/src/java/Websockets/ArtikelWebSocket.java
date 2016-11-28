/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import Cache.ArtikelCache;
import Websockets.SessionRegister.ArtikelSessionRegister;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author user
 */
@ServerEndpoint("/ArtikelWebSocket")
public class ArtikelWebSocket extends WebSocketConfig{
  @Inject ArtikelSessionRegister artikelSessionRegister;
  //private WebSocketConfig webSocketConfig;
  
  @OnMessage
  public void messageReceiver(String message) {
      System.out.println(message);
      this.setId(Long.parseLong(message));
      this.setKlasseninfo("Artikel");
      
      /*try {
          //System.out.println("Received message:" + message);
          session.getBasicRemote().sendObject(aCache.getById(1L));
          System.out.println("onMessage: " + aCache.getById(1L).getBezeichnung());
          aCache.update();
      } catch (IOException ex) {
          Logger.getLogger(ArtikelWebSocket.class.getName()).log(Level.SEVERE, null, ex);
      /*} catch (EncodeException ex) {
          Logger.getLogger(ArtikelWebSocket.class.getName()).log(Level.SEVERE, null, ex);
      */
  }

  @OnOpen
  public void onOpen(Session session) {
    //this.session=session;
    //this.session=session;
    this.setSession(session);
    System.out.println("onOpen: " + session.getId());
    //this.messageReceiver("hi");
    
    //System.out.println("onOpen: Notification list size: " + sessions.size());
  }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        
        
        System.out.println("Session " +session.getId()+" has ended");
    }
    
}
