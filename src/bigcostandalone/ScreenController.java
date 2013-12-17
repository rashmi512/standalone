/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bigcostandalone;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author rashmireddy
 */
/**
 * <p>Description: It is an controller class which is used to inject UI components</p>
 *
 */
public class ScreenController extends StackPane{          //stackpane- used to place 1 screen on top of another screen
    private HashMap<String,Node> screens=new HashMap<>(); /* string- screen id
                                                             node- root of the scene graph for that particular screen */
    public ScreenController()
    {
        super();
    }
    public void addScreen(String name,Node screen)
    {
        screens.put(name,screen);         // add the screen to the collection
      //  System.out.println(name+","+screen);
    }
    public Node getScreen(String name)           
    {
        return screens.get(name);                //returns the screen based on the screen id
    }
   public boolean loadScreen(String name, String resource) {       //name- screen id,resource- fxml file
   try
    {
        //System.out.println(name+", "+resource);
        FXMLLoader myLoader=new FXMLLoader(getClass().getResource(resource));
        System.out.println(myLoader);
        Parent loadScreen=(Parent)myLoader.load();
        
        ControlledScreen myScreenController=(ControlledScreen) myLoader.getController();
        myScreenController.setScreenParent(this); 
       System.out.println(name+","+loadScreen);
        
        addScreen(name,loadScreen); //add screen to hashmap
        return true;
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        return false;
    }
    }
    public boolean setScreen(final String name)
    {
       System.out.println(screens);
        if(screens.get(name)!=null)    //screen loaded
        {
            
            final DoubleProperty opacity=opacityProperty();
            if(!getChildren().isEmpty())     //check is this the 1st screen going to display
            {
                //it has many screens,code for transition b/w screens
                Timeline fade=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(opacity,1.0)),
                new KeyFrame(new Duration(1000),new EventHandler<ActionEvent>()
                {
                @Override
                //after 1sec the eventhandler mtd triggers the handle mtd
                public void handle(ActionEvent ae)
                {
                    getChildren().remove(0); // only 1 screen at a time so remove the existing screen & add new screen
                    getChildren().add(0,screens.get(name));  
                    Timeline fadein=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(opacity,0.0)),
                    new KeyFrame(new Duration(800),new KeyValue(opacity,1.0)));
                    fadein.play();
                }
                },new KeyValue(opacity,0.0)));
                fade.play();
            }
            // if no other screens exist
            else
            {
                setOpacity(0.0);   // if opacity is 0, we dn't display screen 
                getChildren().add(screens.get(name));    //add the screen to the stack with the given id
               // fadein effect is used for the sreen transition,can display another also
                Timeline fadein=new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(opacity,0.0)),
                new KeyFrame(new Duration(2500),new KeyValue(opacity,1.0))); //opacity(1.0)- display the screen in 2.5sec
                fadein.play();
            }
            return true;
       }
     else
     {
         System.out.println("screen has been not loaded");
         return false;
     }

   }
    public boolean unloadscreen(String name)
    {
        if(screens.remove(name)==null)
        {
            System.out.println("screen doesn't exist");
            return false;
        }
        else
            return true;
    }
}
