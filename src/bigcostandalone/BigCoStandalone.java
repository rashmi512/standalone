/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author B.Revanth
 */
public class BigCoStandalone extends Application {

    Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 600.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    @Override
    public void start(Stage stage) throws Exception {
        /* Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
         Scene scene = new Scene(root);
        
         stage.setScene(scene);
         stage.show();*/
        this.stage = stage;
        stage.setTitle("BigCo.");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotoLogin();
        stage.show();
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoHome() {
        try {
            HomeController home = (HomeController) replaceSceneContent("Home.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoUpload() {
        try {
            UploadController home = (UploadController) replaceSceneContent("Upload.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSearch() {
        try {
            SearchController home = (SearchController) replaceSceneContent("Search.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoForum() {
        try {
            ForumController home = (ForumController) replaceSceneContent("Forum.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoViewVideo() {
        try {
            ViewVideoController home = (ViewVideoController) replaceSceneContent("ViewVideo.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoViewOffline() {
        try {
            ViewOfflineController home = (ViewOfflineController) replaceSceneContent("ViewOffline.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoForumList() {
        try {
            ForumListController home = (ForumListController) replaceSceneContent("ForumList.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void gotoViewForum() {
        try {
            ViewForumController home = (ViewForumController) replaceSceneContent("ViewForum.fxml");
            home.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(BigCoStandalone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = BigCoStandalone.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(BigCoStandalone.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 1200, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}