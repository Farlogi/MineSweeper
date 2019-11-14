package mainPack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mainPack.gameLogic.MineSweeper;


public class DifficultMenuController {

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
        mediumModeButton.setOnAction(actionEvent -> {
            easyModeButton.getScene().getWindow().hide();
            startNewGame(16,16,40);
        });
        hardModeButton.setOnAction(actionEvent -> {
            easyModeButton.getScene().getWindow().hide();
            startNewGame(30,16,99);
        });

    }

    private void startNewGame(int cols, int rows, int bombs) {
        MineSweeper mineSweeper = new MineSweeper(cols, rows, bombs);
        mineSweeper.initialize();

    }
}
