package org.awtgl.rigidbody;

import org.awtgl.vectors.Vector2;

public class Rigidbody {
    
    private Vector2 pos = new Vector2();
    private float rot = 0.0f;

    public Vector2 getPos() {
        return pos;
    }



    public void setPos(Vector2 pos) {
        this.pos = pos;
    }



    public float getRot() {
        return rot;
    }



    public void setRot(float rot) {
        this.rot = rot;
    }

}
