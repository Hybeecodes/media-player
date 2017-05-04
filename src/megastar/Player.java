/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megastar;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Obikoya Emmanuel
 */
class Player extends BorderPane{
    
    Media media;
    MediaPlayer player;
    MediaView view;
    Pane spane;
    
    public Player(String file){
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        spane = new Pane();
        
        spane.getChildren().add(view);
        player.setAutoPlay(false);
        
        setCenter(spane);
        
        MediaBar bar = new MediaBar(player);
        setBottom(bar) ;
        
        setStyle("-fx-backgroung-color:#000000");
        player.play();
       // player.pause();
        
        
    }
}
