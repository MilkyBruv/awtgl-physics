package org.awtgl.physics.rigidbody;

import org.awtgl.physics.primitives.AABB;
import org.awtgl.physics.primitives.Box;
import org.awtgl.physics.primitives.Circle;
import org.awtgl.physics.primitives.Ray;
import org.awtgl.physics.primitives.RaycastResult;
import org.awtgl.physics.vectors.Line;
import org.awtgl.physics.vectors.VMath;
import org.awtgl.physics.vectors.Vector2;

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

    } public static boolean lineHitsPoint(Line line, Vector2 point) { return pointOnLine(point, line); }



    public static boolean pointInCircle(Vector2 point, Circle circle) {

        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToPoint = new Vector2(point).sub(circleCenter);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius();

    } public static boolean circleHitsPoint(Circle circle, Vector2 point) { return pointInCircle(point, circle); }



    public static boolean pointInAABB(Vector2 point, AABB box) {

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return point.x <= max.x && min.x <= point.x && 
                point.y <= max.y && min.y <= point.y;

    } public static boolean ABBHitsPoint(AABB box, Vector2 point) { return pointInAABB(point, box); }



    public static boolean pointInBox(Vector2 point, Box box) {

        Vector2 localBoxPoint = new Vector2(point);
        VMath.rotateVector(localBoxPoint, box.getRigidbody().getPos(), box.getRigidbody().getRot());

        Vector2 min = box.getLocalMin();
        Vector2 max = box.getLocalMax();

        return localBoxPoint.x <= max.x && min.x <= localBoxPoint.x && 
                localBoxPoint.y <= max.y && min.y <= localBoxPoint.y;

    } public static boolean boxHitsPoint(Box box, Vector2 point) { return pointInBox(point, box); }



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

    } public static boolean circleHitsLine() { return lineHitsCircle(null, null); }



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

    } public static boolean AABBHitsLine(AABB box, Line line) { return lineHitsAABB(line, box); }



    public static boolean lineHitsBox(Line line, Box box) {

        float theta = -box.getRigidbody().getRot();
        Vector2 center = box.getRigidbody().getPos();
        Vector2 localStart = new Vector2(line.getStart());
        Vector2 localEnd = new Vector2(line.getEnd());

        VMath.rotateVector(localStart, center, theta);
        VMath.rotateVector(localEnd, center, theta);

        Line localLine = new Line(localStart, localEnd);
        AABB aabb = new AABB(box.getLocalMin(), box.getLocalMax());

        return lineHitsAABB(localLine, aabb);

    } public static boolean boxHitsLine(Box box, Line line) { return lineHitsBox(line, box); }



    public static boolean raycastCircle(Circle circle, Ray ray, RaycastResult result) {

        RaycastResult.reset(result);

        Vector2 originToCircle = new Vector2(circle.getCenter()).sub(ray.getOrigin());
        float radiusSquared = circle.getRadius() * circle.getRadius();
        float originToCircleLengthSquared = originToCircle.lengthSquared();

        float a = originToCircle.dot(ray.getDir());
        float bSquared = originToCircleLengthSquared - (a * a);

        if (radiusSquared - bSquared < 0.0f) {

            return false;

        }

        float f = (float) Math.sqrt(radiusSquared - bSquared);
        float t = 0;

        if (originToCircleLengthSquared < radiusSquared) {

            t = a + f;

        } else {

            t = a - f;

        }

        if (result != null) {

            Vector2 point = new Vector2(ray.getOrigin()).add(new Vector2(ray.getDir()).mul(t));
            Vector2 normal = new Vector2(point).sub(circle.getCenter());
            normal.normalize();

            result.init(point, normal, t, true);

        }

        return true;

    }



    public static boolean raycastAABB(AABB box, Ray ray, RaycastResult result) {

        RaycastResult.reset(result);

        Vector2 unitVector = ray.getDir();
        unitVector.normalize();
        unitVector.x = (unitVector.x != 0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f / unitVector.y : 0f;

        Vector2 min = box.getMax();
        min.sub(ray.getOrigin()).mul(unitVector);
        Vector2 max = box.getMax();
        max.sub(ray.getOrigin()).mul(unitVector);

        float tmin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tmax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));

        if (tmax < 0 || tmin > tmax) {

            return false;

        }

        float t = (tmin < 0f) ? tmax : tmin;
        boolean hit = t > 0f; // && t * t < ray.getMaximum();

        if (!hit) {

            return false;

        }

        if (result != null) {

            Vector2 point = new Vector2(ray.getOrigin()).add(new Vector2(ray.getDir()).mul(t));
            Vector2 normal = new Vector2(ray.getOrigin()).sub(point);
            normal.normalize();

            result.init(point, normal, t, true);

        }

        return true;

    }



    public static boolean raycastBox(Box box, Ray ray, RaycastResult result) {

        RaycastResult.reset(result);

        Vector2 size = box.getHalfSize();
        Vector2 xAxis = new Vector2(1, 0);
        Vector2 yAxis = new Vector2(0, 1);
        VMath.rotateVector(xAxis, new Vector2(0, 0), -box.getRigidbody().getRot());
        VMath.rotateVector(yAxis, new Vector2(0, 0), -box.getRigidbody().getRot());

        Vector2 p = new Vector2(box.getRigidbody().getPos()).sub(ray.getOrigin());
        Vector2 f = new Vector2(xAxis.dot(ray.getDir()), yAxis.dot(ray.getDir()));
        Vector2 e = new Vector2(xAxis.dot(p), yAxis.dot(p));

        float[] tArr = {0, 0, 0, 0};

        for (int i = 0; i < 2; i++) {
            
            if (VMath.compare(f.get(i), 0)) {

                if (-e.get(i) - size.get(i) > 0 || -e.get(i) + size.get(i) < 0) {

                    return false;

                }

                f.set(0.00001f, f.y);

            }

            tArr[i * 2 + 0] = (e.get(i) + size.get(i)) / f.get(i);
            tArr[i * 2 + 1] = (e.get(i) - size.get(i)) / f.get(i);

        }

        float tmin = Math.max(Math.min(tArr[0], tArr[1]), Math.min(tArr[2], tArr[3]));
        float tmax = Math.min(Math.max(tArr[0], tArr[1]), Math.max(tArr[2], tArr[3]));

        float t = (tmin < 0f) ? tmax : tmin;
        boolean hit = t > 0f;

        if (!hit) {

            return false;

        }

        if (result != null) {

            Vector2 point = new Vector2(ray.getOrigin()).add(new Vector2(ray.getDir()).mul(t));
            Vector2 normal = new Vector2(ray.getOrigin()).sub(point);
            normal.normalize();

            result.init(point, normal, t, true);

        }

        return true;

    }



    public static boolean circleHitsCircle(Circle c1, Circle c2) {

        Vector2 vecBetweenCenters = new Vector2(c1.getCenter()).sub(c2.getCenter());
        float radiiSum = c1.getRadius() + c2.getRadius();

        return vecBetweenCenters.lengthSquared() <= radiiSum * radiiSum;

    }



    public static boolean circleHitsAABB(Circle circle, AABB box) {

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        Vector2 closestPointToCircle = new Vector2(circle.getCenter());

        if (closestPointToCircle.x < min.x) {

            closestPointToCircle.x = min.x;

        } else if (closestPointToCircle.x > max.x) {

            closestPointToCircle.x = max.x;

        }

        if (closestPointToCircle.y < min.y) {

            closestPointToCircle.y = min.y;

        } else if (closestPointToCircle.y > max.y) {

            closestPointToCircle.y = max.y;

        }

        Vector2 circleToBox = new Vector2(circle.getCenter()).sub(closestPointToCircle);

        return circleToBox.lengthSquared() <= circle.getRadius() * circle.getRadius();

    } public static boolean AABBHitsCircle(AABB box, Circle circle) { return circleHitsAABB(circle, box); }



    public static boolean circleHitsBox(Circle circle, Box box) {

        Vector2 min = new Vector2();
        Vector2 max = new Vector2(box.getHalfSize()).mul(2);

        Vector2 r = new Vector2(circle.getCenter()).sub(box.getRigidbody().getPos());
        VMath.rotateVector(r, new Vector2(0, 0), -box.getRigidbody().getRot());
        Vector2 localCirclePos = new Vector2(r).add(box.getHalfSize());

        Vector2 closestPointToCircle = new Vector2(localCirclePos);

        if (closestPointToCircle.x < min.x) {

            closestPointToCircle.x = min.x;

        } else if (closestPointToCircle.x > max.x) {

            closestPointToCircle.x = max.x;

        }

        if (closestPointToCircle.y < min.y) {

            closestPointToCircle.y = min.y;

        } else if (closestPointToCircle.y > max.y) {

            closestPointToCircle.y = max.y;

        }

        Vector2 circleToBox = new Vector2(localCirclePos).sub(closestPointToCircle);

        return circleToBox.lengthSquared() <= circle.getRadius() * circle.getRadius();

    } public static boolean boxHitsCircle(Box box, Circle circle) { return circleHitsBox(circle, box); }



    public static boolean AABBHitsAABB(AABB b1, AABB b2) {

        Vector2[] axesToTest = {new Vector2(0, 1), new Vector2(1, 0)};

        for (int i = 0; i < axesToTest.length; i++) {
            
            if (!overlapsOnAxis(b1, b2, axesToTest[i])) {

                return false;

            }

        }

        return true;

    }



    public static boolean AABBHitsBox(AABB b1, Box b2) {

        Vector2[] axesToTest = {
            
            new Vector2(0, 1), new Vector2(1, 0),
            new Vector2(0, 1), new Vector2(1, 0)
        
        };

        VMath.rotateVector(axesToTest[2], new Vector2(0, 0), b2.getRigidbody().getRot());
        VMath.rotateVector(axesToTest[3], new Vector2(0, 0), b2.getRigidbody().getRot());

        for (int i = 0; i < axesToTest.length; i++) {
            
            if (!overlapsOnAxis(b1, b2, axesToTest[i])) {

                return false;

            }

        }

        return true;

    }



    private static boolean overlapsOnAxis(AABB b1, AABB b2, Vector2 axis) {

        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);

        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));

    } private static boolean overlapsOnAxis(AABB b1, Box b2, Vector2 axis) {

        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);

        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));

    } private static boolean overlapsOnAxis(Box b1, AABB b2, Vector2 axis) {

        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);

        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));

    } private static boolean overlapsOnAxis(Box b1, Box b2, Vector2 axis) {

        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);

        return ((interval2.x <= interval1.y) && (interval1.x <= interval2.y));

    }



    private static Vector2 getInterval(AABB box, Vector2 axis) {

        Vector2 result = new Vector2(0, 0);

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        Vector2[] vertices = {

            new Vector2(min.x, min.y), new Vector2(min.x, max.y),
            new Vector2(max.x, min.y), new Vector2(max.x, max.y)

        };

        result.x = axis.dot(vertices[0]);
        result.y = result.x;

        for (int i = 0; i < 4; i++) {
            
            float proj = axis.dot(vertices[i]);

            if (proj < result.x) {

                result.x = proj;

            }

            if (proj > result.y) {

                result.y = proj;

            }

        }

        return result;

    } private static Vector2 getInterval(Box box, Vector2 axis) {

        Vector2 result = new Vector2(0, 0);

        Vector2[] vertices = box.getVertices();

        result.x = axis.dot(vertices[0]);
        result.y = result.x;

        for (int i = 0; i < 4; i++) {
            
            float proj = axis.dot(vertices[i]);

            if (proj < result.x) {

                result.x = proj;

            }

            if (proj > result.y) {

                result.y = proj;

            }

        }

        return result;

    }

}
