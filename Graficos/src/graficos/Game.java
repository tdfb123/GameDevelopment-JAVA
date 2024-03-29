package graficos;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 240;//final porque é uma constate e não sofre alteração
    private final int HEIGTH = 160;
    private final int SCALE = 3;
    private int x = 0;

    private BufferedImage image;

    private Spritesheet sheet;
    private BufferedImage[] player;//Alterado para Array para adicionar 2 sprites agora.
    private int frames = 0;
    private int maxFrames = 20;//quanto menor, mais rápido a animação.
    private int curAnimation = 0, maxAnimation = 2;
    public Game(){
        sheet = new Spritesheet("/spritesheet.png");
        player = new BufferedImage[2];//2 animações, por isso 2 de parâmetro
        player[0] = sheet.getSprite(0,0,16,16);//coordenadas da sprite, isso deve ser verificado no paint.net
        player[1] = sheet.getSprite(16,0,16,16);//coordenadas da sprite, isso deve ser verificado no paint.net
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGTH,BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame(){
        frame = new JFrame("Desenvolvimento beta 0.1");
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

    public synchronized  void stop() {//certifica que quando ocorrer algum erro no jogo, todas as threads sejam finalizadas!
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.start();//aqui inicia o jogo
    }

    public void tick() {//Método da lógica do jogo
        frames++;
        if(frames > maxFrames)
            frames = 0;
        curAnimation++;
        if(curAnimation >= maxAnimation) {
            curAnimation = 0;
        }

    }

    public void render() {//Método dos graficos
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);//colocar entre 2 ou 3
            return;//como se fosse um break.
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(100, 10, 160));
        g.fillRect(0, 0,WIDTH, HEIGTH);

        //RENDERIZAÇÃO DO JOGO
        Graphics2D g2 = (Graphics2D) g;//isso transforma a variável g em Graphics 2D, o que permite tecnicas mais avançadas tipo animação e etc...
        g2.drawImage(player[curAnimation],90,90,null);//se a sprite for colocada por último, ela fica por cima de qualquer sprite ou cenário, cor de fundo.
        g2.dispose();//método de otimização
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

        stop();
    }
}

