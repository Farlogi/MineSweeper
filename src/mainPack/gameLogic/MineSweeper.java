package mainPack.gameLogic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MineSweeper {
    private int cols;
    private int rows;
    private int bombs;
    private final int IMG_SIZE = 50;

    public MineSweeper(int cols, int rows, int bombs) {
        this.cols = cols;
        this.rows = rows;
        this.bombs = bombs;
    }

    public void initialize() throws IOException {
        Group root = new Group();
        Image image = new Image("file:src/mainPack/resources/img/closed.png");
        ImageView[][] imageViews = new ImageView[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                imageViews[i][j] = new ImageView(image);
                imageViews[i][j].setY(i * IMG_SIZE);
                imageViews[i][j].setX(j * IMG_SIZE);
                root.getChildren().add(imageViews[i][j]);
            }

        }
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            ImageView imageView = null;
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    imageView = new ImageView(new Image("file:src/mainPack/resources/img/bombed.png"));
                    imageView.setY((int)mouseEvent.getSceneY() / 50 * 50);
                    imageView.setX((int)mouseEvent.getSceneX() / 50 * 50);
                    root.getChildren().set(((int) (mouseEvent.getSceneY() / 50) * cols) + (int) mouseEvent.getSceneX() / 50, imageView);
                }
        });
        Stage stage = new Stage();
        stage.setScene(new Scene(root, cols * IMG_SIZE, rows * IMG_SIZE));
        stage.getIcons().add(new Image("file:src/mainPack/resources/img/icon.png"));
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.show();

    }
}
