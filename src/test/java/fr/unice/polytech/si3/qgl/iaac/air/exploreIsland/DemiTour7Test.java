package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 11/02/2017.
 */
public class DemiTour7Test {

    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() throws IOException {
        drone = new Drone(EnumOrientation.EST);
        state = new DemiTour7(0);
        read = new ReadJSON("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }
    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"N\" } }");
        state=new DemiTour7(1);
        assertEquals(state.execute(drone),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"E\" } }");

    }

    @Test
    public void waitTestWithState0(){
        assertTrue(state.nextState(read) instanceof DemiTour7);
    }

    @Test
    public void waitTestWithState1(){
        state=new DemiTour7(1);
        assertTrue(state.nextState(read) instanceof EchoFront8);
    }
}
