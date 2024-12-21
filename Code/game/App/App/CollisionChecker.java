package game.App.App;

import game.App.Entity.Entity;

public class CollisionChecker {
    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() +entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY= entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY= entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX/gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX/gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY/gamePanel.getTileSize();
        int tileNum1, tileNum2;

        switch(entity.getDirection()) {
            //here is to check if left shoulder(col) and right shoulder(col) are hitting the tile which has set Collision
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
        }
        }

    }

