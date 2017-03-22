package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.WEST;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class HeadingRightTest {

    private Drone drone;
    private State HeadingRight;
    private ReadJSON2 read;

    @Before
    public void ini() throws IOException {
        drone = new Drone(SOUTH);
        HeadingRight = new HeadingRight();
        read = new ReadJSON2();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(HeadingRight.execute(drone),HEADING.toString(WEST.toString()));
    }


    @Test
    public void waitTest() throws IOException {
        read.read("{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" } }");
        assertTrue(HeadingRight.nextState(read) instanceof EchoFace);
    }

}
