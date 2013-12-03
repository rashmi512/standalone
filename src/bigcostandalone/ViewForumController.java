/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bigcostandalone;

import Helper.AfterConnecting;
import Helper.ConnectionStatus;
import Helper.CurrentUserHelper;
import Helper.ForumHelper;
import Helper.ReplyHelper;
import Helper.UserHelper;
import POJO.Forums;
import POJO.Replys;
import POJO.Userdetails;
import SerializedClasses.CurForumClick;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class ViewForumController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label connStatus, posted;
    @FXML
    TextArea forumTitle, forumDesc;
    @FXML
    ListView<HBox> replyList;
    @FXML
    Button home, upload, search, forum, logout;
    private BigCoStandalone application;
    ArrayList<Replys> replys;
    ConnectionStatus conn = new ConnectionStatus();
    CurrentUserHelper cuh = new CurrentUserHelper();
    ForumHelper fh = new ForumHelper();
    AfterConnecting ac = new AfterConnecting();
    public void setApp(BigCoStandalone application) {
        this.application = application;
    }
    @FXML
    private void gotoHome(ActionEvent event) {
        application.gotoHome();
    }
    @FXML
    private void gotoUpload(ActionEvent event) {
        application.gotoUpload();
    }
    @FXML
    private void gotoSearch(ActionEvent event) {
        application.gotoSearch();
    }
    @FXML
    private void gotoForum(ActionEvent event) {
        application.gotoForum();
    }
    @FXML
    private void gotoLogin(ActionEvent event) {
        application.gotoLogin();
    }
    public void onLoadForum(ArrayList<Replys> found)
    {
        HBox[] alh = new HBox[found.size()];
        VBox[] alv = new VBox[found.size()];
        for (int i = 0; i < found.size(); i++) {
            alh[i] = new HBox();
            alv[i] = new VBox();
            Label com = new Label();
            Label posted = new Label();
            //Image image=new Image("profile.jpg");
            //System.out.println(found.get(i).getPosts() + "...................");
            com.textProperty().set(found.get(i).getReply());

            UserHelper uh = new UserHelper();
            Userdetails u = uh.getUSerDetails(found.get(i).getUid());

            posted.textProperty().set("Posted by " + u.getUsername() +" on "+found.get(i).getOndate());
            //alh[i].getChildren().add(ali[i]);
            alv[i].getChildren().add(com);
            alv[i].getChildren().add(posted);
            alv[i].setSpacing(5);
            alh[i].getChildren().add(alv[i]);
            replyList.itemsProperty().get().add(alh[i]);

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ForumHelper fh = new ForumHelper();
        ReplyHelper rh = new ReplyHelper();
        CurForumClick cfc = null;
        Forums f =null;
        try {
            cfc = fh.getCurrentForum();
            f = fh.getForumDetails(cfc.getFid());
            forumTitle.setText(f.getFtitle());
            forumDesc.setText(f.getQuestion());
            UserHelper uh = new UserHelper();
            Userdetails u = uh.getUSerDetails(f.getUid());
            posted.setText("Posted by "+u.getUsername()+" on "+f.getOndate());
            replys = rh.getForumReplyDetails(f.getForumid());
            if(replys!=null && replys.size()>0)
            {
                onLoadForum(replys);
            }
        } catch (IOException ex) {
            Logger.getLogger(ViewForumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
