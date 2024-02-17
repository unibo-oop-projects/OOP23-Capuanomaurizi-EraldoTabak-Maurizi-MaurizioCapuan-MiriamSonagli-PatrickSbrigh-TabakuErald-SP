package it.unibo.superpeach.graphics;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class CustomButton extends JButton {
    public CustomButton(final String text, final Color customColor, final Color customColor1, final int scale) {
        super(text);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setFont(new Font("Monospaced", Font.BOLD, 10 * scale));
        setForeground(customColor1);
        setBackground(customColor);
        setFocusable(false);
        // Creazione di un bordo
        Border border = BorderFactory.createLineBorder(customColor1, 2, true);
        setBorder(border);
        setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
    }
}
