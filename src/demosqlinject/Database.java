/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demosqlinject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hammerhead
 */
public class Database implements DatabaseInterface{

    private String password;
    private String userName;
    private String databaseName;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public Database(String databaseName, String userName, String password) {

        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;

    }

    @Override
    public boolean vulnLogin(String inputUsername, String userPassword) {
        boolean check = true; //fix
        try {
            try (Connection c = setUpConnection()) {

                String query = "select * from admins where username='" + inputUsername + "' and password='" + userPassword + "'";
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {

                    check = true;
                } else {

                    rs.close();

                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return check;

    }

    @Override
    public boolean secureLogin(String inputUsername, String userPassword) {
        boolean check = false;

        try {
            try (Connection c = setUpConnection()) {

                String query = "SELECT * FROM admins WHERE username=? AND password=? ";
                pstmt = c.prepareStatement(query);
                pstmt.setString(1, inputUsername);
                pstmt.setString(2, userPassword);
                rs = pstmt.executeQuery();
                if (rs.next()) {

                    check = true;
                } else {

                    rs.close();

                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);

            }

        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return check;

    }

    public Connection setUpConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        Class.forName("org.mariadb.jdbc.Driver").newInstance();//Change this to your driver!
        String URL = "jdbc:mysql://localhost:3306/" + databaseName + "?user=" + userName + "&password=" + password + "";
        Connection c = DriverManager.getConnection(URL);
        return c;
    }

}
