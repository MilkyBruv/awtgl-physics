package org.awtgl.physics.forces;

import java.util.ArrayList;
import java.util.List;

import org.awtgl.physics.rigidbody.Rigidbody;

public class ForceRegistry {
    
    private List<ForceRegistration> registry;

    public ForceRegistry() {

        this.registry = new ArrayList<>();

    }



    public void add(Rigidbody rigidbody, ForceGenerator forceGenerator) {

        ForceRegistration forceRegistration = new ForceRegistration(forceGenerator, rigidbody);
        registry.add(forceRegistration);

    }



    public void remove(Rigidbody rigidbody, ForceGenerator forceGenerator) {

        ForceRegistration forceRegistration = new ForceRegistration(forceGenerator, rigidbody);
        registry.remove(forceRegistration);

    }



    public void clear() {

        registry.clear();

    }



    public void updateForces(float dt) {

        for (ForceRegistration forceRegistration : registry) {
            
            forceRegistration.forceGenerator.updateForce(forceRegistration.rigidbody, dt);

        }

    }



    public void zeroForces() {

        for (ForceRegistration forceRegistration : registry) {
            
            // forceRegistration.rigidbody.zeroForces();

        }

    }

}
