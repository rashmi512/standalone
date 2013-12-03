/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import SerializedClasses.CurrentUser;
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
public class CurrentUserHelper {

    public void insert(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<CurrentUser> au = new ArrayList<CurrentUser>();
        CurrentUser ad = new CurrentUser();
        File file = new File("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\curuser.txt");
        if (!file.exists()) {
            ad.setUsername(username);
            au.add(ad);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(au);
            oout.close();
            fileOut.close();
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            au = (ArrayList<CurrentUser>) in.readObject();
            in.close();
            ad.setUsername(username);
            au.add(ad);
            FileOutputStream fileOut = new FileOutputStream(file, true);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(au);
            oout.close();
            fileOut.close();
        }
    }

    public String getDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<CurrentUser> au = new ArrayList<CurrentUser>();
        CurrentUser ad = new CurrentUser();
        File file = new File("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\curuser.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        au = (ArrayList<CurrentUser>) in.readObject();
        in.close();
        return au.get(au.size()-1).getUsername();
    }

    public void removeDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<CurrentUser> au = new ArrayList<CurrentUser>();
        CurrentUser ad = new CurrentUser();
        File file = new File("C:\\Users\\B.Revanth\\Documents\\NetBeansProjects\\BigCoStandalone\\curuser.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        au = (ArrayList<CurrentUser>) in.readObject();
        in.close();
        au.clear();
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(fileOut);
        oout.writeObject(au);
        oout.close();
        fileOut.close();
    }
}
