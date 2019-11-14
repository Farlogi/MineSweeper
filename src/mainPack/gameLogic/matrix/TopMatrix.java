package mainPack.gameLogic.matrix;

import mainPack.gameLogic.ImageBox;

public class TopMatrix extends Matrix {

    public TopMatrix(int cols, int rows){
        field = new ImageBox[rows][cols];
        initField(cols,rows);
    }

    @Override
    void initField(int cols, int rows) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                field[i][j] = ImageBox.CLOSED;
    }

}
