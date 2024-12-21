package game.App.Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectKey extends SuperObject{
    public ObjectKey(){
        setName("Key");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
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
