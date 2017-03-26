package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.map.Carte;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FISH;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by florian on 13/02/2017.
 */
public class CarteTest {

    private Carte carte;
    private ReadJSON readJSON;

    @Before
    public void defineContext() throws IOException {
        readJSON = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");;
        readJSON.read("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte(readJSON);

    }

    @Test
    public void ShouldHasFish() throws IOException {
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\", \"GRASSLAND\"], \"creeks\": [\"xy\"], \"sites\": []}, \"status\": \"OK\"}");
        assertFalse(carte.hasResource(FISH));
        carte.addAirCase(new Point(1,1));
        assertTrue(carte.hasResource(FISH));
    }

    @Test
    public void ShouldHasCreekID() throws IOException {
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\", \"GRASSLAND\"], \"creeks\": [\"xy\"], \"sites\": []}, \"status\": \"OK\"}");
        carte.addAirCase(new Point(1,1));
        assertEquals("xy", carte.getCreekID());
    }

    @Test
    public void ShouldHasCreek() throws IOException {
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\", \"GRASSLAND\"], \"creeks\": [\"xy\"], \"sites\": []}, \"status\": \"OK\"}");
        carte.addAirCase(new Point(1,1));
        assertTrue(carte.hasAcrique());
        assertEquals(new Point(1,1), carte.getACreek());
    }

    @Test
    public void getNearestResourceTest() throws IOException{
        carte.addAirCase(new Point(4,4));
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        carte.getCases().get(new Point(4,4)).update(readJSON);
        carte.addAirCase(new Point(5,5));
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"LAKE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        carte.getCases().get(new Point(5,5)).update(readJSON);
        carte.addAirCase(new Point(6,6));
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"TROPICAL_RAIN_FOREST\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        carte.getCases().get(new Point(6,6)).update(readJSON);
        assertEquals(carte.getNearestResource(WOOD,new Point(0,0)),new Point(6,6));
    }


}
