/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache.Updater;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

/**
 *
 * @author user
 */
@Dependent
public class Zeitproducer {
    @Produces @Zeitgeber public int Zeit=500;
}
