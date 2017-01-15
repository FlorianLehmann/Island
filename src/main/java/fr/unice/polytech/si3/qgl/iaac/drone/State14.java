package fr.unice.polytech.si3.qgl.iaac.drone;

import fr.unice.polytech.si3.qgl.iaac.ReadJSON;

import static fr.unice.polytech.si3.qgl.iaac.EnumJSON.FLY;

public class State14 implements State {

    /**
     * Le drone avance d'une case
     */
    @Override
    public void execute(Drone drone) {
        drone.setAction(FLY.toString(""));
        drone.changeCoord(FLY, drone.getDirection());
    }

    /**
     * Retour a l'etat initial
     */
    @Override
    public void wait(Drone drone) {
        drone.subBudget((int) ReadJSON.getInformations().get("cost"));
        drone.setState(new State12());
    }
}
