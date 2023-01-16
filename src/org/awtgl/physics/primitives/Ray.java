package org.awtgl.physics.primitives;

import org.awtgl.physics.vectors.Vector2;

public class Ray {
    
    private Vector2 origin;
    private Vector2 dir;

    public Ray(Vector2 origin, Vector2 dir) {

        this.origin = origin;
        this.dir = dir;
        this.dir.normalize(null);

    }



    public Vector2 getOrigin() {

        return this.origin;

    }



    public Vector2 getDir() {

        return this.dir;

    }

}
