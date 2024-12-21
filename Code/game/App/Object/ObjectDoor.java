package game.App.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends SuperObject {
    public ObjectDoor(){
        setName("Door");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
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
