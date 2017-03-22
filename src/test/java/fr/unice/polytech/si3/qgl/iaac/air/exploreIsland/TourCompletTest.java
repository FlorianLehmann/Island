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
 * Created by sebde on 12/02/2017.
 */
public class TourCompletTest {

    ReadJSON read ;
    Drone drone;
    State state;

    @Before
    public void ini() throws IOException {
        drone = new Drone(EnumOrientation.WEST);
        state = new TourComplet(0);
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"S\"}");
    }

    @Test
    public void executeState0FLyTest(){
        assertEquals(state.execute(drone),"{ \"action\": \"fly\" }");
    }

    @Test
    public void executeState1EchoLeftTest(){
        state = new TourComplet(1);
        assertEquals(state.execute(drone),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"N\" } }");
    }

    @Test
    public void executeState2HeadingFromWestToNorthTest(){
        state = new TourComplet(2);
        assertEquals(state.execute(drone),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"N\" } }");
    }

    @Test
    public void executeState3FlyTest(){
        state = new TourComplet(3);
        assertEquals(state.execute(drone),"{ \"action\": \"fly\" }");
    }

    @Test
    public void executeState4HeadingFromNorthToEastTest(){
        drone = new Drone(EnumOrientation.NORTH);
        state = new TourComplet(4);
        assertEquals(state.execute(drone),"{ \"action\": \"heading\", \"parameters\": { \"direction\":\"E\" } }");
    }

    @Test
    public void waitTest(){
        assertTrue(state.nextState(read) instanceof TourComplet);
        for(int i=0;i<5;i++) {
            state.execute(drone);
            state = state.nextState(read);
        }
        assertTrue(state instanceof EchoFront8);
    }
}
