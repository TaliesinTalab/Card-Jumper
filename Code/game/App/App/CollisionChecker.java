package game.App.App;

import game.App.Entity.Entity;
import game.App.Object.SuperObject;

import java.util.Arrays;

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

    /**
     * checks collision between player and object
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999, objectIndex = 0;

        for(SuperObject object : gamePanel.getPlacedObjects()) {
            if(object != null) {
                //Entity's solidAreaPosition
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidArea().x);
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidArea().y);

                //Object's SolidAreaPosition
                object.setSolidAreaX(object.getWorldX() + object.getSolidArea().x);
                object.setSolidAreaY(object.getWorldY() + object.getSolidArea().y);

                switch(entity.getDirection()) {
                    case "up":
                        entity.setSolidAreaY(entity.getSolidArea().y - entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                        case "down":
                            entity.setSolidAreaY(entity.getSolidArea().y + entity.getSpeed());
                            if(entity.getSolidArea().intersects(object.getSolidArea())) {
                                if(object.getCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if(player) index = objectIndex;
                            }
                            break;
                            case "left":
                                entity.setSolidAreaX(entity.getSolidArea().x - entity.getSpeed());
                                if(entity.getSolidArea().intersects(object.getSolidArea())) {
                                    if(object.getCollision()) {
                                        entity.setCollisionOn(true);
                                    }
                                    if(player) index = objectIndex;
                                }
                                break;
                                case "right":
                                    entity.setSolidAreaX(entity.getSolidArea().x + entity.getSpeed());
                                    if(entity.getSolidArea().intersects(object.getSolidArea())) {
                                        if(object.getCollision()) {
                                            entity.setCollisionOn(true);
                                        }
                                        if(player) index = objectIndex;
                                    }
                                    break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());

                object.setSolidAreaX(object.getSolidAreaDefaultX());
                object.setSolidAreaY(object.getSolidAreaDefaultY());
            }
            objectIndex++;
        }
        return index;
    }
}
