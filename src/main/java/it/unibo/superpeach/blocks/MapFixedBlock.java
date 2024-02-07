package it.unibo.superpeach.blocks;

import java.awt.Graphics;

public class MapFixedBlock extends Block{

    public MapFixedBlock(double x, double y, double width, double height, int scale, BlockType type) {
        super(x, y, width, height, scale);
        setType(type);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        switch (getType()) {
            case TERRAIN:
                setSprites(getTexturer().getBricksCastle());
                g.drawImage(getSprites()[0], (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
                break;

            case LUCKY:
                setSprites(getTexturer().getLucky());
                g.drawImage(getSprites()[0], (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
                break;

            case BRICK:
                setSprites(getTexturer().getBricksCastle());
                g.drawImage(getSprites()[2], (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
                break;
        }
    }

}
