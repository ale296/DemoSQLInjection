/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demosqlinject;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author hammerhead
 */
public interface DatabaseInterface {
    public boolean vulnLogin(String username, String password);
    public boolean secureLogin(String username, String password);
    public Connection setUpConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException;
    
}
