package mainPack.gameLogic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainPack.Main;


public class MineSweeper {

    private final int COLS;
    private final int ROWS;
    private final int BOMBS;
    private final int IMG_SIZE = 50;
    private Game game;
    private Group root;
    private Label label;
    private ImageView[][] imageViews;
    private Stage stage;

    public MineSweeper(int COLS, int ROWS, int BOMBS) {
        this.COLS = COLS;
        this.ROWS = ROWS;
        this.BOMBS = BOMBS;
        setImages();
        game = new Game(COLS,ROWS,BOMBS);
    }

    public void initialize() {
        root = initTopMatrix();
        label = new Label();
        label.setLayoutY(IMG_SIZE * ROWS);
        label.setText("Be careful, good luck!");
        root.getChildren().add(label);
        show();
        mouseListener();
    }

    private Group initTopMatrix(){
        root = new Group();
        Coord coord;
        imageViews = new ImageView[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                coord = new Coord(j,i);
                imageViews[i][j] = new ImageView(game.getTopImageBox(coord).getImage());
                imageViews[i][j].setY(i * IMG_SIZE);
                imageViews[i][j].setX(j * IMG_SIZE);
                root.getChildren().add(imageViews[i][j]);
            }
        }
        return root;
    }

    private void show(){
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(ImageBox.ICON.getImage());
        stage.setTitle("Minesweeper");
        stage.setResizable(false);
        stage.show();
    }

    private void mouseListener(){
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (game.getGameStat() == GameStat.PLAYED) {
                Coord coord = new Coord((int) mouseEvent.getSceneX() / IMG_SIZE,
                        (int) mouseEvent.getSceneY() / IMG_SIZE);

                if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                    game.leftClickLogik(coord);

                if (mouseEvent.getButton().equals(MouseButton.SECONDARY))
                    game.rightClickLogik(coord);

                if (mouseEvent.getButton().equals(MouseButton.MIDDLE))
                    newGame();

                changeTopMatrix(root);
                checkWinLose(label);
            }
            else{
                newGame();
            }
        });
    }

    private void changeTopMatrix (Group root){
        Coord coord;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
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

    private void checkWinLose(Label label) {
        if (game.getGameStat() == GameStat.LOSE){
            label.setText("You lose... Big BOOM!!! Press button...");
            root.getChildren().set(ROWS * COLS, label);
            return;
        }

        if (game.playerWin(COLS, ROWS)) {
            game.setGameStat(GameStat.WIN);
            label.setText("Congratulation!!! Press button...");
            root.getChildren().set(ROWS * COLS, label);
        }

    }

    private void newGame(){
        stage.getScene().getWindow().hide();
        Main main = new Main();
        try {
            main.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
