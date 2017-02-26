package fr.unice.polytech.si3.qgl.iaac.ground;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.*;
import static fr.unice.polytech.si3.qgl.iaac.resources.EnumPrimaryResources.WOOD;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Quentin on 19/02/2017.
 */
public class MenTest {

    Men men;

    @Before
    public void ini(){
        Point coords = new Point(3,3);
        men = new Men(coords);
    }

    @Test
    public void moveToNorth(){
        Point point = new Point(3,4);
        assertEquals(men.moveTo(NORTH), MOVETO.toString(NORTH.front().toString()));
        assertEquals(men.getCoord(),point);
    }

    @Test
    public void moveToSouth(){
        Point point = new Point(3,2);
        assertEquals(men.moveTo(SOUTH), MOVETO.toString(SOUTH.front().toString()));
        assertEquals(men.getCoord(),point);
    }

    @Test
    public void moveToEST(){
        Point point = new Point(4,3);
        assertEquals(men.moveTo(EST), MOVETO.toString(EST.front().toString()));
        assertEquals(men.getCoord(),point);

    }

    @Test
    public void moveToWest(){
        Point point = new Point(2,3);
        assertEquals(men.moveTo(WEST), MOVETO.toString(WEST.front().toString()));
        assertEquals(men.getCoord(),point);

    }

    @Test
    public void exploreTest(){
        assertEquals(men.explore(), EXPLORE.toString(""));
    }

    @Test
    public void exploitWood(){
        assertEquals(men.exploit(WOOD), EXPLOIT.toString(WOOD.toString()));
    }

    @Test
    public void glimpseTest(){
        assertEquals(men.glimpse(NORTH,4), GLIMPSE.toString(NORTH.toString(),4));
    }

    @Test
    public void scoutTest(){
        assertEquals(men.scout(NORTH), SCOUT.toString(NORTH.toString()));
    }
}
