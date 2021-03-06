package fr.unice.polytech.si3.qgl.iaac.air;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.NORTH;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.WEST;
import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Quentin on 05/02/2017.
 */
public class DroneTest {

    Drone drone;

    @Before
    public void iniDrone(){
        drone = new Drone(NORTH);
    }
    @Test
    public void echoFrontTest(){
        assertEquals(drone.echo(FRONT), ECHO.toString(NORTH.toString()));
    }

    @Test
    public void multipleEchoLeft(){
        assertEquals(drone.echo(LEFT), ECHO.toString(WEST.toString()));
        assertEquals(drone.echo(LEFT), ECHO.toString(WEST.toString()));
    }

    @Test
    public void flyTest(){
        Point point = new Point(0,3);
        assertEquals(drone.fly(), FLY.toString(""));
        assertEquals(drone.getCoord(), point);
    }

    @Test
    public void headingLeftTest(){
        Point point = new Point(-3,3);
        assertEquals(drone.heading(LEFT),HEADING.toString(NORTH.left().toString()));
        assertEquals(drone.getCoord(),point);
        assertEquals(drone.getLastDirection(),LEFT);
    }

    @Test
    public void headingRightTest(){
        Point point = new Point(3,3);
        assertEquals(drone.heading(RIGHT),HEADING.toString(NORTH.right().toString()));
        assertEquals(drone.getCoord(),point);
        assertEquals(drone.getLastDirection(),RIGHT);
    }

    @Test
    public void scanTest(){
        assertEquals(drone.scan(), SCAN.toString(""));
    }

}
