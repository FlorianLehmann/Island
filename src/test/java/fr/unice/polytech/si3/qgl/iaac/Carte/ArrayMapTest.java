package fr.unice.polytech.si3.qgl.iaac.Carte;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.ArrayMap;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.carte.Case;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by florian on 03/03/2017.
 */
public class ArrayMapTest {

    private List<Case> list;
    private ArrayMap map;
    private ReadJSON json;

    public static final int size = 10;
    public static final int border = 2;

    @Before
    public void defineContext() {
        list = new ArrayList<>();
        json = new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        createAnIsland();
        map = new ArrayMap(list);
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
    public void ShouldGetGroundCase() {
        for (int i = border; i < size - border ; i++) {
            for (int j = border; j < size - border; j++) {
                assertTrue(!map.get(i,j).containOcean());
            }
        }
    }

    @Test
    public void ShouldBeAnEdge() {
        assertTrue(map.isEdge(2,2));
        assertTrue(map.isEdge(2,7));
    }

    @Test
    public void ShouldNotBeAnEdge() {
        assertFalse(map.isEdge(1,2));
        assertFalse(map.isEdge(3,3));
        assertFalse(map.isEdge(8,9));
    }
}
