package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.WEST;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class EchoRightTest {

    private Drone drone;
    private State echoRight;
    private ReadJSON2 read;

    @Before
    public void ini() throws IOException {
        drone = new Drone(SOUTH);
        echoRight = new EchoRight();
        read = new ReadJSON2();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(echoRight.execute(drone),ECHO.toString(WEST.toString()));
    }


    @Test
    public void waitWithOceanRight() throws IOException {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(echoRight.nextState(read) instanceof Fly);
    }

    @Test
    public void waitWithGroundRight() throws IOException {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoRight.nextState(read) instanceof HeadingRight);
    }

}
