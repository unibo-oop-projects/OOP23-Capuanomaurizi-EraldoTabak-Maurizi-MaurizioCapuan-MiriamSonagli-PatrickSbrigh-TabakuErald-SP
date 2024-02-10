package it.unibo.superpeach.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeachMenu extends JFrame {

    public PeachMenu() {
        setTitle("SUPER PEACH");
        setSize(960, 720);  // devo farla adattiva, giusto?
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color customColor = new Color(123, 160, 91);

        //DA METTERE IL PERCORSO DELL'IMMAGINE
        ImageIcon imageIcon = new ImageIcon("PeachSpriteIcon.jpeg");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);

        JLabel titleLabel = new JLabel("SUPER PEACH");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 50));
        panel.add(titleLabel);

        JButton startButton = new JButton("START GAME");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Monospaced", Font.BOLD,25));
        startButton.setBackground(customColor);
        startButton.setFocusable(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Come avvio il gioco??
                JOptionPane.showMessageDialog(null, "START!");
                panel.setBackground(Color.BLUE);
            }
        });
        panel.add(startButton);

        JButton optionsButton = new JButton("OPTIONS");
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setFont(new Font("Monospaced", Font.BOLD,25));
        optionsButton.setBackground(customColor);
        optionsButton.setFocusable(false);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aprire un menu delle opzioni
                JOptionPane.showMessageDialog(null, "Opzioni del gioco");
            }
        });
        panel.add(optionsButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(new Font("Monospaced", Font.BOLD,25));
        exitButton.setBackground(customColor);
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chiudo l'applicazione
                System.exit(0);
            }
        });
        panel.add(exitButton);

        add(panel);
        setLocationRelativeTo(null); //centra il frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PeachMenu();
            }
        });
    }
}