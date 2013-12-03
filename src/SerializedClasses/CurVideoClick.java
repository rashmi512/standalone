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
public class CurVideoClick implements Serializable{
    private int vid;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
    
}
