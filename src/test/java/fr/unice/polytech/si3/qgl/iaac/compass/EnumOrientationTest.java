package fr.unice.polytech.si3.qgl.iaac.compass;

import org.junit.Ignore;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.NORTH;
import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 22/03/2017.
 */
public class EnumOrientationTest {

    private EnumOrientation enumOrientation;

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void getANonExistingOrientation() {
        EnumOrientation.getEnumDirection("");
    }

    @Ignore
    @Test
    public void getAnExistingOrientation() {
        assertEquals(NORTH ,EnumOrientation.getEnumDirection("NORTH"));
    }

}
