package mainPack.gameLogic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MineSweeper {
    private Game game;
    private Group root;
    ImageView[][] imageViews;
    private final int COLS;
    private final int ROWS;
    private final int BOMBS;
    private final int IMG_SIZE = 50;


    public MineSweeper(int COLS, int ROWS, int BOMBS) {
        this.COLS = COLS;
        this.ROWS = ROWS;
        this.BOMBS = BOMBS;
        setImages();
        game = new Game(COLS,ROWS,BOMBS);
    }

    private Group initTopMatrix(){
        root = new Group();
        Coord coord;
        imageViews = new ImageView[COLS][ROWS];
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                coord = new Coord(j,i);
                imageViews[i][j] = new ImageView(game.getTopImageBox(coord).getImage());
                imageViews[i][j].setY(i * IMG_SIZE);
                imageViews[i][j].setX(j * IMG_SIZE);
                root.getChildren().add(imageViews[i][j]);
            }
        }
        return root;
    }

    private void changeTopMatrix (Group root){
        Coord coord;
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                coord = new Coord(j,i);
                imageViews[i][j] = new ImageView(game.getTopImageBox(coord).getImage());
                imageViews[i][j].setY(i * IMG_SIZE);
                imageViews[i][j].setX(j * IMG_SIZE);
                root.getChildren().set(i * COLS + j, imageViews[i][j]);
            }
        }
    }


    private void setImages(){
        for(ImageBox box: ImageBox.values()){
            box.setImage(getImage(box.name()));
        }
    }

    private Image getImage(String name){
        Image image = new Image("file:src/mainPack/resources/img/" + name.toLowerCase() + ".png");
        return image;
    }

    public void initialize() {
        root = initTopMatrix();

        root.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            ImageView imageView;
            Coord coord = new Coord((int)mouseEvent.getSceneX() / IMG_SIZE,
                                    (int)mouseEvent.getSceneY() / IMG_SIZE);

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                game.leftClickLogik(coord);
                changeTopMatrix(root);
            }
        });
        show();

    }

    private void show(){
        Stage stage = new Stage();
        stage.setScene(new Scene(root, COLS * IMG_SIZE, ROWS * IMG_SIZE));
        stage.getIcons().add(ImageBox.ICON.getImage());
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.show();
    }
}
