import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private final int WIDTH = 160;
    private final int HEIGTH = 120;
    private final int SCALE = 3;

    public Game(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
        frame = new JFrame("Game #1");//titulo do aplicativo
        frame.add(this);
        frame.setResizable(false);//impede que o jogador aumente a janela
        frame.pack();
        frame.setLocationRelativeTo(null);//
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quando clickar para fechar, de fato feche!
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        Game game = new Game();
    }

    public void run() {

    }
}
