package game.App.App;

import game.App.Object.ObjectKey;
import game.App.Object.SuperObject;

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
        ObjectKey tmpObjectKey = new ObjectKey();

        //DEMO CODE START
        tmpObjectKey.setWorldX(2 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(2 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 0);

        tmpObjectKey.setWorldX(8 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(8 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 1);

        tmpObjectKey.setWorldX(16 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(16 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 2);

        tmpObjectKey.setWorldX(32 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(32 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 3);

        tmpObjectKey.setWorldX(40 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(40 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 4);

        tmpObjectKey.setWorldX(2 * gamePanel.getTileSize());
        tmpObjectKey.setWorldY(40 * gamePanel.getTileSize());
        placeObjectAtIndex(new ObjectKey(tmpObjectKey), 5);
        //DEMO CODE END
    }
}
