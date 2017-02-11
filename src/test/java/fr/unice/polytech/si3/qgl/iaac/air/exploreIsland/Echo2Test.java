package fr.unice.polytech.si3.qgl.iaac.air.exploreIsland;

import fr.unice.polytech.si3.qgl.iaac.EnumOrientation;
import fr.unice.polytech.si3.qgl.iaac.air.Drone;
import fr.unice.polytech.si3.qgl.iaac.air.State;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sebde on 11/02/2017.
 */
public class Echo2Test {

    @Test
    public void executeTest(){
        Drone drone=new Drone(EnumOrientation.EST);
        State state=new Echo2();
        assertEquals(state.execute(drone),"{ \"action\": \"echo\", \"parameters\": { \"direction\":\"E\" } }");
    }
}
