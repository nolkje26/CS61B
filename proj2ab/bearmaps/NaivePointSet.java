package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        double bestDist = Double.POSITIVE_INFINITY;
        Point bestPoint = null;
        for (Point point : points) {
            double distance = Math.sqrt(point.distance(x, point.getX(), y, point.getY()));
            if (distance < bestDist) {
                bestDist = distance;
                bestPoint = point;
            }
        }
        return bestPoint;
    }
}
