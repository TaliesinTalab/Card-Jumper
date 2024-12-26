package game.App.Object;

import game.App.App.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectKey extends SuperObject{

    GamePanel gamePanel;

    public ObjectKey(GamePanel gamePanel){
        setName("Key");
        this.gamePanel = gamePanel;

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
            uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectKey(ObjectKey key){
        setName("Key");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
