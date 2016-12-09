/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Exeption;

/**
 *
 * @author florian
 */
public class DBErrorExeption extends Exception {

    public DBErrorExeption(String string) {
        super(string);
    }

    public DBErrorExeption() {
        super("DBErrorExeption");
    }
}
