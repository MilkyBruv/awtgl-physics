package org.awtgl.vectors;

public class Vector2 {
    
    public float x;
    public float y;

    public Vector2() {

        //

    }



    public Vector2(float x, float y) {

        this.x = x;
        this.y = y;

    }



    public Vector2(Vector2 vec) {

        this.x = vec.x;
        this.y = vec.y;

    }



    public void zero() {

        this.x = 0;
        this.y = 0;

    }



    public void set(float x, float y) {

        this.x = x;
        this.y = y;

    }



    public void set(Vector2 vec) {

        this.x = vec.x;
        this.y = vec.y;

    }



    public float length() {

        return (float) Math.sqrt(this.lengthSquared());

    }



    public Vector2 normalize(Vector2 dest) {

        float l = this.length();

        if (dest == null) {

            dest = new Vector2(this.x / l, this.y / l);

        } else {

            dest.set(x / l, y / l);

        }

        return dest;

    }



    public float dot(Vector2 vec) {

        return this.x * vec.x + this.y * vec.y;

    }



    public float lengthSquared() {

        return this.x * this.x + this.y * this.y;

    }



    public Vector2 sub(Vector2 vec) {

        return new Vector2(this.x - vec.x, this.y - vec.y);

    }



    public Vector2 add(Vector2 vec) {

        return new Vector2(this.x + vec.x, this.y + vec.y);

    }



    public Vector2 mul(Vector2 vec) {

        return new Vector2(this.x * vec.x, this.y * vec.y);

    }



    public Vector2 div(Vector2 vec) {

        return new Vector2(this.x / vec.x, this.y / vec.y);

    }



    public Vector2 sub(int val) {

        return new Vector2(this.x - val, this.y - val);

    }



    public Vector2 add(int val) {

        return new Vector2(this.x + val, this.y + val);

    }



    public Vector2 mul(int val) {

        return new Vector2(this.x * val, this.y * val);

    }



    public Vector2 div(int val) {

        return new Vector2(this.x / val, this.y / val);

    }



    public Vector2 sub(float val) {

        return new Vector2(Math.round(this.x - val), Math.round(this.y - val));

    }



    public Vector2 add(float val) {

        return new Vector2(Math.round(this.x + val), Math.round(this.y + val));

    }



    public Vector2 mul(float val) {

        return new Vector2(Math.round(this.x * val), Math.round(this.y * val));

    }



    public Vector2 div(float val) {

        return new Vector2(Math.round(this.x / val), Math.round(this.y / val));

    }



    public Vector2 sub(double val) {

        return new Vector2((int) Math.round(this.x - val), (int) Math.round(this.y - val));

    }



    public Vector2 add(double val) {

        return new Vector2((int) Math.round(this.x + val), (int) Math.round(this.y + val));

    }



    public Vector2 mul(double val) {

        return new Vector2((int) Math.round(this.x * val), (int) Math.round(this.y * val));

    }



    public Vector2 div(double val) {

        return new Vector2((int) Math.round(this.x / val), (int) Math.round(this.y / val));

    }



    public Vector2 sub(long val) {

        return new Vector2((int) Math.round(this.x - val), (int) Math.round(this.y - val));

    }



    public Vector2 add(long val) {

        return new Vector2((int) Math.round(this.x + val), (int) Math.round(this.y + val));

    }



    public Vector2 mul(long val) {

        return new Vector2((int) Math.round(this.x * val), (int) Math.round(this.y * val));

    }



    public Vector2 div(long val) {

        return new Vector2((int) Math.round(this.x / val), (int) Math.round(this.y / val));

    }

}
