/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author B.Revanth
 */
public class FileDownload {
    public void download(String address, String localFileName) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream in = null;
        String name="E:\\NetBeansProjects\\BigCoStandalone\\videos\\"+localFileName;
        try {
            URL url = new URL(address);
            out = new BufferedOutputStream(new FileOutputStream(name));
            conn = url.openConnection();
            in = conn.getInputStream();
            byte[] buffer = new byte[1024];

            int numRead;
            long numWritten = 0;

            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
                numWritten += numRead;
            }

            System.out.println(localFileName + "\t" + numWritten);
        } 
        catch (Exception exception) { 
            exception.printStackTrace();
        } 
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } 
            catch (IOException ioe) {
            }
        }
    }

    public void download(String address) {
        int lastSlashIndex = address.lastIndexOf('/');
        if (lastSlashIndex >= 0 &&
        lastSlashIndex < address.length() - 1) {
            download(address, address.substring(lastSlashIndex + 1));
        } 
        else {
            System.err.println("Could not figure out local file name for "+address);
        }
    }
}
