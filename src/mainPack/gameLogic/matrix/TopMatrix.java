package mainPack.gameLogic.matrix;

import mainPack.gameLogic.ImageBox;

public class TopMatrix extends Matrix {

    public TopMatrix(int cols, int rows){
        field = new ImageBox[cols][rows];
        initField(cols,rows);
    }


    @Override
    void initField(int cols, int rows) {
        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                field[i][j] = ImageBox.CLOSED;
    }
}
