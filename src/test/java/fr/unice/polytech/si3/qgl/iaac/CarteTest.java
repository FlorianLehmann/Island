package fr.unice.polytech.si3.qgl.iaac;

import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.FISH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by florian on 13/02/2017.
 */
public class CarteTest {

    private Carte carte;
    private ReadJSON readJSON;

    @Before
    public void defineContext() {
        readJSON = new ReadJSON("{ \"men\": 12, \"budget\": 10000, \"contracts\": [ { \"amount\": 600, \"resource\": \"WOOD\" }, " +
                "{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte(readJSON);

    }

    @Test
    public void ShouldHasFish() {
        readJSON.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\", \"GRASSLAND\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        assertFalse(carte.hasResource(FISH));
        carte.addAirCase(new Point(1,1));
        assertTrue(carte.hasResource(FISH));
    }


}
