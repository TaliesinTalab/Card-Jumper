package game.App.App;

import game.App.Object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

    /** Displays the number of keys the player has collected
    * Tracks and shows the elapsed playtime
    * Displays custom messages on the screen temporarily
    * Shows a congratulatory endgame screen when the player finishes the game
    */

public class UserInterface {

    private GamePanel gamePanel;
    private Font arial_40, arial_80B;
    private BufferedImage keyImage;
    private  boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;
    private double playTime;
    private DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        ObjectKey key = new ObjectKey(gamePanel);
        keyImage = key.getImage();
    }

    // Setter
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void showMassage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D graphics2D){ // to display the Kay that player has

        if(gameFinished) {

            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the ...";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.getScreenWidth()/2 - textLenght/2;
            y = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()*3);
            graphics2D.drawString(text, x, y);

            text = "Your Time is: " + dFormat.format(playTime) + "!";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.getScreenWidth()/2 - textLenght/2;
            y = gamePanel.getScreenHeight()/2 + (gamePanel.getTileSize()*4);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial_80B);
            graphics2D.setColor(Color.black);
            text = "Congratulations!";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();

            x = gamePanel.getScreenWidth()/2 - textLenght/2;
            y = gamePanel.getScreenHeight()/2 + (gamePanel.getTileSize()*2);
            graphics2D.drawString(text, x, y);


            gamePanel.killGameThread();


        } else {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(keyImage, gamePanel.getTileSize()/2, gamePanel.getTileSize()/2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            graphics2D.drawString("x " + gamePanel.getPlayer().getKeys(),74,65);

            // TIME
            playTime += (double) 1/60;
            graphics2D.drawString("Time:" + dFormat.format(playTime), gamePanel.getTileSize()*11, 65);

            // MESSAGE
            if(messageOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 5);

                messageCounter++;

                if (messageCounter > 120) { // 120 Frames are 2 seconds
                    messageCounter = 0;
                    messageOn = false;
                }
            }



        }

    }
}

