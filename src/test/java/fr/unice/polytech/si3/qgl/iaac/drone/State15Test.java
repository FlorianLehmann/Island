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
public class State15Test {
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
        drone.setState(new State15());
        drone.setLastDirection("R");
        drone.getState().execute(drone);
        assertEquals(ECHO.toString(drone.getDirection().left()),drone.getAction());
    }

    @Test
    public void Action2Test(){
        drone.setState(new State15());
        drone.setLastDirection("L");
        drone.getState().execute(drone);
        assertEquals(ECHO.toString(drone.getDirection().right()),drone.getAction());
    }
}
