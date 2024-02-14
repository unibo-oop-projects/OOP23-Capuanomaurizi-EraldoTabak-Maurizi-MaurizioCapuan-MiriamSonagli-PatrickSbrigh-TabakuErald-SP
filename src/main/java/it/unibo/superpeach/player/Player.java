package it.unibo.superpeach.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import it.unibo.superpeach.blocks.Block;
import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.enemies.Enemy;

public abstract class Player {
    private static final int FALL_SPEED = 3;
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private Rectangle rectangle;
    private boolean jumped;
    private BlocksHandler blocksHandler;
    private EnemiesHandler enemiesHandler;
    private int moveX;
    private int moveY;
    private int padding_bound = 5;

    public Player(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler, EnemiesHandler enemiesHandler){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.jumped = false;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        this.blocksHandler = blocksHandler;
        moveX = 0;
        moveY = 0;
        padding_bound *=scale;
        this.enemiesHandler = enemiesHandler;      
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }

    public int getScale(){
        return this.scale;
    }

    public boolean hasJumped(){
        return this.jumped;
    }

    public void setX(int x){
        this.x = x*this.scale;
    }

    public void setY(int y){
        this.y = y*this.scale;
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHasJumped(boolean jumped){
        this.jumped = jumped;
    }

    public void updateRectangle(){
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void showRectangle(Graphics g){
        Graphics2D graph = (Graphics2D)g;
        graph.setColor(Color.BLACK);
        //graph.draw(getBottomBound());
        //graph.draw(getTopBound());
        graph.draw(getLeftBound());
        graph.draw(getRightBound());
        graph.draw(rectangle);

    }

    public Rectangle getTopBound(){
        //return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, getHeight()/2);
        //return new Rectangle(getX(), getY(), getWidth(), getHeight()/2);
        //return new Rectangle(getX(), getY(), getWidth(), padding_bound);
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, padding_bound);
    }

    public Rectangle getBottomBound(){
        //return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+(getHeight()/2), getWidth()/2, getHeight()/2);
        //return new Rectangle(getX(), getY()+(getHeight()/2), getWidth(), getHeight()/2);
        //return new Rectangle(getX(), getY()+getHeight()-padding_bound, getWidth(), padding_bound);
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-padding_bound, getWidth()/2, padding_bound);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(getX(), getY()+padding_bound, padding_bound, getHeight()-2*padding_bound);
        //return new Rectangle(getX(), getY(), getWidth()/2, getHeight());
        //return new Rectangle(getX(), getY(), padding_bound, getHeight());
    }

    public Rectangle getRightBound(){
        return new Rectangle(getX()+getWidth()-padding_bound, getY()+padding_bound, padding_bound ,getHeight()-2*padding_bound);
        //return new Rectangle(getX()+getWidth()/2, getY(), getWidth()/2 ,getHeight());
        //return new Rectangle(getX()+getWidth()-padding_bound, getY(), padding_bound, getHeight());
    }

    public void collision(){
        for(Block block : blocksHandler.getBlocks()){
            if(block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT 
            || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
            || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN
            || block.getType() == BlockType.POPPED_LUCKY){
                if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    moveY=0;
                }
                else if(block.getBoundingBox().contains(getTopBound())){
                    setYCollisionTop(block);
                    moveY = FALL_SPEED;
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    moveY=0;
                }
                else if(block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    moveY = FALL_SPEED;
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    moveX=0;
                }
            }
            else if(block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK){
                if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    moveY=0;
                }
                else if(block.getBoundingBox().contains(getTopBound())){
                    setYCollisionTop(block);
                    moveY = FALL_SPEED;
                    //add method
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    moveY=0;
                }
                else if(block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    moveY = FALL_SPEED;
                    //addmethod
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    moveX=0;
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    moveX=0;
                }
            }
            else if(block.getType() == BlockType.DEATH_BLOCK){
                if(block.getBoundingBox().intersects(getBottomBound())){
                    System.out.println("MORTO");
                    //MUORI o cava power up o vita
                }
                else if(block.getBoundingBox().intersects(getTopBound())){
                    System.out.println("MORTO");
                    //MUORI
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    System.out.println("MORTO");
                    //MUORI
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    System.out.println("MORTO");
                    //MUORI
                }
            }
            else if(block.getType() == BlockType.CASTLE_DOOR_BOT || block.getType() == BlockType.CASTLE_DOOR_TOP){
                if(block.getBoundingBox().intersects(getBottomBound())){
                    System.out.println("VINTO");
                    //vinci aggiungi punti
                }
                else if(block.getBoundingBox().intersects(getTopBound())){
                    System.out.println("VINTO");
                    //vinci aggiungi punti
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    System.out.println("VINTO");
                    //vinci aggiungi punti
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    System.out.println("VINTO");
                    //vinci aggiungi punti
                }
            }
            else if(block.getType() == BlockType.FLAG_TIP){
                if(block.getBoundingBox().intersects(getTopBound())){
                    System.out.println("1000 Punti");
                    //vinci aggiungi punti
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    System.out.println("1000 Punti");
                    //vinci aggiungi punti
                }
            }
            else if(block.getType() == BlockType.FLAG_RIGHT || block.getType() == BlockType.FLAG_LEFT || block.getType() == BlockType.FLAG_POLE){
                if(block.getBoundingBox().intersects(getRightBound())){
                    System.out.println("500 Punti");
                    //vinci aggiungi punti
                    //FIX
                }
            }
        }

        for(Enemy enemy : enemiesHandler.getEnemies()){
            
        }
    }

    private void setYCollisionTop(Block block){
        setY(block.getY()/block.getScale()+block.getHeight()/block.getScale());
    }

    private void setYCollisionBottom(Block block){
        setY(block.getY()/block.getScale()-getHeight()/getScale());
    }

    private void setXCollisionLeft(Block block){
        setX((block.getX()+block.getWidth())/getScale());
    }

    private void setXCollisionRight(Block block){
        setX(block.getX()/block.getScale()-getWidth()/getScale());
    }

    public void setMoveY(int moveY){
        this.moveY = moveY; 
    }
    
    public void setMoveX(int moveX){
        this.moveX = moveX; 
    }
    
    public int getMoveY(){
        return this.moveY; 
    }
    
    public int getMoveX(){
        return this.moveX;
    }

    public void fall() {
        moveY = FALL_SPEED;
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void jump();
    public abstract void render(Graphics g);
    public abstract void tick();
}
