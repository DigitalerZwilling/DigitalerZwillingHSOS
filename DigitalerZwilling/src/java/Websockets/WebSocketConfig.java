/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websockets;

import java.util.Objects;
import javax.websocket.Session;

/**
 *
 * @author user
 */
public class WebSocketConfig {
    private Session session;
    private Long id;
    private String klasseninfo;
    private Boolean istListe;

    public WebSocketConfig() {
        this.istListe = false;
        System.out.println("config hier!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public Boolean getIstListe() {
        return istListe;
    }

    public void setIstListe(Boolean istListe) {
        this.istListe = istListe;
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

    public String getKlasseninfo() {
        return klasseninfo;
    }

    public void setKlasseninfo(String klasseninfo) {
        this.klasseninfo = klasseninfo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.session);
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.klasseninfo);
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
        final WebSocketConfig other = (WebSocketConfig) obj;
        if (!Objects.equals(this.klasseninfo, other.klasseninfo)) {
            return false;
        }
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
