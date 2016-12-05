package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static junit.framework.TestCase.assertEquals;

import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import fr.unice.polytech.si3.qgl.iaac.carte.poi.*;
import org.junit.*;

/**
 * Created by sebde on 05/12/2016.
 */
public class State10Test {
    private Drone drone;
    private Carte carte;

    @Before
    public void init() {
        carte = new Carte();
        drone = new Drone(NORD,carte);
        drone.setState(new State10());
    }

    @Test
    public void ActionTest(){
        drone.getState().execute(drone);
        assertEquals(drone.getAction(),FLY.toString(drone.getDirection().front()));
    }
}
