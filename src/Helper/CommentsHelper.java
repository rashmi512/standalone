/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import POJO.Comments;
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
public class CommentsHelper {
    Session session = null;
    public ArrayList<Comments> getCommentDetails(int videoid) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            ArrayList<Comments> ud=new ArrayList<Comments>();
            Query q = session.createQuery("from Comments where vid=" + videoid );
            ud=(ArrayList<Comments>) q.list();
            /*String str="";
            UserHelper uh=new UserHelper();
            for(int i=ud.size()-1;i>=0;i--)
            {
               Userdetails u=uh.getUSerDetails(ud.get(i).getUid());
               str +="<div class='replypost'><div class='replyleft'>"
                       + "<img src='images/profile.jpg' width='80px' height='80px'></div>"
                       + "<div class='replyright'>"+ ud.get(i).getPosts()+"<br><br>posted by "+u.getUsername()+"</div></div><br>";
            }*/
            return ud;
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
}
