package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;
    
    @FXML
    private Button quitButton;
    
    @FXML
    private Button instructionsButton;
    
    @FXML
    private Button settingsButton;
    
    @FXML
    private Button tipsButton;

    private DungeonSelectScreen dungeonSelectScreen;
    
    private InstructionsScreen instructionsScreen;
    
    private SettingsScreen settingsScreen;
    
    private TipsScreen tipsScreen;

    @FXML
    void handleStartButton(ActionEvent event) {
    	dungeonSelectScreen.start();
    }
    
    @FXML
    void handleQuitButton(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }
    
    @FXML
    void handleInstructionsButton(ActionEvent event) {
    	instructionsScreen.start();
    }
    
    @FXML
    void handleSettingsButton(ActionEvent event) {
    	settingsScreen.start();
    }
    
    @FXML
    void handleTipsButton(ActionEvent event) {
    	tipsScreen.start();
    }


    public void setDungeonSelectScreen(DungeonSelectScreen dungeonSelectScreen) {
        this.dungeonSelectScreen = dungeonSelectScreen;
    }
    
    public void setInstructionsScreen(InstructionsScreen instructionsScreen) {
        this.instructionsScreen = instructionsScreen;
    }
    
    public void setSettingsScreen(SettingsScreen settingsScreen) {
        this.settingsScreen = settingsScreen;
    }
    
    public void setTipsScreen(TipsScreen tipsScreen) {
        this.tipsScreen = tipsScreen;
    }
      
    
}
