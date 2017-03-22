package fr.unice.polytech.si3.qgl.iaac.air.findIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static fr.unice.polytech.si3.qgl.iaac.json.EnumJSON.FLY;
import static fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation.SOUTH;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 11/02/2017.
 */
public class FlyTest {

    private Drone drone;
    private State fly;
    private ReadJSON read;

    @Before
    public void ini() throws IOException {
        drone = new Drone(SOUTH);
        fly = new Fly();
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(fly.execute(drone),FLY.toString(""));
    }


    @Test
    public void waitTest() throws IOException {
        read.read("{ \"action\": \"fly\" }");
        assertTrue(fly.nextState(read) instanceof EchoLeft);
    }


}
