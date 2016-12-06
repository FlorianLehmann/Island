package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import static org.junit.Assert.assertEquals;


import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;

/**
 * Created by Quentin on 06/12/2016.
 */
public class State4Test {

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

    }

    @Ignore
    @Test
    public void ActionTest() {
        nbCaseRight=10;
        nbCaseLeft=20;
        drone.setState(new State4());
        drone.getState().execute(drone);
        assertEquals(HEADING.toString(drone.getDirection().left()), drone.getAction());
    }
}

