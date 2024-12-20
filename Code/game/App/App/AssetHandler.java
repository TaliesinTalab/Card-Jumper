package game.App.App;

import game.App.Object.ObjectKey;

public class AssetHandler {
    private GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.getPlacedObjects()[0] = new ObjectKey();
        gamePanel.getPlacedObjects()[0].setWorldX(2 * gamePanel.tileSize);
        gamePanel.getPlacedObjects()[0].setWorldY(4 * gamePanel.tileSize);
    }
}
