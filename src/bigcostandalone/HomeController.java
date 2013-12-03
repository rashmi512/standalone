/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import Helper.AfterConnecting;
import Helper.ConnectionStatus;
import Helper.CurrentUserHelper;
import Helper.ForumHelper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label connStatus;
    @FXML
    Button home, upload, search, forum, logout;
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
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
