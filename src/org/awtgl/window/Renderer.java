package org.awtgl.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Renderer {

    public static void drawImage(Image image, int x, int y, int rot, Image display) {
    
        AffineTransform backup = new AffineTransform();
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();
        AffineTransform a = AffineTransform.getRotateInstance(rot, x + (image.getBufferedImage().getWidth() / 2), y + (image.getBufferedImage().getHeight() / 2));

        g2d.setTransform(a);
        g2d.drawImage(image.getBufferedImage(), x, y, image.getBufferedImage().getWidth(), image.getBufferedImage().getHeight(), null);
        g2d.setTransform(backup);
        g2d.dispose();

    }



    public static void drawImage(Image image, int x, int y, int width, int height, int rot, Image display) {
    
        AffineTransform backup = new AffineTransform();
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();
        AffineTransform a = AffineTransform.getRotateInstance(rot, x + (width / 2), y + (height / 2));

        g2d.setTransform(a);
        g2d.drawImage(image.getBufferedImage(), x, y, width, height, null);
        g2d.setTransform(backup);
        g2d.dispose();

    }



    public static void drawLine(int x1, int y1, int x2, int y2, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();

    }

}
