package org.awtgl.entity;

import java.awt.Rectangle;

public class CollisionRect {

    // Collision rectangle class for collisions (is always attatched to an <Entity>)
    
    public int x;
    public int y;
    public int windowX;
    public int windowY;
    public int width;
    public int height;
    public Entity entity = null;
    public boolean centered = false;
    protected Rectangle awtRectangle;

    public CollisionRect(Entity e, int x, int y, int width, int height) {

        this.entity = e;
        
        this.x = x;
        this.y = y;
        this.windowX = e.x + this.x;
        this.windowY = e.y + this.y;
        this.width = width;
        this.height = height;

        this.awtRectangle = new Rectangle(this.windowX, this.windowY, this.width, this.height);

    }



    public CollisionRect(Entity e, int width, int height) {

        this.centered = true;
        this.entity = e;

        this.width = width;
        this.height = height;
        this.x = (e.width / 2) - (this.width / 2);
        this.y = (e.height / 2) - (this.height / 2);
        this.windowX = e.x + this.x;
        this.windowY = e.y + this.y;

        this.awtRectangle = new Rectangle(this.windowX, this.windowY, this.width, this.height);

    }



    public CollisionRect(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.windowX = this.x;
        this.windowY = this.y;
        this.width = width;
        this.height = height;

        this.awtRectangle = new Rectangle(this.x, this.y, this.width, this.height);

    }



    public boolean hitsRect(CollisionRect rect) {

        // Detects collisions between 2 <CollisionRect>s

        if (this.awtRectangle.intersects(rect.awtRectangle)) {

            return true;

        }

        return false;

    }



    public boolean hitsEntity(Entity e) {

        // Detects collisions between a <CollisionRect> and an <Entity>

        if (e.rect != null) {

            if (this.awtRectangle.intersects(e.rect.awtRectangle)) {
    
                return true;
    
            }

            return false;

        }
        
        return false;

    }



    public void update() {

        if (this.entity != null) {

            if (!this.centered) {

                this.windowX = this.entity.x + this.x;
                this.windowY = this.entity.y + this.y;
        
                this.awtRectangle = new Rectangle(this.windowX, this.windowY, this.width, this.height);

            }

            if (this.centered) {

                this.x = (this.entity.width / 2) - (this.width / 2);
                this.y = (this.entity.height / 2) - (this.height / 2);
                this.windowX = this.entity.x + this.x;
                this.windowY = this.entity.y + this.y;
        
                this.awtRectangle = new Rectangle(this.windowX, this.windowY, this.width, this.height);

            }

        }

        if (this.entity == null) {

            this.awtRectangle = new Rectangle(this.windowX, this.windowY, this.width, this.height);

        }

    }



    public int getX() {
        return x;
    }



    public void setX(int x) {
        this.x = x;
    }



    public int getY() {
        return y;
    }



    public void setY(int y) {
        this.y = y;
    }



    public int getWindowX() {
        return windowX;
    }



    public void setWindowX(int windowX) {
        this.windowX = windowX;
    }



    public int getWindowY() {
        return windowY;
    }



    public void setWindowY(int windowY) {
        this.windowY = windowY;
    }



    public int getWidth() {
        return width;
    }



    public void setWidth(int width) {
        this.width = width;
    }



    public int getHeight() {
        return height;
    }



    public void setHeight(int height) {
        this.height = height;
    }



    public Entity getEntity() {
        return entity;
    }



    public void setEntity(Entity entity) {
        this.entity = entity;
    }

}
