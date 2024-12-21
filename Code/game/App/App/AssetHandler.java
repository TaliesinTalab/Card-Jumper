package game.App.App;

import game.App.Object.ObjectKey;

public class AssetHandler {
    private GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        ObjectKey tmpObjectKey = new ObjectKey();

        tmpObjectKey.setWorldX(11 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(11 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 0);

        tmpObjectKey.setWorldX(2 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(2 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 1);

        tmpObjectKey.setWorldX(11 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(2 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 2);

        tmpObjectKey.setWorldX(2 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(11 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 3);

        tmpObjectKey.setWorldX(45 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(45 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 4);

        tmpObjectKey.setWorldX(2 * gamePanel.tileSize);
        tmpObjectKey.setWorldY(45 * gamePanel.tileSize);
        gamePanel.placeObjectAtIndex(new ObjectKey(), 5);
    }
}
