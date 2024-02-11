package it.unibo.superpeach.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.blocks.graphics.Texturer;
import it.unibo.superpeach.graphics.PeachMenu;
import it.unibo.superpeach.level.Camera;
import it.unibo.superpeach.level.LevelHandler;

public class Game extends Canvas implements Runnable{

    //GAME CONSTANTS
    private static final int MILLS_PER_SECOND = 1000;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final double TICKS_PER_SECOND = 60.0;
    private static final String GAME_NAME = "Super Peach";
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 360;

    //GAME VARIABLES
    private boolean running;
    private static int GAME_SCALE = 2;

    //GAME COMPONENTS
    private Thread mainGameLoop;
    private BlocksHandler blocksHandler;
    private static Texturer blocksTexturer;
    private LevelHandler levelHandler;
    private Camera camera;
    private static PeachMenu window;

    public static void main(String[] args) {
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_SCALE, new Game());
    }

    public void init(){
        blocksTexturer = new Texturer();
        blocksHandler = new BlocksHandler();
        levelHandler = new LevelHandler(blocksHandler, GAME_SCALE);
        levelHandler.drawLevel();
        camera = new Camera(WINDOW_WIDTH*GAME_SCALE, WINDOW_HEIGHT*GAME_SCALE);
        start();
    }

    private synchronized void start(){
        mainGameLoop = new Thread(this);
        mainGameLoop.start();
        running = true;
    }

    public synchronized void changeScale(int newScale){
        GAME_SCALE = newScale;
        window.closeWindow();
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_SCALE, new Game());
    }

    private synchronized void stop(){
        try {
            mainGameLoop.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticksAmount = TICKS_PER_SECOND;
        double ns = NANOS_PER_SECOND / ticksAmount;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        //GAMELOOP
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if(System.currentTimeMillis() - timer >= MILLS_PER_SECOND){
                timer += MILLS_PER_SECOND;
                System.out.println("FPS: "+frames+" TPS: "+updates);
                frames=0;
                updates=0;
            }
        }

        stop();
    }

    private void tick(){
        blocksHandler.tickBlocks();
        //player should tick camera passing his reference
        camera.tick();
    }

    private void render(){
        BufferStrategy buffStrat = this.getBufferStrategy();
        if(buffStrat == null){
            this.createBufferStrategy(3);
            return;
        } 
        Graphics g = buffStrat.getDrawGraphics();

        g.setColor(Color.PINK);
        g.fillRect(0, 0, WINDOW_WIDTH*GAME_SCALE, WINDOW_HEIGHT*GAME_SCALE);
        g.translate(camera.getCameraX(), camera.getCameraY());

        blocksHandler.renderBlocks(g);

        //clean for next frame
        g.dispose();
        buffStrat.show();
    }

    public static Texturer getBlocksTexturer() {
        return blocksTexturer;
    }

    public void setGameScale(int s){
        GAME_SCALE = s;
    }

}
