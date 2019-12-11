package pong;

import java.awt.*;

public class Enemy {

    public double x,y;//usa-se double, pois os valores da i.a vai ser alterado conforme o passar do jogo, como velocidade por exemplo
    public int width, height;
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick(){
        //x+= (Game.ball.x - x - 6);//cálculo para fazer com que o inimigo sempre acompanhe a bola e sempre ganhe(i.a)
        x+= (Game.ball.x - x - 6) * 0.4;//tirando 0.4(40%) da velocidade, assim temos chance de ganhar da i.a





    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,width,height);
    }
}
