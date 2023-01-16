package org.awtgl.physics;

import java.util.ArrayList;
import java.util.List;

import org.awtgl.physics.forces.ForceRegistry;
import org.awtgl.physics.forces.Gravity;
import org.awtgl.physics.rigidbody.Rigidbody;
import org.awtgl.physics.vectors.Vector2;

public class PhysicsSystem {
    
    private ForceRegistry forceRegistry;
    private List<Rigidbody> rigidbodies;
    private Gravity gravity;
    private float fixedUpdate;

    public PhysicsSystem(float fixedUpdateDt, Vector2 gravity) {

        this.forceRegistry = new ForceRegistry();
        this.rigidbodies = new ArrayList<>();
        this.gravity = new Gravity(gravity);
        this.fixedUpdate = fixedUpdateDt;

    }



    public void update(float dt) {

        this.fixedUpdate();

    }



    public void fixedUpdate() {

        this.forceRegistry.updateForces(this.fixedUpdate);

        for (int i = 0; i < this.rigidbodies.size(); i++) {
            
            this.rigidbodies.get(i).physicsUpdate(this.fixedUpdate);

        }

    }



    public void addRigidbody(Rigidbody rigidbody) {

        this.rigidbodies.add(rigidbody);
        this.forceRegistry.add(rigidbody, this.gravity);

    }

}
