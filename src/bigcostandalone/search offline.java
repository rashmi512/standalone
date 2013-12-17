/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import Helper.AfterConnecting;
import Helper.ConnectionStatus;
import Helper.CurrentUserHelper;
import Helper.ForumHelper;
import Helper.VideoHelper;
import POJO.Video;
import SerializedClasses.CurVideoClick;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class SearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label connStatus, searchStatus;
    @FXML
    TextField keyword;
    @FXML
    Button home, upload, search, forum, logout, searchSubmit;
    @FXML
    ListView<HBox> searchList;
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
    private void submitSearch(ActionEvent event) throws ClassNotFoundException, IOException {
        VideoHelper vh = new VideoHelper();
        if (conn.connAvail() && conn.isAvailable()) {
            ArrayList<Video> found = vh.search(keyword.getText());
            if (found.isEmpty()) {
                searchStatus.setText("No Videos Found");
            } else {
                onLoad(found);
            }
        }
        else
        {
            ArrayList<Video> found = vh.searchInCache(keyword.getText());
            if (found.isEmpty()) {
                searchStatus.setText("No Videos Found");
            } else {
                onLoadOffline(found);
            }
        }
        //onLoad();
    }

    public void onLoad(ArrayList<Video> found) {
        HBox[] alh = new HBox[found.size()];
        Button[] alb = new Button[found.size()];
        VBox[] alv = new VBox[found.size()];
        for (int i = 0; i < found.size(); i++) {
            alh[i] = new HBox();
            alv[i] = new VBox();
            alb[i] = new Button();
            Label desc = new Label();
            Label title = new Label();
            title.textProperty().set(found.get(i).getTitle());
            desc.textProperty().set(found.get(i).getDescription());
            alb[i].textProperty().set("Watch");
            alb[i].setId(found.get(i).getVideoid().toString());
            alv[i].getChildren().add(title);
            alv[i].getChildren().add(desc);
            alv[i].getChildren().add(alb[i]);
            alv[i].setSpacing(10);
            alh[i].getChildren().add(alv[i]);
            alb[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
                    //Getting the source of the click
                    Button b = (Button) arg0.getSource();
                    VideoHelper vh = new VideoHelper();
                    CurVideoClick cvc = new CurVideoClick();
                    cvc.setVid(Integer.parseInt(b.getId()));
                    try {
                        System.out.println(Integer.parseInt(b.getId())+".......................");
                        vh.insertCurVideoInFile(cvc);
                        application.gotoViewVideo();
                    } catch (IOException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            searchList.itemsProperty().get().add(alh[i]);
        }

    }
    public void onLoadOffline(ArrayList<Video> found) {
        HBox[] alh = new HBox[found.size()];
        Button[] alb = new Button[found.size()];
        VBox[] alv = new VBox[found.size()];
        for (int i = 0; i < found.size(); i++) {
            alh[i] = new HBox();
            alv[i] = new VBox();
            alb[i] = new Button();
            Label desc = new Label();
            Label title = new Label();
            title.textProperty().set(found.get(i).getTitle());
            desc.textProperty().set(found.get(i).getDescription());
            alb[i].textProperty().set("Watch");
            alb[i].setId(found.get(i).getVideoid().toString());
            alv[i].getChildren().add(title);
            alv[i].getChildren().add(desc);
            alv[i].getChildren().add(alb[i]);
            alv[i].setSpacing(10);
            alh[i].getChildren().add(alv[i]);
            alb[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
                    //Getting the source of the click
                    Button b = (Button) arg0.getSource();
                    VideoHelper vh = new VideoHelper();
                    CurVideoClick cvc = new CurVideoClick();
                    cvc.setVid(Integer.parseInt(b.getId()));
                    try {
                        System.out.println(Integer.parseInt(b.getId())+".......................");
                        vh.insertCurVideoInFile(cvc);
                        application.gotoViewOffline();
                    } catch (IOException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            searchList.itemsProperty().get().add(alh[i]);
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
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
