/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import POJO.Replys;
import POJO.Userdetails;
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
public class ReplyHelper {
    Session session = null;
    public ArrayList<Replys> getForumReplyDetails(int forumid) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Replys> ud=new ArrayList<Replys>();
            Query q = session.createQuery("from Replys where ref=" + forumid );
            ud=(ArrayList<Replys>) q.list();
            return ud;
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
