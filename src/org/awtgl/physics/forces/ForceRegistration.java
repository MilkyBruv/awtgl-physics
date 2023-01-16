package org.awtgl.physics.forces;

import org.awtgl.physics.rigidbody.Rigidbody;

public class ForceRegistration {
    
    public ForceGenerator forceGenerator;
    public Rigidbody rigidbody;

    public ForceRegistration(ForceGenerator forceGenerator, Rigidbody rigidbody) {

        this.forceGenerator = forceGenerator;
        this.rigidbody = rigidbody;

    }



    @Override
    public boolean equals(Object other) {

        if (other == null) {

            return false;

        }

        if (other.getClass() != ForceRegistration.class) {

            return false;

        }

        ForceRegistration forceRegistration = (ForceRegistration) other;

        return forceRegistration.rigidbody == this.rigidbody && forceRegistration.forceGenerator == this.forceGenerator;

    }

}
