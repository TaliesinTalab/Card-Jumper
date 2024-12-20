package game.App.Tiles;

import game.App.App.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) {
        //Constructor for TileManager, tiles is the actual tile-sprites that we have. We only need one grass-tile
        // so that we can populate the map entirely with them. Imagine it being an 'original tile' that the map
        // generation copies
        this.gamePanel = gamePanel;
        tiles = new Tile[10]; //check later if we can use an ArrayList for this instead
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/maps/BigWorldMap"); //current map is a placeholder
    }

    public void getTileImage() {
        // Much like getPlayerImage(), this function is here to purely assign the TileManager object its sprites
        // Must be updated when new tile sprites are added
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[1].collision=true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tiles[2].collision=true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tiles[4].collision=true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        //This function simply draws the tiles onto the screen. It uses four variables to determine the tile(col & row)
        // and location (x & y). The loop is based on (row < gamePanel.maxScreenRow) because col is reset within the
        // while loop whenever it reaches the maximum. This is done so that it prints all columns for each row indent.
        int col = 0, row = 0;
        while (row < gamePanel.maxWorldRow) {
            int tileNumber = mapTileNumber[col][row];

            int worldX = col * gamePanel.tileSize;
            int worldY = row * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
               worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
               worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
               worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2d.drawImage(tiles[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            col++;

            if (col >= gamePanel.maxWorldCol) {
                col = 0;
                row++;

            }
        }
    }
    public void loadMap(String fileName) {
        //This function parses txt files and updates the TileManager's mapTileNumber 2d-array with the corresponding
        //tile number. Note that a tile number refers to the 'value' assigned to the sprite in getTileImage()
        try{
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();
                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
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
