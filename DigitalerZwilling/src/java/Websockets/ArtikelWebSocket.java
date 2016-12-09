/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import Cache.ArtikelCache;
import Cache.Cache;
import Cache.Updater.Updater;
import javax.inject.Inject;
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
<<<<<<< HEAD
public class ArtikelWebSocket extends WebSocketConfig {

    @Inject
    ArtikelSessionRegister artikelSessionRegister;
    //private WebSocketConfig webSocketConfig;

    @OnMessage
    public void messageReceiver(String message) {
        System.out.println(message);
        this.setId(Long.parseLong(message));
        this.setKlasseninfo("Artikel");
        this.artikelSessionRegister.addSession(this);
        this.fertigRegistriert();

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

=======
public class ArtikelWebSocket extends WebSocket{
    @Inject
    Updater webSocketUpdater;
    
    @Inject
    ArtikelCache artikelCache;
  
    @OnMessage
    public void messageReceiver(String message) {
        if (message.equals("LIST")){
            this.setId(null);
        }
        else{
            this.setId(Long.parseLong(message));
        }
        this.webSocketUpdater.addWebSocket(this);
        this.setRegistriert(Boolean.TRUE);
    }

    @OnOpen
    public void onOpen(Session session) {
      this.setSession(session);
    }
 
>>>>>>> refs/remotes/origin/master
    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     */
    @OnClose
<<<<<<< HEAD
    public void onClose(Session session) {
        this.nichtmehrRegistriert();
        this.artikelSessionRegister.remove(this);
        System.out.println("Session " + session.getId() + " has ended");
    }

=======
    public void onClose(Session session){
        this.setRegistriert(Boolean.FALSE);
        this.webSocketUpdater.removeWebSocket(this);
    }

    @Override
    protected Cache getCache() {
        return artikelCache;
    }
>>>>>>> refs/remotes/origin/master
}
