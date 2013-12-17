/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import POJO.Forums;
import SerializedClasses.CurForumClick;
import SerializedClasses.ForumDetails;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Rajasekhar
 */
public class Forumtest {

    Session session = null;

    public boolean insertForumDetails(Forums vd) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            session.save(vd);
            System.out.println("this is query : \t" + trans.toString());
            trans.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return false;
    }

    public void insert(ForumDetails fd) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
        //ForumDetails fd = new ForumDetails();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\forum.txt");
        if (!file.exists()) {

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
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\forum.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<ForumDetails>) in.readObject();
            in.close();
            if (afd.size() == 0) {
                return null;
            }
            return afd;
        }

    }

    public void removeDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
        ForumDetails fd = new ForumDetails();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\forum.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        afd = (ArrayList<ForumDetails>) in.readObject();
        System.out.println("Clearing..........");
        afd.clear();
        System.out.println(afd.size() + "........");
        in.close();
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(fileOut);
        oout.writeObject(afd);
        oout.close();
        fileOut.close();
    }
    public ArrayList<Forums> getAllForums()
    {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Forums> ud=new ArrayList<Forums>();
            Query q = session.createQuery("from Forums");
            ud=(ArrayList<Forums>) q.list();
            return ud;
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public boolean insertCurForumInFile(CurForumClick vd) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\curforumdetails.txt");
        if (!file.exists()) {

            //afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(vd);
            oout.close();
            fileOut.close();
        } else {
            
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(vd);
            oout.close();
            fileOut.close();
        }
        return false;
    }
    public CurForumClick getCurrentForum() throws FileNotFoundException, IOException, ClassNotFoundException {
        //ArrayList<CurVideoClick> afd = new ArrayList<CurVideoClick>();
        CurForumClick cvc = new CurForumClick();
        //Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\curforumdetails.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cvc = (CurForumClick) in.readObject();
            //afd = (ArrayList<CurVideoClick>) in.readObject();
            in.close();
            /*if (afd.size() == 0) {
             return null;
             }*/
            return cvc;
        }
    }
    public Forums getForumDetails(int forumid) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Forums> ud=new ArrayList<Forums>();
            Query q = session.createQuery("from Forums where forumid=" + forumid );
            ud=(ArrayList<Forums>) q.list();
            if(ud.size()==1)
                return ud.get(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
