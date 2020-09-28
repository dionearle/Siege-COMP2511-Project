package unsw.dungeon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WinController {

    @FXML
    private Button quitButton;

    @FXML
    void handleQuitButton(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

}
