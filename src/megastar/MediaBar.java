
package megastar;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author Obikoya Emmanuel
 */
public class MediaBar extends HBox {
    
    Slider time = new Slider();
     Slider vol = new Slider();
     
     Button playButton = new Button("||");
     
     Label volume = new Label("Volume");
     
     MediaPlayer player;
    public MediaBar(MediaPlayer play){
        player=play;
        
        setAlignment(Pos.CENTER);
        setPadding(new  Insets(5,10,5,10));
        
        vol.setPrefWidth(70);
        vol.minWidth(70);
        vol.setValue(100);
        
        HBox.setHgrow(time, Priority.ALWAYS);
        
        playButton.setPrefWidth(30);
        
        getChildren().add(playButton);
        getChildren().add(time);
         getChildren().add(volume);
         getChildren().add(vol);
         
         
         playButton.setOnAction(e ->{
            Status status = player.getStatus();
            
            if(status==status.PLAYING){
                if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                    player.seek(player.getStartTime()); 
                    player.play();
                      
                }else
                    player.pause();
                playButton.setText(">");
              
            }
            if(status==status.PAUSED || status==status.STOPPED || status==status.HALTED){
                player.play();
                playButton.setText("||");
            }
            
            
         });
         
         player.currentTimeProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ob) {
                updateValues();
                
            }
        });
         
         time.valueProperty().addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable observable) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
                if(time.isPressed()){
                    player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
                }
            }
             
         });
         
         vol.valueProperty().addListener(new InvalidationListener(){

            @Override
            public void invalidated(Observable observable) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
                if(vol.isPressed()){
                   player.setVolume(vol.getValue()/100);
                }
            }
             
         });
         
         
         
         
    }
    protected void updateValues(){
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                      time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
                 }
             });
            
         }
    
}
