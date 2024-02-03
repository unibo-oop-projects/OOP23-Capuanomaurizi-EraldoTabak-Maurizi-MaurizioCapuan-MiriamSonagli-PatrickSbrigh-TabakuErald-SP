package it.unibo.superpeach.graphics;

import java.awt.Dimension;
import javax.swing.*;

import it.unibo.superpeach.game.Game;

public class GameWindow {

    private JFrame frame;
    private Dimension size;

    public GameWindow(String title, int width, int height, Game g){
        size = new Dimension(width, height);
        frame = new JFrame(title);

        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centre screen

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(g);
        frame.setVisible(true);

    }

}
