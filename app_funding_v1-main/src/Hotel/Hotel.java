/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import Connection.ConnectionDatabase;
import config.propsLoader;
import java.sql.Connection;

/**
 *
 * @author Rizky
 */
public class Hotel {

    // Metode Apakah User sudah pernah Login
    private static boolean cekUserLogin(){
        String user = new propsLoader().loadUser();
        String lastLogin = new propsLoader().loadLastLogin();
        return user != null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Connect into database and fetching user data
        ConnectionDatabase database = new ConnectionDatabase();
        Connection conn = database.connect(); // Memanggil metode connect untuk membuat koneksi ke database

        // ConnectionEmail.checkConnection();
        // Melakukan pengecekan apakah koneksi berhasil
        if (conn != null) { // setelah koneksi berhasil
            System.out.println("Database connection successful.");

            if (cekUserLogin()){
                // Membuat objek UserDashboard dan menampilkannya
                // new ReservationMenu().setVisible(true);
            } else {
                // Tampilkan menu Login
                // new Login().setVisible(true);
            }
            
            new Login().setVisible(true);
        }
    }
}