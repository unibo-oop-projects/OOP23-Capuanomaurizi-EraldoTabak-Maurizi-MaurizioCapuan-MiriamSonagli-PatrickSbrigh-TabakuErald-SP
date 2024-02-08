package it.unibo.superpeach.blocks;

import java.awt.Graphics;

public class MapBackgroundBlock extends Block{

    public MapBackgroundBlock(int x, int y, int width, int height, int scale, BlockType type){
        super(x, y, width, height, scale);
        setType(type);
        switch (type) {
            case CLOUD:
                setSprites(getTexturer().getCloud());
                break;
            case BUSH:
                setSprites(getTexturer().getBush());
                break;
            case HILL:
                setSprites(getTexturer().getHill());
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
            case CLOUD:
                buildCloud(g);
                break;

            case BUSH:
                buildBush(g);    
                break;

            case HILL:
                buildHill(g);  
                break;
            default:
                break;
        }
    }

    private void buildCloud(Graphics g){
        int x = getX();
        int y = getY();
        g.drawImage(getSprites()[0], x, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[1], x+32, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[2], x+64, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[3], x, y+32, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[4], x+32, y+32, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[5], x+64, y+32, getWidth(), getHeight(), null);
    }

    private void buildBush(Graphics g){
        int x = getX();
        int y = getY();
        g.drawImage(getSprites()[0], x, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[1], x+32, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[2], x+64, y, getWidth(), getHeight(), null);
    }

    private void buildHill(Graphics g){
        int x = getX();
        int y = getY();
        g.drawImage(getSprites()[1], x, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[1], x+32, y-32, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[0], x+64, y-64, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[2], x+32, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[4], x+96, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[3], x+64, y-32, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[3], x+64, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[5], x+128, y, getWidth(), getHeight(), null);
        g.drawImage(getSprites()[5], x+96, y-32, getWidth(), getHeight(), null);
    }

}
