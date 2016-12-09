/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatenbankSchnittestelle.Exeption;

/**
 *
 * @author florian
 */
public class QueryExeption extends Exception {

    public QueryExeption() {
        super("QueryExetion");
    }

    public QueryExeption(String str) {
        super(str);
    }
}
