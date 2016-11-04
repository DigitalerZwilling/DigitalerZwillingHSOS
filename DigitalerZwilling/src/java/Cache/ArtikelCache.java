/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author User
 */
public class ArtikelCache extends Cache{
    

    @Override
    public void update() {
        //pro ID machen also durch map iterieren leider...
       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void parseResultSet(ResultSet rs,int identifier) {
        switch (identifier){
            case 0:    ;//fuer update all
                break;
            case 1:    ;//fuer update erste sql-Anfrage
                break;
            case 2: ;//fuer 2. update sql Abfrage
                break;
            default:    ;//fuer Fehlerfaelle
                break;
        }
    
            
            
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
