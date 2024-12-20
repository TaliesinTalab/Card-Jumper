package game.App.Object;

import game.App.App.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//SuperClass which is never instantiated (could be made abstract)
public class SuperObject {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;

    //draws objects on screen
    public void draw(Graphics2D g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.getPlayer().getX_coordinate();
        int screenY = worldY - gamePanel.getPlayer().getY_coordinate();

        if(worldX + gamePanel.tileSize >  gamePanel.getPlayer().x &&
                worldX - gamePanel.tileSize <  gamePanel.getPlayer().x &&
                worldY + gamePanel.tileSize >  gamePanel.getPlayer().y &&
                worldY - gamePanel.tileSize <  gamePanel.getPlayer().y)
            g2d.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

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
}
