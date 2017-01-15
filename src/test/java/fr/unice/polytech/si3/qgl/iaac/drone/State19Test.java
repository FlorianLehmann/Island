package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;
/**
 * Created by Quentin on 06/12/2016.
 */
public class State19Test {
    private ReadJSON read;
    private Drone drone;
    private Carte carte;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(NORD, carte);
        drone.setState(new State19());

    }


    @Test
    public void ActionTest() {
        drone.getState().execute(drone);
        assertEquals(ECHO.toString(drone.getDirection().front()), drone.getAction());
    }

    @Test
    public void GroundTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 4, \"found\": \"GROUND\" }, \"status\": \"OK\" }");
        drone.setCaseToTarget(1);
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State20);
    }


    @Test
    public void OceanTest() {
        read.read("{ \"cost\": 1, \"extras\": { \"range\": 3, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");
        drone.setCaseToTarget(1);
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof DemiTour1);
    }

}
