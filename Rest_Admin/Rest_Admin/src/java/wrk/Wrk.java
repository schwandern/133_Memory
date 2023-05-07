/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wrk;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jni.User;

/**
 *
 * @author reyx
 */
public class Wrk {

    private String port;
    private String dbName;

    public Wrk(String port, String dbName) {
        this.port = port;
        this.dbName = dbName;
    }

    /**
     * Ouverture d'une connexion sur la base de données. Ajouter
     * mysql-connector-j-....jar dans les librairies.
     *
     * @return true si tout s'est bien passé, sinon false
     */
    private Connection dbConnexion;

    public boolean openConnexion() {
        //5.182.248.18
        final String url = "jdbc:mysql://5.182.248.18:" + port + "/" + dbName + "?serverTimezone=CET";
        final String user = "reyx_adminRey";
        final String pw = "(5?,XX=iyt!C"; 
        boolean result = false;

        try {
            //nécessaire pour fonctionnement en web
            //"com.mysql.jdbc.cj.Driver"

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Connexion au driver JDBC à échoué!\n" + ex.getMessage());
        }
        try {
            dbConnexion = DriverManager.getConnection(url, user, pw);
            //System.out.println("Connection successfull");
            result = true;

        } catch (SQLException ex) {
            System.out.println("Connexion à la BD a échouée!\n" + ex.getMessage());
        }
        return result;
    }

    /**
     * Cette méthode permet de fermer la connexion à la base de données après
     * que la requête ait été envoyée.
     *
     * @return true si tout s'est bien passé, sinon false
     */
    public boolean closeConnexion() {
        boolean result = false;
        try {
            if (dbConnexion != null) {
                if (!dbConnexion.isClosed()) {
                    dbConnexion.close();
                } else {
                }
            } else {
            }
            result = true;
        } catch (SQLException e) {
        }
        return result;
    }

    public String GetMdp(String user) {
        boolean resultLogin = false;

        ArrayList<String> lstUsers = null;
        boolean result = openConnexion();
        if (result) {
            System.out.println("connection ok");
            PreparedStatement ps = null;

            String passwordDB = "";
            try {
                ps = dbConnexion.prepareStatement("SELECT password FROM t_user WHERE user like (?) ");
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    passwordDB = (String) rs.getString("password");

                }
                rs.close();

            } catch (Exception ex) {
                return "error";
            }

            if (result) {
                result = closeConnexion();
            }

            return passwordDB;
        }

        return "error";

    }

    public ArrayList<String> getUsers(String user) {
        ArrayList<String> lstUsers = null;
        boolean result = openConnexion();
        if (result) {
            System.out.println("connection ok");
            PreparedStatement ps = null;
            String pk_user = "";
            String password = "";
            lstUsers = new ArrayList<String>();
            try {
                ps = dbConnexion.prepareStatement("SELECT * FROM t_user WHERE user like (?) ");
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    pk_user = (String) rs.getString("PK_User");
                    user = (String) rs.getString("user");
                    password = (String) rs.getString("password");
                    lstUsers.add(pk_user + ", " + user + ", " + password);
                }
                rs.close();
                result = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (result) {
                result = closeConnexion();
            }
        }
        return lstUsers;
    }

    public boolean addUsers(String user, String password) {
        boolean success = false;
        boolean result = openConnexion();

        if (result) {
            PreparedStatement ps = null;

            try {

                ps = dbConnexion.prepareStatement("INSERT INTO t_user (user, password) VALUES (?, ?)");

                ps.setString(1, user);
                ps.setString(2, password);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                    System.out.println("New entry added to t_user table.");
                } else {
                    System.out.println("No rows affected when adding new entry to t_user table.");
                }
            } catch (Exception ex) {

                System.out.println("Error executing insert statement: " + ex.getMessage());
            }
            if (success) {
                result = closeConnexion();
            }
        }
        return success;
    }
}
