package mainPack.gameLogic;

import mainPack.gameLogic.matrix.LowerMatrix;
import mainPack.gameLogic.matrix.TopMatrix;

import java.util.List;

public class Game {
    private LowerMatrix lowerMatrix;
    private TopMatrix topMatrix;

    public Game(int cols, int rows, int bombs){
        lowerMatrix = new LowerMatrix(cols, rows, bombs);
        topMatrix = new TopMatrix(cols, rows);
        setBombs(bombs);
    }



    private void fixBoxesAroundBombs(Coord coord)  {
        List<Coord> coords = lowerMatrix.getCoordsAround(coord);
        for(Coord around: coords) {
            if (lowerMatrix.getImageBox(around) != ImageBox.BOMB && lowerMatrix.getImageBox(around) != null) {
                lowerMatrix.setImageBox(lowerMatrix.getImageBox(around).getNextNumberBox(), around);
            }
        }


    }

    private void setBombs(int bombs) {
        Coord coord;
        while (bombs != 0) {
            coord = lowerMatrix.getRandomCoord();
            if (lowerMatrix.getImageBox(coord) != ImageBox.BOMB) {
                lowerMatrix.setImageBox(ImageBox.BOMB, coord);
                fixBoxesAroundBombs(coord);
                bombs--;
            }
        }
    }

    public ImageBox getTopImageBox(Coord coord){
        return topMatrix.getImageBox(coord);
    }


    public void leftClickLogik (Coord coord){
        if (topMatrix.getImageBox(coord) == ImageBox.CLOSED) {
            if (lowerMatrix.getImageBox(coord) == ImageBox.ZERO) {
                topMatrix.setImageBox(lowerMatrix.getImageBox(coord), coord);
                List<Coord> coords = lowerMatrix.getCoordsAround(coord);
                System.out.println(coords.size());
                for (Coord x : coords)
                    leftClickLogik(x);
            }
            else if(lowerMatrix.getImageBox(coord) == ImageBox.BOMB){
                
            }
            else {
                topMatrix.setImageBox(lowerMatrix.getImageBox(coord), coord);
            }
        }
    }



}
