package game.App.Object;

import game.App.App.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectBoots extends SuperObject {
    GamePanel gamePanel;
    public ObjectBoots(GamePanel gamePanel){
        setName("Boots");
        this.gamePanel = gamePanel;

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
            uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectBoots(ObjectBoots boots){
        setName("Boots");

        setWorldX(boots.getWorldX());
        setWorldY(boots.getWorldY());

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
