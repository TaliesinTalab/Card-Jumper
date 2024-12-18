package game.App.Tiles;

import game.App.App.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10]; //check later if we can use an ArrayList for this instead
        mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        loadMap("/maps/map");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        int col = 0, row = 0, x = 0, y = 0;
        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNumber = mapTileNumber[col][row];
            g2d.drawImage(tiles[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;
            if (col >= gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
    public void loadMap(String fileName) {
        try{
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = br.readLine();
                while (col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gamePanel.maxScreenCol) {
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
