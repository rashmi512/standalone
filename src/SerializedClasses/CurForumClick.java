/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SerializedClasses;

import java.io.Serializable;

/**
 *
 * @author B.Revanth
 */
public class CurForumClick implements Serializable{
    private int fid;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
    
}
