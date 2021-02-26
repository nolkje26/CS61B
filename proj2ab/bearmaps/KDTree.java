package bearmaps;
import java.util.List;

public class KDTree {
    private static final boolean vertical = true;

    private class KNode {
        public Point point;
        public KNode leftChild;
        public KNode rightChild;
        public boolean orientation;

        private KNode(Point point, Boolean orientation) {
            this.point = point;
            this.leftChild = null;
            this.rightChild = null;
            this.orientation = orientation;
        }
    }

    public KNode root;

    // Let (x, y) be represented by (0, 1)
    public KDTree(List<Point> points) {
         for (Point point : points){
             this.root = insert(this.root, point, vertical);
         }
    }

    public KNode insert(KNode node, Point point, boolean orienation) {
        if (node == null) {
            return new KNode(point, orienation);
        } else if (node.orientation) {
            if (point.getX() < node.point.getX()) {
                node.leftChild = insert(node.leftChild, point, !node.orientation);
            } else {
                node.rightChild = insert(node.rightChild, point, !node.orientation);
            }
        } else if (!node.orientation) {
            if (point.getY() < node.point.getY()) {
                node.leftChild = insert(node.leftChild, point, !node.orientation);
            } else {
                node.rightChild = insert(node.rightChild, point, !node.orientation);
            }
        }
        return node;
    }

    private KNode nearestHelper(KNode node, Point point, KNode best) {
        if (node == null) { return best; }
        if (Point.distance(point, node.point) < Point.distance(point, best.point)) {
            best = node;
        }

        KNode goodSide;
        KNode badSide;

        if (node.orientation) {
            if (point.getX() < node.point.getX()) {
                goodSide = node.leftChild;
                badSide = node.rightChild;
            } else {
                goodSide = node.rightChild;
                badSide = node.leftChild;
            }
        } else {
            if (point.getY() < node.point.getY()) {
                goodSide = node.leftChild;
                badSide = node.rightChild;
            } else {
                goodSide = node.rightChild;
                badSide = node.leftChild;
            }
        }

        best = nearestHelper(goodSide, point, best);

        if (node.orientation && (node.point.getX() - point.getX() < Point.distance(point, best.point))) {
            best = nearestHelper(badSide, point, best);
        } else if (!node.orientation && (node.point.getY() - point.getY() < Point.distance(point, best.point))) {
            best = nearestHelper(badSide, point, best);
        }
        return best;
    }

    public Point nearest(double x, double y) {
        KNode best = nearestHelper(this.root, new Point(x, y), this.root);
        return best.point;
    }
}
