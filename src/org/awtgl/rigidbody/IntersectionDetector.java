package org.awtgl.rigidbody;

import org.awtgl.primitives.AABB;
import org.awtgl.primitives.Box;
import org.awtgl.primitives.Circle;
import org.awtgl.vectors.Line;
import org.awtgl.vectors.VMath;
import org.awtgl.vectors.Vector2;

public class IntersectionDetector {
    
    public static boolean pointOnLine(Vector2 point, Line line) {

        float dx = line.getEnd().x - line.getStart().x;
        float dy = line.getEnd().y - line.getStart().y;

        if (dx == 0) {

            return VMath.compare(point.x, line.getStart().x);

        }

        float m = dy / dx;
        float c = line.getEnd().y - (m * line.getEnd().x);

        // Check line equation (y = mx + c)
        return point.y == m * point.x + c;

    }



    public static boolean pointInCircle(Vector2 point, Circle circle) {

        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToPoint = new Vector2(point).sub(circleCenter);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius();

    }



    public static boolean pointInAABB(Vector2 point, AABB box) {

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return point.x <= max.x && min.x <= point.x && 
                point.y <= max.y && min.y <= point.y;

    }



    public static boolean pointInBox(Vector2 point, Box box) {

        Vector2 localBoxPoint = new Vector2(point);
        VMath.rotateVector(localBoxPoint, box.getRigidbody().getPos(), box.getRigidbody().getRot());

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return localBoxPoint.x <= max.x && min.x <= localBoxPoint.x && 
                localBoxPoint.y <= max.y && min.y <= localBoxPoint.y;

    }



    public static boolean lineHitsCircle(Line line, Circle circle) {

        if (pointInCircle(line.getStart(), circle) || pointInCircle(line.getEnd(), circle)) {

            return true;

        }
        
        Vector2 ab = new Vector2(line.getEnd()).sub(line.getStart());

        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToLineStart = new Vector2(circleCenter).sub(line.getStart());
        float t = centerToLineStart.dot(ab) / ab.dot(ab);

        if (t < 0.0f || t > 1.0f) {

            return false;

        }

        Vector2 closestPoint = new Vector2(line.getStart()).add(ab.mul(t));

        return pointInCircle(closestPoint, circle);

    }



    public static boolean lineHitsAABB(Line line, AABB box) {

        if (pointInAABB(line.getStart(), box) || pointInAABB(line.getEnd(), box)) {

            return true;

        }

        Vector2 unitVector = new Vector2(line.getEnd()).sub(line.getStart());
        unitVector.normalize(unitVector);
        unitVector.x = (unitVector.x != 0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f / unitVector.y : 0f;

        Vector2 min = box.getMax();
        min.sub(line.getStart()).mul(unitVector);
        Vector2 max = box.getMax();
        max.sub(line.getStart()).mul(unitVector);

        float tmin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tmax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));

        if (tmax < 0 || tmin > tmax) {

            return false;

        }

        float t = (tmin < 0f) ? tmax : tmin;

        return t > 0f && t * t < line.lengthSquared();

    }



    public static boolean lineHitsBox(Line line, Box box) {

        float theta = -box.getRigidbody().getRot();
        Vector2 center = box.getRigidbody().getPos();
        Vector2 localStart = new Vector2(line.getStart());
        Vector2 localEnd = new Vector2(line.getEnd());

        VMath.rotateVector(localStart, center, theta);
        VMath.rotateVector(localEnd, center, theta);

        Line localLine = new Line(localStart, localEnd);
        AABB aabb = new AABB(box.getMin(), box.getMax());

        return lineHitsAABB(localLine, aabb);

    }

}
