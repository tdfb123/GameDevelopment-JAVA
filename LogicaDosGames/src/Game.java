public class Game implements Runnable {

    private boolean isRunning;
    private Thread thread;
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public synchronized void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void tick(){
        //atualizar o jogo.
        System.out.println("Tick");
    }

    public void render(){
        //renderizar o jogo.
        System.out.println("Render");
    }

    @Override
    public void run() {

        while (isRunning) {
            tick();
            render();
            try {
                thread.sleep(1000/60);//setar em 60fps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}