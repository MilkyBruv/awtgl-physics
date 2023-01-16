package org.awtgl.physics.rigidbody;

import org.awtgl.physics.vectors.Vector2;

public class Rigidbody {
    
    private Vector2 pos = new Vector2();
    private float rot = 0.0f;

    private float mass = 0.0f;
    private float invMass = 0.0f;

    private Vector2 forceAccum = new Vector2();
    private Vector2 linearVelocity = new Vector2();
    private float angularVelocity = 0.0f;
    private float linearDamping = 0.0f;
    private float angularDamping = 0.0f;

    private boolean fixedRotation = false;

    public void physicsUpdate(float dt) {

        if (this.mass == 0.0f) {

            return;

        }

        Vector2 acc = new Vector2(this.forceAccum).mul(this.invMass);
        this.linearVelocity.add(acc.mul(dt));

        this.pos.add(new Vector2(linearVelocity).mul(dt));

        this.clearAccumulators();

    }



    public void clearAccumulators() {

        this.forceAccum.zero();

    }
    
    
    
    public Vector2 getPos() {

        return pos;

    }



    public float getRot() {

        return rot;
        
    }



    public void setTransform(Vector2 pos, float rot) {

        this.pos.set(pos);
        this.rot = rot;

    }



    public void setTransform(Vector2 pos) {

        this.pos.set(pos);

    }

    

    public float getMass() {

        return this.mass;

    }



    public void setMass(float mass) {

        this.mass = mass;

        if (this.mass != 0.0f) {

            this.invMass = 1.0f / this.mass;

        }

    }



    public void addForce(Vector2 force) {

        this.forceAccum.add(force);

    }

}
