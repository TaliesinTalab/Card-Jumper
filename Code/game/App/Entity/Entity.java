package game.App.Entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private String name;
    public int x, y, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    // Setters
    public void setName(String new_name) {this.name = new_name;}
    public void setY_coordinate(int new_y) {this.y = new_y;}
    public void setX_coordinate(int new_x) {this.x = new_x;}

    // Getters
    public String getName() {
        return name;
    }
    public int getY_coordinate() {
        return y;
    }
    public int getX_coordinate() {
        return x;
    }

    // Other functions
    abstract void attack();


    // main
    public static void main(String[] args) {
    }
}