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


    private int size;

    private static final Logger logger = LogManager.getLogger(ArrayMap.class);

    public ArrayMap(Map<Point, Case> map) {
        this.map = map;
        edge = new HashMap<>();
        listeY = new LinkedList<>();
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

    private void computeEdge() {

        for (Map.Entry<Point, Case> tile: map.entrySet())
            if (!tile.getValue().containOcean())
                edge.put(tile.getValue().getCoords() ,isAnEdge(tile.getValue().getCoords()));




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
