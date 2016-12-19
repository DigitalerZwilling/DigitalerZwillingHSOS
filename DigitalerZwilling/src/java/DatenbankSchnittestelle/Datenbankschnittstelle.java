/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankSchnittestelle;

import DatenbankSchnittestelle.Exeption.DBNotFoundExeption;
import DatenbankSchnittestelle.Exeption.QueryExeption;
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
 * @author chrklaas
 */
@ApplicationScoped
public class Datenbankschnittstelle {

    private Connection data;                                                        // Datenbank Verbindung
    //-----------------------------------------------------------------------------

    public Datenbankschnittstelle() throws DBNotFoundExeption{
        String DbUrl = "jdbc:derby://localhost:1527/db_DigitalerZwilling";
        String DbCd = "org.apache.derby.jdbc.ClientDriver";
        String DbUser = "root";
        String DbPw = "Didpw4df";
        //String DbUser = "db_user";
        //String DbPw = "SB0222";
        
        try {
            Class.forName(DbCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(DbUrl, DbUser, DbPw);
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundExeption();
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
        }
    }
    
    public Datenbankschnittstelle(String DbUrl, String DbCd, String DbUser, String DbPw) throws DBNotFoundExeption {
        try {
            Class.forName(DbCd).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(DbUrl, DbUser, DbPw);
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            throw new DBNotFoundExeption();
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
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
     * @throws DatenbankSchnittestelle.Exeption.DBNotFoundExeption
     * @throws DatenbankSchnittestelle.Exeption.QueryExeption
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
                    rsMap.put(rsmd.getColumnName(i), new ArrayList<>());
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
                Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
                throw new QueryExeption();
            }
        }
        return rsMap;
    }
}
