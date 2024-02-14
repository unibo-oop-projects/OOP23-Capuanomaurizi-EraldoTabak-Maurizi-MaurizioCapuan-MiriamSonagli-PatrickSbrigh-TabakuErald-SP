package it.unibo.superpeach.graphics;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton{
    public CustomButton(String text, Color customColor, int scale) {
        super(text);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setFont(new Font("Monospaced", Font.BOLD, 20 * scale));
        setBackground(customColor);
        setFocusable(false);
        setBorderPainted(false);
    }
}
