package mainPack.gameLogic.matrix;

import mainPack.gameLogic.Coord;
import mainPack.gameLogic.ImageBox;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    ImageBox[][] field;

    public List<Coord> getCoordsAround (Coord coord){
        List<Coord> coords = new ArrayList<>();
        Coord around;
        for (int i = coord.getX() - 1; i <= coord.getX() + 1; i++) {
            for (int j = coord.getY() - 1; j <= coord.getY() + 1; j++) {
                around = new Coord(i, j);
                if (!coord.equals(around) && i >= 0 && j >= 0 && i < field[0].length && j < field.length )
                    coords.add(around);
            }
        }
        return coords;
    }

    void initField(int cols, int rows) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                field[i][j] = ImageBox.ZERO;
    }

    public ImageBox getImageBox(Coord coord) {
            return field[ coord.getY() ] [ coord.getX() ];
    }

    public void setImageBox(ImageBox box, Coord coord){
        field[coord.getY()] [coord.getX()] = box;
    }


}
