package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.HEADING;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.EST;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
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
    public void ini(){
        drone = new Drone(SOUTH);
        HeadingLeft = new HeadingLeft();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(HeadingLeft.execute(drone),HEADING.toString(EST.toString()));
    }


    @Test
    public void waitTest(){
        read.read("{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" } }");
        assertTrue(HeadingLeft.wait(read) instanceof EchoFace);
    }


}
