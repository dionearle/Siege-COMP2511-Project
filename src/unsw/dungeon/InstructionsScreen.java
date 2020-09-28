package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionsScreen {

    private Stage stage;
    private String title;
    private InstructionsController controller;
    private Scene scene;

    public InstructionsScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Instructions";

        controller = new InstructionsController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("instructions.fxml"));
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

    public InstructionsController getController() {
        return controller;
    }
}

