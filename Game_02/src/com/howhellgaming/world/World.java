package com.howhellgaming.world;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class World {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;


    public World(String path){
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));
            int[] pixels = new int[map.getWidth() * map.getHeight()];//calculo para saber o tamanho do mapa em pixels.
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            tiles = new Tile[map.getWidth() * map.getHeight()];
            map.getRGB(0,0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
            for(int xx = 0; xx < map.getWidth(); xx++){
                for(int yy = 0; yy < map.getHeight(); yy++) {
                    int pixelAtual = pixels[xx + (yy * map.getWidth())];

                    if(pixelAtual == 0xFF000000){
                        //floor/mapa
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);                    }else if(pixelAtual == 0xFFFFFFFF) {
                    }else if(pixelAtual == 0xFFFFFFFF){
                        //parede
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_WALL);
                    }else if(pixelAtual == 0xFFB6FF00){
                        //player
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    }else {
                        //Floor/chão
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        for(int xx = 0; xx < WIDTH; xx++){
            for(int yy = 0; yy < HEIGHT; yy++) {
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);

            }
        }

    }

}
