/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Rizky
 */
public class propsLoader {
    private static final Properties PROPS = new Properties();
    private static final String FILE_PATH = "D:\\4 ISA 1\\Project\\Booking_Hotel\\src\\config\\user.properties";
    
    public static void loadProperties() {
        try {
            PROPS.load(propsLoader.class.getResourceAsStream("user.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Memuat Konfigurasi Pengguna", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String loadUserID(){
        loadProperties();
        return PROPS.getProperty("User_ID");
    }
    
    public static String loadUser() {
        loadProperties();
        return PROPS.getProperty("User_Login");
    }

    public static String loadFullName(){
        loadProperties();
        return PROPS.getProperty("User_FullName");
    }
    
    public static String loadEmail(){
        loadProperties();
        return PROPS.getProperty("User_Email");
    }
    
    public static String loadLastLogin(){
        loadProperties();
        return PROPS.getProperty("User_LastLogin");
    }
    
    public static String loadEmailForgot(){
        loadProperties();
        return PROPS.getProperty("User_EmailForgotPassReq");
    }
    
    public static void setEmail(String email){
        try {
            PROPS.setProperty("User_Email", email);
            PROPS.store(new FileOutputStream(FILE_PATH), null);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan Informasi Pengguna", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Metode untuk mendapatkan tanggal dan waktu saat ini dalam format yang diinginkan
    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
    // Metode untuk menyimpan data pengguna ke dalam file user.properties
    public static void saveUser(String UserID, String username, String email, String fullname) {
        try {
            String loginTime = getCurrentDateTime();
            PROPS.setProperty("User_ID", UserID);
            PROPS.setProperty("User_Login", username);
            PROPS.setProperty("User_Email", email);
            PROPS.setProperty("User_FullName", fullname);
            PROPS.setProperty("User_LoginTime", loginTime);
            PROPS.store(new FileOutputStream(FILE_PATH), null);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan Informasi Pengguna", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void removeUser() {
        try {
            // Memuat file user.properties
            InputStream inputStream = new FileInputStream(FILE_PATH);
            PROPS.load(inputStream);
            inputStream.close();

            PROPS.remove("User_ID");
            PROPS.remove("User_Login");
            PROPS.remove("User_Email");
            PROPS.remove("User_FullName");
            PROPS.remove("User_LoginTime");

            // Simpan kembali file user.properties
            OutputStream outputStream = new FileOutputStream(FILE_PATH);
            PROPS.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Pengguna", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}