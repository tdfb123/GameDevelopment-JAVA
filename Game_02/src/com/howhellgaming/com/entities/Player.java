package com.howhellgaming.com.entities;

import com.howhellgaming.main.Game;
import com.howhellgaming.world.Camera;
import com.howhellgaming.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    public boolean right,up, left, down;
    public int right_dir = 0,left_dir = 1;
    public int dir = right_dir;
    public double speed = 0.5;

    private int frames = 0,maxFrames = 5, index = 0, maxIndex = 3;
    private boolean moved = false;
    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        rightPlayer = new BufferedImage[4];//4 sprites de animação da direita do player(sprite)
        leftPlayer = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);//calculo realizado para seguir a sequencia de sprites no paint.net
        }

        for (int i = 0; i < 4; i++) {
            leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);

        }
    }
    public void tick() {
        moved = false;
        if (right && World.isFree((int)(x+speed),this.getY())) {//verifica na hora do personagem mover-se, se há algum muro ou objeto estatico, se houver, não passa!
            moved = true;
            dir = right_dir;
            x += speed;
        }
        else if (left && World.isFree((int)(x-speed),this.getY())){//negativo porque é para esquerda
            moved = true;
            dir = left_dir;
            x -= speed;
        }
        if (up && World.isFree(this.getX(),(int)(y-speed))) {
            moved = true;
            y -= speed;
        }
        else if (down && World.isFree(this.getX(),(int)(y+speed))) {
            moved = true;
            y += speed;
        }

        if(moved) {
            frames++;
            if(frames == maxFrames) {
                frames = 0;
                index++;
                if(index > maxIndex)
                    index = 0;
            }
        }

        Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2),0, World.WIDTH*16 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY() - (Game.HEIGTH / 2), 0,World.HEIGHT*16 - Game.HEIGTH);

    }




    public void render(Graphics g){
        if(dir == right_dir){
            g.drawImage(rightPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
        }else if(dir == left_dir){
            g.drawImage(leftPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);

        }
    }

}
