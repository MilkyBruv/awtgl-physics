package org.awtgl.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.awtgl.vectors.Vector2;

public class Renderer {

    public static void drawImage(Image image, Vector2 vec, int rot, Image display) {
    
        AffineTransform backup = new AffineTransform();
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();
        AffineTransform a = AffineTransform.getRotateInstance(rot, Math.round(vec.x) + (image.getBufferedImage().getWidth() / 2), Math.round(vec.y) + (image.getBufferedImage().getHeight() / 2));

        g2d.setTransform(a);
        g2d.drawImage(image.getBufferedImage(), Math.round(vec.x), Math.round(vec.y), image.getBufferedImage().getWidth(), image.getBufferedImage().getHeight(), null);
        g2d.setTransform(backup);
        g2d.dispose();

    }



    public static void drawImage(Image image, Vector2 vec, int width, int height, int rot, Image display) {
    
        AffineTransform backup = new AffineTransform();
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();
        AffineTransform a = AffineTransform.getRotateInstance(rot, Math.round(vec.x) + (width / 2), Math.round(vec.y) + (height / 2));

        g2d.setTransform(a);
        g2d.drawImage(image.getBufferedImage(), Math.round(vec.x), Math.round(vec.y), width, height, null);
        g2d.setTransform(backup);
        g2d.dispose();

    }



    public static void drawLine(Vector2 vec1, Vector2 vec2, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawLine(Math.round(vec1.x), Math.round(vec1.y), Math.round(vec2.x), Math.round(vec2.y));
        g2d.dispose();

    }



    public static void drawCircle(Vector2 vec, float radius, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRoundRect(Math.round(vec.x - radius), Math.round(vec.y - radius), Math.round(radius * 2), Math.round(radius * 2), Math.round(radius * 2), Math.round(radius * 2));
        g2d.dispose();

    }

}
