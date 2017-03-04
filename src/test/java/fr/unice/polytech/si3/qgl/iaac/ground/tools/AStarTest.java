package fr.unice.polytech.si3.qgl.iaac.ground.tools;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.ArrayMap;
import fr.unice.polytech.si3.qgl.iaac.carte.Case;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by florian on 03/03/2017.
 */
public class AStarTest {

    private AStar aStar;
    private Point location;
    private Point target;
    private ArrayMap map;

    private java.util.List<Case> list;
    private ReadJSON json;

    public static final int size = 10;
    public static final int border = 2;

    @Before
    public void defineWay() {
        location = new Point(3,3);
        target = new Point(6,3);
        list = new ArrayList<>();
        json = new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        createAnIsland();
        map = new ArrayMap(list);
        aStar = new AStar(location, target, map );
    }

    private void createAnIsland() {

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int i = 0; i < border ; i++) {
            for (int j = 0; j < border; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < border; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

        for (int i = size - border; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

        for (int i = border; i < size - border ; i++) {
            for (int j = size - border; j < size; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < size - border; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

    }

    @Test
    public void ShouldReachTile(){
        aStar.compute();
        Deque<Point> way = aStar.getWay();
        assertEquals(3, way.size());
        for (int i = 4; i < 6; i++) {
            assertEquals(new Point(i,3),way.pop());
        }
    }

    @Test
    public void ShouldReachTile2(){
        location = new Point(2,4);
        target = new Point(6,3);
        createAnIsland2();
        map = new ArrayMap(list);
        aStar = new AStar(location, target, map );
        aStar.compute();
        Deque<Point> way = aStar.getWay();
        assertEquals(11, way.size());

    }


    private void createAnIsland2() {

        list.clear();
        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


            for (int j =  border; j < size-border; j++) {
                Case tile = new Case(new Point(4,j));
                tile.update(json);
                list.add(tile);
            }


        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int j =  0; j < border; j++) {
            Case tile = new Case(new Point(4,j));
            tile.update(json);
            list.add(tile);
        }

        for (int j =  size-border; j < size; j++) {
            Case tile = new Case(new Point(4,j));
            tile.update(json);
            list.add(tile);
        }

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < size ; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

        for (int i = 5; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.add(tile);
            }
        }

    }
}

