/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import SerializedClasses.ForumDetails;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author B.Revanth
 */
public class AfterConnecting {

    public void insertForum() throws FileNotFoundException, IOException, ClassNotFoundException {
        ConnectionStatus conn = new ConnectionStatus();
        if (conn.connAvail()) {
            ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
            ForumHelper fh = new ForumHelper();
            afd = fh.getForumDetails();
            if (afd != null) {
                for (int i = 0; i < afd.size(); i++) {
                    try {
                        java.net.URL netUrl = new java.net.URL("http://localhost:8084/BigCo/ForumController?title=" + afd.get(i).getTitle() + "&desc=" + afd.get(i).getDesc() + "&username=" + afd.get(i).getUsername());
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
                        while (null != response) {
                            System.out.println(response);
                            response = reader.readLine();
                        }


                    } catch (MalformedURLException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                fh.removeDetails();
            }
        }

    }
}
