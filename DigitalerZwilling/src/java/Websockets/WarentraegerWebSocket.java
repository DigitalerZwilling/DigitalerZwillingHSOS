/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import Websockets.SessionRegister.WarentraegerSessionRegister;
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
@ServerEndpoint("/WarentraegerWebSocket")
public class WarentraegerWebSocket extends WebSocketConfig{

  @Inject WarentraegerSessionRegister warentraegerSessionRegister;
  
  

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
          this.setIstListe(Boolean.TRUE);
          this.setId(0L);
      }
      else{
          this.setIstListe(Boolean.FALSE);
          this.setId(Long.parseLong(message));
      }
      this.setKlasseninfo("Warentraeger");
      this.warentraegerSessionRegister.addSession(this);
      this.fertigRegistriert();
  }

  @OnOpen
  public void onOpen(Session session) {
    this.setSession(session);
    System.out.println("onOpen: " + session.getId());
  }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        this.nichtmehrRegistriert();
        this.warentraegerSessionRegister.remove(this);
        System.out.println("Session " +session.getId()+" has ended");
    }
    
    
}
