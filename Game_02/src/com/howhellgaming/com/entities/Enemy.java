package com.howhellgaming.com.entities;

import com.howhellgaming.main.Game;
import com.howhellgaming.world.World;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{

    private double speed =0.2;//só tem o speed porque o resto está herdando da classe pai


    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick (){
        if((int)x < Game.player.getX() && World.isFree((int)(x+speed),this.getY())){
            x+=speed;
        }
        else if((int)x > Game.player.getX()&& World.isFree((int)(x-speed),this.getY())) {
            x-=speed;
        }

        if((int)y < Game.player.getY()&& World.isFree(this.getX(),(int)(y+speed))){//neste método: use else if se quiser a movimentação por sqm igual tibia, ou use if se quiser movimentação igual pxg na diagonal tambem.
            y+=speed;
        }
        else if((int)y > Game.player.getY() && World.isFree(this.getX(),(int)(y-speed))) {
            y-=speed;
        }
    }

}
