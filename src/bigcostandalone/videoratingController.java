/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author rajasekhar
 */
public class videoratingController implements Initializable {
    private void handleButtonAction(ActionEvent event) throws IOException 
    {
       Image image = new Image("ram.jpg");
        
         ImageView iv1 = new ImageView();
         iv1.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  

   
    
}
