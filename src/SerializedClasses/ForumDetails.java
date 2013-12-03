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
public class ForumDetails implements Serializable{
    private String username;
    private String title;
    private String desc;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
