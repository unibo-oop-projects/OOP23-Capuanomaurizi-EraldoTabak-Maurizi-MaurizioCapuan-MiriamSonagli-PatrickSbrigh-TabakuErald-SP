package it.unibo.superpeach.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.player.graphics.PlayerTexture;

public class Peach extends Player{
    private static final int SPEED_X = 10;
    private static final int JUMP_HEIGHT = 50;
    private static final int LOOK_RIGHT = 0;
    private static final int LOOK_LEFT = 1;
    private static final int WALK_LEFT = 2;
    private static final int WALK_RIGHT=3;
    private static final int JUMP_FALL = 4;
    private int spriteNeeded = 0;
    private PlayerTexture texture;
    private BufferedImage[] sprite;

    public Peach(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler){
        super(x, y, width, height, scale, blocksHandler);
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
        spriteNeeded = LOOK_LEFT;
        setX((getX()/getScale())-SPEED_X);
        if(hasJumped()){
            fall();
        }
    }

    @Override
    public void moveRight() {
        spriteNeeded = LOOK_RIGHT;
        setX((getX()/getScale())+SPEED_X);
        if(hasJumped()){
            fall();
        }
    }

    @Override
    public void jump() {
        spriteNeeded = JUMP_FALL;
        setY((getY()/getScale())-JUMP_HEIGHT);
        setHasJumped(true);
    }
    
    @Override
    public void fall() {
        spriteNeeded = JUMP_FALL;
        setY((getY()/getScale())+JUMP_HEIGHT);
        setHasJumped(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getSprites()[spriteNeeded], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        collision();
    }
}
