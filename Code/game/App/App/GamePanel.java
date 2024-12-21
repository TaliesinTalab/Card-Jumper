package game.App.App;

import game.App.Entity.Player;
import game.App.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread; // We are using threads so that the game continues even if the player is idle
    private KeyHandler keyHandler = new KeyHandler(); // This is needed for us to read inputs
    private Player player = new Player(this, keyHandler);
    private TileManager tileManager = new TileManager(this); //responsible for the game-map being rendered
    private CollisionChecker collisionChecker = new CollisionChecker(this);

    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 pixel tile
    private final int scale = 3; //Tile-Size by three to make it look better on modern monitors
    private final int tileSize = originalTileSize * scale; // actual tile size
    private final int maxScreenCol = 16; // Biggest screen will show 16 tiles per column
    private final int maxScreenRow = 12; // Biggest screen will show 16 tiles per row
    private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow;// 576 pixels

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;

    // GAME SETTINGS
    private final int fps = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }


    // Getters
    public int getTileSize() {
        return tileSize;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public TileManager getTileManager() {
        return tileManager;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public Player getPlayer() {
        return player;
    }
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    // Other Methods

    /**
     * Starts the thread responsible to keep the game running, used in App.java
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     *  This function acts as the main game-loop and will continue running until the gameThread is killed.
     *          It first calculates how often to run the loop per second (drawInterval). The try-catch block within the loop
     *          ensures that it is held to the update-amount per second. This is important because otherwise a single
     *          button press might cause the player to fly in a direction due to the program having updated itself very
     *          often in that short time frame.
     */
    @Override
    public void run() {
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

    /**
     * Responsible for actually 'drawing' the visuals on screen
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
