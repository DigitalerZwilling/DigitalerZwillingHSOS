package Cache;

import Cache.Exeption.DBErrorExeption;
import Cache.Exeption.ElementNotFoundExeption;
import Cache.Updater.Updater;
import DatenKlassen.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    protected Map<Long,Element> elements[];
    
    @Inject
    private Updater updater;

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
    
    public Element getById(Long id) throws ElementNotFoundExeption{
        if(!(state==true?elements[1]:elements[0]).containsKey(id)) throw new ElementNotFoundExeption();
        
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
    
    public Cache() {}
    
    @PostConstruct
    public void init(){
        elements = new Map[2];
        elements[0] = new HashMap<>();
        elements[1] = new HashMap<>();
        updater.registerCache(this);
        
        try {
            this.updateAll();
        } catch (DBErrorExeption ex) {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    abstract public void update() throws DBErrorExeption;
    abstract public void updateAll() throws DBErrorExeption;
}
