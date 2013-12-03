/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author B.Revanth
 */
public class ConnectionStatus {

    public boolean connAvail() throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://10.10.11.56:3306/bigco", "root", "root");
        } catch (SQLException e) {
        }

        if (connection != null) {
            //System.out.println("You made it, take control your database now!");
            return true;
        } else {
            //System.out.println("Failed to make connection!");
            return false;
        }
    }
}
