package game.App.Entity;

import game.App.App.GamePanel;
import game.App.App.KeyHandler;
import game.App.App.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    private int strength, intelligence, dexterity, cuteness, body, level, health, fullHealth, healthMod;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private final int screenX;
    private final int screenY;
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private int keys = 0;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        //It sets the exact area of the player which area is solid
        setSolidArea(new Rectangle());
        setSolidAreaX(9);
        setSolidAreaY(20);
        setSolidAreaWidth(30);
        setSolidAreaHeight(28);

        //preserves the default value of solidArea
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

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
    public void setDefaultValues(){
        this.worldX = gamePanel.getTileSize() * 23; // starting position
        this.worldY = gamePanel.getTileSize() * 21;
        this.speed = 2;
        setDirection("down");
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
    public void setKeys(int keys) {
        this.keys = keys;
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
    public int getKeys() {
        return this.keys;
    }

    /**
     * This function simply assigns the player character his sprites. If we change a sprite or add one, then
     *          we must also implement that here. The variables below actually belong to the Entity super-class of Player
     */
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
        up1 = setup("boy_up_1");

    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            image = uTool.scaleImage(image,gamePanel.getTileSize(),gamePanel.getTileSize());

        }catch(IOException e){
            e.printStackTrace();
        }
        return image;

    }


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
    public void healToFull() {
        this.health = this.fullHealth;
    }
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
    public void increaseHealth(int mod) {
        this.healthMod += mod;
        calculateFullHealth();
        heal(mod);
    }

    /**
     * update() changes the player's position depending on which button has been pressed. It is also responsible
     *          for cycling through the player sprites via updating spriteCounter and spriteNumber which then play a role
     *          in draw()
     */
    public void update() {

        if (keyHandler.getUpPressed() || keyHandler.getDownPressed()
                || keyHandler.getLeftPressed() || keyHandler.getRightPressed()) {
            if (keyHandler.getUpPressed()) {
                setDirection("up");
            }
            if (keyHandler.getDownPressed()) {
                setDirection("down");
            }
            if (keyHandler.getLeftPressed()) {
                setDirection("left");
            }
            if (keyHandler.getRightPressed()) {
                setDirection("right");
            }

            //check tile collision
            setCollisionOn(false);
            gamePanel.getCollisionChecker().checkTile(this);

            //check object collision
            int objectIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickUpObject(objectIndex);

            //player can only move if collision is false
            if(!getCollisionOn()){

                switch (getDirection()){
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

    /**
     * responsible for item pickup
     */
    public void pickUpObject(int index) {
        if(index != 999) {
            String objectName = gamePanel.getPlacedObjects()[index].getName();

            switch (objectName) {
                case "Key":
                    keys++;
                    gamePanel.playSE(2);
                    gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                    gamePanel.getUserInterface().showMassage("You got a key!");
                    break;
                case "Door":
                    if(keys > 0) {
                        gamePanel.playSE(1);
                        gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                        keys--;
                        gamePanel.getUserInterface().showMassage("You opened the door!");
                    } else {
                        gamePanel.getUserInterface().showMassage("You need a key!");
                    }
                    break;
                case "Boots":
                    speed +=2;
                    gamePanel.playSE(3);
                    gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                    gamePanel.getUserInterface().showMassage("Speed up!");
                    break;
                case "Adventurer's Backpack":
                    gamePanel.getUserInterface().setGameFinished(true);
                    gamePanel.stopMusic();
                    gamePanel.playSE(4);
                    break;
            }
        }
    }

    /**
     * This is responsible for the actual changing of sprites when the player does something. For example, it
     *          sets the player's image to the corresponding sprite, depending on the current spriteNumber. spriteNumber is
     *          continuously switched by update().
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        switch (getDirection()) {
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
        g2d.drawImage(image, screenX, screenY, null);
    }
}
