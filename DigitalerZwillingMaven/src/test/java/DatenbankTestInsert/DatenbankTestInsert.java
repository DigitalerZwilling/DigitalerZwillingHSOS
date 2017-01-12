/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankTestInsert;

import DatenbankSchnittstelle.DatenbankSchnittstelle;
import DatenbankSchnittstelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittstelle.Exeption.QueryExeption;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author florian
 */
@ApplicationScoped
public class DatenbankTestInsert extends DatenbankSchnittstelle{

    public DatenbankTestInsert() throws DBNotFoundExeption {
        super();
    }

    public void datenbankUpdate(String sqlStatement)  throws DBNotFoundExeption, QueryExeption {
        if (data == null){
            throw new DBNotFoundExeption();
        } else {
            try {
                Statement stmt = data.createStatement();
                stmt.executeUpdate(sqlStatement);
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryExeption();
            }
            
        }
    }
    
    public void close() throws QueryExeption{
        if(data != null)
            try {
                data.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
            throw new QueryExeption();
        }
    }
}
