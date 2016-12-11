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
public class State12Test {
    private Drone drone;
    private Carte carte;
    private ReadJSON read;

    @Before
    public void init() {
        read = new ReadJSON();
        read.read("{\"men\": 12,\"budget\": 10000,\"contracts\": [{ \"amount\": 600, \"resource\": \"WOOD\" },{ \"amount\": 200, \"resource\": \"GLASS\" }],\"heading\": \"W\"}");
        carte = new Carte();
        drone = new Drone(SUD,carte);
        drone.setState(new State12());
    }

    @Test
    public void ActionTest(){
        drone.getState().execute(drone);
        assertEquals(SCAN.toString(""),drone.getAction());
    }

    @Ignore
    @Test
    public void PUCreekTest() {
        for(int i=0;i<7;i++){drone.setCreek(""+i);}
        read.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [1o65o], \"sites\": [22ht8]}, \"status\": \"OK\"}");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State26);
    }

    @Test
    public void NoPUCreekTest() {
        read.read("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [], \"sites\": []}, \"status\": \"OK\"}");
        drone.getState().wait(drone);
        assertTrue(drone.getState() instanceof State13);
    }

}
