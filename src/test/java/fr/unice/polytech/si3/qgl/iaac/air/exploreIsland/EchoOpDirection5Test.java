package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebde on 11/02/2017.
 */
public class EchoOpDirection5Test {

    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() {
        drone = new Drone(EnumOrientation.EST);
        state = new EchoOpDirection5();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }
    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"N\" } }");
    }

    @Test
    public void waitWithGroudRange2(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(state.nextState(read) instanceof Fly6);
    }

    @Test
    public void waitWithGroundRange4(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 4, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(state.nextState(read) instanceof DemiTour7);
    }
    @Test
    public void waitWithoutGround(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(state.nextState(read) instanceof DemiTour7);
    }
}
