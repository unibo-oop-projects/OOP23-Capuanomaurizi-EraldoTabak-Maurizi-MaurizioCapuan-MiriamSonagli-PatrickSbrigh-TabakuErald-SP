package it.unibo.superpeach.graphics;
import it.unibo.superpeach.game.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PeachMenu extends JFrame {

    private JFrame frame;
    private Dimension size;
    @SuppressWarnings("unused")
    private final Game game;
    private Clip clip;

    public PeachMenu(String title, int width, int height, int scale, Game game) {

        size = new Dimension(width*scale, height*scale);
        frame = new JFrame(title);
        ImageIcon imageicon = new ImageIcon("src/main/resources/it/unibo/superpeach/icon/PeachIcon.png");
        this.game = game;

        frame.setIconImage(imageicon.getImage());
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
        //panel.setLayout(new BorderLayout());

        //JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setBackground(Color.PINK);

        JLabel titleLabel = new JLabel("SUPER PEACH");
        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40*scale));
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
        panel.add(Box.createVerticalStrut(10*scale));

        CustomButton optionsButton = new CustomButton("VOLUME", customColor, customColor1, scale);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Nuovo frame per il pannello delle opzioni
                JFrame optionsFrame = new JFrame("Volume Options");
                optionsFrame.setSize(300, 200); // Imposta le dimensioni del frame
                optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiudi solo il frame delle opzioni quando viene chiuso

                // Nuovo pannello per le opzioni
                JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new FlowLayout());

                // Aggiunta di una JComboBox per la selezione della canzone
                String[] songList = {
                    "src/main/resources/it/unibo/superpeach/music/Sound1.wav",
                    "src/main/resources/it/unibo/superpeach/music/Sound2.wav",
                    "src/main/resources/it/unibo/superpeach/music/Sound3.wav"
                }; 

                JComboBox<String> songComboBox = new JComboBox<>(songList);
                optionsPanel.add(songComboBox);

                // Aggiunta di un pulsante per confermare la selezione della canzone
                JButton selectButton = new JButton("Select");
                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Recupera la canzone selezionata
                        String selectedSong = (String) songComboBox.getSelectedItem();
                        // Aggiorna la riproduzione della canzone
                        updateSong(selectedSong);
                        // Chiudi la finestra delle opzioni
                        optionsFrame.dispose();
                    }
                });
                optionsPanel.add(selectButton);

                optionsFrame.add(optionsPanel);
                optionsFrame.setLocationRelativeTo(null); // Centra il frame delle opzioni nello schermo
                optionsFrame.setVisible(true);
            }

            // Metodo per aggiornare la canzone selezionata
            private void updateSong(String selectedSong) {
                // Ferma la canzone corrente se è in riproduzione
                if (clip != null && clip.isRunning()) {
                    clip.stop();
                    clip.close();
                }
    
                // Carica e avvia la nuova canzone selezionata
                File audioFile = new File(selectedSong);
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY); // Riproduci in loop
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        panel.add(optionsButton);
        panel.add(Box.createVerticalStrut(10*scale));



        CustomButton GUIScaleButton = new CustomButton("GUI Scale: " + scale, customColor, customColor1, scale);
        GUIScaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeScale(scale == 1 ? 2 : 1);
            }
        });
        panel.add(GUIScaleButton);
        panel.add(Box.createVerticalStrut(10*scale));

        CustomButton exitButton = new CustomButton("EXIT", customColor, customColor1, scale);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        /*
        File audioFile = new File("src/main/resources/it/unibo/superpeach/music/Sound1.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            //clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


        frame.add(panel);
        frame.setVisible(true);
    }

    public void closeWindow(){
        frame.setVisible(false);
        frame.dispose();
    }

}