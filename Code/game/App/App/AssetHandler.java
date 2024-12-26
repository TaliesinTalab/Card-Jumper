package game.App.App;

import game.App.Object.*;

public class AssetHandler {
    private GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * places provided object at provided index of placedObjects array
     */
    public void placeObjectAtIndex(SuperObject object, int index) {
        if (index >= 0 && index < gamePanel.getPlacedObjects().length) {
            SuperObject[] objects = gamePanel.getPlacedObjects();

            objects[index] = object;

            gamePanel.setPlacedObjects(objects);
        }
    }

    /**
     * uses placeObjectAtIndex() to initiate the placedObjects array ( --> GamePanel.setupGame() )
     */
    public void setObject() {
        ObjectKey tmpObjectKey = new ObjectKey(gamePanel);
        ObjectDoor tmpObjectDoor = new ObjectDoor(gamePanel);
        ObjectAdventurersBackpack tmpObjectAdventurersBackpack = new ObjectAdventurersBackpack(gamePanel);
        ObjectBoots tmpObjectBoots = new ObjectBoots(gamePanel);

        //DEMO CODE START
        tmpObjectAdventurersBackpack.setWorldX(6 * gamePanel.getTileSize());
        tmpObjectAdventurersBackpack.setWorldY(6 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectAdventurersBackpack(tmpObjectAdventurersBackpack), 0);

        tmpObjectKey.setWorldX(10 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(10 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 1);

        tmpObjectDoor.setWorldX(8 * gamePanel.getTileSize());
        tmpObjectDoor.setWorldY(12 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectDoor(tmpObjectDoor), 2);

        tmpObjectBoots.setWorldX(9 * gamePanel.getTileSize());
        tmpObjectBoots.setWorldY(16 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectBoots(tmpObjectBoots), 3);
        //DEMO CODE END
    }
}
