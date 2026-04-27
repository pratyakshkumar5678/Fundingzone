
package Connection;

import GUI.Loading;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDatabase {
    public Connection connect() {
        // Menampilkan Loading Screen
        Loading loadingScreen = new Loading();
        loadingScreen.setVisible(true);
        Connection conn = null;
        try {
            
            Properties props = new Properties();
            
            props.load(getClass().getClassLoader().getResourceAsStream("Config/application.properties"));
            
           
            String url = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String password = props.getProperty("DB_PASSWORD");
            
            
            conn = DriverManager.getConnection(url, user, password);
            
        } catch (IOException | SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }

        loadingScreen.dispose();
        return conn;
    }
}
