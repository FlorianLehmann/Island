package fr.unice.polytech.si3.qgl.iaac.drone;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.*;
import fr.unice.polytech.si3.qgl.iaac.EnumDirection;
import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

public class State14 implements State {

    /**
     *
     * Le drone avance d'une case
     *
     */
    @Override
    public void execute(Drone drone) {
	drone.setAction(FLY.toString(""));
    }

    /**
     *
     * Retour a l'etat initial
     *
     */
    @Override
    public void wait(Drone drone) {
	drone.setState(new State12());
    }
}
