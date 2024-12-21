package game.App.Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject {
    public ObjectDoor(){
        setName("door");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectDoor(ObjectDoor door){
        setName("door");

        setWorldX(door.getWorldX());
        setWorldY(door.getWorldY());

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
