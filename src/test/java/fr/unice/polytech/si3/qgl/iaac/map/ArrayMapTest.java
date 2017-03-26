package fr.unice.polytech.si3.qgl.iaac.map;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMapTest {

    public static final int size = 10;
    public static final int border = 2;
    private Map<Point, Case> list;
    private ArrayMap map;
    private ReadJSON json;

    @Before
    public void defineContext() throws IOException {
        list = new HashMap<>();
        json = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        ;
        json.read("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        createAnIsland();
        map = new ArrayMap(list);
    }

    private void createAnIsland() throws IOException {

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i, j));
                tile.update(json);
                list.put(new Point(i, j), tile);
            }
        }

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


        for (int i = border; i < size - border; i++) {
            for (int j = border; j < size - border; j++) {
                Case tile = new Case(new Point(i, j));
                tile.update(json);
                list.put(new Point(i, j), tile);
            }
        }

    }

    @Test
    public void ShouldGetGroundCase() {
        for (int i = border; i < size - border; i++) {
            for (int j = border; j < size - border; j++) {
                assertTrue(!map.get(new Point(i, j)).containOcean());
            }
        }
    }

    @Test
    public void ShouldBeAnEdge() {
        assertTrue(map.isEdge(new Point(2, 2)));
        assertTrue(map.isEdge(new Point(2, 3)));
        assertTrue(map.isEdge(new Point(3, 7)));
        assertTrue(map.isEdge(new Point(4, 7)));
        assertTrue(map.isEdge(new Point(5, 7)));
        assertTrue(map.isEdge(new Point(2, 7)));
    }

    @Test
    public void ShouldNotBeAnEdge() {

        assertFalse(map.isEdge(new Point(1, 2)));
        //assertFalse(map.isEdge(new Point(3,3)));
        assertFalse(map.isEdge(new Point(8, 9)));

    }


    private void createAnIsland2() throws IOException {

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Case tile = new Case(new Point(i, j));
                tile.update(json);
                list.put(new Point(i, j), tile);
            }
        }

        json.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [], \"sites\": [\"id\"]}, \"status\": \"OK\"}");


        for (int i = border; i < size - border; i++) {
            for (int j = border; j < size - border; j++) {
                Case tile = new Case(new Point(i, j));
                tile.update(json);
                list.put(new Point(i, j), tile);
            }
        }

        Map<Point, Case> list2 = new HashMap<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j += 4) {
                list2.put(new Point(i, j), list.get(new Point(i, j)));
            }
        }


        list = list2;

    }

    @Test
    public void ShouldBeAnEdge2() throws IOException {
        createAnIsland2();
        assertTrue(map.isEdge(new Point(2, 2)));
        assertTrue(map.isEdge(new Point(2, 3)));
        assertTrue(map.isEdge(new Point(3, 7)));
        assertTrue(map.isEdge(new Point(4, 7)));
        assertTrue(map.isEdge(new Point(5, 7)));
        assertTrue(map.isEdge(new Point(2, 7)));

    }
}
