package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;

/**
 * Created by sebde on 05/12/2016.
 */
public class StopTest {
    private Drone drone;
    private Carte carte;
    private ReadJSON read;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(SUD,carte);
    }

    @Test
    public void ActionTest(){
        drone.setState(new Stop());
        drone.getState().execute(drone);
        assertEquals(STOP.toString(""),drone.getAction());
    }
}
