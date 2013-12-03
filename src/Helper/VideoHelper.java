/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import POJO.Video;
import SerializedClasses.CurVideoClick;
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
 * @author B.Revanth
 */
public class VideoHelper {

    Session session = null;

    public boolean insertVideoDetails(Video vd) {
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

    public boolean insertInFile(Video vd) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        //ForumDetails fd = new ForumDetails();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\videodetails.txt");
        if (!file.exists()) {

            afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<Video>) in.readObject();
            in.close();

            afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file, true);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        }
        return false;
    }

    public ArrayList<Video> getUploadVideos() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        //Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\videodetails.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<Video>) in.readObject();
            in.close();
            if (afd.size() == 0) {
                return null;
            }
            return afd;
        }
    }

    public void removeUploadDetails() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\forum.txt");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        afd = (ArrayList<Video>) in.readObject();
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

    public ArrayList<Video> getAllVideos() {
        ArrayList<Video> all = new ArrayList<Video>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        try {

            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Video");
            all = (ArrayList<Video>) q.list();
            return all;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ArrayList<Video> search(String keyword) {
        String keys[] = keyword.split(" ");
        ArrayList<Video> all = getAllVideos();
        ArrayList<Video> found = new ArrayList<Video>();
        for (int i = 0; i < all.size(); i++) {
            Video v = all.get(i);
            String desc = new String(v.getTitle());
            for (int j = 0; j < keys.length; j++) {
                if (desc.toLowerCase().contains(keys[j].toLowerCase())) {
                    //System.out.println("text:" + ah);
                    if (!found.contains(all.get(i))) {      // chcks for the word found in notes or not if found then the noets instance is inserted into the arraylist
                        found.add(all.get(i));
                    }
                }
            }
        }
        if (found.isEmpty()) {
            for (int i = 0; i < all.size(); i++) {
                Video v = all.get(i);
                String desc = new String(v.getDescription());
                for (int j = 0; j < keys.length; j++) {
                    if (desc.toLowerCase().contains(keys[j].toLowerCase())) {
                        //System.out.println("text:" + ah);
                        if (!found.contains(all.get(i))) {      // chcks for the word found in notes or not if found then the noets instance is inserted into the arraylist
                            found.add(all.get(i));
                        }
                    }
                }

            }

        } else {
            return found;
        }
        return found;

    }

    public boolean insertCurVideoInFile(CurVideoClick vd) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<CurVideoClick> afd = new ArrayList<CurVideoClick>();
        //CurVideoClick cvc = new CurVideoClick();
        //ForumDetails fd = new ForumDetails();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\curvideodetails.txt");
        if (!file.exists()) {

            //afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(vd);
            oout.close();
            fileOut.close();
        } else {
            /* FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn);
             afd = (ArrayList<CurVideoClick>) in.readObject();
             in.close();

             afd.add(vd);*/
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(vd);
            oout.close();
            fileOut.close();
        }
        return false;
    }

    public CurVideoClick getCurrentVideo() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<CurVideoClick> afd = new ArrayList<CurVideoClick>();
        CurVideoClick cvc = new CurVideoClick();
        //Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\curvideodetails.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cvc = (CurVideoClick) in.readObject();
            //afd = (ArrayList<CurVideoClick>) in.readObject();
            in.close();
            /*if (afd.size() == 0) {
             return null;
             }*/
            return cvc;
        }
    }

    public Video getVideodetails(int videoid) {
        ArrayList<Video> all = new ArrayList<Video>();
        //String str="";
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        try {

            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Video where videoid=" + videoid);
            all = (ArrayList<Video>) q.list();
            if (all.size() > 0) {
                //System.out.println(all.size() + "................");
                return all.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean insertInCache(Video vd) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        //ForumDetails fd = new ForumDetails();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\videodetailscache.txt");
        if (!file.exists()) {

            afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<Video>) in.readObject();
            in.close();

            afd.add(vd);
            FileOutputStream fileOut = new FileOutputStream(file, true);
            ObjectOutputStream oout = new ObjectOutputStream(fileOut);
            oout.writeObject(afd);
            oout.close();
            fileOut.close();
        }
        return false;
    }

    public ArrayList<Video> getAllVideosCache() throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        //Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\videodetailscache.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<Video>) in.readObject();
            in.close();
            if (afd.size() == 0) {
                return null;
            }
            return afd;
        }

    }

    public ArrayList<Video> searchInCache(String keyword) throws IOException, FileNotFoundException, ClassNotFoundException {
        String keys[] = keyword.split(" ");
        ArrayList<Video> all = getAllVideosCache();
        ArrayList<Video> found = new ArrayList<Video>();
        for (int i = 0; i < all.size(); i++) {
            Video v = all.get(i);
            String desc = new String(v.getTitle());
            for (int j = 0; j < keys.length; j++) {
                if (desc.toLowerCase().contains(keys[j].toLowerCase())) {
                    //System.out.println("text:" + ah);
                    if (!found.contains(all.get(i))) {      // chcks for the word found in notes or not if found then the noets instance is inserted into the arraylist
                        found.add(all.get(i));
                    }
                }
            }
        }
        if (found.isEmpty()) {
            for (int i = 0; i < all.size(); i++) {
                Video v = all.get(i);
                String desc = new String(v.getDescription());
                for (int j = 0; j < keys.length; j++) {
                    if (desc.toLowerCase().contains(keys[j].toLowerCase())) {
                        //System.out.println("text:" + ah);
                        if (!found.contains(all.get(i))) {      // chcks for the word found in notes or not if found then the noets instance is inserted into the arraylist
                            found.add(all.get(i));
                        }
                    }
                }

            }

        } else {
            return found;
        }
        return found;

    }

    public Video getVideoDetailsCache(int vid) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Video> afd = new ArrayList<Video>();
        //Video fd = new Video();
        File file = new File("E:\\NetBeansProjects\\BigCoStandalone\\videodetailscache.txt");
        if (!file.exists()) {
            return null;
        } else {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            afd = (ArrayList<Video>) in.readObject();
            in.close();

            for (int i = 0; i < afd.size(); i++) {
                if (vid == afd.get(i).getVideoid()) {
                    return afd.get(i);
                }
            }
            return null;
        }
    }
}
