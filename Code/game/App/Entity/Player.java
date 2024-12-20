package game.App.Entity;

import game.App.App.GamePanel;
import game.App.App.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private int strength, intelligence, dexterity, cuteness, body, level, health, fullHealth, healthMod;
    public GamePanel gamePanel;
    public KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x =8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();
    }

    // Setter
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setCuteness(int cuteness) {
        this.cuteness = cuteness;
    }
    public void setBody(int body) {
        this.body = body;
    }

    // Getter
    public int getStrength() {return this.strength;}
    public int getIntelligence() {return this.intelligence;}
    public int getDexterity() {return this.dexterity;}
    public int getCuteness() {return this.cuteness;}
    public int getBody() {return this.body;}
    public int getLevel() {return this.level;}
    public int getHealth() {return this.health;}
    public int getFullHealth() {return this.fullHealth;}
    public int getScreenX() { return this.screenX;}
    public int getScreenY() { return this.screenY;}

    // Additional Functions
    public void levelUp() {
        this.level++;
        calculateFullHealth();
        healToFull();
        System.out.println("You have levelled up!\n" +
                "Your current level is: " + this.level);
    }
    public void cutenessUp() {
        this.cuteness++;
        System.out.println("You become cuter!");
    }
    public void cutenessDown() {
        this.cuteness--;
        System.out.println("You feel less cute...");
    }
    public void calculateFullHealth() {
        this.fullHealth = (this.body*2)+(this.level*5)+healthMod;
    }
    // Returns the user back to their full health..
    public void healToFull() {
        this.health = this.fullHealth;
    }
    // Heals the user by a specified amount.
    //      e.g. a potion healing you by 10.
    // args: int 'amount' is the amount that you will be healed by
    public void heal(int amount) {
        if (this.health + amount < this.fullHealth) {
            this.health += amount;
            System.out.println("Healed to " + this.health);
        }
        else {
            healToFull();
            System.out.println("Healed to full health.");
        }
    }
    // Is meant to be used for items that permanently increase the user's health.
    //      e.g. a potion adding +4 to your full health
    // args: int 'mod' is the amount that will be added to your hp
    public void increaseHealth(int mod) {
        this.healthMod += mod;
        calculateFullHealth();
        heal(mod);
    }

    public void update() {
        // update() changes the player's position depending on which button has been pressed. It is also responsible
        // for cycling through the player sprites via updating spriteCounter and spriteNumber which then play a role
        // in draw()
        if (keyHandler.upPressed || keyHandler.downPressed
                || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn =false;
            gamePanel.collisionChecker.checkTile(this);

            if(!collisionOn){

                switch (direction){
                    case "up":
                        this.worldY -= this.speed;
                        break;
                    case "down":
                        this.worldY += this.speed;
                        break;
                    case "left":
                        this.worldX -= this.speed;
                        break;
                    case "right":
                        this.worldX += this.speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 30) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                }
                else{
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2d) {
        //This is responsible for the actual changing of sprites when the player does something. For example, it
        // sets the player's image to the corresponding sprite, depending on the current spriteNumber. spriteNumber is
        // continuously switched by update().
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;
            default:
                break;
        }
        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
    public void getPlayerImage(){
        // This function simply assigns the player character his sprites. If we change a sprite or add one, then
        // we must also implement that here. The variables below actually belong to the Entity super-class of Player
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        this.worldX = gamePanel.tileSize * 23; // starting position
        this.worldY = gamePanel.tileSize * 21;
        this.speed = 2;
        direction = "down";
        this.strength = 1;
        this.intelligence = 1;
        this.dexterity = 1;
        this.cuteness = 10; // cuteness goes down over time (as the MC becomes more traumatised / bloodied)
        this.body = 1;
        this.level = 1;
        this.healthMod = 3;
        calculateFullHealth();
        this.health = this.fullHealth;
    }

}
