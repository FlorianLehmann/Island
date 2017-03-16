package fr.unice.polytech.si3.qgl.iaac.Carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.ArrayMap;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.carte.Case;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMapTest {

    private Map<Point, Case> list;
    private ArrayMap map;
    private ReadJSON json;

    public static final int size = 10;
    public static final int border = 2;

    @Before
    public void defineContext() {
        list = new HashMap<>();
        json = new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        createAnIsland();
        map = new ArrayMap(list);
    }

    private void createAnIsland() {

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.put(new Point(i,j),tile);
            }
        }

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < size - border; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.put(new Point(i,j),tile);
            }
        }

    }

    @Test
    public void ShouldGetGroundCase() {
        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < size - border; j++) {
                assertTrue(!map.get(new Point(i,j)).containOcean());
            }
        }
    }

    @Ignore
    @Test
    public void ShouldBeAnEdge() {
        assertTrue(map.isEdge(new Point(2,2)));
        assertTrue(map.isEdge(new Point(2,3)));
        assertTrue(map.isEdge(new Point(3,7)));
        assertTrue(map.isEdge(new Point(4,7)));
        assertTrue(map.isEdge(new Point(5,7)));
        assertTrue(map.isEdge(new Point(2,7)));
    }

    @Test
    public void ShouldNotBeAnEdge() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map.isEdge(new Point(i,j)) + " ");
            }
            System.out.println();
        }
        assertFalse(map.isEdge(new Point(1,2)));
        //assertFalse(map.isEdge(new Point(3,3)));
        assertFalse(map.isEdge(new Point(8,9)));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map.isEdge(new Point(i,j)) + " ");
            }
            System.out.println();
        }
    }


    private void createAnIsland2() {

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.put(new Point(i,j),tile);
            }
        }

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < size - border; j++) {
                Case tile = new Case(new Point(i,j));
                tile.update(json);
                list.put(new Point(i,j),tile);
            }
        }

        Map<Point, Case> list2= new HashMap<>();

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j+=4) {
                list2.put(new Point(i,j),list.get(new Point(i,j)));
            }
        }


        list = list2;

    }

    @Ignore
    @Test
    public void ShouldBeAnEdge2() {
        createAnIsland2();
        map = new ArrayMap(list);
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " +map.isEdge(new Point(i,j)));
            }
            System.out.println(" ");
        }

        assertTrue(map.isEdge(new Point(2,2)));
        assertTrue(map.isEdge(new Point(2,3)));
        assertTrue(map.isEdge(new Point(3,7)));
        assertTrue(map.isEdge(new Point(4,7)));
        assertTrue(map.isEdge(new Point(5,7)));
        assertTrue(map.isEdge(new Point(2,7)));

    }
}
