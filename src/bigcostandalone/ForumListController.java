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
import Helper.UserHelper;
import Helper.VideoHelper;
import POJO.Forums;
import POJO.Userdetails;
import SerializedClasses.CurForumClick;
import SerializedClasses.CurVideoClick;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class ForumListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label connStatus, forumListStatus;
    @FXML
    Button home, upload, search, forum, logout;
    @FXML
    ListView<HBox> forumList;
    @FXML
    Hyperlink postNewTopic;
    private BigCoStandalone application;
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
    public void forumOnLoad(ArrayList<Forums> all)
    {
        Button[] alb = new Button[all.size()];
        HBox[] alh = new HBox[all.size()];
        VBox[] alv = new VBox[all.size()];
        for(int i=0;i<all.size();i++)
        {
            alh[i] = new HBox();
            alv[i] = new VBox();
            alb[i] = new Button();
            Label title = new Label();
            Label posted = new Label();
            title.textProperty().set(all.get(i).getFtitle());
            UserHelper uh = new UserHelper();
            Userdetails u = uh.getUSerDetails(all.get(i).getUid());
            posted.textProperty().set("Posted by "+u.getUsername()+" on "+all.get(i).getOndate());
            alb[i].textProperty().set("View");
            alb[i].setId(all.get(i).getForumid().toString());
            alv[i].getChildren().add(title);
            alv[i].getChildren().add(posted);
            alv[i].getChildren().add(alb[i]);
            alv[i].setSpacing(10);
            alh[i].getChildren().add(alv[i]);
            alb[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
                    //Getting the source of the click
                    Button b = (Button) arg0.getSource();
                    ForumHelper vh = new ForumHelper();
                    CurForumClick cvc = new CurForumClick();
                    cvc.setFid(Integer.parseInt(b.getId()));
                    try {
                        System.out.println(Integer.parseInt(b.getId())+".......................");
                        vh.insertCurForumInFile(cvc);
                        application.gotoViewForum();
                    } catch (IOException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            forumList.itemsProperty().get().add(alh[i]);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            if (conn.connAvail() && conn.isAvailable()) {
                connStatus.setText("Available");
                connStatus.setTextFill(Color.GREEN);
                ac.insertForum();
                ac.insertUploadDetails();
                ArrayList<Forums> af = new ArrayList<Forums>();
                af = fh.getAllForums();
                if (af != null) {
                    forumOnLoad(af);
                } else {
                    forumListStatus.setText("No Posts Available");
                }
            } else {
                connStatus.setText("Offline");
                connStatus.setTextFill(Color.RED);
                forumListStatus.setText("Connection Not Available");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForumListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ForumListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ForumListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
