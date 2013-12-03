/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import POJO.Forums;
import POJO.Video;
import SerializedClasses.ForumDetails;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author rajasekhar
 */
public class connectionstatustest {

    public void insertForum() throws FileNotFoundException, IOException, ClassNotFoundException {
        ConnectionStatus conn = new ConnectionStatus();
        if (conn.connAvail()) {
            ArrayList<ForumDetails> afd = new ArrayList<ForumDetails>();
            ForumHelper fh = new ForumHelper();
            afd = fh.getForumDetails();
            if (afd != null) {
                for (int i = 0; i < afd.size(); i++) {
                    /*try {
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
                     }*/
                    Forums f = new Forums();
                    f.setFtitle(afd.get(i).getTitle());
                    f.setQuestion(afd.get(i).getDesc());
                    f.setUid(afd.get(i).getUid());
                    f.setOndate(afd.get(i).getOndate());
                    f.setStatus(1);
                    fh.insertForumDetails(f);
                }

                fh.removeDetails();
            }
        }

    }

    public void insertUploadDetails() throws ClassNotFoundException, IOException {
        ConnectionStatus conn = new ConnectionStatus();
        if (conn.connAvail()) {
            ArrayList<Video> av = new ArrayList<Video>();
            VideoHelper vh = new VideoHelper();
            av = vh.getUploadVideos();
            if (av != null) {
                for (int i = 0; i < av.size(); i++) {
                    Video v = new Video();
                    v.setCategoryid(av.get(i).getCategoryid());
                    v.setTitle(av.get(i).getTitle());
                    v.setDescription(av.get(i).getDescription());
                    v.setVideopath(av.get(i).getVideopath());
                    vh.insertVideoDetails(v);
                    HttpURLConnection connection = null;
                    DataOutputStream outputStream = null;
                    DataInputStream inputStream = null;

                    String pathToOurFile = "E:\\NetBeansProjects\\BigCoStandalone\\videos\\" + av.get(i).getVideopath();
                    String urlServer = "http://localhost:8084/BigCo/UploadController";
                    String lineEnd = "\r\n";
                    String twoHyphens = "--";
                    String boundary = "*****";

                    int bytesRead, bytesAvailable, bufferSize;
                    byte[] buffer;
                    int maxBufferSize = 1 * 1024 * 1024;

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
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

// Read file
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {
                            outputStream.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math.min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                        }

                        outputStream.writeBytes(lineEnd);
                        outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

// Responses from the server (code and message)
                        InputStream stream = connection.getInputStream();
                        BufferedInputStream in = new BufferedInputStream(stream);
                        int j = 0;
                        while ((j = in.read()) != -1) {
                            System.out.write(i);
                        }
                        in.close();

                        fileInputStream.close();
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception ex) {
//Exception handling
                    }
                }
                vh.removeUploadDetails();
            }

        }

    }
}
