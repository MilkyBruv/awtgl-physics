package main;

import java.io.IOException;

import org.awtgl.primitives.Circle;
import org.awtgl.rigidbody.IntersectionDetector;
import org.awtgl.vectors.Line;
import org.awtgl.vectors.Vector2;
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

        this.l1 = new Line(new Vector2(0, 0), new Vector2(10, 15));
        this.p1 = new Vector2(6, 6);
        this.c1 = new Circle(5f);

    }
    


    @Override
    public void update() {

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

    }



    @Override
    public void draw(Image display) {

        Renderer.drawLine(this.l1.getStart(), this.l1.getEnd(), 0xffffff, display);
        Renderer.drawImage(this.pImage, this.p1, 1, 1, 0, display);
        Renderer.drawCircle(new Vector2(10, 10), 10, 0xefefef, display);

    }

}
