package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;

/**
 * Created by sebde on 05/12/2016.
 */
public class State10Test {
    private Drone drone;
    private Carte carte;
    private ReadJSON read;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(SUD,carte);
        drone.setState(new State10());
    }

    @Test
    public void ActionTest(){
        drone.getState().execute(drone);
        assertEquals(FLY.toString(""),drone.getAction());
    }

    @Test
    public void WaitTest() {
        read.read("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");
        drone.setCaseToTarget(4);
        while(drone.getCaseToTarget() > 0) {
            drone.getState().wait(drone);
            assertTrue(drone.getState() instanceof State10);
            drone.setCaseToTarget(drone.getCaseToTarget() - 1);
        }
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State12);

    }

}
