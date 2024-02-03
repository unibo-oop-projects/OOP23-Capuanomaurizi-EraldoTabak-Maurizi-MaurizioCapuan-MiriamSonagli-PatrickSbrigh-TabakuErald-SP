package it.unibo.superpeach.blocks;

import java.awt.Graphics;

public class MapFixedBlock extends Block{

    public MapFixedBlock(double x, double y, double w, double h, int s) {
        super(x, y, w, h, s);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        showBorders(g);
    }

}
