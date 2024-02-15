package it.unibo.superpeach.graphics;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton{
    public CustomButton(String text, Color customColor, Color customColor1, int scale) {
        super(text);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setFont(new Font("Monospaced", Font.BOLD, 20 * scale));
        setForeground(customColor1);
        setBackground(customColor);
        setFocusable(false);
        setBorderPainted(false);
    }
}
