package mainPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {


    @FXML
    private Button startGameButton;

    @FXML
    void initialize() {
        startGameButton.setOnAction(actionEvent -> {
            startGameButton.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("difficultMenu.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image("file:src/mainPack/resources/img/icon.png"));
                stage.setTitle("Minesweeper");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
