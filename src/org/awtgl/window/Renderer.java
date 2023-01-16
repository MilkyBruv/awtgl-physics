package org.awtgl.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import org.awtgl.physics.primitives.AABB;
import org.awtgl.physics.primitives.Box;
import org.awtgl.physics.primitives.Circle;
import org.awtgl.physics.rigidbody.Rigidbody;
import org.awtgl.physics.vectors.Line;
import org.awtgl.physics.vectors.Vector2;

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



    public static void drawImage(Image image, Vector2 pos, int width, int height, int rot, Image display) {
    
        AffineTransform backup = new AffineTransform();
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();
        AffineTransform a = AffineTransform.getRotateInstance(rot, Math.round(pos.x) + (width / 2), Math.round(pos.y) + (height / 2));

        g2d.setTransform(a);
        g2d.drawImage(image.getBufferedImage(), Math.round(pos.x), Math.round(pos.y), width, height, null);
        g2d.setTransform(backup);
        g2d.dispose();

    }



    public static void drawLine(Vector2 pos1, Vector2 pos2, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawLine(Math.round(pos1.x), Math.round(pos1.y), Math.round(pos2.x), Math.round(pos2.y));
        g2d.dispose();

    }



    public static void drawLine(Line line, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawLine(Math.round(line.getStart().x), Math.round(line.getStart().y), Math.round(line.getEnd().x), Math.round(line.getEnd().y));
        g2d.dispose();

    }



    public static void drawCircle(Vector2 centerPos, float radius, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRoundRect(Math.round(centerPos.x - radius), Math.round(centerPos.y - radius), Math.round(radius * 2), Math.round(radius * 2), Math.round(radius * 2), Math.round(radius * 2));
        g2d.dispose();

    }



    public static void drawCircle(Circle circle, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRoundRect(

            Math.round(circle.getCenter().x - circle.getRadius()), 
            Math.round(circle.getCenter().y - circle.getRadius()), 
            Math.round(circle.getRadius() * 2), 
            Math.round(circle.getRadius() * 2), 
            Math.round(circle.getRadius() * 2), 
            Math.round(circle.getRadius() * 2)
        
        );
        g2d.dispose();

    }



    public static void drawRect(Vector2 pos, Vector2 size, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRect(

            Math.round(pos.x), 
            Math.round(pos.y), 
            Math.round(size.x), 
            Math.round(size.y)

        );
        g2d.dispose();

    }



    public static void drawRect(Vector2 pos, int width, int height, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRect(

            Math.round(pos.x), 
            Math.round(pos.y), 
            width, 
            height

        );
        g2d.dispose();

    }



    public static void drawRB(Rigidbody rigidbody, Vector2 size, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRect(

            Math.round(rigidbody.getPos().x), 
            Math.round(rigidbody.getPos().y), 
            Math.round(size.x), 
            Math.round(size.y)

        );
        g2d.dispose();

    }



    public static void drawAABB(AABB box, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawRect(

            Math.round(box.getMin().x), 
            Math.round(box.getMin().y), 
            Math.round(box.getMax().x - box.getMin().x), 
            Math.round(box.getMax().y - box.getMin().y)

        );
        g2d.dispose();

    }



    public static void drawBox(Box box, int colour, Image display) {
    
        Graphics2D g2d = (Graphics2D) display.getBufferedImage().getGraphics();

        g2d.setColor(new Color(colour));
        g2d.drawLine(

            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y),
            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y - (box.getHalfSize().y * 2))

        );
        g2d.drawLine(

            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y),
            Math.round(box.getRigidbody().getPos().x - (box.getHalfSize().x * 2)), 
            Math.round(box.getRigidbody().getPos().y)

        );
        g2d.drawLine(

            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y),
            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y + (box.getHalfSize().y * 2))

        );
        g2d.drawLine(

            Math.round(box.getRigidbody().getPos().x), 
            Math.round(box.getRigidbody().getPos().y),
            Math.round(box.getRigidbody().getPos().x + (box.getHalfSize().x * 2)), 
            Math.round(box.getRigidbody().getPos().y)

        );
        g2d.dispose();

    }

}
