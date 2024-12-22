package game.App.Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectBoots extends SuperObject {
    public ObjectBoots(){
        setName("Boots");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
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
