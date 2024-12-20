package game.App.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private String name;
    public int worldX, worldY, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    // Setters
    public void setName(String new_name) {this.name = new_name;}
    public void setY_coordinate(int new_y) {this.worldY = new_y;}
    public void setX_coordinate(int new_x) {this.worldX = new_x;}

    // Getters
    public String getName() {
        return name;
    }
    public int getY_coordinate() {
        return worldY;
    }
    public int getX_coordinate() {
        return worldX;
    }

    // Other functions

}