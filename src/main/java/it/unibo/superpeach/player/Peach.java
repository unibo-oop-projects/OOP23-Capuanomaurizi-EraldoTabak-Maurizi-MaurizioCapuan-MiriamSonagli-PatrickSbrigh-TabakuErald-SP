package it.unibo.superpeach.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.player.graphics.PlayerTexture;
import it.unibo.superpeach.powerups.PowerupsHandler;
import it.unibo.superpeach.powerups.PowerUp.PowerUpType;

public class Peach extends Player{
    private static final int SPEED_X = 4;
    private static final int JUMP_HEIGHT = 55;
    private static final int LOOK_RIGHT_NORM = 0;
    private static final int LOOK_LEFT_NORM = 1;
    private static final int JUMP_FALL_NORM = 2;
    private static final int LOOK_RIGHT_STAR = 3;
    private static final int LOOK_LEFT_STAR = 4;
    private static final int JUMP_FALL_STAR = 5;
    private int spriteNeeded = 0;
    private PlayerTexture texture;
    private BufferedImage[] sprite;

    public Peach(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler, EnemiesHandler enemiesHandler, PowerupsHandler powerUpsHandler){
        super(x, y, width, height, scale, blocksHandler, enemiesHandler, powerUpsHandler);
        this.texture = Game.getPlayerTexturer();
        this.sprite = texture.getPlayerImage();
    }

    public BufferedImage[] getSprites(){
        return this.sprite;
    }

    public PlayerTexture getPlayerTexture(){
        return this.texture;
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
            setMoveY(-JUMP_HEIGHT);
            setHasJumped(true);
            setConsecutiveJump(1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getSprites()[spriteNeeded], getX(), (int)getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setY(getY()/getScale()+getMoveY());
        setX(getX()/getScale()+getMoveX());
        fall();
        collision();
        deleteStar();
    }
}
