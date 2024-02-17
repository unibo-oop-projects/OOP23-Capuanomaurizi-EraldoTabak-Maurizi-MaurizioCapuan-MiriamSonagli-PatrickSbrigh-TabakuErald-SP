package it.unibo.superpeach.gameObjects;

import java.util.List;
import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.enemies.Enemy;

public interface ObjectsHandler {
    public void tick();

    public void render(Graphics g);

    public void add(Enemy enemy);

    public void remove(Enemy enemy);

    public List<Enemy> get();

}
