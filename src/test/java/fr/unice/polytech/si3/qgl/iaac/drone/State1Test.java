package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import org.junit.*;

public class State1Test {

    private Drone drone;
    
    @Before
    public void init() {
	drone = new Drone(NORD);
    }

    @Test
    public void ActionTest() {
	drone.setState(new State1());
	drone.getState().execute();
	assertEquals(ECHO.toString(drone.getDirection().front()), drone.getAction());
    }
    

}
