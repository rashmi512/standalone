/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SerializedClasses;

import java.io.Serializable;

/**
 *
 * @author B.Revanth
 */
public class CurrentUser implements Serializable{
    private String username;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
