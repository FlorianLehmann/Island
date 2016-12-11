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
        drone.setState(new State15());
    }

    @Test
    public void ActionTest(){
        drone.setLastDirection("R");
        drone.getState().execute(drone);
        assertEquals(ECHO.toString(drone.getDirection().left()),drone.getAction());
    }

    @Test
    public void Action2Test(){
        drone.setLastDirection("L");
        drone.getState().execute(drone);
        assertEquals(ECHO.toString(drone.getDirection().right()),drone.getAction());
    }

    @Test
    public void GroundTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        drone.setCaseToTarget(1);
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State16);
    }

    @Test
    public void GroundTest2() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 4, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        drone.setCaseToTarget(1);
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State17);
    }


    @Test
    public void OceanTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 3, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        drone.setCaseToTarget(1);
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State17);
    }

    @Test
    public void OutOfRangeTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State11);
    }
}
