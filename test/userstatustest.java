/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import POJO.NewHibernateUtil;
import POJO.Userdetails;
import POJO.Video;
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
public class UserHelper {
    Session session = null;
    public boolean insertUserDetails(Userdetails vd) {
        try {
            //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //session = sessionFactory.openSession();
            this.session = NewHibernateUtil.getSessionFactory().openSession();
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
    public Userdetails getUSerDetails(String email,String uname) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Userdetails> ud=new ArrayList<Userdetails>();
            Query q = session.createQuery("from Userdetails where email='" + email + "' and username='" + uname + "'");
            ud=(ArrayList<Userdetails>) q.list();
            if(ud.size()==1)
                return ud.get(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public Userdetails getDetails(String email,String password) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            //this.session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Userdetails> ud=new ArrayList<Userdetails>();
            Query q = session.createQuery("from Userdetails where email='" + email + "' and password='" + password + "'");
            ud=(ArrayList<Userdetails>) q.list();
            if(ud.size()==1)
                return ud.get(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public Userdetails getUSerDetails(int uid) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Userdetails> ud=new ArrayList<Userdetails>();
            Query q = session.createQuery("from Userdetails where uid=" + uid );
            ud=(ArrayList<Userdetails>) q.list();
            if(ud.size()==1)
                return ud.get(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public Userdetails getEmailid(String email) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Userdetails> ud=new ArrayList<Userdetails>();
            Query q = session.createQuery("from Userdetails where email='" + email +"'");
            ud=(ArrayList<Userdetails>) q.list();
            if(ud.size()==1)
                return ud.get(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
