package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by florian on 15/01/2017.
 */
public class WoodTest {
    Wood ressource;

    @Before
    public void defineContext() {
        ressource=new Wood();
    }

    @Test
    public void getTabTest(){
        Wood.addWood(new Point(0,0));
        assertEquals(ressource.getTabMax(),new Point(0,0));
    }

    @Test
    public void hasRTest(){
        assertTrue(ressource.hasR());
    }


    @Test
    public void getNearest(){
        Wood.addWood(new Point(0,0));
        assertEquals(ressource.getNearest(new Point(0,0)),new Point(0,0));
    }
}
