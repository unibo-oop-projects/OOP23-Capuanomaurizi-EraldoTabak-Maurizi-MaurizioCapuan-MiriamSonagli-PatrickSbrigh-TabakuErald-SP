package it.unibo.superpeach.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import it.unibo.superpeach.blocks.Block;
import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.blocks.MapFixedBlock;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.enemies.Enemy;

public abstract class Player {
    private static final int FALL_SPEED = 4;
    private static final int LIFE_START = 3;
    private static final int POINT_LUCKY_BRICK = 200;
    private static final int POINT_WIN = 2000;
    private static final int POINT_KILLED_ENEMY = 300;
    private static final int POINT_FLAG_POLE = 500;
    private static final int POINT_FLAG_TIP = 1000;
    private static final int CONSECUTIVE_JUMP = 3;
    private static final int MIN_X = 192;
    private static final int MAX_X = 3456;
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
    private boolean hasHealt;
    private int point;
    private int life;
    private boolean addedPointFlag;
    private boolean addedPointWin;
    private int respawnX;
    private int respawnY;
    private int consecutiveJumps;
    private boolean hasWon;
    private boolean hasLost;

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
        this.hasHealt = false;   
        this.point = 0;
        this.life = LIFE_START;
        this.addedPointFlag= false;
        this.addedPointWin = false;
        this.respawnX = x;
        this.respawnY = y;
        this.consecutiveJumps = 0;
        this.hasWon = false;
        this.hasLost = false;
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

    public int getPoint(){
        return this.point;
    }

    public int getLife(){
        return this.life;
    }

    public int getConsecutiveJump(){
        return this.consecutiveJumps;
    }

    public boolean hasJumped(){
        return this.jumped;
    }

    public void setX(int x){
        if(x >= MIN_X && x <= MAX_X){
            this.x = x*this.scale;
        }
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

    public void setConsecutiveJump(int add){
        if(this.consecutiveJumps+1 <= CONSECUTIVE_JUMP){
            this.consecutiveJumps++;
        }
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
        graph.draw(getBottomBound());
        graph.draw(getTopBound());
        graph.draw(getLeftBound());
        graph.draw(getRightBound());
        graph.draw(rectangle);

    }

    public Rectangle getTopBound(){
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, padding_bound*(padding_bound/getScale()));
    }

    public Rectangle getBottomBound(){
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-padding_bound, getWidth()/2, padding_bound);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(getX(), getY()+padding_bound, padding_bound, getHeight()-2*padding_bound);

    }

    public Rectangle getRightBound(){
        return new Rectangle(getX()+getWidth()-padding_bound, getY()+padding_bound, padding_bound ,getHeight()-2*padding_bound);
    }

    public void resetCosecutiveJump(){
        this.consecutiveJumps = 0;
    }

    public boolean canJump(){
        return this.consecutiveJumps < CONSECUTIVE_JUMP;
    }

    public void collision(){
        for(MapFixedBlock block : blocksHandler.getBlocks()){
            if(block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT 
            || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
            || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN) {
                if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().contains(getTopBound())){
                    setYCollisionTop(block);
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                }
            }
            else if(block.getType() == BlockType.LUCKY){
                if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    changePoint(POINT_LUCKY_BRICK);
                    block.popLuckyBlock();
                }
                else if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                }
            }
            else if(block.getType() == BlockType.POPPED_LUCKY){
                if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                }
                else if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                }
            }
            else if(block.getType() == BlockType.BRICK){
                if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    changePoint(POINT_LUCKY_BRICK);
                    blocksHandler.removeFixedBlock(block);
                }
                else if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                }
            }
            else if(block.getType() == BlockType.DEATH_BLOCK){
                if(block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound()) || block.getBoundingBox().intersects(getRightBound())){
                    System.out.println("MORTO");
                    dead();
                }
            }
            else if(block.getType() == BlockType.CASTLE_DOOR_BOT || block.getType() == BlockType.CASTLE_DOOR_TOP){
                if(block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound()) || block.getBoundingBox().intersects(getRightBound())){
                    if(!addedPointWin){
                        changePoint(POINT_WIN);
                        addedPointWin = true;
                    }
                    this.hasWon = true;
                }
            }
            else if(block.getType() == BlockType.FLAG_TIP){
                if(block.getBoundingBox().intersects(getTopBound()) || block.getBoundingBox().intersects(getLeftBound())){
                    if(!addedPointFlag){
                        changePoint(POINT_FLAG_TIP);
                        addedPointFlag = true;
                    }
                }
            }
            else if(block.getType() == BlockType.FLAG_POLE){
                if(block.getBoundingBox().intersects(getRightBound()) || block.getBoundingBox().contains(getRightBound())
                ||block.getBoundingBox().intersects(getLeftBound()) || block.getBoundingBox().contains(getLeftBound())
                || block.getBoundingBox().intersects(getTopBound()) || block.getBoundingBox().contains(getTopBound())
                || block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().contains(getBottomBound())){
                    if(!addedPointFlag){
                        changePoint(POINT_FLAG_POLE);
                        addedPointFlag = true;
                    }
                    //FIX
                }
            }
        }

        for(Enemy enemy : enemiesHandler.getEnemies()){
            if(isDeadForEnemy(enemy)){
                dead();
            }else if (killedEnemy(enemy)){
                changePoint(POINT_KILLED_ENEMY);
                enemiesHandler.removeEnemy(enemy);
            }
        }
    }

    private void changePoint(int point){
        this.point += point;
        System.out.println("I TUOI CAZZO DI PUNTI SONO: "+ this.point);
    }

    private void dead(){
        if(hasHealt){
            hasHealt = !hasHealt;
        }
        else{
            life--;
            if(life <1){
                this.hasLost = true;
            }
            else{
                setX(respawnX);
                setY(respawnY);
            }
        }
        System.out.println("LE TUE VITE SONO: "+ this.life);
    }

    private boolean isDeadForEnemy(Enemy enemy){
        return enemy.getBounds().intersects(getTopBound()) || enemy.getBounds().intersects(getLeftBound()) || enemy.getBounds().intersects(getRightBound());
    }

    private boolean killedEnemy(Enemy enemy){
        return enemy.getBounds().intersects(getBottomBound());
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

    public boolean hasWon(){
        return this.hasWon;
    }

    public boolean hadLost(){
        return this.hasLost;
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void jump();
    public abstract void render(Graphics g);
    public abstract void tick();
}
