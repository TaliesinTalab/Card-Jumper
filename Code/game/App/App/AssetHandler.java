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
    public void setObject() {}

}
