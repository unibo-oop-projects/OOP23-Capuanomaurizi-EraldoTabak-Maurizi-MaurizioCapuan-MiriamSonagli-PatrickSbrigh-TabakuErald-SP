package it.unibo.superpeach.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.enemies.EnemiesHandler;
import it.unibo.superpeach.gameObjects.player.Peach;
import it.unibo.superpeach.gameObjects.player.PlayerHandler;
import it.unibo.superpeach.gameObjects.powerups.PowerupsHandler;
import it.unibo.superpeach.graphics.PeachMenu;
import it.unibo.superpeach.graphics.Texturer;
import it.unibo.superpeach.keyboard.Keyboard;
import it.unibo.superpeach.level.Camera;
import it.unibo.superpeach.level.LevelHandler;

public class Game extends Canvas implements Runnable {

    private static int GAME_SCALE = 2;

    // GAME CONSTANTS
    private static final int MILLS_PER_SECOND = 1000;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final int GRAPHICS_BUFFERS = 3;
    private static final double TICKS_PER_SECOND = 60.0;
    private static final String GAME_NAME = "Super Peach";
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 360;
    private static final int PLAYER_DEFAULT_X = 240;
    private static final int PLAYER_DEFAULT_Y = WINDOW_HEIGHT/2;
    private static final int PLAYER_DEFAULT_WID_HEIG = 16;    

    // GAME VARIABLES
    private boolean running;
    private boolean gameOver = false;
    private int gameOverBuffers = 0;

    // GAME COMPONENTS
    private Thread mainGameLoop;
    private BlocksHandler blocksHandler;
    private static Texturer texturer;
    private LevelHandler levelHandler;
    private Camera camera;
    private static PeachMenu window;
    private PlayerHandler playerHandler;
    private EnemiesHandler enemiesHandler;
    private PowerupsHandler powerupsHandler;
    private Scoreboard scoreboard;

    public static void main(String[] args) {
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_SCALE, new Game());
    }

    public void init() {
        texturer = new Texturer();
        blocksHandler = new BlocksHandler();
        enemiesHandler = new EnemiesHandler();
        playerHandler = new PlayerHandler();
        powerupsHandler = new PowerupsHandler();
        scoreboard = new Scoreboard(3, 7, GAME_SCALE);
        playerHandler.setPlayer(new Peach(PLAYER_DEFAULT_X, PLAYER_DEFAULT_Y, PLAYER_DEFAULT_WID_HEIG, PLAYER_DEFAULT_WID_HEIG, GAME_SCALE, blocksHandler, enemiesHandler, powerupsHandler, scoreboard));// TOFIX
        levelHandler = new LevelHandler(blocksHandler, GAME_SCALE, enemiesHandler);
        levelHandler.drawLevel();
        camera = new Camera(WINDOW_WIDTH, GAME_SCALE);
        this.addKeyListener(new Keyboard(playerHandler, this));
        start();
    }

    private void start() {
        mainGameLoop = new Thread(this);
        mainGameLoop.start();
        running = true;
    }

    public void changeScale(int newScale) {
        GAME_SCALE = newScale;
        window.closeWindow();
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_SCALE, new Game());
    }

    public void restart() {
        stop();
        window.closeWindow();
        window.stopBackgroundMusic();
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, GAME_SCALE, new Game());
    }

    private void stop() {
        running = false;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticksAmount = TICKS_PER_SECOND;
        double ns = NANOS_PER_SECOND / ticksAmount;
        double delta = 0;
        long timer = System.currentTimeMillis();

        // GAMELOOP
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            if (System.currentTimeMillis() - timer >= MILLS_PER_SECOND) {
                timer += MILLS_PER_SECOND;
            }
        }
    }

    private void tick() {
        playerHandler.tick();
        enemiesHandler.tickEnemies();
        powerupsHandler.tickPowerups();
        camera.tick(playerHandler.getPlayer());
        if(playerHandler.getPlayer().hasLost() || playerHandler.getPlayer().hasWon()){
            gameOver = true;
        }
    }

    private void render() {
        try {
            BufferStrategy buffStrat = this.getBufferStrategy();
            if (buffStrat == null) {
                this.createBufferStrategy(3);
                return;
            }
            Graphics g = buffStrat.getDrawGraphics();
            if(!gameOver){
                g.setColor(Color.PINK);
                g.fillRect(0, 0, WINDOW_WIDTH * GAME_SCALE, WINDOW_HEIGHT * GAME_SCALE);
                g.translate(camera.getCameraX(), 0);
                blocksHandler.renderBlocks(g);
                enemiesHandler.renderEnemies(g);
                playerHandler.render(g);
                powerupsHandler.renderPowerups(g);
                scoreboard.render(g, playerHandler.getPlayer().getX());
            }
            else{
                gameOverBuffers++;
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WINDOW_WIDTH * GAME_SCALE, WINDOW_HEIGHT * GAME_SCALE);
                g.translate(0, 0);
                g.setColor(Color.PINK);
                g.setFont(new Font("Monospaced", Font.BOLD, 25*GAME_SCALE));
                if(playerHandler.getPlayer().hasWon()){
                    g.drawString("You WON!", WINDOW_WIDTH*GAME_SCALE/3, WINDOW_HEIGHT*GAME_SCALE/2 - 25*GAME_SCALE);
                    g.drawString("SCORE: " + playerHandler.getPlayer().getScore(), WINDOW_WIDTH*GAME_SCALE/3, WINDOW_HEIGHT*GAME_SCALE/2 + 10*GAME_SCALE);
                } else {
                    g.drawString("Game Over", WINDOW_WIDTH*GAME_SCALE/3, WINDOW_HEIGHT*GAME_SCALE/2 - 25*GAME_SCALE);
                    g.drawString("You LOSE", WINDOW_WIDTH*GAME_SCALE/3 + 7*GAME_SCALE, WINDOW_HEIGHT*GAME_SCALE/2 + 10*GAME_SCALE);
                }
                if(gameOverBuffers == GRAPHICS_BUFFERS){
                    stop();
                }
            }
            g.dispose();
            buffStrat.show();
        } catch (IllegalStateException e) {}
    }

    public static Texturer getTexturer() {
        return texturer;
    }

}
