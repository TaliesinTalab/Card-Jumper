package game.App.Object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectAdventurersBackpack extends SuperObject {
    public ObjectAdventurersBackpack(){
        setName("Adventurer's Backpack");

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/adventurers_backpack.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectAdventurersBackpack(ObjectAdventurersBackpack adventurersBackpack){
        setName("Adventurer's Backpack");

        setWorldX(adventurersBackpack.getWorldX());
        setWorldY(adventurersBackpack.getWorldY());

        try {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/adventurers_backpack.png"))));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
