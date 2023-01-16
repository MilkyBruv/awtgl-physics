package org.awtgl.physics.forces;

import org.awtgl.physics.rigidbody.Rigidbody;

public interface ForceGenerator {
    
    void updateForce(Rigidbody rigidbody, float dt);

}
