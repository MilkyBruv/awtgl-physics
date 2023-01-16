package org.awtgl.vectors;

public class VMath {
    
    public static void rotateVector(Vector2 vec, Vector2 cent, float angle) {

        Vector2 v = vec;
        Vector2 c = cent;
        float a = (float) Math.toRadians(angle);

        Vector2 newVec = new Vector2(

            (int) Math.round(((v.x - c.x) * Math.cos(a)) - ((c.y - v.y) * Math.sin(a)) + c.x),
            (int) Math.round(((c.y - v.y) * Math.cos(a)) + ((v.x - c.x) * Math.sin(a)) + c.y)

        );

        vec.x = newVec.x;
        vec.y = newVec.y;

    }



    public static boolean compare(float x, float y, float epsilon) {

        return Math.abs(x - y) <= epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));

    }



    public static boolean compare(Vector2 vec1, Vector2 vec2, float epsilon) {

        return compare((float) vec1.x, (float) vec2.x, epsilon) && compare((float) vec1.x, (float) vec2.y, epsilon);

    }



    public static boolean compare(float x, float y) {

        return Math.abs(x - y) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));

    }



    public static boolean compare(Vector2 vec1, Vector2 vec2) {

        return compare((float) vec1.x, (float) vec2.x) && compare((float) vec1.x, (float) vec2.y);

    }

}
