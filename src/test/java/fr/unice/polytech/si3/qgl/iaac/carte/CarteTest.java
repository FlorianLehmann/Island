package fr.unice.polytech.si3.qgl.iaac.carte;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;

import fr.unice.polytech.si3.qgl.iaac.carte.CarteTest;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.Creek;
import org.junit.*;

import java.awt.*;

/**
 * Created by sebde on 05/12/2016
 */
public class CarteTest {

private Carte carte;
    @Before
    public void init(){
        carte=new Carte();
    }
    @Test
    public void testSetCreek(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new Creek("creek1"),new Point(0,0));
        assertEquals(carte.hasCreek(),true);
    }
}
