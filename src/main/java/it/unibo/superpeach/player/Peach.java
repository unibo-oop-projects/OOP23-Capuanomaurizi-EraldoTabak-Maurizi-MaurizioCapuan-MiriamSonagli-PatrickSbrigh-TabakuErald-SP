package it.unibo.superpeach.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.player.graphics.PlayerTexture;

public class Peach extends Player{
    private static final int SPEED_X = 10;
    private static final int JUMP = 50;
    private PlayerTexture texture;
    private BufferedImage[] sprite;

    public Peach(int x, int y, int width, int height, int scale){
        super(x, y, width, height, scale);
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
        setX((getX()/getScale())-SPEED_X);
    }

    @Override
    public void moveRight() {
        setX((getX()/getScale())+SPEED_X);
    }

    @Override
    public void jump() {
        setY((getY()/getScale())-JUMP);
        setHasJumped(true);
    }
    
    @Override
    public void fall() {
        setY((getY()/getScale())+JUMP);
        setHasJumped(false);
    }

    @Override
    public void render(Graphics g) {
        showRectangle(g);

        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateRectangle();
    }
}
