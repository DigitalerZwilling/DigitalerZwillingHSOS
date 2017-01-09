/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankTestInsert;

import DatenbankSchnittstelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittstelle.Exeption.QueryExeption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author florian
 */
@ApplicationScoped
public class DatenbankTestInsert {

    private Connection data;

    public DatenbankTestInsert(String DBUrl, String DBCd, String DBUser, String DBPw) throws DBNotFoundExeption{
        
        try {
            Class.forName(DBCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(DBUrl, DBUser, DBPw);
        } catch (SQLException ex) {
            Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundExeption();
        }
    }
    
    /**
     * Die Funktion uebermittelt das Statement an die Datenbank und ruft eine im
     * Parameter "goal" angegeben Funktion parseResult auf in der das ResultSet
     * uebergeben wurde. Anschließend werden ResultSet und Statement
     * geschlossen.
     *
     * @param sqlStatement sql-Anfrage an die Datenbank
     * @return Map die das Result set darstellt
     * @throws DatenbankSchnittstelle.Exeption.DBNotFoundExeption
     * @throws DatenbankSchnittstelle.Exeption.QueryExeption
     */
    public Map<String, List<String>> datenbankAnfrage(String sqlStatement) throws DBNotFoundExeption, QueryExeption {
        Map<String, List<String>> rsMap = new HashMap<>();

        if (data == null) {
            throw new DBNotFoundExeption();
        } else {
            try {
                Statement stmt = this.data.createStatement();
                ResultSet rs = stmt.executeQuery(sqlStatement);
                //----------------------------------------------------
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    rsMap.put(rsmd.getColumnName(i), new ArrayList<String>());
                }
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        rsMap.get(rsmd.getColumnName(i)).add(rs.getString(i));
                    }
                }
                //------------------------------------------------------
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatenbankTestInsert.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryExeption();
            }
        }
        return rsMap;
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
}
