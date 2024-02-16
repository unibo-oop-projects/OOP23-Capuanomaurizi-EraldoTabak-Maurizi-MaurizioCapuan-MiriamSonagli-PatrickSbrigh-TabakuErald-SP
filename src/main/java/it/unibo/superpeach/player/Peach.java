package it.unibo.superpeach.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.graphics.Texturer;
import it.unibo.superpeach.powerups.PowerupsHandler;
import it.unibo.superpeach.powerups.PowerUp.PowerUpType;

public class Peach extends Player{
    private static final int SPEED_X = 4;
    private static final int JUMP_HEIGHT_NORM = 45;
    private static final int JUMP_HEIGHT_BIG = 55;
    private static final int LOOK_RIGHT_NORM = 0;
    private static final int LOOK_LEFT_NORM = 1;
    private static final int JUMP_FALL_NORM = 2;
    private static final int LOOK_RIGHT_STAR = 3;
    private static final int LOOK_LEFT_STAR = 4;
    private static final int JUMP_FALL_STAR = 5;
    private int spriteNeeded = 0;
    private Texturer texturer;
    private BufferedImage[] sprite;

    public Peach(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler, EnemiesHandler enemiesHandler, PowerupsHandler powerUpsHandler, Scoreboard scoreboard){
        super(x, y, width, height, scale, blocksHandler, enemiesHandler, powerUpsHandler, scoreboard);
        this.texturer = Game.getTexturer();
        this.sprite = texturer.getPeach();
    }

    public BufferedImage[] getSprites(){
        return this.sprite;
    }

    public Texturer getPlayerTexture(){
        return this.texturer;
    }

    @Override
    public void moveLeft() {
        if(whatPowerUp() == null){
            spriteNeeded = LOOK_LEFT_NORM;
        }
        else if(whatPowerUp() == PowerUpType.STAR){
            spriteNeeded = LOOK_LEFT_STAR;
        }
        setMoveX(-SPEED_X);
    }

    @Override
    public void moveRight() {
        if(whatPowerUp() == null){
            spriteNeeded = LOOK_RIGHT_NORM;
        }
        else if(whatPowerUp() == PowerUpType.STAR){
            spriteNeeded = LOOK_RIGHT_STAR;
        }
        setMoveX(SPEED_X);
    }

    @Override
    public void jump() {
        if(!hasJumped() && canJump()){
            if(whatPowerUp() == null){
                spriteNeeded = JUMP_FALL_NORM;
            }
            else if(whatPowerUp() == PowerUpType.STAR){
                spriteNeeded = JUMP_FALL_STAR;
            }

            if(whatPowerUp() == PowerUpType.RED_MUSHROOM){
                setMoveY(-JUMP_HEIGHT_BIG);
            }
            else{
                setMoveY(-JUMP_HEIGHT_NORM);
            }
            setHasJumped(true);
            setConsecutiveJump(1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getSprites()[spriteNeeded], getX(), getY(), getWidth(), getHeight(), null);
        //showRectangle(g);
    }

    @Override
    public void tick() {
        //updateRectangle();
        setY(getY()/getScale()+getMoveY());
        setX(getX()/getScale()+getMoveX());
        fall();
        collision();
        deleteStarLittle();
        deleteStarBig();
    }
}
