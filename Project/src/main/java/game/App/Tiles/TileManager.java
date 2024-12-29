package game.App.Tiles;

import game.App.App.GamePanel;
import game.App.App.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] mapTileNumber;

    /**
     * Constructor for TileManager, tiles is the actual tile-sprites that we have. We only need one grass-tile
     *          so that we can populate the map entirely with them. Imagine it being an 'original tile' that the map
     *          generation copies
     */
    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tiles = new Tile[10]; //check later if we can use an ArrayList for this instead
        mapTileNumber = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/BigWorldMap"); //current map is a placeholder
    }

    // Setters & Getters
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public Tile[] getTiles() {
        return tiles;
    }
    public int[][] getMapTileNumber() {
        return mapTileNumber;
    }
    /**
     * Much like getPlayerImage(), this function is here to purely assign the TileManager object its sprites
     *          Must be updated when new tile sprites are added
     */
    public void getTileImage() {

        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "sand", false);
        setup(4, "tree", true);
        setup(5, "dirt", false);
    }
//scale
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/"+ imageName +".png"))));
            tiles[index].setImage(uTool.scaleImage(tiles[index].getImage(),gamePanel.getTileSize(),gamePanel.getTileSize()));
            tiles[index].setCollision(collision);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    // Other Methods

    /**
     * This function simply draws the tiles onto the screen. It uses four variables to determine the tile(col & row)
     *          and location (x & y). The loop is based on (row < gamePanel.maxScreenRow) because col is reset within the
     *          while loop whenever it reaches the maximum. This is done so that it prints all columns for each row indent.
     */
    public void draw(Graphics2D g2d) {
        int col = 0, row = 0;
        while (row < gamePanel.getMaxWorldRow()) {
            int tileNumber = mapTileNumber[col][row];

            int worldX = col * gamePanel.getTileSize();
            int worldY = row * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
               worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
               worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
               worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

                g2d.drawImage(tiles[tileNumber].getImage(), screenX, screenY,null);
            }
            col++;

            if (col >= gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;

            }
        }
    }

    /**
     * This function parses txt files and updates the TileManager's mapTileNumber 2d-array with the corresponding
     *         tile number. Note that a tile number refers to the 'value' assigned to the sprite in getTileImage()
     */
    public void loadMap(String fileName) {

        try{
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
                String line = br.readLine();
                while (col < gamePanel.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
