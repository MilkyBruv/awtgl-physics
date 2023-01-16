package org.awtgl.physics.primitives;

import org.awtgl.physics.vectors.Vector2;

public class RaycastResult {
    
    private Vector2 point;
    private Vector2 normal;
    private float t;
    private boolean hit;

    public RaycastResult() {

        this.point = new Vector2();
        this.normal = new Vector2();
        this.t = -1;
        this.hit = false;

    }



    public void init(Vector2 point, Vector2 normal, float t, boolean hit) {

        this.point.set(point);
        this.normal.set(normal);
        this.t = t;
        this.hit = hit;

    }



    public static void reset(RaycastResult result) {

        if (result != null) {

            result.point.zero();
            result.normal.set(0, 0);
            result.t = -1;
            result.hit = false;

        }

    }

}
