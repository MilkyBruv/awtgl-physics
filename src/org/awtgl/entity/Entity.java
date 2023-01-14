package org.awtgl.entity;

public class Entity {

    // Base class for implementing entities, sprites, physics, collisions, etc
    
    public int x;
    public int y;
    public int width;
    public int height;
    public int rotation;
    public CollisionRect rect = null;

    public Entity() { }



    public Entity(int x, int y, int width, int height, int rotation, int rectX, int rectY, int rectWidth, int rectHeight) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;

        // Create new <CollisionRect>
        this.rect = new CollisionRect(this, rectX, rectY, rectWidth, rectHeight);

    }



    public Entity(int x, int y, int width, int height, int rotation, CollisionRect rect) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;

        this.rect = rect;

    }



    public Entity(int x, int y, int width, int height, int rotation) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;

    }



    public boolean hitsEntity(Entity e) {

        // Detect collisions between 2 entities

        if (e.rect != null && this.rect != null) {

            if (this.rect.awtRectangle.intersects(e.rect.awtRectangle)) {
    
                return true;
    
            }

            return false;

        }
        
        return false;

    }



    public void baseUpdate() {

        // Update rect only if it exists
        if (this.rect != null) {

            this.rect.update();

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



    public CollisionRect getRect() {
        return rect;
    }



    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

}
