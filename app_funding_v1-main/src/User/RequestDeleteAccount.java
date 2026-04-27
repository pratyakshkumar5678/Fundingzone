/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

import Connection.ConnectionDatabase;
import config.propsLoader;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Rizky
 */
public class RequestDeleteAccount {
    public static boolean deleteAccount() {
        ConnectionDatabase database = new ConnectionDatabase();
        try (Connection conn = database.connect()) {
            String query = "DELETE FROM reservation WHERE userid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, propsLoader.loadUserID());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0){
                    return delAccount();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while deleting user.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }
    
    public static boolean delAccount() {
        ConnectionDatabase database = new ConnectionDatabase();
        try (Connection conn = database.connect()) {
            String query = "DELETE FROM user WHERE userid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, propsLoader.loadUserID());
                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0; // Mengembalikan true jika ada baris yang terpengaruh
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while deleting user.", "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
