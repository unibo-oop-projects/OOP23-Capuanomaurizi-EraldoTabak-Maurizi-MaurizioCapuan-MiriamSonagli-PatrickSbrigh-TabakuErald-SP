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
import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.powerups.PowerUp;
import it.unibo.superpeach.powerups.PowerupsHandler;
import it.unibo.superpeach.powerups.PowerUp.PowerUpType;

public abstract class Player {
    private static final int FALL_SPEED = 4;
    private static final int LIFE_START = 3;
    private static final int POINT_LUCKY_BRICK = 200;
    private static final int POINT_WIN = 2000;
    private static final int POINT_KILLED_ENEMY = 300;
    private static final int POINT_FLAG_POLE = 500;
    private static final int POINT_FLAG_TIP = 1000;
    private static final int CONSECUTIVE_JUMP = 2;
    private static final int MIN_X = 240;
    private static final int MAX_X = 3504;
    private static final int TICK_FOR_STAR = 700;
    private static final int PADDING = 5;
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private Rectangle rectangle;
    private boolean jumped;
    private BlocksHandler blocksHandler;
    private EnemiesHandler enemiesHandler;
    private PowerupsHandler powerupsHandler;
    private Scoreboard scoreboard;
    private int moveX;
    private int moveY;
    private int padding_bound;
    private int point;
    private int life;
    private boolean addedPointFlag;
    private boolean addedPointWin;
    private int respawnX;
    private int respawnY;
    private int consecutiveJumps;
    private boolean hasWon;
    private boolean hasLost;
    private PowerUpType typePowerUp;
    private int numTickStar;
    private int coins;

    public Player(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler, EnemiesHandler enemiesHandler, PowerupsHandler powersUpHandler, Scoreboard scoreboard){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.jumped = false;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        this.blocksHandler = blocksHandler;
        this.moveX = 0;
        this.moveY = 0;
        this.padding_bound = PADDING*getScale();
        this.enemiesHandler = enemiesHandler;
        this.scoreboard = scoreboard;
        this.point = 0;
        this.life = LIFE_START;
        this.addedPointFlag = false;
        this.addedPointWin = false;
        this.respawnX = x;
        this.respawnY = y;
        this.consecutiveJumps = 0;
        this.hasWon = false;
        this.hasLost = false;
        this.typePowerUp = null;
        this.numTickStar = 0;
        this.powerupsHandler = powersUpHandler;
        this.coins = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public int getScale() {
        return this.scale;
    }

    public int getPoint() {
        return this.point;
    }

    public int getLife() {
        return this.life;
    }

    public int getConsecutiveJump() {
        return this.consecutiveJumps;
    }

    public int getCoins(){
        return this.coins;
    }

    public boolean hasJumped(){
        return this.jumped;
    }

    public void setX(int x) {
        if (x >= MIN_X && x <= MAX_X) {
            this.x = x * this.scale;
        }
    }

    public void setY(int y) {
        this.y = y * this.scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setConsecutiveJump(int add) {
        if (this.consecutiveJumps + 1 <= CONSECUTIVE_JUMP) {
            this.consecutiveJumps++;
        }
    }

    public void setHasJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void updateRectangle() {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void showRectangle(Graphics g) {
        Graphics2D graph = (Graphics2D) g;
        graph.setColor(Color.BLACK);
        graph.draw(getBottomBound());
        graph.draw(getTopBound());
        //graph.draw(getLeftBound());
        //graph.draw(getRightBound());
        graph.draw(rectangle);

    }

    public Rectangle getTopBound(){
        if(typePowerUp == PowerUpType.RED_MUSHROOM){
            return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, (padding_bound)*((padding_bound/getScale())));
        }
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, (padding_bound/2)*((padding_bound)/getScale()));
    }

    public Rectangle getBottomBound(){
        if(typePowerUp == PowerUpType.RED_MUSHROOM){
            return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-padding_bound, getWidth()/2, padding_bound);
        }
        else{
            return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-(padding_bound/3), getWidth()/2, padding_bound/3);
        }
    }

    public Rectangle getLeftBound(){
        return new Rectangle(getX(), getY()+(padding_bound), (padding_bound), getHeight()-2*(padding_bound));

    }

    public Rectangle getRightBound(){
        return new Rectangle(getX()+getWidth()-(padding_bound), getY()+(padding_bound), (padding_bound) ,getHeight()-2*(padding_bound));
    }

    public void resetCosecutiveJump() {
        this.consecutiveJumps = 0;
    }

    public boolean canJump() {
        return this.consecutiveJumps < CONSECUTIVE_JUMP;
    }

    public void collision() {
        for (MapFixedBlock block : blocksHandler.getBlocks()) {
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN) {
                if (block.getBoundingBox().contains(getBottomBound())) {
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                } else if (block.getBoundingBox().contains(getTopBound())) {
                    setYCollisionTop(block);
                } else if (block.getBoundingBox().contains(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().contains(getRightBound())) {
                    setXCollisionRight(block);
                } else if (block.getBoundingBox().intersects(getBottomBound())) {
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                } else if (block.getBoundingBox().intersects(getTopBound())) {
                    setYCollisionTop(block);
                } else if (block.getBoundingBox().intersects(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().intersects(getRightBound())) {
                    setXCollisionRight(block);
                }
            }
            else if(block.getType() == BlockType.LUCKY){
                if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    changePoint(POINT_LUCKY_BRICK);
                    block.popLuckyBlock(powerupsHandler, blocksHandler);
                }
                else if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                /*if(block.getBoundingBox().contains(getBottomBound()) || block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    changePoint(POINT_LUCKY_BRICK);
                    block.popLuckyBlock(powerupsHandler, blocksHandler);
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        block.popLuckyBlock(powerupsHandler, blocksHandler);
                        changePoint(POINT_LUCKY_BRICK);
                    }
                }*/
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
                /*if(block.getBoundingBox().contains(getBottomBound()) || (block.getBoundingBox().intersects(getBottomBound()))){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                }
                else if(block.getBoundingBox().contains(getTopBound()) || (block.getBoundingBox().intersects(getTopBound()))){
                    setYCollisionTop(block);
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().contains(getRightBound())) {
                    setXCollisionRight(block);
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().intersects(getRightBound())) {
                    setXCollisionRight(block);
                }*/
            }
            else if(block.getType() == BlockType.BRICK){
                if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    if(typePowerUp != null){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                /*if(block.getBoundingBox().contains(getBottomBound()) || block.getBoundingBox().intersects(getBottomBound())){
                    setYCollisionBottom(block);
                    resetCosecutiveJump();
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
                    setYCollisionTop(block);
                    if(typePowerUp != null){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().contains(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().intersects(getLeftBound())){
                    setXCollisionLeft(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }
                else if(block.getBoundingBox().intersects(getRightBound())){
                    setXCollisionRight(block);
                    if(typePowerUp == PowerUpType.STAR){
                        changePoint(POINT_LUCKY_BRICK);
                        blocksHandler.removeFixedBlock(block);
                    }
                }*/
            }
            else if(block.getType() == BlockType.DEATH_BLOCK){
                if(block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound()) || block.getBoundingBox().intersects(getRightBound())){
                    dead();
                }
            } else if (block.getType() == BlockType.CASTLE_DOOR_BOT || block.getType() == BlockType.CASTLE_DOOR_TOP) {
                if (block.getBoundingBox().intersects(getBottomBound())
                        || block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().intersects(getLeftBound())
                        || block.getBoundingBox().intersects(getRightBound())) {
                    if (!addedPointWin) {
                        changePoint(POINT_WIN);
                        addedPointWin = true;
                    }
                    this.hasWon = true;
                }
            } else if (block.getType() == BlockType.FLAG_TIP) {
                if (block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().intersects(getLeftBound())) {
                    if (!addedPointFlag) {
                        changePoint(POINT_FLAG_TIP);
                        addedPointFlag = true;
                    }
                }
            } else if (block.getType() == BlockType.FLAG_POLE) {
                if (block.getBoundingBox().intersects(getRightBound())
                        || block.getBoundingBox().contains(getRightBound())
                        || block.getBoundingBox().intersects(getLeftBound())
                        || block.getBoundingBox().contains(getLeftBound())
                        || block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().contains(getTopBound())
                        || block.getBoundingBox().intersects(getBottomBound())
                        || block.getBoundingBox().contains(getBottomBound())) {
                    if (!addedPointFlag) {
                        changePoint(POINT_FLAG_POLE);
                        addedPointFlag = true;
                    }
                }
            }
        }

        for(Enemy enemy : enemiesHandler.getEnemies()){
            if(isDeadForEnemy(enemy) && typePowerUp != PowerUpType.STAR){
                dead();
            }else if (killedEnemy(enemy) || (isDeadForEnemy(enemy) && typePowerUp == PowerUpType.STAR)){
                changePoint(POINT_KILLED_ENEMY);
                enemiesHandler.removeEnemy(enemy);
            }
        }

        for(PowerUp power : powerupsHandler.getPowerups()){
            if(touchPowerUp(power)){
                switch (power.getPowerUpType()) {
                    case COIN:
                        scoreboard.collectCoin();
                        coins++;
                        break;
                    case RED_MUSHROOM:
                        becomeBig();
                        break;
                    case STAR:
                        typePowerUp = PowerUpType.STAR;
                        break;
                    case LIFE_MUSHROOM:
                        life++;
                        break;
                }
                power.die();
            }
        }
    }

    private void changePoint(int point) {
        this.point += point;
    }

    private boolean touchPowerUp(PowerUp power){
        return power.getBounds().intersects(getBottomBound()) || power.getBounds().intersects(getTopBound()) 
            || power.getBounds().intersects(getLeftBound()) || power.getBounds().intersects(getRightBound());
    }

    private void becomeBig(){
        if(typePowerUp != PowerUpType.RED_MUSHROOM){
            setHeight(height*2);
        }
        typePowerUp = PowerUpType.RED_MUSHROOM;
    }

    private void dead(){
        if(typePowerUp == PowerUpType.RED_MUSHROOM){
           typePowerUp = null;
           this.height /= 2;
        }
        else{
            life--;
            scoreboard.removeHeart();
            if (life < 0) {
                this.hasLost = true;
            } else {
                setX(respawnX);
                setY(respawnY);
            }
        }
    }

    public PowerUpType whatPowerUp(){
        return this.typePowerUp;
    }

    private boolean isDeadForEnemy(Enemy enemy) {
        return enemy.getBounds().intersects(getTopBound()) || enemy.getBounds().intersects(getLeftBound())
                || enemy.getBounds().intersects(getRightBound());
    }

    private boolean killedEnemy(Enemy enemy) {
        return enemy.getBounds().intersects(getBottomBound());
    }

    private void setYCollisionTop(Block block) {
        setY(block.getY() / block.getScale() + block.getHeight() / block.getScale());
    }

    private void setYCollisionBottom(Block block) {
        setY(block.getY() / block.getScale() - getHeight() / getScale());
    }

    private void setXCollisionLeft(Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
    }

    private void setXCollisionRight(Block block) {
        setX(block.getX() / block.getScale() - getWidth() / getScale());
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return this.moveY;
    }

    public int getMoveX() {
        return this.moveX;
    }

    public void fall() {
        moveY = FALL_SPEED;
    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public boolean hasLost() {
        return this.hasLost;
    }

    public void deleteStarLittle(){
        if(typePowerUp == PowerUpType.STAR){
            if(numTickStar >= TICK_FOR_STAR){
                typePowerUp = null;
                numTickStar = 0;

            }
            else{
                numTickStar++;
            }
        }
    }

    public void deleteStarBig(){
        if(typePowerUp == PowerUpType.STAR){
            if(numTickStar >= TICK_FOR_STAR){
                typePowerUp = PowerUpType.RED_MUSHROOM;
                numTickStar = 0;

            }
            else{
                numTickStar++;
            }
        }
    }

    private boolean isFalling(){
        return moveY>0;
    }

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void jump();

    public abstract void render(Graphics g);

    public abstract void tick();
}
