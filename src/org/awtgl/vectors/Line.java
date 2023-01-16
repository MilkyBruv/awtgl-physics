package org.awtgl.vectors;

public class Line {
    
    private Vector2 from;
    private Vector2 to;
    private int lifetime;

    public Line(Vector2 from, Vector2 to, int lifetime) {

        this.from = from;
        this.to = to;
        this.lifetime = lifetime;

    }



    public Line(Vector2 from, Vector2 to) {

        this.from = from;
        this.to = to;

    }



    public int beginFrame() {

        this.lifetime--;
        
        return this.lifetime;

    }



    public Vector2 getStart() {

        return this.from;

    }



    public Vector2 getEnd() {

        return this.to;

    }



    public float lengthSquared() {

        return new Vector2(this.to).sub(this.from).lengthSquared();

    }

}
