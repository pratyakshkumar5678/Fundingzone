/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import config.propsLoader;
import javax.swing.JOptionPane;

/**
 *
 * @author Rizky
 */
public class Logout {
    public void removeUser(){
        propsLoader.removeUser();
        JOptionPane.showMessageDialog(null, "Berhasil Keluar", "Successful Logout", JOptionPane.INFORMATION_MESSAGE);
    }
}
