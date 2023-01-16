package org.awtgl.primitives;

import org.awtgl.rigidbody.Rigidbody;
import org.awtgl.vectors.VMath;
import org.awtgl.vectors.Vector2;

public class Box {

    private Vector2 size = new Vector2();
    private Vector2 halfSize;
    private Rigidbody rigidbody = null;

    public Box() {

        this.halfSize = new Vector2(this.size.div(2));

    }



    public Box(Vector2 min, Vector2 max) {

        this.size = new Vector2(max).sub(min);
        this.halfSize = new Vector2(this.size.div(2));

    }



    public Vector2 getMin() {

        return new Vector2(this.rigidbody.getPos()).sub(this.halfSize);

    }



    public Vector2 getMax() {

        return new Vector2(this.rigidbody.getPos()).add(this.halfSize);

    }



    public Vector2[] getVertices() {

        Vector2 min = this.getMin();
        Vector2 max = this.getMax();

        Vector2[] vertices = {

            new Vector2(min.x, min.y), new Vector2(min.x, min.y),
            new Vector2(max.x, max.y), new Vector2(max.x, max.y)

        };

        if (this.rigidbody.getRot() != 0.0f) {

            for (Vector2 vert : vertices) {

                VMath.rotateVector(vert, this.rigidbody.getPos(), this.rigidbody.getRot());

            }

        }

        return vertices;

    }



    public Rigidbody getRigidbody() {

        return this.rigidbody;

    }

}