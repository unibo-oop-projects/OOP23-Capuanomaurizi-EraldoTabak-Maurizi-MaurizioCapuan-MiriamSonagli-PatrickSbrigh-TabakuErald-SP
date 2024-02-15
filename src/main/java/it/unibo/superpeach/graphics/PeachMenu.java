package it.unibo.superpeach.graphics;
import it.unibo.superpeach.game.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;

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
        ImageIcon imageicon = new ImageIcon("src/main/resources/it/unibo/superpeach/tiles/PeachIcon.png");
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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);

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
                optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiudo solo il frame delle opzioni quando viene chiuso

                // Nuovo pannello per le opzioni
                JPanel optionsPanel = new JPanel();
                optionsPanel.setLayout(new FlowLayout());

                // Slider per regolare il volume
                JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100 , 50) {
                    @Override
                    public void updateUI() {
                        setUI(new BasicSliderUI(this) {
                            @Override
                            public void paintThumb(Graphics g) {
                                Rectangle knobBounds = thumbRect;
                                int w = knobBounds.width;
                                int h = knobBounds.height;
                                Graphics2D g2d = (Graphics2D) g.create();
                                g2d.setColor(customColor);
                                g2d.fillRect(knobBounds.x, knobBounds.y, w, h);
                                g2d.dispose();
                            }
                        });
                    }
                };
                volumeSlider.setMajorTickSpacing(10);
                volumeSlider.setMinorTickSpacing(1);
                volumeSlider.setPaintTicks(true);
                volumeSlider.setPaintLabels(true);

                // Cambiamento del volume
                volumeSlider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        int volume = source.getValue();
                        System.out.println("Volume: " + volume);
                        // Qui puoi applicare il volume alla tua musica
                    }
                });

                optionsPanel.add(volumeSlider);
                optionsFrame.add(optionsPanel);
                optionsFrame.setLocationRelativeTo(null); // Centra il frame delle opzioni nello schermo
                optionsFrame.setVisible(true);

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

        frame.add(panel);
        frame.setVisible(true);
    }

    public void closeWindow(){
        frame.setVisible(false);
        frame.dispose();
    }

}