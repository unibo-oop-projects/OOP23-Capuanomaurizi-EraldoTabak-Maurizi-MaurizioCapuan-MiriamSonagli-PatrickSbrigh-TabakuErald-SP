package it.unibo.superpeach.graphics;

import it.unibo.superpeach.game.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PeachMenu extends JFrame {

    private JFrame frame;
    private Dimension size;
    private final Game game;
    private Clip clip;

    public PeachMenu(String title, int width, int height, int scale, Game game) {
        this.game = game;
        size = new Dimension(width * scale, height * scale);
        frame = new JFrame(title);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/it/unibo/superpeach/icon/peach_icon.png");

        frame.setIconImage(imageIcon.getImage());
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centre screen

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Color customColor = new Color(218, 165, 32);    //SFONDO BOTTONI
        Color customColor1 = new Color(101, 67, 33);    //PER LE SCRITTE

        String imagePath = "src/main/resources/it/unibo/superpeach/tiles/background.png";
        ImagePanel panel = new ImagePanel(imagePath);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("SUPER PEACH");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40 * scale));
        titleLabel.setForeground(Color.PINK);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20 * scale));

        // START GAME BUTTON
        CustomButton startButton = new CustomButton("START GAME", customColor, customColor1, scale);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.remove(panel);
                frame.add(game);
                game.init();
            }
        });
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10 * scale));

        
        // GUI SCALE BUTTON
        String[] GUIScaleList = {"Tiny", "Small", "Medium", "Large"};
        JComboBox<String> GUIComboBox = new JComboBox<>(GUIScaleList);
        GUIComboBox.setLayout(new FlowLayout());
        GUIComboBox.setBackground(customColor);
        GUIComboBox.setForeground(customColor1);
        GUIComboBox.setFont(new Font("Monospaced", Font.BOLD, 10*scale));

        GUIComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        CustomButton GUIScaleButton = new CustomButton("GUI SCALE", customColor, customColor1, scale);
        GUIComboBox.setPreferredSize(GUIScaleButton.getPreferredSize());
        GUIComboBox.setMaximumSize(GUIScaleButton.getPreferredSize());

        GUIScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIScaleButton.add(GUIComboBox);
            }
        });

        GUIComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBackgroundMusic(); // Ferma la riproduzione della canzone corrente
                GUIComboBox.setPreferredSize(GUIScaleButton.getPreferredSize());
                GUIComboBox.setMaximumSize(GUIScaleButton.getPreferredSize());
                String selectedScale = (String) GUIComboBox.getSelectedItem();
                int scalerange = 1;
                switch (selectedScale) {
                    case "Tiny":
                        scalerange = 1;
                        break;
                
                    case "Small":
                        scalerange = 2;
                        break;

                        case "Medium":
                        scalerange = 3;
                        break;
                        
                        case "Large":
                        scalerange = 4;
                        break;
                    }
                game.changeScale(scalerange);
                GUIComboBox.getParent().revalidate();
                GUIComboBox.getParent().repaint();
            }
        });
        getContentPane().add(GUIComboBox, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.add(GUIScaleButton);
        panel.add(Box.createVerticalStrut(10 * scale));
        
        // MUSIC BUTTON
        String sound1 = new String("src/main/resources/it/unibo/superpeach/music/sound1.wav");
        String sound2 = new String("src/main/resources/it/unibo/superpeach/music/sound2.wav");
        String sound3 = new String("src/main/resources/it/unibo/superpeach/music/sound3.wav");
        String[] songList = {"n. 1", "n. 2", "n. 3"};
        JComboBox<String> songComboBox = new JComboBox<>(songList);
        songComboBox.setLayout(new FlowLayout());
        songComboBox.setBackground(customColor);
        songComboBox.setForeground(customColor1);
        songComboBox.setFont(new Font("Monospaced", Font.BOLD, 10*scale));

        songComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        CustomButton optionsButton = new CustomButton("MUSIC", customColor, customColor1, scale);
        songComboBox.setPreferredSize(optionsButton.getPreferredSize());
        songComboBox.setMaximumSize(optionsButton.getPreferredSize());
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsButton.add(songComboBox);
            }
        });

        songComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSong = (String) songComboBox.getSelectedItem();
                String path = "";
                switch (selectedSong) {
                    case "n. 1":
                        path = sound1;
                        break;
                
                    case "n. 2":
                        path = sound2;
                        break;

                    case "n. 3":
                        path = sound3;
                        break;
                }

                playSong(path);
            }
        });

        getContentPane().add(songComboBox, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        panel.add(optionsButton);
        panel.add(Box.createVerticalStrut(10 * scale));
        
        // EXIT BUTTON
        CustomButton exitButton = new CustomButton("EXIT", customColor, customColor1, scale);
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

    private void playSong(String songFilePath) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }

        try {
            File audioFile = new File(songFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void closeWindow() {
        frame.setVisible(false);
        frame.dispose();
    }
}