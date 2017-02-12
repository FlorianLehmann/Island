package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.air.exploreIsland.FlyToEarth4;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.EST;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.SOUTH;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class EchoLeftTest {

    private Drone drone;
    private State echoLeft;
    private ReadJSON read;

    @Before
    public void ini(){
        drone = new Drone(SOUTH);
        echoLeft = new EchoLeft();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(echoLeft.execute(drone),ECHO.toString(EST.toString()));
    }


    @Test
    public void waitWithOceanFly(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(echoLeft.wait(read) instanceof EchoRight);
    }

    @Test
    public void waitWithGroundLeft(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoLeft.wait(read) instanceof HeadingLeft);
    }

    @Test
    public void isOverTest(){
        echoLeft.execute(drone);
        assertEquals(echoLeft.isOver(),true);
    }
}

