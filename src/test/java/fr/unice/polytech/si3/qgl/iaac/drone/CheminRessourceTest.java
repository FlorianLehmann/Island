package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;
import static org.junit.Assert.*;

public class CheminRessourceTest {
    private ReadJSON read;
    private Drone drone;
    private Carte carte;
    
    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(SUD, new Carte());
        drone.setState(new EchoFront());
    }

    @Test
    public void ActionTest() {
	drone.getState().execute(drone);
	assertEquals(ECHO.toString(drone.getDirection().front()),drone.getAction());
    }

    @Test
    public void GroundTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        drone.getState().wait(drone);
        assertEquals(drone.getCaseToTarget(),2);
        assertTrue(drone.getState() instanceof State10);
        assertEquals("G",drone.getLastDirection());
    }
    
    @Test
    public void OceanTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof EchoLeft);
    }
    
    @Test
    public void OutOfRangeTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof Stop);
    }
    

}
