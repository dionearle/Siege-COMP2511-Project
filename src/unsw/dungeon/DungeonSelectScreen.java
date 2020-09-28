package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonSelectScreen {

    private Stage stage;
    private String title;
    private DungeonSelectController controller;
    private Scene scene;

    public DungeonSelectScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungeon Select Screen";

        controller = new DungeonSelectController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dungeonSelect.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonSelectController getController() {
        return controller;
    }
}

