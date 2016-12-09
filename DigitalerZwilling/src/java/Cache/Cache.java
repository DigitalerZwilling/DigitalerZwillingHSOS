/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import Cache.Exeption.DBErrorExeption;
import Cache.Exeption.ElementNotFoundExeption;
import Cache.Updater.Updater;
import DatenKlassen.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author User
 *
 * getAll -> Funktionaler aufruf möglich.
 */
public abstract class Cache {

    static protected boolean state;

    protected Map<Long, Element> elements[];

    @Inject
    Updater updater;

    public Map<Long, Element>[] getElements() {
        return elements;
    }

    public void setElements(Map<Long, Element> elements[]) {
        this.elements = elements;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        Cache.state = state;
    }

    public void toggleState() {
        state = !state;
    }

    public Element getById(Long id) throws ElementNotFoundExeption {
        if (id < 0 || id >= (state == true ? elements[1] : elements[0]).size()) {
            throw new ElementNotFoundExeption();
        }

        return state == true ? elements[1].get(id) : elements[0].get(id);
    }

    public List<Element> getAll() {
        List<Element> elementList = new ArrayList<>();
        Map<Long, Element> currentElemets = state == true ? elements[1] : elements[0];
        for (Map.Entry<Long, Element> entry : currentElemets.entrySet()) {
            elementList.add(entry.getValue());
        }
        return elementList;
    }

    public Cache() {
    }

    @PostConstruct
    public void init() {
        elements = new Map[2];
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        updater.registerCache(this);
    }

    abstract public void update() throws DBErrorExeption;

    abstract public void updateAll();
}
