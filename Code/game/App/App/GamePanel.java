package game.App.App;

import game.App.Entity.Player;
import game.App.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 pixel tile
    private final int scale = 3; //scale com.example.rpg_ryisnow.Tile Size by three to make it look better on modern monitors
    public final int tileSize = originalTileSize * scale; // actual tile size
    public final int maxScreenCol = 16; // Biggest screen will show 16 tiles per column
    public final int maxScreenRow = 12; // Biggest screen will show 16 tiles per row
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    private final int fps = 60;

    Thread gameThread; // We are using threads so that the game continues even if the player is idle
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game loop
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
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
