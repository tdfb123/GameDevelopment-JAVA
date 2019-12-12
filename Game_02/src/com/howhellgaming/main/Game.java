package com.howhellgaming.main;

import com.howhellgaming.com.entities.*;
import com.howhellgaming.graficos.Spritesheet;
import com.howhellgaming.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {


    private static final long serialVersionUID = 1L;
    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    public static final int WIDTH = 320;//final porque é uma constate e não sofre alteração
    public static final int HEIGTH = 180;
    private final int SCALE = 2;

    private BufferedImage image;

    public static List<Entity> entities;
    public static Spritesheet spritesheet;

    public static World world;

    public static Player player;


    public Game(){
        addKeyListener(this);//this porque o Listener está nesta classe, se fosse outra classe teria que colocar no parâmetro.
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
        initFrame();
        //Inicializando objetos.

        image = new BufferedImage(WIDTH, HEIGTH,BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<Entity>();
        spritesheet = new Spritesheet("/spritesheet.png");//nome do arquivo de sprite
        player = new Player(0,0,16,16,spritesheet.getSprite(32,0,16,16));
        entities.add(player);
        world = new World("/map.png");

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
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    public void render() {//Método dos graficos
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);//colocar entre 2 ou 3
            return;//como se fosse um break.
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(40, 40, 40));
        g.fillRect(0, 0,WIDTH, HEIGTH);

        //RENDERIZAÇÃO DO JOGO
        //Graphics2D g2 = (Graphics2D) g;//isso transforma a variável g em Graphics 2D, o que permite tecnicas mais avançadas tipo animação e etc...
        world.render(g);
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render(g);
        }
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image,0,0,WIDTH*SCALE, HEIGTH*SCALE,null);
        bs.show();
    }

    public void run() {//looping para o jogo estar em constante running.
        long lasTime = System.nanoTime();//pega o tempo atual do nosso computador em nano segundos, isso da mais precisão nos fps de maneira profissional
        double amountOfTicks = 60.0;//local onde seta o FPS.
        double ns = 1000000000 / amountOfTicks;//calculo para saber o momento certo de atualizar a tela do jogo
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();//retorna o tempo do computador, usamos isso para contagem do fps, mas é opcional.
        requestFocus();//Método que foca a tela automaticamente
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

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            player.right = true;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            player.right = false;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            player.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            player.down = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

