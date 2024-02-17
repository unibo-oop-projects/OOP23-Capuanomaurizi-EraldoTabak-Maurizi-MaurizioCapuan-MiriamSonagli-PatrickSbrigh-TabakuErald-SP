package it.unibo.superpeach.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Resource image loading class.
 * 
 * @author Maurizio Capuano
 */
public final class BufferedImageLoader {

    private BufferedImage img;

    /**
     * @param imgPath ClassLoader resource path
     * @return BufferedImage containing the retrieved image or null if method fails
     */
    public BufferedImage loadImage(final String imgPath) {
        try {
            img = ImageIO.read(ClassLoader.getSystemResource(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
