package it.unibo.superpeach.blocks;

import java.awt.Graphics;

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
            case PIPE:
                setSprites(getTexturer().getPipe());
                break;
            default:
                break;
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        switch (getType()) {
            case TERRAIN:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case LUCKY:
                g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case BRICK:
                g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case PIPE:
                break;
            default:
                break;
        }
    }

    // private void buildPipe(){

    // }

}
