package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State12 implements State {

    /**
     *
     * Demande un SCAN
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(SCAN.toString(""));
    }

    /**
     *
     * Analyse du résultat
     *
     */
    @Override
    public void wait(Drone drone) {
	if ( drone.hasPU() && drone.hasCreek() ) {
	    drone.setState(new State11());
	}
	else {
	    drone.setState(new State13());
	}
    }

}
