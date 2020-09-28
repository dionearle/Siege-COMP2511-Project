package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsScreen {

    private Stage stage;
    private String title;
    private SettingsController controller;
    private Scene scene;

    public SettingsScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Settings";

        controller = new SettingsController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
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

    public SettingsController getController() {
        return controller;
    }
}

