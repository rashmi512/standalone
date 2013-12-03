/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import Helper.AuthenticationHelper;
import Helper.ConnectionStatus;
import Helper.CurrentUserHelper;
import Helper.UserHelper;
import POJO.Userdetails;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label connStatus, loginStatus;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button login, reset;
    private BigCoStandalone application;
    ConnectionStatus conn = new ConnectionStatus();
    AuthenticationHelper ah = new AuthenticationHelper();
    CurrentUserHelper cuh = new CurrentUserHelper();

    public void setApp(BigCoStandalone application) {
        this.application = application;
    }

    @FXML
    private void loginSubmit(ActionEvent event) throws ClassNotFoundException, FileNotFoundException, IOException {
        if (username.getText().equals("") || password.getText().equals("")) {
            loginStatus.setText("Please enter the valid Details..");
        } else {
            if (conn.connAvail()) {
                /* try {

                 java.net.URL netUrl = new java.net.URL("http://localhost:8084/BigCo/LoginController?username=" + username.getText() + "&password=" + password.getText());
                 HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
                 connection.setRequestMethod("GET");
                 connection.setUseCaches(false);
                 connection.setDoInput(true);
                 connection.setDoOutput(true);
                 DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                 out.flush();
                 out.close();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 String response = reader.readLine();
                 if (response.equals("false")) {
                 loginStatus.setText("** Invalid Email/Password");
                 loginStatus.setTextFill(Color.RED);
                 } else if (response.equals("true")) {
                 ah.insertDetails(username.getText(), password.getText());
                 cuh.insert(username.getText());
                 application.gotoHome();
                 }
                 } catch (MalformedURLException ex) {
                 ex.printStackTrace();
                 } catch (IOException ex) {
                 ex.printStackTrace();
                 }*/
                UserHelper uh = new UserHelper();
                Userdetails ud = uh.getDetails(username.getText(), password.getText());
                if (ud != null) {
                    ah.insertDetails(username.getText(), password.getText());
                    cuh.insert(username.getText(),ud.getUid());
                    application.gotoHome();
                } else {
                    loginStatus.setText("** Invalid Email/Password");
                    loginStatus.setTextFill(Color.RED);
                }
            } else {
                if (ah.isAuthenticated(username.getText(), password.getText())) {
                    application.gotoHome();
                } else {
                    loginStatus.setText("** Invalid Email/Password");
                    loginStatus.setTextFill(Color.RED);
                }
            }

        }
    }

    @FXML
    private void resetDetails(ActionEvent event) {
        username.setText("");
        password.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            if (conn.connAvail() && conn.isAvailable()) {
                connStatus.setText("Available");
                connStatus.setTextFill(Color.GREEN);
            } else {
                connStatus.setText("Offline");
                connStatus.setTextFill(Color.RED);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
