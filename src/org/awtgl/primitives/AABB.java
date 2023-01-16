package org.awtgl.primitives;

import org.awtgl.rigidbody.Rigidbody;
import org.awtgl.vectors.Vector2;

public class AABB {

    private Vector2 size = new Vector2();
    private Vector2 halfSize;
    private Rigidbody rigidbody = null;

    public AABB() {

        this.halfSize = new Vector2(this.size.div(2));

    }



    public AABB(Vector2 min, Vector2 max) {

        this.size = new Vector2(max).sub(min);
        this.halfSize = new Vector2(this.size.div(2));

    }



    public Vector2 getMin() {

        return new Vector2(this.rigidbody.getPos()).sub(this.halfSize);

    }



    public Vector2 getMax() {

        return new Vector2(this.rigidbody.getPos()).add(this.halfSize);

    }

}
