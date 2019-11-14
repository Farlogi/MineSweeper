package mainPack.gameLogic;

import javafx.scene.image.Image;

public enum ImageBox {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB,
    ICON;

    private Image image;

    public ImageBox getNextNumberBox(){
        return ImageBox.values()[this.ordinal() + 1];
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
