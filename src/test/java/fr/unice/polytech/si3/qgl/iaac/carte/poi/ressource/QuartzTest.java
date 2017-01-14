package fr.unice.polytech.si3.qgl.iaac.carte.poi.ressource;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

/**
 * Created by sebde on 14/01/2017.
 */
public class QuartzTest {

    @Test
    public void getTabTest(){
        Res quartz=new Quartz();
        assertEquals(quartz.getTabMax(),new Point(94,94));
    }

    @Test
    public void hasRTest(){
        Res quartz=new Quartz();
        assertFalse(quartz.hasR());
    }

/*
    @Test
    public void getNearest(){
        Res quartz=new Quartz();
        Quartz.addQuartz(new Point(0,0));
        assertEquals(quartz.getNearest(new Point(0,0)),new Point(0,0));
    }
    */

}
