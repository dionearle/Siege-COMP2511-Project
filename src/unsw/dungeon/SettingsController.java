package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController {

    @FXML
    private Button backButton;
    
    boolean muteState = true;
    boolean muteStateE = true;
    @FXML
    private Button muteButton;
    
    @FXML
    private Button muteEffectsButton;
    
    private StartScreen startScreen;

    @FXML
    void handleBackButton(ActionEvent event) {
    	startScreen.start();
    }
    
    @FXML
    void handleMuteButton(ActionEvent event) {
    	System.out.println("MUTE");
    	
    	if (muteState) {
    		//wall.mediaPlayer.setVolume(0);
    		//mediaPlayer.setMute(true);
    		//mediaPlayer.play();
    		muteState = false;
    		//mp.setMute(true);
    		System.out.println("MUTEAAA");
    		muteButton.setText("Unmute Background Music");
    		
    	} else {
    		//mediaPlayer.setMute(false);
    		//wall.unMuteAudio();
    		muteState = true;
    		//mp.setMute(false);
    		muteButton.setText("Mute Background Music");
    	}
    	
    }
    
    
    @FXML
    void handleMuteEffectsButton(ActionEvent event) {
    	System.out.println("MUTE");
    	
    	if (muteStateE) {
    		//wall.mediaPlayer.setVolume(0);
    		//mediaPlayer.setMute(true);
    		//mediaPlayer.play();
    		muteStateE = false;
    		//mp.setMute(true);
    		System.out.println("MUTEAAA");
    		muteEffectsButton.setText("Unmute Effects");
    		
    	} else {
    		//mediaPlayer.setMute(false);
    		//wall.unMuteAudio();
    		muteStateE = true;
    		//mp.setMute(false);
    		muteEffectsButton.setText("Mute Effects");
    	}
    	
    }
    
    
    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

}
