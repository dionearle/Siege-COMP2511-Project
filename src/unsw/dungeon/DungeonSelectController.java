package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DungeonSelectController {

	@FXML
    private Button dungeon1Button;

    @FXML
    private Button dungeon2Button;

    @FXML
    private Button dungeon3Button;

    @FXML
    private Button dungeon4Button;

    @FXML
    private Button backButton;
    
    private StartScreen startScreen;
    
    private DungeonScreen dungeon1Screen;
    
    private DungeonScreen dungeon2Screen;
    
    private DungeonScreen dungeon3Screen;
    
    private DungeonScreen dungeon4Screen;

    @FXML
    void handleBackButton(ActionEvent event) {
    	startScreen.start();
    }
    
    @FXML
    void handleDungeon1Button(ActionEvent event) {
    	dungeon1Screen.start();
    }
    
    @FXML
    void handleDungeon2Button(ActionEvent event) {
    	dungeon2Screen.start();
    }

    @FXML
    void handleDungeon3Button(ActionEvent event) {
    	dungeon3Screen.start();
    }

    @FXML
    void handleDungeon4Button(ActionEvent event) {
    	dungeon4Screen.start();
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }
    
    public void setDungeon1Screen(DungeonScreen dungeonScreen) {
        this.dungeon1Screen = dungeonScreen;
    }
    
    public void setDungeon2Screen(DungeonScreen dungeonScreen) {
        this.dungeon2Screen = dungeonScreen;
    }
    
    public void setDungeon3Screen(DungeonScreen dungeonScreen) {
        this.dungeon3Screen = dungeonScreen;
    }
    
    public void setDungeon4Screen(DungeonScreen dungeonScreen) {
        this.dungeon4Screen = dungeonScreen;
    }

}
