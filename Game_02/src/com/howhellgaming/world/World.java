package com.howhellgaming.world;

import com.howhellgaming.com.entities.*;
import com.howhellgaming.main.Game;

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
                    tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
                    if(pixelAtual == 0xFFFFFFFF){
                        //parede
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_WALL);
                    }else if(pixelAtual == 0xFF086900){
                        //player
                        Game.player.setX(xx*16);
                        Game.player.setY(yy*16);
                    }else if(pixelAtual == 0xFFFF1C2B){
                        //Enemy
                        Game.entities.add(new Enemy(xx*16,yy*16,16,16,Entity.ENEMY_EN));

                    }else if(pixelAtual == 0xFFB6FF00){
                        //Weapon
                        Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));

                    }else if(pixelAtual == 0xFFFF006E){
                        //Life Pack
                        Game.entities.add(new Lifepack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN));

                    }else if(pixelAtual == 0xFF3F497F){
                        //Bullet
                        Game.entities.add(new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN));

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
