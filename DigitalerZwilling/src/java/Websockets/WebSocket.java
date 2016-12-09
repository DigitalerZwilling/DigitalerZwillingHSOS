/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import Cache.Cache;
import java.util.Objects;
import javax.websocket.Session;

/**
 *
 * @author user
 */
public abstract class WebSocket {
    private Session session;
    private Long id;
    private Boolean registriert;

    public WebSocket() {
        this.registriert = Boolean.FALSE;
        System.out.println("config hier!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    public void update(){
        //Update Methode fehlt!!!!
    }
    
    protected abstract Cache getCache();

    public Boolean getRegistriert() {
        return registriert;
    }

    public void setRegistriert(Boolean registriert) {
        this.registriert = registriert;
    }
    
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.session);
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WebSocket other = (WebSocket) obj;
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
