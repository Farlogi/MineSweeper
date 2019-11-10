package mainPack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mainPack.gameLogic.MineSweeper;

import java.net.URL;
import java.util.ResourceBundle;


public class DifficultMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button easyModeButton;

    @FXML
    private Button mediumModeButton;

    @FXML
    private Button hardModeButton;

    @FXML
    void initialize() {
        easyModeButton.setOnAction(actionEvent -> {
            easyModeButton.getScene().getWindow().hide();
            startNewGame(9,9,10);
        });

    }

    private void startNewGame(int cols, int rows, int bombs) {
        MineSweeper mineSweeper = new MineSweeper(cols, rows,bombs);
        mineSweeper.initialize();

    }
}
