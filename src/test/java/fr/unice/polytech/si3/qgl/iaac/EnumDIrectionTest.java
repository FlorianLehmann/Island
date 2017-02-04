package fr.unice.polytech.si3.qgl.iaac;

import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.LEFT;
import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.RIGHT;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.EST;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.NORTH;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.WEST;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Quentin on 04/02/2017.
 */
public class EnumDIrectionTest {
    private EnumOrientation orientation;

    @Test
    public void getDirectionTest(){
        orientation = NORTH;
        assertEquals(NORTH, LEFT.getDirection(WEST));

    }

    @Test
    public void getDirectionTest2(){
        orientation = NORTH;
        assertEquals(NORTH, RIGHT.getDirection(EST));

    }
    @Test
    public void getDirectionTest3(){
        orientation = NORTH;
        assertNotEquals(NORTH, RIGHT.getDirection(WEST));

    }
}
