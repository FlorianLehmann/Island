package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.ECHO;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.EnumOrientation.WEST;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class EchoRightTest {

    private Drone drone;
    private State echoRight;
    private ReadJSON read;

    @Before
    public void ini(){
        drone = new Drone(SOUTH);
        echoRight = new EchoRight();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(echoRight.execute(drone),ECHO.toString(WEST.toString()));
    }


    @Test
    public void waitWithOceanFace(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(echoRight.wait(read) instanceof Fly);
    }

    @Test
    public void waitWithGroundFace(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(echoRight.wait(read) instanceof HeadingRight);
    }

    @Test
    public void isOverTest(){
        echoRight.execute(drone);
        assertEquals(echoRight.isOver(),true);
    }
}
