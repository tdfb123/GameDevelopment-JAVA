import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 160;
    private final int HEIGTH = 120;
    private final int SCALE = 4;

    private BufferedImage image;

    public Game(){
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGTH,BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame(){
        frame = new JFrame("Game #1");
        frame.add(this);
        frame.setResizable(false);//impede que o jogador aumente a janela
        frame.pack();
        frame.setLocationRelativeTo(null);//
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//clicar para fechar!
        frame.setVisible(true);
    }

    public synchronized  void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();

    }

    public synchronized  void stop() {

    }

    public static void main(String args[]) {
        Game game = new Game();
        game.start();//aqui inicia o jogo
    }

    public void tick() {//Método da lógica do jogo


    }

    public void render() {//Método dos graficos
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);//colocar entre 2 ou 3
            return;//como se fosse um break.
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 0, 0));
        g.fillRect(0, 0,WIDTH, HEIGTH);
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,WIDTH*SCALE, HEIGTH*SCALE,null);
        bs.show();

    }

    public void run() {//looping para o jogo estar em constante running.
        long lasTime = System.nanoTime();//pega o tempo atual do nosso computador em nano segundos, isso da mais precisão nos fps de maneira profissional
        double amountOfTicks = 60.0;//local onde seta o FPS.
        double ns = 1000000000 / amountOfTicks;//calculo para saber o momento certo de atualizar a tela do jogo
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();//retorna o tempo do computador, usamos isso para contagem do fps, mas é opcional.
        while(isRunning){
            long now = System.nanoTime();
            delta+= (now - lasTime) / ns;
            lasTime = now;
            if(delta >= 1) {
                tick();//atualização da tela.
                render();//renderização da tela.
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                timer+= 1000;
            }

        }
    }
}

