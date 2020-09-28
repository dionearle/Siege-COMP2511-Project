package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeadController {

    @FXML
    private Button quitButton;

    @FXML
    /**
     * Event actions for quit button, to exit game.
     * @param event 	Quit event.
     */
    void handleQuitButton(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

}
