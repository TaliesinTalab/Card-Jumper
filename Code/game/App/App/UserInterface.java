package game.App.App;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Displays the number of keys the player has collected
 * Tracks and shows the elapsed playtime
 * Displays custom messages on the screen temporarily
 * Shows a congratulatory endgame screen when the player finishes the game
 */

public class UserInterface {

    private GamePanel gamePanel;
    Graphics2D g2d;
    private Font arial_40, arial_80B;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;
    private double playTime;
    private DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    // Setter
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void showMassage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        //set default font
        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        //check the current game state
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            //Do playState stuff later
        }
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPauseScreen();
        }
    }

    //this method set everything for the "pause" state of the game
    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gamePanel.getScreenHeight() / 2;
        g2d.drawString(text, x, y);
    }

    //method for the x value of centered text
    public int getXforCenteredText(String text) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamePanel.getScreenWidth() / 2 - length / 2;
        return x;
    }
}

