package it.unibo.superpeach.graphics;

import javax.swing.*;

import it.unibo.superpeach.game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeachMenu extends JFrame {

    private JFrame frame;
    private Dimension size;
    @SuppressWarnings("unused")
    private final Game game;

    public PeachMenu(String title, int width, int height, int scale, Game game) {

        size = new Dimension(width*scale, height*scale);
        frame = new JFrame(title);
        this.game = game;

        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centre screen

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);

        Color customColor = new Color(149, 69, 169);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);

        JLabel titleLabel = new JLabel("SUPER PEACH");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40*scale));
        panel.add(titleLabel);

        JButton startButton = new JButton("START GAME");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Monospaced", Font.BOLD, 20*scale));
        startButton.setBackground(customColor);
        startButton.setFocusable(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Come avvio il gioco??
                panel.setVisible(false);
                frame.remove(panel);
                frame.add(game);
                game.init();
            }
        });
        panel.add(startButton);

        JButton optionsButton = new JButton("OPTIONS");
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setFont(new Font("Monospaced", Font.BOLD, 20*scale));
        optionsButton.setBackground(customColor);
        optionsButton.setFocusable(false);
        optionsButton.setBorderPainted(false);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aprire un menu delle opzioni
                JOptionPane.showMessageDialog(panel, "Opzioni del gioco");
            }
        });
        panel.add(optionsButton);

        JButton GUIScaleButton = new JButton("GUI Scale: " + scale);
        GUIScaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        GUIScaleButton.setFont(new Font("Monospaced", Font.BOLD, 20*scale));
        GUIScaleButton.setBackground(customColor);
        GUIScaleButton.setFocusable(false);
        GUIScaleButton.setBorderPainted(false);
        GUIScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeScale(scale == 1 ? 2 : 1);
            }
        });
        panel.add(GUIScaleButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(new Font("Monospaced", Font.BOLD, 20*scale));
        exitButton.setBackground(customColor);
        exitButton.setFocusable(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chiudo l'applicazione
                System.exit(0);
            }
        });
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void closeWindow(){
        frame.setVisible(false); //you can't see me!
        frame.dispose(); //Destroy the JFrame object
    }

}