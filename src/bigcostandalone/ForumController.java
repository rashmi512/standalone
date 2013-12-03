/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import Helper.AfterConnecting;
import Helper.ConnectionStatus;
import Helper.CurrentUserHelper;
import Helper.ForumHelper;
import POJO.Forums;
import SerializedClasses.CurrentUser;
import SerializedClasses.ForumDetails;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class ForumController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label connStatus, forumStatus;
    @FXML
    TextArea forumDesc;
    @FXML
    TextField forumTitle;
    @FXML
    Button home, upload, search, forum, logout, post;
    @FXML
    Hyperlink viewList;
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
    @FXML
    private void gotoForumList(ActionEvent event) {
        application.gotoForumList();
    }
    @FXML
    private void postForum(ActionEvent event) throws ClassNotFoundException, FileNotFoundException, IOException {
        if (forumTitle.getText().equals("") || forumDesc.getText().equals("")) {
            forumStatus.setText("Enter Valid Forum Details");
        } else {
            //String usr="revanth";
            //String username = cuh.getDetails();
            CurrentUserHelper cuh = new CurrentUserHelper();
            CurrentUser cu = cuh.getDetails();
            if (conn.connAvail() && conn.isAvailable()) {
                /*try {
                 java.net.URL netUrl = new java.net.URL("http://localhost:8084/BigCo/ForumController?title=" + forumTitle.getText() + "&desc=" + forumDesc.getText()+"&username="+usr);
                 HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
                 connection.setRequestMethod("GET");
                 connection.setUseCaches(false);
                 connection.setDoInput(true);
                 connection.setDoOutput(true);
                 DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                 out.flush();
                 out.close();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  

                 } catch (MalformedURLException ex) {
                 ex.printStackTrace();
                 } catch (IOException ex) {
                 ex.printStackTrace();
                 }*/
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                String strDate = sdf.format(cal.getTime());
                Forums f = new Forums();
                f.setUid(cu.getUid());
                f.setFtitle(forumTitle.getText());
                f.setQuestion(forumDesc.getText());
                f.setOndate(strDate);
                f.setStatus(1);
                ForumHelper fh = new ForumHelper();
                if (fh.insertForumDetails(f)) {
                    forumStatus.setText("Posted Successfully");
                } else {
                    forumStatus.setText("Error in Posting");
                }
            } else {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
                String strDate = sdf.format(cal.getTime());
                ForumDetails fd = new ForumDetails();
                fd.setDesc(forumDesc.getText());
                fd.setTitle(forumTitle.getText());
                fd.setUid(cu.getUid());
                fd.setUsername(cu.getUsername());
                fd.setOndate(strDate);
                ForumHelper fh = new ForumHelper();
                fh.insert(fd);
                forumStatus.setText("saved in cache");
                //fh.insert(forumTitle.getText(), forumDesc.getText(), usr);
            }
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
            } else {
                connStatus.setText("Offline");
                connStatus.setTextFill(Color.RED);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
