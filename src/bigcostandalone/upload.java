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
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author B.Revanth
 */
public class UploadController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField title;
    @FXML
    TextArea desc;
    @FXML
    Label connStatus, fileName, uploadStatus;
    @FXML
    Button home, upload, search, forum, logout, chooseFile, submitForum;
    private File f;
    private BigCoStandalone application;
    ConnectionStatus conn = new ConnectionStatus();
    CurrentUserHelper cuh = new CurrentUserHelper();
    //ForumHelper fh = new ForumHelper();
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
    private void fileChooser(ActionEvent event) {
        JFileChooser jfc = new JFileChooser();
        int val = jfc.showOpenDialog(jfc);
        if (val == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
            fileName.setText(f.getName());
        }
    }

    @FXML
    private void forumSubmit(ActionEvent event) throws ClassNotFoundException, FileNotFoundException, IOException {
        if (title.getText().equals("") || desc.getText().equals("")) {
            uploadStatus.setText("Enter Valid Upload Details");
        } else {
            Video v = new Video();
            if (conn.connAvail() && conn.isAvailable()) {
                v.setCategoryid(1);
                v.setDescription(desc.getText());
                v.setTitle(title.getText());
                v.setVideopath(f.getName());
                VideoHelper vh = new VideoHelper();
                vh.insertVideoDetails(v);
                HttpURLConnection connection = null;
                DataOutputStream outputStream = null;
                DataInputStream inputStream = null;

                String pathToOurFile = f.getAbsolutePath();
                String urlServer = "http://localhost:8084/BigCo/UploadController";
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";

                int bytesRead, bytesAvailable, bufferSize=4096;
                byte[] buffer;
                //int maxBufferSize = 1 * 1024 * 1024;

                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile));

                    URL url = new URL(urlServer);
                    connection = (HttpURLConnection) url.openConnection();

// Allow Inputs & Outputs
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

// Enable POST method
                    connection.setRequestMethod("POST");

                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    outputStream = new DataOutputStream(connection.getOutputStream());
                    outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    outputStream.writeBytes("Content-Disposition: form-data; name=\"userfile\";filename=\"" + pathToOurFile + "\"" + lineEnd);
                    outputStream.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream.available();
                    //bufferSize = Math.max(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

// Read file
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {
                        outputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        //bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    outputStream.writeBytes(lineEnd);
                    outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

// Responses from the server (code and message)
                    InputStream stream = connection.getInputStream();
                    BufferedInputStream in = new BufferedInputStream(stream);
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        System.out.write(i);
                    }
                    in.close();

                    fileInputStream.close();
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception ex) {
//Exception handling
                }
                uploadStatus.setText("Uploaded Successfully");
            } else {

                FileInputStream fileInputStream = new FileInputStream(f.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream("E:\\NetBeansProjects\\BigCoStandalone\\videos\\" + f.getName());

                int bufferSize;
                byte[] bufffer = new byte[512];
                while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                    fileOutputStream.write(bufffer, 0, bufferSize);
                }
                fileInputStream.close();
                fileOutputStream.close();
                v.setCategoryid(1);
                v.setDescription(desc.getText());
                v.setTitle(title.getText());
                v.setVideopath(f.getName());
                VideoHelper vh = new VideoHelper();
                vh.insertInFile(v);
                uploadStatus.setText("Saved in Cache");
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
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
