package fr.unice.polytech.si3.qgl.iaac.Carte;

import fr.unice.polytech.si3.qgl.iaac.carte.Array2D;
import fr.unice.polytech.si3.qgl.iaac.carte.Case;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by florian on 01/03/2017.
 */
public class Array2DTest {

    private Array2D array2d;

    @Before
    public void defineContext() {
        array2d = new Array2D();
    }

    @Test
    public void ShouldAddATile() {
        Case tile = new Case(new Point(1,1));
        array2d.add(tile);
        assertEquals(new Point(1,1),array2d.get(1,1).getCoords());
    }

    @Test
    public void ShouldAddATileWithNegativeCoordinate() {
        Case tile = new Case(new Point(1,1));
        Case tile2 = new Case(new Point(-1,-3));
        array2d.add(tile);
        array2d.add(tile2);
        assertEquals(new Point(1,1),array2d.get(1,1).getCoords());
        assertEquals(new Point(-1,-3),array2d.get(-1,-3).getCoords());
        assertEquals(203, array2d.getSize());
    }

    @Test
    public void ShouldResizeArray() {
        Case tile = new Case(new Point(201,1));
        array2d.add(tile);
        assertEquals(new Point(201,1),array2d.get(201,1).getCoords());
        assertEquals(400,array2d.getSize());
    }


}
