package fr.unice.polytech.si3.qgl.iaac.map;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMap {

    /**
     * attributes
     */
    private Map<Point, Case> map;
    private Map<Point, Boolean> edge;
    private Map<Point, Boolean> edge_tmp;
    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;

    /**
     * default constructor
     *
     * @param map
     */
    public ArrayMap(Map<Point, Case> map) {
        edge_tmp = new HashMap<>();
        this.map = map;
        edge = new HashMap<>();
        xMax = Integer.MIN_VALUE;
        xMin = Integer.MAX_VALUE;
        yMax = Integer.MIN_VALUE;
        yMin = Integer.MAX_VALUE;
        computeEdge();
    }

    public Case get(Point point) {
        return map.get(point);
    }

    public boolean isGround2(Point point) {

        Case current = map.get(point);
        Case up = map.get(new Point(point.x, point.y + 3));
        Case down = map.get(new Point(point.x, point.y - 3));
        Case left = map.get(new Point(point.x + 3, point.y));
        Case right = map.get(new Point(point.x - 3, point.y));

        if (current == null) {

            if (containNoOcean(up) && containNoOcean(down))
                return true;

            if (containNoOcean(left) && containNoOcean(right))
                return true;
        }

        return false;

    }

    private boolean containNoOcean(Case tile) {
        return tile != null && !tile.containOcean();
    }

    private void defineMapSize(Point point) {

        //for (Point point1 : edge.keySet()) {
        //Point point = new Point(point1);
        if (point.x < xMin)
            xMin = point.x;
        if (point.x > xMax)
            xMax = point.x;
        if (point.y > yMax)
            yMax = point.y;
        if (point.y < yMin)
            yMin = point.y;
        //}
    }

    private void computeEdge() {

        for (Map.Entry<Point, Case> tile : map.entrySet()) {
            if (!tile.getValue().containOcean() || tile.getValue().hasCreek()) {
                edge.put(tile.getValue().getCoords(), true);
                defineMapSize(tile.getKey());
            }
        }

        //defineMapSize();

        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j < yMax; j++) {
                edge_tmp.put(new Point(i, j), isGround2(new Point(i, j)));
            }
        }

    }

    public boolean isEdge(Point point) {
        return edge.getOrDefault(point, false);
    }

    public boolean isEdgeG(Point point) {
        return !(edge.getOrDefault(point, false) || edge_tmp.getOrDefault(point, false));
    }

    public Point getRandomTarget() {
        Random random;
        Point point;
        do {
            random = new Random();
            int xRandom = xMin + random.nextInt(xMax - xMin);
            random = new Random();
            int yRandom = yMin + random.nextInt(yMax - yMin);
            point = new Point(xRandom, yRandom);
        } while (isEdgeG(point));

        return point;
    }
}
