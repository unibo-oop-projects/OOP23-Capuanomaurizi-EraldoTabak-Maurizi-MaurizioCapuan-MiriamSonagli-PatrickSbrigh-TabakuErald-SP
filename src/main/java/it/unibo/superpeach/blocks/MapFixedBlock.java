package it.unibo.superpeach.blocks;

import java.awt.Graphics;

import it.unibo.superpeach.powerups.PowerupsHandler;

public class MapFixedBlock extends Block{

    public MapFixedBlock(int x, int y, int width, int height, int scale, BlockType type) {
        super(x, y, width, height, scale);
        setType(type);
        switch (type) {
            case TERRAIN:
                setSprites(getTexturer().getTerrain());
                break;
            case LUCKY:
                setSprites(getTexturer().getLucky());
                break;
            case BRICK:
                setSprites(getTexturer().getTerrain());
                break;
            case STONE:
                setSprites(getTexturer().getTerrain());
                break;
            case PIPE_LEFT:
            case PIPE_RIGHT:
            case PIPE_TOP_LEFT:
            case PIPE_TOP_RIGHT:
                setSprites(getTexturer().getPipe());
                break;
            case FLAG_TIP:
            case FLAG_POLE:
                setSprites(getTexturer().getFlag());
                break;
            case DEATH_BLOCK:
            case ALT_BLOCK:
                break;
            default:
                //its for sure a castle block, using default to avoid too many code
                setSprites(getTexturer().getTerrain());
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        switch (getType()) {
            case TERRAIN:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case BRICK:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case STONE:
                g.drawImage(getSprites()[8], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case PIPE_LEFT:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case PIPE_RIGHT:
                g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case PIPE_TOP_LEFT:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case PIPE_TOP_RIGHT:
                g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case FLAG_TIP:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case FLAG_POLE:
                g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_DOOR_TOP:
                g.drawImage(getSprites()[6], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case CASTLE_DOOR_BOT:
                g.drawImage(getSprites()[14], getX(), getY(), getWidth(), getHeight(), null);
                break;
            default:
                break;
        }
    }

    public void popLuckyBlock(PowerupsHandler powerupsHandler, BlocksHandler blocksHandler){
    }

}
