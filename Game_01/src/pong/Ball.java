package pong;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.Random;
import java.time.*;

public class Ball {

    public double x,y;//usa-se double, pois os valores da i.a vai ser alterado conforme o passar do jogo, como velocidade por exemplo
    public int width, height;

    public double dx, dy;//direção x e y.
    public double speed = 1.2;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 4;//dimensão da bola
        this.height = 4;//dimensão da bola

        int angle = new Random().nextInt(120 - 45) + 45;//ângulo da direção inicial da bola
        this.dx = Math.cos(Math.toRadians(angle));//método que faz a bola andar
        this.dy = Math.sin(Math.toRadians(angle));//método que faz a bola andar
    }

    public void tick() throws InterruptedException {

        if(x+(dx*speed) + width >= Game.WIDTH ) {
            dx*=-1;

        }else if(x+(dx*speed) < 0) {
            dx *= -1;
        }

         if(y >= Game.HEIGHT){
             System.out.println("Ponto do inimigo!");
             new Game();// é usado para resetar o jogo
             Thread.sleep(1000);//sleep de 1 segundo para poder se preparar para proximo round
             System.out.println("Recomeçando em: ");
             Thread.sleep(1000);
             System.out.println("3");
             Thread.sleep(1000);
             System.out.println("2");
             Thread.sleep(1000);
             System.out.println("1");
             Thread.sleep(1000);
             return;

         }else if(y < 0){
             System.out.println("Ponto nosso! =D");
             new Game();// é usado para resetar o jogo
             Thread.sleep(1000);
             System.out.println("Recomeçando em: ");
             Thread.sleep(1000);
             System.out.println("3");
             Thread.sleep(1000);
             System.out.println("2");
             Thread.sleep(1000);
             System.out.println("1");
             Thread.sleep(1000);
             return;
         }

        Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
        Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);

        if(bounds.intersects(boundsPlayer)) {//colisao com os jogadores
            int angle = new Random().nextInt(120 - 45) + 45;//ângulo da direção inicial da bola
            this.dx = Math.cos(Math.toRadians(angle));//método que faz a bola andar
            this.dy = Math.sin(Math.toRadians(angle));//método que faz a bola andar
            if(dy > 0)
                dy*=-1;


        }else if(bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(120 - 45) + 45;//ângulo da direção inicial da bola
            this.dx = Math.cos(Math.toRadians(angle));//método que faz a bola andar
            this.dy = Math.sin(Math.toRadians(angle));//método que faz a bola andar
            if(dy < 0)
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

