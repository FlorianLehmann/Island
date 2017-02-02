package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

/**
 * Created by sebde on 14/01/2017.
 */
public class QuartzTest {

    Res quartz;

    @Before
    public void defineContext() {
        quartz=new Quartz();
    }

    @Test
    public void getTabTest(){
        Quartz.addQuartz(new Point(0,0));
        assertEquals(quartz.getTabMax(),new Point(0,0));
    }

    @Test
    public void hasRTest(){
        assertTrue(quartz.hasR());
    }


    @Test
    public void getNearest(){
        Quartz.addQuartz(new Point(0,0));
        assertEquals(quartz.getNearest(new Point(0,0)),new Point(0,0));
    }


}
