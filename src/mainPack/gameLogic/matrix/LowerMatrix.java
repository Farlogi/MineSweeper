package mainPack.gameLogic.matrix;

import mainPack.gameLogic.Coord;
import mainPack.gameLogic.ImageBox;

import java.util.Random;

public class LowerMatrix extends Matrix {

    private Random random;
    private Coord coord;

    public LowerMatrix(int cols, int rows) {
        field = new ImageBox[rows][cols];
        random = new Random();
        initField(cols, rows);
    }

    public Coord getRandomCoord (){
        coord = new Coord(random.nextInt(field[0].length), random.nextInt(field.length));
        return coord;
    }

}
