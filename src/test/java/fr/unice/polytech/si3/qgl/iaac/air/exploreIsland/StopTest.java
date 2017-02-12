package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 11/02/2017.
 */
public class StopTest {
    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() {
        drone = new Drone(EnumOrientation.EST);
        state = new Stop();
        read = new ReadJSON("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }
    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"stop\" }");
    }
}
