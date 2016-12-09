/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import Cache.Cache;
import Cache.TransportbandCache;
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
@ServerEndpoint("/TransportbandWebSocket")
<<<<<<< HEAD
public class TransportbandWebSocket extends WebSocketConfig {

    @Inject
    TransportbandSessionRegister transportbandSessionRegister;

    /**
     *
     * zum konfigurieren der Verbindung nach Verbindungsaufbau Schluesselwort
     * "LIST" gibt an das Listen der Objekte in Json geschickt werden ansonsten
     * muss in der message die id des zu erwartenen Objektes enthalten sein
     *
     */
    @OnMessage
    public void messageReceiver(String message) {
        if (message.equals("LIST")) {
            this.setIstListe(Boolean.TRUE);
            this.setId(0L);
        } else {
            this.setIstListe(Boolean.FALSE);
            this.setId(Long.parseLong(message));
        }
        this.setKlasseninfo("Warentraeger");
        this.transportbandSessionRegister.addSession(this);
        this.fertigRegistriert();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.setSession(session);
        System.out.println("onOpen: " + session.getId());
    }

=======
public class TransportbandWebSocket extends WebSocket{

    @Inject
    Updater webSocketUpdater;
    
    @Inject
    TransportbandCache transportbandCache;  

  /**
   * 
   * zum konfigurieren der Verbindung nach Verbindungsaufbau
   * Schluesselwort "LIST" gibt an das Listen der Objekte in Json geschickt werden
   * ansonsten muss in der message die id des zu erwartenen Objektes enthalten sein
   * 
   */
  
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
        this.transportbandSessionRegister.remove(this);
        System.out.println("Session " + session.getId() + " has ended");
=======
    public void onClose(Session session){
        this.setRegistriert(Boolean.FALSE);
        this.webSocketUpdater.removeWebSocket(this);
    }

    @Override
    protected Cache getCache() {
        return transportbandCache;
>>>>>>> refs/remotes/origin/master
    }
}
