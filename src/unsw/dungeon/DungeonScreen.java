package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DungeonScreen {
    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;

    /**
     * DungeonScreen displays whole dungeon and stage for gameplay.
     * @param stage			Stage with all visual elements included.
     * @param dungeon 		Dungeon with all gameplay and element behaviours included.
     * @throws IOException	Throws exception on failure.
     */
    public DungeonScreen(Stage stage, String dungeon) throws IOException {
        this.stage = stage;
        title = "Dungeon";
        stage.getIcons().add(new Image("exit.png"));
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeon);
        controller = dungeonLoader.loadController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    public void start() {
    	stage.getIcons().add(new Image("exit.png"));
    	
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    public DungeonController getController() {
    	return controller;
    }
    
}
