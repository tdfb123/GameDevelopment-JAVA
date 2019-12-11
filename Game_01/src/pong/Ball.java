package pong;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.Random;

public class Ball {

    public double x,y;//usa-se double, pois os valores da i.a vai ser alterado conforme o passar do jogo, como velocidade por exemplo
    public int width, height;

    public double dx, dy;//direção x e y.
    public double speed = 0.7;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 4;//dimensão da bola
        this.height = 4;//dimensão da bola
        this.dx = new Random().nextGaussian();//método que faz a bola andar na direção
        this.dy = new Random().nextGaussian();//método que faz a bola andar na direção
    }

    public void tick(){

        if(x+(dx*speed) + width >= Game.WIDTH ) {
            dx*=-1;

        }else if(x+(dx*speed) < 0) {
            dx *= -1;
        }

         if(y >= Game.HEIGHT){
             //ponto do inimigo
         }else if(y < 0){
             //ponto do jogador.
         }

        Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
        Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);

        if(bounds.intersects(boundsPlayer)) {//colisao com os jogadores
            dy*=-1;

        }else if(bounds.intersects(boundsEnemy)) {
            dy*=-1;
        }

         x+=dx*speed;
         y+=dy*speed;

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,width,height);
    }
}

