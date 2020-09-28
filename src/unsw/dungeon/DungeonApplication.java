package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	
	private StartScreen startScreen;

	@Override
    public void start(Stage primaryStage) throws IOException {
		
        startScreen = new StartScreen(primaryStage);
        DungeonSelectScreen dungeonSelectScreen = new DungeonSelectScreen(primaryStage);
        InstructionsScreen instructionsScreen = new InstructionsScreen(primaryStage);
        SettingsScreen settingsScreen = new SettingsScreen(primaryStage);
        TipsScreen tipsScreen = new TipsScreen(primaryStage);
        WinScreen winScreen = new WinScreen(primaryStage);
        DeadScreen deadScreen = new DeadScreen(primaryStage);
        
        DungeonScreen dungeon1Screen = new DungeonScreen(primaryStage, "marking.json");
        DungeonScreen dungeon2Screen = new DungeonScreen(primaryStage, "advanced.json");
        DungeonScreen dungeon3Screen = new DungeonScreen(primaryStage, "advanced3.json");
        DungeonScreen dungeon4Screen = new DungeonScreen(primaryStage, "advanced4.json");
        
        startScreen.getController().setDungeonSelectScreen(dungeonSelectScreen);
        startScreen.getController().setInstructionsScreen(instructionsScreen);
        startScreen.getController().setSettingsScreen(settingsScreen);
        startScreen.getController().setTipsScreen(tipsScreen);
        
        dungeonSelectScreen.getController().setStartScreen(startScreen);
        instructionsScreen.getController().setStartScreen(startScreen);
        settingsScreen.getController().setStartScreen(startScreen);
        tipsScreen.getController().setStartScreen(startScreen);
        
        dungeonSelectScreen.getController().setDungeon1Screen(dungeon1Screen);
        dungeonSelectScreen.getController().setDungeon2Screen(dungeon2Screen);
        dungeonSelectScreen.getController().setDungeon3Screen(dungeon3Screen);
        dungeonSelectScreen.getController().setDungeon4Screen(dungeon4Screen);
        
        dungeon1Screen.getController().setDeadScreen(deadScreen);
        dungeon2Screen.getController().setDeadScreen(deadScreen);
        dungeon3Screen.getController().setDeadScreen(deadScreen);
        dungeon4Screen.getController().setDeadScreen(deadScreen);
        
        dungeon1Screen.getController().setWinScreen(winScreen);
        dungeon2Screen.getController().setWinScreen(winScreen);
        dungeon3Screen.getController().setWinScreen(winScreen);
        dungeon4Screen.getController().setWinScreen(winScreen);
        
        startScreen.start();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
