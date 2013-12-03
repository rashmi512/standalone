/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import SerializedClasses.ForumDetails;
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
public class ForumHelper {

    public void insert(String title, String desc, String username) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
        ForumDetails fd = new ForumDetails();
        File file = new File("C:\\Users\\rashmireddy\\Desktop\\se\\forum.txt");
        if (!file.exists()) {
            fd.setTitle(title);
            fd.setDesc(desc);
            fd.setUsername(username);
            afd.add(fd);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<ForumDetails>) in.readObject();
            in.close();
            fd.setTitle(title);
            fd.setDesc(desc);
            fd.setUsername(username);
            afd.add(fd);
            FileOutputStream fileOut = new FileOutputStream(file, true);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        }
    }

    public ArrayList<ForumDetails> getForumDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
        ForumDetails fd = new ForumDetails();
        File file = new File("C:\\Users\\rashmireddy\\Desktop\\se\\forum.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        afd = (ArrayList<ForumDetails>) in.readObject();
        in.close();
        if (afd.size() == 0) {
            return null;
        }
        return afd;
    }

    public void removeDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
        ForumDetails fd = new ForumDetails();
        File file = new File("C:\\Users\\rashmireddy\\Desktop\\se\\forum.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        afd = (ArrayList<ForumDetails>) in.readObject();
        System.out.println("Clearing..........");
        afd.clear();
        System.out.println(afd.size()+"........");
        in.close();
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(fileOut);
        oout.writeObject(afd);
        oout.close();
        fileOut.close();
    }
}
