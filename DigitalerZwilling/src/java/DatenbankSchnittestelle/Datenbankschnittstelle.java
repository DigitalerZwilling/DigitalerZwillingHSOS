/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankSchnittestelle;

import Cache.Cache;
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

/**
 *
 * @author chrklaas
 */
public class Datenbankschnittstelle {
    private static Datenbankschnittstelle instance=null;                                //statische Instanz des Singeltons
    
    //---------------------------------------------------------------------------
    //Datenbank verbindungs daten
    
    private final String _DbURL="jdbc:derby://localhost:1527/db_DigitalerZwilling";   //URL
    //MySQL
    //private final String _DbURL="jdbc:mysql://localhost:1527/db_DigitalerZwilling";   //URL
    private final String _DbUser="db_user";                                            //User
    private final String _DbPw="SB0222";                                              //Passswort
    //---------------------------------------------------------------------------
    private Connection data;                                                        // Datenbank Verbindung
    
    //-----------------------------------------------------------------------------
    private Datenbankschnittstelle(){ 
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(this._DbURL,this._DbUser,this._DbPw);
            
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
        }
    }
    //--------------------------------------------------------------------------------------------
    
    /*-----------------------------------------------------------------------------
    //MySQL
    private Datenbankschnittstelle(){ 
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.data = DriverManager.getConnection(this._DbURL,this._DbUser,this._DbPw);
            
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
            //throw new Exception("Fehler: Datenbankverbindung auf "+ this._DbURL+" nicht möglich");
        }
    }
    //--------------------------------------------------------------------------------------------*/
    
    /**
     * Gibt die vorhandene Instance zurueck bzw. erstellt wenn nötig die erste Instanz dieses Singeltons
     *
     * @return Instance dieses Singelton
     */
    public static Datenbankschnittstelle getInstance(){
        if(instance==null){
            instance=new Datenbankschnittstelle();
        }
        return instance;
    }
    //---------------------------------------------------------------------------------------------

    /**
     * Die Funktion uebermittelt das Statement an die Datenbank und ruft
     * eine im Parameter "goal" angegeben Funktion parseResult auf in der das ResultSet
     * uebergeben wurde. Anschließend werden ResultSet und Statement geschlossen.
     *
     * @param sqlStatement  sql-Anfrage an die Datenbank
     * @return Map die das Result set darstellt
     */
    public Map<String,List<String>> datenbankAnfrage(String sqlStatement){
        Map<String,List<String>> rsMap = new HashMap<>();
        try {
            Statement stmt=this.data.createStatement();
            ResultSet rs=stmt.executeQuery(sqlStatement);
            //----------------------------------------------------
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for(int i = 1;i<=columnCount;i++){
                rsMap.put(rsmd.getColumnName(i),new ArrayList<>());
            }
            while(rs.next()){
                for(int i = 1;i<=columnCount;i++){   
                    rsMap.get(rsmd.getColumnName(i)).add(rs.getString(i));
                }
            }
            //------------------------------------------------------
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankschnittstelle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsMap;
    }
    
}
