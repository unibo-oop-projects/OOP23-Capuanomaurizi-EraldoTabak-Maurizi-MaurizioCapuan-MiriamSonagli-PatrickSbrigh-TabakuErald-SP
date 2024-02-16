package it.unibo.superpeach.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.graphics.Texturer;

public class Scoreboard {
    
    private boolean[] hearts;
    private boolean[] coins;
    private int gameScale;
    private Texturer texturer = new Texturer();
    private BufferedImage[] sprites = texturer.getScoreboard();

    public Scoreboard(int lives, int totalCoins, int scale){
        hearts = new boolean[lives];
        coins = new boolean[totalCoins];
        for (int i = 0; i < lives; i++) {
            hearts[i] = true;
        }
        for (int i = 0; i < totalCoins; i++) {
            coins[i] = false;
        }
        gameScale = scale;
    }

    public void render(Graphics g, int peachX) {
        for (int i = 0; i < hearts.length; i++) {
            if (hearts[i]) {
                g.drawImage(sprites[0], peachX + (i-(hearts.length/2))*16*gameScale + 2*gameScale, 2*gameScale + 16*gameScale, 16*gameScale, 16*gameScale, null);
            } else {
                g.drawImage(sprites[1], peachX + (i-(hearts.length/2))*16*gameScale + 2*gameScale, 2*gameScale + 16*gameScale, 16*gameScale, 16*gameScale, null);
            }
        }
        for (int i = 0; i < coins.length; i++) {
            if (coins[i]) {
                g.drawImage(sprites[2], peachX + (i-(coins.length/2))*16*gameScale + 2*gameScale, 2*gameScale, 16*gameScale, 16*gameScale, null);
            } else {
                g.drawImage(sprites[3], peachX + (i-(coins.length/2))*16*gameScale + 2*gameScale, 2*gameScale, 16*gameScale, 16*gameScale, null);
            }
        }
    }

    public void removeHeart(){
        if(hearts[hearts.length-1]) {
            hearts[hearts.length-1] = false;
        } else {
            int i = 0;
            while (i < hearts.length-1 && hearts[i+1]) {
                i++;
            }
            hearts[i] = false;
        }
    }

    public void collectCoin(){
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] == false) {
                coins[i] = true;
                return;
            }
        }
    }

}
