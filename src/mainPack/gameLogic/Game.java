package mainPack.gameLogic;

import mainPack.gameLogic.matrix.LowerMatrix;
import mainPack.gameLogic.matrix.TopMatrix;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private LowerMatrix lowerMatrix;
    private TopMatrix topMatrix;
    private List<Coord> allBombCoords;
    private GameStat gameStat;

    public Game(int cols, int rows, int bombs){
        gameStat = GameStat.PLAYED;
        lowerMatrix = new LowerMatrix(cols, rows);
        topMatrix = new TopMatrix(cols, rows);
        allBombCoords = new ArrayList<>();
        setBombs(bombs);
    }

    public GameStat getGameStat() {
        return gameStat;
    }

    public void setGameStat(GameStat gameStat) {
        this.gameStat = gameStat;
    }

    public ImageBox getTopImageBox(Coord coord){
        return topMatrix.getImageBox(coord);
    }

    public void leftClickLogik (Coord coord){
        if (topMatrix.getImageBox(coord) == ImageBox.CLOSED) {
            if (lowerMatrix.getImageBox(coord) == ImageBox.ZERO) {
                topMatrix.setImageBox(lowerMatrix.getImageBox(coord), coord);
                List<Coord> coords = lowerMatrix.getCoordsAround(coord);
                for (Coord x : coords)
                    leftClickLogik(x);
            }
            else if(lowerMatrix.getImageBox(coord) == ImageBox.BOMB){
                openBombs();
                gameStat = GameStat.LOSE;
            }
            else {
                topMatrix.setImageBox(lowerMatrix.getImageBox(coord), coord);
            }
        }
    }

    public void rightClickLogik(Coord coord) {
        if (topMatrix.getImageBox(coord) == ImageBox.CLOSED) {
            topMatrix.setImageBox(ImageBox.FLAGED, coord);
            return;
        }

        if (topMatrix.getImageBox(coord) == ImageBox.FLAGED)
            topMatrix.setImageBox(ImageBox.CLOSED, coord);
    }

    public boolean playerWin(int cols, int rows){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (topMatrix.getImageBox(new Coord(j, i)) == ImageBox.CLOSED)
                    return false;

                if (lowerMatrix.getImageBox(new Coord(j, i)) == ImageBox.BOMB &&
                    topMatrix.getImageBox(new Coord(j, i)) != ImageBox.FLAGED)
                    return false;
            }
        }
        return true;
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
            if (thisCoordInCorner(coord))
                continue;
            if (lowerMatrix.getImageBox(coord) != ImageBox.BOMB) {
                lowerMatrix.setImageBox(ImageBox.BOMB, coord);
                fixBoxesAroundBombs(coord);
                allBombCoords.add(coord);
                bombs--;
            }
        }
    }

    private boolean thisCoordInCorner (Coord coord){
        if (coord.equals(new Coord(0,0)) || coord.equals(new Coord(0,1)) ||
                coord.equals(new Coord(1,0)) || coord.equals(new Coord(1,1)))
            return true;
        return false;
    }

    private void openBombs() {
        for(Coord coord: allBombCoords)
            topMatrix.setImageBox(ImageBox.BOMBED, coord);
    }

}
