package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebde on 11/02/2017.
 */
public class EchoFront8Test {

    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() {
        drone = new Drone(EnumOrientation.EST);
        state = new EchoFront8();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }
    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"E\" } }");
    }

    @Test
    public void waitWithGround(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        assertTrue(state.wait(read) instanceof FlyToEarth4);
    }

    @Test
    public void waitWithoutGround(){
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\"}");
        assertTrue(state.wait(read) instanceof TourComplet);
    }
}
