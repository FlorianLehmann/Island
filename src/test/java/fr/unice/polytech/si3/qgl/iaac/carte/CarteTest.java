package fr.unice.polytech.si3.qgl.iaac.carte;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;

import fr.unice.polytech.si3.qgl.iaac.carte.CarteTest;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.Creek;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.PU;
import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;

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
    
    @Test
    public void pasCrique() {
        assertEquals(carte.getNbCreek(), 0);
    }

    @Test
    public void getCoordsCreekTest(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new Creek("creek"),new Point(0,0));
        assertEquals(carte.getCoordCreek(),new Point(0,0));
    }

    @Test
    public void hasPuTestFalse(){
        assertFalse(carte.hasPU());
    }

    @Test
    public void hasPuTestTrue(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new PU("pu"),new Point(0,0));
        assertTrue(carte.hasPU());
    }

    @Test
    public void getCasePuTest(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new PU("pu"),new Point(0,0));
        assertEquals(carte.getCasePU().getCoords(),new Point(0,0));
    }

    @Test
    public void getPuTest(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new PU("pu"),new Point(0,0));
        assertEquals(carte.getPU(),"pu");
    }

    @Test
    public void getcreekTest(){
        carte.addCase(new Point(0,0));
        carte.setPOI(new Creek("creek05"),new Point(0,0));
        assertEquals(carte.getCaseCreek(),"creek05");
    }

    @Test
    public void getNearestCreekTest(){
        carte.addCase(new Point(0,0));
        carte.addCase(new Point(10,10));
        carte.addCase(new Point(100,100));
        carte.setPOI(new PU("pu"),new Point(0,0));
        carte.setPOI(new Creek("idcreek1"),new Point(100,100));
        carte.setPOI(new Creek("idcreek2"),new Point(10,10));
        assertEquals(carte.getNearestCreekPU(),"idcreek2");
    }
    

}
