package fr.unice.polytech.si3.qgl.iaac.map;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMap {

    /**
     * attributes
     */


    private Map<Point, Case> map;
    private Map<Point, Boolean> edge;
    private List<Point> listeY;
    Map<Point, Boolean> edge_tmp;


    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;


    private int size;

    private static final Logger logger = LogManager.getLogger(ArrayMap.class);

    public ArrayMap(Map<Point, Case> map) {
        edge_tmp = new HashMap<>();
        this.map = map;
        edge = new HashMap<>();
        listeY = new LinkedList<>();
        xMax = Integer.MIN_VALUE;
        xMin = Integer.MAX_VALUE;
        yMax = Integer.MIN_VALUE;
        yMin = Integer.MAX_VALUE;
        computeEdge();
    }

    public Case get(Point point) {
        return map.get(point);
    }

    //todo crash si map au bord de la map
    public boolean isAnEdge(Point point) {


        Case left = map.get(new Point(point.x - 1, point.y));
        Case right = map.get(new Point(point.x + 1, point.y));
        Case current = map.get(point);

        if (left != null && right != null)
            if ((left.containOcean() || right.containOcean()))
                return true;

        Case up = map.get(new Point(point.x , point.y + 1));
        Case down = map.get(new Point(point.x , point.y - 1));

        if (up != null && down != null)
            if ((up.containOcean() || down.containOcean()))
                return true;



        return false;

    }

    public boolean isGround(Point point) {

        Case current = map.get(point);
        Case up = map.get(new Point(point.x, point.y + 3));
        Case down = map.get(new Point(point.x, point.y - 3));

        if (current != null && !current.containOcean())
            return true;

        if (up != null && !up.containOcean() && down != null && !down.containOcean())
            return true;

        return false;


    }

    public boolean isGround2(Point point) {

        Case current = map.get(point);
        Case up = map.get(new Point(point.x , point.y +3 ));
        Case down = map.get(new Point(point.x , point.y-3 ));
        Case left = map.get(new Point(point.x + 3, point.y ));
        Case right = map.get(new Point(point.x - 3, point.y ));

        if (up != null && !up.containOcean() && down != null && !down.containOcean() && current == null)
            return true;

        if (left != null && !left.containOcean() && right != null && !right.containOcean() && current == null)
            return true;

        return false;


    }

    private void defineMapSize() {

        for (Point point1 : edge.keySet()) {
            Point point = new Point(point1);
            if (point.x < xMin)
                xMin = point.x;
            if (point.x > xMax)
                xMax = point.x;
            if (point.y > yMax)
                yMax = point.y;
            if (point.y < yMin)
                yMin = point.y;
        }
    }

    private void computeEdge() {

        for (Map.Entry<Point, Case> tile: map.entrySet()) {
            if (!tile.getValue().containOcean())
                edge.put(tile.getValue().getCoords(), true);
        }

        defineMapSize();

        logger.info("Xmin :" + xMin + "Xmax " + xMax);
        for (int i = xMin; i <= xMax ; i++) {
            for (int j = yMin; j < yMax ; j++) {
                edge_tmp.put(new Point(i,j) ,isGround2(new Point(i,j)));
                logger.info("test :" + isGround2(new Point(i,j)));
            }
        }


        logger.info(strto());


    }

    public boolean isEdge(Point point) {
        return edge.getOrDefault(point, false);
    }


    public String strto() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        for (int i = xMin; i < xMax ; i++) {
            for (int j = yMin; j < yMax + 30 ; j++) {
                if (edge.getOrDefault(new Point(i,j), false) || edge_tmp.getOrDefault(new Point(i,j), false) )
                    str.append("1");
                else
                    str.append("0");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public boolean isEdgeG(Point point) {
        return !(edge.getOrDefault(point, false) || edge_tmp.getOrDefault(point, false));
    }
}
