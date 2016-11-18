/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Updater.Updater;
import DatenKlassen.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author User
 * 
 * getAll -> Funktionaler aufruf möglich.
 */
public abstract class Cache {
    static protected boolean state;
    
    protected Map<Long,Element> elements[];
    
    @Inject
    Updater updater;

    public Map<Long, Element>[] getElements() {
        return elements;
    }

    public void setElements(Map<Long, Element> elements[]) {
        this.elements = elements;
    }

    public static boolean isState() {
        return state;
    }

    public static void setState(boolean state) {
        Cache.state = state;
    }
    
    public static void toggleState() {
        state = !state;
    }
    
    public Element getById(Long id) {
        return state==true?elements[1].get(id):elements[0].get(id);
    }
    
    public List<Element> getAll() {
        List<Element> elementList = new ArrayList<>();
        Map<Long,Element> currentElemets = state==true?elements[1]:elements[0];
        for(Map.Entry<Long,Element> entry : currentElemets.entrySet()){
            elementList.add(entry.getValue());
        }
        return elementList;
    }
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Cache() {
        elements = new Map[2];
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        updater.registerCache(this);
    }
    
    abstract public void update();
    abstract public void updateAll();
}
