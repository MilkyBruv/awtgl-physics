package org.awtgl.primitives;

import org.awtgl.rigidbody.Rigidbody;
import org.awtgl.vectors.Vector2;

public class Circle {
    
    private float radius;
    private Rigidbody rigidbody = null;

    public Circle(float radius) {

        this.radius = radius;

    }



    public Vector2 getCenter() {

        return this.rigidbody.getPos();

    }


    
    public float getRadius() {
        return radius;
    }



    public void setRadius(float radius) {
        this.radius = radius;
    }

}
