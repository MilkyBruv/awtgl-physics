package org.awtgl.entity;

import org.awtgl.window.Image;
import org.awtgl.window.Renderer;

public class Sprite extends Entity {

    public Image image;
    public boolean hidden = false;

    public Sprite() {

        //

    }



    public void update() { }



    public void draw(Image display) {

        if (!this.hidden) {

            Renderer.drawImage(this.image, this.x, this.y, this.rotation, display);

        }

    }



    public void hide() {

        this.hidden = true;

    }



    public void show() {

        this.hidden = false;

    }



    public Image getImage() {

        return image;

    }



    public void setImage(Image image) {

        this.image = image;

    }



    public boolean isHidden() {

        return hidden;

    }



    public void setHidden(boolean hidden) {

        this.hidden = hidden;

    }



    public int getRotation() {

        return rotation;

    }



    public void setRotation(int rotation) {

        this.rotation = rotation;

    }

}
