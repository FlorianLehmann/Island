package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 11/02/2017.
 */
public class Echo2Test {

    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() {
        drone = new Drone(EnumOrientation.EST);
        state = new Echo2();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"E\" } }");
    }

    @Test
    public void waitWithGround(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(state.nextState(read) instanceof FlyToEarth4);
    }
    @Test
    public void waitWithoutGroung(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(state.nextState(read) instanceof EchoOpDirection5);
    }
}
