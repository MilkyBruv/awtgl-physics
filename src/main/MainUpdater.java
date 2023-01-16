package main;

import java.io.IOException;

import org.awtgl.physics.PhysicsSystem;
import org.awtgl.physics.primitives.Box;
import org.awtgl.physics.primitives.Circle;
import org.awtgl.physics.rigidbody.IntersectionDetector;
import org.awtgl.physics.rigidbody.Rigidbody;
import org.awtgl.physics.vectors.Line;
import org.awtgl.physics.vectors.Vector2;
import org.awtgl.window.GameSettings;
import org.awtgl.window.Image;
import org.awtgl.window.KeyHandler;
import org.awtgl.window.Keys;
import org.awtgl.window.Renderer;
import org.awtgl.window.Updater;

public class MainUpdater extends Updater {

    private Line l1;
    private Vector2 p1;
    private Circle c1;
    private Box b1;
    private Rigidbody rb1;
    private Rigidbody rb2;

    private PhysicsSystem ps;

    private Image pImage;
    private boolean moved;
    private float speed;

    public MainUpdater(GameSettings settings, Renderer renderer) {

        super(settings, renderer);

        try {

            this.pImage = new Image(System.getProperty("user.dir") + "\\src\\res\\i.png");

        } catch (IOException e) {
            
            e.printStackTrace();

        }

        this.moved = false;
        this.speed = 1f;

        this.ps = new PhysicsSystem(1.0f / 60.f, new Vector2(0, -10));

        this.rb1 = new Rigidbody();
        this.rb1.setTransform(new Vector2(20, 20));
        this.rb1.setMass(100.0f);

        this.rb2 = new Rigidbody();
        this.rb2.setTransform(new Vector2(50, 20));
        this.rb2.setMass(200.0f);

        this.ps.addRigidbody(rb1);
        this.ps.addRigidbody(rb2);

    }
    


    @Override
    public void update(float dt) {

        if (KeyHandler.isKeyPressed(Keys.LEFT) && !this.moved) {

            this.p1 = new Vector2(this.p1.x - this.speed, this.p1.y);
            this.moved = true;

        }

        if (KeyHandler.isKeyPressed(Keys.RIGHT) && !this.moved) {

            this.p1 = new Vector2(this.p1.x + this.speed, this.p1.y);
            this.moved = true;

        }

        if (KeyHandler.isKeyPressed(Keys.UP) && !this.moved) {

            this.p1 = new Vector2(this.p1.x, this.p1.y - this.speed);
            this.moved = true;

        }

        if (KeyHandler.isKeyPressed(Keys.DOWN) && !this.moved) {

            this.p1 = new Vector2(this.p1.x, this.p1.y + this.speed);
            this.moved = true;

        }

        if (
            
            !KeyHandler.isKeyPressed(Keys.LEFT) && 
            !KeyHandler.isKeyPressed(Keys.RIGHT) && 
            !KeyHandler.isKeyPressed(Keys.UP) && 
            !KeyHandler.isKeyPressed(Keys.DOWN)
        
        ) {

            this.moved = false;

        }

        this.ps.update(dt);

    }



    @Override
    public void draw(Image display) {

        // Renderer.drawLine(this.l1.getStart(), this.l1.getEnd(), 0xffffff, display);
        // Renderer.drawImage(this.pImage, this.p1, 1, 1, 0, display);
        // Renderer.drawCircle(new Vector2(10, 10), 10, 0xefefef, display);
        // Renderer.drawBox(b1, 0xffffff, display);

        Renderer.drawRB(this.rb1, new Vector2(10, 10), 0xffffff, display);
        Renderer.drawRB(this.rb2, new Vector2(10, 10), 0xffffff, display);

        System.out.println(this.rb1.getPos().y);

    }

}
