package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class OreTest {

    //tests
    Res ressource;

    @Before
    public void defineContext() {
        ressource = new Ore();
        ressource = new Ore();
    }


    @Test
    public void getTabTest() {
        Ore.addOre(new Point(0, 0));
        assertEquals(ressource.getTabMax(), new Point(0, 0));
    }

    @Test
    public void hasRTest() {
        assertTrue(ressource.hasR());
    }


    @Test
    public void getNearest() {
        Ore.addOre(new Point(0, 0));
        assertEquals(ressource.getNearest(new Point(0, 0)), new Point(0, 0));
    }

}
