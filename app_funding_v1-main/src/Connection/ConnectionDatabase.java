/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import GUI.Loading;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Rizky
 */
public class ConnectionDatabase {
    public Connection connect() {
        // Menampilkan Loading Screen
        Loading loadingScreen = new Loading();
        loadingScreen.setVisible(true);
        Connection conn = null;
        try {
            // Load properties from application.properties file
            Properties props = new Properties();
            
            // Load the properties file from the classpath
            props.load(getClass().getClassLoader().getResourceAsStream("Config/application.properties"));
            
            // Retrieve database connection properties
            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String password = props.getProperty("DB_PASSWORD");
            
            // Create database connection
            conn = DriverManager.getConnection(url, user, password);
            // System.out.println("Database connection successful.");
        } catch (IOException | SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        // Menghilangkan Loading Screen
        loadingScreen.dispose();
        return conn;
    }
}
