package it.unibo.superpeach.gameObjects.player;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.MapFixedBlock;
import it.unibo.superpeach.gameObjects.blocks.Block.BlockType;
import it.unibo.superpeach.gameObjects.enemies.EnemiesHandler;
import it.unibo.superpeach.gameObjects.enemies.Enemy;
import it.unibo.superpeach.gameObjects.powerups.PowerUp;
import it.unibo.superpeach.gameObjects.powerups.PowerupsHandler;
import it.unibo.superpeach.gameObjects.powerups.PowerUp.PowerUpType;

public abstract class Player {
    private static final int FALL_SPEED = 3;
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
    private PowerUpType currentPowerUp;
    private PowerUpType lastPowerUp;
    private int numTickStar;

    public Player(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler, EnemiesHandler enemiesHandler, PowerupsHandler powersUpHandler, Scoreboard scoreboard){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.jumped = false;
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
        this.currentPowerUp = null;
        this.numTickStar = 0;
        this.powerupsHandler = powersUpHandler;
        this.lastPowerUp = null;
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

    public int getScale() {
        return this.scale;
    }

    public int getScore() {
        return this.point;
    }
    
    public int getMoveY() {
        return this.moveY;
    }

    public int getMoveX() {
        return this.moveX;
    }

    public PowerUpType getPowerUp(){
        return this.currentPowerUp;
    }

    public Rectangle getTopBound(){
        if(currentPowerUp == PowerUpType.RED_MUSHROOM || (currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)){
            return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, (padding_bound)*((padding_bound/getScale())));
        }
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY(), getWidth()/2, (padding_bound/getScale())*(padding_bound/2));
    }

    public Rectangle getBottomBound(){
        if(currentPowerUp == PowerUpType.RED_MUSHROOM || (currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)){
            return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-padding_bound, getWidth()/2, padding_bound);
        }
        return new Rectangle(getX()+getWidth()/2-getWidth()/4, getY()+getHeight()-(padding_bound/2), getWidth()/2, padding_bound/2);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(getX(), getY()+(padding_bound), (padding_bound), getHeight()-2*(padding_bound));

    }

    public Rectangle getRightBound(){
        return new Rectangle(getX()+getWidth()-(padding_bound), getY()+(padding_bound), (padding_bound) ,getHeight()-2*(padding_bound));
    }
    
    public boolean hasJumped(){
        return this.jumped;
    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public boolean hasLost() {
        return this.hasLost;
    }

    public void setX(int x) {
        if (x >= MIN_X && x <= MAX_X) {
            this.x = x * this.scale;
        }
    }

    public void setY(int y) {
        this.y = y * this.scale;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public void setHasJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void incrementConsecutiveJump() {
        if (this.consecutiveJumps + 1 <= CONSECUTIVE_JUMP) {
            this.consecutiveJumps++;
        }
    }

    public boolean canJump() {
        return this.consecutiveJumps < CONSECUTIVE_JUMP;
    }

    public void fall() {
        moveY = FALL_SPEED;
    }

    public void starTimeOver(){
        if(currentPowerUp == PowerUpType.STAR){
            if(numTickStar >= TICK_FOR_STAR){
                currentPowerUp = lastPowerUp;
                lastPowerUp = PowerUpType.STAR;
                numTickStar = 0;
            }
            else{
                numTickStar++;
            }
        }
    }

    public void collision() {
        collisionsWithBlocks();
        collisionsWithEnemies();
        collisionsWithPowersUp();
    }

    private void collisionsWithBlocks(){
        for (MapFixedBlock block : blocksHandler.getBlocks()) {
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN) {
                collisionsWithStaticBlocks(block);
            }
            else if(block.getType() == BlockType.LUCKY){
                collisionsWithLucky(block);
            }
            else if(block.getType() == BlockType.POPPED_LUCKY){
                collisionsWithPopped(block);
            }
            else if(block.getType() == BlockType.BRICK){
                collisionsWithBrick(block);
            }
            else if(block.getType() == BlockType.DEATH_BLOCK){
                collisionsWithDeathBlock(block);
            } else if (block.getType() == BlockType.CASTLE_DOOR_BOT || block.getType() == BlockType.CASTLE_DOOR_TOP) {
                collisionsWithCastle(block);
            } else if (block.getType() == BlockType.FLAG_TIP) {
                collisionsWithFlagTip(block);
            } else if (block.getType() == BlockType.FLAG_POLE) {
                collisionsWithFlagPole(block);
            }
        }
    }

    private void collisionsWithFlagPole(MapFixedBlock block){
        if (block.getBoundingBox().intersects(getRightBound())
                        || block.getBoundingBox().contains(getRightBound())
                        || block.getBoundingBox().intersects(getLeftBound())
                        || block.getBoundingBox().contains(getLeftBound())
                        || block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().contains(getTopBound())
                        || block.getBoundingBox().intersects(getBottomBound())
                        || block.getBoundingBox().contains(getBottomBound())) {
                    if (!addedPointFlag) {
                        addPoints(POINT_FLAG_POLE);
                        addedPointFlag = true;
                    }
        }
    }

    private void collisionsWithFlagTip(MapFixedBlock block){
        if (block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().intersects(getLeftBound())) {
                    if (!addedPointFlag) {
                        addPoints(POINT_FLAG_TIP);
                        addedPointFlag = true;
                    }
        }
    }

    private void collisionsWithCastle(MapFixedBlock block){
        if (block.getBoundingBox().intersects(getBottomBound())
                        || block.getBoundingBox().intersects(getTopBound())
                        || block.getBoundingBox().intersects(getLeftBound())
                        || block.getBoundingBox().intersects(getRightBound())) {
                    if (!addedPointWin) {
                        addPoints(POINT_WIN);
                        addedPointWin = true;
                    }
                    this.hasWon = true;
        }
    }

    private void collisionsWithDeathBlock(MapFixedBlock block){
        if(block.getBoundingBox().intersects(getBottomBound()) || block.getBoundingBox().intersects(getTopBound())
                || block.getBoundingBox().intersects(getLeftBound()) || block.getBoundingBox().intersects(getRightBound())){
                    loseLifeOrPowerUp();
        }
    }

    private void collisionsWithBrick(MapFixedBlock block){
        if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
            setYCollisionTop(block);
            if(currentPowerUp != null){
                addPoints(POINT_LUCKY_BRICK);
                blocksHandler.removeFixedBlock(block);
            }
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

    private void collisionsWithPopped(MapFixedBlock block){
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

    private void collisionsWithLucky(MapFixedBlock block){
        if(block.getBoundingBox().contains(getTopBound()) || block.getBoundingBox().intersects(getTopBound())){
            setYCollisionTop(block);
            addPoints(POINT_LUCKY_BRICK);
            block.popLuckyBlock(powerupsHandler, blocksHandler);
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

    private void collisionsWithStaticBlocks(MapFixedBlock block){
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

    private void collisionsWithEnemies(){
        for(Enemy enemy : enemiesHandler.getEnemies()){
            if(touchedEnemy(enemy) && currentPowerUp != PowerUpType.STAR){
                loseLifeOrPowerUp();
            }else if (killedEnemy(enemy) || (touchedEnemy(enemy) && currentPowerUp == PowerUpType.STAR)){
                addPoints(POINT_KILLED_ENEMY);
                enemiesHandler.removeEnemy(enemy);
            }
        }
    }

    private void collisionsWithPowersUp(){
        for(PowerUp power : powerupsHandler.getPowerups()){
            if(touchedPowerUp(power)){
                switch (power.getPowerUpType()) {
                    case COIN:
                        scoreboard.collectCoin();
                        break;
                    case RED_MUSHROOM:
                        if(currentPowerUp != PowerUpType.RED_MUSHROOM && !(currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)){
                            lastPowerUp = currentPowerUp;
                        }
                        redMushroomTrasformation();
                        break;
                    case STAR:
                        lastPowerUp = currentPowerUp;
                        currentPowerUp = PowerUpType.STAR;
                        break;
                    case LIFE_MUSHROOM:
                        if(life < LIFE_START){
                            life++;
                            scoreboard.restoreHeart();
                        }
                        break;
                }
                power.die();
            }
        }
    }

    private void resetCosecutiveJump() {
        this.consecutiveJumps = 0;
    }

    private void addPoints(int point) {
        this.point += point;
    }

    private boolean touchedPowerUp(PowerUp power){
        return power.getBoundingBox().intersects(getBottomBound()) || power.getBoundingBox().intersects(getTopBound()) 
            || power.getBoundingBox().intersects(getLeftBound()) || power.getBoundingBox().intersects(getRightBound());
    }

    private void redMushroomTrasformation(){
        if(!(currentPowerUp == PowerUpType.STAR && lastPowerUp == PowerUpType.RED_MUSHROOM)){
            if(currentPowerUp != PowerUpType.RED_MUSHROOM){
                setHeight(height*2);
            }
            if(currentPowerUp != PowerUpType.STAR){
                currentPowerUp = PowerUpType.RED_MUSHROOM;
            }
            else{
                lastPowerUp = PowerUpType.RED_MUSHROOM;
            }
        }
    }

    private void loseLifeOrPowerUp(){
        if(currentPowerUp == PowerUpType.RED_MUSHROOM){
            lastPowerUp = currentPowerUp;
            this.height /= 2;
            currentPowerUp = null;
            setY(respawnY);
        }
        else if(currentPowerUp == PowerUpType.STAR){
            if(lastPowerUp == PowerUpType.RED_MUSHROOM){
                lastPowerUp = currentPowerUp;
                currentPowerUp = PowerUpType.RED_MUSHROOM;
            }
            else{
                lastPowerUp = currentPowerUp;
                currentPowerUp = null;
            }
            setY(respawnY);
        }
        else{
            life--;
            scoreboard.removeHeart();
            if (life < 0) {
                this.hasLost = true;
            }
            setX(respawnX);
            setY(respawnY);
        }
    }

    private boolean touchedEnemy(Enemy enemy) {
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

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void jump();

    public abstract void render(Graphics g);

    public abstract void tick();
}
