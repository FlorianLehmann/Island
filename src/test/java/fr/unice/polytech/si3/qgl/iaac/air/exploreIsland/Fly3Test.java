package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.compass.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import fr.unice.polytech.si3.qgl.iaac.json.ReadJSON2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 11/02/2017.
 */
public class Fly3Test {
    ReadJSON2 read ;
    Drone drone;
    State state;

    @Before
    public void ini() throws IOException {
        drone = new Drone(EnumOrientation.EST);
        state = new Fly3();
        read = new ReadJSON2();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"fly\" }");

    }

    @Test
    public void waitTest(){
        assertTrue(state.nextState(read) instanceof Scan1);
    }
}
