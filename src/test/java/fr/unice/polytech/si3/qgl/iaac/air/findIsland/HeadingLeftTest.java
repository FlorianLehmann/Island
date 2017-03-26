package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.EST;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.HEADING;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class HeadingLeftTest {

    private Drone drone;
    private State HeadingLeft;
    private ReadJSON read;

    @Before
    public void ini() throws IOException {
        drone = new Drone(SOUTH);
        HeadingLeft = new HeadingLeft();
        read = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(HeadingLeft.execute(drone),HEADING.toString(EST.toString()));
    }


    @Test
    public void waitTest() throws IOException {
        read.read("{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" } }");
        assertTrue(HeadingLeft.nextState(read) instanceof EchoFace);
    }


}
