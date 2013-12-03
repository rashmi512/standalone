/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import bigcostandalone.ScreenController;

/**
 *
 * @author rashmireddy
 */
// all the screen controllers should implement this method
public interface ControlledScreen {
    // this method allows the injection of the parent screen pane
    public void setScreenParent(ScreenController screenPage);
    
}
