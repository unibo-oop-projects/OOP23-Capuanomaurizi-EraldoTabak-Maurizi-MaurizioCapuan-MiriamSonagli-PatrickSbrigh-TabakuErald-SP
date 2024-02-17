package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Enemies Handler class used for enemies management.
 * 
 * @author Eraldo Tabaku
 */
public class EnemiesHandler {

    private List<Enemy> enemies;

    public EnemiesHandler() {
        enemies = new ArrayList<Enemy>();
    }

    /**
     * this method calls the tick() method of all the enemies.
     */
    public void tickEnemies() {
        Set<Enemy> enemiesToRemove = new HashSet<>();
        for (Enemy enemy : enemies) {
            enemy.tick();
            if (!enemy.getIsAlive()) {
                enemiesToRemove.add(enemy);
            }
        }

        for (Enemy enemy : enemiesToRemove) {
            enemies.remove(enemy);

        }
    }

    /**
     * method used to call the render method of
     * all the enemies generated.
     * 
     * @param g for the graphics.
     */
    public void renderEnemies(final Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
    }

    /**
     * @param enemy enemy to add to the enemies handler.
     */
    public void addEnemy(final Enemy enemy) {
        enemies.add(enemy);
    }

    /**
     * @param enemy to remove an enemy from the enemies handler.
     */
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    /**
     * @return a list of the enemies the are currently alive.
     */
    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

}
