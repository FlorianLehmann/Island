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
public class FurTest {
    Res ressource;

    @Before
    public void defineContext() {
        ressource=new Fur();
    }


    @Test
    public void getTabTest(){
        Fur.addFur(new Point(0,0));
        assertEquals(ressource.getTabMax(),new Point(0,0));
    }

    @Test
    public void hasRTest(){
        Fur.addFur(new Point(0,0));
        assertTrue(ressource.hasR());
    }


    @Test
    public void getNearest(){
        Fur.addFur(new Point(0,0));
        assertEquals(ressource.getNearest(new Point(0,0)),new Point(0,0));
    }
}
