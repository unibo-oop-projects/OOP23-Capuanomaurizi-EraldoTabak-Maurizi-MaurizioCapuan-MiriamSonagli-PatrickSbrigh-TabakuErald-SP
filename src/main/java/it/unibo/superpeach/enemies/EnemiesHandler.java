package it.unibo.superpeach.enemies;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class EnemiesHandler {

    private List<Enemy> enemies;

    public EnemiesHandler() {
        enemies = new ArrayList<Enemy>();
    }

    public void tickEnemies() {
        for (Enemy enemy : enemies) {
            enemy.tick();
        }
    }

    public void renderEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
    }

    public void addEnemy(Enemy b) {
        enemies.add(b);
    }

    public void removeEnemy(Enemy b) { // la cosa del null (?)
        enemies.remove(b);
    }

    public List<Enemy> getEnemies() {
        return List.copyOf(enemies);
    }

}
