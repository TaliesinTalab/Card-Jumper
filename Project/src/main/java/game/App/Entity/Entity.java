package game.App.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private String name;
    protected int worldX, worldY, speed;
    private Rectangle solidArea;
    private int solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn = false;
    private String direction;

    // Setters
    public void setName(String new_name) {this.name = new_name;}
    public void setSolidArea(Rectangle new_solidArea) {solidArea = new_solidArea;}
    public void setSolidAreaX(int x) {solidArea.x = x;}
    public void setSolidAreaY(int y) {solidArea.y = y;}
    public void setSolidAreaWidth(int w) {solidArea.width = w;}
    public void setSolidAreaHeight(int h) {solidArea.height = h;}
    public void setCollisionOn(boolean new_collision) {this.collisionOn = new_collision;}
    public void setDirection(String new_direction) {this.direction = new_direction;}
    protected void setSolidAreaDefaultX(int new_solidAreaDefaultX) {
        this.solidAreaDefaultX = new_solidAreaDefaultX;
    }
    protected void setSolidAreaDefaultY(int new_solidAreaDefaultY) {
        this.solidAreaDefaultY = new_solidAreaDefaultY;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getWorldY() {
        return worldY;
    }
    public int getWorldX() {
        return worldX;
    }
    public int getSpeed() {
        return speed;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public boolean getCollisionOn() {
        return collisionOn;
    }
    public String getDirection() {
        return direction;
    }
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    // Other Methods

}