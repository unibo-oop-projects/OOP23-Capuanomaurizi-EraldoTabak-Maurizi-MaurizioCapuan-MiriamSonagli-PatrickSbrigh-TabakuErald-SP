package it.unibo.superpeach.graphics;

import java.awt.Dimension;
import javax.swing.*;

import it.unibo.superpeach.game.Game;

public class GameWindow {

    private JFrame frame;
    private Dimension size;

    public GameWindow(String title, int width, int height, int scale, Game game){
        size = new Dimension(width*scale, height*scale);
        frame = new JFrame(title);

        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centre screen

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(game);
        frame.setVisible(true);

    }

    public int getWidth(){
        return (int)size.getWidth();
    }

    public int getHeight(){
        return (int)size.getHeight();
    }

    public void changeScale(int scale){
        size.setSize((int)size.getWidth()*scale, (int)size.getHeight()*scale);
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
    }

}
