package org.awtgl.physics.forces;

import org.awtgl.physics.rigidbody.Rigidbody;
import org.awtgl.physics.vectors.Vector2;

public class Gravity implements ForceGenerator {

    private Vector2 gravity;

    public Gravity(Vector2 force) {

        this.gravity = new Vector2(force);

    }

    @Override
    public void updateForce(Rigidbody rigidbody, float dt) {

        rigidbody.addForce(new Vector2(gravity).mul(rigidbody.getMass()));
        
    }
    
}
