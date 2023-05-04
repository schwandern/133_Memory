/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author schwandern
 */
public class WrkHTTP {

    public WrkHTTP() {

    }

    public String getclassement() throws MalformedURLException, IOException {
        URL url = new URL("https://schwandern.emf-informatique.ch/java-Rest_Classement/webresources/db/getClassement");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        String json = "";
        while ((output = br.readLine()) != null) {
            json += output;
        }

        conn.disconnect();

        return json;
    }

    public String addEntree(String score, String name) throws MalformedURLException, IOException {
        String data = "score=" + score + "&name=" + name;
        URL url = new URL("https://schwandern.emf-informatique.ch/java-Rest_Classement/webresources/db/addEntree");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        String responseFromServer = "";
        while ((output = br.readLine()) != null) {
            responseFromServer += output;

        }
        conn.disconnect();

        return responseFromServer;
    }

    public String getUserByName(String name) throws IOException {
        String data = "&user=" + name;
        URL url = new URL("https://reyx.emf-informatique.ch/javaRest_Admin/webresources/db/Getusers");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        String responseFromServer = "";
        while ((output = br.readLine()) != null) {
            responseFromServer += output;

        }

        conn.disconnect();
        return responseFromServer;
    }

    public String addUser(String name, String password) throws MalformedURLException, IOException {
        String data = "&user=" + name + "&password=" + password;
        URL url = new URL("https://reyx.emf-informatique.ch/javaRest_Admin02/webresources/db/Addusers");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        String responseFromServer = "";
        while ((output = br.readLine()) != null) {
            responseFromServer += output;

        }

        conn.disconnect();
        return responseFromServer;
    }
    
    public String checkLogin(String name, String password) throws MalformedURLException, IOException {
        String data = "&user=" + name + "&password=" + password;
        URL url = new URL("https://reyx.emf-informatique.ch/javaRest_Admin02/webresources/db/checkLogin");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        String responseFromServer = "";
        while ((output = br.readLine()) != null) {
            responseFromServer += output;

        }

        conn.disconnect();
        return responseFromServer;
    }

}
