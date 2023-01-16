package org.awtgl.physics.primitives;

import org.awtgl.physics.rigidbody.Rigidbody;
import org.awtgl.physics.vectors.VMath;
import org.awtgl.physics.vectors.Vector2;

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



    public Vector2 getLocalMin() {

        return new Vector2(this.rigidbody.getPos()).sub(this.halfSize);

    }



    public Vector2 getLocalMax() {

        return new Vector2(this.rigidbody.getPos()).add(this.halfSize);

    }



    public Vector2[] getVertices() {

        Vector2 min = this.getLocalMin();
        Vector2 max = this.getLocalMax();

        Vector2[] vertices = {

            new Vector2(min.x, min.y), new Vector2(min.x, max.y),
            new Vector2(max.x, min.y), new Vector2(max.x, max.y)

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



    public Vector2 getHalfSize() {

        return this.halfSize;

    }



    public void setRigidbody(Rigidbody rigidbody) {

        this.rigidbody = rigidbody;

    }



    public void setSize(Vector2 size) {

        this.size.set(size);
        this.halfSize.set(size.x / 2, size.y / 2);

    }

}