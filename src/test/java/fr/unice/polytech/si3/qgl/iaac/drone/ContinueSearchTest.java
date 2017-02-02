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
public class ContinueSearchTest {

    private ReadJSON read;
    private Drone drone;
    private Carte carte;
    private int nbCaseRight;
    private int nbCaseLeft;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(NORD, carte);
        drone.setState(new ContinueSearch());
    }

    @Test
    public void ActionTest() {
        nbCaseRight=10;
        nbCaseLeft=20;
        drone.setNbCaseRight(nbCaseRight);
        drone.setNbCaseLeft(nbCaseLeft);
        drone.setState(new ContinueSearch());
        drone.getState().execute(drone);
        assertEquals(HEADING.toString(NORD.left()), drone.getAction());
    }

    @Test
    public void ActionTest2() {
        nbCaseRight=20;
        nbCaseLeft=10;
        drone.setNbCaseRight(nbCaseRight);
        drone.setNbCaseLeft(nbCaseLeft);
        drone.setState(new ContinueSearch());
        drone.getState().execute(drone);
        assertEquals(HEADING.toString(NORD.right()), drone.getAction());
    }

    @Test
    public void WaitTest() {
        read.read("{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof EchoFront);
    }
}

