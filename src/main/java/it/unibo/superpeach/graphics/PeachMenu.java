package it.unibo.superpeach.graphics;

import it.unibo.superpeach.game.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PeachMenu extends JFrame {

    private JFrame frame;
    private Dimension size;
    private final Game game;
    private Clip clip;

    public PeachMenu(String title, int width, int height, int scale, Game game) {
        this.game = game;
        size = new Dimension(width * scale, height * scale);
        frame = new JFrame(title);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/it/unibo/superpeach/icon/PeachIcon.png");

        frame.setIconImage(imageIcon.getImage());
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centre screen

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Color customColor = new Color(218, 165, 32);    //149, 69, 169
        Color customColor1 = new Color(101, 67, 33);    //PER LE SCRITTE

        String imagePath = "src/main/resources/it/unibo/superpeach/tiles/Background.png";
        ImagePanel panel = new ImagePanel(imagePath);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("SUPER PEACH");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40 * scale));
        titleLabel.setForeground(customColor1);
        panel.add(titleLabel);

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

        CustomButton optionsButton = new CustomButton("MUSIC", customColor, customColor1, scale);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame optionsFrame = new JFrame("Volume Options");
                optionsFrame.setSize(300, 200);
                optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String imagePath = "src/main/resources/it/unibo/superpeach/tiles/MusicBackground.png";
                ImagePanel optionsPanel = new ImagePanel(imagePath);
                //optionsPanel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                
                //JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new FlowLayout());

                String[] songList = {
                        "src/main/resources/it/unibo/superpeach/music/Sound1.wav",
                        "src/main/resources/it/unibo/superpeach/music/Sound2.wav",
                        "src/main/resources/it/unibo/superpeach/music/Sound3.wav"
                };

                JComboBox<String> songComboBox = new JComboBox<>(songList);
                optionsPanel.add(songComboBox);

                CustomButton selectButton = new CustomButton("Select", customColor, customColor1, scale/2);
                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedSong = (String) songComboBox.getSelectedItem();
                        updateSong(selectedSong);
                        optionsFrame.dispose();
                    }
                });
                optionsPanel.add(selectButton);

                optionsFrame.add(optionsPanel);
                optionsFrame.setLocationRelativeTo(null);
                optionsFrame.setVisible(true);
            }

            private void updateSong(String selectedSong) {
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }

                File audioFile = new File(selectedSong);
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
        panel.add(optionsButton);
        panel.add(Box.createVerticalStrut(10 * scale));

        CustomButton GUIScaleButton = new CustomButton("GUI Scale: " + scale, customColor, customColor1, scale);
        GUIScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBackgroundMusic(); // Ferma la riproduzione della canzone corrente
                game.changeScale(scale == 1 ? 2 : 1);
            }
        });
        panel.add(GUIScaleButton);
        panel.add(Box.createVerticalStrut(10 * scale));

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