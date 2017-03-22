package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.EST;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
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
        assertTrue(echoLeft.nextState(read) instanceof EchoRight);
    }

    @Test
    public void waitWithGroundLeft(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoLeft.nextState(read) instanceof HeadingLeft);
    }

}

