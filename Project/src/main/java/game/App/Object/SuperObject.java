package game.App.Object;

import game.App.App.GamePanel;
import game.App.App.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

//SuperClass which is never instantiated (could be made abstract)
public class SuperObject {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    UtilityTool uTool= new UtilityTool();

    /**
     * Draws objects on screen
     */
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            g2d.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    //Getter
    public BufferedImage getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
    public boolean getCollision() {
        return collision;
    }
    public int getWorldX() {
        return worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    //Setter
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public void setSolidAreaX(int x) {solidArea.x = x;}
    public void setSolidAreaY(int y) {solidArea.y = y;}
    public void setSolidAreaWidth(int w) {solidArea.width = w;}
    public void setSolidAreaHeight(int h) {solidArea.height = h;}
    public void setSolidArea(Rectangle rect) {solidArea = rect;}
}
