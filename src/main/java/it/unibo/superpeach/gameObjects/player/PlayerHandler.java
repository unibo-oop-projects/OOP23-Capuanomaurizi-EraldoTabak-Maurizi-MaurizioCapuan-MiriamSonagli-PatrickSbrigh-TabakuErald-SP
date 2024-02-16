package it.unibo.superpeach.gameObjects.player;

import java.awt.Graphics;

public class PlayerHandler {
    private Player player;

    public int setPlayer(Player p){
        if(this.player == null){
            this.player = p;
            return 0;
        }
        return -1;
    }

    public Player getPlayer(){
        return this.player;
    }

    public int removePlayer(){
        if(this.player != null){
            this.player = null;
            return 0;
        }
        return -1;
    }

    public void tick(){
        this.player.tick();
    }

    public void render(Graphics g){
        this.player.render(g);
    }
}
