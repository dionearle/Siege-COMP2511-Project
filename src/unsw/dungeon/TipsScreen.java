package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TipsScreen {

    private Stage stage;
    private String title;
    private TipsController controller;
    private Scene scene;

    public TipsScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Tips";

        controller = new TipsController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tips.fxml"));
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

    public TipsController getController() {
        return controller;
    }
}

