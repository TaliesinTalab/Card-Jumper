package game.App.Object;

import game.App.App.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject {
    GamePanel gamePanel;

    public ObjectDoor(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Door");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
            uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
        } catch(IOException e) {
            e.printStackTrace();
        }

        setCollision(true);
    }

    public ObjectDoor(ObjectDoor door){
        setName("Door");

        setWorldX(door.getWorldX());
        setWorldY(door.getWorldY());

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }

        setCollision(true);
    }
}
