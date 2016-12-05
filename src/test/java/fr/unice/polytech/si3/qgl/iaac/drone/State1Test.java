package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.drone.Drone.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumDirection.*;
import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.carte.Carte;
import org.junit.*;
import static org.junit.Assert.*;

public class State1Test {

    private Drone drone;
    private Carte carte;
    
    @Before
    public void init() {
	carte = new Carte();
	drone = new Drone(NORD, carte);
		
    }

    @Ignore
    public void ActionTest() {
	init();
	drone.setState(new State1());
	drone.getState().execute(drone);
	assertEquals(ECHO.toString(drone.getDirection().front()), drone.getAction());
	//assertTrue(true);
    }
    

}
