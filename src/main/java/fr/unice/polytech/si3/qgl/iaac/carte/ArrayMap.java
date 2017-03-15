package fr.unice.polytech.si3.qgl.iaac.carte;



import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.ground.GroundStrategy;
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

    private int xMax;
    private int xMin;
    private int yMax;
    private int yMin;


    private int size;

    private static final Logger logger = LogManager.getLogger(ArrayMap.class);

    public ArrayMap(Map<Point, Case> map) {
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

    //todo crash si carte au bord de la carte
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

    private void defineMapSize() {
        for (Map.Entry<Point, Boolean> tile: edge.entrySet()) {
            if (tile.getKey().x < xMin)
                xMin = tile.getKey().x;
            if (tile.getKey().x > xMax)
                xMax = tile.getKey().y;
            if (tile.getKey().y > yMax)
                yMax = tile.getKey().y;
            if (tile.getKey().y < yMin)
                yMin = tile.getKey().y;
        }
    }

    private void computeEdge() {

        for (Map.Entry<Point, Case> tile: map.entrySet())
            if (!tile.getValue().containOcean())
                edge.put(tile.getValue().getCoords() ,isAnEdge(tile.getValue().getCoords()));

        for (Map.Entry<Point, Boolean> tile: edge.entrySet())
            logger.info(tile.getKey());

        defineMapSize();

        for (Map.Entry<Point, Boolean> tile: edge.entrySet()) {
            if (left(edge, tile.getKey()) && !right(edge, tile.getKey())) {
                // droite + 3
                Point point = new Point(tile.getKey().x + 3, tile.getKey().y);
                // on passe sur toute la ligne
                if (hasEdgeMinusY(tile.getKey())) {
                    for (int i = yMax; i >= yMin ; i--) {
                        //si on trouve on relie
                        if (edge.get(new Point(point.x, i))) {
                            connect(new Point(point.x, i), point);
                        }
                        //si on trouve on relie
                    }
                }
                else {
                    for (int i = yMin; i <= yMax ; i++) {
                        //si on trouve on relie
                        if (edge.get(new Point(point.x, i))) {
                            connect(new Point(point.x, i), point);
                        }

                    }
                }


            }
            else if (right(edge, tile.getKey()) && !left(edge, tile.getKey())) {

            }
            else if (up(edge, tile.getKey()) && !down(edge, tile.getKey())) {

            }
            else if (down(edge, tile.getKey()) && !up(edge, tile.getKey())){

            }
        }

    }

    private void connect(Point point, Point point1) {
    }

    private boolean hasEdgeMinusY(Point key) {
        for (int i = key.y -1 ; i >= yMin ; i--) {
            if (edge.get(new Point(key.x,i))) {
                return true;
            }
        }
        return false;
    }

    private boolean up(Map<Point, Boolean> edge, Point key) {
        return edge.get(new Point(key.x, key.y + 1));
    }

    private boolean down(Map<Point, Boolean> edge, Point key) {
        return edge.get(new Point(key.x, key.y - 1));
    }

    private boolean left(Map<Point, Boolean> edge, Point key) {
        return edge.get(new Point(key.x - 1, key.y));
    }

    private boolean right(Map<Point, Boolean> edge, Point key) {
        return edge.get(new Point(key.x + 1, key.y));
    }

    public boolean isEdge(Point point) {
        return edge.getOrDefault(point, false);
    }


    public String strto() {
        StringBuilder str = new StringBuilder();
        str.append("\n");
        for (int i = 0; i < 100; i++) {
            for (int j = -125; j < 0; j++) {
                if (isEdge(new Point(i,j)))
                    str.append("1");
                else
                    str.append("0");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public boolean isEdgeG(Point point) {
        Case tile =  map.get(point);
        if (tile == null)
            return false;
        if (tile.containOcean())
            return true;
        return false;

    }
}
