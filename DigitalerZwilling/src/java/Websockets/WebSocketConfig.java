/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import javax.websocket.Session;

/**
 *
 * @author user
 */
public class WebSocketConfig {
    private Session session;
    private Long id;
    private String klasseninfo;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlasseninfo() {
        return klasseninfo;
    }

    public void setKlasseninfo(String klasseninfo) {
        this.klasseninfo = klasseninfo;
    }
    
}
