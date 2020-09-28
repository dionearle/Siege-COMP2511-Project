package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeadScreen {

    private Stage stage;
    private String title;
    private DeadController controller;
    private Scene scene;

    /**
     * Screen to handle a players death.
     * @param stage			Shows specific stage and art given a death.
     * @throws IOException	IOException if fails.
     */
    public DeadScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dead Screen";

        controller = new DeadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dead.fxml"));
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

    public DeadController getController() {
        return controller;
    }
}

