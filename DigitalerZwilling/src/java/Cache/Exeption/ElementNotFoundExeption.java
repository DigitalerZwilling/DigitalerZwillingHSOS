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
public class ElementNotFoundExeption extends Exception {
    public ElementNotFoundExeption(){
        super("ElementNotFoundExeption");
    }
    
    public ElementNotFoundExeption(String str){
        super(str);
    }
}
