package mainPack;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mainPack.gameLogic.MineSweeper;


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
            try {
                startNewGame(9,9,10);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void startNewGame(int cols, int rows, int bombs) throws IOException {
        MineSweeper mineSweeper = new MineSweeper(cols, rows,bombs);
        mineSweeper.initialize();

    }
}
