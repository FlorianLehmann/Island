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

        /*if (current != null && !current.containOcean())
            return true;*/

        if (up != null && !up.containOcean() && down != null && !down.containOcean() && current == null)
            return true;

        if (left != null && !left.containOcean() && right != null && !right.containOcean() && current == null)
            return true;

        return false;


    }

    private void defineMapSize() {
        /*for (Map.Entry<Point, Boolean> tile: edge.entrySet()) {
            if (tile.getKey().x < xMin)
                xMin = tile.getKey().x;
            if (tile.getKey().x > xMax)
                xMax = tile.getKey().y;
            if (tile.getKey().y > yMax)
                yMax = tile.getKey().y;
            if (tile.getKey().y < yMin)
                yMin = tile.getKey().y;
        }*/

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


        /*logger.info("Xmin :" + xMin + "Xmax " + xMax);
        for (int i = xMin; i <= xMax ; i++) {
            for (int j = yMin; j < yMax ; j++) {
                edge.put(new Point(i,j) ,isGround(new Point(i,j)));
                logger.info("test :" + isGround(new Point(i,j)));
            }
        }*/

        /*for (int i = xMin; i <= xMax ; i++) {
            for (int j = yMin; j < yMax ; j++) {
                edge.put(new Point(i,j) ,isGround(new Point(i,j)));
            }
        }*/

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

        //for (Map.Entry<Point, Boolean> tile: edge.entrySet())
//            logger.info(strto());



        /*for (Map.Entry<Point, Boolean> tile: edge.entrySet()) {
            if (left(edge, tile.getKey()) && !right(edge, tile.getKey())) {
                // droite + 3
                Point point = new Point(tile.getKey().x + 4, tile.getKey().y);
                // on passe sur toute la ligne
                if (hasEdgeMinusY(tile.getKey())) {

                    for (int i = yMax; i >= yMin ; i--) {
                        //si on trouve on relie

                        if (edge.getOrDefault(new Point(point.x, i), false)) {
                            //logger.info("OKOKOKOKOKOKOKOK");
                            logger.info("connect " + tile.getKey() + "to " + new Point(point.x, i));

                            connectX(new Point(point.x, i), tile.getKey());//TODO avant point.x au lieu de get.x
                            //TODO METTRE UN BREAK
                            //TODO METTRE UN BREAK
                            //TODO METTRE UN BREAK
                            //TODO METTRE UN BREAK
                            //TODO METTRE UN BREAK
                        }
                        //si on trouve on relie
                    }
                }
                else {


                    for (int i = yMin; i <= yMax ; i++) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(point.x, i), false)) {

                            connectX(new Point(point.x, i), tile.getKey());
                        }

                    }
                }


            }
            else if (right(edge, tile.getKey()) && !left(edge, tile.getKey())) {
                Point point = new Point(tile.getKey().x - 4, tile.getKey().y);

                if (hasEdgeMinusY(tile.getKey())) {
                    for (int i = yMax; i >= yMin ; i--) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(point.x, i), false)) {

                            connectX(new Point(point.x, i), tile.getKey());
                        }
                        //si on trouve on relie
                    }
                }
                else {
                    for (int i = yMin; i <= yMax ; i++) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(point.x, i), false)) {

                            connectX(new Point(point.x, i), tile.getKey());
                        }

                    }
                }
            }
            else if (up(edge, tile.getKey()) && !down(edge, tile.getKey())) {
                Point point = new Point(tile.getKey().x , tile.getKey().y - 4 );//todo

                if (hasEdgeMinusX(tile.getKey())) {
                    for (int i = xMax; i >= xMin ; i--) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(i, point.y), false)) {

                            connectY(new Point(i, point.y), tile.getKey());
                        }
                        //si on trouve on relie
                    }
                }
                else {
                    for (int i = xMin; i <= xMax ; i++) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(i, point.y), false)) {

                            connectY(new Point(i, point.y), tile.getKey());
                        }
                    }
                }
            }
            else if (down(edge, tile.getKey()) && !up(edge, tile.getKey())){//todo
                Point point = new Point(tile.getKey().x , tile.getKey().y + 4 );//todo

                if (hasEdgeMinusX(tile.getKey())) {
                    for (int i = xMax; i >= xMin ; i--) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(i, point.y), false)) {

                            connectY(new Point(i, point.y), tile.getKey());
                        }
                        //si on trouve on relie
                    }
                }
                else {
                    for (int i = xMin; i <= xMax ; i++) {
                        //si on trouve on relie
                        if (edge.getOrDefault(new Point(i, point.y), false)) {

                            connectY(new Point(i, point.y), tile.getKey());
                        }
                    }
                }


            }
        }*/
        logger.info(strto());

        /*for (Map.Entry<Point, Boolean> tile: edge_tmp.entrySet()) {
            edge.put(tile.getKey(), true);
        }*/

//        logger.info(strto());

    }

    private boolean hasEdgeMinusX(Point key) { //todo
        for (int i = key.x -1 ; i >= xMin ; i--) {
            if (edge.getOrDefault(new Point(i,key.y), false)) {
                return true;
            }
        }
        return false;
    }

    private void connectX(Point point1, Point point) {
        //équation de la droite
        //vecteur
        int a = point.x - point1.x;
        int b = point.y - point1.y;


        int c = -a*point.x + b * point.y;

        if (point.x < point1.x) {
            //logger.info("TESTTEST");
            for (int x = point.x; x < point1.x; x++) {
                //logger.info("TESTTEST");
                Point p = new Point(x, b==0?point.y:point.y);
                edge_tmp.put(p, true);
            }
        }
        else  {//todo
            //logger.info("TESTTEST");
            for (int x = point1.x; x < point.x; x++) {
                //logger.info("TESTTEST");
                Point p = new Point(x, b==0?point.y:point.y);//((int) ((a * x + c) / b)));
                edge_tmp.put(p, true);
            }
        }

        //a*x - b*y +c =0
        //

    }

    private void connectY(Point point1, Point point) {
        //équation de la droite
        //vecteur
        int a = point.x - point1.x;
        int b = point.y - point1.y;
        int c = -a*point.x + b * point.y;

        if (point.y < point1.y) {

            for (int y = point.y; y < point1.y; y++) {
                Point p = new Point(point.x/*(int) ((b*y-c)/a)*/, y);
                edge_tmp.put(p, true);
            }
        }
        else {
            for (int y = point1.y; y < point.y; y++) {
                Point p = new Point(point.x/*(int) ((b*y-c)/a)*/, y);
                edge_tmp.put(p, true);
            }
        }
        //A*x - b*y + c =0
        //(b*y-c)/a
    }

    private boolean hasEdgeMinusY(Point key) {
        for (int i = key.y -1 ; i >= yMin ; i--) {
            if (edge.getOrDefault(new Point(key.x,i), false)) {
                return true;
            }
        }
        return false;
    }

    private boolean up(Map<Point, Boolean> edge, Point key) {
        return edge.getOrDefault(new Point(key.x, key.y + 1),false);
    }

    private boolean down(Map<Point, Boolean> edge, Point key) {
        return edge.getOrDefault(new Point(key.x, key.y - 1),false);
    }

    private boolean left(Map<Point, Boolean> edge, Point key) {
        return edge.getOrDefault(new Point(key.x - 1, key.y),false);
    }

    private boolean right(Map<Point, Boolean> edge, Point key) {
        return edge.getOrDefault(new Point(key.x + 1, key.y),false);
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
        /*Case tile =  map.get(point);
        if (tile == null)
            return false;
        if (tile.containOcean())
            return true;
        return false;*/

    }
}
