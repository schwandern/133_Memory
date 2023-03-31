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

/**
 *
 * @author schwandern
 */
public class Wrk {
    
     private String port;
    private String dbName;
    
    public Wrk(String port, String dbName){
        this.port = port;
        this.dbName = dbName;
    }
    
     /**
     * Ouverture d'une connexion sur la base de données.
     * Ajouter mysql-connector-j-....jar dans les librairies.
     * 
     * @return true si tout s'est bien passé, sinon false
     */
     private Connection dbConnexion;
    
    public boolean openConnexion(){

        final String url = "jdbc:mysql://localhost:" + port + "/" + dbName + "?serverTimezone=CET";
        final String user = "schwandern_admin";
        final String pw =  "BW}J.;Z~[uDZ";// 
        boolean result = false;
 
        try{
            //nécessaire pour fonctionnement en web
            Class.forName("com.mysql.jdbc.Driver");
        } catch ( ClassNotFoundException ex ) {
             System.out.println("Connexion au driver JDBC à échoué!\n" + ex.getMessage());
        } 
        try {
            dbConnexion = DriverManager.getConnection( url, user, pw );
            //System.out.println("Connection successfull");
            result = true;

        } catch ( SQLException ex ) {
             System.out.println("Connexion à la BD a échouée!\n" + ex.getMessage());
        }     
        return result;
    }
    
     /**
     * Cette méthode permet de fermer la connexion à la base de données après
     * que la requête ait été envoyée.
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

   public ArrayList<String> getClassement() {
    ArrayList<String> lstClassement = null;
    boolean result = openConnexion();
    if (result) {
        System.out.println("Connection successful.");
        PreparedStatement ps = null;
        String entree = "";
        String score = "";
        String nom = "";
        String fk_user = "";
        lstClassement = new ArrayList<String>();
        try {
            ps = dbConnexion.prepareStatement("SELECT * FROM t_classement");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entree = Integer.toString(rs.getInt("PK_entree"));
                score = rs.getString("score");
                nom = rs.getString("nom");
                fk_user = rs.getString("fk_user");
                lstClassement.add(entree + ", " + score + ", " + nom + ", " + fk_user);
            }
            rs.close();
            result = true;
            System.out.println("Query executed successfully.");
        } catch (Exception ex) {
            System.out.println("Error executing query: " + ex.getMessage());
        }

        if (result) {
            result = closeConnexion();
        }
    }
    return lstClassement;
}
   
   
   public boolean insertClassementEntry(int score, String nom, int fk_user) {
    boolean success = false;
    boolean result = openConnexion();
    if (result) {
        PreparedStatement ps = null;
        try {
            ps = dbConnexion.prepareStatement("INSERT INTO t_classement (score, nom, fk_user) VALUES (?, ?, ?)");
            ps.setInt(1, score);
            ps.setString(2, nom);
            ps.setInt(3, fk_user);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
                System.out.println("New entry added to t_classement table.");
            } else {
                System.out.println("No rows affected when adding new entry to t_classement table.");
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
