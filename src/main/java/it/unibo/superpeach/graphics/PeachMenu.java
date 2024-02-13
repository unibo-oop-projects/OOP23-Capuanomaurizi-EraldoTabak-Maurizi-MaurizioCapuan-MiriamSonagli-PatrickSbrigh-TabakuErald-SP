package it.unibo.superpeach.graphics;
import it.unibo.superpeach.game.Game;

import javax.swing.*;
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

        CustomButton startButton = new CustomButton("START GAME", customColor, scale);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.remove(panel);
                frame.add(game);
                game.init();
            }
        });

        CustomButton optionsButton = new CustomButton("OPTIONS", customColor, scale);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // Aprire un menu delle opzioni
            JOptionPane.showMessageDialog(panel, "Opzioni del gioco");
        }
        });
        panel.add(optionsButton);

        CustomButton GUIScaleButton = new CustomButton("GUI Scale: " + scale, customColor, scale);
        GUIScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeScale(scale == 1 ? 2 : 1);
            }
        });
        panel.add(GUIScaleButton);

        CustomButton exitButton = new CustomButton("EXIT", customColor, scale);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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