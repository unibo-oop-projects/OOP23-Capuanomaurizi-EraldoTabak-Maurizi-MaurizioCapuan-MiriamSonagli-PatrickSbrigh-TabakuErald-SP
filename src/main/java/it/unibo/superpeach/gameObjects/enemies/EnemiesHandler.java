package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnemiesHandler {

    private List<Enemy> enemies;

    public EnemiesHandler() {
        enemies = new ArrayList<Enemy>();
    }

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

    public void renderEnemies(final Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
    }

    public void addEnemy(final Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(final Enemy enemy) {

        this.enemies.remove(enemy);
    }

    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

}
