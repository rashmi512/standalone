/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import SerializedClasses.AuthenticationDetails;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author B.Revanth
 */
public class AuthenticationHelper {

    public void insertDetails(String username, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (isAuthenticated(username, password)) {
            return;
        } else {
            ArrayList<AuthenticationDetails> au = new ArrayList<AuthenticationDetails>();
            AuthenticationDetails ad = new AuthenticationDetails();
            File file = new File("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\user.txt");
            if (!file.exists()) {
                ad.setUsername(username);
                ad.setPassword(password);
                au.add(ad);
                FileOutputStream fileOut = new FileOutputStream(file, true);
                ObjectOutputStream oout = new ObjectOutputStream(fileOut);
                oout.writeObject(au);
                oout.close();
                fileOut.close();
            } else {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                au = (ArrayList<AuthenticationDetails>) in.readObject();
                in.close();
                ad.setUsername(username);
                ad.setPassword(password);
                au.add(ad);
                FileOutputStream fileOut = new FileOutputStream(file, true);
                ObjectOutputStream oout = new ObjectOutputStream(fileOut);
                oout.writeObject(au);
                oout.close();
                fileOut.close();
            }

        }

    }

    public boolean isAuthenticated(String username, String password) {
        File file = new File("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\user.txt");
        if (!file.exists()) {
            return false;
        } else {
            try {
                ArrayList<AuthenticationDetails> au = new ArrayList<AuthenticationDetails>();
                FileInputStream fileIn = new FileInputStream("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\user.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                au = (ArrayList<AuthenticationDetails>) in.readObject();
                //System.out.println("......." + au.size());
                for (int i = 0; i < au.size(); i++) {
                    if (au.get(i).getUsername().equals(username) && au.get(i).getPassword().equals(password)) {
                        return true;
                    }
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
