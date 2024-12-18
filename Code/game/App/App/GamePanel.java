package game.App.App;

import game.App.Entity.Player;
import game.App.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 pixel tile
    private final int scale = 3; //Tile-Size by three to make it look better on modern monitors
    public final int tileSize = originalTileSize * scale; // actual tile size
    public final int maxScreenCol = 16; // Biggest screen will show 16 tiles per column
    public final int maxScreenRow = 12; // Biggest screen will show 16 tiles per row
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    private final int fps = 60;

    Thread gameThread; // We are using threads so that the game continues even if the player is idle
    KeyHandler keyHandler = new KeyHandler(); // This is needed for us to read inputs
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this); //responsible for the game-map being rendered

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        //Starts the thread responsible to keep the game running, used in App.java
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // This function acts as the main game-loop and will continue running until the gameThread is killed.
        // It first calculates how often to run the loop per second (drawInterval). The try-catch block within the loop
        // ensures that it is held to the update-amount per second. This is important because otherwise a single
        // button press might cause the player to fly in a direction due to the program having updated itself very
        // often in that short time frame.
        double drawInterval = (double) 1000 /fps;
        double nextUpdate = System.currentTimeMillis() + drawInterval;

        while(gameThread != null) {
            update(); // updates the character's position
            repaint(); // calls paintComponent method that we have overwritten below
            try {
                double remainingTime = nextUpdate - System.currentTimeMillis();
                remainingTime = remainingTime / 1000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextUpdate += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        //Responsible for actually 'drawing' the visuals on screen
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
